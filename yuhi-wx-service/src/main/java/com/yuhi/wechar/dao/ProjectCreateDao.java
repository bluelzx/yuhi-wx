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
@Repository("projectCreateDao")
public class ProjectCreateDao extends JdbcTemplatesDao {
	
	private static final String SQL_SELECT = "SELECT * FROM PROJECT_CREATE";
	private static final String SQL_SELECT_BYID = "SELECT * FROM PROJECT_CREATE WHERE ID = ?";
	
	public List<JSONObject> getList(String sql,Object[] param) throws Exception {
		return super.queryForJsonList(SQL_SELECT+" where 1=1 "+sql,param);
	}
	public JSONObject getById(String id){
		return super.queryForJsonObject(SQL_SELECT_BYID, id);
	}

	@Override
	protected String setControllerTable() {
		// TODO Auto-generated method stub
		return "PROJECT_CREATE";
	}

	
}