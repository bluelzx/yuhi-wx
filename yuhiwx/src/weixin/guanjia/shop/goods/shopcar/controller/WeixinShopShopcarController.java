package weixin.guanjia.shop.goods.shopcar.controller;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import weixin.guanjia.shop.goods.shopcar.entity.WeixinShopShopcarEntity;
import weixin.guanjia.shop.goods.shopcar.service.WeixinShopShopcarServiceI;



/**   
 * @Title: Controller
 * @Description: weixin_shop_shopcar
 * @author onlineGenerator
 * @date 2016-06-13 12:51:01
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinShopShopcarController")
public class WeixinShopShopcarController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinShopShopcarController.class);

	@Autowired
	private WeixinShopShopcarServiceI weixinShopShopcarService;
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
	 * weixin_shop_shopcar列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinShopShopcar")
	public ModelAndView weixinShopShopcar(HttpServletRequest request) {
		return new ModelAndView("weixin/guanjia/shopcar/weixinShopShopcarList");
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
	public void datagrid(WeixinShopShopcarEntity weixinShopShopcar,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinShopShopcarEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinShopShopcar, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinShopShopcarService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除weixin_shop_shopcar
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinShopShopcarEntity weixinShopShopcar, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinShopShopcar = systemService.getEntity(WeixinShopShopcarEntity.class, weixinShopShopcar.getId());
		message = "weixin_shop_shopcar删除成功";
		try{
			weixinShopShopcarService.delete(weixinShopShopcar);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "weixin_shop_shopcar删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除weixin_shop_shopcar
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "weixin_shop_shopcar删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinShopShopcarEntity weixinShopShopcar = systemService.getEntity(WeixinShopShopcarEntity.class, 
				id
				);
				weixinShopShopcarService.delete(weixinShopShopcar);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "weixin_shop_shopcar删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加weixin_shop_shopcar
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinShopShopcarEntity weixinShopShopcar, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "weixin_shop_shopcar添加成功";
		try{
			weixinShopShopcarService.save(weixinShopShopcar);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "weixin_shop_shopcar添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新weixin_shop_shopcar
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinShopShopcarEntity weixinShopShopcar, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "weixin_shop_shopcar更新成功";
		WeixinShopShopcarEntity t = weixinShopShopcarService.get(WeixinShopShopcarEntity.class, weixinShopShopcar.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinShopShopcar, t);
			weixinShopShopcarService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "weixin_shop_shopcar更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * weixin_shop_shopcar新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinShopShopcarEntity weixinShopShopcar, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinShopShopcar.getId())) {
			weixinShopShopcar = weixinShopShopcarService.getEntity(WeixinShopShopcarEntity.class, weixinShopShopcar.getId());
			req.setAttribute("weixinShopShopcarPage", weixinShopShopcar);
		}
		return new ModelAndView("weixin/guanjia/shopcar/weixinShopShopcar-add");
	}
	/**
	 * weixin_shop_shopcar编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinShopShopcarEntity weixinShopShopcar, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinShopShopcar.getId())) {
			weixinShopShopcar = weixinShopShopcarService.getEntity(WeixinShopShopcarEntity.class, weixinShopShopcar.getId());
			req.setAttribute("weixinShopShopcarPage", weixinShopShopcar);
		}
		return new ModelAndView("weixin/guanjia/shopcar/weixinShopShopcar-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/guanjia/shopcar/weixinShopShopcarUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinShopShopcarEntity weixinShopShopcar,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "weixin_shop_shopcar";
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
			CriteriaQuery cq = new CriteriaQuery(WeixinShopShopcarEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinShopShopcar, request.getParameterMap());
			
			List<WeixinShopShopcarEntity> weixinShopShopcars = this.weixinShopShopcarService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("weixin_shop_shopcar列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinShopShopcarEntity.class, weixinShopShopcars);
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
	public void exportXlsByT(WeixinShopShopcarEntity weixinShopShopcar,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "weixin_shop_shopcar";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("weixin_shop_shopcar列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinShopShopcarEntity.class, null);
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
				List<WeixinShopShopcarEntity> listWeixinShopShopcarEntitys = 
					(List<WeixinShopShopcarEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),WeixinShopShopcarEntity.class,params);
				for (WeixinShopShopcarEntity weixinShopShopcar : listWeixinShopShopcarEntitys) {
					weixinShopShopcarService.save(weixinShopShopcar);
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
