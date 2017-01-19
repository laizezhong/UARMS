package com.ray.demo.admin.manager.pdfmaker;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import com.itextpdf.text.DocumentException;
import com.ray.demo.admin.manager.BaseManager;
import com.ray.demo.admin.model.system.SysUser;
import com.ray.demo.admin.service.system.UserService;
import com.ray.demo.admin.util.PdfMaker;

@Component
public class PdfManager extends BaseManager {

    @Autowired
    private UserService userService;
	
	public byte[] createPdf (HttpServletRequest req) throws IOException, DocumentException {
		
		String tplId = req.getParameter("tplId");
		
		switch (tplId) {
		
		case "testpdf":	
			
			List<SysUser> users = userService.listAll();
			
			Context ctx = new Context();
		    ctx.setVariable("message", "欢迎您访问PDF演示！！");
		    ctx.setVariable("users", users);
			return PdfMaker.makePdf(tplId, ctx);
		
		case "":
			
			
		}
		
		return null;
		
	}
	
	
	public byte[] renderPdf (String title, String htmlContent) throws IOException, DocumentException {
			
	    return PdfMaker.renderPdf(title, htmlContent);
		
	}
	
}
