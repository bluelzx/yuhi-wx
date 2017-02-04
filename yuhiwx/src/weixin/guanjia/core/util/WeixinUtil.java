package weixin.guanjia.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.web.multipart.MultipartFile;

import weixin.exception.CurstomWeixinException;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.core.entity.common.AccessToken;
import weixin.guanjia.core.entity.model.AccessTokenYw;
import weixin.guanjia.message.entity.Articles;
import weixin.util.WeiXinConstants;
import weixin.util.WeixinRedirectUrl;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.util.logging.Logger;

/**
 * 公众平台通用接口工具类
 * 
 * @author liuyq
 * @date 2013-08-09
 */
public class WeixinUtil {
	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	// 客服接口地址
	public static String send_message_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	// 获取用户openid列表
	public final static String get_userlist_url = "https://api.weixin.qq.com/cgi-bin/user/get";
	// 用户详细信息
	public final static String get_userlist_detail = "https://api.weixin.qq.com/cgi-bin/user/info";
	// 根据OpenID列表群发
	public final static String Send_mesGroup_byopenids = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=";
	//上传临时图片素材
	public final static String upload_images_url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=";
	//上传临时永久素材（除了发送图文信息的缩略图）
	public final static String upload_AlongSource_url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=";
	//用于发送图文消息的缩略图的上传
	public final static String upload_AlongSource_THUMB = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=";
	//删除永久素材
	public final static String delete_AlongSource_url = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=";
	//上传图文素材
	public final static String upload_PicOrText_url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=";
	// private static final ResourceBundle bundle =
	// ResourceBundle.getBundle("weixin");
	// jsapi的票据 https://api.weixin.qq.com/cgi-bin/ticket/getticket?
	public final static String get_jsapi_ticket = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	
	public final static String send_mes_template="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";//发送模板消息
	
	
	
	
	/**
	 * 发送模板消息
	 * @param systemService
	 * @param appid
	 * @param appsecret
	 * @param OpenId
	 * @param map
	 * @param templateid
	 * @param detailurl
	 * @return
	 */
	public static  JSONObject sendMainMesBytemplate(SystemService systemService,String appid, String appsecret,String OpenId, Map<String, String> map, String templateid, String detailurl) {
		AccessToken accessToken = WeixinUtil.getAccessToken(systemService,WeixinRedirectUrl.PROJECT_APPID,WeixinRedirectUrl.PROJECT_APPSECRET);
		String jsonstr="{\"touser\":\""+OpenId+"\","+
				"\"template_id\":\""+templateid+"\","+
				"\"url\":\""+detailurl+"\","+
				"\"topcolor\":\"#FF0000\","+
				"\"data\":{" +getdataForTemplateJson(map)+"}" +
				"}";
		JSONObject json = WeixinUtil.httpRequest(send_mes_template+accessToken.getToken(), "POST",jsonstr);
		return json;
		
	}
	/**
	 * 客户信息发送(用于工程派单推送)
	 * @param systemService
	 * @param appid
	 * @param appsecret
	 * @param request 
	 * @param OpenId 
	 * @param content 
	 * @param appid 
	 * @param appsecret 
	 * @param firstOpenId
	 * @return
	 */
	public static JSONObject sendMainMesByOpenid(SystemService systemService,String appid, String appsecret,String OpenId, String content) {
		JSONObject json=null;
		try {
	
			String url="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
			AccessToken accessToken = WeixinUtil.getAccessToken(systemService,appid, appsecret);
			json = WeixinUtil.httpRequest(url+accessToken.getToken(), "GET",
					"{\"touser\":\""+OpenId+"\",\"msgtype\":\"text\"," +
							"\"text\":{\"content\":\""+content+"\"} }");
			//{"errmsg":"ok","errcode":0}
			if(json.getString("errcode").equals("0")){
				//发送成功
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	
	
	
	/**
	 * 获取jsapi票据 jsapi_ticket 的有效期为 7200 秒，开发者必须全局缓存 jsapi_ticket ，防止超过调用频率。
	 * 
	 * @param systemService
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public static String getjsapiTicket(SystemService systemService,
			String appid, String appsecret) {
		// http://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=ACCESS_TOKEN
		AccessToken accessToken = WeixinUtil.getAccessToken(systemService,
				appid, appsecret);
		JSONObject json = WeixinUtil.httpRequest(get_jsapi_ticket, "GET",
				"access_token=" + accessToken.getToken() + "&type=jsapi");
		// {
		// errcode: 0,
		// errmsg: "ok",
		// ticket:
		// "kgt8ON7yVITDhtdwci0qec_YP4bbVfmkI7JNX_XjoGFidzHNFwmEDHVhes1M0VKIWjWEo2pJKSnl2mvq4zGY9g",
		// expires_in: 7200
		// }
		return json.getString("ticket");// TODO
	}

	/**
	 * 获取用户详细信息
	 * 
	 * @param systemService
	 * @param appid
	 * @param appsecret
	 * @param firstOpenId
	 * @return
	 */
	public static JSONObject getUserDetail(SystemService systemService,
			String appid, String appsecret, String OpenId) {

		AccessToken accessToken = WeixinUtil.getAccessToken(systemService,
				appid, appsecret);
		JSONObject json = WeixinUtil.httpRequest(get_userlist_detail, "GET",
				"access_token=" + accessToken.getToken()
						+ "&lang=zh_CN&openid=" + OpenId);
		return json;
	}

	
	
	/**
	 * 根据openid用户组-消息群发接口 * @param systemService
	 * 
	 * @param appid
	 * @param appsecret
	 * @param firstOpenId
	 * @return
	 */
	public static JSONObject SendMesToUsers(SystemService systemService,
								String appid, String appsecret,String[] openids,
										int type,String contentOrMediaid) {
		String openidjson = JSON.toJSONString(openids);
		AccessToken accessToken = WeixinUtil.getAccessToken(systemService,
				appid, appsecret);
		StringBuffer requeststr=new StringBuffer("{ \"touser\":[\"oFsDqwh2EA5SAA2HL91ySvk7TO9c\","
				+ "\"oFsDqwi4j7GDdLU2_onXkeqVMCW0\","
				+ "\"oFsDqws42N-91MzkWWId-gkleFOI\"],");
		switch (type) {
		case 0:
			requeststr.append("\"msgtype\": \"text\", \"text\": { \"content\": \""+contentOrMediaid+"\"} }");
			break;
		case 1:
			requeststr.append("\"msgtype\": \"image\",  \"image\":{\"media_id\":\""+contentOrMediaid+"\"} }");
			break;
		case 2:
			requeststr.append("\"msgtype\": \"mpnews\",  \"mpnews\":{\"media_id\":\""+contentOrMediaid+"\"} }");
			break;
		default:
			break;
		}
		JSONObject json = WeixinUtil.httpRequest(Send_mesGroup_byopenids
				+ accessToken.getToken(), "POST",requeststr.toString());
		//
		// &type=image&offset=0&count=20
		// JSONObject json =
		// WeixinUtil.httpRequest("https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+accessToken.getToken(),
		// "POST","" +
		// "{\"type\":\"image\",\"offset\":0,\"count\":20 }");

		return json;
	}



	/**
	 * 上传临时图片素材 * @param systemService
	 * 
	 * @param appid
	 * @param appsecret
	 * @param file
	 * @param firstOpenId
	 * @return
	 */
	public static JSONObject uploadtemporarypic(SystemService systemService,
			String appid, String appsecret, File file) {
		AccessToken accessToken = WeixinUtil.getAccessToken(systemService,
				appid, appsecret);
		String result = postMedia(upload_images_url + accessToken.getToken() + "&type=image",
				file);
		return JSONObject.fromObject(result);
	}

	/**
	 * 新增永久素材
	 * 
	 * @param systemService
	 * @param appid
	 * @param appsecret
	 * @param file
	 * @return
	 * @throws CurstomWeixinException
	 * @Success { "media_id":MEDIA_ID, "url":URL }
	 */
	public static JSONObject uploadAlongSource(SystemService systemService,
			String appid, String appsecret, File file, String AddSourceType)
			throws CurstomWeixinException {
		 String url=upload_AlongSource_url;
		if("thumb".equalsIgnoreCase(AddSourceType))
			 url = upload_AlongSource_THUMB;
		AccessToken accessToken = WeixinUtil.getAccessToken(systemService,
				appid, appsecret);
		String result = postMedia(url + accessToken.getToken() + "&type="
				+ AddSourceType, file);
		JSONObject callbackSource = JSONObject.fromObject(result);
		if (callbackSource.get("errcode") != null) {
			throw new CurstomWeixinException(callbackSource.getInt("errcode"),
					callbackSource.getString("errmsg"));
		}
		return callbackSource;
	}

	/**
	 * 删除永久素材
	 * @param systemService
	 * @param appid
	 * @param appsecret
	 * @param media_id
	 * @return
	 * @throws CurstomWeixinException
	 * @success {
    "errcode":ERRCODE,
    "errmsg":ERRMSG
}
0000
	 */
	public static JSONObject DeleteAlongpic(SystemService systemService,
			String appid, String appsecret, String media_id)
			throws CurstomWeixinException {
		
		AccessToken accessToken = WeixinUtil.getAccessToken(systemService,
				appid, appsecret);
		JSONObject callbackSource = WeixinUtil.httpRequest(delete_AlongSource_url+ accessToken.getToken(), "POST","{"+
							"\"media_id\":\""+media_id+"\"}");
		if (callbackSource.getInt("errcode")!= 0) {
			throw new CurstomWeixinException(callbackSource.getInt("errcode"),
					callbackSource.getString("errmsg"));
		}
		return callbackSource;
	}

	

	
	/**
	 * 上传图文信息 * @param systemService
	 * @param appid
	 * @param appsecret
	 * @param file
	 * @param firstOpenId
	 * @return 
	 * @success
	 * 			{
  				"type":"news",
   				"media_id":"CsEf3ldqkAYJAU6EJeIkStVDSvffUJ54vqbThMgplD-VJXXof6ctX5fI6-aYyUiQ",
   				"created_at":1391857799
				}
	 * 
	 */
	public static JSONObject uploadpicAndTextUtil(SystemService systemService,
			String appid, String appsecret,List<Articles> articles) {
		if(articles!=null&&articles.size()>0){
			
			AccessToken accessToken = WeixinUtil.getAccessToken(systemService,
					appid, appsecret);
			String json="{\"articles\":"+JSON.toJSONString(articles)+"}";
			JSONObject result = httpRequest(upload_PicOrText_url + accessToken.getToken(),"POST",json);
			return JSONObject.fromObject(result);
		}
			return null;
	}

	/**
	 * 获取用户openid列表 * @param systemService
	 * 
	 * @param appid
	 * @param appsecret
	 * @param firstOpenId
	 * @return
	 */
	public static JSONObject getUserList(SystemService systemService,
			String appid, String appsecret) {

		AccessToken accessToken = WeixinUtil.getAccessToken(systemService,
				appid, appsecret);
		JSONObject json = WeixinUtil.httpRequest(get_userlist_url, "GET",
				"access_token=" + accessToken.getToken());
		return json;
	}
	/**
	 * 组装模板参数json
	 * @param map
	 * @return
	 */
	private static  String getdataForTemplateJson(Map<String, String> map){
		StringBuffer sb=new StringBuffer();
		for (String key : map.keySet()) {
			sb.append("\""+key+"\": {"+
                   "\"value\":\""+map.get(key)+"\","+
                   "\"color\":\"#173177\""+
                   "},");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
			// jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			org.jeecgframework.core.util.LogUtil
					.info("Weixin server connection timed out.");
		} catch (Exception e) {
			org.jeecgframework.core.util.LogUtil.info("https request error:{}"
					+ e.getMessage());
		}
		return jsonObject;
	}
	
	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static String httpRequestByStr(String requestUrl,
			String requestMethod, String outputStr) {
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			// jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			org.jeecgframework.core.util.LogUtil
					.info("Weixin server connection timed out.");
		} catch (Exception e) {
			org.jeecgframework.core.util.LogUtil.info("https request error:{}"
					+ e.getMessage());
		}
		return buffer.toString();
	}
	
	/**
	 * 提交媒体文件
	 * 
	 * @param url
	 *            提交的网址
	 * @param file
	 *            提交的文件
	 * @return 返回的结果
	 */
	public static String postMedia(String url, File file) {
		String result = "";
		PostMethod filePost = new PostMethod(url);
		Part[] parts = new Part[1];
		try {
			// 设定参数名称和值，类似form表单中的<input name="filename” type="file" />
			parts[0] = new FilePart("media", file);
		} catch (FileNotFoundException e) {
			System.err.println("发送 POST请求出现异常！\n" + e.getMessage());
		}

		// 设置多媒体参数，作用类似form表单中的enctype="multipart/form-data" ，
		filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost
				.getParams()));
		org.apache.commons.httpclient.HttpClient clients = new org.apache.commons.httpclient.HttpClient();

		int status = 0;

		try {
			status = clients.executeMethod(filePost);
		} catch (HttpException e) {
			System.err.println("发送 POST请求出现异常！\n" + e.getMessage());
		} catch (IOException e) {
			System.err.println("发送 POST请求出现异常！\n" + e.getMessage());
		}

		try {
			result = StringUtil.convertStreamToString(filePost
					.getResponseBodyAsStream());
			if (status != 200) {
				System.err.println("发送 POST请求出现异常！\n");
			}
		} catch (Exception e) {
			System.err.println("发送 POST请求出现异常！\n" + e.getMessage());
		}
		return result;
	}

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static AccessToken getAccessToken(SystemService systemService,
			String appid, String appsecret) {
		// 第三方用户唯一凭证
		// String appid = bundle.getString("appId");
		// // 第三方用户唯一凭证密钥
		// String appsecret = bundle.getString("appSecret");

		AccessTokenYw accessTocken = getRealAccessToken(systemService);

		if (accessTocken != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date end = new java.util.Date();
			java.util.Date start = new java.util.Date(accessTocken.getAddTime()
					.getTime());
			if (end.getTime() - accessTocken.getAddTime().getTime() > accessTocken
					.getExpires_in() * 1000) {
				AccessToken accessToken = null;
				String requestUrl = access_token_url.replace("APPID", appid)
						.replace("APPSECRET", appsecret);
				JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
				// 如果请求成功
				if (null != jsonObject) {
					try {
						accessToken = new AccessToken();
						accessToken.setToken(jsonObject
								.getString("access_token"));
						accessToken.setExpiresIn(jsonObject
								.getInt("expires_in"));
						// 凭证过期更新凭证
						AccessTokenYw atyw = new AccessTokenYw();
						atyw.setId(accessTocken.getId());
						atyw.setExpires_in(jsonObject.getInt("expires_in"));
						atyw.setAccess_token(jsonObject
								.getString("access_token"));
						updateAccessToken(atyw, systemService);
					} catch (Exception e) {
						accessToken = null;
						// 获取token失败
						String wrongMessage = "获取token失败 errcode:{} errmsg:{}"
								+ jsonObject.getInt("errcode")
								+ jsonObject.getString("errmsg");
						org.jeecgframework.core.util.LogUtil.info(wrongMessage);
					}
				}
				return accessToken;
			} else {

				AccessToken accessToken = new AccessToken();
				accessToken.setToken(accessTocken.getAccess_token());
				accessToken.setExpiresIn(accessTocken.getExpires_in());
				return accessToken;
			}
		} else {

			AccessToken accessToken = null;
			String requestUrl = access_token_url.replace("APPID", appid)
					.replace("APPSECRET", appsecret);
			JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
			// 如果请求成功
			if (null != jsonObject) {
				try {
					accessToken = new AccessToken();
					accessToken.setToken(jsonObject.getString("access_token"));
					accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
					AccessTokenYw atyw = new AccessTokenYw();
					atyw.setExpires_in(jsonObject.getInt("expires_in"));
					atyw.setAccess_token(jsonObject.getString("access_token"));
					atyw.setAddTime(new Timestamp(new Date().getTime()));
					saveAccessToken(atyw, systemService);

				} catch (Exception e) {
					accessToken = null;
					// 获取token失败
					String wrongMessage = "获取token失败 errcode:{} errmsg:{}"
							+ jsonObject.getInt("errcode")
							+ jsonObject.getString("errmsg");
					org.jeecgframework.core.util.LogUtil.info(wrongMessage);
				}
			}
			return accessToken;
		}
	}

	/**
	 * 从数据库中读取凭证
	 * 
	 * @return
	 */
	public static AccessTokenYw getRealAccessToken(SystemService systemService) {
		List<AccessTokenYw> accessTockenList = systemService
				.findByQueryString("FROM AccessTokenYw");
		return accessTockenList != null && accessTockenList.size() > 0 ? accessTockenList
				.get(0) : null;
	}

	/**
	 * 保存凭证
	 * 
	 * @return
	 */
	public static void saveAccessToken(AccessTokenYw accessTocken,
			SystemService systemService) {
		systemService.save(accessTocken);
	}

	/**
	 * 更新凭证
	 * 
	 * @return
	 */
	public static void updateAccessToken(AccessTokenYw accessTocken,
			SystemService systemService) {
		String sql = "UPDATE WEIXIN_ACCESSTOKEN SET ACCESS_TOKEN='"
				+ accessTocken.getAccess_token() + "',EXPIRES_IB="
				+ accessTocken.getExpires_in() + ",ADDTIME=NOW() WHERE ID='"
				+ accessTocken.getId() + "'";
		systemService.updateBySqlString(sql);
	}

	/**
	 * 编码
	 * 
	 * @param bstr
	 * @return String
	 */
	public static String encode(byte[] bstr) {
		return new sun.misc.BASE64Encoder().encode(bstr);
	}

	/**
	 * 解码
	 * 
	 * @param str
	 * @return string
	 */
	public static byte[] decode(String str) {

		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bt;

	}

}