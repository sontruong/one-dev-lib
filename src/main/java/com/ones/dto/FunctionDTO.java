/**
 * 
 */
package com.ones.dto;

import javax.validation.constraints.NotNull;

import com.ones.utils.ApplicationMessage;

/**
 * @author son.truong Dec 16, 2017 4:42:44 PM
 */
public class FunctionDTO {

	private Long id;
	@NotNull(message = ApplicationMessage.FUNCTION_NAME_NULL)
	private String name;
	@NotNull(message = ApplicationMessage.FUNCTION_AUTORITYKEY_NULL)
	private String functionKey;
	private String api;
	private String method;
	private Boolean allowAll;
	private Boolean onOrg;
	private Boolean onObj;
	private String icon;
	private Long parent;
	/** constructor of AuthorityDTO */
	public FunctionDTO() {
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

}
