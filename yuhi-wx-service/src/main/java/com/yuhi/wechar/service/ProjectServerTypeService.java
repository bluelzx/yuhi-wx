package com.yuhi.wechar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.wechar.dao.ProjectServerTypeDao;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.facade.ProjectServerTypeFacade;
import com.yuhi.wechar.util.BaseTools;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* owner服务实现

<!-- owner服务 -->
<dubbo:reference interface="com.yuhi.wechar.facade.ProjectServerTypeFacade" id="projectServerTypeFacade" />

	@Autowired
	private ProjectServerTypeFacade projectServerTypeFacade;

*/
@Service("projectServerTypeService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.wechar.facade.ProjectServerTypeFacade.class,version="1.0", protocol = {"rest", "dubbo"})
public class ProjectServerTypeService implements ProjectServerTypeFacade{
	/**
	 * 导入数据操作层
	 */
	@Autowired
	private ProjectServerTypeDao projectServerTypeDao;

	@Override
	@Transactional
	public boolean delete(String id)  throws Exception{
		return projectServerTypeDao.delete(id)>-1;
	}

	@Override
	@Transactional
	public boolean save(JSONObject entity)  throws Exception{
		return projectServerTypeDao.insert(entity)>-1;
	}

	@Override
	@Transactional
	public boolean Update(Map<String, Object> paramMap, String id)  throws Exception{
		return projectServerTypeDao.update(paramMap, id)>-1;
	}

	@Override
	public JSONObject getObjByid(String id) throws Exception {
		return projectServerTypeDao.getById(id);
	}

	@Override
	public List<JSONObject> getList() throws Exception {
		return projectServerTypeDao.getList();
	}
	
	
	@Override
	public List<JSONObject> getPageList(Integer rows,Integer page) throws Exception {
		String sql="select * from project_server_type";
		return projectServerTypeDao.queryForJsonListPage(sql, page, rows, null);
	}

	@Override
	public Long getCountbySql(String... args) {
		// TODO Auto-generated method stub
		String sql="select count(1) from project_server_type";
		return projectServerTypeDao.queryForCountNum(sql, args);
	}

	@Override
	public String datagrid(MapData md, Integer pageNum, Integer pagesize)
			throws Exception {
		StringBuffer sql=new StringBuffer("from project_server_type where 1=1 ");
		List<String> args=new ArrayList<String>(md.size());
//		条件构造
//		if(md.containsKey("part")){
//			args.add(md.getString("part"));
//			sql.append(" and part=? ");
//		}
//		查询
		Long total=projectServerTypeDao.queryForCountNum(BaseTools.countSql+sql.toString(), args.toArray());
		List<JSONObject> objlist=projectServerTypeDao.queryForJsonListPage("select * "+sql.toString(), pageNum, pagesize, args.toArray());
		return BaseTools.getDatagridJson(total, objlist); 
	}
}