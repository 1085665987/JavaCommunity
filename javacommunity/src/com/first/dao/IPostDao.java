package com.first.dao;

import java.util.List;

import com.firday.bean.Node;
import com.firday.bean.PostContent;
import com.firday.bean.PostInfo;
import com.firday.bean.UserInfo;

public interface IPostDao {
    /**
     * 查询帖子
     * a节点（关键字）
     * b页数
     * c人
     */
    
    /**
     * 根据某节点类型，查询，即查询某节点下的所有帖子
     * @param node
     * @return
     */
    public List<PostInfo> doQueryPostByType(int nodeId);
    
    /************************************************************/
    /**
     * 插入帖子
     * 
     * @param post
     * @return
     */
    public PostInfo doAddPost(PostInfo post);

    /**
     * 修稿
     * 
     * @param newPost
     * @param oldPost
     */
    public int doUpdatePost(PostInfo newPost, PostInfo oldPost);

    /**
     * 修稿
     * 
     * @param postId
     * @param newContent
     */
    public int doUpdatePost(int postId, PostContent newContent);

    /**
     * 删除某一帖子
     * 
     * @param post
     * @return
     */
    public int doDeletePost(PostInfo post);

    /**
     * 删除某一帖子
     * 
     * @param postId
     * @return
     */
    public int doDeletePost(int postId);

    /**
     * 删除部分帖子
     * 
     * @param posts
     * @return
     */
    public int doDeletePosts(List<PostInfo> posts);

    /**
     * 删除部分帖子
     * 
     * @param postIds
     * @return
     */
    public int doDeletePostsById(int ... postIds);
    /**
     * 删除部分帖子
     * @param postIds
     * @return
     */
    public int doDeletePostsById(List<Integer> postIds);

    /**
     * 删除某个人的帖子
     * 
     * @param user
     * @return
     */
    public int doDeletePosts(UserInfo user);

    /**
     * 根据帖子id查询帖子
     * 
     * @param postId
     * @return
     */
    public PostInfo doQueryPost(int postId);

    /**
     * 查询帖子
     * 
     * @param post
     * @return
     */
    public PostInfo doQueryPost(PostInfo post);
    
    /**
     * 查询帖子的图片
     * @param post
     * @return
     */
    public List<String> doQueryPostImgList(PostInfo post);
    /**
     * 查询帖子的图片 
     * @param postId
     * @return
     */
    public List<String> doQueryPostImgList(int postId);

    /**
     * 根据SQL语句不同，查询post
     * @param sql
     * @return
     */
    public List<PostInfo> doQueryPostList(String sql);
    
    /**
     * 根据SQL语句不同，查询post
     * @param sql
     * @param objs
     * @return
     */
    public List<PostInfo> doQueryPostList(String sql,Object ...objs);
    
    /**
     * 查询全部帖子
     * 
     * @return
     */
    public List<PostInfo> doQueryAllPost();

    /**
     * 分页查询，一页有20条帖子
     * 查询某一页，sql语句用limit关键字，
     * 分页查询 一页3行  查询第3页
     * select * from post ORDER BY post_id desc limit 6,3
     * 6=（页数-1）*行数
     * @param page
     * @return
     */
    public List<PostInfo> doQueryAllPostByPage(int page);

    /**
     * 分页查询，一页有20条帖子
     * 
     * @param startPage
     *            起始页
     * @param pageCount
     *            查询页数
     * @return
     */
    public List<PostInfo> doQueryAllPostByPage(int startPage, int pageCount);

    /**
     * 查询某一个人的帖子
     * 
     * @param userInfo
     * @return
     */
    public List<PostInfo> doQueryUserPosts(UserInfo userInfo);

    /**
     * 查询某人某一天的帖子
     * 
     * @param userinfo
     * @param dayTime
     * @return
     */
    public List<PostInfo> doQueryUserPosts(UserInfo userinfo, long dayTime);
}
