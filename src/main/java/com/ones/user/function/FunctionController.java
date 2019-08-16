/**
 * 
 */
package com.ones.user.function;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ones.base.BaseController;
import com.ones.dto.FunctionFilterDTO;
import com.ones.utils.AppConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author son.truong Dec 16, 2017 4:42:44 PM
 */
@ApiIgnore
@Api(value = AppConfig.API_ONE_MANAGEMENT, tags = "One management APIs")
@Controller
@RequestMapping(value = AppConfig.API_ONE_MANAGEMENT)
public class FunctionController extends BaseController {

	@Autowired
	FunctionService functionService;

	/** constructor of AuthorityController */
	public FunctionController() {
		super();
	}

	@ApiOperation(value = "Get detail of function", notes = "Get detail of function", hidden = true)
	@RequestMapping(value = "function", method = RequestMethod.GET, produces = AppConfig.APPLICATION_CONSUMES)
	public @ResponseBody ResponseEntity<Function> get(@RequestHeader(required = false) String authorization,
			@ApiParam @RequestParam Long functionId) {
		Function authority = functionService.findById(functionId);
		return new ResponseEntity<Function>(authority, HttpStatus.OK);
	}

	@ApiOperation(value = "Search/Filter list functions", notes = "Search/Filter list function", hidden = true)
	@RequestMapping(value = "functions", method = RequestMethod.POST, produces = AppConfig.APPLICATION_CONSUMES)
	public @ResponseBody ResponseEntity<Page<Function>> gets(
			@ApiParam @RequestHeader(required = false) String authorization, @RequestBody FunctionFilterDTO dto) {

		Page<Function> pageAuthority = functionService.search(dto);

		return new ResponseEntity<Page<Function>>(pageAuthority, HttpStatus.OK);
	}
	
	@ApiOperation(value = "get list root functions", notes = "get list root functions", hidden = true)
	@RequestMapping(value = "functions/root", method = RequestMethod.POST, produces = AppConfig.APPLICATION_CONSUMES)
	public @ResponseBody ResponseEntity<Collection<Function>> findRoots(
			@ApiParam @RequestHeader(required = false) String authorization, @RequestBody FunctionFilterDTO dto) {

		Collection<Function> pageAuthority = functionService.findRoot(authorization);

		return new ResponseEntity<Collection<Function>>(pageAuthority, HttpStatus.OK);
	}
}
