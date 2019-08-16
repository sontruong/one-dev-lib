/**
 * 
 */
package com.ones.workflow;

import java.io.File;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ones.base.BaseService;
import com.ones.security.AppUserDetails;
import com.ones.utils.AppUtils;
import com.ones.utils.ExcelUtils;
import com.ones.utils.MyReflectField;

/**
 * @author son.truong Jun 22, 2018, 2:11:58 PM
 */
@Service
public class WHistoryServiceImpl extends BaseService implements WHistoryService {

	@Autowired
	WHistoryRepository wHistoryRepository;

	/** constructor of WHistoryServiceImpl */
	public WHistoryServiceImpl() {
		super();
	}

	public Page<WHistory> search(WHistoryFilterDTO wHistory) {
		Sort sort = AppUtils.createSort(wHistory.getSortDTOs());
		PageRequest pageRequest = AppUtils.createPageRequest(wHistory.getPage(), wHistory.getLimit(), sort);
		Page<WHistory> pageWHistory = wHistoryRepository.findAll(WHistory.createSpecification(wHistory), pageRequest);
		return pageWHistory;
	}

	public Page<WHistory> searchOr(WHistoryFilterDTO wHistory) {
		Sort sort = AppUtils.createSort(wHistory.getSortDTOs());
		PageRequest pageRequest = AppUtils.createPageRequest(wHistory.getPage(), wHistory.getLimit(), sort);
		Page<WHistory> pageWHistory = wHistoryRepository.findAll(WHistory.createSpecificationOr(wHistory), pageRequest);
		return pageWHistory;
	}

	public WHistory findById(Long id) {
		if (null == id) {
			return null;
		}
		WHistory wHistory = wHistoryRepository.findOne(id);
		return wHistory;
	}

	public WHistory edit(WHistory wHistory) {
		return wHistoryRepository.save(wHistory);
	}

	public void exportData(WHistoryFilterDTO dto, HttpServletResponse response) {
		File file = null;
		dto.setLimit(555);
		dto.setPage(0);
		try {
			MyReflectField[] fields = getFields(new WHistoryDTO(), dto.getOptions(), false);
			Page<WHistory> pageWHistory = search(dto);
			Collection<WHistory> collection = pageWHistory.getContent();
			int page = 1;
			while (page < pageWHistory.getTotalPages()) {
				dto.setPage(page);
				pageWHistory = search(dto);
				collection.addAll(pageWHistory.getContent());
				page++;
			}
			file = ExcelUtils.createList(fields, "wHistorys", collection, true);
			cdnService.downloadFile(response, file,
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != file) {
				file.deleteOnExit();
			}
		}
	}

	public void deleteById(Long id) {
		wHistoryRepository.delete(id);
	}

	public Integer deleteByIds(Collection<Long> ids) {
		return wHistoryRepository.deleteInList(ids);
	}

	@Override
	public WHistory addHistory(AppUserDetails appUserDetails, Long id, String entityType, String entityStatus, String comment) {
		WHistory history = new WHistory();
		history.setActedBy(appUserDetails.getId());
		history.setActedOn(new Date());
		history.setComment(comment);
		history.setEntityId(id);
		history.setEntityStatus(entityStatus);
		history.setEntityType(entityType);
		return history;
	}

}
