package com.yuhi.wechar.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.alibaba.fastjson.JSONObject;
import com.yuhi.base.JdbcTemplatesDao;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* 表数据操作
*/
@Repository("projectCardStatisticsDao")
public class ProjectCardStatisticsDao extends JdbcTemplatesDao {

	private static final String SQL_SELECT = "SELECT ID,STARTTIME,ENDTIME,CARDTYPE,CARDSTATE,PPERSON,OWNER,PCSERVER,ISPAY,REMINDSTATE,PART FROM PROJECT_CARD_STATISTICS";
	private static final String SQL_SELECT_BYID = "SELECT ID,STARTTIME,ENDTIME,CARDTYPE,CARDSTATE,PPERSON,OWNER,PCSERVER,ISPAY,REMINDSTATE,PART FROM PROJECT_CARD_STATISTICS WHERE ID = ?";
	
	public List<JSONObject> getList() throws Exception {
		return super.queryForJsonList(SQL_SELECT);
	}
	
	public JSONObject getById(String id){
		return super.queryForJsonObject(SQL_SELECT_BYID, id);
	}

	@Override
	protected String setControllerTable() {
		// TODO Auto-generated method stub
		return "PROJECTCARDSTATISTICS";
	}

	
}