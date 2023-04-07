package com.firday.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.firday.action.PostAction;

/**
 * Servlet implementation class QueryForIndexServlet
 */
@WebServlet("/index.do")
public class QueryForIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext applicationContext;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryForIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
	applicationContext=new ClassPathXmlApplicationContext("bean.xml");
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
	    PostAction postAction = (PostAction)applicationContext.getBean("postAction");
	    postAction.queryPosts(request, response);
	}

}
