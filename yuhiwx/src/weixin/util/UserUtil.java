package weixin.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jeecgframework.core.util.StringUtil;
/**
 * 
 * @author 李森林
 * 描述一下：用户操作
 * 2016-6-20  下午5:28:01
 */
public class UserUtil {


	    /**
	     * 设置session
	     * @param key 
	     *
	     * @param session
	     * @param user
	     */
	    public static void saveToSession(HttpServletRequest request, Object info, String key) {
	    	request.getSession().setAttribute(key, info);
	    }
	    /**
	     * 删除session指定对象
	     * @param request
	     * @param key
	     */
	    public static void RemoveToSession(HttpServletRequest request, String key) {
	    	request.getSession().removeAttribute("key");
	    }
	    /**
	     * 获取session存放对象
	     * @param request
	     * @param key
	     * @return
	     */
	    public static Object getToSessionObject(HttpServletRequest request, String key){
	    	try {
				Object attribute = request.getSession().getAttribute(key);
				if(attribute!=null){
					return attribute;
				}
			} catch (Exception e) {
				return null;
			}
	    	return null;
	    }
	    /**
	     * 获取session存放字符串
	     * @param request
	     * @param key
	     * @return
	     */
	    public static String getToSessionString(HttpServletRequest request, String key){
	    	try {
				Object attribute = request.getSession().getAttribute(key);
				if(attribute!=null&&!StringUtil.isEmpty(attribute.toString())){
					return attribute.toString();
				}
			} catch (Exception e) {
				return null;
			}
	    	return null;
	    }
	    /**
	     * 销毁session会话
	     * @param request
	     */
	    public static void destroySession(HttpServletRequest request){
	    	request.getSession().invalidate();
	    }

	    

}
