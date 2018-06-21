package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.Shop;

public interface ShopDao {
	/*
	 * 新增店铺
	 */

	int insertShop(Shop shop);
	
	/*
	 * 更新店铺
	 */
	int updateShop(Shop shop);
	 
	/*
	 * 获取shop的查询
	 */
	Shop queryByShopId(long shopId);
	/*
	 * 店铺的模糊查询
	 */
	List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition,
		@Param("rowIndex")int rowIndex,	
		@Param("pageSize")int pageSize);
	
	/*
	 * 获取查询出的总数
	 */
	int queryShopCount(@Param("shopCondition")Shop shopCondition);
}
