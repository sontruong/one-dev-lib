/**
 * 
 */
package com.ones.dto;

/**
 * @author son.truong Dec 16, 2017 4:42:44 PM
 */
public class FunctionUserFilterDTO extends BaseFilter {

	private Long userId;
	private Long functionId;

	/** constructor of AuthorityUserFilterDTO */
	public FunctionUserFilterDTO() {
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
