package com.joyce.util;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @Author: Joyce
 * @Date: 2019/1/10 9:50
 * @AsUse:
 */
public class JsonUtil {


    /**
     * 得到格式化JSON字符串
     * @param object
     * @return
     */
    public static String  formatObjectToJsonString(Object object){
        String objString = object.toString();
        System.out.println(objString);
        JsonParser jsonParser=new JsonParser();
        JsonObject jsonObject=jsonParser.parse(objString).getAsJsonObject();
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonObject);
    }


}
