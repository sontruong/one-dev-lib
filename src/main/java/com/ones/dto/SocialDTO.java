package com.ones.dto;

import com.ones.type.SocialProvider;

public class SocialDTO {
	SocialProvider socialProvider;
	String email;
	String token;
	String id;
	/**
	 * @return the socialProvider
	 */
	public SocialProvider getSocialProvider() {
		return socialProvider;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param socialProvider the socialProvider to set
	 */
	public void setSocialProvider(SocialProvider socialProvider) {
		this.socialProvider = socialProvider;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}
