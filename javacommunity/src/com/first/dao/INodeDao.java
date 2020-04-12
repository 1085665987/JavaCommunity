package com.first.dao;

import java.util.List;

import com.firday.bean.Node;
import com.firday.bean.PostInfo;

public interface INodeDao {

    /**
     * 查询全部节点
     * 父节点1：
     * 		{
     * 			子节点1；
     * 			子节点2；。。。
     * 		}
     * 父节点2：
     * 		{
     * 			子节点1；
     * 			子节点2；。。。
     * 		}
     * @return
     */
    public List<Node> doQueryAllNode() ;
    
    /**
     * 删除某一父节点
     * @return
     */
    public int doDeleteParentNodeById(int nodeId);
    
    /**
     * 删除某一子节点
     * @return
     */
    public int doDeleteChildNodeById(int nodeId);
    
    /**
     * 增加某一父节点
     * @param parentNode
     * @return
     */
    public Node doAddParentNode(Node parentNode);

    /**
     * 增加某一子节点
     * @param parentNode
     * @return
     */
    public Node doAddChildNode(Node parentNode,Node childNode);

    /**
     * 查询某一节点的详细情况
     * @param nodeId
     * @return 
     */
	public Node doQueryChildNodeById(int nodeId);

	/**
	 * 查询节点下的帖子
	 * @param nodeId
	 * @return
	 */
	public List<PostInfo> doQueryNodePosts(int nodeId);
	
	/**
	 * 查询所有子节点
	 * 每一个子节点都有其父节点信息
	 * @return
	 */
	public List<Node> doQueryAllChildNode();
	
	
	/**
	 * 分页查询所有子节点
	 * @param page 页数
	 * @return
	 */
	public List<Node> doQueryAllChildNodeByPage(int page);

	/**
	 * 查询所有子节点
	 * @param sql sql语句
	 * @param params 参数
	 * @return
	 */
	public List<Node> doQueryAllChildNode(String sql, Object ...params);

	/**
	 * 查询一共有多少页节点
	 * 一页节点10个
	 */
	public int doQueryChildNodePageCount();

	/**
	 * 查询所有父节点
	 * @return
	 */
	public List<Node> doQueryAllParentNode();

	/**
	 * 更新子节点信息
	 * @return
	 */
	public	int doUpdateChildNode(Node childNode);

	/**
	 * 更新子节点信息
	 * @param childNodeId
	 * @param params
	 * @return
	 */
	public int doUpdateChildNode(int childNodeId, Object  ... params);
}
