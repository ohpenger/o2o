package com.imooc.o2o.web.shopadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.service.ProductService;

@Controller
@RequestMapping("/shopadmin")
public class ProductManagementController {
	@Autowired
	private ProductService productService;
	/*
	 * 获取目录
	 */
	
	/*
	 * 添加商品
	 */
	 @RequestMapping(value="/addproduct",method=RequestMethod.POST)
     @ResponseBody
     private Map<String,Object>addProduct(@RequestBody Map<String, String> map,HttpServletRequest request){
   	  Map<String,Object> modelMap =new HashMap<String,Object>();
   	  
   	  String productName=map.get("product-name");
   	  String productDesc=map.get("product-desc");
   	  String priority = map.get("priority");
   	  String normalPrice = map.get("normal-price");
   	  String promotionPrice = map.get("promotion-price");
   	  String productCategoryId = map.get("productCategoryId");
   	     	
   	  
   	 
   	  //从session中获取当前店铺的Id并赋值给product，减少对前端数据的依赖
   	  Product product = new Product();
   	 // Shop currentShop=(Shop)request.getSession().getAttribute("currentShop");
   	  Shop shop=new Shop();
   	  shop.setShopId(1L);
   	  //shop.setShopId(currentShop.getShopId());
   	  ProductCategory productCategory =new ProductCategory();
   	  productCategory.setProductCategoryId(Long.valueOf(productCategoryId));
   	  
   	  //对数据进行组装
   	  product.setShop(shop);
   	  product.setProductName(productName);
   	  product.setProductDesc(productDesc);
   	  product.setPriority(Integer.valueOf(priority));
   	  product.setNormalPrice(normalPrice);
   	  product.setPromotionPrice(promotionPrice);
   	  product.setProductCategory(productCategory);
   	    	     	     	  
   	  //执行添加操作
   	  ProductExecution pe=productService.addProduct(product);
   	  if(pe.getState()==ProductStateEnum.SUCCESS.getState()) {
   		  modelMap.put("success",true);
   	  }else {
   		  modelMap.put("success",false);
			  modelMap.put("errMsg",pe.getStateInfo());
   	  }
  
   	  return modelMap;
     }
}
