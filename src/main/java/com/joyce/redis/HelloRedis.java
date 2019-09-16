package com.joyce.redis;

import redis.clients.jedis.Jedis;

public class HelloRedis {
    public static void main(String [] args){
        Jedis jedis=new Jedis("127.0.0.1");
        jedis.auth("root");

        System.out.println("Server is running :"+jedis.ping());
        System.out.println("Connection to server successful!");
       // jedis.set("data","HelloRedis");

        System.out.println("1111111");
        System.out.println(jedis.get("data"));
        System.out.println("22222");

    }

}
