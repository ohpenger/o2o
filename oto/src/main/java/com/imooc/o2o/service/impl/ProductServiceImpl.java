package com.imooc.o2o.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDao productDao;

	@Override
	public ProductExecution addProduct(Product product) {

		if(product!=null&&product.getShop()!=null&&product.getShop().getShopId()!=null) {
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			product.setEnableStatus(1);
			product.setImgAddr("test");
			int effectedNum = productDao.insertProduct(product);
			return new ProductExecution(ProductStateEnum.SUCCESS,product);
		}
		else {
			return new ProductExecution(ProductStateEnum.INNER_ERROR,product);
		}
		

	}
	
	
}
