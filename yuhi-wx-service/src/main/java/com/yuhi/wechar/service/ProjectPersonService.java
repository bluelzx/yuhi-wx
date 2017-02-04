package com.yuhi.wechar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.wechar.dao.ProjectPersonDao;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.facade.ProjectPersonFacade;
import com.yuhi.wechar.util.BaseTools;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* owner服务实现

<!-- owner服务 -->
<dubbo:reference interface="com.yuhi.wechar.facade.ProjectPersonFacade" id="projectPersonFacade" />

	@Autowired
	private ProjectPersonFacade projectPersonFacade;

*/
@Service("projectPersonService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.wechar.facade.ProjectPersonFacade.class,version="1.0", protocol = {"rest", "dubbo"})
public class ProjectPersonService implements ProjectPersonFacade{
	/**
	 * 导入数据操作层
	 */
	@Autowired
	private ProjectPersonDao projectPersonDao;

	@Override
	public boolean delete(String id)  throws Exception{
		return projectPersonDao.delete(id)>-1;
	}

	@Override
	@Transactional
	public boolean save(JSONObject entity)  throws Exception{
		return projectPersonDao.insert(entity)>-1;
	}

	@Override
	@Transactional
	public boolean Update(Map<String, Object> paramMap, String id)  throws Exception{
		return projectPersonDao.update(paramMap, id)>-1;
	}

	@Override
	public JSONObject getObjByid(String id) throws Exception {
		return projectPersonDao.getById(id);
	}

	@Override
	public List<JSONObject> getList() throws Exception {
		return projectPersonDao.getList();
	}

	@Override
	public String isBudding(String openid)  throws Exception{
			String part=projectPersonDao.
					queryForString("SELECT part FROM PROJECT_PERSON WHERE OPENID=?",
							openid);
				if(StringUtils.isNotEmpty(part)){
					return part;
				}
		return null;
	}

	@Override
	public boolean isBoss(String openid)  throws Exception{
			String entitycount=projectPersonDao.
					queryForString("SELECT (SELECT COUNT(1) FROM PROJECT_CARD WHERE OPENID=?)+(SELECT COUNT(1) FROM PROJECT_CREATE WHERE OPENID=?) COUNTS",
							openid,openid);
			int nums=Integer.valueOf(entitycount);
			return nums>0;
	}

	@Override
	public JSONObject getppObjByOpenid(String openid)  throws Exception{
		JSONObject pp=projectPersonDao.queryForJsonObject("SELECT * FROM PROJECT_PERSON where openid=?", openid);
		return pp!=null?pp:null;
	}

	@Override
	public String datagrid(MapData md, Integer pageNum, Integer pagesize)
			throws Exception {
		StringBuffer sql=new StringBuffer("from PROJECT_PERSON where 1=1 ");
		List<String> args=new ArrayList<String>(md.size());
//		条件构造
		if(md.containsKey("part")){
			args.add(md.getString("part"));
			sql.append(" and part=? ");
		}
//		查询
		Long total=projectPersonDao.queryForCountNum(BaseTools.countSql+sql.toString(), args.toArray());
		List<JSONObject> objlist=projectPersonDao.queryForJsonListPage("select * "+sql.toString(), pageNum, pagesize, args.toArray());
		return BaseTools.getDatagridJson(total, objlist); 
	}
	
}