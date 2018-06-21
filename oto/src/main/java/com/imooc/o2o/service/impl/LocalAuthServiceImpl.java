package com.imooc.o2o.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.o2o.dao.LocalAuthDao;
import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.service.LocalAuthService;

@Service
public class LocalAuthServiceImpl implements LocalAuthService{
	@Autowired
	private LocalAuthDao localAuthDao;
	
	@Override
	public void addAuth(LocalAuth localAuth) {
		localAuth.setCreateTime(new Date());
		localAuth.setLastEditTime(new Date());
		localAuthDao.insertLocalAuth(localAuth);
	}

}
