package com.ray.demo.admin.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.core.io.ClassPathResource;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

public class PdfMaker {
	
	public static byte[] makePdf(String tplId, Context ctx) 
			throws IOException, DocumentException {
		
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	    
	    templateResolver.setPrefix("pdftpl/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode("HTML5");
	    templateResolver.setCharacterEncoding("UTF-8");
	    
	    TemplateEngine templateEngine = new TemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver);

	    ctx.setLocale(Locale.SIMPLIFIED_CHINESE);
	    String htmlContent = templateEngine.process(tplId, ctx);

	    ITextRenderer renderer = new ITextRenderer();
	    ITextFontResolver fontResolver = renderer.getFontResolver();

	    ClassPathResource fontRes = new ClassPathResource("fonts/SIMSUN.TTC");
	    ClassPathResource cssRes = new ClassPathResource("pdftpl/template.html");
	    fontResolver.addFont(fontRes.getURL().toString(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

	    String cssPath = cssRes.getURL().toString();
	    int idx = cssPath.indexOf("/template.html");
	    String basePath = cssPath.substring(0, idx+1);

	    renderer.setDocumentFromString(htmlContent, basePath);
	    
	    renderer.layout();
	    ByteArrayOutputStream os = new ByteArrayOutputStream();
	    renderer.createPDF(os);

	    byte[] pdfAsBytes = os.toByteArray();
	    os.close();
	    
/*	    
	    FileOutputStream fos = new FileOutputStream(new File("/tmp/message.pdf"));
	    fos.write(pdfAsBytes);
	    fos.close();
*/

	    return pdfAsBytes;

	}
	
	public static byte[] renderPdf(String title, String content) 
			throws IOException, DocumentException {
		
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	    
	    templateResolver.setPrefix("pdftpl/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode("HTML5");
	    templateResolver.setCharacterEncoding("UTF-8");
	    
	    TemplateEngine templateEngine = new TemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver);

	    Context ctx = new Context();
	    ctx.setVariable("pageTitle", title);
	    ctx.setVariable("pageContent", content);
	    ctx.setLocale(Locale.SIMPLIFIED_CHINESE);
	        
	    String htmlContent = fixHtmlTag(StringEscapeUtils.unescapeHtml(templateEngine.process("template", ctx)));
	   // String htmlContent = StringEscapeUtils.unescapeHtml(templateEngine.process("template", ctx));

	    ITextRenderer renderer = new ITextRenderer();
	    ITextFontResolver fontResolver = renderer.getFontResolver();

	    ClassPathResource fontRes = new ClassPathResource("fonts/SIMSUN.TTC");
	    ClassPathResource cssRes = new ClassPathResource("pdftpl/template.html");
	    fontResolver.addFont(fontRes.getURL().toString(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

	    String cssPath = cssRes.getURL().toString();
	    int idx = cssPath.indexOf("/template.html");
	    String basePath = cssPath.substring(0, idx+1);

	    renderer.setDocumentFromString(htmlContent, basePath);
	    
	    renderer.layout();
	    ByteArrayOutputStream os = new ByteArrayOutputStream();
	    renderer.createPDF(os);

	    byte[] pdfAsBytes = os.toByteArray();
	    os.close();
	    
	    
/*	    FileOutputStream fos = new FileOutputStream(new File("/tmp/render.pdf"));
	    fos.write(pdfAsBytes);
	    fos.close();*/

	    return pdfAsBytes;

	}
	
	private static String fixHtmlTag(String html) throws IOException {
		
		InputStream in = IOUtils.toInputStream(html, "utf-8");
		OutputStream out = new ByteArrayOutputStream();

		
		Tidy tidy = new Tidy(); 
		
		tidy.setInputEncoding("utf-8");  
		tidy.setOutputEncoding("utf-8");   
		tidy.setPrintBodyOnly(false);  
		tidy.setTrimEmptyElements(true);  
		tidy.setSmartIndent(true);  
		tidy.setWraplen(1000);  
		tidy.setXmlOut(true);
		tidy.setFixComments(true);
		
		tidy.parseDOM(in, out);
		
		String tidied = out.toString();
		
		in.close();
		out.close();
		
		return tidied;
		
	}	
}
