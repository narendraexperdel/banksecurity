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
import com.example.bank.model.Wettopup;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WettpService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class PlayercashflowController {
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;
	
	@Autowired
	WettpService wettpService;
	
	@PostMapping("playercash-flow")
	public ResponseEntity<Object> playercashflow(@RequestBody  Newplayerregbean newplayerreg){
		
		List<Wettopup> wettopup = new ArrayList<Wettopup>();
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
			if(newplayerreg.getUserid() == "") {
				newplayerreg.setUserid(null);
			}
			
			if(newplayerreg.getUserid() !=null ) {
				Map<String, Object> temp_member_account = new HashMap<>();
				
				Double topup = wettopupService.playercashflow_topup(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(),newplayerreg.getUserid());
				   
				   Double bonus = wettopupService.playercashflow_bonus(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(),newplayerreg.getUserid());
				   
				   Double adjustment = wettopupService.playercashflow_adjustment(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(),newplayerreg.getUserid());
			  
			       Double withdraw = wetwithdrawService.playercashflow_withdraw(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(),newplayerreg.getUserid());
			  
			       Double transferin = wettpService.player_transferin_amount(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(),newplayerreg.getUserid());
			       
			       Double transferout = wettpService.player_transferout_amount(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(),newplayerreg.getUserid());
			       
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
				
				if(transferin == null || transferin == 0.0) {
					transferin = 0.0;
				}
				
				if(transferout == null || transferout == 0.0) {
					transferout = 0.0;
				}
				
				temp_member_account.put("userid", newplayerreg.getUserid());
				temp_member_account.put("topup", topup);
				temp_member_account.put("withdraw", withdraw);
				temp_member_account.put("bonus", bonus);
				temp_member_account.put("adjustment", adjustment);
				temp_member_account.put("transferout", transferout);
				temp_member_account.put("transferin", transferin);
				temp_member_account.put("balance", topup - withdraw);
				
			result_list.add(temp_member_account);
				
				
				
			}else {
				
				
				wettopup = wettopupService.distinctuserid(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1());
				
				 Iterator wettopup_itr = wettopup.iterator();
		 		   
				   while(wettopup_itr.hasNext()){
					   String obj = (String) wettopup_itr.next();
					   
					   Map<String, Object> temp_member_account = new HashMap<>();
						
					   
					   Double topup = wettopupService.playercashflow_topup(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(),obj);
					   
					   Double bonus = wettopupService.playercashflow_bonus(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(),obj);
					   
					   Double adjustment = wettopupService.playercashflow_adjustment(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(),obj);
				  
				       Double withdraw = wetwithdrawService.playercashflow_withdraw(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(),obj);
				  
				       Double transferin = wettpService.player_transferin_amount(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(),obj);
				       
				       Double transferout = wettpService.player_transferout_amount(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(),obj);
				       
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
					
					if(transferin == null || transferin == 0.0) {
						transferin = 0.0;
					}
					
					if(transferout == null || transferout == 0.0) {
						transferout = 0.0;
					}
					
					temp_member_account.put("userid", obj);
					temp_member_account.put("topup", topup);
					temp_member_account.put("withdraw", withdraw);
					temp_member_account.put("bonus", bonus);
					temp_member_account.put("adjustment", adjustment);
					temp_member_account.put("transferout", transferout);
					temp_member_account.put("transferin", transferin);
					temp_member_account.put("balance", topup - withdraw);
					
			    	result_list.add(temp_member_account);
					
				   }
			
			}
			
			
		}
		
		  
		  
		  if(newplayerreg.getUserid() == "") {
				newplayerreg.setUserid(null);
			}
		  
		  if(newplayerreg.getCompanyid1() != null) {
			
			if(newplayerreg.getUserid() !=null ) {
				
				
				 Map<String, Object> temp_member_report_account = new HashMap<>();
					
				   
				   Double topup = wettopupService.playercashflow_topup(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(),newplayerreg.getUserid());
				   
				   Double bonus = wettopupService.playercashflow_bonus(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(),newplayerreg.getUserid());
				   
				   Double adjustment = wettopupService.playercashflow_adjustment(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(),newplayerreg.getUserid());
			  
			       Double withdraw = wetwithdrawService.playercashflow_withdraw(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(),newplayerreg.getUserid());
			  
			       Double transferin = wettpService.player_transferin_amount(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(),newplayerreg.getUserid());
			       
			       Double transferout = wettpService.player_transferout_amount(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(),newplayerreg.getUserid());
			       
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
				
				if(transferin == null || transferin == 0.0) {
					transferin = 0.0;
				}
				
				if(transferout == null || transferout == 0.0) {
					transferout = 0.0;
				}
				
				temp_member_report_account.put("userid", "Total");
				temp_member_report_account.put("topup", topup);
				temp_member_report_account.put("withdraw", withdraw);
				temp_member_report_account.put("bonus", bonus);
				temp_member_report_account.put("adjustment", adjustment);
				temp_member_report_account.put("transferout", transferout);
				temp_member_report_account.put("transferin", transferin);
				temp_member_report_account.put("balance", topup - withdraw);
				
		    	result_list.add(temp_member_report_account);
				
				
				
				
				
				
				
				
				
			}else {
				

				Map<String, Object> temp_member_report_account = new HashMap<>();
				
				Double report_topup = wettopupService.playercashflow__report_topup(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1());
				
				Double report_bonus = wettopupService.playercashflow__report_bonus(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1());
				
				Double report_adjustment = wettopupService.playercashflow__report_adjustment(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1());
				
				Double report_withdraw = wetwithdrawService.playercashflow_report_withdraw(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1());
				
				Double report_transferin = wettpService.player_transferin__report_amount(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1());
				
				Double report_transferout = wettpService.player_transferout_report_amount(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1());
				
				if(report_topup == null || report_topup == 0.0) {
					report_topup = 0.0;
				}
				
				if(report_bonus == null || report_bonus == 0.0) {
					report_bonus = 0.0;
				}
				
				
				if(report_adjustment == null || report_adjustment == 0.0) {
					report_adjustment = 0.0;
				}
				
				
				if(report_withdraw == null || report_withdraw == 0.0) {
					report_withdraw = 0.0;
				}
				
				if(report_transferin == null || report_transferin == 0.0) {
					report_transferin = 0.0;
				}
				
				if(report_transferout == null || report_transferout == 0.0) {
					report_transferout = 0.0;
				}
				
				
				
				temp_member_report_account.put("userid", "Total");
				temp_member_report_account.put("topup", report_topup);
				temp_member_report_account.put("withdraw", report_withdraw);
				temp_member_report_account.put("bonus", report_bonus);
				temp_member_report_account.put("adjustment", report_adjustment);
				temp_member_report_account.put("transferout", report_transferout);
				temp_member_report_account.put("transferin", report_transferin);
				temp_member_report_account.put("balance", report_topup - report_withdraw);
				
		    	result_list.add(temp_member_report_account);
					
				
			}
		
		  }
		
		
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Player Cash flow data");
	    response.put("data", result_list);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		
	}
	

}
