package com.firday.bean;

import java.io.Serializable;

public class UserInfo implements Serializable{

    private static final long serialVersionUID = 1L;
	public static final String DB_USERID="id";						//id
	public static final String DB_USERACCOUNT="account";//个人登录账号（手机号）
	public static final String DB_USERNAME="username";;
	public static final String DB_PASSWORD="password";
	public static final String DB_USERROLE="role";
	public static final String DB_GENDER="gender";
	public static final String DB_PORTRAIT="portrait";
	public static final String DB_JOB="job";
	public static final String DB_USERSTATUS="status";
	public static final String DB_ADDRESS="address";
	public static final String DB_SIGNATURE="signature";
	public static final String DB_INSTRUCTION="instruction";
	public static final String DB_REGISTER_DATE="reg_date";
	public static final String TAG="user";
	
	private int userId;
	private String username;
	private String userAccount;
	private String gender;
	private String password;
	private String portrait;
	private long registerDate;
	
	/**
	 * 0：未激活（注册之后）
	 * 1：正常
	 * 2：拉黑
	 */
	private int userStatus=-1;
	
	
	//职业
	private String job;
	//所在地
	private String address;
	//个性签名
	private String signature;
	//个人简介
	private String instructions;
	
	/**
	 * -1：未指定
	 * 1：管理员
	 * 0：普通用户
	 */
	private int role=-1;
	
	public UserInfo(int userId, String username, String userAccount, String gender, String password, String portrait,
			int userStatus, String job, String address, String signature, String instructions, int role) {
		super();
		this.userId = userId;
		this.username = username;
		this.userAccount = userAccount;
		this.gender = gender;
		this.password = password;
		this.portrait = portrait;
		this.userStatus = userStatus;
		this.job = job;
		this.address = address;
		this.signature = signature;
		this.instructions = instructions;
		this.role = role;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public long getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(long registerDate) {
		this.registerDate = registerDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public UserInfo(int userId, String username, String userAccount, String gender, String password, 
			String portrait, int role) {
		super();
		this.userId = userId;
		this.username = username;
		this.userAccount = userAccount;
		this.gender = gender;
		this.password = password;
		this.portrait = portrait;
		this.role = role;
	}

	public UserInfo(int userId, String username, String userAccount, String gender, String password) {
	    super();
	    this.userId = userId;
	    this.username = username;
	    this.userAccount = userAccount;
	    this.gender = gender;
	    this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getGender() {
	    return gender;
	}

	public void setGender(String gender) {
	    this.gender = gender;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public UserInfo() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", username=" + username + ", userAccount=" + userAccount + ", gender="
				+ gender + ", password=" + password + ", portrait=" + portrait + ", job=" + job + ", address=" + address
				+ ", signature=" + signature + ", instructions=" + instructions + ", role=" + role + "]";
	}	


}
