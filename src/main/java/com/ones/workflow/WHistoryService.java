/**
 * 
 */
package com.ones.workflow;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import com.ones.security.AppUserDetails;

/**
 * @author son.truong Jun 22, 2018, 2:11:58 PM
 */
public interface WHistoryService {

	public Page<WHistory> search(WHistoryFilterDTO wHistory);

	public Page<WHistory> searchOr(WHistoryFilterDTO wHistory);

	public WHistory findById(Long id);

	public WHistory edit(WHistory wHistory);

	public void exportData(WHistoryFilterDTO dto, HttpServletResponse response);

	public void deleteById(Long id);

	public Integer deleteByIds(Collection<Long> ids);

	public WHistory addHistory(AppUserDetails appUserDetails, Long id, String entityType, String entityStatus, String comment);
}
