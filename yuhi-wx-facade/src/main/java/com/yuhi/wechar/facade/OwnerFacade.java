package com.yuhi.wechar.facade;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.alibaba.fastjson.JSONObject;
import com.yuhi.wechar.entity.MapData;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* To change this template use File | Settings | File Templates.
*/
@Path("/ownerService")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public interface OwnerFacade {
	
	/**
	 * 发布业主园区通告
	 * @param request
	 * @param openid
	 * @return
	 */
	@GET
	public void sendMesforOwner(String templateid,String deptid) throws Exception;
	/**
	 * 业主是否绑定
	 * @param request
	 * @param openid
	 * @return
	 */
	@GET
	public JSONObject isBidding(String id) throws Exception;
	/**
	 * 根据openid获取用户
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	@GET
	public JSONObject getOwnerObjByOpenid(String openid) throws Exception;
	/**
	 * 业主解绑/删除
	 */
	@POST
	public Boolean DoUnBidding(String id) throws Exception;
	/**
	 * 业主绑定
	 */
	@POST
	public Boolean DoBidding(MapData md) throws Exception;
	/**
	 * 修改业主信息
	 */
	@POST
	public Boolean DoUpdateOwnerInfo(MapData request) throws Exception;
	/**
	 * 根据园区获取业主id集合
	 */
	@GET
	public List<String> getOwnerIds(String deptid) throws Exception ;
	/**
	 * 分页
	 * 获取客户列表
	 */
	@GET
	@Path("/ownerlist/{pagenum}/{pagesize}")
	public List<JSONObject> getOwnerList(@PathParam(value = "pagenum")int pagenum,@PathParam(value = "pagesize")int pagesize) throws Exception ;
	/**
	 * 数据视图集合
	 */
	@POST
	@Path("/datagrid/{pagenum}/{pagesize}")
	public String datagrid(MapData md,@PathParam(value = "pagenum")Integer pageNum,@PathParam(value = "pagesize") Integer pagesize) throws Exception;
}