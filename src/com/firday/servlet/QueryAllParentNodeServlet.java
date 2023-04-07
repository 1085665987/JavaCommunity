package com.firday.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.firday.action.NodeAction;

/**
 * Servlet implementation class QueryAllParentNodeServlet
 */
@WebServlet("/queryAllParentNode.json")
public class QueryAllParentNodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext applicationContext;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryAllParentNodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.applicationContext=new ClassPathXmlApplicationContext("bean.xml");
		NodeAction nodeAction=(NodeAction)applicationContext.getBean("nodeAction");
		nodeAction.doQueryAllParentNodeForJson(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
