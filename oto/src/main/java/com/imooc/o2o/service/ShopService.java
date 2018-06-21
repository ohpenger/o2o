package com.imooc.o2o.service;

import java.io.File;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;

public interface ShopService {
	/*
	 * 添加商铺
	 */
	ShopExecution addShop(Shop shop);
	
	/*
	 * 通过店铺ID获取店铺信息
	 */
	Shop getByShopId(long shopId);
	
	/*
	 * 更新店铺信息
	 */
	ShopExecution modifyShop(Shop shop);
	
	/*
	 * 根据shopcondition分页返回相应列表数据
	 */
	public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
}
