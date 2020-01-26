package com.example.bank.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.example.bank.model.TmpPlayer;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WettpService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class MemberaccountController {
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;

	@PostMapping("memberaccount")
	public ResponseEntity<Object> memberaccount(@RequestBody  Newplayerregbean newplayerreg){
		
		List<TmpPlayer> tmpplayer = new ArrayList<TmpPlayer>();
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		
		
		if(newplayerreg.getCompanyid1() !=null) {
			
			if(newplayerreg.getUserid() == "") {
				newplayerreg.setUserid(null);
			}
			
			if(newplayerreg.getUserid() !=null ) {
			 tmpplayer = tmpplayerService.tmpplayer_list(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getUserid());
		
			}else {
				tmpplayer = tmpplayerService.tmpplayer_list(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate());
			}
			
	         Iterator tmpplayer_itr = ((List<TmpPlayer>) tmpplayer).iterator();
	 		   
			   while(tmpplayer_itr.hasNext()){
				   Object[] obj1 = (Object[]) tmpplayer_itr.next();
				   
				   Map<String, Object> temp_member_account = new HashMap<>();
				   
			Double topup = 	 wettopupService.memberaccount_topup(String.valueOf(obj1[8]), newplayerreg.getCompanyid1()) ;
			
			Double bonus = 	 wettopupService.memberaccount_bonus(String.valueOf(obj1[8]), newplayerreg.getCompanyid1()) ;
			
			Double adjustment = wettopupService.memberaccount_adjustment(String.valueOf(obj1[8]), newplayerreg.getCompanyid1()) ;
			
			Double withdraw = wetwithdrawService.memberaccount_withdraw(String.valueOf(obj1[8]), newplayerreg.getCompanyid1()) ;
			
			if(topup == null || topup == 0.0) {
				topup = 0.0;
			}
			
			if(bonus == null || bonus == 0.0) {
				bonus = 0.0;
			}
			
			
			if(adjustment == null || adjustment == 0.0) {
				adjustment = 0.0;
			}
			
			
			if(withdraw == null || withdraw == 0.0) {
				withdraw = 0.0;
			}
			
			
			
			temp_member_account.put("userid", String.valueOf(obj1[8]));
			temp_member_account.put("totaltopup", topup);
			temp_member_account.put("withdraw", withdraw);
			temp_member_account.put("bonus", bonus);
			temp_member_account.put("adjustment", adjustment);
			temp_member_account.put("balance", topup - withdraw);
			
		result_list.add(temp_member_account);
		   }
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Member Account data");
	    response.put("data", result_list);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
