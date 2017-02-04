package weixin.idea.forum;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import weixin.guanjia.shop.forum.entity.WeixinUserFollowEntity;
import weixin.guanjia.shop.forum.entity.WeixinUserSendcardEntity;
import weixin.guanjia.shop.forum.service.WeixinUserFollowServiceI;
import weixin.guanjia.shop.forum.service.WeixinUserSendcardServiceI;
import weixin.guanjia.subrice.entity.SyvscribeusersEntity;
import weixin.guanjia.subrice.service.SyvscribeusersServiceI;
import weixin.util.UserUtil;
import weixin.util.WeixinRedirectUrl;

@Scope("prototype")
@Controller
@RequestMapping("/forumController")
public class forumController {
	@Autowired
	private WeixinUserFollowServiceI userFollowService;
	@Autowired
	private WeixinUserSendcardServiceI userSendcardService;
	@Autowired
	private SyvscribeusersServiceI syvscribeusersService;
	/**
	 * 论坛主页
	 * @param pageindex
	 * @param request
	 * type="1.热门 2.新发表3.我的"
	 */
	@RequestMapping(params = "showSendCards")
	public String showSendCards(@RequestParam(defaultValue="1",required=false) Integer pageindex,
			@RequestParam(defaultValue="1",required=false) Integer type,
			HttpServletRequest request){
		//String part = UserUtil.getToSessionString(request, WeixinRedirectUrl.PART);
		String part="东边";
		if(!StringUtil.isEmpty(part)){
		
		String openid = UserUtil.getToSessionString(request, WeixinRedirectUrl.OPENID);
		StringBuffer sqlurl=new StringBuffer("select card.title,card.update_date,users.headimgurl,users.nickname,(select count(1) from weixin_user_follow where sendcardid = card.id) followcount,card.id "+
											 " from  weixin_user_sendcard card,syvscribeusers users "+
											 " where card.openid=users.id and orderid=0 and part='"+part+"' ");
		sqlurl.append(gettypeString(type,openid));
		//查询到用户所有的帖子
		Map<String, Object> map=new HashMap<String, Object>();
		HqlQuery hqlQuery=new HqlQuery(sqlurl.toString(), map);
		hqlQuery.setCurPage(pageindex);
		hqlQuery.setPageSize(3);
		PageList pagelist=userSendcardService.getPageListBySql(hqlQuery, false);
		//查询到管理员的帖子
		Map<String, Object> amap=new HashMap<String, Object>();
		HqlQuery ahqlQuery=new HqlQuery("select card.title,card.id,card.titleimage "+
										" from  weixin_user_sendcard card "+
										" where  part='"+part+"' and card.orderid!=0 ORDER BY card.update_date DESC", map);
		hqlQuery.setPageSize(5);
		PageList adminlist=userSendcardService.getPageListBySql(ahqlQuery, false);
	
		
		request.setAttribute("adminlist", adminlist);
		request.setAttribute("SendCardpagelist", pagelist);
		request.setAttribute("type", type);
		return "weixin/idea/forum/forumlist";
		}
		return "";
	}
	
	/**
	 * 显示帖子详情
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "showSendCardsDetail")
	public String showSendCardsDetail(@RequestParam(required=true) String id,
			HttpServletRequest request){
		//String part = UserUtil.getToSessionString(request, WeixinRedirectUrl.PART);
		WeixinUserSendcardEntity entity = userSendcardService.get(WeixinUserSendcardEntity.class, id);
		Map<String, Object> map=new HashMap<String, Object>();
		HqlQuery hqlQuery=new HqlQuery("SELECT	date,username,usertitleimg,followcontnt,id,useropenid FROM weixin_user_follow WHERE sendcardid = '"+id+"' ORDER BY date DESC ", map);
		PageList followlist = userFollowService.getPageListBySql(hqlQuery, false);
		request.setAttribute("entity", entity);
		request.setAttribute("followlist", followlist);
		return "weixin/idea/forum/forumitemdetail";
	}

	private static HashMap<String, String> cache=new HashMap<String, String>();
	/**
	 * 发布新帖子
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "addshowSendCard",method=RequestMethod.POST)
	@ResponseBody
	public String addshowSendCard(WeixinUserSendcardEntity entity,
			HttpServletRequest request){
		try {
			if(request.getSession().getAttribute("validateCode").toString().equals(request.getParameter("vaildcode"))){
				String part = UserUtil.getToSessionString(request, WeixinRedirectUrl.PART);
				String openid = UserUtil.getToSessionString(request, WeixinRedirectUrl.OPENID);
				if(!StringUtil.isEmpty(part)&&!StringUtil.isEmpty(openid)){
					if(entity!=null){
						entity.setOpenid(openid);
						entity.setPart(part);
						entity.setUpdateDate(new Date());
						entity.setOrderid("0");
						userSendcardService.save(entity);
						return "forumController.do?showSendCards";
					}
				}	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}
		return "";
	}
	/**
	 * 回复帖子
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "callbackfollowCard")
	public String callbackfollowCard(WeixinUserFollowEntity entity,
			HttpServletRequest request){
		String openid = UserUtil.getToSessionString(request, WeixinRedirectUrl.OPENID);
		if(!StringUtil.isEmpty(openid)&&entity!=null){
		SyvscribeusersEntity syvscribeusersEntity = syvscribeusersService.get(SyvscribeusersEntity.class, openid);
		String cardid=request.getParameter("cardid");
		String cardcontent=request.getParameter("cardcontent");
		entity.setSendcardid(cardid);
		entity.setFollowcontnt(cardcontent);
		entity.setDate(new Date());
		entity.setUseropenid(openid);
		entity.setUsername(syvscribeusersEntity.getNickname());
		entity.setUsertitleimg(syvscribeusersEntity.getHeadimgurl());
		userFollowService.save(entity);
		return "redirect:forumController.do?showSendCardsDetail&id="+cardid;
		}
		return "";
	}
	/**
	 * 删除帖子
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "deleteSendCard",method=RequestMethod.POST)
	@ResponseBody
	public String deleteSendCard(@RequestParam(required=true)String id,
			HttpServletRequest request){
		//String part = UserUtil.getToSessionString(request, WeixinRedirectUrl.PART);
		try {
			//删除帖子有关的回复
			userFollowService.updateBySqlString("delete from weixin_user_follow WHERE sendcardid ='"+id+"'");
			//删除帖子
			userSendcardService.deleteEntityById(WeixinUserSendcardEntity.class, id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	/**
	 * 删除回复
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "deletefollowCard",method=RequestMethod.POST)
	@ResponseBody
	public String deletefollowCard(@RequestParam(required=true)String id,
			HttpServletRequest request){
		//String part = UserUtil.getToSessionString(request, WeixinRedirectUrl.PART);
		try {
			//删除回复
			userFollowService.deleteEntityById(WeixinUserFollowEntity.class, id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	/**
	 * 根据类型构建sql
	 * @param type
	 * @param openid
	 * @return
	 */
	private String gettypeString(Integer type, String openid) {
		String order="";
		switch (type) {
		case 1:
			order=" ORDER BY followcount DESC  ";
			break;
		case 2:
			order=" ORDER BY card.update_date DESC  ";
			break;
		case 3:
			order="AND card.openid='"+openid+"' ORDER BY card.update_date DESC ";
			break;
		}
		return order;
	}
	
}
