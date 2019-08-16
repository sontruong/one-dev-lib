/**
 * 
 */
package com.ones.dto;

import javax.validation.constraints.NotNull;

import com.ones.utils.ApplicationMessage;

/**
 * @author son.truong Dec 16, 2017 4:42:44 PM
 */
public class FunctionUserDTO {

	@NotNull(message = ApplicationMessage.FUNCTIONUSER_USERID_NULL)
	private Long userId;
	@NotNull(message = ApplicationMessage.FUNCTIONUSER_FUNCTIONID_NULL)
	private Long functionId;

	/** constructor of AuthorityUserDTO */
	public FunctionUserDTO() {
		super();
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Long functionId) {
		this.functionId = functionId;
	}

}
