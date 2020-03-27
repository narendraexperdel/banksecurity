package com.example.bank.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.bean.Newplayerregbean;
import com.example.bank.model.WemUser;
import com.example.bank.service.WemUserService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WettpService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class LoginController {

	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;
	
	@Autowired
	WettpService wettpService;
	
	@Autowired
	WemUserService wemuserService;
	
	@PostMapping("login")
	public ResponseEntity<Object> login(@RequestBody  Newplayerregbean newplayerreg){
		
		System.out.println("login called");
		
		Map<String, Object> response = new HashMap<>();
		
		if(newplayerreg.getUsername() != null && newplayerreg.getPassword() != null) {
			
			List<WemUser> wemuser = wemuserService.getwemuser(newplayerreg.getUsername(), newplayerreg.getPassword());
			
			if(wemuser.isEmpty()) {
				
				    response.put("code", HttpStatus.BAD_REQUEST);
				    response.put("msg", "username or password not exist");
				    response.put("data", null);
				
			}else if(wemuser.size() == 1){
				
				    response.put("code", HttpStatus.OK);
				    response.put("msg", "Login Successful");
				    response.put("data", null);
				
			}
			
		}
		
		    
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		
	}
	
}
