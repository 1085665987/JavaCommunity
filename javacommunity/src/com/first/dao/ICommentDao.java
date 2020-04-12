package com.first.dao;

import java.util.List;

import com.firday.bean.CommentInfo;
import com.firday.bean.PostInfo;

public interface ICommentDao {

    /**
     * ��������
     * 
     * @param postId
     * @param comment
     * @return
     */
    public CommentInfo doAddComment(CommentInfo comment);

    /**
     * ɾ��ĳһ������
     * 
     * @param commentId
     * @return
     */
    public int doDeleteComment(int commentId);

    /**
     * ɾ��ĳһ������
     * 
     * @param comment
     * @return
     */
    public int doDeleteComment(CommentInfo comment);

    /**
     * ɾ��ĳһ����֮�µ���������
     * 
     * @param post
     * @return
     */
    public int deDeleteComments(PostInfo post);

    /**
     * ɾ��ĳһ����֮�µ���������
     * 
     * @param postId
     * @return
     */
    public int doDeleteComments(int postId);

    /**
     * ����ɾ������
     * 
     * @param commentList
     * @return
     */
    public int doDeleteComments(List<CommentInfo> commentList);

    /**
     * ��ѯĳһ������
     * 
     * @param commentId
     * @return
     */
    public CommentInfo doQueryComment(int commentId);

    /**
     * ��ѯĳһ������
     * 
     * @param comment
     * @return
     */
    public CommentInfo doQueryComment(CommentInfo comment);

    /**
     * ��ѯĳһ���ӵ���������
     * @param postId
     * @return
     */
    public List<CommentInfo> doQueryCommentList(int postId);

    /**
     * ��ѯĳһ���ӵ���������
     * @param post
     * @return
     */
    public List<CommentInfo> doQueryCommentList(PostInfo post);

}
