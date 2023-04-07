package com.firday.dao;

import java.util.List;

import com.firday.bean.PostCollection;

public interface IPostCollection {

	/**
	 * ��ѯĳ���ղصĽڵ���Ŀ
	 * @param id
	 * @return
	 */
	public int doQueryPostCollectionCountById(int id);


	/**
	 * ��ѯĳ���ղصĽڵ�
	 * @param id
	 * @return
	 */
	public List<PostCollection> doQueryPostCollectionById(int id);

}
