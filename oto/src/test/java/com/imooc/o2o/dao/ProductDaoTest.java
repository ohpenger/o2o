package com.imooc.o2o.dao;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;


public class ProductDaoTest extends BaseTest{
	 @Autowired
	  private ProductDao productDao;

	  @Test
	  public void testAInsertProduct() throws Exception{
	    Shop shop1=new Shop();
	    shop1.setShopId(1L);
	    ProductCategory pc1 = new ProductCategory();
	    pc1.setProductCategoryId(1L);
	    Product product1=new Product();
	    product1.setProductName("测试1");
	    product1.setProductDesc("测试Desc1");
	    product1.setImgAddr("test1");
	    product1.setPriority(1);
	    product1.setEnableStatus(1);
	    product1.setCreateTime(new Date());
	    product1.setLastEditTime(new Date());
	    product1.setShop(shop1);
	    product1.setProductCategory(pc1);
	    int i = productDao.insertProduct(product1);
	    System.out.println(i);
	  }
}
