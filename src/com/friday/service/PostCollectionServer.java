package com.friday.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.friday.bean.Node;
import com.friday.bean.PostCollection;
import com.friday.bean.PostContent;
import com.friday.bean.PostInfo;
import com.friday.bean.UserInfo;
import com.friday.dao.IPostCollection;

public class PostCollectionServer implements IPostCollection{

	// ���ӳ�IOC������ע��
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    	this.jdbcTemplate = jdbcTemplate;
    }

	/**
	 * ��ѯĳ���ղص�������
	 */
	@Override
	public int doQueryPostCollectionCountById(int userId) {
		String sql="select count(*) as count from post_collection where user_id=?";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql, new Object[]{userId});
		int count = Integer.parseInt(map.get("count").toString());
		return count;
	}
	/**
	 * �ղص��˵�id��һ����ǵ�¼�û�
	 */
	@Override
	public List<PostCollection> doQueryPostCollectionById(int userId) {
		/**
		 * sqlԭ��
		 * select post.post_id,post_text_content,post.sender_id,post.send_time,post.post_type,post.post_title,post.sender_name,post.love_count,
	post.comment_count,post.collection_count,post.child_node_id, user.id,user.account,user.username,user.gender,user.role,user.portrait,
	user.job,user.address,user.signature,user.instruction,user.reg_date
from post,user where post.post_id in (select post_id from post_collection where user_id=1) and post.sender_id=user.id;
		 */
		String sql=" select post.post_id,post_text_content,post.sender_id,post.send_time,post.post_type,post.post_title,post.sender_name,post.love_count, "+
							" post.comment_count,post.collection_count,post.child_node_id, user.id user_id,user.account ,user.username user_username ,user.gender,user.role,user.portrait, "+
							" user.job,user.address,user.signature,user.instruction,user.reg_date "+
							" from post,user where post.post_id in (select post_id from post_collection where user_id=?) and post.sender_id=user.id; ";

		/**
		 * ��ӵ�sql���������ղص��ˣ���ص����ӣ�д���ӵ��ˣ��ղص���Ϣ��id����4�
		 * 	select post.*,sender.*,collecter.* ,post_collection.*
from post,user as sender,user as collecter,post_collection
where post.post_id in (select post_id from post_collection where user_id=2) and post.sender_id=sender.id
and collecter.id=post_collection.user_id and post_collection.post_id=post.post_id and post_collection.user_id=2
		 */
		List<PostCollection> collections = jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<PostCollection>(){
			@Override
			public PostCollection mapRow(ResultSet rs, int arg1) throws SQLException {
				PostCollection postCollection=new PostCollection();

				PostInfo postInfo=new PostInfo();
				postInfo.setPostId(rs.getInt("post_id"));
				postInfo.setPostContent(new PostContent(rs.getString("post_text_content")));
				Timestamp sendTime = rs.getTimestamp("send_time");
				postInfo.setSendTime(sendTime.getTime());
				postInfo.setPostTitle(rs.getString("post_title"));

				int childNodeId=rs.getInt("child_node_id");
				postInfo.setChildNodeId(childNodeId);
				String childNodeName=rs.getString("post_type");
				postInfo.setPostType(childNodeName);
				Node node=new Node();
				node.setNodeId(childNodeId);
				node.setNodeName(childNodeName);

				int senderId=rs.getInt("sender_id");
				String senderName=rs.getString("sender_name");
				UserInfo sender=new UserInfo();
				sender.setUserId(senderId);
				sender.setUsername(senderName);
				sender.setGender(rs.getString("gender"));
				sender.setRole(rs.getInt("role"));
				sender.setPortrait(rs.getString("portrait"));
				postInfo.setSender(sender);

				postInfo.setLoveCount(rs.getInt("love_count"));
				postInfo.setCommentCount(rs.getInt("comment_count"));
				postInfo.setCollectionCount(rs.getInt("collection_count"));

				return postCollection;
			}
		});
		return collections;
	}

}
