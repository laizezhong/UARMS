package com.ray.demo.admin.webapp.controller.common;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.DocumentException;
import com.ray.demo.admin.manager.pdfmaker.PdfManager;
import com.ray.framework.controller.BaseController;
import com.ray.framework.webapp.rest.RestResultResponse;

@Controller
public class ImageUploadController extends BaseController {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired 
	private PdfManager pdfManager;

	@RequestMapping(value="/show-pdf", method = {RequestMethod.POST,RequestMethod.GET})
    public void showPdf (HttpServletResponse res, HttpServletRequest req) 
    		throws IOException,  DocumentException {
        
		OutputStream os = res.getOutputStream();
		String tplId = req.getParameter("tplId");
		if (tplId==null) return;
		
        try {
            
        	res.reset();
            
        	DateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
            String fileName = format.format(new Date()) + ".pdf";
            
            res.setHeader("Content-Disposition", "inline; filename=" + fileName);     
            res.setContentType("application/pdf; charset=utf-8");
 		    		    
			byte[] pdf = pdfManager.createPdf(req);
			
            os.write(pdf);
            os.flush();
                 
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
	
	@RequestMapping(value="/download-pdf", method = {RequestMethod.POST})
    public void downloadPdf (HttpServletResponse res, 
    		@RequestParam("content") String content,
    		@RequestParam("title") String title) throws IOException,  DocumentException {
	
		OutputStream os = res.getOutputStream();
	
        try {
            
        	res.reset();
            
        	DateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
            String fileName = format.format(new Date()) + ".pdf";
            
            res.setHeader("Content-Disposition", "attachment; filename=" + fileName);        
            res.setContentType("application/octet-stream; charset=utf-8");

            byte[] pdf = pdfManager.renderPdf(title, content);
			
            os.write(pdf);
            os.flush();
                 
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
	

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ResponseEntity<RestResultResponse> uploadFileHandler(@RequestParam("path") String path,
			@RequestParam("file") MultipartFile file) {

		try {

			String ob = upload(path, file);
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public ResponseEntity<RestResultResponse> uploadMultipleFileHandler(@RequestParam("path") String path,
			@RequestParam("file") MultipartFile[] files) {

		try {

			List<String> ob = new ArrayList<String>();		
			for (MultipartFile file : files) {
				String ret = upload(path, file);
				ob.add(ret);
			}
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	private String upload(String path, MultipartFile file) throws Exception {

		if (file.isEmpty()) {
			return "EMPTY_FILE";
		}
		
		String retStr = null;
		
		// PropertyUtils.getSmsProperty("upload_path");
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String filePath = rootPath + "/upload/" + path;
		
		File uploadPath = new File(filePath);
		
		if (!uploadPath.exists() && !uploadPath.isDirectory()) {
			uploadPath.mkdirs();
		}

		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(file.getBytes());
		String fileName = file.getOriginalFilename();
		String newName = new BigInteger(1, md5.digest()).toString(16)
				+ fileName.substring(fileName.lastIndexOf(".", fileName.length())).toLowerCase();

		File newFile = new File(filePath + System.getProperty("file.separator") + newName);
		if (!newFile.exists()) {
			file.transferTo(newFile);
			retStr = newName;
		} else {
			retStr = "FILE_EXISTED";
		}

		uploadPath = null;
		newFile = null;
		file = null;
		
		return retStr;
	}

}
