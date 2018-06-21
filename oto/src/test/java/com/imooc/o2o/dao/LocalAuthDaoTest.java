package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.LocalAuth;

public class LocalAuthDaoTest extends BaseTest{
	@Autowired
	private LocalAuthDao localAuthDao;
	
	@Test
	public void testLocalAuthDao() {
		LocalAuth localAuth = new LocalAuth();
		localAuth.setUserName("彭志鹏");
		localAuth.setPassword("123456");
		localAuth.setCreateTime(new Date());
		localAuth.setLastEditTime(new Date());
		int e =localAuthDao.insertLocalAuth(localAuth);
		assertEquals(1,e);
	}

}
