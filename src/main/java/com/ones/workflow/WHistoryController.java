/**
 * 
 */
package com.ones.workflow;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

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
import com.ones.dto.SuccessMessage;
import com.ones.utils.AppConfig;
import com.ones.utils.ApplicationMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author son.truong
 * Jun 22, 2018, 2:11:58 PM
 */
@ApiIgnore
@Api(value = "", tags = "Workflow", hidden = true)
@Controller
@RequestMapping(value = AppConfig.API_MAPPING)
public class WHistoryController extends BaseController {

   @Autowired
   WHistoryService wHistoryService;

   /** constructor of WHistoryController*/
   public WHistoryController() {
      super();
   }

   @ApiOperation(value = "Delete history", notes = "Delete history", hidden = true)
   @RequestMapping(value = "workflow/history", method = RequestMethod.DELETE, produces = AppConfig.APPLICATION_CONSUMES)
   public @ResponseBody ResponseEntity<SuccessMessage> delete(@RequestHeader(required = false) String authorization,
         @RequestParam Long wHistoryId) {
      wHistoryService.deleteById(wHistoryId);
      return new ResponseEntity<SuccessMessage>(new SuccessMessage(ApplicationMessage.SUCCESS), HttpStatus.OK);
   }

   @ApiOperation(value = "Get detail of history", notes = "Get detail of history", hidden = true)
   @RequestMapping(value = "workflow/history", method = RequestMethod.GET, produces = AppConfig.APPLICATION_CONSUMES)
   public @ResponseBody ResponseEntity<WHistory> get(@RequestHeader(required = false) String authorization,
         @ApiParam @RequestParam Long wHistoryId) {
      WHistory wHistory = wHistoryService.findById(wHistoryId);
      return new ResponseEntity<WHistory>(wHistory, HttpStatus.OK);
   }

   @ApiOperation(value = "Search/Filter list histories", notes = "Search/Filter list histories", hidden = true)
   @RequestMapping(value = "workflow/histories", method = RequestMethod.POST, produces = AppConfig.APPLICATION_CONSUMES)
   public @ResponseBody ResponseEntity<Page<WHistory>> gets(
         @ApiParam @RequestHeader(required = false) String authorization, @RequestBody WHistoryFilterDTO dto) {

      Page<WHistory> pageWHistory = wHistoryService.search(dto);

      return new ResponseEntity<Page<WHistory>>(pageWHistory, HttpStatus.OK);
   }

   @ApiOperation(value = "Search/Filter list histories", notes = "Search/Filter list histories", hidden = true)
   @RequestMapping(value = "workflow/histories/or", method = RequestMethod.POST, produces = AppConfig.APPLICATION_CONSUMES)
   public @ResponseBody ResponseEntity<Page<WHistory>> getsOr(
         @ApiParam @RequestHeader(required = false) String authorization, @RequestBody WHistoryFilterDTO dto) {

      Page<WHistory> pageWHistory = wHistoryService.searchOr(dto);

      return new ResponseEntity<Page<WHistory>>(pageWHistory, HttpStatus.OK);
   }

   @ApiOperation(value = "Delete list WHistory", notes = "Delete list WHistory", hidden = true)
   @RequestMapping(value = "workflow/histories", method = RequestMethod.DELETE, produces = AppConfig.APPLICATION_CONSUMES)
   public @ResponseBody ResponseEntity<SuccessMessage> deletes(
         @ApiParam @RequestHeader(required = false) String authorization, @RequestBody Collection<Long> ids) {

      wHistoryService.deleteByIds(ids);

      return new ResponseEntity<SuccessMessage>(new SuccessMessage(ApplicationMessage.SUCCESS), HttpStatus.OK);
   }

   @ApiOperation(value = "Export list histories", notes = "Export list histories", hidden = true)
   @RequestMapping(value = "wHistorys/export", method = RequestMethod.POST)
   public void export(@ApiParam @RequestHeader(required = false) String authorization,
         @RequestBody WHistoryFilterDTO dto, HttpServletResponse response) throws IOException {
      wHistoryService.exportData(dto, response);
   }

}
