package com.imooc.o2o.web.shopadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.service.AreaService;
import com.imooc.o2o.service.ShopCategoryService;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController{
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value="/getshopmanagementinfo",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object>getShopManagementInfo(HttpServletRequest request){
		Map<String,Object> modelMap=new HashMap<String,Object>();
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		
		if(shopId <=0) {
			    Object currentShopObj=request.getSession().getAttribute("currentShop");
				if(currentShopObj==null) {
				modelMap.put("redirect",true);
				modelMap.put("url","/oto/shopadmin/shoplist");
				
			}else {
				Shop currentShop=(Shop)currentShopObj;
				modelMap.put("redirect",false);
				modelMap.put("shopId",currentShop.getShopId());
			}
		}else {			
			Shop currentShop=new Shop();
			currentShop.setShopId(shopId);
			request.getSession().setAttribute("currentShop", currentShop);
			modelMap.put("shopId",currentShop.getShopId());
			modelMap.put("redirect",false);
		}
		return modelMap;
	}
		
	
	@RequestMapping(value="/getshoplist",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getShopList(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		PersonInfo user =new PersonInfo();
		user.setUserId(1L);
		user.setName("test");
		request.getSession().setAttribute("user", user);
		user = (PersonInfo)request.getSession().getAttribute("user");
		
		try {
			Shop shopCondition =new Shop();
			shopCondition.setOwner(user);			
			ShopExecution se =shopService.getShopList(shopCondition, 0, 100);
			modelMap.put("shopList", se.getShopList());
			modelMap.put("user", user);
			modelMap.put("success", true);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	//自动获取地区和商铺类别
	@RequestMapping(value="/getshopinitinfo",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getShopInitInfo(){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		List<Area> areaList = new ArrayList<Area>();
		
		try {
			shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
			areaList = areaService.getAreaList();
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	//获取要修改的商铺
	@RequestMapping(value="/getshopbyid",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object>getShopByid(HttpServletRequest request){
		Map<String,Object>modelMap=new HashMap<String,Object>();
		Long shopId=HttpServletRequestUtil.getLong(request, "shopId");
		if(shopId>-1) {
			try {
				Shop shop=shopService.getByShopId(shopId);
				List<Area> areaList=areaService.getAreaList();
				modelMap.put("shop",shop);
				modelMap.put("areaList",areaList);
				modelMap.put("success",true);
			}catch(Exception e) {
				modelMap.put("success",false);
				modelMap.put("errMsg",e.toString());
			}
		}else {
			modelMap.put("success",false);
			modelMap.put("errMsg","empty shopId");
		}
		return modelMap;
	}

	
	//处理提交的商铺信息
    	@RequestMapping(value="/registershop",method = RequestMethod.POST)
    	@ResponseBody
    	private Map<String,Object> registerShop(@RequestBody Map<String, String> map,HttpServletRequest request){
    		//@Param Map<String,String> map
    		Map<String,Object> modelMap = new HashMap<String,Object>();
    		//1.接收并转化相应的参数，包括店铺信息以及图片信息
    		//String shopStr=HttpServletRequestUtil.getString(request,"shopStr");
    		String shopName = map.get("shopName");
    		String shopAddr =map.get("shopAddr");
    		String phone = map.get("phone");
    		String shopDesc = map.get("shopDesc");
    		String areaId = map.get("areaId");
    		String shopCategoryId= map.get("shopCategoryId");
    		Area area =new Area();
    		ShopCategory shopCategory = new ShopCategory();
    		area.setAreaId(Integer.valueOf(areaId));
    		shopCategory.setShopCategoryId(Long.valueOf(shopCategoryId));
    
    		Shop shop=new Shop();
    		try {
    			shop.setShopName(shopName);
    			shop.setShopAddr(shopAddr);
    			shop.setPhone(phone);
    			shop.setShopDesc(shopDesc);
    			shop.setArea(area);
    			shop.setShopCategory(shopCategory);
    		}catch(Exception e) {
    			modelMap.put("success",false);
    			modelMap.put("errMsg",e.getMessage());
    			return modelMap;
    		}
    		//2.注册店铺
    		if(shop != null && shopName != null) {
    			//session
    			
    	    	//PersonInfo owner = (PersonInfo)request.getSession().getAttribute("user");
    			PersonInfo owner =new PersonInfo();
    			owner.setUserId(1L);
    	    	shop.setOwner(owner);
    	    	ShopExecution se =shopService.addShop(shop);
    	    	if(se.getState()==ShopStateEnum.CHECK.getState()) {
   				modelMap.put("success",true);
   			//该用户可以操作的店铺列表
   				@SuppressWarnings("unchecked")
				List<Shop>shopList=(List<Shop>)request.getSession().getAttribute("shopList");
   				if(shopList==null||shopList.size()==0) {
   					shopList=new ArrayList<Shop>();
   				}
   				shopList.add(se.getShop());
   				request.getSession().setAttribute("shopList",shopList);
    			}else {
   				modelMap.put("success",false);
    			modelMap.put("errMsg","2");  			
    			}
    		return modelMap;	
    	    }
    		else {
    	    	modelMap.put("success",false);
    	    	modelMap.put("errMsg","请输入店铺信息");
    	    	return modelMap;
    	    }
    	}
    



//修改提交的商铺信息
@RequestMapping(value="/modifyshop",method = RequestMethod.POST)
@ResponseBody
private Map<String,Object> modifyShop(@RequestBody Map<String, String> map){
	//@Param Map<String,String> map
	Map<String,Object> modelMap = new HashMap<String,Object>();
	//1.接收并转化相应的参数，包括店铺信息以及图片信息
	//String shopStr=HttpServletRequestUtil.getString(request,"shopStr");
	String shopName = map.get("shopName");
	String shopAddr =map.get("shopAddr");
	String phone = map.get("phone");
	String shopDesc = map.get("shopDesc");
	String areaId = map.get("areaId");
	String shopCategoryId= map.get("shopCategoryId");
	String shopId = map.get("shopId");
	Area area =new Area();
	ShopCategory shopCategory = new ShopCategory();
	area.setAreaId(Integer.valueOf(areaId));
	shopCategory.setShopCategoryId(Long.valueOf(shopCategoryId));
	
	ObjectMapper mapper=new ObjectMapper();
	Shop shop=new Shop();
	try {
		shop.setShopId(Long.valueOf(shopId));
		shop.setShopName(shopName);
		shop.setShopAddr(shopAddr);
		shop.setPhone(phone);
		shop.setShopDesc(shopDesc);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
	}catch(Exception e) {
		modelMap.put("success",false);
		modelMap.put("errMsg",e.getMessage());
		return modelMap;
	}
	//2.注册店铺
	if(shop != null &&shop.getShopId()!= null) {
    	
    	ShopExecution se =shopService.modifyShop(shop);
    	if(se.getState()==ShopStateEnum.SUCCESS.getState()) {
			modelMap.put("success",true);
		}else {
			modelMap.put("success",false);
		modelMap.put("errMsg","2");  			
		}
	return modelMap;	
    }
	else {
    	modelMap.put("success",false);
    	modelMap.put("errMsg","请输入店铺id");
    	return modelMap;
    }
}
}

