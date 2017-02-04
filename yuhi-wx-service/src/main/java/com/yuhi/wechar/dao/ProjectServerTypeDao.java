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
@Repository("projectServerTypeDao")
public class ProjectServerTypeDao extends JdbcTemplatesDao {
	
	private static final String SQL_SELECT = "SELECT * FROM PROJECT_SERVER_TYPE";
	private static final String SQL_SELECT_BYID = "SELECT * FROM PROJECT_SERVER_TYPE WHERE ID = ?";
	
	public List<JSONObject> getList() throws Exception {
		return super.queryForJsonList(SQL_SELECT);
	}
	
	public JSONObject getById(String id){
		return super.queryForJsonObject(SQL_SELECT_BYID, id);
	}

	@Override
	protected String setControllerTable() {
		// TODO Auto-generated method stub
		return "PROJECT_SERVER_TYPE";
	}

	
}