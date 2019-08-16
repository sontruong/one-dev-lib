/**
 * 
 */
package com.ones.user.function;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author son.truong Dec 16, 2017 4:42:44 PM
 */
@Repository
public interface FunctionRepository
		extends PagingAndSortingRepository<Function, Long>, JpaSpecificationExecutor<Function> {

	Function findTop1ByName(String name);

	@Query("from Function where id in (?1)")
	Collection<Function> findListFunction(Collection<Long> ids);

	@Modifying
	@Query("delete Function where id in (?1)")
	Integer deleteInList(Collection<Long> ids);

	Function findTop1ByApiAndMethod(String api, String method);

	@Query("from Function a where ?1 MEMBER OF a.userIds or a.allowAll = true")
	Collection<Function> findByUserId(Long userId);

	@Query("from Function a where ?1 MEMBER OF a.roles")
	Collection<Function> findByRole(String role);

	@Query("from Function a where a.parent is null and a.method =?1")
	Collection<Function> findRootByMethod(String method);
}
