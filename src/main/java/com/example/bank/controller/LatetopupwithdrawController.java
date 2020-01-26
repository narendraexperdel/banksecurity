package com.example.bank.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.example.bank.model.Wettopup;
import com.example.bank.model.Wetwithdraw;
import com.example.bank.service.ExpensestypeService;
import com.example.bank.service.OthIncomeService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class LatetopupwithdrawController {
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;;
	
	@Autowired
	ExpensestypeService expensetypeService;
	
	@Autowired
	OthIncomeService othincomeService;
	
	@PostMapping("latetop-with")
	public ResponseEntity<Object> latetopupwithdraw(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>(); 
		List<Map<String , Object>> result_list = new ArrayList<>();
		Double bonus = 0.0;
		
		if(newplayerreg.getCompanyid1() !=null) {
			
			List<Wettopup> latetopupwithdraw = wettopupService.latetopupwithdraw(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1());
			
			for(Wettopup latetopupwithdraw_itr: latetopupwithdraw) {
				
				 Map<String, Object> temp_latetopup_withdraw= new HashMap<>();
				   
				
				Date claimdate = latetopupwithdraw_itr.getClaimdate();
				
				Date csdate = latetopupwithdraw_itr.getCsdonetime();
				
				Date bankdate = latetopupwithdraw_itr.getBankdonetime();
				
				long diff = csdate.getTime() - claimdate.getTime();

				long diffMinutes = diff / (60 * 1000) % 60;
				
				long diffbank = bankdate.getTime() - claimdate.getTime();
				
				long diffcs = csdate.getTime() - bankdate.getTime();
				
				long diffbankMinutes = diffbank / (60 * 1000) % 60;
				
				long diffcsMinutes = diffcs / (60 * 1000) % 60;
				
				System.out.println(diffMinutes);
				
				
				
				if(diffMinutes > 10) {
					
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm");  
					Date claimdate1 = latetopupwithdraw_itr.getClaimdate();
					
					Date csdate1 = latetopupwithdraw_itr.getCsdonetime();
					
					Date bankdate1 = latetopupwithdraw_itr.getBankdonetime();
					Date issueddate1 = latetopupwithdraw_itr.getTime();
				    String strDate1= formatter.format(claimdate1);  
				    String strDate2= formatter.format(csdate1);
				    String strDate3= formatter.format(bankdate1);
				    String strDate4= formatter.format(issueddate1);
					
					
					System.out.println(diffMinutes);
					
					if(latetopupwithdraw_itr.getBonus() != 0.0 || latetopupwithdraw_itr.getBonus() != null) {
						bonus = latetopupwithdraw_itr.getBonus();
					}
					
					temp_latetopup_withdraw.put("trancid", latetopupwithdraw_itr.getTrancid());
					temp_latetopup_withdraw.put("playerid", latetopupwithdraw_itr.getUserid());
					temp_latetopup_withdraw.put("product", latetopupwithdraw_itr.getProductid());
					temp_latetopup_withdraw.put("amount", latetopupwithdraw_itr.getAmount());
					temp_latetopup_withdraw.put("bonus", bonus);
					temp_latetopup_withdraw.put("issuedtdate", latetopupwithdraw_itr.getIssuedby()+"~"+strDate4);
					temp_latetopup_withdraw.put("claimdate", latetopupwithdraw_itr.getClaimby()+"~"+strDate1);
					temp_latetopup_withdraw.put("bankapproveby", latetopupwithdraw_itr.getKioskdoneby()+"~"+strDate3);
					temp_latetopup_withdraw.put("csapproveby", latetopupwithdraw_itr.getCsapproveby()+"~"+strDate2);
					temp_latetopup_withdraw.put("latebonusgave", 5);
					temp_latetopup_withdraw.put("banktime", diffbankMinutes);
					temp_latetopup_withdraw.put("cstime", diffcsMinutes);
					temp_latetopup_withdraw.put("remark", "Late Top Up Tranc ID:"+latetopupwithdraw_itr.getTrancid());
					
					result_list.add(temp_latetopup_withdraw);
					
				}

				
				
			}
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "late top up withdraw data");
	    response.put("data", result_list);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("late-with")
	public ResponseEntity<Object> latewithdraw(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>(); 
		List<Map<String , Object>> result_list = new ArrayList<>();
		Double bonus = 0.0;
		
		if(newplayerreg.getCompanyid1() !=null) {
			
			List<Wetwithdraw> latetopupwithdraw = wetwithdrawService.latewithdraw(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1());
			
			for(Wetwithdraw latetopupwithdraw_itr: latetopupwithdraw) {
				
				 Map<String, Object> temp_latetopup_withdraw= new HashMap<>();
				   
				
				Date claimdate = latetopupwithdraw_itr.getTime();
				
				Date csdate = latetopupwithdraw_itr.getCsdonetime();
				
				Date bankdate = latetopupwithdraw_itr.getBankdonetime();
				
				long diff = csdate.getTime() - claimdate.getTime();

				long diffMinutes = diff / (60 * 1000) % 60;
				
				long diffbank = bankdate.getTime() - claimdate.getTime();
				
				long diffcs = csdate.getTime() - bankdate.getTime();
				
				long diffbankMinutes = diffbank / (60 * 1000) % 60;
				
				long diffcsMinutes = diffcs / (60 * 1000) % 60;
				
				System.out.println(diffMinutes);
				
				
				
				if(diffMinutes > 20) {
					
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm");  
					Date claimdate1 = latetopupwithdraw_itr.getCashoutdonetime();
					
					Date csdate1 = latetopupwithdraw_itr.getCsdonetime();
					
					Date bankdate1 = latetopupwithdraw_itr.getBankdonetime();
					Date issueddate1 = latetopupwithdraw_itr.getTime();
				    String strDate1= formatter.format(claimdate1);  
				    String strDate2= formatter.format(csdate1);
				    String strDate3= formatter.format(bankdate1);
				    String strDate4= formatter.format(issueddate1);
					
					
					System.out.println(diffMinutes);
					
					
					
					temp_latetopup_withdraw.put("trancid", latetopupwithdraw_itr.getTrancid());
					temp_latetopup_withdraw.put("playerid", latetopupwithdraw_itr.getUserid());
					temp_latetopup_withdraw.put("product", latetopupwithdraw_itr.getProductid());
					temp_latetopup_withdraw.put("amount", latetopupwithdraw_itr.getAmount());
					temp_latetopup_withdraw.put("bonus", bonus);
					temp_latetopup_withdraw.put("issuedtdate", latetopupwithdraw_itr.getIssuedby()+"~"+strDate4);
					temp_latetopup_withdraw.put("claimdate", latetopupwithdraw_itr.getCashoutstatus()+"~"+strDate1);
					temp_latetopup_withdraw.put("bankapproveby", latetopupwithdraw_itr.getBankapprove()+"~"+strDate3);
					temp_latetopup_withdraw.put("csapproveby", latetopupwithdraw_itr.getCsapprove()+"~"+strDate2);
					temp_latetopup_withdraw.put("latebonusgave", 5);
					temp_latetopup_withdraw.put("banktime", diffbankMinutes);
					temp_latetopup_withdraw.put("cstime", diffcsMinutes);
					temp_latetopup_withdraw.put("remark", "Late Withdraw Tranc ID:"+latetopupwithdraw_itr.getTrancid());
					
					result_list.add(temp_latetopup_withdraw);
					
				}

				
				
			}
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "late top up withdraw data");
	    response.put("data", result_list);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
