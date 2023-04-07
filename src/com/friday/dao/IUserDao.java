package com.friday.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.friday.bean.UserInfo;

public interface IUserDao {

	/**
	 * �����û���ע��
	 * @param userInfo
	 * @return �Ѹ�����Ŀ
	 */
	public UserInfo doAddUser(UserInfo userInfo) throws Exception;

	/**
	 * �û������޸�
	 * @param userInfo
	 * @return �Ѹ�����Ŀ
	 */
	public int doUpdateUser(Map<String,Object> map) throws Exception;

	/**
	 * ����UserInfo�����޸��û�����
	 * @param userInfo
	 * @return �Ѹ�����Ŀ
	 * @throws Exception
	 */
	public int doUpdateUser(UserInfo userInfo) throws Exception;

	/**
	 * ��ѯĳһ�û���Ϣ
	 * @param userInfo
	 * @return
	 */
	public UserInfo doQueryUser(String sql) throws Exception;

	/**
	 * ��ѯĳһ�û���Ϣ
	 * @param userInfo
	 * @return
	 */
	public UserInfo doQueryUser(UserInfo userInfo) throws Exception;

	/**
	 * ��ѯĳһ�û���Ϣ
	 * @param userInfo
	 * @return
	 */
	public UserInfo doQueryUserById(int userId) throws Exception;


	/**
	 * ��ѯĳһ�û���Ϣ
	 * @param userMap
	 * @return
	 * @throws Exception
	 */
	public UserInfo doQueryUser(Map<String,Object> userMap) throws Exception;

	/**
	 * ��ѯȫ���û�
	 * @return
	 */
	public List<UserInfo> doQueryUserList() throws Exception;

	/**
	 * ��ѯȫ���û�������������Ա��
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<UserInfo> doQueryUserList(String sql) throws Exception;

	/**
	 * ��ѯĳһҳ���û�������������Ա��
	 * @param page
	 * @return
	 * @throws IOException
	 */
	public List<UserInfo> doQueryUserListByPage(int page) throws IOException;

	/**
	 * ���ݹؼ��֣���ѯĳЩ�û�
	 * @param keywords
	 * @return
	 */
	public List<UserInfo> doQueryUserList(Map<String,Object> keywordMap) throws Exception;

	/**
	 * ��ѯȫ���û�����������Ա��
	 * @return
	 * @throws IOException
	 */
	public List<UserInfo> doQueryAllUserList() throws IOException;


	/**
	 * ��ѯ�û���ҳ����ÿҳ5��
	 * @return
	 */
	public int doQueryUserPageCount();


}
