/**
 * 
 */
package com.ones.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;

import com.google.gson.Gson;
import com.ones.utils.ApplicationMessage;

/**
 * @author son.truong Apr 13, 2017 11:03:56 PM
 */
public class BaseSecurityChecker {
	private static final Logger LOG = LoggerFactory.getLogger(BaseSecurityChecker.class);

	public AppUserDetails getLoggingUser() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication auth = securityContext.getAuthentication();
		AppUserDetails appUserDetails = null;
		try {
			Gson gson = new Gson();
			System.out.println("===========: " + gson.toJson(auth));
			appUserDetails = (AppUserDetails) auth.getPrincipal();
		} catch (Exception e) {
			LOG.error("==[getLoggingUser] " + e.getMessage(), e);
		}
		if (null == appUserDetails) {
			throw new UnauthorizedUserException(ApplicationMessage.UNAUTHORISED);
		}
		return appUserDetails;
	}
	
	public String getLoggingUsername() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication auth = securityContext.getAuthentication();
		String username = null;
		try {
			username = "" + auth.getPrincipal();
		} catch (Exception e) {
			LOG.error("==[getLoggingUser] " + e.getMessage(), e);
		}
		if (null == username) {
			throw new UnauthorizedUserException(ApplicationMessage.UNAUTHORISED);
		}
		return username;
	}
}
