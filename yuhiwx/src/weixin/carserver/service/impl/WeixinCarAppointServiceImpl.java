package weixin.carserver.service.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.carserver.entity.WeixinCarAppointEntity;
import weixin.carserver.service.WeixinCarAppointServiceI;

@Service("weixinCarAppointService")
@Transactional
public class WeixinCarAppointServiceImpl extends CommonServiceImpl implements WeixinCarAppointServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((WeixinCarAppointEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((WeixinCarAppointEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((WeixinCarAppointEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinCarAppointEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinCarAppointEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinCarAppointEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,WeixinCarAppointEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{openid}",String.valueOf(t.getOpenid()));
 		sql  = sql.replace("#{createdate}",String.valueOf(t.getCreatedate()));
 		sql  = sql.replace("#{serverid}",String.valueOf(t.getServerid()));
 		sql  = sql.replace("#{orderdate}",String.valueOf(t.getOrderdate()));
 		sql  = sql.replace("#{carid}",String.valueOf(t.getCarid()));
 		sql  = sql.replace("#{phone}",String.valueOf(t.getPhone()));
 		sql  = sql.replace("#{state}",String.valueOf(t.getState()));
 		sql  = sql.replace("#{originalprice}",String.valueOf(t.getOriginalprice()));
 		sql  = sql.replace("#{discountprice}",String.valueOf(t.getDiscountprice()));
 		sql  = sql.replace("#{price}",String.valueOf(t.getPrice()));
 		sql  = sql.replace("#{descs}",String.valueOf(t.getDescs()));
 		sql  = sql.replace("#{additionalprice}",String.valueOf(t.getAdditionalprice()));
 		sql  = sql.replace("#{additionaldesc}",String.valueOf(t.getAdditionaldesc()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}