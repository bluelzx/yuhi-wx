package com.buss.project.controller;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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
import org.jeecgframework.core.util.Word2Html;
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

import weixin.util.BaseTools;
import weixin.util.RandomUtils;

import com.alibaba.fastjson.JSONObject;
import com.buss.project.entity.ProjectOwnermesEntity;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.facade.ProjectOwnermesFacade;



/**   
 * @Title: Controller
 * @Description: 园区通告
 * @author onlineGenerator
 * @date 2016-11-13 09:35:21
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/projectOwnermesController")
public class ProjectOwnermesController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProjectOwnermesController.class);

	@Autowired
	private ProjectOwnermesFacade projectOwnermesService;
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
	 * 园区通告列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "projectOwnermes")
	public ModelAndView projectOwnermes(HttpServletRequest request) {
		return new ModelAndView("weixin/idea/project/system/projectOwnermesList");
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
	public void datagrid(ProjectOwnermesEntity projectOwnermes,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,@RequestParam(required=true)Integer page,
			@RequestParam(required=true)Integer rows) {
		MapData md=new MapData(request,"field");
		// 自定义追加查询条件
		HttpSession session = ContextHolderUtils.getSession();
		Client c=ClientManager.getInstance().getClient(session.getId());
		String deptid=c.getUser().getTSDepart().getId();
		if(!deptid.equals("4028d881436d514601436d5214d70015"))md.put("part", deptid);
		try {
			TagUtil.wirteJson(response,projectOwnermesService.datagrid(md, page, rows));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 添加园区通告模板
	 * 
	 * @return
	 */
	@RequestMapping(params = "doAddTmpleate",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public AjaxJson doAddTmpleate(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		ResourceBundle bundler = ResourceBundle.getBundle("sysConfig");
		String rootPath=bundler.getString("NginxMsgTmplate");
		if(request instanceof MultipartHttpServletRequest){
			JSONObject obj=new JSONObject();
				MultipartFile pictureFile=	((MultipartHttpServletRequest) request).getMultiFileMap().getFirst("upfile");
					String pictureFile_name =  pictureFile.getOriginalFilename();
					String filename =pictureFile_name.substring(0,pictureFile_name.lastIndexOf("."));
					String suffix = pictureFile_name.substring(pictureFile_name.lastIndexOf(".")); //文件后缀名
					String newFileName = filename+RandomUtils.getDateString();
					String dateproject=RandomUtils.getFileDateString();
					String url = "/"+dateproject+"/"+newFileName+suffix;
					File uploadPic = new java.io.File(rootPath+url);   
					if(!uploadPic.exists()){   //判断文件路径是否存在
						uploadPic.mkdirs();      //不存在即创建
					}
					try {
						pictureFile.transferTo(uploadPic);
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}   	//向磁盘写文件
					Word2Html.wordToHtml(rootPath+"/"+dateproject+"/", newFileName+suffix);
					j.setMsg("/"+dateproject+"/"+newFileName);
		}
		return j;
	}
	/**
	 * 删除园区通告
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(String id, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "园区通告删除成功";
		try{
			projectOwnermesService.delete(id);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "园区通告删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除园区通告
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "园区通告删除成功";
		try{
			for(String id:ids.split(",")){
				projectOwnermesService.delete(id);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "园区通告删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加园区通告
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ProjectOwnermesEntity projectOwnermes, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新园区通告
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
		
		HttpSession session = ContextHolderUtils.getSession();
		Client c=ClientManager.getInstance().getClient(session.getId());
		String deptid=c.getUser().getTSDepart().getId();
		JSONObject t=null;
		if(id!=null&&StringUtils.isNotEmpty(id)){
			message = "园区通告更新成功";
			try {
				t= projectOwnermesService.getObjByid(id);
				t.put("update_date",BaseTools.getDate(null));
				t.put("update_name",c.getUser().getUserName());
				projectOwnermesService.Update(md.getMap(), id);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "园区通告更新失败";
				throw new BusinessException(e.getMessage());
			}
		}else{
			t=new JSONObject(md);
			message = "园区通告添加成功";
			try{
				t.put("id", BaseTools.getUUID());
				t.put("part",deptid);
				t.put("create_date",BaseTools.getDate(null));
				t.put("create_name",c.getUser().getUserName());
				projectOwnermesService.save(t);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}catch(Exception e){
				e.printStackTrace();
				message = "园区通告添加失败";
				throw new BusinessException(e.getMessage());
			}
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 园区通告新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(String id, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {
			JSONObject projectOwnermes=null;
			try {
				projectOwnermes = projectOwnermesService.getObjByid(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("projectOwnermesPage", projectOwnermes);
		}
		return new ModelAndView("weixin/idea/project/system/projectOwnermes-update");
	}
	/**
	 * 园区通告编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(String id, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {
			JSONObject projectOwnermes=null;
			try {
				projectOwnermes = projectOwnermesService.getObjByid(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("projectOwnermesPage", projectOwnermes);
		}
		return new ModelAndView("weixin/idea/project/system/projectOwnermes-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/buss/project/projectOwnermesUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(ProjectOwnermesEntity projectOwnermes,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "园区通告";
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
			CriteriaQuery cq = new CriteriaQuery(ProjectOwnermesEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, projectOwnermes, request.getParameterMap());
			
			List<ProjectOwnermesEntity> projectOwnermess = this.systemService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("园区通告列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), ProjectOwnermesEntity.class, projectOwnermess);
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
	public void exportXlsByT(ProjectOwnermesEntity projectOwnermes,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "园区通告";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("园区通告列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), ProjectOwnermesEntity.class, null);
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
				List<ProjectOwnermesEntity> listProjectOwnermesEntitys = 
					(List<ProjectOwnermesEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),ProjectOwnermesEntity.class,params);
				for (ProjectOwnermesEntity projectOwnermes : listProjectOwnermesEntitys) {
					systemService.save(projectOwnermes);
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
