package com.first.dao;

import java.util.List;

import com.firday.bean.CommentInfo;
import com.firday.bean.PostInfo;

public interface ICommentDao {

    /**
     * 增加评论
     * 
     * @param postId
     * @param comment
     * @return
     */
    public CommentInfo doAddComment(CommentInfo comment);

    /**
     * 删除某一条评论
     * 
     * @param commentId
     * @return
     */
    public int doDeleteComment(int commentId);

    /**
     * 删除某一条评论
     * 
     * @param comment
     * @return
     */
    public int doDeleteComment(CommentInfo comment);

    /**
     * 删除某一帖子之下的所有评论
     * 
     * @param post
     * @return
     */
    public int deDeleteComments(PostInfo post);

    /**
     * 删除某一帖子之下的所有评论
     * 
     * @param postId
     * @return
     */
    public int doDeleteComments(int postId);

    /**
     * 批量删除评论
     * 
     * @param commentList
     * @return
     */
    public int doDeleteComments(List<CommentInfo> commentList);

    /**
     * 查询某一条评论
     * 
     * @param commentId
     * @return
     */
    public CommentInfo doQueryComment(int commentId);

    /**
     * 查询某一条评论
     * 
     * @param comment
     * @return
     */
    public CommentInfo doQueryComment(CommentInfo comment);

    /**
     * 查询某一帖子的所有评论
     * @param postId
     * @return
     */
    public List<CommentInfo> doQueryCommentList(int postId);

    /**
     * 查询某一帖子的所有评论
     * @param post
     * @return
     */
    public List<CommentInfo> doQueryCommentList(PostInfo post);

}
