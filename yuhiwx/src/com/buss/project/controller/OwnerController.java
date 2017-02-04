package com.buss.project.controller;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
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

import weixin.guanjia.core.util.WeixinUtil;
import weixin.util.BaseTools;
import weixin.util.WeixinRedirectUrl;

import com.alibaba.fastjson.JSONObject;
import com.buss.project.entity.OwnerEntity;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.facade.OwnerFacade;
import com.yuhi.wechar.facade.ProjectOwnermesFacade;
import com.yuhi.wechar.facade.ProjectPersonFacade;



/**   
 * @Title: Controller
 * @Description: owner
 * @author onlineGenerator
 * @date 2016-10-31 09:07:16
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/ownerController")
public class OwnerController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(OwnerController.class);
	@Autowired
	private OwnerFacade ownerService;
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
	 * 业主园区通告
	 * @param request
	 * @param openid
	 * @return
	 */
	@RequestMapping(params = "ParkCircular")
	public String ParkCircular(HttpServletRequest request,String openid) {
		HttpSession session = ContextHolderUtils.getSession();
		Client c=ClientManager.getInstance().getClient(session.getId());
		String deptid=c.getUser().getTSDepart().getId();
//		List<ProjectOwnermesEntity> entity=projectOwnermesService.findHql("select new ProjectOwnermesEntity(id,title) from ProjectOwnermesEntity where part=?",deptid);
//		request.setAttribute("ProjectOwnermes", entity);
		return "weixin/idea/project/system/owner-sendmes";
	}
	@RequestMapping(params = "sendMesforOwner")
	@ResponseBody
	public AjaxJson sendMesforOwner(HttpServletRequest request,
			@RequestParam(required=true)String templateid){
//		ProjectOwnermesEntity entity=projectOwnermesService.get(ProjectOwnermesEntity.class, );
		AjaxJson aj=new AjaxJson();
		Map<String, String> map = new HashMap<String, String>();
		HttpSession session = ContextHolderUtils.getSession();
		Client c=ClientManager.getInstance().getClient(session.getId());
		String deptid=c.getUser().getTSDepart().getId();
		JSONObject ownerMesentity=null;
		try {
			ownerMesentity = projectOwnermesService.getObjByid(templateid);
			ownerService.sendMesforOwner(templateid,deptid);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			aj.setSuccess(false);
			return aj;
		}
		map.put("first", ownerMesentity.getString("title"));
		map.put("keyword1",ownerMesentity.getString("title"));
		map.put("keyword2", BaseTools.getDate(null));
		map.put("keyword3", ownerMesentity.getString("content"));
		map.put("remark", StringUtils.isNotEmpty(ownerMesentity.getString("remark"))?ownerMesentity.getString("remark"):"感谢你对元海集团的大力支持!");
		try {
			
			List<String> ownerid=ownerService.getOwnerIds(deptid);
//			for (String string : ownerid) {
//				sendMes(map,string,"FYLPNEQZJta52aSYo6NcH1lVR8IR1JU0GStszsjvVAs","域名"+entity.getTemplatehtml()+".html");
//			}
			aj.setMsg("发布成功");
		} catch (Exception e) {
			aj.setSuccess(false);
			e.printStackTrace();
		}
		return aj;
	}	
	/**
	 * 业主绑定
	 * @param request
	 * @param openid
	 * @return
	 */
	@RequestMapping(params = "ob")
	public String ob(HttpServletRequest request,String openid) {
		request.setAttribute("openid", openid);
			try {
				JSONObject owner = ownerService.isBidding(openid);
				if(owner!=null&&StringUtils.isNotEmpty(owner.getString("ID"))){
					request.setAttribute("owner", owner);
					return "weixin/idea/project/template/OwnerinfoOrUpdate";
					//如果已经绑定
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "weixin/idea/project/template/OwnerBudding";
	}
	/**
	 * owner列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "owner")
	public ModelAndView owner(HttpServletRequest request) {
		return new ModelAndView("weixin/idea/project/system/ownerList");
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
	public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, Integer rows, Integer page) {
		MapData md=new MapData(request,"field");
		// 自定义追加查询条件
		HttpSession session = ContextHolderUtils.getSession();
		Client c=ClientManager.getInstance().getClient(session.getId());
		String deptid=c.getUser().getTSDepart().getId();
		if(!deptid.equals("4028d881436d514601436d5214d70015"))md.put("part", deptid);
		try {
			TagUtil.wirteJson(response,ownerService.datagrid(md, page, rows));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除owner
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(String id, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
//		owner = systemService.getEntity(OwnerEntity.class, owner.getId());
		message = "owner删除成功";
		try{
			ownerService.DoUnBidding(id);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "owner删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除owner
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "owner删除成功";
		try{
			for(String id:ids.split(",")){
				ownerService.DoUnBidding(id);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "owner删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加owner
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HttpServletRequest request) {
		MapData md=new MapData(request);
		AjaxJson j = new AjaxJson();
		message = "owner添加成功";
		try{
			ownerService.DoBidding(md);
		}catch(Exception e){
			e.printStackTrace();
			message = "owner添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新owner
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		MapData md=new MapData(request);
		try {
			ownerService.DoUpdateOwnerInfo(md);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	public void chang(Date date,Date date2){
		
	}

	/**OwnerinfoOrUpdate.jsp
	 * owner新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(String id, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {
			JSONObject owner=null;
			try {
				owner = ownerService.isBidding(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("ownerPage", owner);
		}
		return new ModelAndView("weixin/idea/project/system/owner-add");
	}
	/**
	 * owner编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(String id, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {
			//TODO
			JSONObject owner=null;
			try {
				owner = ownerService.isBidding(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("owner", owner);
		}
		return new ModelAndView("weixin/idea/project/system/owner-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/buss/project/ownerUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(OwnerEntity owner,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "owner";
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
			CriteriaQuery cq = new CriteriaQuery(OwnerEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, owner, request.getParameterMap());
			//TODO
//			List<OwnerEntity> owners = this.ownerService.getListByCriteriaQuery(cq,false);
//			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("owner列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
//					"导出信息"), OwnerEntity.class, owners);
//			fOut = response.getOutputStream();
//			workbook.write(fOut);
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
	public void exportXlsByT(OwnerEntity owner,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "owner";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("owner列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), OwnerEntity.class, null);
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
				List<OwnerEntity> listOwnerEntitys = 
					(List<OwnerEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),OwnerEntity.class,params);
//				for (OwnerEntity owner : listOwnerEntitys) {
//					ownerService.save(owner);
//				}
				//TODO
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
	private net.sf.json.JSONObject sendMes(Map<String, String> map, String openid,
			String tempateid, String url) {
		return WeixinUtil.sendMainMesBytemplate(systemService,
				WeixinRedirectUrl.PROJECT_APPID,
				WeixinRedirectUrl.PROJECT_APPSECRET, openid, map, tempateid,
				url);
	}
}
