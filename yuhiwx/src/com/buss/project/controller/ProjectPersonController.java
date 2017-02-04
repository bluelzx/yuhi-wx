package com.buss.project.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.buss.project.entity.ProjectPersonEntity;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.facade.OwnerFacade;
import com.yuhi.wechar.facade.ProjectPersonFacade;

/**
 * @Title: Controller
 * @Description: 工程人员表
 * @author onlineGenerator
 * @date 2016-09-26 11:30:06
 * @version V1.0
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/projectPersonController")
public class ProjectPersonController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(ProjectPersonController.class);
	@Autowired
	private OwnerFacade ownerService;
	@Autowired
	private ProjectPersonFacade projectPersonService;
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
	 * 工程人员停用
	 * 
	 * @param request
	 * @return
	 * 
	 *         TODO
	 */
	@RequestMapping(params = "destroyperson")
	public String destroyperson(HttpServletRequest request) {
		request.getParameter("id"); // 用户id
//		int nums = projectPersonService.updateBySqlString("");
		return "SUCCESS";
	}

	/**
	 * 工程人员绑定页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "ppb")
	public String ppb(HttpServletRequest request,String openid) {
		request.setAttribute("openid", openid);
		JSONObject entity=null;
		try {
			entity = ownerService.getOwnerObjByOpenid(openid);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(entity==null||StringUtils.isEmpty(entity.getString("id"))){
			try {
				entity = projectPersonService.getppObjByOpenid(openid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(entity!=null&&StringUtils.isNotEmpty(entity.getString("id"))){
				request.setAttribute("pp", entity);
				return "weixin/idea/project/template/ProjectPersoninfoOrupdate";
				//如果已经绑定
			}
			return "weixin/idea/project/template/ProjectPersonbudding2";
		}
		return "weixin/idea/project/template/buddingerror";
	}

	/**
	 * 工程人员表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "projectPerson")
	public ModelAndView projectPerson(HttpServletRequest request) {
		return new ModelAndView("weixin/idea/project/system/projectPersonList");
	}

	@RequestMapping(params = "partProjectCard")
	public ModelAndView partProjectCard(HttpServletRequest request, String isok,String part) {
		ModelAndView mv = new ModelAndView(
				"weixin/idea/project/system/projectPersonList");
		mv.addObject("isok", isok);
		mv.addObject("part", part);
		return mv;
	}
	/**
	 * 工程人员表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "showinfoorupdate")
	public ModelAndView showinfoorupdate(String openid,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(openid)) {
			JSONObject projectPerson=null;
			try {
				projectPerson = projectPersonService.getppObjByOpenid(openid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("pp", projectPerson);
		}
		return new ModelAndView(
				"weixin/idea/project/template/ProjectPersoninfoOrupdate");
	}
	/**
	 * 禁用/启用
	 * 
	 * @param projectPerson
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "resetstate")
	@ResponseBody
	public AjaxJson resetstate(String id,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		Map<String, Object> map=new HashMap<String, Object>();
		message = "工程人员表操作成功";
		try {
			JSONObject projectPerson = projectPersonService.getObjByid(id);
			if (projectPerson.getString("isok").equals("2")) {
				map.put("ISOK", "1");
			} else {
				map.put("ISOK", "2");
			}
			projectPersonService.Update(map,id);
			systemService.addLog(message, Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "工程人员表操作失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
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
	public void datagrid(ProjectPersonEntity projectPerson,
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
			TagUtil.wirteJson(response,projectPersonService.datagrid(md, page, rows));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除工程人员表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(String id,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "工程人员表删除成功";
		try {
			projectPersonService.delete(id);
			systemService.addLog(message, Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "工程人员表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除工程人员表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "工程人员表删除成功";
		try {
			for (String id : ids.split(",")) {
				projectPersonService.delete(id);
				systemService.addLog(message, Globals.Log_Type_DEL,
						Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "工程人员表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加工程人员表
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
		try {
			obj.put("isok", "1");
			projectPersonService.save(obj);
//			WeixinUtil.sendMainMesByOpenid(systemService, WeixinRedirectUrl.PROJECT_APPID,WeixinRedirectUrl.PROJECT_APPSECRET,projectPerson.getOpenid()
//					,"元海集团提醒你：\n工程人员绑定成功");
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 更新工程人员表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ProjectPersonEntity projectPerson,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		MapData md=new MapData(request);
		String id=md.getString("id");
		md.setUnContainKey("id");
		try {
			projectPersonService.Update(md.getMap(), id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 工程人员表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(String id,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {
			JSONObject projectPerson=null;
			try {
				projectPerson = projectPersonService.getObjByid(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("projectPersonPage", projectPerson);
		}
		return new ModelAndView("weixin/idea/project/system/projectPerson-add");
	}

	/**
	 * 工程人员表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(String id, 
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {
			JSONObject projectPerson=null;
			try {
				projectPerson = projectPersonService.getObjByid(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("projectPersonPage", projectPerson);
		}
		return new ModelAndView(
				"weixin/idea/project/system/projectperson-update");
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView(
				"weixin/idea/project/system/projectPersonUpload");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(ProjectPersonEntity projectPerson,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "工程人员表";
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
			CriteriaQuery cq = new CriteriaQuery(ProjectPersonEntity.class,
					dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil
					.installHql(cq, projectPerson, request.getParameterMap());

			List<ProjectPersonEntity> projectPersons = this.systemService
					.getListByCriteriaQuery(cq, false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("工程人员表列表",
					"导出人:" + ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), ProjectPersonEntity.class, projectPersons);
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
	public void exportXlsByT(ProjectPersonEntity projectPerson,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "工程人员表";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("工程人员表列表",
					"导出人:" + ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), ProjectPersonEntity.class, null);
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
				List<ProjectPersonEntity> listProjectPersonEntitys = (List<ProjectPersonEntity>) ExcelImportUtil
						.importExcelByIs(file.getInputStream(),
								ProjectPersonEntity.class, params);
				for (ProjectPersonEntity projectPerson : listProjectPersonEntitys) {
					systemService.save(projectPerson);
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
