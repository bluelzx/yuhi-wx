package weixin.guanjia.shop.goods.items.controller;
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

import weixin.guanjia.shop.goods.items.entity.WeixinShopGoodsEntity;
import weixin.guanjia.shop.goods.items.service.WeixinShopGoodsServiceI;
import weixin.guanjia.shop.goods.type.entity.WeixinMallCategoryEntity;
import weixin.guanjia.shop.goods.type.service.WeixinMallCategoryServiceI;



/**   
 * @Title: Controller
 * @Description: 商品信息
 * @author onlineGenerator
 * @date 2016-06-12 08:45:28
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinShopGoodsController")
public class WeixinShopGoodsController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinShopGoodsController.class);

	@Autowired
	private WeixinMallCategoryServiceI weixinMallCategoryServiceI; 
	@Autowired
	private WeixinShopGoodsServiceI weixinShopGoodsService;
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
	 * 商品信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinShopGoods")
	public ModelAndView weixinShopGoods(HttpServletRequest request) {
		return new ModelAndView("/weixin/guanjia/items/wsGoodsList");
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
	public void datagrid(WeixinShopGoodsEntity weixinShopGoods,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinShopGoodsEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinShopGoods, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinShopGoodsService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除商品信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinShopGoodsEntity weixinShopGoods, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinShopGoods = systemService.getEntity(WeixinShopGoodsEntity.class, weixinShopGoods.getId());
		message = "商品信息删除成功";
		try{
			weixinShopGoodsService.delete(weixinShopGoods);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商品信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除商品信息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "商品信息删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinShopGoodsEntity weixinShopGoods = systemService.getEntity(WeixinShopGoodsEntity.class, 
				id
				);
				weixinShopGoodsService.delete(weixinShopGoods);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "商品信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加商品信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinShopGoodsEntity weixinShopGoods, HttpServletRequest request) {
		String imageHref=request.getParameter("imageHref");
		weixinShopGoods.setTitleImg(imageHref);
		AjaxJson j = new AjaxJson();
		message = "商品信息添加成功";
		try{
			weixinShopGoodsService.save(weixinShopGoods);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商品信息添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新商品信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinShopGoodsEntity weixinShopGoods, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "商品信息更新成功";
		WeixinShopGoodsEntity t = weixinShopGoodsService.get(WeixinShopGoodsEntity.class, weixinShopGoods.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinShopGoods, t);
			weixinShopGoodsService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "商品信息更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 商品信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinShopGoodsEntity weixinShopGoods, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinShopGoods.getId())) {
			weixinShopGoods = weixinShopGoodsService.getEntity(WeixinShopGoodsEntity.class, weixinShopGoods.getId());
			req.setAttribute("weixinShopGoodsPage", weixinShopGoods);
		}
		List<Object> list = weixinMallCategoryServiceI.getList(WeixinMallCategoryEntity.class);
		List<String[]> buiese = systemService.getBuiese();
		req.setAttribute("parenttypes", list);
		req.setAttribute("buieses", buiese);
		return new ModelAndView("/weixin/guanjia/items/addwsGood");
	}
	/**
	 * 商品信息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinShopGoodsEntity weixinShopGoods, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinShopGoods.getId())) {
			weixinShopGoods = weixinShopGoodsService.getEntity(WeixinShopGoodsEntity.class, weixinShopGoods.getId());
			req.setAttribute("weixinShopGoodsPage", weixinShopGoods);
		}
		return new ModelAndView("com/buss/a/weixinShopGoods-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/buss/a/weixinShopGoodsUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinShopGoodsEntity weixinShopGoods,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "商品信息";
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
			CriteriaQuery cq = new CriteriaQuery(WeixinShopGoodsEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinShopGoods, request.getParameterMap());
			
			List<WeixinShopGoodsEntity> weixinShopGoodss = this.weixinShopGoodsService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("商品信息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinShopGoodsEntity.class, weixinShopGoodss);
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
	public void exportXlsByT(WeixinShopGoodsEntity weixinShopGoods,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "商品信息";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("商品信息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinShopGoodsEntity.class, null);
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
				List<WeixinShopGoodsEntity> listWeixinShopGoodsEntitys = 
					(List<WeixinShopGoodsEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),WeixinShopGoodsEntity.class,params);
				for (WeixinShopGoodsEntity weixinShopGoods : listWeixinShopGoodsEntitys) {
					weixinShopGoodsService.save(weixinShopGoods);
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
