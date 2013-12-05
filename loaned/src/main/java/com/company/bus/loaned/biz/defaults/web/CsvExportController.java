package com.company.bus.loaned.biz.defaults.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.bus.loaned.common.support.CSVGenerator;
import com.company.bus.loaned.common.support.JsonUtils;
import com.company.bus.loaned.common.web.controller.BaseController;


/**
 * @Description csv导出
 * @author 
 * @date 2013-3-14
 * @version 1.0
 */
@Controller
@RequestMapping("/export")
public class CsvExportController extends BaseController {
	/**
	 * @Description csv当页导出(下载)
	 * @param charset
	 * @param csvdata
	 * @param response
	 * @throws Exception
	 * @author 
	 */
	@RequestMapping("csvExport")
	public void csvExport(
			@RequestParam("charset") String charset,
			@RequestParam("csvdata") String csvdata,
			HttpServletResponse response) throws Exception{
		log.debug("Get data: {}", new Object[]{csvdata});
		List<Object> datalist = JsonUtils.jsonString2Object(csvdata, ArrayList.class);	
		//输出csv到response
		CSVGenerator.generateCsvFile(response, charset, CSVGenerator.list2StringArray(datalist), ',');		
	}
}
