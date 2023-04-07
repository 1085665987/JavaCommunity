package com.firday.bean;

public class UserInterest {

	private  int interestId;
	private UserInfo  interestUser;
	private  UserInfo  interestedUser;
	public int getInterestId() {
		return interestId;
	}
	public void setInterestId(int interestId) {
		this.interestId = interestId;
	}
	public UserInfo getInterestUser() {
		return interestUser;
	}
	public void setInterestUser(UserInfo interestUser) {
		this.interestUser = interestUser;
	}
	public UserInfo getInterestedUser() {
		return interestedUser;
	}
	public void setInterestedUser(UserInfo interestedUser) {
		this.interestedUser = interestedUser;
	}
}
