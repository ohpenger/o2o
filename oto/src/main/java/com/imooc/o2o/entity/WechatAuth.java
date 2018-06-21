package com.imooc.o2o.entity;

import java.util.Date;

public class WechatAuth {
	private Long wechatAuthId;
	private int openId;
	private Date createTime;
	private PersonInfo personInfo;
	public Long getWechatAuthId() {
		return wechatAuthId;
	}
	public void setWechatAuthId(Long wechatAuthId) {
		this.wechatAuthId = wechatAuthId;
	}
	public int getOpenId() {
		return openId;
	}
	public void setOpenId(int openId) {
		this.openId = openId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

}
