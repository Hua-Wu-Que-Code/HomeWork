package com.example.codetwo.config;

import com.example.codetwo.domain.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {


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
		UserVo userVo = new UserVo();
		userVo.setAddress("shanghai");
		userVo.setName("testdfas");
		userVo.setAge(123);
		valueOperations.set("User1",userVo);
		System.out.println("testValue:" + valueOperations.get("User1"));

	}
	
	@Test
	public void testExpiredValue() throws Exception {
		UserVo userVo = new UserVo();
		userVo.setAddress("shanghai");
		userVo.setName("testdfas");
		userVo.setAge(123);
		valueOperations.set("User1",userVo,3,TimeUnit.SECONDS);
		Thread.currentThread().sleep(5000);
		System.out.println("testExpiredValue:" + valueOperations.get("User1"));

	}

	@Test
	public void testSetOperation() throws Exception {
		UserVo userVo1 = new UserVo();
		userVo1.setAddress("beijing");
		userVo1.setName("jantent");
		userVo1.setAge(23);
		UserVo auserVo = new UserVo();
		auserVo.setAddress("a-address");
		auserVo.setName("antent");
		auserVo.setAge(23);
		setOperations.add("user:test", userVo1, userVo1,auserVo);
		Set<Object> result = setOperations.members("user:test");
		System.out.println("testSetOperation: " + result);
	}

	@Test
	public void HashOperations() throws Exception {
		UserVo userVo = new UserVo();
		userVo.setAddress("beijing");
		userVo.setName("jantent");
		userVo.setAge(23);
		hashOperations.put("hash:user", userVo.hashCode() + "", userVo);
		System.out.println("HashOperations: " + hashOperations.get("hash:user", userVo.hashCode() + ""));
	}

	@Test
	public void ListOperations() throws Exception {
		UserVo userVo = new UserVo();
		userVo.setAddress("beijing");
		userVo.setName("jantent");
		userVo.setAge(23);
		listOperations.leftPush("list:user", userVo);
		System.out.println("ListOperations: " + listOperations.leftPop("list:user"));
		// pop之后 值会消失
		System.out.println("ListOperations: " + listOperations.leftPop("list:user"));
	}
	
	@Test
	public void ZSetOperations() throws Exception {
	ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<Object>("zset-1",9.6);
    ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<Object>("zset-6",9.9);
    Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
    tuples.add(objectTypedTuple1);
    tuples.add(objectTypedTuple2);
    System.out.println("ZSetOperations: " +zSetOperations.add("zset1",tuples));
    System.out.println("ZSetOperations: " +zSetOperations.range("zset1",0,-1));
	}
}