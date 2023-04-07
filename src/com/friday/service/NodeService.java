package com.friday.service;

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

import com.friday.bean.Node;
import com.friday.bean.PostInfo;
import com.friday.dao.INodeDao;

public class NodeService implements INodeDao {

    // ���ӳ�IOC������ע��
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * ��ѯ���нڵ�
     * ���ڵ�->�ӽڵ�
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
		// ��ѯ�ӽڵ�
		List<Node> childNodeList = jdbcTemplate.query("select * from child_node where parent_node_id = ? ",
			new Object[] { nodeId }, new RowMapper<Node>() {
			    @Override
			    public Node mapRow(ResultSet rs, int arg1) throws SQLException {
				Node childNode = new Node();
				childNode.setNodeId(rs.getInt("child_node_id"));
				childNode.setNodeName(rs.getString("child_node_name"));
				childNode.setNodeTitle(rs.getString("child_node_title"));
				// �˴���ʱ������ѯ���Ӳ���
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
     * ��ѯ���� ���ڵ�
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
	// ��ѯparent_node_idΪnodeId���ӽڵ���
	int childNodeCount = (Integer) jdbcTemplate
		.queryForMap("select count(*) as childNodeCount from child_node where parent_node_id=?;",
			new Object[] { nodeId })
		.get("childNodeCount");
	// ������ɾ����Ŀ
	int deleteChildNodeCount = jdbcTemplate.update("delect from child_node where node_id= ? ",
		new PreparedStatementSetter() {
		    @Override
		    public void setValues(PreparedStatement ps) throws SQLException {
			ps.setInt(1, nodeId);
		    }
		});
	// ����ͬ���ӽڵ�ȫ��ɾ�����
	if (childNodeCount == deleteChildNodeCount) {
	    // ɾ��node����node_idΪnodeId��һ�����ڵ�
	    int count = jdbcTemplate.update("delete from node where node_id = ?", nodeId);
	    return count > 0 ? count : -1;
	}
	return -1;
    }

    @Override
    public int doDeleteChildNodeById(int childNodeId) {
	// ������ɾ����Ŀ
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
				node.setNodeTitle(rs.getString(Node.DB_CHILD_NODETITLE));	//����
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
     * �漰Post��Dao���Service�������
     * ��ʱ�ݲ�����ֱ�ӽ�����nodeAction�����
     */
    @Override
    public List<PostInfo> doQueryNodePosts(int  nodeId){
    	return null;
    }

    /**
     * ��ѯ�����ӽڵ�
     */
    @Override
    public List<Node> doQueryAllChildNode(){
    	String sql="select * from child_node,node   where child_node.parent_node_id=node.node_id";
    	return  doQueryAllChildNode(sql);
    }

    /**
     * ��ҳ��ѯ�����ӽڵ�
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
				//�ӽڵ�
				childNode.setNodeId(rs.getInt(Node.DB_CHILD_NODEID));
				childNode.setNodeName(rs.getString(Node.DB_CHILD_NODENAME));
				childNode.setPostCount(rs.getInt(Node.DB_POST_COUNT));
				childNode.setNodeTitle(rs.getString(Node.DB_CHILD_NODETITLE));
				//���ڵ�
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
