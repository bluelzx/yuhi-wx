package weixin.idea.extend.function.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.util.ApplicationContextUtil;

import freemarker.template.utility.StringUtil;

import weixin.carserver.service.WeixinCarCustomerServiceI;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.entity.message.resp.Article;
import weixin.guanjia.core.entity.message.resp.NewsMessageResp;
import weixin.guanjia.core.entity.message.resp.TextMessageResp;
import weixin.guanjia.core.util.MessageUtil;
import weixin.idea.extend.function.KeyServiceI;
import weixin.util.WeixinRedirectUrl;
/**
 * 客户绑定
 */
public class CurstomCarServerBudding implements KeyServiceI  {
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return "客户绑定";
	}

	@Override
	public String excute(String content, TextMessageResp defaultMessage,
			HttpServletRequest request) {
		WeixinCarCustomerServiceI service =(WeixinCarCustomerServiceI)ApplicationContextUtil.getContext().getBean("weixinCarCustomerService");
		
		//获取访问的用户
		String openid = defaultMessage.getToUserName();
		
		//查询用户是否绑定		
		if(!StringUtils.isEmpty(openid)&&service.isBudding(openid)){
			//已经绑定
			defaultMessage.setContent("客户您好，你的账户已经绑定！");
			return MessageUtil.textMessageToXml(defaultMessage);
		}
		ResourceBundle bundler = ResourceBundle.getBundle("sysConfig");
		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("信息绑定");
		article.setDescription("新客户需要进行绑定");
		// 此userid后期需要通过高级接口获取到微信帐号，此处先以加密后的ID为参数进行传递
		article.setUrl(bundler.getString("domain")+"/webpage/weixin/carserver/CarServerbudding.jsp?openid="+openid);
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
	

}
