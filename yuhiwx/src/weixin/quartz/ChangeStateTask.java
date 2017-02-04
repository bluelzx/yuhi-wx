package weixin.quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jeecgframework.web.system.service.SystemService;
import org.jeewx.api.core.exception.WexinReqException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.entity.common.AccessToken;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.subrice.entity.SyvscribeusersEntity;
import weixin.guanjia.subrice.service.SyvscribeusersServiceI;
import weixin.util.WeiXinConstants;


/**
 * 定时任务
 * @author 李森林
 *
 */
@Component("changeStateTask")
public class ChangeStateTask{
	@Resource
	private SystemService systemService;
	@Resource
	private SyvscribeusersServiceI syvscribeusersServiceI;
	@Resource
	private  WeixinAccountServiceI weixinAccountService; 
	
	/**
	 * 定时更新用户
	 * 0 0 * * * ?  表示每次秒和分为0时触发一次（每小时一次）
	 * "@Scheduled"时spring提供的定时任务标签
	 */
	@Scheduled(cron = "0 0 * * * ?")
	public void doJob(){
		weixinAccountService.updateentityList(systemService, syvscribeusersServiceI);
	}
	
	

	
	/**
	 * 固定每分钟执行一次
	 */
	@Scheduled(fixedRate = 60*1000)
	public void doJob1(){
//		WeixinUtil.getjsapiTicket(systemService,"wx23093572b097a654","45bc41a56beda271cb3320c807ff6dda");
		
	}
	
	
	
	
	/**
	 * 上次任务结束后一分钟后再次执行
	 */
//	@Scheduled(fixedDelay = 60*1000)
//	public void doJob2(){
//		System.out.println(new Date() + "-----------------doJob2");
//	}
}