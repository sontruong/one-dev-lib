/**
 * 
 */
package com.ones.utils;

/**
 * @author son.truong 
 * Apr 13, 2017 11:19:02 PM
 */
public class ApplicationMessage {

	//common
	public static final String DATE_IN_PAST = "date.must.be.greater.equal.now";
	
	// Permission
	public static final String UNAUTHORISED = "UNAUTHORIZED";
	public static final String NOT_ACCEPTABLE = "NOT_ACCEPTABLE";
	public static final String LOGIN_FAIL = "LOGIN_FAIL";
	public static final String ACCOUNT_IS_BLOCKED = "ACCOUNT_BLOCKED";
	public static final String ACCOUNT_NOT_RIGHT = "ACCOUNT_NOT_RIGHT";

	// Server
	public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
	public static final String BANDWIDTH_LIMIT_EXCEEDED = "BANDWIDTH_LIMIT_EXCEEDED";
	public static final String REQUEST_PARAM_REQUIRE = "REQUEST_PARAM_REQUIRE";
	public static final String WRONG_PARAM_TYPE = "WRONG_PARAM_TYPE";
	public static final String REQUEST_BODY_REQUIRE = "REQUEST_BODY_REQUIRE";
	public static final String NOT_FOUND_EXCEPTION = "NOT_FOUND_EXCEPTION";

	// success message
	public static final String SUCCESS = "SUCCESS";
	public static final String FAIL = "FAIL";

	// default error message
	public static final String VALUE_NOT_RIGHT = "VALUE_NOT_RIGHT";
	public static final String ATTACHMENT_NOT_FOUND = "ATTACHMENT_NOT_FOUND";

	// user
	public static final String USER_ID_NULL = "user.id.null";
	public static final String USER_EMAIL_NULL = "user.email.null";
	public static final String USER_EMAIL_EXIST = "user.email.exist";
	public static final String USER_NOT_EXIST = "user.not.exist";
	public static final String USER_FIRST_NULL = "user.firstname.null";
	public static final String USER_LAST_NULL = "user.lastname.null";
	public static final String USER_USERNAME_NULL = "user.username.null";
	
	// Function
	public static final String FUNCTION_NAME_NULL = "function.name.null";
	public static final String FUNCTION_AUTORITYKEY_NULL = "function.autoritykey.null";

	// Function role
	public static final String FUNCTIONROLE_AUTHORITYID_NULL = "functionrole.functionid.null";

	// function user
	public static final String FUNCTIONUSER_USERID_NULL = "functionuser.userid.null";
	public static final String FUNCTIONUSER_FUNCTIONID_NULL = "functionuser.functionid.null";
	
	//Authority
	public static final String AUTHORITY_NAME_NULL = "authority.name.null";
	
	//Organization
	public static final String ORGANIZATION_CODE_NULL = "organization.code.null";
	public static final String ORGANIZATION_CREATEDFOR_NULL = "organization.createfor.null";
	
	
	//workflow history
	public static final String WHISTORY_ENTITYTYPE_NULL = "whistory.entitytype.null";
    public static final String WHISTORY_ENTITYID_NULL = "whistory.entityid.null";
    public static final String WHISTORY_ACTEDBY_NULL = "whistory.actedby.null";
    public static final String WHISTORY_ACTEDON_NULL = "whistory.actedon.null";
    public static final String WHISTORY_ENTITYSTATUS_NULL = "whistory.entitystatus.null";
    public static final String WHISTORY_COMMENT_RANGE_0_255 = "content.whistory.comment.range.0.255";
    public static final String WHISTORY_DIFF_RANGE_0_65535 = "content.whistory.diff.range.0.65535";

}
