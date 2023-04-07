package com.friday.service;

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

import com.friday.bean.PostContent;
import com.friday.bean.PostInfo;
import com.friday.bean.UserInfo;
import com.friday.dao.IPostDao;
import com.friday.util.DisplayDateUtil;
import com.friday.util.PostTypeUtil;

public class PostService implements IPostDao {
	private static final String TAG = "PostService";

	// ���ӳ�IOC������ע��
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * ��˼·�� 1������post���в������ӣ������е�ͼƬ�ݲ����룩���õ����سɹ���Ŀֵ��
	 * 2���õ�����ֵ֮����Ϊ1������ɹ���������ͼƬ���в��������е�ͼƬ 3��������ɣ�����������Ϣ����������id��post_id��
	 */
	@Override
	public PostInfo doAddPost(PostInfo post) {
		// ��ȡ���ӱ���
		String postTitle = post.getPostTitle();
		PostContent postContent = post.getPostContent();
		// ��ȡ���ӵ�ͼƬ
		List<String> imgList = postContent.imgsContent;
		// ��ȡ���ӵ�����
		String text = postContent.textContent;
		// ���ӷ�����id
		int senderId = post.getSenderId();
		// ���ӷ���������
		String senderName = post.getSenderName();
		// ��������
		String postType = post.getPostType();

		// ����һ�����������ߣ�Ŀ�ķ���һ������
		KeyHolder keyHolder = new GeneratedKeyHolder();
		// ���±��˴��ǲ��������
		int count = jdbcTemplate.update(new PreparedStatementCreator() {
			private String postSql = "insert into post(post_text_content,sender_id,post_type,post_title,sender_name) values(?,?,?,?,?)";

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(postSql, PreparedStatement.RETURN_GENERATED_KEYS);
				// ���ò�����1,2,3,4��sql����еģ����±�
				ps.setString(1, text);
				ps.setInt(2, senderId);
				ps.setString(3, postType);
				ps.setString(4, postTitle);
				ps.setString(5, senderName);
				return ps;
			}
		}, keyHolder);

		// �����³ɹ�
		if (count == 1) {
			// �������������ߵõ�����
			int postId = keyHolder.getKey().intValue();
			// System.out.println("���������ߵõ�������" + postId);
			// ���ص������Ƿ���ȷ
			if (postId > 0) {
				post.setPostId(postId);
				if (imgList != null && imgList.size() > 0) {
					// ������ͼƬ������������ͼƬ����jdbcTemplate.batchUpdate()����
					String imgsSql = "insert into post_pic_content(post_id,pic_url) values(?,?);";
					int[] batchUpdate = jdbcTemplate.batchUpdate(imgsSql, new BatchPreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps, int index) throws SQLException {
							// ���ò���
							ps.setInt(1, postId);
							ps.setString(2, imgList.get(index));
						}

						@Override
						public int getBatchSize() {
							// ���ز�����Ŀ������Ŀ�ģ���֪Spring�������뼸������
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
	 * �ݲ�����������
	 */
	@Override
	public int doUpdatePost(PostInfo newPost, PostInfo oldPost) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * �ݲ�����������
	 */
	@Override
	public int doUpdatePost(int postId, PostContent newContent) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * �ݲ�����û�����ô����
	 */
	@Override
	public int doDeletePost(PostInfo post) {
		return doDeletePost(post.getPostId());
	}

	@Override
	public int doDeletePost(int postId) {
		String sql = "delete from post where post_id=" + postId;
		int updateCount = jdbcTemplate.update(sql);
		// System.out.println(updateCount == 1 ? "�ɹ�" : "ʧ��");
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
	 * ����ɾ��
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
		// �����Ѿ�ɾ�������Ӹ�������Ȼ�ͷ���0����ʾɾ���Ĳ�ȫ
		// ������0����Ҫ����ع������˴��Ͳ��ٴ�����
		return isSuccessful ? postIds.length : 0;
	}

	@Override
	public int doDeletePostsById(List<Integer> postIds) {
		int[] pIds = new int[postIds.size()];
		for (int i = 0; i < postIds.size(); i++) {
			pIds[i] = postIds.get(i);
		}
		// ���� doDeletePostsById(int[] postIds) ����
		return doDeletePostsById(pIds);
	}

	/**
	 * ɾ��ĳ���˵�����
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
		// ��ѯ���ӣ��������е�ͼƬ�⣩
		String queryPostSql = "select * from post where post_id='" + postId + "'";
		Map<String, Object> postMap = jdbcTemplate.queryForMap(queryPostSql);
		// ��ѯ���������ͼƬ
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
		// ���ֺ�ͼƬ��װ�ɶ���
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
				// ��ѯpost���е�����
				int postId = rs.getInt(PostInfo.DB_POST_ID);
				String postTitle = rs.getString(PostInfo.DB_POST_TITLE);
				String postType = rs.getString(PostInfo.DB_POST_TYPE);
				Timestamp timestamp = rs.getTimestamp(PostInfo.DB_SEND_TIME);
				long sendTime = timestamp.getTime();
				int senderId = rs.getInt(PostInfo.DB_SENDER_ID);
				String senderName = rs.getString(PostInfo.DB_SENDER_NAME);
				String postTextCount = rs.getString(PostContent.DB_POST_TEXT_CONTENT);
				// ��ѯ���ӱ��е�����
				List<String> imgList = doQueryPostImgList(postId);
				PostContent postContent = new PostContent(postTextCount, imgList);
				// ����һ��PostInfo����ʱ����ѯ���ӵ�������Ϣ��
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
				// ��ѯpost���е�����
				int postId = rs.getInt(PostInfo.DB_POST_ID);
				String postTitle = rs.getString(PostInfo.DB_POST_TITLE);
				String postType = rs.getString(PostInfo.DB_POST_TYPE);
				Timestamp timestamp = rs.getTimestamp(PostInfo.DB_SEND_TIME);
				long sendTime = timestamp.getTime();
				int senderId = rs.getInt(PostInfo.DB_SENDER_ID);
				String senderName = rs.getString(PostInfo.DB_SENDER_NAME);
				String postTextCount = rs.getString(PostContent.DB_POST_TEXT_CONTENT);
				// ��ѯ���ӱ��е�����
				List<String> imgList = doQueryPostImgList(postId);
				PostContent postContent = new PostContent(postTextCount, imgList);
				// ����һ��PostInfo����ʱ����ѯ���ӵ�������Ϣ��
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
	 * ����ҳ����ѯ ��ҳ��ѯ һҳ3�� ��ѯ��3ҳ select * from post order by post_id desc limit 6,3
	 * 6=��ҳ��-1��*����
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
	 * ��ѯĳ�˵�����
	 */
	@Override
	public List<PostInfo> doQueryUserPosts(UserInfo user) {
		String sql = "select * from post where sender_id= " + user.getUserId();
		return doQueryPostList(sql);
	}

	/**
	 * ��ѯĳ��ĳ�������
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
