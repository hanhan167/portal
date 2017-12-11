package com.hansy.portal.common.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SafeCodeServlet extends HttpServlet {
	private static final long serialVersionUID = -8460163889569407599L;
	public static final String VALIDATE_CODE = "Rand";
	
    //产生随即的字体  
    private Font getFont() {  
        Random random = new Random();  
        Font font[] = new Font[5];  
        font[0] = new Font("Ravie", Font.PLAIN, 24);  
        font[1] = new Font("Antique Olive Compact", Font.PLAIN, 24);  
        font[2] = new Font("Forte", Font.PLAIN, 24);  
        font[3] = new Font("Wide Latin", Font.PLAIN, 24);  
        font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, 24);  
        return font[random.nextInt(5)];  
    }  
  
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        // 设置响应头 Content-type类型  
        resp.setContentType("image/jpeg");  
        // 以下三句是用于设置页面不缓存  
        resp.setHeader("Pragma", "No-cache");  
        resp.setHeader("Cache-Control", "No-cache");  
        resp.setDateHeader("Expires", 0);  
        String sRand = String.valueOf(Math.random()*9000+1000).substring(0, 4);
        req.getSession().setAttribute(VALIDATE_CODE, sRand);
  
        OutputStream os = resp.getOutputStream();  
        int width = 120, height = 37;  
        // 建立指定宽、高和BufferedImage对象  
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);  
  
        Graphics g = image.getGraphics(); // 该画笔画在image上  
        Color c = g.getColor(); // 保存当前画笔的颜色，用完画笔后要回复现场  
        g.fillRect(0, 0, width, height);  
        char[] ch = "abcdefghjkmnpqrstuvwxyz23456789".toCharArray(); // 随即产生的字符串 不包括 i l(小写L) o（小写O） 1（数字1）0(数字0)  
        int length = ch.length; // 随即字符串的长度  
        Random random = new Random();  
     // 设置字体  
        g.setFont(getFont());  
        // 随即生成0-9的数字  
        String rand = new Character(ch[random.nextInt(length)]).toString();  
        // 设置随机颜色  
        g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));  
        g.drawString(sRand, 20 + 6, 25);  
        //产生随即干扰点  
        for (int i = 0; i < 20; i++) {  
            int x1 = random.nextInt(width);  
            int y1 = random.nextInt(height);  
            g.drawOval(x1, y1, 2, 2);  
        }  
        g.setColor(c); // 将画笔的颜色再设置回去  
        g.dispose();  
  
        // 输出图像到页面  
        ImageIO.write(image, "JPEG", os);  
    } 
    
    
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 doGet(req, resp);
	}


	
}
