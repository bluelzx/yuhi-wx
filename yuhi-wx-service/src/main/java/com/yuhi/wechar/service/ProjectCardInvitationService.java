package com.yuhi.wechar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.wechar.dao.ProjectCardInvitationDao;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.facade.ProjectCardInvitationFacade;
import com.yuhi.wechar.util.BaseTools;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* owner服务实现

<!-- owner服务 -->
<dubbo:reference interface="com.yuhi.wechar.facade.ProjectCardInvitationFacade" id="projectCardInvitationFacade" />

	@Autowired
	private ProjectCardInvitationFacade projectCardInvitationFacade;

*/
@Service("projectCardInvitationService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.wechar.facade.ProjectCardInvitationFacade.class,version="1.0", protocol = {"rest", "dubbo"})
public class ProjectCardInvitationService implements ProjectCardInvitationFacade{
	/**
	 * 导入数据操作层
	 */
	@Autowired
	private ProjectCardInvitationDao projectCardInvitationDao;

	@Override
	@Transactional
	public boolean delete(String id)  throws Exception{
		return projectCardInvitationDao.delete(id)>-1;
	}

	@Override
	@Transactional
	public boolean save(JSONObject entity)  throws Exception{
		return projectCardInvitationDao.insert(entity)>-1;
	}

	@Override
	@Transactional
	public boolean Update(Map<String, Object> paramMap, String id)  throws Exception{
		return projectCardInvitationDao.update(paramMap, id)>-1;
	}

	@Override
	public JSONObject getObjByid(String id) throws Exception {
		return projectCardInvitationDao.getById(id);
	}

	@Override
	public List<JSONObject> getList() throws Exception {
		return projectCardInvitationDao.getList();
	}

	@Override
	@Transactional
	public void addProjectInvitation(String pcid,
			List<Map<String, String>> invitationEntities) throws Exception {
		//删除原有耗材
		String delpcisql="DELETE FROM `project_card_invitation` WHERE projectCardid=?";
		int count=projectCardInvitationDao.getJdbcTemplate().update(delpcisql,pcid);
		for (Map<String, String> hashMap : invitationEntities) {
			if (hashMap == null)
				continue;
			JSONObject obj=new JSONObject();
			obj.put("name", hashMap.get("name"));
			obj.put("price", hashMap.get("price"));
			obj.put("descs", hashMap.get("descs"));
			obj.put("specifications", hashMap.get("specifications"));
			Object id=hashMap.get("id");
			Integer inid=0;
			if(id!=null)inid=Integer.valueOf(id.toString());
			obj.put("invitationId", inid);
			obj.put("typeid", 1);
			obj.put("projectCardid", pcid);
			save(obj);
		}
		if(count==0){
			//标记工程单已有耗材
			delpcisql="update `project_card` set invitationid=1 where id=?";
			projectCardInvitationDao.getJdbcTemplate().update(delpcisql,pcid);
		}
	}

	@Override
	public String datagrid(MapData md, Integer pageNum, Integer pagesize)
			throws Exception {
		StringBuffer sql=new StringBuffer("from project_card_invitation where 1=1 ");
		List<String> args=new ArrayList<String>(md.size());
//		条件构造
		if(md.containsKey("projectcardid")){
			args.add(md.getString("projectcardid"));
			sql.append(" and projectcardid=? ");
		}
//		查询
		Long total=projectCardInvitationDao.queryForCountNum(BaseTools.countSql+sql.toString(), args.toArray());
		List<JSONObject> objlist=projectCardInvitationDao.queryForJsonListPage("select * "+sql.toString(), pageNum, pagesize, args.toArray());
		return BaseTools.getDatagridJson(total, objlist); 
	}
	
}