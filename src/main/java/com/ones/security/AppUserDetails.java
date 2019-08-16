package com.ones.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.gson.annotations.Expose;
import com.ones.user.User;
import com.ones.user.function.Function;

/**
 * @author son.truong
 * Apr 13, 2017 11:03:56 PM
 */
@JsonInclude(Include.NON_NULL)
public class AppUserDetails implements UserDetails {

	/** */
	private static final long serialVersionUID = -4777124807325532850L;

	@Expose(serialize = true, deserialize = true)
	private Long id;
	
	@Expose(serialize = true, deserialize = true)
	private
	String username;
	
	private User user;
	
	@JsonIgnore
	String password;

	@Expose(serialize = true, deserialize = true)
	@JsonIgnore
	private Collection<? extends GrantedAuthority> authorities;
	
	@Expose(serialize = true, deserialize = true)
	private Collection<UserAuthorities> userAuthorities;

	@Expose(serialize = true, deserialize = true)
	private List<String> roles;
	
	@Expose(serialize = true, deserialize = true)
	private Boolean accountNonExpired;

	@Expose(serialize = true, deserialize = true)
	private Boolean accountNonLocked;
	
	@Expose(serialize = true, deserialize = true)
	private Boolean credentialsNonExpired;
	
	@Expose(serialize = true, deserialize = true)
	private Boolean enabled;
	
	private Collection<Function> apiFunctions;
	
	private Collection<Function> menuFunctions;
	
	public AppUserDetails() {
		super();
	}


	public AppUserDetails(Long id, String username, String passwd, User user, Collection<UserAuthorities> userAuthorities, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.setUsername(username);
		this.password = passwd;
		this.authorities = authorities;
		this.roles = new ArrayList<>();
		this.user = user;
		if(null != authorities){
			for (GrantedAuthority authority : authorities) {
				this.roles.add(authority.getAuthority());
			}
		}
		this.userAuthorities = userAuthorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String toString() {
		return "UserDetails [username=" + getUsername() + ", authorities=" + getRoles().toString() + ", isAccountNonExpired()="
				+ isAccountNonExpired() + ", isAccountNonLocked()=" + isAccountNonLocked()
				+ ", isCredentialsNonExpired()=" + isCredentialsNonExpired() + ", isEnabled()=" + isEnabled() + "]";
	}
	
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public List<String> getRoles() {
		if(null == roles){
			roles = new ArrayList<>();
		}
		return roles;
	}


	/**
	 * @return the userAuthorities
	 */
	public Collection<UserAuthorities> getUserAuthorities() {
		return userAuthorities;
	}


	/**
	 * @param userAuthorities the userAuthorities to set
	 */
	public void setUserAuthorities(Collection<UserAuthorities> userAuthorities) {
		this.userAuthorities = userAuthorities;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}


	/**
	 * @return the apiFunctions
	 */
	public Collection<Function> getApiFunctions() {
		return apiFunctions;
	}


	/**
	 * @return the menuFunctions
	 */
	public Collection<Function> getMenuFunctions() {
		return menuFunctions;
	}


	/**
	 * @param apiFunctions the apiFunctions to set
	 */
	public void setApiFunctions(Collection<Function> apiFunctions) {
		this.apiFunctions = apiFunctions;
	}


	/**
	 * @param menuFunctions the menuFunctions to set
	 */
	public void setMenuFunctions(Collection<Function> menuFunctions) {
		this.menuFunctions = menuFunctions;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
}
