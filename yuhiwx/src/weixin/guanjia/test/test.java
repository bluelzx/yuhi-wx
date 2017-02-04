package weixin.guanjia.test;
import weixin.guanjia.core.entity.common.AccessToken;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.subrice.entity.SyvscribeusersEntity;
import weixin.guanjia.subrice.service.SyvscribeusersServiceI;
import weixin.util.WeiXinConstants;
import weixin.util.WeixinRedirectUrl;

import java.util.Collection;
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



/**
 * 
 * @author 李森林
 * 描述一下： 获取jsapi
 * 2016-6-20  下午5:29:21
 */
@Scope("prototype")
@Controller
@RequestMapping("/pay")
public class test extends BaseController {
	/**
	 * 
	 * getAccessToken
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(test.class);
	@Resource
	private SystemService systemService;

	
	
	
	@RequestMapping(params = "test")
	@ResponseBody
	public Collection<String> importExcel(HttpServletRequest request, HttpServletResponse response) {
		String jsapi_ticket = WeixinUtil.getjsapiTicket(systemService,"wx2c73741d132b2564","1a431765ca915e124022060289605bbe");
        // 注意 URL 一定要动态获取，不能 hardcode   webpage/weixin/test/MyJsp.jsp
//        String url = "http://21c1973d.ngrok.io/yuhiwx/webpage/weixin/test/MyJsp.jsp";
       String url=request.getParameter("url");
		Map<String, String> ret = WeiXinConstants.sign(jsapi_ticket, url);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
//        request.setAttribute("timestamp", ret.get("timestamp"));
//        request.setAttribute("nonceStr", ret.get("nonceStr"));
//        request.setAttribute("signature", ret.get("signature"));
		return ret.values();
	}
	@RequestMapping("/pay")
	public String pay(){
		return "weixin/pay/web.jsp";
	}
	
}
