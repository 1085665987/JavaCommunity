package com.first.util;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.firday.bean.CommentInfo;
import com.firday.bean.PostInfo;
import com.firday.bean.UserInfo;

/**
 * 类型装换Util类
 * @author Friday
 *
 */
public class TypeUtil {
	
	/**
	 * 类型装换：由UserInfo->Map
	 * @param user
	 * @return
	 */
	public Map<String, Object> userToMap(UserInfo user){
	    return null;
	}
	
	/**
	 * 类型装换：由Map->UserInfo
	 * @param userMap
	 * @return
	 */
	public  UserInfo mapToUser(Map<String, Object> userMap){
	    return null;
	}
	
	/**
	 * 类型装换：由Map->PostInfo
	 * @param postMap
	 * @return
	 */
	public PostInfo mapToPost(Map<String, Object> postMap){
	    return null;
	}
	
	/**
	 * 类型装换：由PostInfo->Map 
	 * @param post
	 * @return
	 */
	public Map<String, Object> postToMap(PostInfo post){
	    return null;
	}
	
	/**
	 * 类型装换：由Map->CommentInfo
	 * @param commentMap
	 * @return
	 */
	public CommentInfo mapToComment(Map<String, Object> commentMap){
	    return null;
	}
	
	/**
	 * 类型装换：由CommentInfo->Map 
	 * @param comment
	 * @return
	 */
	public Map<String, Object> commentToMap(CommentInfo comment){
	    return null;
	}
}
