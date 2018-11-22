package com.imooc.rabbit.utils;


import java.util.List;
import java.util.Map;
 
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.imooc.rabbit.entity.Order;

public class FastJsonConvertUtil {
	
	 public static Order convertJSONToObject(String message,Object obj){
	        Order order = JSON.parseObject(message, new TypeReference<Order>() {});
	        return order;
	    }

	    public static String convertObjectToJSON(Object obj){
	        String text = JSON.toJSONString(obj);
	        return text;
	    }

}
