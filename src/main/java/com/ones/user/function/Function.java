/**
 * 
 */
package com.ones.user.function;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.jpa.domain.Specification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ones.dto.FunctionFilterDTO;
import com.ones.utils.ApplicationMessage;

import io.swagger.annotations.ApiModel;

/**
 * @author son.truong Dec 16, 2017 4:42:44 PM
 */
@ApiModel
@Entity
@Table(name = "one_function")
public class Function implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@NotNull(message = ApplicationMessage.FUNCTION_NAME_NULL)
	@Column(nullable = false)
	private String name;

	@NotNull(message = ApplicationMessage.FUNCTION_AUTORITYKEY_NULL)
	@Column(name = "function_key", nullable = false)
	private String functionKey;

	private String api;

	@Column(name = "api_method", nullable = true)
	private String method;
	
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection
	@CollectionTable(name = "function_role")
	@Column(name = "role")
	private List<String> roles;

	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection
	@CollectionTable(name = "function_user")
	@Column(name = "user_id")
	private List<Long> userIds;

	@Column(name = "allow_all", nullable = true)
	private Boolean allowAll;

	@Column(name = "on_org", nullable = true)
	private Boolean onOrg;

	@Column(name = "on_obj", nullable = true)
	private Boolean onObj;

	private String icon;
	
	private Long parent;
	
	@Transient
	private Collection<Function> childrenFunction;
	
	/** constructor of Function */
	public Function() {
		super();
	}

	public Function(Long id) {
		this.id = id;
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

	public static Specification<Function> createSpecification(final FunctionFilterDTO authority) {
		return new Specification<Function>() {
			@Override
			public Predicate toPredicate(Root<Function> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				/* query for field Name */
				Predicate predicateName;
				if (null != authority.getName() && !authority.getName().isEmpty()) {
					Expression<String> path = root.get("name");
					predicateName = cb.like(cb.lower(path), "%" + authority.getName().toLowerCase() + "%");
				} else {
					predicateName = cb.conjunction();
				}

				/* query for field AutorityKey */
				Predicate predicateAutorityKey;
				if (null != authority.getFunctionKey() && !authority.getFunctionKey().isEmpty()) {
					Expression<String> path = root.get("functionKey");
					predicateAutorityKey = cb.like(cb.lower(path),
							"%" + authority.getFunctionKey().toLowerCase() + "%");
				} else {
					predicateAutorityKey = cb.conjunction();
				}

				/* query for field Api */
				Predicate predicateApi;
				if (null != authority.getApi() && !authority.getApi().isEmpty()) {
					Expression<String> path = root.get("api");
					predicateApi = cb.like(cb.lower(path), "%" + authority.getApi().toLowerCase() + "%");
				} else {
					predicateApi = cb.conjunction();
				}

				/* query for field Method */
				Predicate predicateMethod;
				if (null != authority.getMethod() && !authority.getMethod().isEmpty()) {
					Expression<String> path = root.get("method");
					predicateMethod = cb.like(cb.lower(path), "%" + authority.getMethod().toLowerCase() + "%");
				} else {
					predicateMethod = cb.conjunction();
				}

				/* query for field AllowAll */
				Predicate predicateAllowAll;
				if (null != authority.getAllowAll()) {
					Expression<Boolean> path = root.get("allowAll");
					predicateAllowAll = cb.equal(path, authority.getAllowAll());
				} else {
					predicateAllowAll = cb.conjunction();
				}

				/* query for field UserId */
				Predicate predicateUser;
				if (null != authority.getUserId()) {
					Expression<Collection<Long>> path = root.get("userIds");
					predicateUser = cb.isMember(authority.getUserId(), path);
				} else {
					predicateUser = cb.conjunction();
				}

				/* query for field role */
				Predicate predicateRole;
				if (null != authority.getRoles() && 0 < authority.getRoles().size()) {
					Expression<Collection<String>> path = root.get("roles");
					Predicate[] predicates = new Predicate[authority.getRoles().size()];
					int count = 0;
					for (String role : authority.getRoles()) {
						predicates[count] = cb.isMember(role, path);
						count++;
					}
					predicateRole = cb.or(predicates);
				} else {
					predicateRole = cb.conjunction();
				}

				/* query for field OnOrg */
				Predicate predicateOnOrg;
				if (null != authority.getOnOrg()) {
					Expression<Boolean> path = root.get("onOrg");
					predicateOnOrg = cb.equal(path, authority.getOnOrg());
				} else {
					predicateOnOrg = cb.conjunction();
				}

				/* query for field OnObj */
				Predicate predicateOnObj;
				if (null != authority.getOnObj()) {
					Expression<Boolean> path = root.get("onObj");
					predicateOnObj = cb.equal(path, authority.getOnObj());
				} else {
					predicateOnObj = cb.conjunction();
				}

				return cb.and(predicateName, predicateAutorityKey, predicateApi, predicateMethod, predicateAllowAll,
						predicateOnOrg, predicateOnObj, predicateUser, predicateRole);
			}
		};
	}

	public static Specification<Function> createFunctionSpecification(final FunctionFilterDTO authority) {
		return new Specification<Function>() {
			@Override
			public Predicate toPredicate(Root<Function> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				/* query for field AllowAll */
				Expression<Boolean> pathAllowAll = root.get("allowAll");
				Predicate predicateAllowAll = cb.equal(pathAllowAll, true);


				/* query for field UserId */
				Predicate predicateUser;
				if (null != authority.getUserId()) {
					Expression<Collection<Long>> path = root.get("userIds");
					predicateUser = cb.isMember(authority.getUserId(), path);
				} else {
					predicateUser = cb.conjunction();
				}

				/* query for field role */
				Predicate predicateRole;
				if (null != authority.getRoles() && 0 < authority.getRoles().size()) {
					Expression<Collection<String>> path = root.get("roles");
					Predicate[] predicates = new Predicate[authority.getRoles().size()];
					int count = 0;
					for (String role : authority.getRoles()) {
						predicates[count] = cb.isMember(role, path);
						count++;
					}
					predicateRole = cb.or(predicates);
				} else {
					predicateRole = cb.conjunction();
				}

				return cb.or(predicateAllowAll, predicateUser, predicateRole);
			}
		};
	}

	public String getFunctionKey() {
		return functionKey;
	}

	public void setFunctionKey(String functionKey) {
		this.functionKey = functionKey;
	}

	public List<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
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

	public Collection<Function> getChildrenFunction() {
		return childrenFunction;
	}

	public void setChildrenFunction(Collection<Function> childrenFunction) {
		this.childrenFunction = childrenFunction;
	}
}
