package com.yuhi.wechar.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.wechar.dao.ProjectPartDao;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.entity.TreeNode;
import com.yuhi.wechar.facade.ProjectPartFacade;
import com.yuhi.wechar.util.BaseTools;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* owner服务实现

<!-- owner服务 -->
<dubbo:reference interface="com.yuhi.wechar.facade.ProjectPartFacade" id="projectPartFacade" />

	@Autowired
	private ProjectPartFacade projectPartFacade;

*/
@Service("projectPartService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.wechar.facade.ProjectPartFacade.class,version="1.0", protocol = {"rest", "dubbo"})
public class ProjectPartService implements ProjectPartFacade{
	/**
	 * 导入数据操作层
	 */
	@Autowired
	private ProjectPartDao projectPartDao;

	@Override
	@Transactional
	public boolean delete(String id)  throws Exception{
		return projectPartDao.delete(id)>-1;
	}

	@Override
	@Transactional
	public boolean save(JSONObject entity)  throws Exception{
		return projectPartDao.insert(entity)>-1;
	}

	@Override
	@Transactional
	public boolean Update(Map<String, Object> paramMap, String id)  throws Exception{
		return projectPartDao.update(paramMap, id)>-1;
	}

	@Override
	public JSONObject getObjByid(String id) throws Exception {
		return projectPartDao.getById(id);
	}

	@Override
	public List<JSONObject> getList() throws Exception {
		return projectPartDao.getList();
	}
	
	@Override
	public List<TreeNode> getpartNodeByType(String nodeid)  throws Exception {
		if(StringUtils.isEmpty(nodeid))return null;
		List<TreeNode> treelist=projectPartDao.getJdbcTemplate().query("SELECT ID,REGION,DESCS FROM PROJECT_PART WHERE PARENTSID=?",new String[]{nodeid},new RowMapper<TreeNode>(){
			@Override
			public TreeNode mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				return new TreeNode(rs.getString("ID"), rs.getString("REGION"), rs.getString("DESCS"));
			}
		});
		if(treelist!=null&&treelist.size()>0)return treelist;
		return null;
	}

	@Override
	public List<Map<String, Object>> getpartNodeBywirter(String wirter,
			String part)  throws Exception {
		String sql="select id,region,parentsid from project_part where part=? and region like ?";
		List<Map<String, Object>> map = projectPartDao.queryforMap(sql, part,wirter);
		return map!=null&&map.size()>0?map:null;
	}

	@Override
	@Transactional
	public void deletedigui(String pnodesid)  throws Exception {
				//获取父权限
				List<TreeNode> pnodes=projectPartDao.queryforListByType(TreeNode.class, 
						"SELECT E.ID,E.REGION TEXT,DESCS `DESC` FROM PROJECT_PART E WHERE E.PARENTSID=?"
						,pnodesid);
				//获取子权限
				for (TreeNode treeNode : pnodes) {
					treeNode.setChildren(getpartNodeByType(treeNode.getId()));
				}
				//删除
				for (TreeNode treeNode : pnodes) {
					deleteNode(treeNode);
				}
	}

	
	@Override
	@Transactional
	public void importData(List<String[]> excelToArrayList)  throws Exception {
				int size=excelToArrayList.size();
				for (int j = 1; j < size; j++) {
					String parentid="0";
					String[] strings=excelToArrayList.get(j);
					if(strings!=null&&strings.length>0){
						for (int i = 0; i < strings.length-1; i++) {
							parentid=isexits(strings[i],parentid,strings[strings.length-1]);
						}
					}
				}
	}
	/**
	 * 是否存在节点
	 * @param region
	 * @param parentid
	 * @param part
	 * @return
	 * @throws Exception
	 */
	private String isexits(String region,String parentid, String part) throws Exception{
		List<String> entitylist=projectPartDao.
				queryforListByType(String.class,"select id from project_part where region=? and parentsid=?",
						region,parentid);
		String entityid="";
		if(entitylist!=null&&entitylist.size()>0){
			entityid=entitylist.get(0);
		}else{
			JSONObject entity=new JSONObject();
			entity.put("parentsid", parentid);
			entity.put("id", entityid);
			entity.put("region", region);
			entity.put("part", part);
			save(entity);
		}
		return entityid;
	}
	/**
	 * 递归删除
	 * @param node
	 * @throws Exception
	 */
	private void deleteNode(TreeNode node) throws Exception{
		if(node.getChildren()!=null&&node.getChildren().size()>0){
			for (TreeNode n : node.getChildren()) {
				deleteNode(n);
			}
		}else{
			delete(node.getId());
		}
	}

	@Override
	public String datagrid(MapData md, Integer pageNum, Integer pagesize)
			throws Exception {
		StringBuffer sql=new StringBuffer("from project_part where 1=1 ");
		List<String> args=new ArrayList<String>(md.size());
//		条件构造
		if(md.containsKey("part")){
			args.add(md.getString("part"));
			sql.append(" and part=? ");
		}
//		查询
		Long total=projectPartDao.queryForCountNum(BaseTools.countSql+sql.toString(), args.toArray());
		List<JSONObject> objlist=projectPartDao.queryForJsonListPage("select * "+sql.toString(), pageNum, pagesize, args.toArray());
		return BaseTools.getDatagridJson(total, objlist);  
	}
}