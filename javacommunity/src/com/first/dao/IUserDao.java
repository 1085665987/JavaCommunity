package com.first.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.firday.bean.NodeCollection;
import com.firday.bean.UserInfo;

public interface IUserDao {

	/**
	 * 增加用户，注册
	 * @param userInfo
	 * @return 已更改条目
	 */
	public UserInfo doAddUser(UserInfo userInfo) throws Exception;
	
	/**
	 * 用户数据修改
	 * @param userInfo
	 * @return 已更改条目
	 */
	public int doUpdateUser(Map<String,Object> map) throws Exception; 
	
	/**
	 * 根据UserInfo对象，修改用户数据
	 * @param userInfo
	 * @return 已更改条目
	 * @throws Exception
	 */
	public int doUpdateUser(UserInfo userInfo) throws Exception; 
	
	/**
	 * 查询某一用户信息
	 * @param userInfo
	 * @return
	 */
	public UserInfo doQueryUser(String sql) throws Exception; 
	
	/**
	 * 查询某一用户信息
	 * @param userInfo
	 * @return
	 */
	public UserInfo doQueryUser(UserInfo userInfo) throws Exception; 
	
	/**
	 * 查询某一用户信息
	 * @param userInfo
	 * @return
	 */
	public UserInfo doQueryUserById(int userId) throws Exception; 
	
	
	/**
	 * 查询某一用户信息
	 * @param userMap
	 * @return
	 * @throws Exception
	 */
	public UserInfo doQueryUser(Map<String,Object> userMap) throws Exception; 
	
	/**
	 * 查询全部用户
	 * @return
	 */
	public List<UserInfo> doQueryUserList() throws Exception; 
	
	/**
	 * 查询全部用户（不包括管理员）
	 * @param sql   
	 * @return
	 * @throws Exception
	 */
	public List<UserInfo> doQueryUserList(String sql) throws Exception; 
	
	/**
	 * 查询某一页的用户（不包括管理员）
	 * @param page
	 * @return
	 * @throws IOException
	 */
	public List<UserInfo> doQueryUserListByPage(int page) throws IOException; 
	
	/**
	 * 根据关键字，查询某些用户
	 * @param keywords
	 * @return
	 */
	public List<UserInfo> doQueryUserList(Map<String,Object> keywordMap) throws Exception;

	/**
	 * 查询全部用户（包括管理员）
	 * @return
	 * @throws IOException
	 */
	public List<UserInfo> doQueryAllUserList() throws IOException;

	
	/**
	 * 查询用户总页数，每页5个
	 * @return
	 */
	public int doQueryUserPageCount();

	
}
