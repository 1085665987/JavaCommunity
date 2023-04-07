package com.firday.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.firday.bean.PostCollection;
import com.firday.service.PostCollectionServer;

public class PostCollectionAction {

	private PostCollectionServer postCollectionServer;
	public void setPostCollectionServer(PostCollectionServer postCollectionServer) {
		this.postCollectionServer = postCollectionServer;
	}

	public int doQueryPostCollectionCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId=Integer.parseInt(request.getParameter("userId"));
		int count=postCollectionServer.doQueryPostCollectionCountById(userId);

//		PrintWriter out = response.getWriter();
//		out.write(count);
//		out.flush();
		return count;
	}

	public List<PostCollection> doQueryPostCollection(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		int userId=Integer.parseInt(request.getParameter("userId"));
		List<PostCollection> collectionList=postCollectionServer.doQueryPostCollectionById(userId);
		return collectionList;
	}
}
