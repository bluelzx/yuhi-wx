package com.yuhi.wechar.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class BaseTools {

	public final static String countSql="select count(1) ";
	
	
	public static String getDatagridJson(Long total,List<JSONObject> objlist){
		JSONObject obj=new JSONObject();
		obj.put("total", total);
		obj.put("rows", objlist);
//		return JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
		return obj.toString();
	}
	
	
}
