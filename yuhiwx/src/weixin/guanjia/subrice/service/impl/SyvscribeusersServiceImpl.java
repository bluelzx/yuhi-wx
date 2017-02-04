package weixin.guanjia.subrice.service.impl;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.subrice.service.SyvscribeusersServiceI;
import net.sf.json.JSONObject;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.StringUtil;

import weixin.guanjia.subrice.entity.SyvscribeusersEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.io.Serializable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service("syvscribeusersService")
@Transactional
public class SyvscribeusersServiceImpl extends CommonServiceImpl implements SyvscribeusersServiceI {

	@Resource
	private   WeixinAccountServiceI weixinAccountService; 
	
	public List<String[]> getPart(){
		List<String[]> list = getSession().createSQLQuery("select id,part from weixin_shop_business").list();
		return list;
	}
	
	 public  String getSendOpenid(HttpServletRequest req, String code){
		 List<WeixinAccountEntity> list = weixinAccountService.getList(WeixinAccountEntity.class);
		 String openid;
			if(list!=null&&list.size()>0){   //是否存在公众号列表
				for (WeixinAccountEntity weixinAccountEntity : list) {
					JSONObject httpRequest = weixin.guanjia.core.util.WeixinUtil.httpRequest("https://api.weixin.qq.com/sns/oauth2/access_token?" +
							"appid="+weixinAccountEntity.getAccountappid()+"&secret="+weixinAccountEntity.getAccountappsecret()+"&code="+code+"&grant_type=authorization_code", "GET", "");
					openid=httpRequest.getString("openid");
					if(!StringUtil.isEmpty(openid)){
						System.out.println("公众号id："+weixinAccountEntity.getAccountappid()+"用户执行了绑定认证");
						return openid;
					}
				}
				return null;
			}else{
				System.out.println("无绑定公众号");
				return null;
			}
	 }
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((SyvscribeusersEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((SyvscribeusersEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((SyvscribeusersEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(SyvscribeusersEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(SyvscribeusersEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(SyvscribeusersEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,SyvscribeusersEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{city}",String.valueOf(t.getCity()));
 		sql  = sql.replace("#{headimgurl}",String.valueOf(t.getHeadimgurl()));
 		sql  = sql.replace("#{language}",String.valueOf(t.getLanguage()));
 		sql  = sql.replace("#{country}",String.valueOf(t.getCountry()));
 		sql  = sql.replace("#{groupid}",String.valueOf(t.getGroupid()));
 		sql  = sql.replace("#{nickname}",String.valueOf(t.getNickname()));
 		sql  = sql.replace("#{province}",String.valueOf(t.getProvince()));
 		sql  = sql.replace("#{remark}",String.valueOf(t.getRemark()));
 		sql  = sql.replace("#{subscribetime}",String.valueOf(t.getSubscribetime()));
 		sql  = sql.replace("#{subscribe}",String.valueOf(t.getSubscribe()));
 		sql  = sql.replace("#{sex}",String.valueOf(t.getSex()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}