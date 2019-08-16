package com.ones.security;

import com.google.gson.annotations.Expose;

public class UserAuthorities {
	
	@Expose(serialize = true, deserialize = true)
	private String id;

	@Expose(serialize = true, deserialize = true)
	private String authorityId;

	@Expose(serialize = true, deserialize = true)
	private String userId;

	@Expose(serialize = true, deserialize = true)
	private String clientId;

	@Expose(serialize = true, deserialize = true)
	private Long orgId;
	
	@Expose(serialize = true, deserialize = true)
	private Authority authorityAuthorityId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Authority getAuthorityAuthorityId() {
		return authorityAuthorityId;
	}

	public void setAuthorityAuthorityId(Authority authorityAuthorityId) {
		this.authorityAuthorityId = authorityAuthorityId;
	}

	@Override
	public String toString() {
		return "ClassPojo [id = " + id + ", authorityId = " + authorityId + ", userId = " + userId + ", clientId = "
				+ clientId + ", authorityAuthorityId = " + authorityAuthorityId + "]";
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
}
