package com.yuhi.wechar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.wechar.dao.ProjectCardStatisticsDao;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.facade.ProjectCardStatisticsFacade;
import com.yuhi.wechar.util.BaseTools;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* owner服务实现

<!-- ProjectCardStatistics服务 -->
<dubbo:reference interface="com.yuhi.wechar.facade.ProjectCardStatisticsFacade" id="projectCardStatisticsFacade" />

	@Autowired
	private ProjectCardStatisticsFacade projectCardStatisticsFacade;

*/
@Service("projectCardStatisticsService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.wechar.facade.ProjectCardStatisticsFacade.class,version="1.0", protocol = {"rest", "dubbo"})
public class ProjectCardStatisticsService implements ProjectCardStatisticsFacade{
	/**
	 * 导入数据操作层
	 */
	@Autowired
	private ProjectCardStatisticsDao projectCardStatisticsDao;

	@Override
	@Transactional
	public boolean delete(String id)  throws Exception{
		return projectCardStatisticsDao.delete(id)>-1;
	}

	@Override
	@Transactional
	public boolean save(JSONObject entity)  throws Exception{
		return projectCardStatisticsDao.insert(entity)>-1;
	}

	@Override
	@Transactional
	public boolean Update(Map<String, Object> paramMap, String id)  throws Exception{
		return projectCardStatisticsDao.update(paramMap, id)>-1;
	}

	@Override
	public JSONObject getObjByid(String id) throws Exception {
		return projectCardStatisticsDao.getById(id);
	}

	@Override
	public List<JSONObject> getList() throws Exception {
		return projectCardStatisticsDao.getList();
	}

	@Override
	public List<JSONObject> getDatabySql(String sql, String... args) {
		// TODO Auto-generated method stub
		return projectCardStatisticsDao.queryForJsonList(sql, args);
	}

	@Override
	public JSONObject getObjbySql(String sql, String... args) {
		// TODO Auto-generated method stub
		return projectCardStatisticsDao.queryForJsonObject(sql, args);
	}

	@Override
	public List<Map<String, Object>> getMapbySql(String sql, String... args) {
		// TODO Auto-generated method stub
		return projectCardStatisticsDao.queryforMap(sql, args);
	}

	@Override
	public Long getCountbySql(String sql, String... args) {
		// TODO Auto-generated method stub
		return projectCardStatisticsDao.queryForCountNum(sql, args);
	}

	@Override
	public String datagrid(MapData md, Integer pageNum, Integer pagesize)
			throws Exception {
		StringBuffer sql=new StringBuffer("from PROJECT_CARD_STATISTICS where 1=1 ");
		List<String> args=new ArrayList<String>(md.size());
//		条件构造
		if(md.containsKey("part")){
			args.add(md.getString("part"));
			sql.append(" and part=? ");
		}
//		查询
		Long total=projectCardStatisticsDao.queryForCountNum(BaseTools.countSql+sql.toString(), args.toArray());
		List<JSONObject> objlist=projectCardStatisticsDao.queryForJsonListPage("select * "+sql.toString(), pageNum, pagesize, args.toArray());
		return BaseTools.getDatagridJson(total, objlist); 
	}
	
}