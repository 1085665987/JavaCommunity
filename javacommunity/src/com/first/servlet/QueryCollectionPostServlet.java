package com.first.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.first.action.PostCollectionAction;

/**
 * Servlet implementation class QueryCollectionPostServlet
 */
@WebServlet("/queryCollectionPost.do")
public class QueryCollectionPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext applicationContext;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryCollectionPostServlet() {
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
		PostCollectionAction postCollectionAction=(PostCollectionAction) applicationContext.getBean("postCollectionAction");
		postCollectionAction.doQueryPostCollection(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
