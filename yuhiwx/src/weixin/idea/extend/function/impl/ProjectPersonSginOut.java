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
import weixin.util.WeixinRedirectUrl;

import com.alibaba.fastjson.JSONObject;
import com.buss.project.entity.ProjectSignEntity;
import com.yuhi.wechar.facade.OwnerFacade;
import com.yuhi.wechar.facade.ProjectPersonFacade;
import com.yuhi.wechar.facade.ProjectSignFacade;
/**
 * 工程人员签退
 * @author 李森林
 *
 */
public class ProjectPersonSginOut implements KeyServiceI  {
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return "工程人员签退";
	}

	@Override
	public String excute(String content, TextMessageResp defaultMessage,
			HttpServletRequest request) {
		ProjectPersonFacade serviceI =(ProjectPersonFacade)ApplicationContextUtil.getContext().getBean("projectPersonFacade");
		ProjectSignFacade service =(ProjectSignFacade)ApplicationContextUtil.getContext().getBean("projectSignFacade");
		String openid = defaultMessage.getToUserName();
		String part = null;
		try {
			part = serviceI.isBudding(openid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(StringUtils.isNotEmpty(openid)&&StringUtils.isNotEmpty(part)){
			int errorcode = service.isExitsSignHistory(2,openid);
			switch (errorcode) {
			case WeixinRedirectUrl.SIGN_OK:
				newThreadTosave(service,openid,part);
				defaultMessage.setContent("元海物业提醒您:签退成功");
				break;
			case WeixinRedirectUrl.NO_SIGN:
				defaultMessage.setContent("元海物业提醒您:请在签退之前先签到");
				break;
			case WeixinRedirectUrl.REPLACE_SIGN:
				defaultMessage.setContent("元海物业提醒您:请勿重复签退");
				break;
			case WeixinRedirectUrl.SIGN_ERROR:
				defaultMessage.setContent("元海物业提醒您:服务器异常,请联系管理员");
				break;
			}
			return MessageUtil.textMessageToXml(defaultMessage);
		}
		OwnerFacade Ownerservice =(OwnerFacade)ApplicationContextUtil.getContext().getBean("ownerService");
		//查询用户是否存在发表记录
		try {
			JSONObject obj=Ownerservice.getOwnerObjByOpenid(openid);
			if(obj!=null&&StringUtils.isNotEmpty(obj.getString("id"))){
				defaultMessage.setContent("元海物业提醒您:业主不能使用签退功能");
				return MessageUtil.textMessageToXml(defaultMessage);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResourceBundle bundler = ResourceBundle.getBundle("sysConfig");
		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("信息绑定");
		article.setDescription("工程人员需要进行绑定");
		// 此userid后期需要通过高级接口获取到微信帐号，此处先以加密后的ID为参数进行传递
		article.setUrl(bundler.getString("domain")+"/projectPersonController.do?ppb&openid="+openid);
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
	
	
	
	//防止线程阻塞
	public void newThreadTosave(final ProjectSignFacade service,final String openid,final String part){
		new Thread(
			 new Runnable() {
				public void run() {
					JSONObject entity=new JSONObject();
					entity.put("openid",openid);
					entity.put("type","2");
					entity.put("create_time",new Date());
					entity.put("part",part);
					try {
						service.save(entity);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
				}
			}
		).run();
		
	}
	
	

}
