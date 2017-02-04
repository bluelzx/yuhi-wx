package org.jeecgframework.web.system.controller.core;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.datasource.DataSourceContextHolder;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.util.WeiXinConstants;

@Controller
@RequestMapping
public class CasIndexController {
	
	private Logger log = Logger.getLogger(LoginController.class);
	private SystemService systemService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	private UserService userService;
	private String message = null;
	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@Autowired
	public void setUserService(UserService userService) {

		this.userService = userService;
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String login(ModelMap modelMap,HttpServletRequest request,String ticket) {
		DataSourceContextHolder.setDataSourceType(DataSourceType.dataSource_jeecg);
		String roles = "";
		try{
			AttributePrincipal principal = (AttributePrincipal)request.getUserPrincipal();  
			if(principal!=null){
				Map<String, Object> attributes = principal.getAttributes(); 
				String userid=(String)attributes.get("userid");
				TSUser u=userService.get(TSUser.class, userid);
				if(u!=null){
					message = "用户: " + u.getUserName() + "["
		                       + u.getTSDepart().getDepartname() + "]" + "登录成功";
					ResourceUtil.CasSetSession(u);
					 // 添加登陆日志
				       systemService.addLog(message, Globals.Log_Type_LOGIN,
				               Globals.Log_Leavel_INFO);
				       WeixinAccountEntity  weixinAccountEntity = weixinAccountService.findLoginWeixinAccount();
				       request.getSession().setAttribute(WeiXinConstants.WEIXIN_ACCOUNT, weixinAccountEntity);
						List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", u.getId());

						for (TSRoleUser ru : rUsers) {
							TSRole role = ru.getTSRole();
							roles += role.getRoleName() + ",";
						}
						if (roles.length() > 0) {
							roles = roles.substring(0, roles.length() - 1);
						}
				        modelMap.put("roleName", roles);
				        modelMap.put("userName", u.getUserName());
						request.getSession().setAttribute("CKFinder_UserRole", "admin");
						// 默认风格
						String indexStyle = "shortcut";
						Cookie[] cookies = request.getCookies();
						for (Cookie cookie : cookies) {
							if (cookie == null || StringUtils.isEmpty(cookie.getName())) {
								continue;
							}
							if (cookie.getName().equalsIgnoreCase("JEECGINDEXSTYLE")) {
								indexStyle = cookie.getValue();
							}
						}
						// 要添加自己的风格，复制下面三行即可
						if (StringUtils.isNotEmpty(indexStyle)
								&& indexStyle.equalsIgnoreCase("bootstrap")) {
							return "main/bootstrap_main";
						}
						if (StringUtils.isNotEmpty(indexStyle)
								&& indexStyle.equalsIgnoreCase("shortcut")) {
							return "main/shortcut_main";
						}
						if (StringUtils.isNotEmpty(indexStyle)
								&& indexStyle.equalsIgnoreCase("sliding")) {
							return "main/sliding_main";
						}
						return "main/main";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "common/noAuth";
		
		
		
//		DataSourceContextHolder.setDataSourceType(DataSourceType.dataSource_jeecg);
//		TSUser user = ResourceUtil.getSessionUserName();
//		String roles = "";
//		if (user != null) {
//			WeixinAccountEntity  weixinAccountEntity = weixinAccountService.findLoginWeixinAccount();
//			request.getSession().setAttribute(WeiXinConstants.WEIXIN_ACCOUNT, weixinAccountEntity);
//			List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
//			for (TSRoleUser ru : rUsers) {
//				TSRole role = ru.getTSRole();
//				roles += role.getRoleName() + ",";
//			}
//			if (roles.length() > 0) {
//				roles = roles.substring(0, roles.length() - 1);
//			}
//            modelMap.put("roleName", roles);
//            modelMap.put("userName", user.getUserName());
//			request.getSession().setAttribute("CKFinder_UserRole", "admin");
//			// 默认风格
//			String indexStyle = "shortcut";
//			Cookie[] cookies = request.getCookies();
//			for (Cookie cookie : cookies) {
//				if (cookie == null || StringUtils.isEmpty(cookie.getName())) {
//					continue;
//				}
//				if (cookie.getName().equalsIgnoreCase("JEECGINDEXSTYLE")) {
//					indexStyle = cookie.getValue();
//				}
//			}
//			// 要添加自己的风格，复制下面三行即可
//			if (StringUtils.isNotEmpty(indexStyle)
//					&& indexStyle.equalsIgnoreCase("bootstrap")) {
//				return "main/bootstrap_main";
//			}
//			if (StringUtils.isNotEmpty(indexStyle)
//					&& indexStyle.equalsIgnoreCase("shortcut")) {
//				return "main/shortcut_main";
//			}
//			if (StringUtils.isNotEmpty(indexStyle)
//					&& indexStyle.equalsIgnoreCase("sliding")) {
//				return "main/sliding_main";
//			}
//			return "main/main";
//		} else {
//			return "login/login";
//		}

	}
	
	/**
	 * 退出系统
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout")
	@ResponseBody
	public String logout(HttpServletRequest request) {
		HttpSession session = ContextHolderUtils.getSession();
		TSUser user = ResourceUtil.getSessionUserName();
		systemService.addLog("用户" + user.getUserName() + "已退出",
				Globals.Log_Type_EXIT, Globals.Log_Leavel_INFO);
		session.invalidate();
		ClientManager.getInstance().removeClinet(session.getId());
		return "ok";
	}
	
}
