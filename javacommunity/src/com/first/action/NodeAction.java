package com.first.action;

import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.firday.bean.Node;
import com.firday.bean.PostInfo;
import com.firday.bean.UserInfo;
import com.first.service.NodeService;
import com.first.service.PostService;
import com.first.service.UserService;
import com.first.util.ResponseJsonUtils;

public class NodeAction {

	private NodeService nodeService;

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public List<Node> doQueryAllNode(HttpServletRequest request, HttpServletResponse response) {
		List<Node> nodeList = nodeService.doQueryAllNode();
		Map<String, Object> data = new HashMap<>();
		data.put("msg", ((nodeList != null) && (nodeList.size() > 0)) ? "successful" : "error");
		data.put("nodeList", nodeList);
		ResponseJsonUtils.json(response, data);
		return nodeList;
	}

	public Node doQueryNodeById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int nodeId = Integer.parseInt(request.getParameter("nodeId"));
		Node node = nodeService.doQueryChildNodeById(nodeId);
		return node;
	}

	/**
	 * 查询子节点，调到上层处理
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public List<Node> doManageNode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.valueOf(request.getParameter("page"));
		}
		List<Node> nodeList = nodeService.doQueryAllChildNodeByPage(page);

		int administratorId = Integer.valueOf(request.getParameter("administratorId"));
		UserInfo administrator = userService.doQueryUserById(administratorId);

		int sumPage = nodeService.doQueryChildNodePageCount();
		request.setAttribute("currentPage", page);
		request.setAttribute("nodeList", nodeList);
		request.setAttribute("sumPageCount", sumPage);
		request.setAttribute("administrator", administrator);
		request.getRequestDispatcher("manageNode.jsp").forward(request, response);
		return nodeList;
	}

	/**
	 * 查询节点数据 修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public Node doQueryNodeForEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int nodeId = Integer.parseInt(request.getParameter("nodeId"));
		Node node = nodeService.doQueryChildNodeById(nodeId);
		int administratorId = Integer.valueOf(request.getParameter("administratorId"));
		UserInfo administrator = userService.doQueryUserById(administratorId);
		request.setAttribute("node", node);
		request.setAttribute("administrator", administrator);
		request.getRequestDispatcher("editNode.jsp").forward(request, response);
		return node;
	}

	/**
	 * 查询全部父节点 返回Json数据
	 * 
	 * @param request
	 * @param response
	 */
	public void doQueryAllParentNodeForJson(HttpServletRequest request, HttpServletResponse response) {
		List<Node> parentNodeList = nodeService.doQueryAllParentNode();
		Map<String, Object> data = new HashMap<>();
		data.put("msg", data != null && parentNodeList.size() > 0 ? true : false);
		data.put("rs", parentNodeList);
		ResponseJsonUtils.json(response, data);
	}

	/**
	 * 做更新子节点操作
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public int doUpdateNodeData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int childNodeId = Integer.parseInt(request.getParameter("childNodeId"));

		String childNodeName = request.getParameter("childNodeName");
		String childNodeTitle = request.getParameter("childNodeTitle");
		int parentNodeId = Integer.parseInt(request.getParameter("parentNodeId"));

		int count = nodeService.doUpdateChildNode(childNodeId, childNodeName, childNodeTitle, parentNodeId);
		if (count == 1) {
			PrintWriter out = response.getWriter();
			// 运行jsp页面的函数脚本，返回上一页
			out.write(
					"<SCRIPT language=javascript>function go() { window.history.go(-2); alert('修改成功');}setTimeout('go()',1000);</SCRIPT>");
			out.flush();
		}
		return count;
	}

	/**
	 * 删除某一子节点
	 * 
	 * @param childNodeId
	 */
	public void doDeleteChildNodeById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int childNodeId = Integer.parseInt(request.getParameter("childNodeId"));
		int count = nodeService.doDeleteChildNodeById(childNodeId);
		PrintWriter out = response.getWriter();
		if (count == 1) {
			out.write("successful");
		} else {
			out.write("error");
		}
		out.flush();
	}
}
