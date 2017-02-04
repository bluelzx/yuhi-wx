package com.yuhi.wechar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.util.DateUtils;
import com.yuhi.util.IDCreate;
import com.yuhi.wechar.dao.OwnerDao;
import com.yuhi.wechar.dao.ProjectCardDao;
import com.yuhi.wechar.dao.ProjectCreateDao;
import com.yuhi.wechar.dao.ProjectPartDao;
import com.yuhi.wechar.dao.ProjectPickHistoryDao;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.facade.OwnerFacade;
import com.yuhi.wechar.facade.ProjectCreateFacade;
import com.yuhi.wechar.util.BaseTools;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* owner服务实现

<!-- owner服务 -->
<dubbo:reference interface="com.yuhi.wechar.facade.ProjectCreateFacade" id="projectCreateFacade" />

	@Autowired
	private ProjectCreateFacade projectCreateFacade;

*/
@Service("projectCreateService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.wechar.facade.ProjectCreateFacade.class,version="1.0", protocol = {"rest", "dubbo"})
public class ProjectCreateService implements ProjectCreateFacade{
	/**
	 * 导入数据操作层
	 */
	@Autowired
	private ProjectCreateDao projectCreateDao;
	@Autowired
	private OwnerDao ownerDao;
	@Autowired
	private ProjectPartDao projectPartDao; 
	@Autowired
	private ProjectCardDao projectCradDao; 
	@Autowired
	private ProjectPickHistoryDao projectPickHistoryDao;
	
	@Override
	@Transactional
	public boolean delete(String id)  throws Exception{
		projectPickHistoryDao.delete(new String[]{"pcid=?"}, new String[]{id});
		return projectCreateDao.delete(id)>-1;
	}


	@Override
	@Transactional
	public boolean Update(Map<String, Object> paramMap, String id)  throws Exception{
		return projectCreateDao.update(paramMap, id)>-1;
	}

	@Override
	public JSONObject getObjByid(String id) throws Exception {
		return projectCreateDao.getById(id);
	}

	@Override
	public List<JSONObject> getList() throws Exception {
		return projectCreateDao.getList("", new String[0]);
	}

	@Override
	public List<JSONObject> getDatabySql(String sql, String... args)
			throws Exception {
		// TODO Auto-generated method stub
		return projectCreateDao.queryForJsonList(sql, args);
	}

	@Override
	public Long getCountbySql(String sql, String... args) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String datagrid(MapData md, Integer pageNum, Integer pagesize)
			throws Exception {
		StringBuffer sql=new StringBuffer("from Project_Create p,Owner ow where p.ownerid=ow.id ");
		List<String> args=new ArrayList<String>(md.size());
//		条件构造
		if(md.containsKey("part")){
			args.add(md.getString("part"));
			sql.append(" and p.part=? ");
		}
		if(md.containsKey("id")){
			args.add(md.getString("id")+"%");
			sql.append(" and p.id like ?");
		}
		if(md.containsKey("ownername")){
			args.add("%"+md.getString("ownername")+"%");
			sql.append(" and ow.name like ? ");
		}
		if(md.containsKey("phone")){
			args.add(md.getString("phone")+"%");
			sql.append(" and p.phone like  ? ");
		}
		sql.append("ORDER BY CREATE_DATE DESC");
//		查询
		Long total=projectCreateDao.queryForCountNum(BaseTools.countSql+sql.toString(), args.toArray());
		String selectSql="select p.id,ow.name ownername,p.create_date,p.content,p.starttime,p.endtime,p.descs,p.phone,p.otherContact,p.pctype,p.publicplace  ";
		List<JSONObject> objlist=projectCreateDao.queryForJsonListPage(selectSql+sql.toString(), pageNum, pagesize, args.toArray());
		return BaseTools.getDatagridJson(total, objlist); 
	}

	@Override
	public JSONObject dispatchProjectCartForPerson(String dispatchopenidOrPhone,
			String pcid) throws Exception {
		JSONObject	result=new JSONObject();
		if (StringUtils.isNotEmpty(dispatchopenidOrPhone)) {
			String[] person = dispatchopenidOrPhone.split(",");
			if (person != null && person.length > 2) {
				JSONObject entity = projectCreateDao.getById(pcid);
				JSONObject pcEntity = new JSONObject();
					pcEntity.put("id", entity.getString("id"));
					pcEntity.put("content", entity.getString("content"));
					pcEntity.put("create_time", entity.getString("create_time"));
					pcEntity.put("part", entity.getString("part"));
					pcEntity.put("ownerid", entity.getString("ownerid"));
					pcEntity.put("descs", entity.getString("descs"));
					pcEntity.put("create_time",DateUtils.formatDateTime(entity.getDate("create_date")));
					pcEntity.put("endtime", DateUtils.formatDateTime(entity.getDate("endtime")));
					pcEntity.put("invitationid", entity.getString("invitationid"));
					pcEntity.put("starttime", DateUtils.formatDateTime(entity.getDate("starttime")));
					pcEntity.put("phone", entity.getString("phone"));
					pcEntity.put("othercontact", entity.getString("othercontact"));
					pcEntity.put("pcserver", entity.getString("pcserver"));
					pcEntity.put("pctype", entity.getString("pctype"));
					pcEntity.put("publicplace", entity.getString("publicplace"));
					pcEntity.put("state","1");
					pcEntity.put("distribution_openid",person[0]);
					pcEntity.put("distribution_time", DateUtils.getDate("yyyy-MM-dd hh:mm:ss"));
					pcEntity.put("isok", "1");
					pcEntity.put("ispay", "1");
					pcEntity.put("reminderstate","0");
					pcEntity.put("roomid",entity.getString("roomid"));
					pcEntity.put("throughaudit","5");
					projectCradDao.insert(pcEntity);
					projectCreateDao.delete(pcid);

				JSONObject owner = ownerDao.getById(pcEntity.getString("ownerid"));
				
				Map<String, String> map = new HashMap<String, String>();
				map.put("first", "尊敬的业主,您的工程单已分配");
				map.put("keyword1", pcEntity.getString("id"));
				map.put("keyword2", pcEntity.getString("content"));
				map.put("keyword3", person[1]);
				map.put("keyword4", pcEntity.getString("publicplace"));
				map.put("remark", "感谢你对元海集团的大力支持!");

				Map<String, String> psmap = new HashMap<String, String>();
				psmap.put("first", "工程人员您好，您收到工单分配通知");
				psmap.put("keyword1", pcEntity.getInteger("pctype") == 1 ? "公共单" : "服务单");
				psmap.put("keyword2", owner.getString("name"));
				psmap.put("keyword3", pcEntity.getString("phone"));
				psmap.put("keyword4", pcEntity.getString("publicplace"));
				psmap.put("keyword5", pcEntity.getString("content"));
				psmap.put("remark", "（收到通知后请及时与用户联系进行工程确认）");
				//模板消息
				result.put("ownersendid", owner.getString("openid"));
				result.put("ownermsg", map);
				result.put("personsendid", owner.getString("distribution_openid"));
				result.put("personmsg", psmap);
				result.put("projectcardid", entity.getString("id"));
			}
		}
		return result;
	}

	@Override
	@Transactional
	public void doAddPublicforWindow(String pcserver,String part, String userid) throws Exception {
		// TODO Auto-generated method stub
			JSONObject obj=new JSONObject();
			obj.put("id",IDCreate.getDateString());
			obj.put("ownerid",userid);
			obj.put("create_date",DateUtils.getDate("yyyy-MM-dd hh:mm:ss"));
			obj.put("invitationid",0);
			obj.put("pctype",0);
			obj.put("part",part);
			obj.put("content",pcserver);
			projectCreateDao.insert(obj);
	}

	@Override
	@Transactional
	public void doAddforWindow(MapData md, String roomsbyid, String part,
			String userid) throws Exception {
		// TODO Auto-generated method stub
		JSONObject obj=new JSONObject(md);
			if(StringUtils.isEmpty(obj.getString("roomid"))||StringUtils.isEmpty(roomsbyid)){
				//异常访问
				throw new IllegalAccessError("异常访问");
			}else{
				String publicplace=projectPartDao.getpublicplace(roomsbyid);
				obj.put("publicplace", publicplace);
			}
			obj.put("id",IDCreate.getDateString());
			obj.put("ownerid",userid);
			obj.put("create_date",DateUtils.getDate("yyyy-MM-dd hh:mm:ss"));
			obj.put("invitationid",0);
			obj.put("pctype",0);
			obj.put("part",part);
			projectCreateDao.insert(obj);
	}

	@Override
	@Transactional
	public String doAddForOwner(String openid, String ownerid)
			throws Exception {
		JSONObject projectCreate=new JSONObject();
		JSONObject oe = ownerDao.getById(ownerid);
		projectCreate.put("id",IDCreate.getDateString());
		projectCreate.put("invitationid",0);
		projectCreate.put("create_date",DateUtils.getDate("yyyy-MM-dd hh:mm:ss"));
		projectCreate.put("part",oe.getString("part"));
		projectCreate.put("pctype",0);
		projectCreate.put("publicplace",oe.getString("partdesc"));
		projectCreateDao.insert(projectCreate);
		return projectCreate.getString("id")+","+oe.getString("part")+","+oe.getString("openid");
	}

	@Override
	public JSONObject getProjectCardDetail(String projectCardid)
			throws Exception {
		String hql = "SELECT O.PARTDESC,O.PHONE,O.NAME,PC.STARTTIME,PC.ENDTIME,PC.OTHERCONTACT,PC.CONTENT,PC.DESCS FROM PROJECT_CREATE PC,OWNER O WHERE PC.OWNERID=O.ID AND PC.ID=?";
		return  projectCreateDao.queryForJsonObject(hql, projectCardid);
	}
	
	@Override
	public List<JSONObject> getProjectCreateList(String ownerid) throws Exception {
		List<JSONObject> objlist=projectCreateDao.getList(" and ownerid=? ", new String[]{ownerid});
		return objlist;
	}
	
}