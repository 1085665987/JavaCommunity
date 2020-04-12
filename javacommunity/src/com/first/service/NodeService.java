package com.first.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.firday.bean.Node;
import com.firday.bean.PostContent;
import com.firday.bean.PostInfo;
import com.first.dao.INodeDao;

public class NodeService implements INodeDao {

    // 连接池IOC容器，注入
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 查询所有节点
     * 父节点->子节点
     */
    @Override
    public List<Node> doQueryAllNode() {
	List<Node> nodeList = new ArrayList<Node>();
	String queryParentSql = "select * from node";
	jdbcTemplate.query(queryParentSql, new RowCallbackHandler() {
	    @Override
	    public void processRow(ResultSet rs) throws SQLException {
		Node node = new Node();
		String nodeName = rs.getString("node_name");
		int nodeId = rs.getInt("node_id");
		// 查询子节点
		List<Node> childNodeList = jdbcTemplate.query("select * from child_node where parent_node_id = ? ",
			new Object[] { nodeId }, new RowMapper<Node>() {
			    @Override
			    public Node mapRow(ResultSet rs, int arg1) throws SQLException {
				Node childNode = new Node();
				childNode.setNodeId(rs.getInt("child_node_id"));
				childNode.setNodeName(rs.getString("child_node_name"));
				childNode.setNodeTitle(rs.getString("child_node_title"));
				// 此处暂时不做查询帖子操作
				return childNode;
			    }
			});
		node.setNodeId(nodeId);
		node.setNodeName(nodeName);
		node.setChildNodeList(childNodeList);
		nodeList.add(node);
	    }
	});
	return nodeList;
    }
    
    /**
     * 查询所有 父节点
     */
    @Override
    public List<Node> doQueryAllParentNode() {
	String queryParentSql = "select * from node";
	List<Node> nodeList = jdbcTemplate.query(queryParentSql, new RowMapper<Node>(){
		@Override
		public Node mapRow(ResultSet rs, int arg1) throws SQLException {
			Node node = new Node();
			String nodeName = rs.getString("node_name");
			int nodeId = rs.getInt("node_id");
			node.setNodeId(nodeId);
			node.setNodeName(nodeName);
			return node;
		}
	});
	return nodeList;
    }

    @Override
    public int doDeleteParentNodeById(int nodeId) {
	// 查询parent_node_id为nodeId的子节点数
	int childNodeCount = (Integer) jdbcTemplate
		.queryForMap("select count(*) as childNodeCount from child_node where parent_node_id=?;",
			new Object[] { nodeId })
		.get("childNodeCount");
	// 返回已删除条目
	int deleteChildNodeCount = jdbcTemplate.update("delect from child_node where node_id= ? ",
		new PreparedStatementSetter() {
		    @Override
		    public void setValues(PreparedStatement ps) throws SQLException {
			ps.setInt(1, nodeId);
		    }
		});
	// 若相同，子节点全部删除完成
	if (childNodeCount == deleteChildNodeCount) {
	    // 删除node表中node_id为nodeId的一条父节点
	    int count = jdbcTemplate.update("delete from node where node_id = ?", nodeId);
	    return count > 0 ? count : -1;
	}
	return -1;
    }

    @Override
    public int doDeleteChildNodeById(int childNodeId) {
	// 返回已删除条目
	int deleteChildNodeCount = jdbcTemplate.update("delete from child_node where child_node_id= ? ",
		new PreparedStatementSetter() {
		    @Override
		    public void setValues(PreparedStatement ps) throws SQLException {
			ps.setInt(1, childNodeId);
		    }
		});
	return deleteChildNodeCount;
    }

    @Override
    public Node doAddParentNode(Node parentNode) {

	return null;
    }

    @Override
    public Node doAddChildNode(Node parentNode, Node childNode) {
	int parentNodeId = parentNode.getNodeId();

	String childNodeName = childNode.getNodeName();
	KeyHolder keyHolder = new GeneratedKeyHolder();
	int count = jdbcTemplate.update(new PreparedStatementCreator() {
	    @Override
	    public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
		String sql = "insert into child_node(child_node_name,parent_node_id) values(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, parentNodeId);
		ps.setString(2, childNodeName);
		return ps;
	    }
	}, keyHolder);
	childNode.setNodeId(keyHolder.getKey().intValue());
	if (count > 0) {
	    return childNode;
	} else {
	    return null;
	}
    }

    @Override
	public Node doQueryChildNodeById(int nodeId) {
		Node node = jdbcTemplate.queryForObject("select * from child_node,node where child_node.parent_node_id=node.node_id and child_node_id=?", 
				new Object[]{nodeId}, new RowMapper<Node>() {
			@Override
			public Node mapRow(ResultSet rs, int arg1) throws SQLException {
				Node node = new Node();
				node.setNodeId(rs.getInt(Node.DB_CHILD_NODEID));
				node.setNodeTitle(rs.getString(Node.DB_CHILD_NODETITLE));	//标语			
				node.setNodeName(rs.getString(Node.DB_CHILD_NODENAME));
				node.setPostCount(rs.getInt(Node.DB_POST_COUNT));

				Node parentNode=new Node();
				parentNode.setNodeId(rs.getInt(Node.DB_NODEID));
				parentNode.setNodeName(rs.getString(Node.DB_NODENAME));
				node.setParentNode(parentNode);
				return node;
			}
		});
		return node;
	}
    
    /***
     * 涉及Post的Dao层和Service层操作，
     * 此时暂不处理，直接交付给nodeAction层操作
     */
    @Override
    public List<PostInfo> doQueryNodePosts(int  nodeId){
    	return null;
    }

    /**
     * 查询所有子节点
     */
    @Override
    public List<Node> doQueryAllChildNode(){
    	String sql="select * from child_node,node   where child_node.parent_node_id=node.node_id";
    	return  doQueryAllChildNode(sql);
    }

    /**
     * 分页查询所有子节点
     */
	@Override
	public List<Node> doQueryAllChildNodeByPage(int page) {
		String sql="select * from child_node,node   where child_node.parent_node_id=node.node_id   limit ?,10" ;
		return doQueryAllChildNode(sql,(page-1)*10);
	}

	@Override
	public List<Node> doQueryAllChildNode(String sql,Object...params) {
		List<Node> childNodeList = jdbcTemplate.query(sql, params, new RowMapper<Node>(){
			@Override
			public Node mapRow(ResultSet rs, int arg1) throws SQLException {
				Node childNode=new Node();
				//子节点
				childNode.setNodeId(rs.getInt(Node.DB_CHILD_NODEID));
				childNode.setNodeName(rs.getString(Node.DB_CHILD_NODENAME));
				childNode.setPostCount(rs.getInt(Node.DB_POST_COUNT));
				childNode.setNodeTitle(rs.getString(Node.DB_CHILD_NODETITLE));
				//父节点
				Node parentNode=new Node();
				parentNode.setNodeId(rs.getInt(Node.DB_NODEID));
				parentNode.setNodeName(rs.getString(Node.DB_NODENAME));
				childNode.setParentNode(parentNode);
				return childNode;
			}
		});
		return childNodeList;
	}

	@Override
	public int doQueryChildNodePageCount() {
		String sql="select count(*) as count from child_node ";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		int count = Integer.valueOf(map.get("count").toString())/10+1;
		return count;
	}
	
	@Override
	public int doUpdateChildNode(int  childNodeId,Object ...params){
		String sql="update child_node set child_node_name= ? ,child_node_title=? , parent_node_id=? where child_node_id= ? ";
		int update = jdbcTemplate.update(sql, new Object[]{
				params[0],params[1],params[2],childNodeId
		});
		return update;
	}
	
	@Override
	public int doUpdateChildNode(Node childNode){
		int nodeId=childNode.getNodeId();
		String nodeName=childNode.getNodeName();
		String nodeTitle=childNode.getNodeTitle();
		
		Node parentNode=childNode.getParentNode();
		int parentNodeId=parentNode.getNodeId();
		
		return doUpdateChildNode(nodeId,nodeName,nodeTitle,parentNodeId);
	}
}
