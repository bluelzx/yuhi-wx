package weixin.guanjia.message.service.impl;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.exception.CurstomWeixinException;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.message.entity.Articles;
import weixin.guanjia.message.entity.WeixinAlongSourceEntity;
import weixin.guanjia.message.service.WeixinAlongSourceServiceI;
import weixin.util.WeixinRedirectUrl;

@Service("weixinAlongSourceService")
@Transactional
public class WeixinAlongSourceServiceImpl extends CommonServiceImpl implements WeixinAlongSourceServiceI {
	@Resource
	private SystemService systemService;

	
	/**
	 * 上传素材
	 */
	@Override
	public boolean uploadSource(String name,WeixinAccountEntity  weixinAccountEntity,File file, String type) {
		JSONObject mesCallback=new JSONObject();
		try {
			mesCallback = WeixinUtil.uploadAlongSource(systemService, weixinAccountEntity.getAccountappid(),
					weixinAccountEntity.getAccountappsecret(),file,type);
		} catch (CurstomWeixinException e) {
			e.printStackTrace();
			return false;
		}
		WeixinAlongSourceEntity entity=new WeixinAlongSourceEntity();
		entity.setCreateDate(new Date());
		if(WeixinRedirectUrl.THUMB.equalsIgnoreCase(type))
			entity.setMediaId(mesCallback.getString("thumb_media_id"));
		else entity.setMediaId(mesCallback.getString("media_id"));
		entity.setUrl(mesCallback.get("url")!=null?mesCallback.getString("url"):"");
		entity.setType(type);
		entity.setName(name);
		save(entity);
		return true;
	}
	/**
	 * 上传图文素材
	 * 	 * @return
	 */
	public boolean uploadNews(WeixinAccountEntity weixinAccountEntity,List<Articles> articles){
		JSONObject obj=	WeixinUtil.uploadpicAndTextUtil(systemService,weixinAccountEntity.getAccountappid(),
				weixinAccountEntity.getAccountappsecret(), articles);
		WeixinAlongSourceEntity entity=new WeixinAlongSourceEntity();
		entity.setCreateDate(new Date(obj.getLong("created_at")));
		entity.setMediaId(obj.getString("media_id"));
		entity.setType(obj.getString("type"));
		save(entity);
		return true;
	}
	
	
	
 	public <T> void delete(T entity,WeixinAccountEntity  weixinAccountEntity) {
 		try {
			WeixinUtil.DeleteAlongpic(systemService, weixinAccountEntity.getAccountappid(),
					weixinAccountEntity.getAccountappsecret(), ((WeixinAlongSourceEntity)entity).getMediaId());
		} catch (CurstomWeixinException e) {
			e.printStackTrace();
		}
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((WeixinAlongSourceEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((WeixinAlongSourceEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((WeixinAlongSourceEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinAlongSourceEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinAlongSourceEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinAlongSourceEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,WeixinAlongSourceEntity t){
 		sql  = sql.replace("#{media_id}",String.valueOf(t.getMediaId()));
 		sql  = sql.replace("#{url}",String.valueOf(t.getUrl()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
	
}