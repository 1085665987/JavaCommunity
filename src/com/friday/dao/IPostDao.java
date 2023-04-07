package com.friday.dao;

import java.util.List;

import com.friday.bean.PostContent;
import com.friday.bean.PostInfo;
import com.friday.bean.UserInfo;

public interface IPostDao {
    /**
     * ��ѯ����
     * a�ڵ㣨�ؼ��֣�
     * bҳ��
     * c��
     */

    /**
     * ����ĳ�ڵ����ͣ���ѯ������ѯĳ�ڵ��µ���������
     * @param node
     * @return
     */
    public List<PostInfo> doQueryPostByType(int nodeId);

    /************************************************************/
    /**
     * ��������
     *
     * @param post
     * @return
     */
    public PostInfo doAddPost(PostInfo post);

    /**
     * �޸�
     *
     * @param newPost
     * @param oldPost
     */
    public int doUpdatePost(PostInfo newPost, PostInfo oldPost);

    /**
     * �޸�
     *
     * @param postId
     * @param newContent
     */
    public int doUpdatePost(int postId, PostContent newContent);

    /**
     * ɾ��ĳһ����
     *
     * @param post
     * @return
     */
    public int doDeletePost(PostInfo post);

    /**
     * ɾ��ĳһ����
     *
     * @param postId
     * @return
     */
    public int doDeletePost(int postId);

    /**
     * ɾ����������
     *
     * @param posts
     * @return
     */
    public int doDeletePosts(List<PostInfo> posts);

    /**
     * ɾ����������
     *
     * @param postIds
     * @return
     */
    public int doDeletePostsById(int ... postIds);
    /**
     * ɾ����������
     * @param postIds
     * @return
     */
    public int doDeletePostsById(List<Integer> postIds);

    /**
     * ɾ��ĳ���˵�����
     *
     * @param user
     * @return
     */
    public int doDeletePosts(UserInfo user);

    /**
     * ��������id��ѯ����
     *
     * @param postId
     * @return
     */
    public PostInfo doQueryPost(int postId);

    /**
     * ��ѯ����
     *
     * @param post
     * @return
     */
    public PostInfo doQueryPost(PostInfo post);

    /**
     * ��ѯ���ӵ�ͼƬ
     * @param post
     * @return
     */
    public List<String> doQueryPostImgList(PostInfo post);
    /**
     * ��ѯ���ӵ�ͼƬ
     * @param postId
     * @return
     */
    public List<String> doQueryPostImgList(int postId);

    /**
     * ����SQL��䲻ͬ����ѯpost
     * @param sql
     * @return
     */
    public List<PostInfo> doQueryPostList(String sql);

    /**
     * ����SQL��䲻ͬ����ѯpost
     * @param sql
     * @param objs
     * @return
     */
    public List<PostInfo> doQueryPostList(String sql,Object ...objs);

    /**
     * ��ѯȫ������
     *
     * @return
     */
    public List<PostInfo> doQueryAllPost();

    /**
     * ��ҳ��ѯ��һҳ��20������
     * ��ѯĳһҳ��sql�����limit�ؼ��֣�
     * ��ҳ��ѯ һҳ3��  ��ѯ��3ҳ
     * select * from post ORDER BY post_id desc limit 6,3
     * 6=��ҳ��-1��*����
     * @param page
     * @return
     */
    public List<PostInfo> doQueryAllPostByPage(int page);

    /**
     * ��ҳ��ѯ��һҳ��20������
     *
     * @param startPage
     *            ��ʼҳ
     * @param pageCount
     *            ��ѯҳ��
     * @return
     */
    public List<PostInfo> doQueryAllPostByPage(int startPage, int pageCount);

    /**
     * ��ѯĳһ���˵�����
     *
     * @param userInfo
     * @return
     */
    public List<PostInfo> doQueryUserPosts(UserInfo userInfo);

    /**
     * ��ѯĳ��ĳһ�������
     *
     * @param userinfo
     * @param dayTime
     * @return
     */
    public List<PostInfo> doQueryUserPosts(UserInfo userinfo, long dayTime);
}
