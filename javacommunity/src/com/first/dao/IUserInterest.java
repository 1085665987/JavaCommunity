package com.first.dao;

import java.util.List;

import com.firday.bean.UserInfo;
public interface IUserInterest {
	
	/**
	 * 查询某人收藏的节点数目
	 * @param id
	 * @return
	 */
	public int doQueryUserInterestCountById(int id);
	
	
	/**
	 * 查询某人收藏的节点
	 * @param id
	 * @return
	 */
	public List<UserInfo> doQueryUserInterestById(int id);

}
