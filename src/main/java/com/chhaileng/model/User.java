package com.chhaileng.model;

public class User {
	private String sessionId;
	private String username;
	
	public User() {
		
	}
	
	public User(String sessionId, String username) {
		this.sessionId = sessionId;
		this.username = username;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [sessionId=" + sessionId + ", username=" + username + "]";
	}

}
