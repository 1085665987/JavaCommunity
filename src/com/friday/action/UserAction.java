package com.friday.action;

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

import com.friday.bean.UserInfo;
import com.friday.service.UserService;
import com.friday.util.CookieUtils;
import com.friday.util.ResponseJsonUtils;

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
			// д��session
			HttpSession session = request.getSession();
			session.setAttribute(UserInfo.TAG, userInfo);
			// д��cookie
			CookieUtils.setCookie(request, response, UserInfo.DB_USERACCOUNT, account);
			CookieUtils.setCookie(request, response, UserInfo.DB_PASSWORD, password);
			data.put("msg", "100");
			data.put("date", userInfo);
			// �ֻ��ź�������ȷ����ʱ����ǰ�˷������ݣ�ֱ���ض���index.jsp
			response.sendRedirect("index.do");
		} else {
			// �ֻ��ź����벻��ȷ������ǰ�˷�������
			data.put("msg", "101");
			data.put("date", null);
			response.getWriter().print("<script>alert('�û������������');window.location.replace('userLog.jsp');</script>");
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
		// 101����������£�����ʧ��
		// 102�����ݿ������д��˺ţ�����ʧ��
		System.out.println(user);
		data.put("msg", user.getUserId() > 0 ? 100 : user.getUserId() == -1 ? 102 : 101);
		data.put("data", user.getUserId() > 0 ? user : null);
		System.out.println(data);
		ResponseJsonUtils.json(response, data);
		return user;
	}

	/**
	 * ��Ҫ���й��û����ݿ⹤��ʱҪ��������
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doQueryUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// ���session������ ����û��
		UserInfo userInfo = (UserInfo) session.getAttribute(UserInfo.TAG);
		if (userInfo != null) {
			response.sendRedirect("index.jsp");
		} else {
			// ��sessionû�У�ȥ��cookie
			String cookieAccount = CookieUtils.getCookieValue(request, UserInfo.DB_USERACCOUNT);
			String cookiePassword = CookieUtils.getCookieValue(request, UserInfo.DB_PASSWORD);
			if (cookieAccount != null && cookiePassword != null) {
				// ����¼����
				request.getRequestDispatcher("http://localhost:8080/javacommunity/login.do?account=" + cookieAccount
						+ "&password=" + cookiePassword).forward(request, response);
			} else {
				// ��û��session��Ҳû��cookie��ȥ����¼ҳ������¼����
				request.getRequestDispatcher("/userLoign.jsp").forward(request, response);
			}
		}
	}

	/**
	 * ��ѯ�û���ϸ��Ϣ
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
	 * �ϴ�ͷ��
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
	 * ���ĸ�����Ϣ
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
			request.setAttribute("msg", "���޸�");
		}else{
			request.setAttribute("msg", "������");
		}
		String path="queryUserDetailData.do?userId="+userId;
		request.getRequestDispatcher(path).forward(request, response);
		return count;
	}

	/**
	 * �û�������ѯ��ͨ�û��б�
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
		//��ѯ��ҳ��
		int sumPageCount=userService.doQueryUserPageCount();
		//����user�б�
		request.setAttribute("userList", userList);
		//������ҳ��
		request.setAttribute("sumPageCount", sumPageCount);
		//���õ�ǰҳ
		request.setAttribute("currentPage", page);
		//���ع���Ա��Ϣ
		request.setAttribute("administrator", administrator);
		request.getRequestDispatcher("manageUser.jsp").forward(request, response);
		return userList;
	}

	/**
	 * �����û�Ȩ��
	 * actionId:
	 * 1���������
	 * 2�����ù���Ա����
	 * 3�����ڲ���
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
			if(user.getUserStatus()!=1){//���ݿ��е��û�״̬����1�����м������
				Map<String , Object> map=new HashMap<>();
				map.put(UserInfo.DB_USERID, userId);
				map.put(UserInfo.DB_USERSTATUS, 1);
				int count = userService.doUpdateUser(map);
				if(count ==1){
					out.write("success");
				}else{
					out.write("error");
				}
			}else{//���ݿ��е��û�״̬��������1
				out.write("error");
			}
		}else if(actionId==2){
			if(user.getRole()==0){//���ݿ��е��û����ǹ���Ա���Ϳ�������
				Map<String , Object> map=new HashMap<>();
				map.put(UserInfo.DB_USERID, userId);
				map.put(UserInfo.DB_USERROLE, 1);
				int count = userService.doUpdateUser(map);
				if(count ==1){
					out.write("success");
				}else{
					out.write("error");
				}
			}else{//���ݿ��е��û��������ǹ���Ա���Ͳ���������
				out.write("error");
			}
		}else if(actionId==3){
			if(user.getUserStatus()==1){//���ݿ��е��û�״̬��1,��������
				Map<String , Object> map=new HashMap<>();
				map.put(UserInfo.DB_USERID, userId);
				map.put(UserInfo.DB_USERSTATUS, 2);
				int count = userService.doUpdateUser(map);
				if(count ==1){
					out.write("success");
				}else{
					out.write("error");
				}
			}else{//���ݿ��е��û�״̬����1
				out.write("error");
			}
		}else{
			out.write("error");
		}
		out.flush();
	}
}
