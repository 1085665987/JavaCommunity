package com.friday.service;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.friday.bean.UserInfo;
import com.friday.dao.IUserInterest;

public class UserInterestServer implements IUserInterest{

	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public int doQueryUserInterestCountById(int userId) {
		String sql="select count(*) as count from interest where  interest_user_id=?";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql, new Object[]{userId});
		int count = Integer.parseInt(map.get("count").toString());
		return count;
	}
	@Override
	public List<UserInfo> doQueryUserInterestById(int id) {
		return null;
	}
}
