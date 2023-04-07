package com.friday.dao;

import java.util.List;

import com.friday.bean.PostCollection;

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
