package weixin.guanjia.message.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
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
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.message.dao.TextTemplateDao;
import weixin.guanjia.message.entity.Articles;
import weixin.guanjia.message.entity.TextTemplate;
import weixin.guanjia.message.entity.WeixinAlongSourceEntity;
import weixin.guanjia.message.service.TextTemplateServiceI;
import weixin.guanjia.message.service.WeixinAlongSourceServiceI;
import weixin.guanjia.subrice.entity.SyvscribeusersEntity;
import weixin.guanjia.subrice.service.SyvscribeusersServiceI;
import weixin.util.WeiXinConstants;

/**
 * 群发消息与永久素材管理
 * 
 * @author 李森林 描述一下： 2016-6-18 下午3:56:39
 */

@Scope("prototype")
@Controller
@RequestMapping("/groupSendMes")
public class WeixingroupSendMes extends BaseController {
	private static final Logger logger = Logger
			.getLogger(WeixingroupSendMes.class);
	private String message;

	@Resource
	private SystemService systemService;
	@Resource
	private SyvscribeusersServiceI syvscribeusersServiceI;
	@Autowired
	private WeixinAlongSourceServiceI weixinAlongSourceService;
	@Autowired
	private TextTemplateDao textTemplateDao;
	@Autowired
	private TextTemplateServiceI textTemplateService;

	/**
	 * 上传图文消息素材
	 * @param request
	 * @param article
	 * @return
	 */
	@RequestMapping(params = "uploadMesofpicAndText")
	@ResponseBody
	public AjaxJson uploadMesofpicAndText(HttpServletRequest request,
			Articles article) {
		AjaxJson j = new AjaxJson();
		WeixinAccountEntity weixinAccountEntity = (WeixinAccountEntity) request
				.getSession().getAttribute(WeiXinConstants.WEIXIN_ACCOUNT);
		List<Articles> articles = new ArrayList<Articles>();
		articles.add(article);
		boolean isok = weixinAlongSourceService.uploadNews(weixinAccountEntity,
				articles);
		if (!isok)message = "weixin_along_source添加失败";
		message = "weixin_along_source添加成功";
		j.setMsg(message);
		return j;
	}

	/**
	 * 群发其他消息
	 * 
	 * @param request
	 * @param openid
	 * @param type
	 * @param picindex
	 * @return
	 */
	@RequestMapping(params = "sendMesByOther", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson sendMesByOther(HttpServletRequest request,
			@RequestParam(required = true) String openid,
			@RequestParam(required = true) String type,
			@RequestParam(required = true) String picindex) {
		AjaxJson j = new AjaxJson();
		WeixinAccountEntity weixinAccountEntity = (WeixinAccountEntity) request
				.getSession().getAttribute(WeiXinConstants.WEIXIN_ACCOUNT);
		JSONObject mesCallback = WeixinUtil.SendMesToUsers(systemService,
				weixinAccountEntity.getAccountappid(),
				weixinAccountEntity.getAccountappsecret(), openid.split(","),
				2, picindex);
		
		//TODO Not judge
		//if (!isok)message = "weixin_along_source添加失败";
		message = "weixin_along_source添加成功";
		j.setMsg(message);
		return j;
	}

	/**
	 * 群发文本信息
	 * @param request
	 * @param openid
	 * @param texttemple
	 * @return
	 */
	@RequestMapping(params = "sendMesBytext", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson sendMesBytext(HttpServletRequest request,
			@RequestParam(required = true) String openid,
			@RequestParam(required = true) String texttemple) {
		AjaxJson j = new AjaxJson();
		WeixinAccountEntity weixinAccountEntity = (WeixinAccountEntity) request
				.getSession().getAttribute(WeiXinConstants.WEIXIN_ACCOUNT);
		String[] content = texttemple.split(",");
		TextTemplate textTemplate = textTemplateDao.getTextTemplate(content[1],
				content[0]);
		JSONObject mesCallback = WeixinUtil.SendMesToUsers(systemService,
				weixinAccountEntity.getAccountappid(),
				weixinAccountEntity.getAccountappsecret(), openid.split(","),
				0, textTemplate.getContent());
		//TODO Not judge
		//if (!isok)message = "weixin_along_source添加失败";
		message = "weixin_along_source添加成功";
		j.setMsg(message);
		return j;
	}

	/**
	 * 群发文本页面跳转
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "gosendtextpage")
	public ModelAndView gosendtextpage(HttpServletRequest request) {
		List<TextTemplate> textlist = textTemplateService
				.getList(TextTemplate.class);
		List<SyvscribeusersEntity> userlist = syvscribeusersServiceI
				.getList(SyvscribeusersEntity.class);
		request.setAttribute("textlist", textlist);
		request.setAttribute("userlist", userlist);
		return new ModelAndView(
				"weixin/guanjia/group/messendmanager/sendtextmes");
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
	public void datagrid(WeixinAlongSourceEntity weixinAlongSource,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinAlongSourceEntity.class,
				dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				weixinAlongSource, request.getParameterMap());
		try {
			// 自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinAlongSourceService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除weixin_along_source
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinAlongSourceEntity weixinAlongSource,
			HttpServletRequest request) {
		String parameter = request.getParameter("ids");
		AjaxJson j = new AjaxJson();
		weixinAlongSource = systemService.getEntity(
				WeixinAlongSourceEntity.class, weixinAlongSource.getMediaId());
		message = "weixin_along_source删除成功";
		try {
			WeixinAccountEntity weixinAccountEntity = (WeixinAccountEntity) request
					.getSession().getAttribute(WeiXinConstants.WEIXIN_ACCOUNT);
			weixinAlongSourceService.delete(weixinAlongSource,
					weixinAccountEntity);
			systemService.addLog(message, Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "weixin_along_source删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加weixin_along_source
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson doAdd(WeixinAlongSourceEntity weixinAlongSource,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String files =request.getParameter("file");
		String sourcetype = request.getParameter("sourcetype");
		String name = request.getParameter("name");
		File file=new File(request.getRealPath("/")+files.substring(files.indexOf("/userfiles")));
		WeixinAccountEntity weixinAccountEntity = (WeixinAccountEntity) request.getSession().getAttribute(
								WeiXinConstants.WEIXIN_ACCOUNT);
		boolean isok = weixinAlongSourceService.uploadSource(name,weixinAccountEntity, file, sourcetype);
		if (!isok)message = "weixin_along_source添加失败";
		message = "weixin_along_source添加成功";
		j.setMsg(message);
		return j;
	}
	/**
	 * weixin_along_source新增图文素材页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAddPicOrText")
	public ModelAndView goAddPicOrText(HttpServletRequest request) {
		List<Object[]> list = weixinAlongSourceService
				.findListbySql("select media_id,name from weixin_along_source where type='THUMB'");
		request.setAttribute("piclist", list);
		return new ModelAndView(
				"weixin/guanjia/group/sourcemanager/weixinAlongSource-addtextorpic");
	}

	/**
	 * weixin_along_source列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinAlongSource")
	public ModelAndView weixinAlongSource(HttpServletRequest request) {
		return new ModelAndView(
				"weixin/guanjia/group/sourcemanager/weixinAlongSourceList");
	}

	/**
	 * weixin_along_source新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinAlongSourceEntity weixinAlongSource,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinAlongSource.getMediaId())) {
			weixinAlongSource = weixinAlongSourceService.getEntity(
					WeixinAlongSourceEntity.class,
					weixinAlongSource.getMediaId());
			req.setAttribute("weixinAlongSourcePage", weixinAlongSource);
		}
		return new ModelAndView(
				"weixin/guanjia/group/sourcemanager/weixinAlongSource-addtext");
	}

	/**
	 * 群发其他页面跳转
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "gosendanOtherpage")
	public ModelAndView gosendanOtherpage(HttpServletRequest request) {
		List<WeixinAlongSourceEntity> list = weixinAlongSourceService
				.getList(WeixinAlongSourceEntity.class);
		List<SyvscribeusersEntity> userlist = syvscribeusersServiceI
				.getList(SyvscribeusersEntity.class);
		request.setAttribute("userlist", userlist);
		request.setAttribute("piclist", list);
		return new ModelAndView("weixin/guanjia/group/messendmanager/sendOther");
	}
	
	/**
	 * 异步checkbox联动
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "gettypedata")
	@ResponseBody
	public List<WeixinAlongSourceEntity> gettypedata(HttpServletRequest request,String typename) {
		List<WeixinAlongSourceEntity> list = weixinAlongSourceService
				.findListbySql("select media_id,name from weixin_along_source where type='"+typename+"'");
		return list;
	}
	
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
