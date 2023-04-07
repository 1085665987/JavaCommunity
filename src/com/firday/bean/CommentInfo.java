package com.firday.bean;

import java.io.Serializable;

/**
 * @author Friday
 *  帖子评论
 */
public class CommentInfo  implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static final String DB_COMMENT_ID="comment_id";
    public static final String DB_COMMENT_CONTENT="comment_content";
    public static final String DB_SENDER_ID="sender_id";    
    public static final String DB_SENDER_NAME="sender_name";
    public static final String DB_SEND_TIME="send_time";
    
    
    private int commentId;			//评论id
    private String commentContent;		//评论正文，字符串
    private int senderId;				//发送者id	
    
    private String senderName;		//发送者姓名
    private long sendTime;			//发送时间
    
    private int postId;				//帖子的id
    
    public CommentInfo() {
	super();
    }

    public CommentInfo(int commentId, String commentContent, int senderId, String senderName, long sendTime,int postId) {
	this.commentId = commentId;
	this.commentContent = commentContent;
	this.senderId = senderId;
	this.senderName = senderName;
	this.sendTime = sendTime;
	this.postId = postId;
    }

    @Override
    public String toString() {
	return "CommentInfo [commentId=" + commentId + ", commentContent=" + commentContent + ", senderId=" + senderId
		+ ", senderName=" + senderName + ", sendTime=" + sendTime + ", postId=" + postId + "]";
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
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

    public long getSnederTime() {
        return sendTime;
    }

    public void setSnederTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
