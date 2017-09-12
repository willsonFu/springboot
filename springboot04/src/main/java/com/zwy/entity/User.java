package com.zwy.entity;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -6456125918610833879L;
	private int userId;
	private String username;
	private String password;
	private String nickName;
	private String access_token;
	private int expires_in;
	private String refresh_access_token;
	private int refresh_expires_in;

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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_access_token() {
		return refresh_access_token;
	}

	public void setRefresh_access_token(String refresh_access_token) {
		this.refresh_access_token = refresh_access_token;
	}

	public int getRefresh_expires_in() {
		return refresh_expires_in;
	}

	public void setRefresh_expires_in(int refresh_expires_in) {
		this.refresh_expires_in = refresh_expires_in;
	}
}
