package com.first.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class RegisterServlet
 */

//@WebServlet("/register.do")
@MultipartConfig
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(request.getContextPath());
		try{
			Part part=request.getPart("picture");
			String savePath = request.getServletContext().getRealPath("/pictures");  
			String fileName=part.getSubmittedFileName();
			System.out.println("原文件名："+fileName);
			out.print("原文件名："+fileName+"<br />");
			String expendName=fileName.substring(fileName.indexOf("."));
			
			fileName=UUID.randomUUID().toString()+expendName;
			System.out.println("新文件名："+fileName);
			out.print("新文件名："+fileName +"<br />");
			
			part.write(savePath+"/"+fileName);
			System.out.println("图片路径："+savePath+"\\"+fileName);
			
		    String contentType = part.getContentType();
		    System.out.println("内容类型："+contentType);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println("姓名："+username);
		out.print("姓名："+username);
		System.out.println("密码："+password);
		out.print("密码："+password);
		
	}
}
