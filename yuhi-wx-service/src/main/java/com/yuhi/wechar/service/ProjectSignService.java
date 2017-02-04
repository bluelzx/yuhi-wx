package com.yuhi.wechar.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.wechar.WeiXinConstants;
import com.yuhi.wechar.dao.ProjectSignDao;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.facade.ProjectSignFacade;
import com.yuhi.wechar.util.BaseTools;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* owner服务实现

<!-- owner服务 -->
<dubbo:reference interface="com.yuhi.wechar.facade.ProjectSignFacade" id="projectSignFacade" />

	@Autowired
	private ProjectSignFacade projectSignFacade;

*/
@Service("projectSignService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.wechar.facade.ProjectSignFacade.class,version="1.0", protocol = {"rest", "dubbo"})
public class ProjectSignService implements ProjectSignFacade{
	/**
	 * 导入数据操作层
	 */
	@Autowired
	private ProjectSignDao projectSignDao;

	@Override
	@Transactional
	public boolean delete(String id)  throws Exception{
		return projectSignDao.delete(id)>-1;
	}

	@Override
	@Transactional
	public boolean save(JSONObject entity)  throws Exception{
		return projectSignDao.insert(entity)>-1;
	}

	@Override
	@Transactional
	public boolean Update(Map<String, Object> paramMap, String id)  throws Exception{
		return projectSignDao.update(paramMap, id)>-1;
	}

	@Override
	public JSONObject getObjByid(String id) throws Exception {
		return projectSignDao.getById(id);
	}

	@Override
	public List<JSONObject> getList() throws Exception {
		return projectSignDao.getList();
	}

	@Override
	@Transactional
	public int isExitsSignHistory(int i, String openid) {
		try {
			StringBuffer sb=new StringBuffer("SELECT ID FROM PROJECT_SIGN WHERE OPENID='"+openid+"' " +
											"AND TO_DAYS(CREATE_TIME) = TO_DAYS(NOW()) " +
											"AND TYPE='");
			if(i==2){ //判断签退前是否签到
				List<String> ids = projectSignDao.queryforListByType(String.class,sb.toString()+"1'");
				if(ids!=null&&ids.size()>0){   
					return WeiXinConstants.NO_SIGN;
				}
			}
			//签到处理 SELECT * FROM project_sign WHERE openid='' AND TO_DAYS(create_time) = TO_DAYS(NOW()) AND type='1'
			sb.append(i+"'");  
			List<String> list = projectSignDao.queryforListByType(String.class,sb.toString());
			if(list.size()>0){ //判断是否重复签
				return WeiXinConstants.REPLACE_SIGN;
			}
			projectSignDao.updateBySql("UPDATE PROJECT_PERSON SET ISOK='"+i+"' WHERE OPENID='"+openid+"'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return WeiXinConstants.SIGN_ERROR;
		}
		return WeiXinConstants.SIGN_OK;
	}

	@Override
	public String datagrid(MapData md, Integer pageNum, Integer pagesize)
			throws Exception {
		String sql="select * from PROJECT_SIGN";
		String[] args ={};
//		条件构造
//		md.containsKey("name")
		Long total=projectSignDao.queryForCountNum(sql, args);
		List<JSONObject> objlist=projectSignDao.queryForJsonListPage(sql, pageNum, pagesize, args);
		return BaseTools.getDatagridJson(total, objlist);
	}
	
}