package weixin.guanjia.subrice.service;
import weixin.guanjia.subrice.entity.SyvscribeusersEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface SyvscribeusersServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(SyvscribeusersEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(SyvscribeusersEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(SyvscribeusersEntity t);

	public List<String[]> getPart();

	public String getSendOpenid(HttpServletRequest request, String code);
}
