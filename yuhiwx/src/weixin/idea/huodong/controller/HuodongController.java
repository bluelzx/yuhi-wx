package weixin.idea.huodong.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.idea.huodong.entity.HuodongEntity;
import weixin.idea.huodong.entity.WeixinVoteEntity;
import weixin.idea.huodong.entity.WeixinVoteItemsEntity;
import weixin.idea.huodong.service.HuodongServiceI;
import weixin.idea.huodong.service.WeixinVoteItemsServiceI;
import weixin.idea.huodong.service.WeixinVoteServiceI;
import weixin.util.BaseTools;
import weixin.util.WeixinRedirectUrl;

/**   
 * @Title: Controller
 * @Description: 活动
 * @author zhangdaihao
 * @date 2014-06-06 11:05:30
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/huodongController")
public class HuodongController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HuodongController.class);
	@Autowired
	private WeixinVoteServiceI weixinVoteService;
	@Autowired
	private WeixinVoteItemsServiceI weixinVoteItemsService;
	@Autowired
	private HuodongServiceI huodongService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@RequestMapping(params = "userlocal")
	public String userlocal(HttpServletRequest request,HttpServletResponse respnose,
				String voteid){
//		https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx23093572b097a654&redirect_uri=http://www.baidu.com/index.jsp&response_type=code&scope=snsapi_userinfo#wechat_redirect
		String code=request.getParameter("code");
		if(!StringUtils.isEmpty(code)){
			JSONObject httpRequest = weixin.guanjia.core.util.WeixinUtil.httpRequest("https://api.weixin.qq.com/sns/oauth2/access_token?" +
					"appid="+WeixinRedirectUrl.PROJECT_APPID+"&secret="+WeixinRedirectUrl.PROJECT_APPSECRET+"&code="+code+"&grant_type=authorization_code", "GET", "");
			String useropenid=httpRequest.getString("openid");
			List<String[]> pickethistory=weixinVoteItemsService.findListbySql("select id from weixin_vote_history where name='"+useropenid+"' and voteid='"+voteid+"'");
			if(pickethistory!=null&&pickethistory.size()>0){
				request.setAttribute("choicetype","1");//验证该openid已经投票
			}
			//累计参与人数自增
			weixinVoteService.updateBySqlString("update weixin_vote set totalperson=totalperson+1 where id='"+voteid+"'");
			request.setAttribute("openid", useropenid);
			return "weixin/idea/huodong/toupiao/toupiaolist";
		}
		WeixinVoteEntity voteEntity=weixinVoteService.get(WeixinVoteEntity.class, "297eb23e559b175401559b1754360000");
		request.setAttribute("voteEntity", voteEntity);
		//TODO 测试环境下  （实际为未授权访问）
		return "weixin/idea/huodong/toupiao/toupiaolist";
	}
	
	/**
	 * 获取投票列表json
	 * @param request
	 * @param respnose
	 * @param voteid
	 * @return
	 */
	@RequestMapping(params = "getpicketlist")
	@ResponseBody
	public List<WeixinVoteItemsEntity> getpicketlist(HttpServletRequest request,HttpServletResponse respnose,
				String voteid,@RequestParam(defaultValue="1")String curPage){
		Map<String, Object> map=new HashMap<String, Object>();
		//		CriteriaQuery hqlQuery=new CriteriaQuery(WeixinVoteItemsEntity.class);
//		hqlQuery.eq("voteid", voteid);
//		hqlQuery.setPageSize(1);
//		hqlQuery.setCurPage(Integer.valueOf(curPage));
		HqlQuery hqlQuery=new HqlQuery("from WeixinVoteItemsEntity where voteid='"+voteid+"'", map);
		hqlQuery.setMyaction("");
		hqlQuery.setPageSize(100);
		hqlQuery.setCurPage(Integer.valueOf(curPage));
		PageList pagelist=weixinVoteItemsService.getPageList(hqlQuery, false);
		
		return pagelist.getResultList();
	}
	
	
	/**
	 * 投票
	 * @param request
	 * @param respnose
	 * @param voteid
	 * @return
	 */
	@RequestMapping(params = "addpicket")
	@ResponseBody
	public String addpicket(HttpServletRequest request,HttpServletResponse respnose,
				String voteid,String openid,@RequestParam(required=true)String id){
		WeixinVoteItemsEntity itemsEntity=weixinVoteItemsService.get(WeixinVoteItemsEntity.class,id);
		if(itemsEntity!=null){
			//修改选择项
			String piaocount=(Integer.valueOf(itemsEntity.getPiaocount())+1)+"";
			itemsEntity.setPiaocount(piaocount);
			weixinVoteItemsService.updateEntitie(itemsEntity);
			//增加投票历史记录
			List<String> param=new ArrayList<String>();
			weixinVoteItemsService.executeSql("insert weixin_vote_history values(?,?,NOW(),?,?)", 
					BaseTools.getUUID(),openid,itemsEntity.getId(),itemsEntity.getVoteid());
			//累计票数自增
			weixinVoteService.updateBySqlString("update weixin_vote set totalpicket=totalpicket+1 where id='"+voteid+"'");
			return "SUCCESS";
		}
		return "ERROR";
	}
	
	
	/**
	 * 活动列表 页面跳转
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		//2：大转盘 1：刮刮乐
		String type = request.getParameter("type");
		request.setAttribute("type", type);
		return new ModelAndView("weixin/idea/huodong/huodong/huodongList");
	}

	/**
	 * 统计图 AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */
	@RequestMapping(params = "getpicketresult")
	@ResponseBody
	public picketresultdata getpicketresult(HttpServletRequest request,String voteid) {
		List<Object[]> pickethistory=weixinVoteItemsService.findListbySql("select `name`,piaocount from weixin_vote_items where voteid='"+voteid+"'");
		if(pickethistory!=null&&pickethistory.size()>0){
			List<String> categories=new ArrayList<String>();
			List<Integer> datas=new ArrayList<Integer>();
			for (Object[] strings : pickethistory) {
				categories.add(strings[0].toString());
				datas.add(Integer.valueOf(strings[1].toString()));
			}
			picketresultdata data=new picketresultdata(categories,datas);
			return data;
		}
		return null;
	}
	
	private class picketresultdata{
		private List<String> categories;
		private List<Integer> data;
		public picketresultdata(List<String> categories, List<Integer> data) {
			super();
			this.categories = categories;
			this.data = data;
		}
		public List<String> getCategories() {
			return categories;
		}
		public void setCategories(List<String> categories) {
			this.categories = categories;
		}
		public List<Integer> getData() {
			return data;
		}
		public void setData(List<Integer> data) {
			this.data = data;
		}
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
	public void datagrid(HuodongEntity huodong,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String type = request.getParameter("type");
		CriteriaQuery cq = new CriteriaQuery(HuodongEntity.class, dataGrid);
		cq.eq("type", type);
		cq.eq(ACCOUNTID, ResourceUtil.getWeiXinAccountId());
		cq.add();
		org.jeecgframework.core.util.LogUtil.info(".....type....."+type);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, huodong, request.getParameterMap());
		this.huodongService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除活动
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(HuodongEntity huodong, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		huodong = systemService.getEntity(HuodongEntity.class, huodong.getId());
		message = "活动删除成功";
		huodongService.delete(huodong);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加活动
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(HuodongEntity huodong, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(huodong.getId())) {
			message = "活动更新成功";
			HuodongEntity t = huodongService.get(HuodongEntity.class, huodong.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(huodong, t);
				huodongService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "活动更新失败";
			}
		} else {
			message = "活动添加成功";
			huodongService.save(huodong);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 活动列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addOrUpdate")
	public ModelAndView addOrUpdate(HuodongEntity huodong, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(huodong.getId())) {
			huodong = huodongService.getEntity(HuodongEntity.class, huodong.getId());
			req.setAttribute("huodongPage", huodong);
		}
		return new ModelAndView("weixin/idea/huodong/huodong/huodong");
	}
}
