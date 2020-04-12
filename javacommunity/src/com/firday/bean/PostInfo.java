package com.firday.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author Friday 帖子
 */
public class PostInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int postId; // 帖子id

    private PostContent postContent; // 帖子正文,可能是图片，也可能是文字
    private String postTitle; // 帖子标题
    
    private long sendTime; // 帖子发布时间
    
    private int loveCount;	//点赞数
    private int commentCount;//评论数
    private int collectionCount;//收藏数
    
    private int senderId; // 发帖人id
    private String senderName; // 发帖人姓名
    private UserInfo sender;	//帖子发送人
    
    private int childNodeId;//子节点id
    private String postType; // 帖子节点名字：分享发现，爬虫，水一波。。。。。
    private Node childNode;//所属节点
    
    public UserInfo getSender() {
		return sender;
	}

	public Node getChildNode() {
		return childNode;
	}

	public void setChildNode(Node childNode) {
		this.childNode = childNode;
	}

	public void setSender(UserInfo sender) {
		this.sender = sender;
	}

	public static final String DB_POST_ID="post_id";

    public static final String DB_SENDER_ID="sender_id";
    public static final String DB_SEND_TIME="send_time";
    public static final String DB_POST_TYPE="post_type";
    public static final String DB_POST_TITLE="post_title";
    public static final String DB_SENDER_NAME = "sender_name";
    public static final String DB_PAGECOUNT="page_count";
    
    public static final String DB_LOVE_COUNT = "love_count";
    public static final String DB_COMMENT_COUNT="comment_count";
    public static final String DB_COLLECTION_COUNT="collection_count";

    private List<CommentInfo> commentList; // 帖子的评论

    public int getPostId() {
	return postId;
    }

    public void setPostId(int postId) {
	this.postId = postId;
    }

    public int getChildNodeId() {
		return childNodeId;
	}

	public void setChildNodeId(int childNodeId) {
		this.childNodeId = childNodeId;
	}

	public PostContent getPostContent() {
	return postContent;
    }

    public void setPostContent(PostContent postContent) {
	this.postContent = postContent;
    }

    public String getPostTitle() {
	return postTitle;
    }

    public void setPostTitle(String postTitle) {
	this.postTitle = postTitle;
    }

    public int getSenderId() {
	return senderId;
    }

    public void setSenderId(int senderId) {
	this.senderId = senderId;
    }

    public String getSenderName() {
	return senderName;
    }

    public void setSenderName(String senderName) {
	this.senderName = senderName;
    }

    public long getSendTime() {
	return sendTime;
    }

    public void setSendTime(long sendTime) {
	this.sendTime = sendTime;
    }

    public String getPostType() {
	return postType;
    }

    public void setPostType(String postType) {
	this.postType = postType;
    }

    public List<CommentInfo> getCommentList() {
	return commentList;
    }

    public void setCommentList(List<CommentInfo> commentList) {
	this.commentList = commentList;
    }

    public int getLoveCount() {
		return loveCount;
	}

	public void setLoveCount(int loveCount) {
		this.loveCount = loveCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getCollectionCount() {
		return collectionCount;
	}

	public void setCollectionCount(int collectionCount) {
		this.collectionCount = collectionCount;
	}

	public PostInfo(int postId, PostContent postContent) {
	super();
	this.postId = postId;
	this.postContent = postContent;
    }

    @Override
	public String toString() {
		return "PostInfo [postId=" + postId + ", postContent=" + postContent + ", postTitle=" + postTitle
				+ ", senderId=" + senderId + ", senderName=" + senderName + ", sendTime=" + sendTime + ", postType="
				+ postType + ", loveCount=" + loveCount + ", commentCount=" + commentCount + ", collectionCount="
				+ collectionCount + ", commentList=" + commentList + "]";
	}

	public PostInfo() {
    }

    public PostInfo(int postId, String postTitle, PostContent postContent, int senderId, String senderName,
	    long sendTime, String postType) {
	super();
	this.postId = postId;
	this.postContent = postContent;
	this.postTitle = postTitle;
	this.senderId = senderId;
	this.senderName = senderName;
	this.sendTime = sendTime;
	this.postType = postType;
    }

}
