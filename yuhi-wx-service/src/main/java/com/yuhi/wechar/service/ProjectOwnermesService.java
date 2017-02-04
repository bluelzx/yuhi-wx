package com.yuhi.wechar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.wechar.dao.ProjectOwnermesDao;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.facade.ProjectOwnermesFacade;
import com.yuhi.wechar.util.BaseTools;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* owner服务实现

<!-- owner服务 -->
<dubbo:reference interface="com.yuhi.wechar.facade.ProjectOwnermesFacade" id="projectOwnermesFacade" />

	@Autowired
	private ProjectOwnermesFacade projectOwnermesFacade;

*/
@Service("projectOwnermesService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.wechar.facade.ProjectOwnermesFacade.class,version="1.0", protocol = {"rest", "dubbo"})
public class ProjectOwnermesService implements ProjectOwnermesFacade{
	/**
	 * 导入数据操作层
	 */
	@Autowired
	private ProjectOwnermesDao projectOwnermesDao;

	@Override
	@Transactional
	public boolean delete(String id)  throws Exception{
		return projectOwnermesDao.delete(id)>-1;
	}

	@Override
	@Transactional
	public boolean save(JSONObject entity)  throws Exception{
		return projectOwnermesDao.insert(entity)>-1;
	}

	@Override
	@Transactional
	public boolean Update(Map<String, Object> paramMap, String id)  throws Exception{
		return projectOwnermesDao.update(paramMap, id)>-1;
	}

	@Override
	public JSONObject getObjByid(String id) throws Exception {
		return projectOwnermesDao.getById(id);
	}

	@Override
	public List<JSONObject> getList() throws Exception {
		return projectOwnermesDao.getList();
	}
	
	@Override
	public String datagrid(MapData md,Integer pageNum,Integer pagesize) throws Exception{
		StringBuffer sql=new StringBuffer("from PROJECT_OWNERMES where 1=1 ");
		List<String> args=new ArrayList<String>(md.size());
//		条件构造
		if(md.containsKey("part")){
			args.add(md.getString("part"));
			sql.append(" and part=? ");
		}
		Long total=projectOwnermesDao.queryForCountNum(BaseTools.countSql+sql.toString(), args.toArray());
		List<JSONObject> objlist=projectOwnermesDao.queryForJsonListPage("select * "+sql.toString(), pageNum, pagesize, args.toArray());
		return BaseTools.getDatagridJson(total, objlist); 
	}
}