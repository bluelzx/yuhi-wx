package weixin.guanjia.shop.goods.items.service;
import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

import weixin.guanjia.shop.goods.items.entity.WeixinShopGoodsEntity;

public interface WeixinShopGoodsServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinShopGoodsEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinShopGoodsEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinShopGoodsEntity t);
}
