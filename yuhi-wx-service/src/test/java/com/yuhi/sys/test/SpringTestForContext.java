package com.yuhi.sys.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.wechar.service.OwnerService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-context.xml"})
public class SpringTestForContext{

	@Autowired
	private OwnerService ownerservice;
	
	@Test
	public void insert() throws Exception{
		
		JSONObject entity=new JSONObject();
		entity.put("name", "青年荟客服中心");
		entity.put("id", "123");
//		ownerservice.save(entity);
//		ownerservice.getList();
//		ownerservice.getObjByid("tests");
//		ownerservice.delete("tests");
	}
	
	@Test
	public void del(){
	}
	
	@Test
	public void update(){
	}
	@Test
	public void show(){
	}
}
