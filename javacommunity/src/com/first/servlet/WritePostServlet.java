package com.first.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.first.action.PostAction;

/**
 * Servlet implementation class CKEditorTestServlet
 */
@WebServlet("/writePost.do")
public class WritePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext applicationContext;

	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		applicationContext=new ClassPathXmlApplicationContext("bean.xml");
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WritePostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PostAction postAction=(PostAction) applicationContext.getBean("postAction");
		postAction.doAddPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		doGet(request, response);
	}

}
