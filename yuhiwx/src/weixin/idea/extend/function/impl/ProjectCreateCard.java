package weixin.idea.extend.function.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.util.ApplicationContextUtil;

import weixin.guanjia.core.entity.message.resp.Article;
import weixin.guanjia.core.entity.message.resp.NewsMessageResp;
import weixin.guanjia.core.entity.message.resp.TextMessageResp;
import weixin.guanjia.core.util.MessageUtil;
import weixin.idea.extend.function.KeyServiceI;

import com.alibaba.fastjson.JSONObject;
import com.buss.project.entity.OwnerEntity;
import com.yuhi.wechar.facade.OwnerFacade;
import com.yuhi.wechar.facade.ProjectPersonFacade;
public class ProjectCreateCard implements KeyServiceI  {

	

	
	@Override
	public String getKey() {
		return "业主发单";
	}

	@Override
	public String excute(String content, TextMessageResp defaultMessage,
			HttpServletRequest request) {
		ProjectPersonFacade projectPersonService =(ProjectPersonFacade)ApplicationContextUtil.getContext().getBean("projectPersonFacade");
		OwnerFacade ownerService=(OwnerFacade)ApplicationContextUtil.getContext().getBean("ownerFacade");
		
		//获取访问的用户
		String openid = defaultMessage.getToUserName();
		ResourceBundle bundler = ResourceBundle.getBundle("sysConfig");
//		//判断身份是工程人员还是业主
		JSONObject pplist = null;
		try {
			pplist = projectPersonService.getppObjByOpenid(openid);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(pplist!=null){
			defaultMessage.setContent("元海物业提醒您:工程人员不可发单");
			return MessageUtil.textMessageToXml(defaultMessage);
		}
		JSONObject entity = null;
		try {
			entity = ownerService.getOwnerObjByOpenid(openid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(entity!=null){
			List<Article> articleList = new ArrayList<Article>();
			Article article = new Article();
			article.setTitle("工程单发起");
			article.setDescription("需要完善工程单详情");
			article.setPicUrl("http://pic85.huitu.com/res/20160826/208798_20160826225324934200_1.jpg");
			// 此userid后期需要通过高级接口获取到微信帐号，此处先以加密后的ID为参数进行传递
//			article.setUrl(bundler.getString("domain")+"/webpage/weixin/idea/project/createProjectCard.jsp?openid="+openid+"&ownerid="+s[0]+"&phone="+s[1]);
			article.setUrl(bundler.getString("domain")+"/webpage/weixin/idea/project/template/ProjectCardCreate.jsp?openid="+openid+"&ownerid="+entity.getString("id")
					+"&phone="+entity.getString("phone")+"&part="+entity.getString("part"));
			articleList.add(article);
			NewsMessageResp newsMessage = new NewsMessageResp();
			newsMessage.setToUserName(defaultMessage.getToUserName());
			newsMessage.setFromUserName(defaultMessage.getFromUserName());
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			return MessageUtil.newsMessageToXml(newsMessage);
		}
		defaultMessage.setContent("元海物业提醒您:请先在个人中心->选择对应的身份进行绑定");
		return MessageUtil.textMessageToXml(defaultMessage);
	}

}
