package com.friday.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.friday.service.UserInterestServer;

public class UserInterestAction {

	private UserInterestServer userInterestServer;
	public void setUserInterestServer(UserInterestServer userInterestServer) {
		this.userInterestServer = userInterestServer;
	}

	public int doQueryUserInterestCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId=Integer.parseInt(request.getParameter("userId"));
		int count=userInterestServer.doQueryUserInterestCountById(userId);

//		PrintWriter out = response.getWriter();
//		out.write(count);
//		out.flush();
		return count;
	}
}
