package com.first.service;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.firday.bean.Node;
import com.first.dao.INodeCollection;

public class NodeCollectionServer implements INodeCollection{
    // ���ӳ�IOC������ע��
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    	this.jdbcTemplate = jdbcTemplate;
    }
	/**
	 * ��ѯĳ���ղصĽڵ���
	 */
	@Override
	public int doQueryNodeCollectionCountById(int userId) {
		String sql="select count(*) as count from node_collection where user_id=?";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql, new Object[]{userId});
		int count = Integer.parseInt(map.get("count").toString());
		return count;
	}

	@Override
	public List<Node> doQueryNodeCollectionById(int userId) {
		return null;
	}

}
