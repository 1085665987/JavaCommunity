package com.firday.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.firday.bean.UserInfo;

public class UserRowMapperUtil extends RowMapperUtil<UserInfo> {
	@Override
	public UserInfo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		UserInfo user = new UserInfo();
		user.setUserId(resultSet.getInt(UserInfo.DB_USERID));
		user.setUsername(resultSet.getString(UserInfo.DB_USERNAME));
		user.setPassword(resultSet.getString(UserInfo.DB_PASSWORD));
		user.setUserAccount(resultSet.getString(UserInfo.DB_USERACCOUNT));
		user.setGender(resultSet.getString(UserInfo.DB_GENDER));
		user.setRole(resultSet.getInt(UserInfo.DB_USERROLE));
		user.setAddress(resultSet.getString(UserInfo.DB_ADDRESS));
		user.setInstructions(resultSet.getString(UserInfo.DB_INSTRUCTION));
		user.setJob(resultSet.getString(UserInfo.DB_JOB));
		user.setPortrait(resultSet.getString(UserInfo.DB_PORTRAIT));
		user.setUserStatus(resultSet.getInt(UserInfo.DB_USERSTATUS));
		user.setSignature(resultSet.getString(UserInfo.DB_SIGNATURE));
		Timestamp timestamp = resultSet.getTimestamp(UserInfo.DB_REGISTER_DATE);
		user.setRegisterDate(timestamp.getTime());
		return user;
	}

}
