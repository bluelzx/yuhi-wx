package weixin.guanjia.message.service;
import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.message.entity.Articles;
import weixin.guanjia.message.entity.WeixinAlongSourceEntity;

public interface WeixinAlongSourceServiceI extends CommonService{
	
 	public <T> void delete(T entity,WeixinAccountEntity  weixinAccountEntity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinAlongSourceEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinAlongSourceEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinAlongSourceEntity t);
 	
 	
 	boolean uploadSource(String name,WeixinAccountEntity  weixinAccountEntity,File file, String type);
 	public boolean uploadNews(WeixinAccountEntity weixinAccountEntity,List<Articles> articles);
}
