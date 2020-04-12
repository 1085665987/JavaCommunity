package com.first.util;

import java.util.HashMap;
import java.util.Map;

import com.firday.bean.UserInfo;

/**
 * ¼Ì³ÐÓÚTypeUtil
 * @author Friday
 *
 */
public class UserTypeUtil extends TypeUtil {
	
	@Override
	public Map<String, Object> userToMap(UserInfo userInfo) {
		int userId = userInfo.getUserId();
		String account=userInfo.getUserAccount();
		String username = userInfo.getUsername();
		String password = userInfo.getPassword();
		String gender=userInfo.getGender();
		String portrait=userInfo.getPortrait();
		String job=userInfo.getJob();
		String address=userInfo.getAddress();
		String signature=userInfo.getSignature();
		String instruction=userInfo.getInstructions();
		int role=userInfo.getRole();
		int status=userInfo.getUserStatus();
		
		Map<String, Object> userMap = new HashMap<String, Object>();
		if (userId > 0) {
			userMap.put(UserInfo.DB_USERID, userId);
		}
		if (username != null && username.length() > 0) {
			userMap.put(UserInfo.DB_USERNAME, username);
		}
		if (account != null && account.length() > 0) {
			userMap.put(UserInfo.DB_USERACCOUNT, account);
		}
		if (password != null && password.length() > 0) {
			userMap.put(UserInfo.DB_PASSWORD, password);
		}
		if (gender != null && gender.length() > 0) {
			userMap.put(UserInfo.DB_GENDER, gender);
		}
		if (portrait != null && portrait.length() > 0) {
			userMap.put(UserInfo.DB_PORTRAIT, portrait);
		}
		if (job != null && job.length() > 0) {
			userMap.put(UserInfo.DB_JOB, job);
		}
		if (address != null && address.length() > 0) {
			userMap.put(UserInfo.DB_ADDRESS, address);
		}
		if (signature != null && signature.length() > 0) {
			userMap.put(UserInfo.DB_SIGNATURE, signature);
		}
		if (instruction != null && instruction.length() > 0) {
			userMap.put(UserInfo.DB_INSTRUCTION, instruction);
		}
		if(role!=-1){
			userMap.put(UserInfo.DB_USERROLE,role);
		}
		if(status!=-1){
			userMap.put(UserInfo.DB_USERSTATUS,status);
		}
		return userMap;
	}

	@Override
	public UserInfo mapToUser(Map<String, Object> userMap) {
	    int userId = (int) userMap.get(UserInfo.DB_USERID);
	    String userAccount= (String)userMap.get(UserInfo.DB_USERACCOUNT);
	    String username=(String)userMap.get(UserInfo.DB_USERNAME);
	    String password=(String)userMap.get(UserInfo.DB_PASSWORD);
	    String gender=(String)userMap.get(UserInfo.DB_GENDER);
	    String job=(String)userMap.get(UserInfo.DB_JOB);
	    String address=(String)userMap.get(UserInfo.DB_ADDRESS);
	    String signature=(String)userMap.get(UserInfo.DB_SIGNATURE);
	    String instruction=(String)userMap.get(UserInfo.DB_INSTRUCTION);
	    String portrait=(String)userMap.get(UserInfo.DB_PORTRAIT);
	    int role=-1;
	    if(userMap.get(UserInfo.DB_USERROLE)!=null){
	    	role=(Integer)userMap.get(UserInfo.DB_USERROLE);
	    }
	    int status=-1;
	    if(userMap.get(UserInfo.DB_USERSTATUS)!=null){
	    	status=(Integer)userMap.get(UserInfo.DB_USERSTATUS);
	    }
	    return new UserInfo(userId, username, userAccount, gender, password, portrait, status, job, address, signature, instruction, role);
	}
}
