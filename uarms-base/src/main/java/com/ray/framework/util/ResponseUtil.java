package com.ray.framework.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ray.framework.dao.vo.JsonResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wb-guoshengwang on 2015/6/8.
 */
public class ResponseUtil {
    private static Logger logger= LoggerFactory.getLogger(ResponseUtil.class);

    /**
     * 输出xml
     * @param response
     * @param text
     */
    public static void renderXML(HttpServletResponse response,final String text){
        String encoding="UTF-8";
        String contentType="text/xml;charset=" + encoding;
        try {
            response.setContentType(contentType);
            setNoCache(response);    //设置为不缓冲
            response.getWriter().write(text);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
    /**
     * 输出text
     * @param response
     * @param text
     */
    public static void renderText(HttpServletResponse response,final String text){
        String encoding="UTF-8";
        String contentType="text/plain;charset=" + encoding;
        try {
            response.setContentType(contentType);
            //setNoCache(response);     //设置为不缓冲
            response.getWriter().write(text);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     *
     * @param response
     * @param jsonStr
     */
    public static void renderJson(HttpServletResponse response,final String jsonStr){
        try {
            response.reset();    //clear .. after setXXX
            response.setContentType("application/json;charset=UTF-8");

            setNoCache(response);   //设置为不缓冲
            PrintWriter out = response.getWriter();
            out.write(jsonStr);
            out.flush();
        /*          out.close();*/
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 通用的Ajax调用返回json格式的渲染器
     * @param response
     * @param jsonResult
     */
    public static void renderJsonResult(HttpServletResponse response,JsonResult jsonResult){
        try {
            response.setContentType("application/json;charset=UTF-8");
            setNoCache(response);   //设置为不缓冲
            PrintWriter out = response.getWriter();

            String jsonStr = JSON.toJSONString(jsonResult);

            out.write(jsonStr);
            out.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 通用的Ajax调用返回json格式的渲染器
     * @param response
     * @param jsonObject
     */
    public static void renderJsonResult(HttpServletResponse response,JSONObject jsonObject){
        try {
            response.setContentType("application/json;charset=UTF-8");
            setNoCache(response);   //设置为不缓冲
            PrintWriter out = response.getWriter();
            out.write(jsonObject.toJSONString());
            out.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 输出html
     * @param response
     * @param html
     */
    public static void renderHtml(HttpServletResponse response,final String html){
        String encoding="UTF-8";
        String contentType="text/html;charset=" + encoding;
        try {
            response.setContentType(contentType);
            setNoCache(response);  //设置为不缓冲
            response.getWriter().write(html);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 设置为不缓冲
     *
     * @param response
     */
    public static void setNoCache(HttpServletResponse response){
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
//        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate,max-age=-1");
        response.setDateHeader("Expires", 0);
    }

    /**
     * 把十六进制Unicode编码字符串转换为中文字符串
     *
     * @param str
     * @return
     */
    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }



}
