package com.example.bank.controller;

import java.util.ArrayList;
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
import com.example.bank.service.ExpensestypeService;
import com.example.bank.service.OthIncomeService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class BalanceinquiryController {
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;;
	
	@Autowired
	ExpensestypeService expensetypeService;
	
	@Autowired
	OthIncomeService othincomeService;
	
	
	@PostMapping("bal-inquiry")
	public ResponseEntity<Object> balanceinquity(@RequestBody  Newplayerregbean newplayerreg){
		Map<String, Object> response = new HashMap<>();
		Map<String,Object> result_list = new HashMap<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
			Double topupamount = wettopupService.getsumamountbyuserid(newplayerreg.getPlayerid(), newplayerreg.getProductid(), newplayerreg.getCompanyid1());
			
			Double withdrawamount = wetwithdrawService.getsumamountbyuserid_withdraw(newplayerreg.getPlayerid(), newplayerreg.getProductid(), newplayerreg.getCompanyid1());
			
			  
			if(topupamount == null || topupamount == 0.0) {
				topupamount = 0.0;
			}
			
			if(withdrawamount == null || withdrawamount == 0.0) {
				withdrawamount = 0.0;
			}
			
			result_list.put("topupamount", topupamount);
			result_list.put("withdrawamount", withdrawamount);
			result_list.put("balance", topupamount - withdrawamount);
			
		}
		
		response.put(" code", HttpStatus.OK);
	    response.put("msg", "balance inquiry data");
	    response.put("data", result_list);
	    
	    return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
