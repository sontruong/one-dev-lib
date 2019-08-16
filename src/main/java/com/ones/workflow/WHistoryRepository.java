/**
 * 
 */
package com.ones.workflow;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author son.truong
 * Jun 22, 2018, 2:11:58 PM
 */
@Repository
public interface WHistoryRepository extends PagingAndSortingRepository<WHistory, Long>, JpaSpecificationExecutor<WHistory> {

    @Modifying
    @Query("delete WHistory where id in (?1)")
    Integer deleteInList(Collection<Long> ids);

}
