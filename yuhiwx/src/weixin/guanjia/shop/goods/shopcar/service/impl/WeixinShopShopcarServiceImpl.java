package weixin.guanjia.shop.goods.shopcar.service.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.guanjia.shop.goods.shopcar.entity.WeixinShopShopcarEntity;
import weixin.guanjia.shop.goods.shopcar.service.WeixinShopShopcarServiceI;

@Service("weixinShopShopcarService")
@Transactional
public class WeixinShopShopcarServiceImpl extends CommonServiceImpl implements WeixinShopShopcarServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((WeixinShopShopcarEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((WeixinShopShopcarEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((WeixinShopShopcarEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinShopShopcarEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinShopShopcarEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinShopShopcarEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,WeixinShopShopcarEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{ws_goods_id}",String.valueOf(t.getWsGoodsId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{ws_goods_price}",String.valueOf(t.getWsGoodsPrice()));
 		sql  = sql.replace("#{ws_goods_count}",String.valueOf(t.getWsGoodsCount()));
 		sql  = sql.replace("#{userid}",String.valueOf(t.getUserid()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}

	@Override
	public WeixinShopShopcarEntity isExeitShopcar(String productId, String openid) {
		WeixinShopShopcarEntity entity = singleResult("from WeixinShopShopcarEntity where wsGoodsId='"+productId+"' " +
				"and userid='"+openid+"'");
		return entity;
	}
}