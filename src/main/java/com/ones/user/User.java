package com.ones.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author son.truong Dec 8, 2016 10:52:07 PM
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String firstName;

	private String lastName;

	private String username;

	private String address;

	private Long provinceId;

	private String tel1;

	private String nickName;

	private String password;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

	private Long updatedBy;
	
	String faceAccount;
	String zaloAccount;
	String linkedAccount;
	String githubAccount;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;

	@Temporal(TemporalType.TIMESTAMP)
	private Date currentLogin;

	private AccountStatus status;

	public User() {
	}

	public User(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @return the status
	 */
	public AccountStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	/**
	 * @return the currentLogin
	 */
	public Date getCurrentLogin() {
		return currentLogin;
	}

	/**
	 * @param currentLogin
	 *            the currentLogin to set
	 */
	public void setCurrentLogin(Date currentLogin) {
		this.currentLogin = currentLogin;
	}

	public String getFaceAccount() {
		return faceAccount;
	}

	public void setFaceAccount(String faceAccount) {
		this.faceAccount = faceAccount;
	}

	public String getZaloAccount() {
		return zaloAccount;
	}

	public void setZaloAccount(String zaloAccount) {
		this.zaloAccount = zaloAccount;
	}

	public String getLinkedAccount() {
		return linkedAccount;
	}

	public void setLinkedAccount(String linkedAccount) {
		this.linkedAccount = linkedAccount;
	}

	public String getGithubAccount() {
		return githubAccount;
	}

	public void setGithubAccount(String githubAccount) {
		this.githubAccount = githubAccount;
	}
}
