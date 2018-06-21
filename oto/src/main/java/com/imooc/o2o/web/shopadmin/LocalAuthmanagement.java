package com.imooc.o2o.web.shopadmin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.service.LocalAuthService;

@Controller
@RequestMapping("/shopadmin")
public class LocalAuthmanagement {
	@Autowired
	private LocalAuthService LocalAuthService;
	
	
	@RequestMapping(value="/addlocalauth",method=RequestMethod.POST)
    @ResponseBody
    private Map<String,Object>addLocalAuth(@RequestBody Map<String, String> map,HttpServletRequest request){
		Map<String,Object> modelMap =new HashMap<String,Object>();
		
		String userName = map.get("userName");
		String password = map.get("password");
		
		LocalAuth localAuth = new LocalAuth();
		localAuth.setPassword(password);
		localAuth.setUserName(userName);
		
		LocalAuthService.addAuth(localAuth);
		
		modelMap.put("success", true);
		return modelMap;
	}
}