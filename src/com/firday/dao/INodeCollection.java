package com.firday.dao;

import java.util.List;

import com.firday.bean.Node;
public interface INodeCollection {

	/**
	 * ��ѯĳ���ղصĽڵ���Ŀ
	 * @param id
	 * @return
	 */
	public int doQueryNodeCollectionCountById(int id);


	/**
	 * ��ѯĳ���ղصĽڵ�
	 * @param id
	 * @return
	 */
	public List<Node> doQueryNodeCollectionById(int id);

}
