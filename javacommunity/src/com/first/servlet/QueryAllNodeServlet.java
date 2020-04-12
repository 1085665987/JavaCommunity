package com.first.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.first.action.NodeAction;

/**
 * Servlet implementation class QueryAllNodeServlet
 */
@WebServlet("/queryAllNode.do")
public class QueryAllNodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ApplicationContext applicationContext;

    /**
     * @see HttpServlet#HttpServlet()
     */
    @Override
    public void init() throws ServletException {
	// TODO Auto-generated method stub
	super.init();
	applicationContext = new ClassPathXmlApplicationContext("bean.xml");
    }

    public QueryAllNodeServlet() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	NodeAction nodeAction = (NodeAction) applicationContext.getBean("nodeAction");
	nodeAction.doQueryAllNode(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html; charset=utf-8");
	doGet(request, response);
    }

}
