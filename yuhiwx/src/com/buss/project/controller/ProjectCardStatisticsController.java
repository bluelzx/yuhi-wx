package com.buss.project.controller;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.Client;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.buss.project.entity.ProjectCardEntity;
import com.buss.project.entity.ProjectCardStatisticsEntity;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.entity.resultdata;
import com.yuhi.wechar.facade.ProjectCardFacade;
import com.yuhi.wechar.facade.ProjectCardStatisticsFacade;


/**   
 * @Title: Controller
 * @Description: project_card_statistics
 * @author onlineGenerator
 * @date 2016-11-30 09:25:39
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/projectCardStatisticsController")
public class ProjectCardStatisticsController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProjectCardStatisticsController.class);

	@Autowired
	private ProjectCardStatisticsFacade projectCardStatisticsService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private ProjectCardFacade projectCardService;
	
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * project_card_statistics列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "projectCardStatistics")
	public ModelAndView projectCardStatistics(HttpServletRequest request) {
		return new ModelAndView("weixin/idea/project/system/projectCardStatistics-List");
	}
	
	
	
	
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param openid 
	 * @param user
	 * @return 
	 * @throws Exception 
	 */

	@RequestMapping(params = "statisticsData")
	@ResponseBody
	public resultdata GetstatisticsData(ProjectCardEntity projectCard,
			@RequestParam(required=true)Date times,HttpServletRequest request) throws Exception {
		HttpSession session = ContextHolderUtils.getSession();
		Client c=ClientManager.getInstance().getClient(session.getId());
		String deptid=c.getUser().getTSDepart().getId();
		resultdata data=projectCardService.getReportData(times,deptid);
		return data;
	}
	
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ProjectCardStatisticsEntity projectCardStatistics,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, Integer rows, Integer page) {
		MapData md=new MapData(request,"field");
		// 自定义追加查询条件
		HttpSession session = ContextHolderUtils.getSession();
		Client c=ClientManager.getInstance().getClient(session.getId());
		String deptid=c.getUser().getTSDepart().getId();
		if(!deptid.equals("4028d881436d514601436d5214d70015"))md.put("part", deptid);
		try {
			TagUtil.wirteJson(response,projectCardStatisticsService.datagrid(md, page, rows));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除project_card_statistics
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(String id, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "project_card_statistics删除成功";
		try{
			projectCardStatisticsService.delete(id);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "project_card_statistics删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除project_card_statistics
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "project_card_statistics删除成功";
		try{
			for(String id:ids.split(",")){
				projectCardStatisticsService.delete(id);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "project_card_statistics删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加project_card_statistics
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HttpServletRequest request) {
		MapData md=new MapData(request);
		JSONObject obj=new JSONObject(md);
		AjaxJson j = new AjaxJson();
		message = "project_card_statistics添加成功";
		try{
			projectCardStatisticsService.save(obj);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "project_card_statistics添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新project_card_statistics
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ProjectCardStatisticsEntity projectCardStatistics, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		MapData md=new MapData(request);
		String id=md.getString("id");
		md.setUnContainKey("id");
		message = "project_card_statistics更新成功";
		try {
			projectCardStatisticsService.Update(md.getMap(), id);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "project_card_statistics更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * project_card_statistics新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(String id, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {
			JSONObject projectCardStatistics=null;
			try {
				projectCardStatistics = projectCardStatisticsService.getObjByid(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("projectCardStatisticsPage", projectCardStatistics);
		}
		return new ModelAndView("com/buss/project/projectCardStatistics-add");
	}
	/**
	 * project_card_statistics编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(String id,HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {
			JSONObject projectCardStatistics=null;
			try {
				projectCardStatistics = projectCardStatisticsService.getObjByid(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("projectCardStatisticsPage", projectCardStatistics);
		}
		return new ModelAndView("com/buss/project/projectCardStatistics-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/buss/project/projectCardStatisticsUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(ProjectCardStatisticsEntity projectCardStatistics,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "project_card_statistics";
			// 根据浏览器进行转码，使其支持中文文件名
			if (BrowserUtils.isIE(request)) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(codedFileName,
										"UTF-8") + ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"),
						"ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}
			// 产生工作簿对象
			HSSFWorkbook workbook = null;
			CriteriaQuery cq = new CriteriaQuery(ProjectCardStatisticsEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, projectCardStatistics, request.getParameterMap());
			
			List<ProjectCardStatisticsEntity> projectCardStatisticss = this.systemService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("project_card_statistics列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), ProjectCardStatisticsEntity.class, projectCardStatisticss);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {

			}
		}
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public void exportXlsByT(ProjectCardStatisticsEntity projectCardStatistics,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "project_card_statistics";
			// 根据浏览器进行转码，使其支持中文文件名
			if (BrowserUtils.isIE(request)) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(codedFileName,
										"UTF-8") + ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"),
						"ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}
			// 产生工作簿对象
			HSSFWorkbook workbook = null;
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("project_card_statistics列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), ProjectCardStatisticsEntity.class, null);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {

			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setSecondTitleRows(1);
			params.setNeedSave(true);
			try {
				List<ProjectCardStatisticsEntity> listProjectCardStatisticsEntitys = 
					(List<ProjectCardStatisticsEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),ProjectCardStatisticsEntity.class,params);
				for (ProjectCardStatisticsEntity projectCardStatistics : listProjectCardStatisticsEntitys) {
					systemService.save(projectCardStatistics);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
}
