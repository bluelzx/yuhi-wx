package com.buss.project.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
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
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.util.BaseTools;
import weixin.util.RandomUtils;
import weixin.util.WeixinRedirectUrl;

import com.alibaba.fastjson.JSONObject;
import com.buss.project.entity.MsgManager;
import com.buss.project.entity.ProjectCreateEntity;
import com.buss.project.entity.ProjectInvitationEntity;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.facade.OwnerFacade;
import com.yuhi.wechar.facade.ProjectCardFacade;
import com.yuhi.wechar.facade.ProjectCreateFacade;
import com.yuhi.wechar.facade.ProjectInvitationFacade;
import com.yuhi.wechar.facade.ProjectPartFacade;

/**
 * @Title: Controller
 * @Description: project_create
 * @author onlineGenerator
 * @date 2016-09-26 11:26:15
 * @version V1.0
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/projectCreateController")
public class ProjectCreateController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(ProjectCreateController.class);
	@Autowired
	private OwnerFacade ownerService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	@Autowired
	private ProjectCreateFacade projectCreateService;
	@Autowired
	private ProjectCardFacade projectCardService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private ProjectInvitationFacade projectInvitationService;
	@Autowired
	private ProjectPartFacade projectPartService;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	//测试
	@RequestMapping(params = "addmsg",method=RequestMethod.GET)
	public void addmsg(String deptid){
		 MsgManager.getInstance().addMsg(deptid);
	}
	
	
	/**
	 * 获取待办消息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "getMsg",method=RequestMethod.GET)
	public AjaxJson getMsg(@RequestParam(required=true)String deptid){
		if(StringUtils.isEmpty(deptid))return null;
		Integer msg = MsgManager.getInstance().getMsg(deptid);
		if(msg!=null&&msg>0){
			AjaxJson aj=new AjaxJson();
			aj.setMsg(msg+"");
			MsgManager.getInstance().removeMsg(deptid);
			return aj;
		}
		return null;
	}
	/**
	 * 人工单录入
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params = "personcard")
	public ModelAndView personcard(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView(
				"weixin/idea/project/system/projectCreate-personcard");
		return mav;
	}
	/**
	 * 公告服务单录入
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "publicProjectCard")
	public ModelAndView publicProjectCard(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(
				"weixin/idea/project/system/projectCreate-publiccard");
		return mav;
	}

	/**
	 * 获取公告服务子耗材
	 * 
	 * @param projectCreate
	 * @return
	 */
	@RequestMapping(params = "getInvitationbyParentid")
	@ResponseBody
	public PageList getInvitationbyParentid(
			ProjectInvitationEntity projectCreate) {
		CriteriaQuery cq = new CriteriaQuery(ProjectInvitationEntity.class);
		if (StringUtils.isNotEmpty(projectCreate.getParentsid() + ""))
			cq.add(Restrictions.eq("parentsid", projectCreate.getParentsid()));
		cq.add(Restrictions.eq("type", 0));
		PageList list = systemService.getPageList(cq, false);
		return list;
	}

	/**
	 * 查看详细
	 * 
	 * @param projectCardid
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params = "goProjectcardByid")
	public String goProjectcardByid(String projectCardid,
			HttpServletRequest request) throws Exception {
		if (StringUtil.isNotEmpty(projectCardid)) {
			JSONObject detaillist = null;
			try {
				detaillist = projectCreateService.getProjectCardDetail(projectCardid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(detaillist!=null&&detaillist.size()>0){
				request.setAttribute("projectCardPage", detaillist);				
			}
		}
		return "weixin/idea/project/template/ProjectCreateDetail";
	}

	@RequestMapping(params = "getProjectCreateList")
	@ResponseBody
	public PageList getProjectCreateList(String ownerid) {
//		CriteriaQuery cq = new CriteriaQuery(ProjectCreateEntity.class);
//		if (StringUtils.isNotEmpty(projectCreate.getOwnerid()))
//			cq.add(Restrictions.eq("ownerid", projectCreate.getOwnerid()));
//		PageList list = systemService.getPageList(cq, false);
//		return list;
		PageList list = null;
		try {
			List<JSONObject> lists=projectCreateService.getProjectCreateList(ownerid);
			if(lists!=null)
			list=new PageList(lists, "", 0, 0, lists.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	/**
	 * 添加发单表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public String doAdd(HttpServletRequest request, String openid, String ownerid) {
		try {
			String result=projectCreateService.doAddForOwner(openid,ownerid);
			String[] content = result.split(",");
			Map<String, String> map = new HashMap<String, String>();
			map.put("first", "尊敬的业主, 您的工单已受理！");
			map.put("keyword1", content[0]);
			map.put("keyword2", "工单已提交,请等待系统分配工程人员");
			map.put("keyword3", BaseTools.getDate(null));
			map.put("remark", "感谢你对元海集团的大力支持!");
			MsgManager.getInstance().addMsg(content[1]);
			sendMes(map, content[2],
					WeixinRedirectUrl.MES_PP_PROJECTCREATE,
					"http://mt.yuhi.com.cn/yuhiwx/projectCreateController.do?goProjectcardByid&projectCardid="+content[0]);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return "1";
	}

	/**
	 * 添加发单表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAddforWindow")
	@ResponseBody
	public AjaxJson doAddforWindow(	HttpServletRequest request, String openid,String ownerid,String roomsbyid) {
		AjaxJson j = new AjaxJson();
		MapData md=new MapData(request,"openid","ownerid","roomsbyid");
		try {
			HttpSession session = ContextHolderUtils.getSession();
			Client c = ClientManager.getInstance().getClient(session.getId());
			TSUser  user=c.getUser();
			String deptid = user.getTSDepart().getId();
			projectCreateService.doAddforWindow(md,roomsbyid,deptid,user.getUserName());
			j.setMsg("人工下单成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 添加发单表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAddPublicforWindow")
	@ResponseBody
	public AjaxJson doAddPublicforWindow(HttpServletRequest request,String pcserver, String openid, String ownerid) {
		AjaxJson j = new AjaxJson();
//		MapData md=new MapData(request,"openid","ownerid","roomsbyid");
		try {
			HttpSession session = ContextHolderUtils.getSession();
			Client c = ClientManager.getInstance().getClient(session.getId());
			TSUser  user=c.getUser();
			String deptid = user.getTSDepart().getId();
			projectCreateService.doAddPublicforWindow(pcserver,deptid,user.getUserName());
			j.setMsg("人工下单成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("人工下单失败！");
		}
		return j;
	}

	/**
	 * 分配单
	 * 
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HttpServletRequest request, String openid) throws Exception {
		AjaxJson j = new AjaxJson();
		message = "分配单失败";
		try {
			JSONObject msgobj= projectCreateService.dispatchProjectCartForPerson(openid,request.getParameter("id"));
			Map<String, String> ownermsg=(HashMap<String, String>)msgobj.get("ownermsg");
			Map<String, String> personmsg=(HashMap<String, String>)msgobj.get("personmsg");
			String pcid=msgobj.getString("projectcardid");
			message = "分配单成功";
			
			sendMes(ownermsg,msgobj.getString("ownersendid"),
					"eZeQ1PbWmdHrYmUXq1yekZ8nGf5erSVd--Eu0lrTv6Y",
					"http://mt.yuhi.com.cn/yuhiwx/projectCardController.do?goProjectcardByid&projectCardid="+pcid);
			
			sendMes(personmsg, msgobj.getString("personsendid"),
					"7nKA0i9U5abI8sXEu9Hh37iaj3oIbbsywRVW7o40OkY", 
					"http://mt.yuhi.com.cn/yuhiwx/projectCardController.do?goProjectcardByid&projectCardid="+pcid);
			message = "分配单成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "分配单失败";
			j.setSuccess(false);
		}
		j.setMsg(message);
		return j;
	}

	private net.sf.json.JSONObject sendMes(Map<String, String> map, String openid,
			String tempateid, String url) {
		return WeixinUtil.sendMainMesBytemplate(systemService,
				WeixinRedirectUrl.PROJECT_APPID,
				WeixinRedirectUrl.PROJECT_APPSECRET, openid, map, tempateid,
				url);
	}

	/**
	 * project_create列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "projectCreate")
	public ModelAndView projectCreate(HttpServletRequest request) {
		HttpSession session = ContextHolderUtils.getSession();
		Client c=ClientManager.getInstance().getClient(session.getId());
		String deptid=c.getUser().getTSDepart().getId();
		request.setAttribute("deptid", deptid);
		return new ModelAndView("weixin/idea/project/system/projectCreateList");
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
	public void datagrid(ProjectCreateEntity projectCreate,
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
			TagUtil.wirteJson(response,projectCreateService.datagrid(md, page, rows));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除project_create
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(String id,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "工程单删除成功";
		try {
			JSONObject obj=projectCreateService.getObjByid(id);
			if(obj!=null)
			projectCreateService.delete(id);
			MsgManager.getInstance().addMsg(obj.getString("part"));
		} catch (Exception e) {
			e.printStackTrace();
			message = "工程单删除失败";
			throw new BusinessException(e.getMessage());
		}
		
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除project_create
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "工程单删除成功";
		try {
			for (String id : ids.split(",")) {
				projectCreateService.delete(id);
				systemService.addLog(message, Globals.Log_Type_DEL,
						Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "工程单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * project_create新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(String id,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {
			JSONObject projectCreate=null;
			try {
				projectCreate = projectCreateService.getObjByid(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("projectCreatePage", projectCreate);
		}
		return new ModelAndView("weixin/idea/project/system/projectCreate-add");
	}

	/**
	 * project_create编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(String id,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {
			JSONObject projectCreate=null;
			try {
				projectCreate = projectCreateService.getObjByid(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("projectCreatePage", projectCreate);
		}
		return new ModelAndView(
				"weixin/idea/project/system/projectCreate-detail");
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView(
				"weixin/idea/project/system/projectCreateUpload");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(ProjectCreateEntity projectCreate,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "project_create";
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
			CriteriaQuery cq = new CriteriaQuery(ProjectCreateEntity.class,
					dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil
					.installHql(cq, projectCreate, request.getParameterMap());

			List<ProjectCreateEntity> projectCreates = this.systemService
					.getListByCriteriaQuery(cq, false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle(
					"project_create列表", "导出人:"
							+ ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), ProjectCreateEntity.class, projectCreates);
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
	public void exportXlsByT(ProjectCreateEntity projectCreate,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "project_create";
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
					"project_create列表", "导出人:"
							+ ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), ProjectCreateEntity.class, null);
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
				List<ProjectCreateEntity> listProjectt = (List<ProjectCreateEntity>) ExcelImportUtil
						.importExcelByIs(file.getInputStream(),
								ProjectCreateEntity.class, params);
				for (ProjectCreateEntity projectCreate : listProjectt) {
					systemService.save(projectCreate);
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
