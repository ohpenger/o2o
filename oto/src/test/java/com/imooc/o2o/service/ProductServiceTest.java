package com.imooc.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;

public class ProductServiceTest extends BaseTest{
	@Autowired
	private ProductService productService;
	
	@Test
	public void testAddProduct() {
		Shop shop1=new Shop();
	    shop1.setShopId(1L);
	    ProductCategory pc1 = new ProductCategory();
	    pc1.setProductCategoryId(1L);
	    Product product1=new Product();
	    product1.setProductName("测试商品");
	    product1.setProductDesc("测试描述");
	    product1.setPriority(1);
	    product1.setEnableStatus(ProductStateEnum.SUCCESS.getState());

	    product1.setShop(shop1);
	    product1.setProductCategory(pc1);
	    ProductExecution i = productService.addProduct(product1);
	    assertEquals(ProductStateEnum.SUCCESS.getState(),i.getState());
	}
}
