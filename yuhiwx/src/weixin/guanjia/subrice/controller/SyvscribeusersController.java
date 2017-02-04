package weixin.guanjia.subrice.controller;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.subrice.entity.SyvscribeusersEntity;
import weixin.guanjia.subrice.service.SyvscribeusersServiceI;
import weixin.util.WeixinRedirectUrl;

import java.util.List;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeewx.api.core.common.util.WeixinUtil;



/**   
 * @Title: Controller
 * @Description: 关注用户
 * @author onlineGenerator
 * @date 2016-06-06 12:33:41
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/syvscribeusersController")
public class SyvscribeusersController extends BaseController {
	/**
	 * 
	 * getAccessToken
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SyvscribeusersController.class);

	@Autowired
	private SyvscribeusersServiceI syvscribeusersService;
	@Autowired
	private SystemService systemService;
	@Resource
	private  WeixinAccountServiceI weixinAccountService; 
	@Resource
	private SyvscribeusersServiceI syvscribeusersServiceI;
	
	
	private String message;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 用户绑定
	 *   1.请求用户授权地址为：
	 * 		https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx23093572b097a654&redirect_uri=http%3A%2F%2Fd7c2292a.ngrok.io%2Fyuhiwx%2FsyvscribeusersController.do%3Fsyvscribeusers&response_type=code&scope=snsapi_userinfo#wechat_redirect
	 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx23093572b097a654&redirect_uri=http%3A%2F%2Fd7c2292a.ngrok.io%2Fyuhiwx%2FsyvscribeusersController.do%3Fsyvscribeusers&response_type=code&scope=snsapi_base#wechat_redirect	
	 * 注意：redirect_uri	是	授权后重定向的回调链接地址，请使用urlencode对链接进行处理
	 * 
	 * 
	 * 2.根据code请求openid
	 * https://api.weixin.qq.com/sns/oauth2/access_token?
	 * appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
	 *	 
	 */
	@RequestMapping(params = "userlocal")
	public ModelAndView userlocal(HttpServletRequest request,HttpServletResponse respnose){
//		https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx23093572b097a654&redirect_uri=http://www.baidu.com/index.jsp&response_type=code&scope=snsapi_userinfo#wechat_redirect
		String code=request.getParameter("code");
		if(code!=null){
			String useropenid = syvscribeusersService.getSendOpenid(request,code);
//			{"scope":"snsapi_base","openid":"oFsDqwi4j7GDdLU2_onXkeqVMCW0","expires_in":7200,"refresh_token":"q7viVqZFDJuiYoPQzo15o9fgmw7nqNyj4ujnlUX2r5Vjgz8PGbi2euBb_jJLAThCwGcI0HEmYVjsd42OyfX5he4LLxJii7EYJqI3LZfzCp0","access_token":"AzZIuv9HMN9zS5IHEjMLEGDJYT_uWM7frw0gpSVpOm5zTAhJ9qCNp2ldYEMKANv2nge_FM97bfmYaJFdXDdBRxHUBLD7cec8lLp630Ilj9M"}
			List<String[]> srtlist=syvscribeusersService.getPart();
			request.setAttribute("partlist", srtlist);
			request.setAttribute("openid", useropenid);
			return new ModelAndView("weixin/idea/shop/Binding");
		}else{
			return new ModelAndView("404页面");
		}
	}
	@RequestMapping(params = "userbinding")
	public String userbinding(HttpServletRequest request,HttpServletResponse respnose) throws IOException{
		String openid = request.getParameter("openid");
		String part = request.getParameter("part");
		String mobileNo = request.getParameter("mobileNo");
		if(StringUtil.isEmpty(openid)||StringUtil.isEmpty(part)){
			respnose.getWriter().write("访问异常");
			return null;
		}
		SyvscribeusersEntity entity = syvscribeusersService.get(SyvscribeusersEntity.class, openid);
		if(entity==null){  //可能没立即刷新好友列表，//可能未关注
			weixinAccountService.updateentityList(systemService, syvscribeusersServiceI); 
			entity = syvscribeusersService.get(SyvscribeusersEntity.class, openid);
			if(entity==null){
				respnose.getWriter().write("请先关注或者稍后重试");
				return null;
			}
		}
		entity.setPartid(part);
		if(!StringUtil.isEmpty(mobileNo))entity.setPhone(mobileNo);
		syvscribeusersService.updateEntitie(entity);
		//TODO   根据openid查询用户信息补齐信息
//		syvscribeusersService.get(class1, id)
		return "redirect:"+WeixinRedirectUrl.GOGOODSLIST; //TODO  返回到区域
	}
	/**
	 * 关注用户列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "syvscribeusers")
	public ModelAndView syvscribeusers(HttpServletRequest request) {
		return new ModelAndView("weixin/guanjia/subrice/syvscribeusersList");
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
	public void datagrid(SyvscribeusersEntity syvscribeusers,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SyvscribeusersEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, syvscribeusers, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.syvscribeusersService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除关注用户
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(SyvscribeusersEntity syvscribeusers, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		syvscribeusers = systemService.getEntity(SyvscribeusersEntity.class, syvscribeusers.getId());
		message = "关注用户删除成功";
		try{
			syvscribeusersService.delete(syvscribeusers);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "关注用户删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除关注用户
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "关注用户删除成功";
		try{
			for(String id:ids.split(",")){
				SyvscribeusersEntity syvscribeusers = systemService.getEntity(SyvscribeusersEntity.class, 
				id
				);
				syvscribeusersService.delete(syvscribeusers);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "关注用户删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加关注用户
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(SyvscribeusersEntity syvscribeusers, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "关注用户添加成功";
		try{
			syvscribeusersService.save(syvscribeusers);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "关注用户添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新关注用户
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(SyvscribeusersEntity syvscribeusers, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "关注用户更新成功";
		SyvscribeusersEntity t = syvscribeusersService.get(SyvscribeusersEntity.class, syvscribeusers.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(syvscribeusers, t);
			syvscribeusersService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "关注用户更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 关注用户新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(SyvscribeusersEntity syvscribeusers, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(syvscribeusers.getId())) {
			syvscribeusers = syvscribeusersService.getEntity(SyvscribeusersEntity.class, syvscribeusers.getId());
			req.setAttribute("syvscribeusersPage", syvscribeusers);
		}
		return new ModelAndView("weixin/guanjia/subrice/syvscribeusers-add");
	}
	/**
	 * 关注用户查看页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(SyvscribeusersEntity syvscribeusers, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(syvscribeusers.getId())) {
			syvscribeusers = syvscribeusersService.getEntity(SyvscribeusersEntity.class, syvscribeusers.getId());
			req.setAttribute("entity", syvscribeusers);
		}
		return new ModelAndView("weixin/guanjia/subrice/syvscribeusers-show");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/guanjia/subrice/syvscribeusersUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(SyvscribeusersEntity syvscribeusers,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "关注用户";
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
			CriteriaQuery cq = new CriteriaQuery(SyvscribeusersEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, syvscribeusers, request.getParameterMap());
			
			List<SyvscribeusersEntity> syvscribeuserss = this.syvscribeusersService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("关注用户列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), SyvscribeusersEntity.class, syvscribeuserss);
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
	public void exportXlsByT(SyvscribeusersEntity syvscribeusers,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "关注用户";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("关注用户列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), SyvscribeusersEntity.class, null);
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
				List<SyvscribeusersEntity> listSyvscribeusersEntitys = 
					(List<SyvscribeusersEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),SyvscribeusersEntity.class,params);
				for (SyvscribeusersEntity syvscribeusers : listSyvscribeusersEntitys) {
					syvscribeusersService.save(syvscribeusers);
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
