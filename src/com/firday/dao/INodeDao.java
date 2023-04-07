package com.firday.dao;

import java.util.List;

import com.firday.bean.Node;
import com.firday.bean.PostInfo;

public interface INodeDao {

    /**
     * ��ѯȫ���ڵ�
     * ���ڵ�1��
     * 		{
     * 			�ӽڵ�1��
     * 			�ӽڵ�2��������
     * 		}
     * ���ڵ�2��
     * 		{
     * 			�ӽڵ�1��
     * 			�ӽڵ�2��������
     * 		}
     * @return
     */
    public List<Node> doQueryAllNode() ;

    /**
     * ɾ��ĳһ���ڵ�
     * @return
     */
    public int doDeleteParentNodeById(int nodeId);

    /**
     * ɾ��ĳһ�ӽڵ�
     * @return
     */
    public int doDeleteChildNodeById(int nodeId);

    /**
     * ����ĳһ���ڵ�
     * @param parentNode
     * @return
     */
    public Node doAddParentNode(Node parentNode);

    /**
     * ����ĳһ�ӽڵ�
     * @param parentNode
     * @return
     */
    public Node doAddChildNode(Node parentNode,Node childNode);

    /**
     * ��ѯĳһ�ڵ����ϸ���
     * @param nodeId
     * @return
     */
	public Node doQueryChildNodeById(int nodeId);

	/**
	 * ��ѯ�ڵ��µ�����
	 * @param nodeId
	 * @return
	 */
	public List<PostInfo> doQueryNodePosts(int nodeId);

	/**
	 * ��ѯ�����ӽڵ�
	 * ÿһ���ӽڵ㶼���丸�ڵ���Ϣ
	 * @return
	 */
	public List<Node> doQueryAllChildNode();


	/**
	 * ��ҳ��ѯ�����ӽڵ�
	 * @param page ҳ��
	 * @return
	 */
	public List<Node> doQueryAllChildNodeByPage(int page);

	/**
	 * ��ѯ�����ӽڵ�
	 * @param sql sql���
	 * @param params ����
	 * @return
	 */
	public List<Node> doQueryAllChildNode(String sql, Object ...params);

	/**
	 * ��ѯһ���ж���ҳ�ڵ�
	 * һҳ�ڵ�10��
	 */
	public int doQueryChildNodePageCount();

	/**
	 * ��ѯ���и��ڵ�
	 * @return
	 */
	public List<Node> doQueryAllParentNode();

	/**
	 * �����ӽڵ���Ϣ
	 * @return
	 */
	public	int doUpdateChildNode(Node childNode);

	/**
	 * �����ӽڵ���Ϣ
	 * @param childNodeId
	 * @param params
	 * @return
	 */
	public int doUpdateChildNode(int childNodeId, Object  ... params);
}
