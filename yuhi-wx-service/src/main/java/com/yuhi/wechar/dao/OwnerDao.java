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
@Repository("ownerDao")
public class OwnerDao extends JdbcTemplatesDao {
	
	private static final String SQL_SELECT = "SELECT ID,NAME,PHONE,PARTID,SEX,OPENID,ISOK,PARTDESC,PART FROM OWNER";
	private static final String SQL_SELECT_BYID = "SELECT ID,NAME,PHONE,PARTID,SEX,OPENID,ISOK,PARTDESC,PART FROM OWNER WHERE ID = ?";
	
	public List<JSONObject> getList() throws Exception {
		return super.queryForJsonList(SQL_SELECT);
	}
	
	public JSONObject getById(String id){
		return super.queryForJsonObject(SQL_SELECT_BYID, id);
	}
	
	public boolean countByOpenid(String openid){
		return super.queryForCountNum("SELECT COUNT(1) FROM OWNER WHERE OPENID=?", openid)>0;
	}
	
	public JSONObject queryByOpenid(String openid){
		return super.queryForJsonObject(SQL_SELECT+" WHERE OPENID=?", openid);
	}
	
	public List<String> getOwnerIdsbyPart(String part){
		return super.queryforListByType(String.class, "SELECT OPENID FROM OWNER WHERE PART=?", part);
	}
	
	@Override
	protected String setControllerTable() {
		// TODO Auto-generated method stub
		return "OWNER";
	}

	
}