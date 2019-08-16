/**
 * 
 */
package com.ones.user.caching;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Son Truong
 * 
 */
public interface UserCachingRepository extends PagingAndSortingRepository<UserCaching, Long> {
	
	@Query("from UserCaching where id = ?1")
	public UserCaching findByUserId(Long userId);
	
	@Query("from UserCaching where email = ?1")
	public UserCaching findByEmail(String email);
	
	@Query("from UserCaching where token = ?1")
	public UserCaching findByToken(String token);
}
