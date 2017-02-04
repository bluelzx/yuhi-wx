package com.yuhi.wechar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.wechar.dao.ProjectCardObjectionDao;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.facade.ProjectCardObjectionFacade;
import com.yuhi.wechar.util.BaseTools;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* owner服务实现

<!-- owner服务 -->
<dubbo:reference interface="com.yuhi.wechar.facade.ProjectCardObjectionFacade" id="projectCardObjectionFacade" />

	@Autowired
	private ProjectCardObjectionFacade projectCardObjectionFacade;

*/
@Service("projectCardObjectionService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.wechar.facade.ProjectCardObjectionFacade.class,version="1.0", protocol = {"rest", "dubbo"})
public class ProjectCardObjectionService implements ProjectCardObjectionFacade{
	/**
	 * 导入数据操作层
	 */
	@Autowired
	private ProjectCardObjectionDao projectCardObjectionDao;

	@Override
	public boolean delete(String id)  throws Exception{
		return projectCardObjectionDao.delete(id)>-1;
	}

	@Override
	@Transactional
	public boolean save(JSONObject entity)  throws Exception{
		return projectCardObjectionDao.insert(entity)>-1;
	}

	@Override
	@Transactional
	public boolean Update(Map<String, Object> paramMap, String id)  throws Exception{
		return projectCardObjectionDao.update(paramMap, id)>-1;
	}

	@Override
	public JSONObject getObjByid(String id) throws Exception {
		return projectCardObjectionDao.getById(id);
	}

	@Override
	public List<JSONObject> getList() throws Exception {
		return projectCardObjectionDao.getList();
	}

	@Override
	public String datagrid(MapData md, Integer pageNum, Integer pagesize)
			throws Exception {
		StringBuffer sql=new StringBuffer("from PROJECT_CARD_OBJECTION where 1=1 ");
		List<String> args=new ArrayList<String>(md.size());
//		条件构造
		if(md.containsKey("part")){
			args.add(md.getString("part"));
			sql.append(" and part=? ");
		}
//		查询
		Long total=projectCardObjectionDao.queryForCountNum(BaseTools.countSql+sql.toString(), args.toArray());
		List<JSONObject> objlist=projectCardObjectionDao.queryForJsonListPage("select * "+sql.toString(), pageNum, pagesize, args.toArray());
		return BaseTools.getDatagridJson(total, objlist); 
	}
	
}