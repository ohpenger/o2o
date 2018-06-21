package com.imooc.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="shopadmin",method= {RequestMethod.GET})
public class ShopAdminController {
	@RequestMapping(value="/shopoperation")
	public String shopOperation() {
		return "shop/shopoperation";
	}
	
	@RequestMapping(value="/shoplist")
	public String shoplist() {
		return "shop/shoplist";
	}
	
	@RequestMapping(value="/shopmanagement")
	public String shopmanagement() {
		return "shop/shopmanagement";
	}
	
	@RequestMapping(value="/productoperation")
	public String productoperation() {
		return "shop/productopration";
	}
	
	@RequestMapping(value="/register")
	public String register() {
		return "shop/register";
	}
	
	@RequestMapping(value="/ncu")
	public String ncu() {
		return "shop/ncu";
	}
	@RequestMapping(value="/login")
	public String login() {
		return "shop/login";
	}
}
