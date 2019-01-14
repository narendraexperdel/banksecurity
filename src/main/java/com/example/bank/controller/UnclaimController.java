package com.example.bank.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.model.Wettopup;
import com.example.bank.service.WettopupService;

@RestController
@RequestMapping("/")
public class UnclaimController {
	
	@Autowired
	WettopupService wettopupService;

	@GetMapping
	public String test() {
		return "banksecurity working";
	}
	
	@GetMapping("/topup/{id}")
	public ResponseEntity<Object> unclaimListbyId(@PathVariable Integer id) {
		
		Optional<Wettopup> wettopup = wettopupService.findWettopupbyId(id);
		
		Map<String,Object> response = new HashMap<>();
		
		response.put("code", HttpStatus.OK);
		response.put("msg", "Topup found by Id");
		response.put("data", wettopup);
		
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/unclaim")
	public ResponseEntity<Object> allUnclaimList(){
		
		List<Wettopup> unclaimlist = wettopupService.getallUnclaimlist();
		
		Map<String,Object>  customizedlist = new HashMap<>();
		List<Map<String, Object>> totallist = new ArrayList<>();
		
		Map<String, Object> response = new HashMap<>();
		
		for(Wettopup wettopup : unclaimlist) {
		customizedlist.put("trancid", wettopup.getTrancid());
		customizedlist.put("issuedby", wettopup.getIssuedby());
		customizedlist.put("issueddate", wettopup.getIssueddate());
		customizedlist.put("bankintype", wettopup.getBankintype());
		customizedlist.put("amount", wettopup.getAmount());
		customizedlist.put("bank", wettopup.getBank());
		customizedlist.put("depositvia", wettopup.getDepositvia());
		customizedlist.put("bonus", wettopup.getBonus());
		customizedlist.put("status", wettopup.getStatus());
		customizedlist.put("remark", wettopup.getRemark());
		customizedlist.put("claimby", wettopup.getClaimby());
		customizedlist.put("bankapprove", wettopup.getBankapproveby());
		customizedlist.put("csapproveby", wettopup.getCsapproveby());
		totallist.add(customizedlist);
		}
		
        response.put("code", HttpStatus.OK);
        response.put("msg", "All Unclaim List");
        response.put("data", totallist);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		
	}
	
}
