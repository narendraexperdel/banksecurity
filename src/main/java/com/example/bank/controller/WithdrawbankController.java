package com.example.bank.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.bean.Newplayerregbean;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WemplayerService;
import com.example.bank.service.WettopupService;

@RestController
@RequestMapping("/api/")
public class WithdrawbankController {
	
	@Autowired
	WemplayerService wemplayerService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	@Autowired
	WettopupService wettopupService;
	
	@PostMapping("dfd-customer")
	public ResponseEntity<Object> newplayerreg(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
			
			
			
		}
		
		return null;
		}

}
