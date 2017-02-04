package com.yuhi.wechar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.ClientErrorException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.util.DateUtils;
import com.yuhi.util.IDCreate;
import com.yuhi.wechar.dao.OwnerDao;
import com.yuhi.wechar.dao.ProjectOwnermesDao;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.facade.OwnerFacade;
import com.yuhi.wechar.util.BaseTools;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* owner服务实现

<!-- owner服务 -->
<dubbo:reference interface="com.yuhi.wechar.facade.OwnerFacade" id="ownerFacade" />

	@Autowired
	private OwnerFacade ownerFacade;

*/
@Service("ownerService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.wechar.facade.OwnerFacade.class,version="1.0",protocol = {"rest", "dubbo"})
public class OwnerService implements OwnerFacade{
	/**
	 * 导入数据操作层
	 */
	@Autowired
	private OwnerDao ownerDao;
	@Autowired
	private ProjectOwnermesDao projectOwnermesDao;
	
	@Override
	@Transactional
	public void sendMesforOwner(String templateid, String deptid)
			throws Exception {
		//TODO 等待系统解耦
//		JSONObject ownerMesentity=projectOwnermesDao.getById(templateid);
//		if(ownerMesentity!=null){
//			//构建模板消息
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("first", ownerMesentity.getString("title"));
//			map.put("keyword1",ownerMesentity.getString("title"));
//			map.put("keyword2", DateUtils.getDate(null));
//			map.put("keyword3", ownerMesentity.getString("content"));
//			map.put("remark", StringUtils.isNotEmpty(ownerMesentity.getString("remark"))?ownerMesentity.getString("remark"):"感谢你对元海集团的大力支持!");
//			List<String> ownerid=getOwnerIds(deptid);
			//微信推送
//			for (String string : ownerid) {
//			sendMes(map,string,"FYLPNEQZJta52aSYo6NcH1lVR8IR1JU0GStszsjvVAs","域名"+entity.getTemplatehtml()+".html");
//			}
//		}
	}

	@Override
	public JSONObject isBidding(String id) throws Exception {
		// TODO Auto-generated method stub
		return ownerDao.getById(id);
	}

	@Override
	public JSONObject getOwnerObjByOpenid(String openid) throws Exception {
		// TODO Auto-generated method stub
		return ownerDao.queryByOpenid(openid);
	}

	@Override
	@Transactional
	public Boolean DoUnBidding(String id) throws Exception {
		// TODO Auto-generated method stub
		return ownerDao.delete(id)!=-1;
	}

	@Override
	@Transactional
	public Boolean DoBidding(MapData md) throws Exception {
		// TODO Auto-generated method stub
		md.put("id", IDCreate.uuid());
		md.put("isok", 1);
		return ownerDao.insert(new JSONObject(md))!=-1;
	}

	@Override
	@Transactional
	public Boolean DoUpdateOwnerInfo(MapData md) throws Exception {
		// TODO Auto-generated method stub
		String id=md.getString("id");
		md.setUnContainKey("id");
		return ownerDao.update(md.getMap(), id)!=-1;
	}

	@Override
	public List<String> getOwnerIds(String deptid)  throws Exception {
		String sql="select id from owner where part=?";
		return ownerDao.queryforListByType(String.class, sql, deptid);
	}

	@Override
	public List<JSONObject> getOwnerList(int pagenum, int pagesize) throws Exception {
		if(pagenum==0||pagesize==0)ownerDao.getList();
		String sql="select * from owner ";
		return ownerDao.queryForJsonListPage(sql, pagenum, pagesize);
	}

	@Override
	public String datagrid(MapData md, Integer pageNum, Integer pagesize)
			throws Exception {
		StringBuffer sql=new StringBuffer("from owner where 1=1 ");
		List<String> args=new ArrayList<String>(md.size());
//		条件构造
		if(md.containsKey("part")){
			args.add(md.getString("part"));
			sql.append(" and part=? ");
		}
//		查询
		Long total=ownerDao.queryForCountNum(BaseTools.countSql+sql.toString(), args.toArray());
		List<JSONObject> objlist=ownerDao.queryForJsonListPage("select * "+sql.toString(), pageNum, pagesize, args.toArray());
		return BaseTools.getDatagridJson(total, objlist); 
	}

	
	
}