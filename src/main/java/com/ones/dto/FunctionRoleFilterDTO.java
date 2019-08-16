/**
 * 
 */
package com.ones.dto;

/**
 * @author son.truong Dec 16, 2017 4:42:44 PM
 */
public class FunctionRoleFilterDTO extends BaseFilter {

	private Long functionId;
	private String role;

	/** constructor of AuthorityRoleFilterDTO */
	public FunctionRoleFilterDTO() {
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
