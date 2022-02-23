package com.example.codetwo.config;

import com.example.codetwo.domain.ReaderVo;
import com.example.codetwo.domain.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTestTwo {

    //redis模板   
    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private ValueOperations<String, Object> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    @Test
    public void testValue() throws Exception {
        ReaderVo readerVo = new ReaderVo();
        Date date = new Date();
        readerVo.setName("花无缺");
        readerVo.setAccount(145.2);
        readerVo.setSex(1);
        readerVo.setAge(18);
        readerVo.setAddress("山西省");
        readerVo.setIfUse(true);
        readerVo.setDate(date);
        valueOperations.set("reader",readerVo);
        System.out.println("testValue:" + valueOperations.get("reader"));

    }

    @Test
    public void testExpiredValue() throws Exception {
        ReaderVo readerVo = new ReaderVo();
        Date date = new Date();
        readerVo.setName("花无缺");
        readerVo.setAccount(145.2);
        readerVo.setSex(1);
        readerVo.setAge(18);
        readerVo.setAddress("山西省");
        readerVo.setIfUse(true);
        readerVo.setDate(date);
        valueOperations.set("reader",readerVo,3, TimeUnit.SECONDS);
        Thread.currentThread().sleep(5000);
        System.out.println("testExpiredValue:" + valueOperations.get("reader"));

    }

    @Test
    public void testSetOperation() throws Exception {
        ReaderVo readerVo = new ReaderVo();
        Date date = new Date();
        readerVo.setName("花无缺");
        readerVo.setAccount(145.2);
        readerVo.setSex(1);
        readerVo.setAge(18);
        readerVo.setAddress("山西省");
        readerVo.setIfUse(true);
        readerVo.setDate(date);
        ReaderVo auserVo = new ReaderVo();
        auserVo.setName("清风");
        auserVo.setAccount(128.2);
        auserVo.setSex(0);
        auserVo.setAge(8);
        auserVo.setAddress("陕西省");
        auserVo.setIfUse(true);
        auserVo.setDate(date);
        setOperations.add("reader:test", readerVo, readerVo,auserVo);
        Set<Object> result = setOperations.members("reader:test");
        System.out.println("testSetOperation: " + result);
    }

    @Test
    public void HashOperations() throws Exception {
        ReaderVo readerVo = new ReaderVo();
        Date date = new Date();
        readerVo.setName("花无缺");
        readerVo.setAccount(145.2);
        readerVo.setSex(1);
        readerVo.setAge(18);
        readerVo.setAddress("山西省");
        readerVo.setIfUse(true);
        readerVo.setDate(date);
        hashOperations.put("hash:reader", readerVo.hashCode() + "", readerVo);
        System.out.println("HashOperations: " + hashOperations.get("hash:reader", readerVo.hashCode() + ""));
    }

    @Test
    public void ListOperations() throws Exception {
        ReaderVo readerVo = new ReaderVo();
        Date date = new Date();
        readerVo.setName("花无缺");
        readerVo.setAccount(145.2);
        readerVo.setSex(1);
        readerVo.setAge(18);
        readerVo.setAddress("山西省");
        readerVo.setIfUse(true);
        readerVo.setDate(date);
        listOperations.leftPush("list:reader", readerVo);
        System.out.println("ListOperations: " + listOperations.leftPop("list:reader"));
        // pop之后 值会消失
        System.out.println("ListOperations: " + listOperations.leftPop("list:reader"));
    }

    @Test
    public void ZSetOperations() throws Exception {
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<Object>("zSet-1",9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<Object>("zSet-6",9.9);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        System.out.println("ZSetOperations: " +zSetOperations.add("zSet-1",tuples));
        System.out.println("ZSetOperations: " +zSetOperations.range("zSet-6",0,-1));
    }

}
