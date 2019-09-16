package com.joyce.redis;

import redis.clients.jedis.Jedis;

public class RedisDenoTest {
    public void getRedisData(){
        Jedis jedis=new Jedis("127.0.0.1");
        System.out.println(jedis.get("hello"));;
    }

}
