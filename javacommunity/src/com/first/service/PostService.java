package com.first.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.firday.bean.Node;
import com.firday.bean.PostContent;
import com.firday.bean.PostInfo;
import com.firday.bean.UserInfo;
import com.first.dao.IPostDao;
import com.first.util.DisplayDateUtil;
import com.first.util.PostTypeUtil;

public class PostService implements IPostDao {
	private static final String TAG = "PostService";

	// 连接池IOC容器，注入
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 总思路： 1）先往post表中插入帖子（帖子中的图片暂不插入），得到返回成功条目值、
	 * 2）得到返回值之后，若为1（插入成功），再往图片表中插入帖子中的图片 3）操作完成，返回帖子信息（包括帖子id：post_id）
	 */
	@Override
	public PostInfo doAddPost(PostInfo post) {
		// 获取帖子标题
		String postTitle = post.getPostTitle();
		PostContent postContent = post.getPostContent();
		// 获取帖子的图片
		List<String> imgList = postContent.imgsContent;
		// 获取帖子的文字
		String text = postContent.textContent;
		// 帖子发布人id
		int senderId = post.getSenderId();
		// 帖子发布人姓名
		String senderName = post.getSenderName();
		// 帖子类型
		String postType = post.getPostType();

		// 创建一个主键持有者，目的返回一个主键
		KeyHolder keyHolder = new GeneratedKeyHolder();
		// 更新表（此处是插入操作）
		int count = jdbcTemplate.update(new PreparedStatementCreator() {
			private String postSql = "insert into post(post_text_content,sender_id,post_type,post_title,sender_name) values(?,?,?,?,?)";

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(postSql, PreparedStatement.RETURN_GENERATED_KEYS);
				// 设置参数，1,2,3,4是sql语句中的？的下标
				ps.setString(1, text);
				ps.setInt(2, senderId);
				ps.setString(3, postType);
				ps.setString(4, postTitle);
				ps.setString(5, senderName);
				return ps;
			}
		}, keyHolder);

		// 若更新成功
		if (count == 1) {
			// 根据主键持有者得到主键
			int postId = keyHolder.getKey().intValue();
			// System.out.println("主键持有者得到主键：" + postId);
			// 返回的主键是否正确
			if (postId > 0) {
				post.setPostId(postId);
				if (imgList != null && imgList.size() > 0) {
					// 向帖子图片表中批量插入图片，用jdbcTemplate.batchUpdate()方法
					String imgsSql = "insert into post_pic_content(post_id,pic_url) values(?,?);";
					int[] batchUpdate = jdbcTemplate.batchUpdate(imgsSql, new BatchPreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps, int index) throws SQLException {
							// 设置参数
							ps.setInt(1, postId);
							ps.setString(2, imgList.get(index));
						}

						@Override
						public int getBatchSize() {
							// 返回插入条目个数，目的：告知Spring容器插入几条数据
							return imgList.size();
						}
					});
					boolean isSuccessful = true;
					for (int i : batchUpdate) {
						if (i == 0) {
							isSuccessful = false;
							break;
						}
					}
					if (isSuccessful) {
						return post;
					}
				}else{
					return post;
				}
			}
		}
		return null;
	}

	/**
	 * 暂不处理，无意义
	 */
	@Override
	public int doUpdatePost(PostInfo newPost, PostInfo oldPost) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 暂不处理，无意义
	 */
	@Override
	public int doUpdatePost(int postId, PostContent newContent) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 暂不处理，没想好怎么处理
	 */
	@Override
	public int doDeletePost(PostInfo post) {
		return doDeletePost(post.getPostId());
	}

	@Override
	public int doDeletePost(int postId) {
		String sql = "delete from post where post_id=" + postId;
		int updateCount = jdbcTemplate.update(sql);
		// System.out.println(updateCount == 1 ? "成功" : "失败");
		return updateCount;
	}

	@Override
	public int doDeletePosts(List<PostInfo> posts) {
		int[] postIds = new int[posts.size()];
		for (int i = 0; i < posts.size(); i++) {
			postIds[i] = posts.get(i).getPostId();
		}
		return doDeletePostsById(postIds);
	}

	/**
	 * 批量删除
	 */
	@Override
	public int doDeletePostsById(int... postIds) {
		String sql = "delete from post where post_id=?";
		int[] batchUpdate = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				ps.setInt(1, postIds[index]);
			}

			@Override
			public int getBatchSize() {
				return postIds.length;
			}
		});
		boolean isSuccessful = true;
		for (int i : batchUpdate) {
			if (i == 0) {
				isSuccessful = false;
				break;
			}
		}
		// 返回已经删除的帖子个数，不然就返回0：表示删除的不全
		// 若返回0，需要事务回滚处理，此处就不再处理了
		return isSuccessful ? postIds.length : 0;
	}

	@Override
	public int doDeletePostsById(List<Integer> postIds) {
		int[] pIds = new int[postIds.size()];
		for (int i = 0; i < postIds.size(); i++) {
			pIds[i] = postIds.get(i);
		}
		// 交给 doDeletePostsById(int[] postIds) 处理
		return doDeletePostsById(pIds);
	}

	/**
	 * 删除某个人的帖子
	 */
	@Override
	public int doDeletePosts(UserInfo user) {
		List<Integer> postIdList = new ArrayList<Integer>();
		String queryPostIds = "select post_id from post where sender_id=?";
		jdbcTemplate.query(queryPostIds, new Object[] { user.getUserId() }, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				int postId = rs.getInt("post_id");
				// System.out.println(postId);
				postIdList.add(postId);
			}
		});
		return doDeletePostsById(postIdList);
	}

	@Override
	public PostInfo doQueryPost(int postId) {
		// 查询帖子（除帖子中的图片外）
		String queryPostSql = "select * from post where post_id='" + postId + "'";
		Map<String, Object> postMap = jdbcTemplate.queryForMap(queryPostSql);
		// 查询帖子里面的图片
		String queryImgs = "select * from post_pic_content where post_id='" + postId + "'";
		List<String> imgList = jdbcTemplate.query(queryImgs, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				String url = rs.getString(PostContent.DB_POST_IMG_CONTENT);
				// System.out.println(url);
				return url;
			}
		});
		String postTextContent = (String) postMap.get(PostContent.DB_POST_TEXT_CONTENT);
		// 文字和图片封装成对象
		PostContent postContent = new PostContent(postTextContent, imgList);
		PostInfo post = new PostTypeUtil().mapToPost(postMap);
		post.setPostContent(postContent);
		return post;
	}

	@Override
	public PostInfo doQueryPost(PostInfo post) {
		int postId = post.getPostId();
		return doQueryPost(postId);
	}

	@Override
	public List<PostInfo> doQueryPostList(String sql) {
		if (sql == null || sql.length() <= 0) {
			sql = "select * from post";
		}
		List<PostInfo> postList = jdbcTemplate.query(sql, new RowMapper<PostInfo>() {
			@Override
			public PostInfo mapRow(ResultSet rs, int arg1) throws SQLException {
				// 查询post表中的数据
				int postId = rs.getInt(PostInfo.DB_POST_ID);
				String postTitle = rs.getString(PostInfo.DB_POST_TITLE);
				String postType = rs.getString(PostInfo.DB_POST_TYPE);
				Timestamp timestamp = rs.getTimestamp(PostInfo.DB_SEND_TIME);
				long sendTime = timestamp.getTime();
				int senderId = rs.getInt(PostInfo.DB_SENDER_ID);
				String senderName = rs.getString(PostInfo.DB_SENDER_NAME);
				String postTextCount = rs.getString(PostContent.DB_POST_TEXT_CONTENT);
				// 查询帖子表中的数据
				List<String> imgList = doQueryPostImgList(postId);
				PostContent postContent = new PostContent(postTextCount, imgList);
				// 返回一条PostInfo（暂时不查询帖子的评论信息）
				PostInfo postInfo = new PostInfo(postId, postTitle, postContent, senderId, senderName, sendTime,
						postType);
				return postInfo;
			}
		});
		return postList;
	}

	@Override
	public List<PostInfo> doQueryPostList(String sql, Object... objs) {
		List<PostInfo> postList = jdbcTemplate.query(sql, objs, new RowMapper<PostInfo>() {
			@Override
			public PostInfo mapRow(ResultSet rs, int arg1) throws SQLException {
				// 查询post表中的数据
				int postId = rs.getInt(PostInfo.DB_POST_ID);
				String postTitle = rs.getString(PostInfo.DB_POST_TITLE);
				String postType = rs.getString(PostInfo.DB_POST_TYPE);
				Timestamp timestamp = rs.getTimestamp(PostInfo.DB_SEND_TIME);
				long sendTime = timestamp.getTime();
				int senderId = rs.getInt(PostInfo.DB_SENDER_ID);
				String senderName = rs.getString(PostInfo.DB_SENDER_NAME);
				String postTextCount = rs.getString(PostContent.DB_POST_TEXT_CONTENT);
				// 查询帖子表中的数据
				List<String> imgList = doQueryPostImgList(postId);
				PostContent postContent = new PostContent(postTextCount, imgList);
				// 返回一条PostInfo（暂时不查询帖子的评论信息）
				return new PostInfo(postId, postTitle, postContent, senderId, senderName, sendTime, postType);
			}
		});
		return postList;
	}

	@Override
	public List<PostInfo> doQueryAllPost() {
		String sql = "select * from post";
		return doQueryPostList(sql);
	}

	/**
	 * 根据页数查询 分页查询 一页3行 查询第3页 select * from post order by post_id desc limit 6,3
	 * 6=（页数-1）*行数
	 */
	@Override
	public List<PostInfo> doQueryAllPostByPage(int page) {
		String sql = "select * from post order by post_id desc limit " + ((page - 1) * 20) + " ,20";
		return doQueryPostList(sql);
	}

	@Override
	public List<PostInfo> doQueryAllPostByPage(int startPage, int pageCount) {
		String sql = "select * from post order by post_id desc limit " + ((startPage - 1) * 20) + " , "
				+ (20 * pageCount);
		return doQueryPostList(sql);
	}

	/**
	 * 查询某人的帖子
	 */
	@Override
	public List<PostInfo> doQueryUserPosts(UserInfo user) {
		String sql = "select * from post where sender_id= " + user.getUserId();
		return doQueryPostList(sql);
	}

	/**
	 * 查询某人某天的帖子
	 */
	@Override
	public List<PostInfo> doQueryUserPosts(UserInfo userinfo, long dayTime) {
		String sql = "select * from post where date_format(send_time, '%Y-%m-%d')= ? and sender_id=? order by send_time desc";
		return doQueryPostList(sql, DisplayDateUtil.longTimeToDateFormatYMD(dayTime), userinfo.getUserId());
	}

	@Override
	public List<String> doQueryPostImgList(PostInfo post) {
		return doQueryPostImgList(post.getPostId());
	}

	@Override
	public List<String> doQueryPostImgList(int postId) {
		String sql = "select pic_url from post_pic_content where post_id=" + postId;
		List<String> imgList = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString(PostContent.DB_POST_IMG_CONTENT);
			}
		});
		return imgList;
	}

	@Override
	public List<PostInfo> doQueryPostByType(int nodeId) {
		String sql = "select post.* from post,child_node where post_type = child_node_name and child_node.child_node_id = ? ";
		return doQueryPostList(sql, nodeId);
	}
}
