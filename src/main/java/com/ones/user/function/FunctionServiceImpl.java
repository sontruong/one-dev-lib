/**
 * 
 */
package com.ones.user.function;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ones.base.BaseService;
import com.ones.dto.FunctionDTO;
import com.ones.dto.FunctionFilterDTO;
import com.ones.service.CDNService;
import com.ones.utils.AppUtils;
import com.ones.utils.ExcelUtils;
import com.ones.utils.MyReflectField;

/**
 * @author son.truong Dec 16, 2017 4:42:44 PM
 */
@Service
public class FunctionServiceImpl extends BaseService implements FunctionService {

	@Autowired
	CDNService cdnService;
	@Autowired
	FunctionRepository functionRepository;

	/** constructor of AuthorityServiceImpl */
	public FunctionServiceImpl() {
		super();
	}

	public Page<Function> search(FunctionFilterDTO authority) {
		Sort sort = AppUtils.createSort(authority.getSortDTOs());
		PageRequest pageRequest = AppUtils.createPageRequest(authority.getPage(), authority.getLimit(), sort);
		Page<Function> pageAuthority = functionRepository.findAll(Function.createSpecification(authority), pageRequest);
		return pageAuthority;
	}

	public Function findById(Long id) {
		if (null == id) {
			return null;
		}
		Function authority = functionRepository.findOne(id);
		return authority;
	}

	private void setData(Function authority) {
		if (null == authority) {
			authority = new Function();
		}
	}

	private void setData(Function authority, FunctionDTO authorityDTO) {
		/* mapping properties */
		authority.setName(authorityDTO.getName());
		authority.setFunctionKey(authorityDTO.getFunctionKey());
		authority.setApi(authorityDTO.getApi());
		authority.setMethod(authorityDTO.getMethod());
		authority.setAllowAll(authorityDTO.getAllowAll());
		authority.setOnOrg(authorityDTO.getOnOrg());
		authority.setOnObj(authorityDTO.getOnObj());
		authority.setParent(authorityDTO.getParent());
		authority.setIcon(authorityDTO.getIcon());
	}

	public Function edit(Function authority) {
		return functionRepository.save(authority);
	}

	public Function edit(FunctionDTO authorityDTO, String authorization) {
		/* check create new or update */
		Function authority = findById(authorityDTO.getId());
		setData(authority);
		/* mapping properties and verify foreign key */
		setData(authority, authorityDTO);

		authority = functionRepository.save(authority);
		return authority;
	}

	public Function findTop1ByName(String name) {
		return functionRepository.findTop1ByName(name);
	}

	public void exportTemplate(FunctionFilterDTO dto, HttpServletResponse response) {
		File file = null;
		try {
			MyReflectField[] fields = getFields(new FunctionDTO(), dto.getOptions(), false);
			file = ExcelUtils.createTemplate(fields, "authoritys");
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

	public void exportData(FunctionFilterDTO dto, HttpServletResponse response) {
		File file = null;
		dto.setLimit(555);
		dto.setPage(0);
		try {
			MyReflectField[] fields = getFields(new FunctionDTO(), dto.getOptions(), false);
			Page<Function> pageAuthority = search(dto);
			Collection<Function> collection = pageAuthority.getContent();
			int page = 1;
			while (page < pageAuthority.getTotalPages()) {
				dto.setPage(page);
				pageAuthority = search(dto);
				collection.addAll(pageAuthority.getContent());
				page++;
			}
			file = ExcelUtils.createList(fields, "authoritys", collection, true);
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

	public Collection<Object> importData(String authorization, ArrayList<Object> objects) {
		if (CollectionUtils.isEmpty(objects)) {
			return new ArrayList<>();
		}
		Collection<Object> errors = new ArrayList<>();
		for (Object object : objects) {
			Function authority = null;
			FunctionDTO authorityDTO = (FunctionDTO) object;
			if (null == authority && null != authorityDTO.getName()) {
				authority = functionRepository.findTop1ByName(authorityDTO.getName());
			}
			setData(authority);
			setData(authority, authorityDTO);
			functionRepository.save(authority);
		}
		return errors;
	}

	public void deleteById(Long id) {
		functionRepository.delete(id);
	}

	public Integer deleteByIds(Collection<Long> ids) {
		return functionRepository.deleteInList(ids);
	}

	@Override
	public Collection<Function> findRoot(String authorization) {
		return functionRepository.findRootByMethod("menu");
	}

}
