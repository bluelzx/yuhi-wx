package com.buss.project.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.input.NullInputStream;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.Client;

/**
 * 对消息的管理
 * @author JueYue
 * @date 2013-9-28
 * @version 1.0
 */
public class MsgManager {
	
	private static MsgManager instance = new MsgManager();
	
	private MsgManager(){
	}
	
	
	public static MsgManager getInstance(){
		return instance;
	}
	/**
	 * partid,Msg
	 */
	private static Map<String,Integer> map = new HashMap<String,Integer>();
	
	/**
	 * 
	 * @param sessionId
	 * @param client
	 */
	public void addMsg(String deptid){
		if(StringUtils.isNotEmpty(deptid)){
			Integer count= map.get(deptid);
			if(count!=null&&count>0){
				map.put(deptid, ++count);
			}else map.put(deptid, 1);
		}
	}
	/**
	 * sessionId
	 */
	public void removeMsg(String deptid){
		map.remove(deptid);
	}
	/**
	 * 
	 * @param sessionId
	 * @return
	 */
	public Integer getMsg(String deptid){
		return map.get(deptid);
	}
	/**
	 * 
	 * @return
	 */
	public Collection<Integer> getAllMsg(){
		return map.values();
	}

}