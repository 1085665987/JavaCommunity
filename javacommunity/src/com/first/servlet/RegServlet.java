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

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.firday.bean.UserInfo;
import com.first.action.UserAction;
import com.first.util.ResponseJsonUtils;

/**
 * Servlet implementation class RegisterServlet
 */

@WebServlet("/register.do")
@MultipartConfig
public class RegServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ApplicationContext applicationContext = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
	super();
    }

    @Override
    public void init() throws ServletException {
	super.init();
	this.applicationContext = new ClassPathXmlApplicationContext("bean.xml");
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
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=utf-8");
	UserAction userAction=(UserAction)applicationContext.getBean("userAction");
	UserInfo user=userAction.doRegister(request,response);
	
    }
}
