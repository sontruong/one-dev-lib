/**
 * 
 */
package com.ones.dto;

import javax.validation.constraints.NotNull;

import com.ones.utils.ApplicationMessage;

/**
 * @author son.truong Dec 16, 2017 4:42:44 PM
 */
public class FunctionRoleDTO {

	@NotNull(message = ApplicationMessage.FUNCTIONROLE_AUTHORITYID_NULL)
	private Long functionId;
	private String role;

	/** constructor of AuthorityRoleDTO */
	public FunctionRoleDTO() {
		super();
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Long functionId) {
		this.functionId = functionId;
	}

}
