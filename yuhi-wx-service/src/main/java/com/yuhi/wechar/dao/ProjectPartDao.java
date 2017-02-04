package com.yuhi.wechar.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.base.JdbcTemplatesDao;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* 表数据操作
*/
@Repository("projectPartDao")
public class ProjectPartDao extends JdbcTemplatesDao {
	
	private static final String SQL_SELECT = "SELECT * FROM PROJECT_PART";
	private static final String SQL_SELECT_BYID = "SELECT * FROM PROJECT_PART WHERE ID = ?";
	
	public List<JSONObject> getList() throws Exception {
		return super.queryForJsonList(SQL_SELECT);
	}
	
	public JSONObject getById(String id){
		return super.queryForJsonObject(SQL_SELECT_BYID, id);
	}

	@Override
	protected String setControllerTable() {
		// TODO Auto-generated method stub
		return "PROJECT_PART";
	}
	/**
	 * 获取地区详细描述
	 * @param roomid
	 * @return
	 */
	public String getpublicplace(String roomsbyid) {
		StringBuilder sb=new StringBuilder("");
		JSONObject entity=getById(roomsbyid);
		sb.append(entity.getString("region"));
		String parendid=entity.getString("parentsid");
		if(StringUtils.isNotEmpty(parendid)){
			while(!parendid.equals("0")){
				entity=getById(parendid);
				parendid=entity.getString("parentsid");
				sb.insert(0,entity.getString("region"));
			}
		}
		return sb.toString();
	}

	
}