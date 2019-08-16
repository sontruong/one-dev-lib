package com.ones.security;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

/**
 * @author son.truong Dec 8, 2016 10:52:07 PM
 */

public class Authority implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose(serialize = true, deserialize = true)
	private Long id;

	@Expose(serialize = true, deserialize = true)
	private String name;

	@Expose(serialize = true, deserialize = true)
	private String clientId;
	
	private Long orgId;

	public Authority() {
	}

	public Authority(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
}
