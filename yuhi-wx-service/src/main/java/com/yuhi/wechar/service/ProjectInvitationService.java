package com.yuhi.wechar.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSONObject;
import com.yuhi.wechar.dao.ProjectInvitationDao;
import com.yuhi.wechar.entity.InvitationtreeNode;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.facade.ProjectInvitationFacade;
import com.yuhi.wechar.util.BaseTools;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* owner服务实现

<!-- owner服务 -->
<dubbo:reference interface="com.yuhi.wechar.facade.ProjectInvitationFacade" id="projectInvitationFacade" />

	@Autowired
	private ProjectInvitationFacade projectInvitationFacade;

*/
@Service("projectInvitationService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.wechar.facade.ProjectInvitationFacade.class,version="1.0", protocol = {"rest", "dubbo"})
public class ProjectInvitationService implements ProjectInvitationFacade{
	/**
	 * 导入数据操作层
	 */
	@Autowired
	private ProjectInvitationDao projectInvitationDao;

	@Override
	@Transactional
	public boolean delete(String id)  throws Exception{
		return projectInvitationDao.delete(id)>-1;
	}

	@Override
	@Transactional
	public boolean save(JSONObject entity)  throws Exception{
		return projectInvitationDao.insert(entity)>-1;
	}

	@Override
	@Transactional
	public boolean Update(Map<String, Object> paramMap, String id)  throws Exception{
		return projectInvitationDao.update(paramMap, id)>-1;
	}

	@Override
	public JSONObject getObjByid(String id) throws Exception {
		return projectInvitationDao.getById(id);
	}

	@Override
	public List<JSONObject> getList() throws Exception {
		return projectInvitationDao.getList();
	}

	@Override
	public List<InvitationtreeNode> getpartNodeByType(Integer string, String type) {
		//获取父耗材
		String sql="SELECT  id, iname text, idesc `desc`,price,price_desc priceDesc FROM Project_Invitation where parentsid=?";
		List<InvitationtreeNode> pnodes=projectInvitationDao.getJdbcTemplate().query(sql,new String[]{string+""},new RowMapper<InvitationtreeNode>(){
			@Override
			public InvitationtreeNode mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				return new InvitationtreeNode(rs.getInt("id"), rs.getString("text"), rs.getString("desc"), rs.getString("price"), rs.getString("priceDesc"));
			}
		});
		return pnodes;
	}

	@Override
	public boolean isExitsChild(Integer id) {
		// TODO Auto-generated method stub
		Long count=projectInvitationDao.queryForCountNum("SELECT COUNT(1) FROM `project_invitation` where parentsid=?", id);
		return count>0;
	}

	@Override
	@Transactional
	public int changeIncrement() {
		List<Map<String, Object>> map = projectInvitationDao.queryforMap("SELECT INVITATIONID ID,COUNT(INVITATIONID) COUNT FROM `PROJECT_CARD_INVITATION` WHERE INVITATIONID!=0 GROUP BY INVITATIONID");
		for (Map<String, Object> map2 : map) {
			Object id=map2.get("id");
			if(id==null)return 0;
			projectInvitationDao.updateBySql("UPDATE PROJECT_INVITATION SET USE_COUNT=? WHERE ID=?",(String)map2.get("COUNT"),(String)id);
		}
		return map.size();
	}

	@Override
	public String datagrid(MapData md, Integer pageNum, Integer pagesize)
			throws Exception {
		StringBuffer sql=new StringBuffer("from project_invitation where 1=1 ");
		List<String> args=new ArrayList<String>(md.size());
//		条件构造
		if(md.containsKey("part")){
			args.add(md.getString("part"));
			sql.append(" and part=? ");
		}
//		查询
		Long total=projectInvitationDao.queryForCountNum(BaseTools.countSql+sql.toString(), args.toArray());
		List<JSONObject> objlist=projectInvitationDao.queryForJsonListPage("select * "+sql.toString(), pageNum, pagesize, args.toArray());
		return BaseTools.getDatagridJson(total, objlist); 
	}
	
}