package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest{
	@Autowired
	private ShopDao shopDao;
	
	@Test
	@Ignore
	public void testINserShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);		
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setShopCategory(shopCategory);
		shop.setArea(area);
		shop.setShopName("测试的店铺");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setPriority(2);
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1,effectedNum);
	}
		
	@Test
	@Ignore
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(1L);	
		shop.setShopDesc("罗浩狗狗肉包子");
		shop.setShopAddr("测试地址");
		shop.setShopName("罗浩狗狗肉店");
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1,effectedNum);
	}
	
	@Test	
	public void testQueryByShopId() {
		/*Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);*/
		long shopId = 1;
		Shop shop =shopDao.queryByShopId(shopId);
		System.out.println(shop.getArea().getAreaName());
		
	}
	@Test
	@Ignore
	public void testQueryShopList() {
		Shop shopCondition = new Shop();
		
		PersonInfo owner = new PersonInfo();		
		owner.setUserId(1L);
		
		shopCondition.setShopName("梅");
		
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(2L);
		
		shopCondition.setShopCategory(sc);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
		System.out.println("大小："+shopList.size());
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println("总数:"+count);
	}
	
}
