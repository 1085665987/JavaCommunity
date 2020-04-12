package com.first.dao;

import java.util.List;

import com.firday.bean.Node;
public interface INodeCollection {
	
	/**
	 * 查询某人收藏的节点数目
	 * @param id
	 * @return
	 */
	public int doQueryNodeCollectionCountById(int id);
	
	
	/**
	 * 查询某人收藏的节点
	 * @param id
	 * @return
	 */
	public List<Node> doQueryNodeCollectionById(int id);

}
