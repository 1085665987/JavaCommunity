package com.friday.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.friday.bean.Node;
import com.friday.bean.PostInfo;
import com.friday.action.NodeAction;
import com.friday.action.PostAction;

/**
 * ��ѯ�ڵ����������
 * @author Friday
 *
 */
@WebServlet("/queryNodeDetail.do")
public class QueryNodeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ApplicationContext applicationContext;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryNodeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		this.applicationContext=new ClassPathXmlApplicationContext("bean.xml");
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
		PostAction postAction=(PostAction)applicationContext.getBean("postAction");
		List<PostInfo> postList = postAction.queryPostsByNode(request, response);

		NodeAction nodeAction=(NodeAction)applicationContext.getBean("nodeAction");
		Node node=nodeAction.doQueryNodeById(request, response);

		node.setPostList(postList);
		request.setAttribute("node", node);
		request.getRequestDispatcher("nodeDetail.jsp").forward(request, response);
	}

}
