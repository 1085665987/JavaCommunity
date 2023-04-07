package com.firday.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.firday.bean.PostContent;
import com.firday.bean.PostInfo;
import com.firday.bean.UserInfo;
import com.firday.service.PostService;
import com.firday.service.UserService;

public class PostAction {
    private PostService postService;

    private UserService userService;
    public void setUserService(UserService userService) {
		this.userService = userService;
	}

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public List<PostInfo> queryPosts(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
	String strPage=request.getParameter(PostInfo.DB_PAGECOUNT);
	int page=1;
	if(strPage!=null&&strPage.length()>0){
		page=Integer.parseInt(strPage);
	}
	List<PostInfo> postList = postService.doQueryAllPostByPage(page);

	Map <String,Object> data=new HashMap<>();
	data.put("data", postList);

	request.setAttribute("postList", postList);
	request.getRequestDispatcher("/index.jsp").forward(request, response);

	return postList;
    }

    public List<PostInfo> queryPostsByNode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	int nodeId =Integer.parseInt(request.getParameter("nodeId"));

    	List<PostInfo> postList = postService.doQueryPostByType(nodeId);

    	//Map <String,Object> data=new HashMap<>();
    	//data.put("data", postList);

    	//request.setAttribute("postList", postList);
    	//request.getRequestDispatcher("/nodeDetail.jsp").forward(request, response);

    	return postList;
    }

	public void queryPostDetail(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException{
		int postId=Integer.parseInt(request.getParameter("postId"));
		PostInfo post = postService.doQueryPost(postId);
		UserInfo sender = userService.doQueryUserById(post.getSenderId());
		request.setAttribute("post", post);
		request.setAttribute("sender", sender);
		request.getRequestDispatcher("postDetail.jsp").forward(request, response);
	}

	public void doAddPost(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException{
		int senderId = Integer.parseInt(request.getParameter("senderId"));
		String senderName=request.getParameter("senderName");
		String postTitle=request.getParameter("postTitle");
		String postType=request.getParameter("childNodeName");
		String textContent=request.getParameter("content");

		PostInfo postInfo=new PostInfo();
		postInfo.setSenderName(senderName);
		postInfo.setSenderId(senderId);
		postInfo.setPostType(postType);
		postInfo.setPostTitle(postTitle);
		postInfo.setPostContent(new PostContent(textContent));
		PostInfo post = postService.doAddPost(postInfo);
		System.out.println(post);
	}
}
