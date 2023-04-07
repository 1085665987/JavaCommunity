package com.friday.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
			System.out.println("ԭ�ļ�����"+fileName);
			out.print("ԭ�ļ�����"+fileName+"<br />");
			String expendName=fileName.substring(fileName.indexOf("."));

			fileName=UUID.randomUUID().toString()+expendName;
			System.out.println("���ļ�����"+fileName);
			out.print("���ļ�����"+fileName +"<br />");

			part.write(savePath+"/"+fileName);
			System.out.println("ͼƬ·����"+savePath+"\\"+fileName);

		    String contentType = part.getContentType();
		    System.out.println("�������ͣ�"+contentType);
		}catch(Exception e){
			e.printStackTrace();
		}

		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println("������"+username);
		out.print("������"+username);
		System.out.println("���룺"+password);
		out.print("���룺"+password);

	}
}
