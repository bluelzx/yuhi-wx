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
import org.jeecgframework.core.util.ExcelReadUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.Client;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.buss.project.entity.ProjectPartEntity;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.entity.TreeNode;
import com.yuhi.wechar.facade.ProjectPartFacade;



/**   
 * @Title: Controller
 * @Description: project_part
 * @author onlineGenerator
 * @date 2016-10-10 17:23:06
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/projectPartController")
public class ProjectPartController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProjectPartController.class);

	@Autowired
	private ProjectPartFacade projectPartService;
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
	 * 导入地区资料
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
		AjaxJson j = new AjaxJson();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			List<String[]> excelToArrayList=ExcelReadUtil.excelToArrayList(file.getInputStream(),1);
			projectPartService.importData(excelToArrayList);
			System.out.println(excelToArrayList);
		}
		return j;
	}
	
	/**
	 * 获取子项目
	 * @param projectCreate
	 * @return
	 */
	@RequestMapping(params = "getPartbyParentid")
	@ResponseBody
	public PageList getPartbyParentid(ProjectPartEntity projectCreate){
		CriteriaQuery cq=new CriteriaQuery(ProjectPartEntity.class);
		if(StringUtils.isNotEmpty(projectCreate.getParentsid()+""))cq.add(Restrictions.eq("parentsid",projectCreate.getParentsid()));
		PageList list= systemService.getPageList(cq, false);
		return list;
	}
	/*
	 * project_part列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "projectPart")
	public ModelAndView projectPart(HttpServletRequest request) {
		//projectPartList.jsp
		HttpSession session = ContextHolderUtils.getSession();
		Client c=ClientManager.getInstance().getClient(session.getId());
		String deptid=c.getUser().getTSDepart().getId();
		request.setAttribute("deptid",deptid);
		return new ModelAndView("weixin/idea/project/system/projectPartList");
	}

	/**
	 * 模糊查找地点
	 * @param request
	 * @param response
	 * @param treegrid
	 * @return
	 */
	@RequestMapping(params = "autodepart")
	@ResponseBody
	public  List<Map<String, Object>>  autodepart(String wirter) {
		HttpSession session = ContextHolderUtils.getSession();
		Client c=ClientManager.getInstance().getClient(session.getId());
		String deptid=c.getUser().getTSDepart().getId();
		try {
			return projectPartService.getpartNodeBywirter(wirter+"%",deptid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * 地区树形表
	 * @param request
	 * @param response
	 * @param treegrid
	 * @return
	 */
	@RequestMapping(params = "departgrid")
	@ResponseBody
	public  List<TreeNode>  departgrid(String parentid) {
		//projectPartList.jsp
		List<TreeNode> tree=null;
		try {
			tree = projectPartService.getpartNodeByType(parentid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tree;
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
	public void datagrid(ProjectPartEntity projectPart,HttpServletRequest request, HttpServletResponse response, Integer rows, Integer page ,DataGrid dataGrid) {
		MapData md=new MapData(request,"field");
		// 自定义追加查询条件
		HttpSession session = ContextHolderUtils.getSession();
		Client c=ClientManager.getInstance().getClient(session.getId());
		String deptid=c.getUser().getTSDepart().getId();
		if(!deptid.equals("4028d881436d514601436d5214d70015"))md.put("part", deptid);
		try {
			TagUtil.wirteJson(response,projectPartService.datagrid(md, page, rows));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除project_part
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(String id, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "project_part删除成功";
		try{
			projectPartService.delete(id);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "project_part删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除project_part
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "project_part删除成功";
		try{
			for(String id:ids.split(",")){
				projectPartService.delete(id);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "project_part删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加project_part
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
		message = "project_part添加成功";
		try{
			HttpSession session = ContextHolderUtils.getSession();
			Client c = ClientManager.getInstance().getClient(session.getId());
			String deptid = c.getUser().getTSDepart().getId();
			obj.put("part", deptid);
			projectPartService.save(obj);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "project_part添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新project_part
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
		message = "区域更新成功";
		if(StringUtil.isNotEmpty(id)){
			try {
				JSONObject t = projectPartService.getObjByid(id);
				projectPartService.Update(md.getMap(), id);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "区域更新失败";
				throw new BusinessException(e.getMessage());
			}
		}else{
			JSONObject t = new JSONObject(md);
			try{
				HttpSession session = ContextHolderUtils.getSession();
				Client c = ClientManager.getInstance().getClient(session.getId());
				String deptid = c.getUser().getTSDepart().getId();
				t.put("part",deptid);
				projectPartService.save(t);
				j.setSuccess(true);
			}catch(Exception e){
				e.printStackTrace();
				message = "区域添加失败";
				throw new BusinessException(e.getMessage());
			}
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * project_part新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(String id, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {
			JSONObject projectPart=null;
			try {
				projectPart = projectPartService.getObjByid(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("projectPartPage", projectPart);
		}
		return new ModelAndView("weixin/idea/project/system/projectPart-add");
	}
	/**
	 * project_part编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(String id,String parentsid,HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {
			JSONObject projectPart=null;
			try {
				projectPart = projectPartService.getObjByid(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("projectPartPage", projectPart);
		}
		if(StringUtil.isNotEmpty(parentsid)){
			req.setAttribute("projectPartPage", parentsid);
		}
		return new ModelAndView("weixin/idea/project/system/projectPart-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/buss/project/projectPartUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(ProjectPartEntity projectPart,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "project_part";
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
			CriteriaQuery cq = new CriteriaQuery(ProjectPartEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, projectPart, request.getParameterMap());
			
			List<ProjectPartEntity> projectParts = this.systemService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("project_part列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), ProjectPartEntity.class, projectParts);
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
	public void exportXlsByT(ProjectPartEntity projectPart,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "project_part";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("project_part列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), ProjectPartEntity.class, null);
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
	
}
