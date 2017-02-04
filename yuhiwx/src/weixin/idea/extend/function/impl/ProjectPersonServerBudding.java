package weixin.idea.extend.function.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.util.ApplicationContextUtil;

import weixin.guanjia.core.entity.message.resp.Article;
import weixin.guanjia.core.entity.message.resp.NewsMessageResp;
import weixin.guanjia.core.entity.message.resp.TextMessageResp;
import weixin.guanjia.core.util.MessageUtil;
import weixin.idea.extend.function.KeyServiceI;

import com.yuhi.wechar.facade.ProjectPersonFacade;
/**
 * 工程人员绑定
 */
public class ProjectPersonServerBudding implements KeyServiceI  {
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return "工程人员绑定";
	}

	@Override
	public String excute(String content, TextMessageResp defaultMessage,
			HttpServletRequest request) {
		ProjectPersonFacade service =(ProjectPersonFacade)ApplicationContextUtil.getContext().getBean("projectPersonFacade");
		
		//获取访问的用户
		String openid = defaultMessage.getToUserName();
		ResourceBundle bundler = ResourceBundle.getBundle("sysConfig");
		List<Article> articleList = new ArrayList<Article>();
		String part = null;
		try {
			part = service.isBudding(openid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//查询用户是否绑定		
		if(StringUtils.isNotEmpty(openid)&&StringUtils.isNotEmpty(part)){
			//已经绑定
//			defaultMessage.setContent("工程人员您好，你的账户已经绑定！");
//			return MessageUtil.textMessageToXml(defaultMessage);
			Article article = new Article();
			article.setTitle("个人信息");
			article.setDescription("工程人员绑定成功,点击可修改个人信息！");
			article.setPicUrl("http://pic2.ooopic.com/11/37/93/41bOOOPIC88.jpg");
			// 此userid后期需要通过高级接口获取到微信帐号，此处先以加密后的ID为参数进行传递
			article.setUrl(bundler.getString("domain")+"/projectPersonController.do?showinfoorupdate&openid="+openid);
			articleList.add(article);
		}else{
			Article article = new Article();
			article.setTitle("信息绑定");
			article.setDescription("新工程人员需要进行绑定");
			article.setPicUrl("http://pic2.ooopic.com/11/37/93/41bOOOPIC88.jpg");
			// 此userid后期需要通过高级接口获取到微信帐号，此处先以加密后的ID为参数进行传递
			article.setUrl(bundler.getString("domain")+"/projectPersonController.do?ppb&openid="+openid);
			articleList.add(article);
		}
		NewsMessageResp newsMessage = new NewsMessageResp();
		newsMessage.setToUserName(defaultMessage.getToUserName());
		newsMessage.setFromUserName(defaultMessage.getFromUserName());
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		return MessageUtil.newsMessageToXml(newsMessage);
	}
	

}
