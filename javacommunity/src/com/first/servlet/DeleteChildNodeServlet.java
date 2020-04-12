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
 * Servlet implementation class DeleteChildNodeServlet
 */
@WebServlet("/deleteChildNode.json")
public class DeleteChildNodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext applicationContext;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteChildNodeServlet() {
        super();
        
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	applicationContext=new ClassPathXmlApplicationContext("bean.xml");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NodeAction nodeAction=(NodeAction)applicationContext.getBean("nodeAction");
		nodeAction.doDeleteChildNodeById(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
