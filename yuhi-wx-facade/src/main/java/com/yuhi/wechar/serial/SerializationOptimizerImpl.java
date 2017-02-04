package com.yuhi.wechar.serial;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;
import com.alibaba.fastjson.JSONObject;
import com.yuhi.wechar.entity.InvitationtreeNode;
import com.yuhi.wechar.entity.MapData;
import com.yuhi.wechar.entity.TreeNode;
import com.yuhi.wechar.entity.resultdata;

public class SerializationOptimizerImpl implements SerializationOptimizer {

    public Collection<Class> getSerializableClasses() {
        List<Class> classes = new LinkedList<Class>();
        //这里可以把所有需要进行序列化的类进行添加
        classes.add(JSONObject.class);
        classes.add(resultdata.class);
        classes.add(TreeNode.class);
        classes.add(MapData.class);
        classes.add(Long.class);
        classes.add(HashMap.class);
        classes.add(ArrayList.class);
        classes.add(InvitationtreeNode.class);
        return classes;
    }
}
