package com.yuhi.wechar;

/**
 * 
 * @author 李森林
 * 描述一下：微信标识符统一管理
 * 2016-6-20  下午5:28:16
 */
public class WeiXinConstants {
	//查看用户列表
		public static final String GOGOODSLIST="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx23093572b097a654&redirect_uri=http%3a%2f%2f4y6b3ownuq.proxy.qqbrowser.cc%2fyuhiwx%2fshopMainController.do%3fgetGoodsList&response_type=code&scope=snsapi_base#wechat_redirect";
		//重定向用户绑定地址
		public static final String REDIRECTUSERBINGURL="https://open.weixin.qq.com/connect/oauth2/authorize?" +
								"appid=wxacc37c392cfe9916&" +
								"redirect_uri=http%3a%2f%2f4y6b3ownuq.proxy.qqbrowser.cc%2fyuhiwx%2fsyvscribeusersController.do%3fuserlocal" +
								"&response_type=code&scope=snsapi_base#wechat_redirect";
		public static final String OPENID="OPENID";
		public static final String PART="PART";
		
		
		/*素材类型*/
		public static final String IMAGE="IMAGE";
		public static final String VOICE="VOICE";
		public static final String VIDEO="VIDEO";
		public static final String THUMB="THUMB";
		public static final String NEWS="NEWS";
		/*签到类型*/
		public static final int REPLACE_SIGN=10056;
		public static final int NO_SIGN=10057;
		public static final int SIGN_ERROR=10058;
		public static final int SIGN_OK=10059;
		/*工程单修改*/
		public static final int GET_PROJECTCARD=10078;
		public static final int NOGET_PROJECTCARD=10079;
		public static final int FINISHED_PROJECTCARD=10080;
		public static final int START_PROJECRCARD=10081;
		public static final int EVALUATE_PROJECT=10082;
		/*定制模块需要在此说明是那个公众号的   
		 * PROJECT_APPID
		 * */
		public static final String PROJECT_APPID="wx2c73741d132b2564";
		public static final String PROJECT_APPSECRET="1a431765ca915e124022060289605bbe";
		/*暂无解决方案*/
		
		/*业务模板id
		 * */
		//签到，退
		public static final String MES_PS_SIGN="aGTopbrUjUy7U3Qmscqw7QTMqRBnO-tOHzE8qx9A7O0";
		//分配单业主提醒
		public static final String MES_PP_PARTPROJECT="-woV6UGMS1_j_sECHXUfr31j0NWSat8jPwSvNZDY5x0";
		//发单业主提醒
		public static final String MES_PP_PROJECTCREATE="9rHuRPZUmH8RBWK155K1w1WZSAy2QyJaBx3tj_7cW4M";
		//业主评价邀请（办理完成）
		public static final String MES_PP_PROJECTOBJECTION="mcejkzMVQiADMqt3zlRa17Ry9R8q8YGPKuTw0Iwvaw4";
		//业主评价之后工程人员通知
		public static final String MES_PS_PROJECTOBJECTION="h4v7tewQWlVnm9FL_FXpYx4i8v8c66GSRj9nWlLx730";
		//业主绑定提醒
		public static final String MES_PP_PROJECTBUDDING="CIHiH2drjv0VnpZIjkzQ6iXpzWFs5BnutwyhXfntIM4";
		//物业进展反馈提醒
		public static final String MES_PP_PROJECT="8Gf2U2QfTNzKtqIbL1vyoE2rPaw8o2059s6E17CoVOo";
		
		
		
}
