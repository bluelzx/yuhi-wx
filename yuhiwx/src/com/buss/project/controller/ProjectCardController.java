package com.buss.project.controller;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import com.buss.project.entity.MsgManager;
import com.buss.project.entity.ProjectCardEntity;
import com.google.gson.Gson;
import com.sun.star.uno.RuntimeException;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.facade.OwnerFacade;
import com.yuhi.wechar.facade.ProjectCardFacade;
import com.yuhi.wechar.facade.ProjectCardObjectionFacade;
import com.yuhi.wechar.facade.ProjectCreateFacade;
import com.yuhi.wechar.facade.ProjectPersonFacade;
import com.yuhi.wechar.facade.ProjectPickHistoryFacade;
import com.yuhi.wechar.facade.ProjectServerTypeFacade;



/**   
 * @Title: Controller
 * @Description: project_card
 * @author onlineGenerator
 * @date 2016-09-26 11:22:52
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/projectCardController")
public class ProjectCardController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProjectCardController.class);
	@Autowired
	private ProjectCreateFacade projectCreateService;
	@Autowired
	private ProjectPersonFacade projectPersonService;
	@Autowired
	private ProjectCardFacade projectCardService;
	@Autowired
	private OwnerFacade ownerService;
	@Autowired
	private ProjectCardObjectionFacade projectCardObjectionService;
	@Autowired
	private ProjectPickHistoryFacade projectPickHistoryService;
	@Autowired
	private SystemService systemService;

	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	private net.sf.json.JSONObject sendMes(Map<String, String> map,String openid,String tempateid,String url){
		return WeixinUtil.sendMainMesBytemplate(systemService, WeixinRedirectUrl.PROJECT_APPID, WeixinRedirectUrl.PROJECT_APPSECRET,openid,map,tempateid,url);
	}
	@RequestMapping(params = "testsendMes")
	@ResponseBody
	public String sendMes(HttpServletRequest request, HttpServletResponse response){
		Map<String, String> map=new HashMap<String, String>();
		map.put("first", "您已签到成功");
		map.put("keyword1", "张江一科 王##");
		map.put("keyword2", "2015年10月9日 09:10");
		map.put("keyword3", "上海市浦东新区碧波路500号");
		map.put("keyword4", "上班");
		map.put("keyword5", "今天准时上班");
		map.put("remark", "具体内容请查看详情！");	
		sendMes(map,"o9o_Ot88wBF0DndyQ5UJp9sjPKN0",
				WeixinRedirectUrl.MES_PS_SIGN,"#");
		return "success";
	}
	
	/**
	 * 审核
	 * @param request
	 * @param id
	 * @param isok
	 * @return
	 */
	@RequestMapping(params = "PCthroughAudit")
	@ResponseBody
	public AjaxJson PCthroughAudit(HttpServletRequest request,String id,String isok){
		AjaxJson json=new AjaxJson();
		json.setMsg(null);
		try {
			if (StringUtil.isNotEmpty(id)) {
				projectCardService.PCthroughAudit(id,isok);
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		json.setSuccess(true);
		return json;
	}
	
	/**
	 * 查看详细
	 * @param projectCardid
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goProjectcardByid")
	public String goProjectcardByid(String projectCardid,HttpServletRequest request) {
		if (StringUtil.isNotEmpty(projectCardid)) {
			JSONObject detaillist = null;
			try {
				detaillist = projectCardService.getProjectCardDetail(projectCardid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(detaillist!=null&&detaillist.size()>0){
				request.setAttribute("projectCardPage", detaillist);				
			}
		}
		return "weixin/idea/project/template/ProjectCardDetail";
	}
	
	/**
	 * 我的工程单页面
	 */
	@RequestMapping(params = "goProjectListPage")
	public String goProjectListPage(HttpServletRequest request,
			@RequestParam(required=true)String openid,
			String ownerid,
			@RequestParam(required=true)Integer type){  
		List<JSONObject>  pccountmap=null;
		try {
			//待接单_1,待办理_2,办理中 _3,办理结束(待评价)_4,待回访_5,办理完成_6
			switch (type) {
			case 10011:
					pccountmap=projectCardService.getProjectCardBytype(ownerid,true);
				request.setAttribute("pccountmap", pccountmap);
				request.setAttribute("ownerid", ownerid);
				request.setAttribute("openid", openid);
				return "weixin/idea/project/template/projectMenuTab2";
			case 10012:
				pccountmap=projectCardService.getProjectCardBytype(openid,false);
				request.setAttribute("pccountmap", pccountmap);
				request.setAttribute("openid", openid);
				return "weixin/idea/project/template/ppprojectMenuTab2";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "404";
	}
	/**
	 * 我的工程单列表数据(客户)
	 */
	@RequestMapping(params = "getMyProjectListData")
	@ResponseBody
	public PageList getMyProjectListData(HttpServletRequest request,String ownerid,String type){
		PageList list = null;
		try {
			List<JSONObject> lists=projectCardService.getownerListData(ownerid, type);
			list=new PageList(lists, "", 0, 0, lists.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 我的工程单列表数据(工程)
	 */
	@RequestMapping(params = "getppProjectListData")
	@ResponseBody
	public PageList getppProjectListData(HttpServletRequest request,String openid,String type){  	
		PageList list = null;
		try {
			List<JSONObject> lists=projectCardService.getppProjectListData(openid, type);
			list=new PageList(lists, "", 0, 0, lists.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 催单
	 * @param request
	 * @param openid
	 * @param ownerid
	 * @return
	 */
	@RequestMapping(params = "PCReminder",method=RequestMethod.GET)
	@ResponseBody
	public AjaxJson PCReminder(HttpServletRequest request,
								@RequestParam(required=true)String id,
								@RequestParam(required=true)String ownerid){
		AjaxJson aj=new AjaxJson();
		aj.setMsg(null);
		try {
			JSONObject entity=projectCardService.getObjByid(id);
			if(entity.getInteger("reminderstate")==0){
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("REMINDERSTATE", "1");
				projectCardService.Update(map,id);
				//TODO 微信催单
				Map<String, String> psmap=new HashMap<String, String>();
				psmap.put("first", "来自业主的催单通知");
				psmap.put("keyword1", id);
				psmap.put("keyword2", "暂无");
				psmap.put("keyword3", "业主十分着急，请加急处理工单！");
				psmap.put("remark", "（如有疑问，请拨打热线：0757-88880000）");	
				sendMes(psmap,entity.getString("DISTRIBUTION_OPENID"),
						"qX6DrvzbRlNs0OsaQNpdtGPqNfb4vEiZoB_DMG6sE9A",
						"http://mt.yuhi.com.cn/yuhiwx/projectCardController.do?goProjectcardByid&projectCardid="+entity.getString("ID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			aj.setSuccess(false);
		}
		aj.setSuccess(true);
		return aj;
	}
	/**
	 * 工单关闭
	 * @param request
	 * @param openid
	 * @param ownerid
	 * @return
	 */
	@RequestMapping(params = "closeProjectCard",method=RequestMethod.GET)
	@ResponseBody
	public AjaxJson closeProjectCard(HttpServletRequest request,
								@RequestParam(required=true)String id){
		AjaxJson aj=new AjaxJson();
		try {
			aj.setMsg("工程单关闭成功！");
			projectCardService.closeProjectCard(id);
			aj.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			aj.setSuccess(false);
		}
		return aj;
	}
	/**
	 * 支付详情页
	 * @param pcid
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goPay")
	public String goPay(String pcid,String openid,HttpServletRequest request) {
		if (StringUtil.isNotEmpty(pcid)&&StringUtil.isNotEmpty(openid)) {
			request.setAttribute("pcid", pcid);
			request.setAttribute("openid", openid);
		}else{
			throw new RuntimeException();
		}
		return "weixin/idea/project/template/payForProjectCard";
//		return "weixin/pay/payForProjectCard";
	}
	/**
	 * 支付确认
	 * @param request
	 * @param id
	 * @param isok
	 * @return
	 */
	@RequestMapping(params = "confirmpay",method=RequestMethod.GET)
	@ResponseBody
	public AjaxJson confirmpay(HttpServletRequest request,
			@RequestParam(required=true)String id,
			@RequestParam(defaultValue="2")Integer paytype){
		AjaxJson json=new AjaxJson();
		json.setMsg(null);
		try {
			if (StringUtil.isNotEmpty(id)) {
				projectCardService.confirmpay(id,paytype+"");
				json.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}
	
	
	/****(旧)
	 * 我的工程单列表
	 */
	@RequestMapping(params = "getMyProjectList",method=RequestMethod.GET)
	public String getMyProjectList(HttpServletRequest request,String openid,String ownerid){
		//判断身份是工程人员还是业主
		try {
			JSONObject list = projectPersonService.getppObjByOpenid(openid);
			if(list!=null){ //工程人员bnur
				List<JSONObject> projectlist=	projectCardService.getDatabySql("SELECT OWNERID,CREATE_TIME,HANDLE_TIME,DISTRIBUTION_TIME," +
						"GET_TIME,FINISHED_TIME,CONTENT,STATE FROM PROJECT_CARD" +
										" WHERE DISTRIBUTION_OPENID=? AND ISOK='1'",openid);
				request.setAttribute("idtype", "1");
				request.setAttribute("projectlist", projectlist);
			}else { //业主
				List<JSONObject> projectlist=	projectCardService.getDatabySql("SELECT OWNERID,CREATE_TIME,HANDLE_TIME,DISTRIBUTION_TIME," +
						"GET_TIME,FINISHED_TIME,CONTENT,STATE FROM PROJECT_CARD" +
						" WHERE OWNERID=? AND ISOK='1'",ownerid);
				request.setAttribute("idtype", "2");
				request.setAttribute("projectlist", projectlist);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "weixin/idea/project/myProjectCard";
	}

	/**
	 * 工程单状态修改
	 * @param openid 
	 * @param appid 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(params = "updateProjectState",method=RequestMethod.POST)
	@ResponseBody
	public String updateProjectState(HttpServletRequest request,
			@RequestParam(required=true)String id,
			@RequestParam(required=true)Integer type, String openid) throws Exception{
				try{
					MapData md=new MapData(request, "");
					MapData data=projectCardService.updateProjectState(md,id,type,openid);
					int resulttype=(int)data.get("resulttype");
					String pcid=data.getString("pcid");
						switch (resulttype) {
							case WeixinRedirectUrl.NOGET_PROJECTCARD:
								String sendid=data.getString("distribution_openid");
								String part=data.getString("part");
								String ownerid=data.getString("ownerid");
								//消息格式化
								data.setUnContainKey("distribution_openid","resulttype","part","ownerid","pcid");
								
								WeixinUtil.sendMainMesByOpenid(systemService, WeixinRedirectUrl.PROJECT_APPID,
										WeixinRedirectUrl.PROJECT_APPSECRET,sendid
										,"元海集团提醒你：\n拒单已经完成,工程单将会重新交由管理员进行分配");
		//						//消息添加
								MsgManager.getInstance().addMsg(part);
		//						模板消息发送
								sendMes(data, ownerid,
										"7nKA0i9U5abI8sXEu9Hh37iaj3oIbbsywRVW7o40OkY", 
										"http://mt.yuhi.com.cn/yuhiwx/projectCardController.do?goProjectcardByid&projectCardid="+pcid);
								break;
							case WeixinRedirectUrl.GET_PROJECTCARD:
								String sendid2=data.getString("distribution_openid");
								//消息格式化
								data.setUnContainKey("distribution_openid","resulttype","pcid");
								sendMes(data,sendid2,
										"VbFtjp4n7_xjvlysv1P7QfZTMmbNRQ9EoN-f3S14tGY",
										"http://mt.yuhi.com.cn/yuhiwx/projectCardController.do?goProjectcardByid&projectCardid="+pcid);
								break;
							case WeixinRedirectUrl.START_PROJECRCARD:
								//暂无
								break;
							case WeixinRedirectUrl.FINISHED_PROJECTCARD:
								String ownerid2=data.getString("ownerid");
								String pctype=data.getString("pctype");
								String sendid3=data.getString("distribution_openid");
								if(pctype.equals("0")){
									WeixinUtil.sendMainMesByOpenid(systemService,  WeixinRedirectUrl.PROJECT_APPID,WeixinRedirectUrl.PROJECT_APPSECRET, sendid3
											,"元海集团提醒你：\n工程单已完成,辛苦您了,将由业主对此工程单进行评价,密切关注我的工程单查看工单的详情吧");
								}else if(pctype.equals("1")){
									WeixinUtil.sendMainMesByOpenid(systemService,  WeixinRedirectUrl.PROJECT_APPID,WeixinRedirectUrl.PROJECT_APPSECRET, sendid3
											,"元海集团提醒你：\n公共服务单已完成,辛苦您了！");
								}
								//消息格式化
								data.setUnContainKey("distribution_openid","resulttype","pcid","pctype","ownerid");
								sendMes(data, ownerid2,WeixinRedirectUrl.MES_PP_PROJECTCREATE,
										"http://mt.yuhi.com.cn/yuhiwx/projectCardController.do?goProjectcardByid&projectCardid="+pcid);
								break;
							case WeixinRedirectUrl.EVALUATE_PROJECT:
								//暂无
								break;
							default:throw new Exception("请求非法");
							}
				} catch (Exception e) {
					e.printStackTrace();
					return "error";
				}
				return "success";
	}
	//设置服务类型
	@RequestMapping(params="setProjectType",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson setProjectType(String id,String pctype){
		AjaxJson aj=new AjaxJson();
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("pcserver", pctype);
			projectCardService.Update(map, id);
		} catch (Exception e) {
			aj.setSuccess(false);
		}
		return aj;
	}
	/**
	 * project_card列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "projectCard")
	public ModelAndView projectCard(HttpServletRequest request) {
		return new ModelAndView("weixin/idea/project/system/projectCardList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param openid 
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ProjectCardEntity projectCard,HttpServletRequest request, HttpServletResponse response,
						DataGrid dataGrid, String openid,
						@RequestParam(required=true)Integer page,
						@RequestParam(required=true)Integer rows) {
		MapData md=new MapData(request,"field");
		// 自定义追加查询条件
		HttpSession session = ContextHolderUtils.getSession();
		Client c=ClientManager.getInstance().getClient(session.getId());
		String deptid=c.getUser().getTSDepart().getId();
		if(!deptid.equals("4028d881436d514601436d5214d70015"))md.put("part", deptid);
		try {
			TagUtil.wirteJson(response,projectCardService.datagrid(md, page, rows));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	/**
	 * 添加project_card
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ProjectCardEntity projectCard, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		MapData md=new MapData(request);
		JSONObject obj=new JSONObject(md);
		message = "project_card添加成功";
		try{
			projectCardService.save(obj);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "project_card添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 添加回访内容
	 * @param projectCard
	 * @param request
	 * @returnProjectCardObjectionEntity
	 */
	@RequestMapping(params = "CallbackprojectCard")
	@ResponseBody
	public AjaxJson CallbackprojectCard(HttpServletRequest request,String ids,String othercontent) {
			AjaxJson j = new AjaxJson();
			message = "更新成功";
			if(StringUtils.isEmpty(ids)||StringUtils.isEmpty(othercontent)){
				j.setSuccess(false);
				return j;
			}
			try {
				projectCardService.CallbackprojectCard(ids,othercontent);
			} catch (Exception e) {
				e.printStackTrace();
				message = "更新失败";
				j.setSuccess(false);
				throw new BusinessException(e.getMessage());
			}
			j.setMsg(message);
			return j;
		}
	/**
	 * 工单回访页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goCallback")
	public ModelAndView goCallback(ProjectCardEntity projectCard, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(projectCard.getId())) {
			req.setAttribute("id", projectCard.getId());
		}
		return new ModelAndView("weixin/idea/project/system/projectCard-callback");
	}
	@Autowired
	private ProjectServerTypeFacade projectServerTypeService;
	/**
	 * 工单完成页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "finishPc")
	public ModelAndView finishPc(ProjectCardEntity projectCard, HttpServletRequest req) {
			List<JSONObject> entity=null;
			try {
				entity = projectServerTypeService.getList();
			} catch (Exception e) {
				e.printStackTrace();
			}
			req.setAttribute("entity", entity);
		return new ModelAndView("weixin/idea/project/system/projectCard-finish");
	}
	/**
	 * project_card新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(String id, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {
			JSONObject projectCard=null;
			try {
				projectCard = projectCardService.getObjByid(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("projectCardPage", projectCard);
		}
		return new ModelAndView("weixin/idea/project/system/projectCard-add");
	}
	/**
	 * project_card支付详情页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "gopayDetail")
	public ModelAndView gopayDetail(ProjectCardEntity projectCard, HttpServletRequest req) {
		req.setAttribute("id", projectCard.getId());
		return new ModelAndView("weixin/idea/project/system/projectCard-paydetail");
	}
	/**
	 * project_card编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(String id, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {
			JSONObject projectCard=null;
			try {
				projectCard = projectCardService.getObjByid(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("projectCardPage", projectCard);
		}
		return new ModelAndView("weixin/idea/project/system/projectCard-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/idea/project/system/projectCardUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(ProjectCardEntity projectCard,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "project_card";
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
			
			HttpSession session = ContextHolderUtils.getSession();
			Client c=ClientManager.getInstance().getClient(session.getId());
			String deptid=c.getUser().getTSDepart().getId();
			
			String entitysql="select new ProjectCardEntity(p.id,ow.name,p.createTime,p.handleTime,p.distributionTime,p.getTime,p.finishedTime,ppe.name,p.isok,p.content,p.state,p.propertyobjectionsid,p.ownerobjectionid,p.successlevel,p.propertyobjectionfid,p.companyobjectionfid,p.descs,p.edition,p.starttime,p.endtime,p.phone,p.otherContact,p.invitationid,p.reminderstate,p.throughAudit,pp.region,p.isPay,p.pctype,p.pcserver,p.roomid)";
			StringBuffer sql=new StringBuffer(" from ProjectCardEntity p,OwnerEntity ow,ProjectPersonEntity ppe,ProjectPartEntity pp where p.ownerid=ow.id and p.distributionOpenid=ppe.openid and p.part=pp.id");
			if(!deptid.equals("4028d881436d514601436d5214d70015"))sql.append(" and p.part='"+deptid+"'");
//			String begin=request.getParameter("createTime_begin");
//			if(StringUtil.isNotEmpty(begin)){
//				String end=request.getParameter("createTime_end");
//				sql.append(" and p.createTime>='"+begin+"' AND p.createTime<='"+end+"'");
//			}
//			if(StringUtil.isNotEmpty(projectCard.getPctype()))sql.append(" and p.pctype = "+projectCard.getPctype()+" ");
//			if(StringUtils.isNotEmpty(projectCard.getOwnerid()))sql.append(" and ow.name like '%"+projectCard.getOwnerid()+"%' ");
//			if(StringUtils.isNotEmpty(projectCard.getState()))sql.append(" and p.state ='"+projectCard.getState()+"' ");
//			if(StringUtils.isNotEmpty(projectCard.getPhone()))sql.append(" and p.phone like '"+projectCard.getPhone()+"%' ");
//			if(StringUtils.isNotEmpty(projectCard.getIsok()))sql.append(" and p.isok ='"+projectCard.getIsok()+"' ");
//			if(StringUtil.isNotEmpty(projectCard.getThroughAudit()))sql.append(" and p.throughAudit ='"+projectCard.getThroughAudit()+"' ");
//			if(StringUtil.isNotEmpty(projectCard.getIsPay()))sql.append(" and p.isPay ='"+projectCard.getIsPay()+"' ");
//			if(StringUtils.isNotEmpty(projectCard.getId()))sql.append(" and p.id like '%"+projectCard.getId()+"%' ");
//			if(StringUtils.isNotEmpty(projectCard.getRoomid()))sql.append(" and p.roomid like '"+projectCard.getRoomid()+"%' ");
//			List<ProjectCardEntity> entitys=projectCardService.findHql(sql.toString());
			List<ProjectCardEntity> projectCards=systemService.findHql(entitysql+sql.toString()+" order by p.createTime desc");
			//处理数据可视化
			for (ProjectCardEntity e : projectCards) {
				ProjectCardEntity.changeState(e);
				ProjectCardEntity.changeisok(e);
			}
			
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("工程单列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), ProjectCardEntity.class, projectCards);
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
	public void exportXlsByT(ProjectCardEntity projectCard,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "project_card";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("project_card列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), ProjectCardEntity.class, null);
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
				List<ProjectCardEntity> listProjectCardEntitys = 
					(List<ProjectCardEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),ProjectCardEntity.class,params);
				for (ProjectCardEntity projectCard : listProjectCardEntitys) {
					systemService.save(projectCard);
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
