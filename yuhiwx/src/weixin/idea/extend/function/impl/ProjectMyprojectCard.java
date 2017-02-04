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

public class ProjectMyprojectCard implements KeyServiceI {

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return "我的工程单";
	}

	@Override
	public String excute(String content, TextMessageResp defaultMessage,
			HttpServletRequest request) {
		OwnerFacade oservice =(OwnerFacade)ApplicationContextUtil.getContext().getBean("ownerFacade");
		ResourceBundle bundler = ResourceBundle.getBundle("sysConfig");
		String openid = defaultMessage.getToUserName();
		JSONObject ownerid = null;
		try {
			ownerid = oservice.getOwnerObjByOpenid(openid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ownerid="";//测试工程人员
		List<Article> articleList = new ArrayList<Article>();
		if(ownerid!=null){// 判断访问的用户身份
			ProjectPersonFacade ppservice =(ProjectPersonFacade)ApplicationContextUtil.getContext().getBean("projectPersonFacade");
			String part=null;
			try {
				part = ppservice.isBudding(openid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//查询用户是否绑定		
			if(StringUtils.isNotEmpty(part)){ //工程人员
				Article article = new Article();
				article.setTitle("我的工程单(工程人员)");
				article.setDescription("点击查看工程单详情");
				article.setPicUrl("http://img1.imgtn.bdimg.com/it/u=1590491615,4155818826&fm=11&gp=0.jpg");
				// 此userid后期需要通过高级接口获取到微信帐号，此处先以加密后的ID为参数进行传递
//				article.setUrl(bundler.getString("domain")
//						+ "/projectCardController.do?getMyProjectList&openid="
//						+ openid);
				article.setUrl(bundler.getString("domain")
						+ "/projectCardController.do?goProjectListPage&type=10012&openid="
						+ openid);
				articleList.add(article);
			}else{ //未绑定
				defaultMessage.setContent("请先在个人中心->身份绑定");
				return MessageUtil.textMessageToXml(defaultMessage);
			}
		}else{ //业主
			Article article = new Article();
			article.setTitle("我的工程单(业主)");
			article.setDescription("点击查看工程单详情");
			article.setPicUrl("http://img1.imgtn.bdimg.com/it/u=1590491615,4155818826&fm=11&gp=0.jpg");
			// 此userid后期需要通过高级接口获取到微信帐号，此处先以加密后的ID为参数进行传递
//			article.setUrl(bundler.getString("domain")
//					+ "/projectCardController.do?getMyProjectList&openid="
//					+ openid);
			article.setUrl(bundler.getString("domain")
					+ "/projectCardController.do?goProjectListPage&ownerid="
					+ ownerid+"&openid="+openid+"&type=10011");
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
