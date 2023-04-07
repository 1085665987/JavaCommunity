package com.friday.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.friday.bean.CommentInfo;
import com.friday.bean.PostInfo;
import com.friday.dao.ICommentDao;
import com.friday.util.CommentTypeUtil;

public class CommentService implements ICommentDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CommentInfo doAddComment(CommentInfo comment) {
	String sql = "insert into comment (post_id,comment_content,sender_id,sender_name) values(?,?,?,?);";
	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(new PreparedStatementCreator() {
	    @Override
	    public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, comment.getPostId());
		ps.setString(2, comment.getCommentContent());
		ps.setInt(3, comment.getSenderId());
		ps.setString(4, comment.getSenderName());
		return ps;
	    }
	}, keyHolder);
	int key = keyHolder.getKey().intValue();
	if (update == 1 && key > 0) {
	    comment.setCommentId(key);
	}
	return comment;
    }

    @Override
    public int doDeleteComment(int commentId) {
	String sql = "delete from comment where comment_id=" + commentId;
	int update = jdbcTemplate.update(sql);
	return update;
    }

    @Override
    public int doDeleteComment(CommentInfo comment) {
	int commentId = comment.getCommentId();
	return doDeleteComment(commentId);
    }

    @Override
    public int deDeleteComments(PostInfo post) {
	return doDeleteComments(post.getPostId());
    }

    @Override
    public int doDeleteComments(int postId) {
	String sql = "delete from comment where post_id=" + postId;
	int update = jdbcTemplate.update(sql);
	return update;
    }

    /**
     * 批量删除
     */
    @Override
    public int doDeleteComments(List<CommentInfo> commentList) {
	String sql = "delete from comment where comment_id=?";
	int[] batchUpdate = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
	    @Override
	    public void setValues(PreparedStatement ps, int index) throws SQLException {
		ps.setInt(1, commentList.get(index).getPostId());
	    }

	    @Override
	    public int getBatchSize() {
		return commentList.size();
	    }
	});
	boolean isSuccessful = true;
	for (int i : batchUpdate) {
	    if (i == 0) {
		isSuccessful = false;
		break;
	    }
	}
	// 返回更新条目
	return isSuccessful ? batchUpdate.length : 0;
    }

    @Override
    public CommentInfo doQueryComment(int commentId) {
	String sql = "select * form comment where comment_id=" + commentId;
	Map<String, Object> queryForMap = jdbcTemplate.queryForMap(sql);
	return new CommentTypeUtil().mapToComment(queryForMap);
    }

    @Override
    public CommentInfo doQueryComment(CommentInfo comment) {
	return doQueryComment(comment.getCommentId());
    }

    @Override
    public List<CommentInfo> doQueryCommentList(int postId) {
	String sql = "select * from comment where post_id=" + postId;
	return jdbcTemplate.query(sql, new RowMapper<CommentInfo>() {
	    @Override
	    public CommentInfo mapRow(ResultSet rs, int arg1) throws SQLException {
		int commentId = rs.getInt(CommentInfo.DB_COMMENT_ID);
		String commentContent = rs.getString(CommentInfo.DB_COMMENT_CONTENT);
		int senderId = rs.getInt(CommentInfo.DB_SENDER_ID);
		String senderName = rs.getString(CommentInfo.DB_SENDER_NAME);
		long sendTime = rs.getLong(CommentInfo.DB_SEND_TIME);
		int postId = rs.getInt(PostInfo.DB_POST_ID);
		return new CommentInfo(commentId, commentContent, senderId, senderName, sendTime, postId);
	    }
	});
    }

    @Override
    public List<CommentInfo> doQueryCommentList(PostInfo post) {
	return doQueryCommentList(post.getPostId());
    }

}
