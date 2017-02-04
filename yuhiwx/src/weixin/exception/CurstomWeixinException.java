package weixin.exception;
/**
 * 微信自定义异常
 * @author 李森林
 * 描述一下：
 * 2016-6-18  上午11:29:36
 */
public class CurstomWeixinException extends Exception{

	
	public CurstomWeixinException() {
		// TODO Auto-generated constructor stub
	}
	public CurstomWeixinException(int int1, String string) {
		// TODO Auto-generated constructor stub
		super("微信出错："+"错误号("+int1+")错误信息（"+string+")");
	}
	
}

