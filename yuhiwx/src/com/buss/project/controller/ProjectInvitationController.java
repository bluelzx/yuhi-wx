package com.buss.project.controller;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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
import org.jeecgframework.tag.vo.datatable.SortDirection;
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
import com.buss.project.entity.ProjectInvitationEntity;
import com.yuhi.wechar.entity.InvitationtreeNode;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.facade.ProjectInvitationFacade;



/**   
 * @Title: Controller
 * @Description: project_invitation
 * @author onlineGenerator
 * @date 2016-10-10 16:49:27
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/projectInvitationController")
public class ProjectInvitationController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProjectInvitationController.class);

	@Autowired
	private ProjectInvitationFacade projectInvitationService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	/**
	 * 耗材树形表
	 * @param request
	 * @param response
	 * @param treegrid
	 * @return
	 */
	@RequestMapping(params = "departgrid")
	@ResponseBody
	public  List<InvitationtreeNode>  departgrid(Integer parentid,HttpServletRequest request) {
		request.setAttribute("aa", "aaa");
		String type=null;
		List<InvitationtreeNode>  tree=projectInvitationService.getpartNodeByType(parentid," ");
		return tree;
	}
	
	
	@RequestMapping(params = "addInvitationForProjectCard")
	public ModelAndView addInvitationForProjectCard(HttpServletRequest request,
			@RequestParam(required=true)String id) {
		ModelAndView mv=new ModelAndView("weixin/idea/project/template/addInvitationForProjectCard");
		mv.addObject("id", id);
		return mv;
	}
	/**
	 * 获取子耗材
	 * @param projectCreate
	 * @return
	 */
	@RequestMapping(params = "getInvitationbyParentid")
	@ResponseBody
	public PageList getInvitationbyParentid(ProjectInvitationEntity projectCreate){
		CriteriaQuery cq=new CriteriaQuery(ProjectInvitationEntity.class);
		if(StringUtils.isNotEmpty(projectCreate.getParentsid()+""))cq.add(Restrictions.eq("parentsid",projectCreate.getParentsid()));
		cq.add(Restrictions.eq("type",1));
		cq.addOrder("useCount",SortDirection.desc);
		PageList list= systemService.getPageList(cq, false);
		return list;
	}
	/**
	 * project_invitation列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "projectInvitation")
	public ModelAndView projectInvitation(HttpServletRequest request) {
		return new ModelAndView("weixin/idea/project/system/projectInvitationList");
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
	public void datagrid(ProjectInvitationEntity projectInvitation,HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid,
			@RequestParam(required=true)Integer page,
			@RequestParam(required=true)Integer rows) {
		MapData md=new MapData(request,"field");
		// 自定义追加查询条件
		HttpSession session = ContextHolderUtils.getSession();
		Client c=ClientManager.getInstance().getClient(session.getId());
		String deptid=c.getUser().getTSDepart().getId();
		if(!deptid.equals("4028d881436d514601436d5214d70015"))md.put("part", deptid);
		try {
			TagUtil.wirteJson(response,projectInvitationService.datagrid(md, page, rows));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除project_invitation
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(Integer id, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if(projectInvitationService.isExitsChild(id)){
			j.setMsg("该项目下还有子耗材！");
			return j;
		}
		message = "";
		try{
			projectInvitationService.delete(id.toString());
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除project_invitation
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "project_invitation删除成功";
		try{
			for(String id:ids.split(",")){
				projectInvitationService.delete(id);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "project_invitation删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加project_invitation
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
		message = "project_invitation添加成功";
		try{
			obj.put("type", 1);
			projectInvitationService.save(obj);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "project_invitation添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新project_invitation
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		MapData md=new MapData(request);
		String id=md.getString("id");
		md.setUnContainKey("id");
		message = "耗材更新成功";
		if(StringUtil.isNotEmpty(id)){
			try {
				projectInvitationService.Update(md.getMap(), id);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "耗材更新失败";
				throw new BusinessException(e.getMessage());
			}
		}else{
			try {
				JSONObject projectInvitation=new JSONObject(md);
				projectInvitationService.save(projectInvitation);
				j.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				message = "耗材更新失败";
				throw new BusinessException(e.getMessage());
			}
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * project_invitation新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(String id, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {
			JSONObject projectInvitation = null;
			try {
				projectInvitation = projectInvitationService.getObjByid(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("projectInvitationPage", projectInvitation);
		}
		return new ModelAndView("com/buss/project/projectInvitation-add");
	}
	/**
	 * project_invitation编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(String id,String parentsid,HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {//编辑
			JSONObject projectInvitation = null;
			try {
				projectInvitation = projectInvitationService.getObjByid(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("projectInvitationPage", projectInvitation);
		}
		if(StringUtil.isNotEmpty(parentsid)){
			MapData md=new MapData(req);
			JSONObject projectInvitation =new JSONObject(md);
			req.setAttribute("projectInvitationPage", projectInvitation);
		}
		return new ModelAndView("weixin/idea/project/system/projectInvitation-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/buss/project/projectInvitationUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(ProjectInvitationEntity projectInvitation,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "project_invitation";
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
			CriteriaQuery cq = new CriteriaQuery(ProjectInvitationEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, projectInvitation, request.getParameterMap());
			
			List<ProjectInvitationEntity> projectInvitations = this.systemService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("project_invitation列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), ProjectInvitationEntity.class, projectInvitations);
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
	public void exportXlsByT(ProjectInvitationEntity projectInvitation,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "project_invitation";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("project_invitation列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), ProjectInvitationEntity.class, null);
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
				List<ProjectInvitationEntity> listProjectInvitationEntitys = 
					(List<ProjectInvitationEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),ProjectInvitationEntity.class,params);
				for (ProjectInvitationEntity projectInvitation : listProjectInvitationEntitys) {
					systemService.save(projectInvitation);
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
