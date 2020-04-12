package com.firday.bean;

import java.util.List;

public class Node {
    
    public static final String DB_NODEID="node_id";
    public static final String DB_NODENAME="node_name";
    public static final String DB_CHILD_NODEID="child_node_id";
    public static final String DB_CHILD_NODENAME="child_node_name";
    public static final String DB_PARENT_NODEID="parent_node_id";
    public static final String DB_CHILD_NODETITLE="child_node_title";
    public static final String DB_POST_COUNT="post_count";
    public static final String DB_NODETITLE="node_title";

    private int nodeId;
    private String nodeName;
    private String nodeTitle;
    private int postCount;
    
    private Node parentNode; 	//父节点
    
    public int getPostCount() {
		return postCount;
	}
	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
	public Node getParentNode() {
		return parentNode;
	}
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}
	private List<Node> childNodeList; 	//子节点
    
    public String getNodeTitle() {
		return nodeTitle;
	}
	public void setNodeTitle(String nodeTitle) {
		this.nodeTitle = nodeTitle;
	}
	private List<PostInfo> postList;    //节点下面的帖子
    
    public int getNodeId() {
        return nodeId;
    }
    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }
    public String getNodeName() {
        return nodeName;
    }
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
    public List<Node> getChildNodeList() {
        return childNodeList;
    }
    public void setChildNodeList(List<Node> childNodeList) {
        this.childNodeList = childNodeList;
    }
	public List<PostInfo> getPostList() {
		return postList;
	}
	public void setPostList(List<PostInfo> postList) {
		this.postList = postList;
	}
	@Override
	public String toString() {
		return "Node [nodeId=" + nodeId + ", nodeName=" + nodeName + ", nodeTitle=" + nodeTitle + ", postCount="
				+ postCount + ", parentNode=" + parentNode + ", childNodeList=" + childNodeList + ", postList="
				+ postList + "]";
	}
	
}
