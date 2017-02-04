package weixin.idea.huodong.controller;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
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

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.idea.huodong.entity.WeixinVoteEntity;
import weixin.idea.huodong.service.WeixinVoteServiceI;
import weixin.util.WeiXinConstants;



/**   
 * @Title: Controller
 * @Description: 投票表
 * @author onlineGenerator
 * @date 2016-06-29 16:06:58
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinVoteController")
public class WeixinVoteController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinVoteController.class);

	@Autowired
	private WeixinVoteServiceI weixinVoteService;
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
	 * 投票表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinVote")
	public ModelAndView weixinVote(HttpServletRequest request) {
		return new ModelAndView("weixin/idea/huodong/toupiao/weixinVoteList");
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
	public void datagrid(WeixinVoteEntity weixinVote,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinVoteEntity.class, dataGrid);
		//查询条件组装器
		TSUser user = ResourceUtil.getSessionUserName();
//		WeixinAccountEntity entity=(WeixinAccountEntity) request.getSession().getAttribute(WeiXinConstants.WEIXIN_ACCOUNT);
		if(!user.getUserName().equals("admin")){
			cq.eq("accountid", user.getId());
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinVote, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinVoteService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除投票表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinVoteEntity weixinVote, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinVote = systemService.getEntity(WeixinVoteEntity.class, weixinVote.getId());
		message = "投票表删除成功";
		try{
			weixinVoteService.delete(weixinVote);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "投票表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除投票表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "投票表删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinVoteEntity weixinVote = systemService.getEntity(WeixinVoteEntity.class, 
				id
				);
				weixinVoteService.delete(weixinVote);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "投票表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加投票表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinVoteEntity weixinVote, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "投票表操作成功";
		try{
			if(StringUtils.isEmpty(weixinVote.getId())){
					WeixinAccountEntity entity=(WeixinAccountEntity) request.getSession().getAttribute(WeiXinConstants.WEIXIN_ACCOUNT);
					weixinVote.setAccountid(entity.getId());
					weixinVote.setCreateDate(new Date());
					weixinVoteService.save(weixinVote);
					systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}else{
				weixinVoteService.updateEntitie(weixinVote);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "投票表操作失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新投票表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinVoteEntity weixinVote, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "投票表更新成功";
		WeixinVoteEntity t = weixinVoteService.get(WeixinVoteEntity.class, weixinVote.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinVote, t);
			weixinVoteService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "投票表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 投票表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinVoteEntity weixinVote, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinVote.getId())) {
			weixinVote = weixinVoteService.getEntity(WeixinVoteEntity.class, weixinVote.getId());
			req.setAttribute("weixinVotePage", weixinVote);
		}
		return new ModelAndView("weixin/idea/huodong/toupiao/weixinVote-add");
	}
	/**
	 * 投票表编辑子选项页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdateitem")
	public ModelAndView goUpdateitem(WeixinVoteEntity weixinVote, HttpServletRequest req) {
//		if (StringUtil.isNotEmpty(weixinVote.getId())) {
//			weixinVote = weixinVoteService.getEntity(WeixinVoteEntity.class, weixinVote.getId());
//			req.setAttribute("weixinVotePage", weixinVote);
//		}
		req.setAttribute("voteid", weixinVote.getId());
		return new ModelAndView("weixin/idea/huodong/toupiao/weixinVoteItemsList");
	}
	/**
	 * 投票表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdatevote")
	public ModelAndView goUpdatevote(WeixinVoteEntity weixinVote, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinVote.getId())) {
			weixinVote = weixinVoteService.getEntity(WeixinVoteEntity.class, weixinVote.getId());
			req.setAttribute("weixinVote", weixinVote);
		}
		return new ModelAndView("weixin/idea/huodong/toupiao/weixinVote-add");
	}
	/**
	 * 查看结果
	 * @param id
	 * @param req
	 * @return
	 */
	@RequestMapping(params="showresult")
	public String showresult(@RequestParam(required=true)String id, HttpServletRequest req){
		return "weixin/idea/huodong/toupiao/toupiaojiegou.jsp?voteid="+id;
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/buss/aaa/weixinVoteUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinVoteEntity weixinVote,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "投票表";
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
			CriteriaQuery cq = new CriteriaQuery(WeixinVoteEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinVote, request.getParameterMap());
			
			List<WeixinVoteEntity> weixinVotes = this.weixinVoteService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("投票表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinVoteEntity.class, weixinVotes);
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
	public void exportXlsByT(WeixinVoteEntity weixinVote,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "投票表";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("投票表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinVoteEntity.class, null);
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
				List<WeixinVoteEntity> listWeixinVoteEntitys = 
					(List<WeixinVoteEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),WeixinVoteEntity.class,params);
				for (WeixinVoteEntity weixinVote : listWeixinVoteEntitys) {
					weixinVoteService.save(weixinVote);
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
