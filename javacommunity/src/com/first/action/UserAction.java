package com.first.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.firday.bean.UserInfo;
import com.first.service.UserService;
import com.first.util.CookieUtils;
import com.first.util.ResponseJsonUtils;

public class UserAction {
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserInfo doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, Object> parameterMap = new HashMap<>();
		String account = request.getParameter(UserInfo.DB_USERACCOUNT);
		parameterMap.put(UserInfo.DB_USERACCOUNT, account);
		String password = request.getParameter(UserInfo.DB_PASSWORD);
		parameterMap.put(UserInfo.DB_PASSWORD, password);

		UserInfo userInfo = userService.doQueryUser(parameterMap);
		Map<String, Object> data = new HashMap<String, Object>();
		if (userInfo != null) {
			// 写入session
			HttpSession session = request.getSession();
			session.setAttribute(UserInfo.TAG, userInfo);
			// 写入cookie
			CookieUtils.setCookie(request, response, UserInfo.DB_USERACCOUNT, account);
			CookieUtils.setCookie(request, response, UserInfo.DB_PASSWORD, password);
			data.put("msg", "100");
			data.put("date", userInfo);
			// 手机号和密码正确，此时不向前端返回数据，直接重定向到index.jsp
			response.sendRedirect("index.do");
		} else {
			// 手机号和密码不正确，不向前端返回数据
			data.put("msg", "101");
			data.put("date", null);
			response.getWriter().print("<script>alert('用户名或密码错误');window.location.replace('userLog.jsp');</script>");
		}
		// ResponseJsonUtils.json(response, data);

		return userInfo;
	}

	public UserInfo doRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String account = request.getParameter(UserInfo.DB_USERACCOUNT);
		String password = request.getParameter(UserInfo.DB_PASSWORD);

		UserInfo user = new UserInfo();
		user.setUserAccount(account);
		user.setPassword(password);
		user = userService.doAddUser(user);

		Map<String, Object> data = new HashMap<String, Object>();
		// 101：其他情况下，插入失败
		// 102：数据库中已有此账号，插入失败
		System.out.println(user);
		data.put("msg", user.getUserId() > 0 ? 100 : user.getUserId() == -1 ? 102 : 101);
		data.put("data", user.getUserId() > 0 ? user : null);
		System.out.println(data);
		ResponseJsonUtils.json(response, data);
		return user;
	}

	/**
	 * 需要做有关用户数据库工作时要做的请求
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doQueryUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 检查session，是有 还是没有
		UserInfo userInfo = (UserInfo) session.getAttribute(UserInfo.TAG);
		if (userInfo != null) {
			response.sendRedirect("index.jsp");
		} else {
			// 若session没有，去拿cookie
			String cookieAccount = CookieUtils.getCookieValue(request, UserInfo.DB_USERACCOUNT);
			String cookiePassword = CookieUtils.getCookieValue(request, UserInfo.DB_PASSWORD);
			if (cookieAccount != null && cookiePassword != null) {
				// 做登录过程
				request.getRequestDispatcher("http://localhost:8080/javacommunity/login.do?account=" + cookieAccount
						+ "&password=" + cookiePassword).forward(request, response);
			} else {
				// 若没有session，也没有cookie，去做登录页面做登录操作
				request.getRequestDispatcher("/userLoign.jsp").forward(request, response);
			}
		}
	}

	/**
	 * 查询用户详细信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public UserInfo doQueryUserDetailData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.valueOf(request.getParameter("userId"));
		UserInfo user = null;
		try {
			user = userService.doQueryUserById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("user", user);
		request.getRequestDispatcher("userSetting.jsp").forward(request, response);
		return user;
	}
	
	/**
	 * 上传头像
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public int doUpdateUserPortrait(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId=Integer.valueOf(request.getParameter("userId"));
		Part part = request.getPart("fileUpload");
		String fileHeader=part.getHeader("content-disposition");
		String fileName=UUID.randomUUID().toString()+ fileHeader.substring(fileHeader.lastIndexOf("."), fileHeader.length() - 1);
		String path = request.getServletContext().getRealPath("appimg\\portrait\\");
		//System.out.println(path + "\\" + fileName);
		part.write(path + "\\" + fileName);
		
		Map<String,Object> userMap=new HashMap<>();
		userMap.put(UserInfo.DB_USERID, userId);
		userMap.put(UserInfo.DB_PORTRAIT	, "/portrait/"+fileName);
		int count=0;
		PrintWriter out = response.getWriter();
		count = userService.doUpdateUser(userMap);
		if(count == 1){
			out.write("success");
		}else{
			out.write("error");
		}
		out.flush();
		return count;
	}

	/**
	 * 更改个人信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public int doUpdateUserData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.valueOf(request.getParameter("userId"));
		String username=request.getParameter("username");
		String job=request.getParameter("job");
		String address=request.getParameter("address");
		String signature=request.getParameter("signature");
		String instructions=request.getParameter("instructions");
		
		Map <String,Object> userMap=new HashMap<>();
		userMap.put(UserInfo.DB_USERID, userId);
		userMap.put(UserInfo.DB_USERNAME, username);
		userMap.put(UserInfo.DB_JOB, job);
		userMap.put(UserInfo.DB_ADDRESS, address);
		userMap.put(UserInfo.DB_SIGNATURE, signature);
		userMap.put(UserInfo.DB_INSTRUCTION, instructions);
		int count=0;
		count=userService.doUpdateUser(userMap);
		if(count==1){
			request.setAttribute("msg", "已修改");
		}else{
			request.setAttribute("msg", "出错了");
		}
		String path="queryUserDetailData.do?userId="+userId;
		request.getRequestDispatcher(path).forward(request, response);
		return count;
	}
	
	/**
	 * 用户管理，查询普通用户列表
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public List<UserInfo> managerUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page=1;
		if(request.getParameter("page")!=null){
			page=Integer.valueOf(request.getParameter("page"));
		}
		List<UserInfo>userList=userService.doQueryUserListByPage(page);
		int administratorId = Integer.valueOf(request.getParameter("administratorId"));
		UserInfo administrator = userService.doQueryUserById(administratorId);
		//查询总页数
		int sumPageCount=userService.doQueryUserPageCount();
		//返回user列表
		request.setAttribute("userList", userList);
		//设置总页数
		request.setAttribute("sumPageCount", sumPageCount);
		//设置当前页
		request.setAttribute("currentPage", page);
		//返回管理员信息
		request.setAttribute("administrator", administrator);	
		request.getRequestDispatcher("manageUser.jsp").forward(request, response);
		return userList;
	}
	
	/**
	 * 设置用户权限
	 * actionId:
	 * 1：激活操作
	 * 2：设置管理员操作
	 * 3：拉黑操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doOperationUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int actionId=Integer.valueOf(request.getParameter("actionId"));
		int userId=Integer.valueOf(request.getParameter("userId"));
		UserInfo user = userService.doQueryUserById(userId);
		PrintWriter out = response.getWriter();
		if(actionId==1){
			if(user.getUserStatus()!=1){//数据库中的用户状态不是1，进行激活操作
				Map<String , Object> map=new HashMap<>();
				map.put(UserInfo.DB_USERID, userId);
				map.put(UserInfo.DB_USERSTATUS, 1);
				int count = userService.doUpdateUser(map);
				if(count ==1){
					out.write("success");
				}else{
					out.write("error");
				}
			}else{//数据库中的用户状态本来就是1
				out.write("error");
			}
		}else if(actionId==2){
			if(user.getRole()==0){//数据库中的用户不是管理员，就可以设置
				Map<String , Object> map=new HashMap<>();
				map.put(UserInfo.DB_USERID, userId);
				map.put(UserInfo.DB_USERROLE, 1);
				int count = userService.doUpdateUser(map);
				if(count ==1){
					out.write("success");
				}else{
					out.write("error");
				}
			}else{//数据库中的用户本来就是管理员，就不可以设置
				out.write("error");
			}
		}else if(actionId==3){
			if(user.getUserStatus()==1){//数据库中的用户状态是1,可以拉黑
				Map<String , Object> map=new HashMap<>();
				map.put(UserInfo.DB_USERID, userId);
				map.put(UserInfo.DB_USERSTATUS, 2);
				int count = userService.doUpdateUser(map);
				if(count ==1){
					out.write("success");
				}else{
					out.write("error");
				}
			}else{//数据库中的用户状态不是1
				out.write("error");
			}
		}else{
			out.write("error");
		}
		out.flush();
	}
}
