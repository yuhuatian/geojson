package com.joyce;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.joyce.entity.UserInfo;
import com.joyce.util.JsonUtil;

import java.util.*;

/**
 * @Author: Joyce
 * @Date: 2019/1/10 10:14
 * @AsUse:
 */
public class Test {

    public static void change(int a){
         a=10;
    }

    public static Comparator<String > comparator=new Comparator<String>() {
        @Override
        public int compare(String s, String t1) {
            return s.compareTo(t1);
        }
    };
    public static void main(String args[]) {
/*
        Map map = Maps.newHashMap();
        map.put("a","a");
        map.put("B","b");
        List<UserInfo> userInfoList= Lists.newArrayList();*/
        //  for(int i=0;i<5;i++){
         /*   UserInfo userInfo=UserInfo.builder().userName("dadwad ").build();
            userInfoList.add(userInfo);*/
        // }
     /*   System.out.println(JsonUtil.formatObjectToJsonString(userInfo));*/

        List<String> s = Arrays.asList("周一", "周二", "周三", "周四", "周五", "周六", "周日");

        s.sort(comparator);
        System.out.println(s.toString());

    }

}
