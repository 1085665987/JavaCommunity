package com.firday.util;

import java.util.Map;

import com.firday.bean.CommentInfo;
import com.firday.bean.PostInfo;

public class CommentTypeUtil extends TypeUtil {

    @Override
    public CommentInfo mapToComment(Map<String, Object> commentMap) {
	String commentContent=(String) commentMap.get(CommentInfo.DB_COMMENT_CONTENT);
	int senderId=(Integer)commentMap.get(CommentInfo.DB_SENDER_ID);

	String senderName =(String)commentMap.get(CommentInfo.DB_SENDER_NAME);

	long sendTime=(long)commentMap.get(CommentInfo.DB_SEND_TIME);
	int commentId=(Integer)commentMap.get(CommentInfo.DB_COMMENT_ID);
	int postId=(Integer)commentMap.get(PostInfo.DB_POST_ID);
	return new CommentInfo(commentId, commentContent, senderId, senderName, sendTime, postId);
    }

    @Override
    public Map<String, Object> commentToMap(CommentInfo comment) {
	return super.commentToMap(comment);
    }

}
