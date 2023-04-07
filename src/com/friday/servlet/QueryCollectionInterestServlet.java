package com.friday.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.friday.action.NodeCollectionAction;
import com.friday.action.PostCollectionAction;
import com.friday.action.UserInterestAction;
import com.friday.util.ResponseJsonUtils;

/**
 * Servlet implementation class QueryCollectionInterestServlet
 */
@WebServlet("/queryCollectionInterest.json")
public class QueryCollectionInterestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext applicationContext;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryCollectionInterestServlet() {
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
		//���ղؽڵ�
		NodeCollectionAction nodeCollectionAction=(NodeCollectionAction)applicationContext.getBean("nodeCollectionAction");
		int nodeCollectionCount=nodeCollectionAction.doQueryNodeCollectionCount(request, response);

		//���ղ�����
		PostCollectionAction postCollectionAction=(PostCollectionAction)applicationContext.getBean("postCollectionAction");
		int postCollectionCount=postCollectionAction.doQueryPostCollectionCount(request, response);

		//���ע���˵���Ŀ
		UserInterestAction userInterestAction=(UserInterestAction)applicationContext.getBean("userInterestAction");
		int userInterestCount=userInterestAction.doQueryUserInterestCount(request, response);

		Map<String,Object> data=new HashMap<>();
		data.put("nodeCollectionCount", nodeCollectionCount);
		data.put("postCollectionCount", postCollectionCount);
		data.put("userInterestCount", userInterestCount);
		ResponseJsonUtils.json(response, data);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
