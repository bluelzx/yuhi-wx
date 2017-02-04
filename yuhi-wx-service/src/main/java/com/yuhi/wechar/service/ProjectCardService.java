package com.yuhi.wechar.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.yuhi.wechar.WeiXinConstants;
import com.yuhi.wechar.dao.OwnerDao;
import com.yuhi.wechar.dao.ProjectCardDao;
import com.yuhi.wechar.dao.ProjectCardObjectionDao;
import com.yuhi.wechar.dao.ProjectCreateDao;
import com.yuhi.wechar.dao.ProjectPickHistoryDao;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.entity.resultdata;
import com.yuhi.wechar.facade.ProjectCardFacade;
import com.yuhi.wechar.util.BaseTools;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* owner服务实现

<!-- owner服务 -->
<dubbo:reference interface="com.yuhi.wechar.facade.ProjectCardFacade" id="projectCardFacade" />

	@Autowired
	private ProjectCardFacade projectCardFacade;

*/
@Service("projectCardService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.wechar.facade.ProjectCardFacade.class,version="1.0",protocol = {"rest", "dubbo"})
public class ProjectCardService implements ProjectCardFacade{
	/**
	 * 导入数据操作层
	 */
	@Autowired
	private ProjectCardDao projectCardDao;
	@Autowired
	private ProjectCreateDao projectCreateDao;
	@Autowired
	private ProjectPickHistoryDao projectPickHistoryDao;
	@Autowired
	private OwnerDao ownerDao;
	@Autowired
	private ProjectCardObjectionDao projectCardObjectionDao;
	
	@Override
	@Transactional
	public boolean Update(Map<String, Object> paramMap, String id)  throws Exception{
		return projectCardDao.update(paramMap, id)>-1;
	}
	@Override
	@Transactional
	public boolean save(JSONObject entity)  throws Exception{
		return projectCardDao.insert(entity)>-1;
	}

	@Override
	public JSONObject getObjByid(String id) throws Exception {
		return projectCardDao.getById(id);
	}

	@Override
	public List<JSONObject> getDatabySql(String sql, String... args) {
		return projectCardDao.queryForJsonList(sql, args);
	}

	@Override
	public resultdata getReportData(Date time, String deptid) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		String timesql=" AND  YEAR(CREATE_TIME)='"+cal.get(Calendar.YEAR)+"' AND  MONTH(CREATE_TIME) ='"+(cal.get(Calendar.MONTH)+1)+"'";
		//初始化柱状图数据
		StringBuffer sql=new StringBuffer(" SELECT DAY(CREATE_TIME) DAYS,COUNT(1) COUNT FROM `PROJECT_CARD` P WHERE 1=1 ");
		sql.append(timesql);
//		if(StringUtil.isNotEmpty(projectCard.getPctype()))sql.append(" and p.pctype = "+projectCard.getPctype()+" ");
//		if(StringUtils.isNotEmpty(projectCard.getState()))sql.append(" and p.state ='"+projectCard.getState()+"' ");
//		if(StringUtils.isNotEmpty(projectCard.getPhone()))sql.append(" and p.phone like '"+projectCard.getPhone()+"%' ");
//		if(StringUtils.isNotEmpty(projectCard.getIsok()))sql.append(" and p.isok ='"+projectCard.getIsok()+"' ");
//		if(StringUtil.isNotEmpty(projectCard.getThroughAudit()))sql.append(" and p.throughAudit ='"+projectCard.getThroughAudit()+"' ");
//		if(StringUtil.isNotEmpty(projectCard.getIsPay()))sql.append(" and p.isPay ='"+projectCard.getIsPay()+"' ");
		sql.append(" AND PART='"+deptid+"' GROUP BY DAYS");
		List<Map<String, Object>>  pc=projectCardDao.queryforMap(sql.toString());
		if(pc.size()<=0)return null;
		int days=getDaysByYearMonth(cal.get(Calendar.YEAR),(cal.get(Calendar.MONTH)+1));
		List<Long> datas=getvalueAry(pc,days);//月份方式放置数据
		//初始化饼图统计数据
		sql=new StringBuffer(" SELECT CONCAT(STATE,',',COUNT(1)) COUNT FROM `PROJECT_CARD` P WHERE 1=1 ");
		sql.append(timesql);
		sql.append(" AND PART='"+deptid+"' GROUP BY STATE");
		pc=projectCardDao.queryforMap(sql.toString());
		return new resultdata(getdaysAry(days),datas,serzPieData(pc));
	}
	private List<String> serzPieData(final List<Map<String, Object>> pc){
		List<String> arrays=new ArrayList<String>(6);
		for (int i = 1; i <= 6; i++)arrays.add("0");
		for (int i = 0; i < pc.size(); i++) {
			String s=(String) pc.get(i).get("COUNT");
			String[] ary=s.split(",");
			arrays.set(Integer.valueOf(ary[0])-1,ary[1]);
		}
		return arrays;
	}
	private List<String> getdaysAry(final int count){
		List<String> arrays=new ArrayList<String>(count);
		for (int i = 1; i <= count; i++) {
			arrays.add(i+"");
		}
		return arrays;
	}
	private List<Long> getvalueAry(final List<Map<String, Object>> pc,final int count){
		List<Long> arrays=new ArrayList<Long>(count);
		for (int i = 1; i <= count; i++)arrays.add(0l);
		for (int i = 0; i < pc.size(); i++) {
			Integer index=(Integer) pc.get(i).get("days");
			arrays.set(index-1, (Long)pc.get(i).get("count"));
		}
		return arrays;
	}
	 /** 
     * 根据年 月 获取对应的月份 天数 
     * */  
	private int getDaysByYearMonth(int year, int month) {  
          
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }
	
	@Override
	public String datagrid(MapData md, Integer pageNum, Integer pagesize)
			throws Exception {
		StringBuffer sql=new StringBuffer("from PROJECT_CARD p,owner ow,project_person ppe,project_part pp where p.ownerid=ow.id and p.distribution_Openid=ppe.openid and p.part=pp.id ");
		List<String> args=new ArrayList<String>(md.size());
//		条件构造
		String begin=md.getString("createTime_begin");
		if(StringUtils.isNotEmpty(begin)){
			String end=md.getString("createTime_end");
			args.add(begin);
			args.add(end);
			sql.append(" and p.create_Time>=? AND p.create_Time<=? ");
		}
		if(md.containsKey("pctype"))sql.append(" and p.pctype = "+md.getString("pctype")+" ");
		if(md.containsKey("ownerid"))sql.append(" and ow.name like '%"+md.getString("ownerid")+"%' ");
		if(md.containsKey("state"))sql.append(" and p.state ='"+md.getString("state")+"' ");
		if(md.containsKey("phone"))sql.append(" and p.phone like '"+md.getString("phone")+"%' ");
		if(md.containsKey("isok"))sql.append(" and p.isok ='"+md.getString("isok")+"' ");
		if(md.containsKey("throughaudit"))sql.append(" and p.throughAudit ='"+md.getString("throughaudit")+"' ");
		if(md.containsKey("ispay"))sql.append(" and p.isPay ='"+md.getString("ispay")+"' ");
		if(md.containsKey("id"))sql.append(" and p.id like '%"+md.getString("id")+"%' ");
		if(md.containsKey("roomid"))sql.append(" and p.roomid like '"+md.getString("roomid")+"%' ");
		
		if(md.containsKey("part")){
			args.add(md.getString("part"));
			sql.append(" and p.part=? ");
		}
//		查询
		Long total=projectCardDao.queryForCountNum(BaseTools.countSql+sql.toString(), args.toArray());
		sql.append(" order by p.create_Time desc");
		String selectSql="select p.id,ow.name owner,p.create_Time,p.handle_Time,p.distribution_Time,p.get_Time,p.finished_Time,ppe.name,p.isok,p.content,p.state,p.propertyobjectionsid,p.ownerobjectionid,p.successlevel,p.propertyobjectionfid,p.companyobjectionfid,p.descs,p.edition,p.starttime,p.endtime,p.phone,p.otherContact,p.invitationid,p.reminderstate,p.throughAudit,pp.region,p.isPay,p.pctype,p.pcserver,p.roomid ";
		List<JSONObject> objlist=projectCardDao.queryForJsonListPage(selectSql+sql.toString(), pageNum, pagesize, args.toArray());
		return BaseTools.getDatagridJson(total, objlist); 
	}

	@Override
	@Transactional
	public boolean PCthroughAudit(String id, String isok) throws Exception {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("throughAudit", isok);
		return projectCardDao.update(paramMap, id)!=-1;
	}

	@Override
	public JSONObject getProjectCardDetail(String projectCardid)
			throws Exception {
		// TODO Auto-generated method stub
		String hql="SELECT O.PARTDESC,O.PHONE,O.NAME,PC.STARTTIME,PC.ENDTIME,PC.OTHERCONTACT,PC.STATE,PC.STATE,PC.CONTENT,PC.DESCS FROM PROJECT_CARD PC,OWNER O WHERE PC.OWNERID=O.ID AND PC.ID=?";
		return projectCardDao.queryForJsonObject(hql, projectCardid);
	}

	@Override
	public List<JSONObject> getProjectCardBytype(String personid,
			boolean isowner) throws Exception {
		String sql=isowner?"SELECT COUNT(1) count,state FROM `project_card` where ownerid=? GROUP BY state ORDER BY state":" SELECT COUNT(1) count,state FROM `project_card` where distribution_openid=?  GROUP BY state  ORDER BY state";
		return projectCardDao.queryForJsonList(sql, personid);
	}

	@Override
	@Transactional
	public void closeProjectCard(String id) {
			JSONObject entity=projectCardDao.getById(id);
			if(entity.getInteger("reminderstate")==0){
				Map<String, Object> param=new HashMap<String, Object>();
				param.put("isok", 0);
				param.remove("id");
				//TODO 微信催单
				projectCardDao.update(param, id);
			}
	}
	
	@Override
	@Transactional
	public void confirmpay(String id, String paytype) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isPay",paytype);
		projectCardDao.update(map, id);
	}
	
	@Override
	@Transactional
	public MapData updateProjectState(MapData md,String id, Integer type, String openid)
			throws Exception {
			MapData resultdata=new MapData();
			JSONObject entity = projectCardDao.getById(id);
			Map<String,Object> param=new HashMap<String, Object>();
			String dateStr=DateUtils.datetimeFormat.format(new Date());
			//如果拒单   删除单记录返回重新分配状态
			if(type==WeiXinConstants.NOGET_PROJECTCARD){
				JSONObject createentitys=new JSONObject();
				createentitys.put("ID", entity.getString("id"));
				createentitys.put("ROOMID",entity.getString("roomid"));
				createentitys.put("PART", entity.getString("part"));
				createentitys.put("CONTENT", entity.getString("content"));
				createentitys.put("OWNERID", entity.getString("ownerid"));
				createentitys.put("DESCS", entity.getString("descs"));
				createentitys.put("CREATE_DATE", entity.getString("create_time"));
				createentitys.put("ENDTIME", entity.getString("endtime"));
				createentitys.put("INVITATIONID", entity.getString("invitationid"));
				createentitys.put("STARTTIME", entity.getString("starttime"));
				createentitys.put("PHONE", entity.getString("phone"));
				createentitys.put("OTHERCONTACT", entity.getString("othercontact"));
				createentitys.put("PCSERVER", entity.getString("pcserver"));
				createentitys.put("PCTYPE", entity.getString("pctype"));
				createentitys.put("PUBLICPLACE", entity.getString("publicplace"));
				projectCreateDao.insert(createentitys);
				projectCardDao.delete(id);
				//历史记录
				String pickdescs=md.containsKey("pickdescs")?md.getString("pickdescs"):"";
				JSONObject pick=new JSONObject();
				pick.put("PCID",entity.getString("id"));
				pick.put("PICKDESCS",pickdescs);
				pick.put("PPNAME",entity.getString("ownerid"));
				pick.put("PPOPENID",entity.getString("distribution_openid"));
				pick.put("TIME",dateStr);
				pick.put("TYPEID",0);
				projectPickHistoryDao.insert(pick);
				//拒单消息提醒
				JSONObject owner = ownerDao.getById(entity.getString("ownerid"));
				//整理返回结果resulttype
				resultdata.put("resulttype", WeiXinConstants.NOGET_PROJECTCARD);
				resultdata.put("distribution_openid",entity.getString("distribution_openid"));
				resultdata.put("part",entity.getString("part"));
				resultdata.put("ownerid",owner.getString("openid"));
				resultdata.put("pcid",entity.getString("id"));
				//模板消息
				resultdata.put("first", "业主您好,您收到拒单通知,正在为你重新分配工程人员");
				resultdata.put("keyword1",  "服务单");
				resultdata.put("keyword2", owner.getString("name"));
				resultdata.put("keyword3", entity.getString("phone"));
				resultdata.put("keyword4", entity.getString("publicplace"));
				resultdata.put("keyword5", StringUtils.isEmpty(pickdescs)?"工程材料暂缺,请耐心等待！":pickdescs);
				resultdata.put("remark", "感谢您对元海集团的大力支持！");
				
//				
			}else{
				switch (type) {
				case WeiXinConstants.GET_PROJECTCARD:
					param.put("HANDLE_TIME",DateUtils.datetimeFormat.format(new Date()));;
					param.put("STATE","2"); //待办理
					//记录
					JSONObject pick=new JSONObject();
					pick.put("PCID",entity.getString("id"));
					pick.put("PPNAME",entity.getString("ownerid"));
					pick.put("PPOPENID",entity.getString("distribution_openid"));
					pick.put("TYPEID",1);
					projectPickHistoryDao.insert(pick);
					//整理返回结果
					resultdata.put("resulttype", WeiXinConstants.GET_PROJECTCARD);
					resultdata.put("distribution_openid",entity.getString("distribution_openid"));
					resultdata.put("pcid",entity.getString("id"));
					
					resultdata.put("first", "您已经成功接单，上门前请提前1小时联系客户。");
					resultdata.put("keyword1", entity.getString("id"));
					resultdata.put("keyword2", dateStr);
					resultdata.put("keyword3", entity.getString("publicplace"));
					resultdata.put("keyword4", entity.getString("id"));
					resultdata.put("keyword5", entity.getInteger("pctype")==1?"公共单":"服务单");
					resultdata.put("remark", "点击“详情”查看完整订单信息。");	
					break;
				case WeiXinConstants.START_PROJECRCARD:
					param.put("GET_TIME",dateStr);
					param.put("HANDLE_TIME",dateStr);
					param.put("STATE",3);//办理中
					
					//整理返回结果
					resultdata.put("resulttype", WeiXinConstants.START_PROJECRCARD);
					
					break;
				case WeiXinConstants.FINISHED_PROJECTCARD:
					param.put("GET_TIME",dateStr);
					param.put("FINISHED_TIME",dateStr);
					if(entity.getInteger("pctype")==0){
						param.put("STATE",4);//待评价
						//整理返回结果
						resultdata.put("pctype", "0");
					}else{
						param.put("STATE",6);//公共单完结
						resultdata.put("pctype", "1");
					}
					
					JSONObject oe=ownerDao.getById(entity.getString("ownerid"));
					//整理返回结果
					resultdata.put("distribution_openid",entity.getString("distribution_openid"));
					resultdata.put("resulttype", WeiXinConstants.FINISHED_PROJECTCARD);
					resultdata.put("ownerid", oe.getString("openid"));
					
					resultdata.put("first", "尊敬的业主, 您的工单已完成！");
					resultdata.put("keyword1", entity.getString("id"));
					resultdata.put("keyword2", "请对此次服务进行评价！");
					resultdata.put("keyword3", dateStr);
					resultdata.put("remark", "感谢你对元海集团的大力支持!");
					break;
				case WeiXinConstants.EVALUATE_PROJECT:
					String evaluatecontent=md.containsKey("evaluatecontent")?md.getString("evaluatecontent"):"";
					JSONObject pcoentity=new JSONObject();
					pcoentity.put("CREATETIME",dateStr);
					pcoentity.put("PROJECTCARDID",id);
					pcoentity.put("STATUS",1);
					pcoentity.put("TYPEID",2); //保修方意见
					pcoentity.put("OTHERCONTENT",evaluatecontent);
					int Objectionid=projectCardObjectionDao.insertOrReturnId(pcoentity);
					
					param.put("THROUGHAUDIT",0);
					param.put("STATE","5");//待回访
					param.put("OWNEROBJECTIONID",Objectionid+"");
					//整理返回结果
					resultdata.put("resulttype", WeiXinConstants.EVALUATE_PROJECT);
					break;
				}
				projectCardDao.update(param, id);
			}
			return resultdata;
	}
	
	@Override
	@Transactional
	public void CallbackprojectCard(String id,String othercontent) throws Exception {
		JSONObject projectcardobjection = new JSONObject();
		projectcardobjection.put("createTime", DateUtils.datetimeFormat.format(new Date()));
		projectcardobjection.put("projectCardid", id);
		projectcardobjection.put("typeid", 3);
		projectcardobjection.put("othercontent", othercontent);
		projectcardobjection.put("usercode", "admin");
		projectCardObjectionDao.insert(projectcardobjection);
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("state","6");
		projectCardDao.update(map,id);
	}

	@Override
	public List<JSONObject> getppProjectListData(String openid, String state)
			throws Exception {
		StringBuffer sql=new StringBuffer("");
		List<JSONObject> obj=new ArrayList<JSONObject>();
		List<String> args=new ArrayList<String>(6);
		if(StringUtils.isNotEmpty(openid)){
			String[] split = state.split(",");
			sql.append(" and distribution_openid=? ");
			sql.append(" and isok=1 ");
			args.add(openid);
			if(split.length>1){
				sql.append(" and (state=? or state=? or state=?) ");
				args.add(split[0]);
				args.add(split[1]);
				args.add(split[2]);
			}else{
				sql.append(" and state=?  ");
				args.add(split[0]);
			}
			sql.append(" ORDER BY CREATE_TIME DESC  ");
			obj = projectCardDao.getList(sql.toString(),args.toArray());
		}else throw new Exception("请求非法");
		return obj;
	}

	@Override
	public List<JSONObject> getownerListData(String ownerid, String state)
			throws Exception {
		StringBuffer sql=new StringBuffer("");
		List<JSONObject> obj=new ArrayList<JSONObject>();
		List<String> args=new ArrayList<String>(6);
		if(StringUtils.isNotEmpty(ownerid)){
			String[] split = state.split(",");
			sql.append(" and ownerid=? ");
			sql.append(" and isok=1 ");
			args.add(ownerid);
			if(split.length>1){
				sql.append(" and (state=? or state=?) ");
				args.add(split[0]);
				args.add(split[1]);
			}else{
				sql.append(" and state=?  ");
				args.add(split[0]);
			}
			sql.append(" ORDER BY CREATE_TIME DESC  ");
			obj = projectCardDao.getList(sql.toString(),args.toArray());
		}else throw new Exception("请求非法");
		return obj;
	}
	
}