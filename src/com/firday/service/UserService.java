package com.firday.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.firday.bean.UserInfo;
import com.firday.dao.IUserDao;
import com.firday.util.RowMapperUtil;
import com.firday.util.TypeUtil;
import com.firday.util.UserRowMapperUtil;
import com.firday.util.UserTypeUtil;

public class UserService implements IUserDao {

	private static final String TAG = "UserService";
	// ���ӳ�IOC������ע��
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public UserInfo doAddUser(UserInfo user) throws IOException {
		// JDBC����
		String sql = "insert into user (account,password) values(?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int count = 0;
		try {
			count = jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, user.getUserAccount());
					ps.setString(2, user.getPassword());
					return ps;
				}
			}, keyHolder);
		} catch (DuplicateKeyException e) {
			user.setUserId(-1);
			return user;
		}
		int userId = keyHolder.getKey().intValue();
		if (count == 1 && userId > 0) {
			user.setUserId(userId);
			return user;
		}
		return null;
	}

	@Override
	public UserInfo doQueryUser(String sql) throws IOException {
		UserInfo userInfo = null;
		Map<String, Object> map = null;
		try {
			map = jdbcTemplate.queryForMap(sql);
			if (map != null) {
				userInfo = new UserInfo();
				userInfo.setUserId(Integer.parseInt(map.get("id").toString()));
				userInfo.setUserAccount(map.get("account").toString());
				userInfo.setUsername(map.get("username").toString());
				userInfo.setPassword(map.get("password").toString());
				userInfo.setRole(Integer.valueOf(map.get("role").toString()));
				userInfo.setUserStatus(Integer.valueOf(map.get("status").toString()));
				userInfo.setPortrait(map.get("portrait").toString());
				userInfo.setGender(String.valueOf(map.get("gender")));
				userInfo.setJob(String.valueOf(map.get("job")));
				userInfo.setAddress(map.get("address").toString());
				userInfo.setSignature(map.get("signature").toString());
				userInfo.setInstructions(map.get("instruction").toString());
				Timestamp timestamp = (Timestamp) map.get("reg_date");
				userInfo.setRegisterDate(timestamp.getTime());
			}
		} catch (EmptyResultDataAccessException e) {
			return userInfo;
		}
		return userInfo;
	}

	@Override
	public UserInfo doQueryUser(UserInfo userInfo) throws IOException {
		TypeUtil typeUtil = new UserTypeUtil();
		Map<String, Object> userMap = typeUtil.userToMap(userInfo);
		return doQueryUser(userMap);
	}

	@Override
	public UserInfo doQueryUser(Map<String, Object> userMap) throws IOException {
		StringBuilder strBuilder = new StringBuilder("select * from user where ");
		for (Map.Entry<String, Object> map : userMap.entrySet()) {
			strBuilder.append(map.getKey());
			strBuilder.append(" = ");
			strBuilder.append(" '" + map.getValue() + "' ");
			strBuilder.append(" and ");
		}
		String sql = strBuilder.toString().substring(0, strBuilder.toString().lastIndexOf("and"));
		try {
			return doQueryUser(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserInfo doQueryUserById(int userId) throws IOException {
		String sql = "select * from user where id=" + userId;
		return doQueryUser(sql);
	}

	@Override
	public List<UserInfo> doQueryUserList(String sql) throws IOException {
		RowMapperUtil<UserInfo> rowMapperUtil=new UserRowMapperUtil();
		List<UserInfo> users = jdbcTemplate.query(sql, rowMapperUtil);
		return users;
	}

	/**
	 * ��ѯȫ��
	 */
	@Override
	public List<UserInfo> doQueryAllUserList() throws IOException {
		String sql = "select * from user";
		return doQueryUserList(sql);
	}

	/**
	 * ��ѯȫ����ͨ�û�
	 */
	@Override
	public List<UserInfo> doQueryUserList() throws IOException {
		String sql = "select * from user order by reg_date desc  where role=0";
		return doQueryUserList(sql);
	}

	/**
	 * ��ѯĳһҳ����ͨ�û�
	 */
	@Override
	public List<UserInfo> doQueryUserListByPage(int page) throws IOException {
		String sql = "select * from user  where  role=0  order by reg_date desc   limit " + ((page - 1) * 5) + " ,5";
		return doQueryUserList(sql);
	}

	@Override
	public List<UserInfo> doQueryUserList(Map<String, Object> keywordMap) throws Exception {
		StringBuilder sql = new StringBuilder("select * from user ");
		if (keywordMap != null) {
			sql.append(" where ");
			for (Map.Entry<String, Object> entry : keywordMap.entrySet()) {
				sql.append(entry.getKey());
				sql.append(" = ");
				sql.append(" '" + entry.getValue() + "' ");
				sql.append(" and ");
			}
			sql.substring(0, sql.toString().lastIndexOf("and"));
		}
		return doQueryUserList(sql.toString());
	}

	@Override
	public int doUpdateUser(Map<String, Object> map) throws IOException {
		TypeUtil typeUtil = new UserTypeUtil();
		UserInfo userInfo = typeUtil.mapToUser(map);
		return doUpdateUser(userInfo);
	}

	@Override
	public int doUpdateUser(UserInfo userInfo) throws IOException {
		StringBuilder sql = new StringBuilder("update user set ");
		int userId = userInfo.getUserId();
		String account = userInfo.getUserAccount();

		int role = userInfo.getRole();
		int status = userInfo.getUserStatus();
		String password = userInfo.getPassword();
		String username = userInfo.getUsername();
		String gender = userInfo.getGender();
		String portrait = userInfo.getPortrait();
		String job = userInfo.getJob();
		String address = userInfo.getAddress();
		String signature = userInfo.getSignature();
		String instruction = userInfo.getInstructions();

		// �޸Ľ�ɫ
		if (status != -1) {
			sql.append(" " + UserInfo.DB_USERSTATUS + " ");
			sql.append(" = ");
			sql.append(" '" + status + "' ,");
		}
		// �޸Ľ�ɫ
		if (role != -1) {
			sql.append(" " + UserInfo.DB_USERROLE + " ");
			sql.append(" = ");
			sql.append(" '" + role + "' ,");
		}
		// �޸�ǩ��
		if (signature != null && signature.length() > 0) {
			sql.append(" " + UserInfo.DB_SIGNATURE + " ");
			sql.append(" = ");
			sql.append(" '" + signature + "' ,");
		}
		// �޸ĵ�ַ
		if (address != null && address.length() > 0) {
			sql.append(" " + UserInfo.DB_ADDRESS + " ");
			sql.append(" = ");
			sql.append(" '" + address + "' ,");
		}
		// �޸ĸ��˼��
		if (instruction != null && instruction.length() > 0) {
			sql.append(" " + UserInfo.DB_INSTRUCTION + " ");
			sql.append(" = ");
			sql.append(" '" + instruction + "' ,");
		}

		// �޸Ĺ���
		if (job != null && job.length() > 0) {
			sql.append(" " + UserInfo.DB_JOB + " ");
			sql.append(" = ");
			sql.append(" '" + job + "' ,");
		}
		// �޸�����
		if (password != null && password.length() >= 6) {
			sql.append(" " + UserInfo.DB_PASSWORD + " ");
			sql.append(" = ");
			sql.append(" '" + userInfo.getPassword() + "' ,");
		}
		// �޸��û���
		if (username != null && username.length() > 0) {
			sql.append(" " + UserInfo.DB_USERNAME + " ");
			sql.append(" = ");
			sql.append(" '" + userInfo.getUsername() + "' ,");
		}
		// �޸��Ա�
		if (gender != null && gender.length() > 0) {
			sql.append(" " + UserInfo.DB_GENDER + " ");
			sql.append(" = ");
			sql.append(" '" + userInfo.getGender() + "' ,");
		}
		// �޸�ͷ��
		if (portrait != null && portrait.length() > 0) {
			sql.append(" " + UserInfo.DB_PORTRAIT + " ");
			sql.append(" = ");
			sql.append(" '" + portrait + "' ,");
		}

		sql = new StringBuilder(sql.substring(0, sql.toString().lastIndexOf(",")));
		sql.append(" where ");

		// ��¼�˺Ų��ܸ�
		if (account != null && account.length() > 0) {
			sql.append("  " + UserInfo.DB_USERACCOUNT + " ");
			sql.append(" = ");
			sql.append(" '" + userInfo.getUserAccount() + "' ");
			sql.append(" and ");
		}
		// �û�id���ܸ�
		if (userId > 0) {
			sql.append(" " + UserInfo.DB_USERID + " ");
			sql.append(" = ");
			sql.append(" '" + userInfo.getUserId() + "' ");
		}
		if (account != null && account.length() > 0) {
			sql = new StringBuilder(sql.substring(0, sql.toString().lastIndexOf("and")));
		}
		int count = jdbcTemplate.update(sql.toString());
		return count;
	}

	@Override
	public int doQueryUserPageCount(){
		String sql="select count(*) as count from user where role=0;";
		Map<String, Object> countMap = jdbcTemplate.queryForMap(sql);
		int pageCount= Integer.valueOf(countMap.get("count").toString())/5+1;
		return pageCount;
	}
}
