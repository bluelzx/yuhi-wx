package com.buss.project.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.buss.project.entity.ProjectCardInvitationEntity;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.facade.ProjectCardFacade;
import com.yuhi.wechar.facade.ProjectCardInvitationFacade;

/**
 * @Title: Controller
 * @Description: project_card_invitation
 * @author onlineGenerator
 * @date 2016-09-26 11:18:06
 * @version V1.0
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/projectCardInvitationController")
public class ProjectCardInvitationController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(ProjectCardInvitationController.class);

	@Autowired
	private ProjectCardInvitationFacade projectCardInvitationService;
	@Autowired
	private ProjectCardFacade projectCardService;
	@Autowired
	private SystemService systemService;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	//獲取已存在的耗材列表
	@RequestMapping(params = "getExitsInvitation", method = { RequestMethod.GET })
	@ResponseBody
	public PageList getExitsInvitation(
			@RequestParam(required = true) String pcid) {
		PageList json = null;
		try {
			CriteriaQuery cq = new CriteriaQuery(
					ProjectCardInvitationEntity.class);
			cq.add(Restrictions.eq("projectcardid", pcid));
			json=systemService.getPageList(cq, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	// 添加耗材
	@RequestMapping(params = "addProjectInvitation", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxJson addProjectInvitation(
			@RequestBody List<Map<String, String>> invitationEntities,
			@RequestParam(required=true)String pcid) {
		AjaxJson json = new AjaxJson();
		if (invitationEntities == null) {
			json.setSuccess(false);
			return json;
		}
		try {
			projectCardInvitationService.addProjectInvitation(pcid,invitationEntities);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
	}
	
	// 无需耗材
		@RequestMapping(params = "nocansubm", method = { RequestMethod.POST })
		@ResponseBody
		public AjaxJson nocansubm(@RequestParam(required=true)String pcid) {
			AjaxJson json = new AjaxJson();
			try {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("invitationid", "1");
				projectCardService.Update(map, pcid);
				json.setSuccess(true);
			} catch (Exception e) {
				json.setSuccess(false);
				e.printStackTrace();
			}
			return json;
		}
		
	
	/**
	 * project_card_invitation列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "projectCardInvitation")
	public ModelAndView projectCardInvitation(HttpServletRequest request,String id) {
		ModelAndView mav=new ModelAndView("weixin/idea/project/system/projectCardInvitationList");
		mav.addObject("projectCardid", id);
		return mav; 
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
	public void datagrid(ProjectCardInvitationEntity projectCardInvitation,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid,
			@RequestParam(required=true)Integer page,
			@RequestParam(required=true)Integer rows) {
		MapData md=new MapData(request,"field");
		// 自定义追加查询条件
		HttpSession session = ContextHolderUtils.getSession();
		Client c=ClientManager.getInstance().getClient(session.getId());
		String deptid=c.getUser().getTSDepart().getId();
		if(!deptid.equals("4028d881436d514601436d5214d70015"))md.put("part", deptid);
		try {
			TagUtil.wirteJson(response,projectCardInvitationService.datagrid(md, page, rows));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除project_card_invitation
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(String id,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "project_card_invitation删除成功";
		try {
			projectCardInvitationService.delete(id);
			systemService.addLog(message, Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "project_card_invitation删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除project_card_invitation
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "project_card_invitation删除成功";
		try {
			for (String id : ids.split(",")) {
				projectCardInvitationService.delete(id);
				systemService.addLog(message, Globals.Log_Type_DEL,
						Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "project_card_invitation删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加project_card_invitation
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
		message = "project_card_invitation添加成功";
		try {
			obj.put("typeid", 1);
			projectCardInvitationService.save(obj);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "project_card_invitation添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新project_card_invitation
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "project_card_invitation更新成功";
		MapData md=new MapData(request);
		String id=md.getString("id");
		md.setUnContainKey("id");
		try {
			projectCardInvitationService.Update(md.getMap(), id);
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "project_card_invitation更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * project_card_invitation新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(
			String id,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {
			JSONObject projectCardInvitation=null;
			try {
				projectCardInvitation = projectCardInvitationService.getObjByid(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("projectCardInvitationPage", projectCardInvitation);
		}
		return new ModelAndView(
				"weixin/idea/project/system/projectCardInvitation-add");
	}

	/**
	 * project_card_invitation编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(
			String id,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {
			JSONObject projectCardInvitation=null;
			try {
				projectCardInvitation = projectCardInvitationService.getObjByid(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("projectCardInvitationPage", projectCardInvitation);
		}
		return new ModelAndView(
				"weixin/idea/project/system/projectCardInvitation-update");
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView(
				"weixin/idea/project/system/projectCardInvitationUpload");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(ProjectCardInvitationEntity projectCardInvitation,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "project_card_invitation";
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
			CriteriaQuery cq = new CriteriaQuery(
					ProjectCardInvitationEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil
					.installHql(cq, projectCardInvitation,
							request.getParameterMap());

			List<ProjectCardInvitationEntity> projectCardInvitations = this.systemService
					.getListByCriteriaQuery(cq, false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle(
					"project_card_invitation列表", "导出人:"
							+ ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), ProjectCardInvitationEntity.class,
					projectCardInvitations);
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
	public void exportXlsByT(ProjectCardInvitationEntity projectCardInvitation,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "project_card_invitation";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle(
					"project_card_invitation列表", "导出人:"
							+ ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), ProjectCardInvitationEntity.class, null);
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
	public AjaxJson importExcel(HttpServletRequest request,
			HttpServletResponse response) {
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
				List<ProjectCardInvitationEntity> listProjectCardInvitationEntitys = (List<ProjectCardInvitationEntity>) ExcelImportUtil
						.importExcelByIs(file.getInputStream(),
								ProjectCardInvitationEntity.class, params);
				for (ProjectCardInvitationEntity projectCardInvitation : listProjectCardInvitationEntitys) {
					systemService.save(projectCardInvitation);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			} finally {
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
