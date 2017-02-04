package com.yuhi.wechar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.wechar.dao.ProjectPickHistoryDao;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.facade.ProjectPickHistoryFacade;
import com.yuhi.wechar.util.BaseTools;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* owner服务实现

<!-- owner服务 -->
<dubbo:reference interface="com.yuhi.wechar.facade.ProjectPickHistoryFacade" id="projectPickHistoryFacade" />

	@Autowired
	private ProjectPickHistoryFacade projectPickHistoryFacade;

*/
@Service("projectPickHistoryService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.wechar.facade.ProjectPickHistoryFacade.class,version="1.0", protocol = {"rest", "dubbo"})
public class ProjectPickHistoryService implements ProjectPickHistoryFacade{
	/**
	 * 导入数据操作层
	 */
	@Autowired
	private ProjectPickHistoryDao projectPickHistoryDao;

	@Override
	@Transactional
	public boolean delete(String id)  throws Exception{
		return projectPickHistoryDao.delete(id)>-1;
	}

	@Override
	@Transactional
	public boolean save(JSONObject entity)  throws Exception{
		return projectPickHistoryDao.insert(entity)>-1;
	}

	@Override
	@Transactional
	public boolean Update(Map<String, Object> paramMap, String id)  throws Exception{
		return projectPickHistoryDao.update(paramMap, id)>-1;
	}

	@Override
	public JSONObject getObjByid(String id) throws Exception {
		return projectPickHistoryDao.getById(id);
	}

	@Override
	public List<JSONObject> getList() throws Exception {
		return projectPickHistoryDao.getList();
	}

	@Override
	public List<JSONObject> getDatabySql(String sql, String... args) {
		// TODO Auto-generated method stub
		return projectPickHistoryDao.queryForJsonList(sql, args);
	}

	@Override
	public JSONObject getObjbySql(String sql, String... args) {
		// TODO Auto-generated method stub
		return projectPickHistoryDao.queryForJsonObject(sql, args);
	}

	@Override
	public List<Map<String, Object>> getMapbySql(String sql, String... args) {
		// TODO Auto-generated method stub
		return projectPickHistoryDao.queryforMap(sql, args);
	}

	@Override
	public Long getCountbySql(String sql, String... args) {
		// TODO Auto-generated method stub
		return projectPickHistoryDao.queryForCountNum(sql, args);
	}

	@Override
	public String datagrid(MapData md, Integer pageNum, Integer pagesize)
			throws Exception {
		StringBuffer sql=new StringBuffer("from Project_Pick_History p,Project_Person ppe where p.ppopenid=ppe.openid  ");
		List<String> args=new ArrayList<String>(md.size());
//		条件构造
		if(md.containsKey("ids")){
			args.add(md.getString("ids"));
			sql.append(" and p.pcid = ? ");
		}
		sql.append(" order by time desc ");
//		查询
		Long total=projectPickHistoryDao.queryForCountNum(BaseTools.countSql+sql.toString(), args.toArray());
		String selectSql="select p.id,ppe.name,p.ppname,p.typeid,p.pickdescs,p.time ";
		List<JSONObject> objlist=projectPickHistoryDao.queryForJsonListPage(selectSql+sql.toString(), pageNum, pagesize, args.toArray());
		return BaseTools.getDatagridJson(total, objlist); 
	}
	
}