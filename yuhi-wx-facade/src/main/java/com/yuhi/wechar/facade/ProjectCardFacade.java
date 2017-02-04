package com.yuhi.wechar.facade;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.yuhi.wechar.entity.resultdata;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* To change this template use File | Settings | File Templates.
* http://localhost:8888/yuhi-wx-service/projectCardService/getObjByid/
*/
@Path("/projectCardService")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public interface ProjectCardFacade {
	@POST
	@Path("/update/{id}")
 	public boolean  Update(Map<String, Object> paramMap,@PathParam(value = "id")String id) throws Exception;
	/**
	 * 保存实体
	 * @param entity
	 * @return
	 */
	@POST
 	public boolean  save(JSONObject entity) throws Exception;
	/**
	 * 根据id获取单个实体
	 * @param openid
	 * @return
	 */
	@GET
	@Path("/getObjByid/{id}")
	public JSONObject getObjByid(@PathParam(value = "id") String id) throws Exception;
	/**********************自定义接口******************************/
	/**
	 * 审核
	 * @param id
	 * @param isok
	 * @return
	 * @throws Exception
	 */
	@POST
	public boolean PCthroughAudit( String id,String isok) throws Exception;
	
	/**
	 * 获取工单详情
	 * @param projectCardid
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/PcDetail/{projectCardid}")
	public JSONObject getProjectCardDetail(@PathParam(value = "projectCardid")String projectCardid) throws Exception;
	/**
	 * 数据视图集合
	 */
	@POST
	@Path("/datagrid/{pagenum}/{pagesize}")
	public String datagrid(MapData md,@PathParam(value = "pagenum")Integer pageNum,@PathParam(value = "pagesize") Integer pagesize) throws Exception;
	/**
	 * 报表数据
	 * @param time
	 * @param deptid
	 * @return
	 */
	@GET
	public resultdata getReportData(Date time,String deptid) throws Exception;
	/**
	 * 根据sql查询
	 * @param sql
	 * @param args
	 * @return
	 */
	@GET
	public List<JSONObject> getDatabySql(String sql,String... args) throws Exception;
	/**
	 * 关闭工单
	 * @param id
	 * @return
	 */
	@POST
	@Path("closeProjectCard")
	public void closeProjectCard(String id) throws Exception;
	/**
	 * 支付确认
	 * @param id
	 * paytype支付类型
	 * @return
	 */
	@POST
//	@Path("/confirmpay/{id}/{paytype}")
	public void confirmpay(String id,String paytype) throws Exception;
	/**
	 * 更改工程单状态
	 * @param md
	 * @param id
	 * @param type
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	@POST
//	@Path("/updateProjectState/{id}/{type}/{openid}")
	public MapData updateProjectState(MapData md,String id,Integer type,String openid) throws Exception;
	/**
	 * 工程单回访
	 * @param id
	 * @throws Exception
	 */
	@POST
//	@Path("/CallbackprojectCard/{id}/{othercontent}")
	public void CallbackprojectCard(String id,String othercontent) throws Exception;
	/****************************************/
	/**
	 * 根据工单状态获取工单
	 * @param personid
	 * @param isowner
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/datagrid/{personid}/{isowner}")
	public List<JSONObject> getProjectCardBytype(@PathParam(value = "personid")String personid,@PathParam(value = "isowner")boolean isowner) throws Exception;
	/**
	 * 工程人员获取工程单
	 * @param openid
	 * @param state
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/getppList/{openid}/{state}")
	public List<JSONObject> getppProjectListData(@PathParam(value = "openid")String openid,@PathParam(value = "state")String state) throws Exception;
	/**
	 * 客户获取工程单
	 * @param ownerid
	 * @param state
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/getownerList/{ownerid}/{state}")
	public List<JSONObject> getownerListData(@PathParam(value = "ownerid")String ownerid,@PathParam(value = "state")String state) throws Exception;
	
}