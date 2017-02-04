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

import com.alibaba.fastjson.JSONObject;
import com.yuhi.wechar.facade.OwnerFacade;
import com.yuhi.wechar.facade.ProjectPersonFacade;
/**
 * 业主绑定
 */
public class OwnerServerBudding implements KeyServiceI  {
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return "业主绑定";
	}

	@Override
	public String excute(String content, TextMessageResp defaultMessage,
			HttpServletRequest request) {
		OwnerFacade service =(OwnerFacade)ApplicationContextUtil.getContext().getBean("ownerFacade");
		ProjectPersonFacade ppservice =(ProjectPersonFacade)ApplicationContextUtil.getContext().getBean("projectPersonFacade");
		//获取访问的用户
		String openid = defaultMessage.getToUserName();
		List<Article> articleList = new ArrayList<Article>();
		ResourceBundle bundler = ResourceBundle.getBundle("sysConfig");
		String part = null;
		try {
			part = ppservice.isBudding(openid);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//part="";//测试业主
		if(StringUtils.isNotEmpty(openid)&&StringUtils.isNotEmpty(part)){
			//已经绑定工程人員
			defaultMessage.setContent("工程人員您好，你的账户已经绑定了工程人員不能再次綁定業主！");
			return MessageUtil.textMessageToXml(defaultMessage);
		}else{
			//查询用户是否绑定		
			try {
				JSONObject obj = service.isBidding(openid);
				if(obj!=null&&StringUtils.isNotEmpty(obj.getString("id"))){
					//已经绑定
					Article article = new Article();
					article.setTitle("个人信息");
					article.setPicUrl("http://img0.bbs.szhome.com/uploadimages/2016/01/0108093336.jpg");
					article.setDescription("业主绑定成功,点击可修改个人信息！");
					// 此userid后期需要通过高级接口获取到微信帐号，此处先以加密后的ID为参数进行传递
					article.setUrl(bundler.getString("domain")+"/ownerController.do?ob&openid="+openid);
					articleList.add(article);
				}else{
					Article article = new Article();
					article.setTitle("信息绑定");
					article.setDescription("新业主需要进行绑定");
					article.setPicUrl("http://img1.gtimg.com/nb_house/pics/hv1/122/43/17/1116512.jpg");
					// 此userid后期需要通过高级接口获取到微信帐号，此处先以加密后的ID为参数进行传递
//			article.setUrl(bundler.getString("domain")+"/webpage/weixin/idea/project/Ownerbudding.jsp?openid="+openid);
					article.setUrl(bundler.getString("domain")+"/ownerController.do?ob&openid="+openid);
					articleList.add(article);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
