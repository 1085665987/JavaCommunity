package com.firday.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.firday.service.NodeCollectionServer;

public class NodeCollectionAction {

	private NodeCollectionServer nodeCollectionServer;

	public void setNodeCollectionServer(NodeCollectionServer nodeCollectionServer) {
		this.nodeCollectionServer = nodeCollectionServer;
	}

	public int doQueryNodeCollectionCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getParameter("userId"));

		int userId=Integer.parseInt(request.getParameter("userId"));

		int count=nodeCollectionServer.doQueryNodeCollectionCountById(userId);

//		PrintWriter out = response.getWriter();
//		out.write(count);
//		out.flush();
		return count;
	}
}
