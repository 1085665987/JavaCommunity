package com.first.dao;

import java.util.List;

import com.firday.bean.PostCollection;
import com.firday.bean.PostInfo;
public interface IPostCollection {
	
	/**
	 * 查询某人收藏的节点数目
	 * @param id
	 * @return
	 */
	public int doQueryPostCollectionCountById(int id);
	
	
	/**
	 * 查询某人收藏的节点
	 * @param id
	 * @return
	 */
	public List<PostCollection> doQueryPostCollectionById(int id);

}
