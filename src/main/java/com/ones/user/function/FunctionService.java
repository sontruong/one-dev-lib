/**
 * 
 */
package com.ones.user.function;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import com.ones.dto.FunctionDTO;
import com.ones.dto.FunctionFilterDTO;

/**
 * @author son.truong
 * Dec 16, 2017 4:42:44 PM
 */
public interface FunctionService {
	
    public Page<Function> search(FunctionFilterDTO authority);
    public Function findById(Long id);
    public Function findTop1ByName( String name);
    public Function edit(FunctionDTO authorityDTO, String authorization);
    public Function edit(Function authority);
    public Collection<Object> importData(String authorization, ArrayList<Object> objects);
    public void exportTemplate(FunctionFilterDTO dto, HttpServletResponse response);
    public void exportData(FunctionFilterDTO dto, HttpServletResponse response);
    public void deleteById(Long id);
    public Integer deleteByIds(Collection<Long> ids);
    public Collection<Function> findRoot(String authorization);
}
