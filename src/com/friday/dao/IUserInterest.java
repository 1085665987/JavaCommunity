package com.friday.dao;

import java.util.List;

import com.friday.bean.UserInfo;
public interface IUserInterest {

	/**
	 * ��ѯĳ���ղصĽڵ���Ŀ
	 * @param id
	 * @return
	 */
	public int doQueryUserInterestCountById(int id);


	/**
	 * ��ѯĳ���ղصĽڵ�
	 * @param id
	 * @return
	 */
	public List<UserInfo> doQueryUserInterestById(int id);

}
