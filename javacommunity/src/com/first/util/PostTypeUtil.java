package com.first.util;

import java.sql.Timestamp;
import java.util.Map;

import com.firday.bean.PostInfo;

public class PostTypeUtil extends TypeUtil{

    @Override
    public PostInfo mapToPost(Map<String, Object> postMap) {
	int postId=(Integer) postMap.get(PostInfo.DB_POST_ID); 
	int senderId=(Integer) postMap.get(PostInfo.DB_SENDER_ID); 
	Timestamp timestamp=(Timestamp)postMap.get(PostInfo.DB_SEND_TIME);
	long sendTime=timestamp.getTime();
	String postType=(String) postMap.get(PostInfo.DB_POST_TYPE);
	String postTitle=(String) postMap.get(PostInfo.DB_POST_TITLE);
	String senderName=(String)postMap.get(PostInfo.DB_SENDER_NAME);
	int loveCount=(Integer)postMap.get(PostInfo.DB_LOVE_COUNT);
	int commentCount=(Integer)postMap.get(PostInfo.DB_COMMENT_COUNT);
	int collectionCount=(Integer)postMap.get(PostInfo.DB_COLLECTION_COUNT);
	
	PostInfo post=new PostInfo();
	post.setPostId(postId);
	post.setSenderId(senderId);
	post.setSendTime(sendTime);
	post.setPostType(postType);
	post.setPostTitle(postTitle);
	post.setSenderName(senderName);
	post.setLoveCount(loveCount);
	post.setCommentCount(commentCount);
	post.setCollectionCount(collectionCount);
	return post;
    }
}
