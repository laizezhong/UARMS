package com.ray.demo.admin.webapp.controller.demo;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;

import com.ray.demo.admin.util.PdfMaker;
import com.ray.framework.controller.BaseController;
import com.ray.framework.webapp.rest.RestResultResponse;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

	@RequestMapping(value="/number", method = RequestMethod.GET)
	public ResponseEntity<RestResultResponse> list(@ModelAttribute NumberModel model) {
		try {

			Object ob = model;
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value="/exportpdf", method = RequestMethod.GET)
    public void download(HttpServletResponse res, @RequestParam("tplId") String tplId) throws IOException {
        OutputStream os = res.getOutputStream();
        try {
            res.reset();
            //res.setHeader("Content-Disposition", "attachment; filename=test.pdf");
            res.setHeader("Content-Disposition", "inline; filename=test.pdf");
            //res.setContentType("application/octet-stream; charset=utf-8");
            
		    Context ctx = new Context();
		    ctx.setVariable("message", "你好， 中文测试");
		    
			byte[] pdf = PdfMaker.makePdf(tplId, ctx);
			
            os.write(pdf);
            os.flush();
        } catch (Exception ex) {
        	System.out.println(ex.getMessage());
         
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
	
	@ResponseBody
	@RequestMapping(value="/showpdf", method = RequestMethod.GET)
    public byte[] showPdf(HttpServletResponse res, @RequestParam("tplId") String tplId)  {
        //OutputStream os = res.getOutputStream();
        try {
            res.reset();
            res.setHeader("Content-Disposition", "attachment; filename=test.pdf");
            res.setContentType("application/octet-stream; charset=utf-8");
            
		    Context ctx = new Context();
		    ctx.setVariable("message", "你好， 中文测试");
		    
			byte[] pdf = PdfMaker.makePdf(tplId, ctx);
			
            return pdf;
            
        } catch (Exception ex) {
        	System.out.println(ex.getMessage());
        	return null;
        }
    }
	
	
	@RequestMapping(value="/testpdf", method = RequestMethod.GET)
	public ResponseEntity<byte[]> testPdf(@RequestParam("tplId") String tplId) {
		try {
		
			//Object ob = PdfMaker.makePdf(tplId);
			
		    Context ctx = new Context();
		    ctx.setVariable("message", "你好， 中文测试");
		    
			byte[] pdf = PdfMaker.makePdf(tplId, ctx);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		    //headers.setContentType(MediaType.parseMediaType("application/pdf"));
		    
		    //String filename = "test.pdf";
		    
		    String filename = new String(("中文文件名".trim()+".pdf").getBytes("gb2312"),"iso-8859-1");//解决中文乱码
		    
		    headers.setContentDispositionFormData("attachment", filename);

		    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdf, headers, HttpStatus.OK);

		    return response;
			


		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
}
