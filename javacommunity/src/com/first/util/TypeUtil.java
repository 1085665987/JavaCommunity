package com.first.util;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.firday.bean.CommentInfo;
import com.firday.bean.PostInfo;
import com.firday.bean.UserInfo;

/**
 * ����װ��Util��
 * @author Friday
 *
 */
public class TypeUtil {
	
	/**
	 * ����װ������UserInfo->Map
	 * @param user
	 * @return
	 */
	public Map<String, Object> userToMap(UserInfo user){
	    return null;
	}
	
	/**
	 * ����װ������Map->UserInfo
	 * @param userMap
	 * @return
	 */
	public  UserInfo mapToUser(Map<String, Object> userMap){
	    return null;
	}
	
	/**
	 * ����װ������Map->PostInfo
	 * @param postMap
	 * @return
	 */
	public PostInfo mapToPost(Map<String, Object> postMap){
	    return null;
	}
	
	/**
	 * ����װ������PostInfo->Map 
	 * @param post
	 * @return
	 */
	public Map<String, Object> postToMap(PostInfo post){
	    return null;
	}
	
	/**
	 * ����װ������Map->CommentInfo
	 * @param commentMap
	 * @return
	 */
	public CommentInfo mapToComment(Map<String, Object> commentMap){
	    return null;
	}
	
	/**
	 * ����װ������CommentInfo->Map 
	 * @param comment
	 * @return
	 */
	public Map<String, Object> commentToMap(CommentInfo comment){
	    return null;
	}
}
