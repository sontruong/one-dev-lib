/**
 * 
 */
package com.ones.dto;

import java.util.Collection;

/**
 * @author son.truong Dec 16, 2017 4:42:44 PM
 */
public class FunctionFilterDTO extends BaseFilter {

	private Long id;
	private String name;
	private String functionKey;
	private String api;
	private String method;
	private Boolean allowAll;
	private Boolean onOrg;
	private Boolean onObj;
	private Long userId;
	private Collection<String> roles;

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @return the roles
	 */
	public Collection<String> getRoles() {
		return roles;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(Collection<String> roles) {
		this.roles = roles;
	}

	/** constructor of AuthorityFilterDTO */
	public FunctionFilterDTO() {
		super();
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

	public String getApi() {
		return this.api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Boolean getAllowAll() {
		return this.allowAll;
	}

	public void setAllowAll(Boolean allowAll) {
		this.allowAll = allowAll;
	}

	public Boolean getOnOrg() {
		return this.onOrg;
	}

	public void setOnOrg(Boolean onOrg) {
		this.onOrg = onOrg;
	}

	public Boolean getOnObj() {
		return this.onObj;
	}

	public void setOnObj(Boolean onObj) {
		this.onObj = onObj;
	}

	public String getFunctionKey() {
		return functionKey;
	}

	public void setFunctionKey(String functionKey) {
		this.functionKey = functionKey;
	}

}
