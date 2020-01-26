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
import com.example.bank.model.Wettopup;
import com.example.bank.service.WettopupService;

@RestController
@RequestMapping("/api/")
public class ClaimbydateController {
	
	@Autowired
	WettopupService wettopupService;
	
	@PostMapping("claimby_date")
	public ResponseEntity<Object> claimby(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Integer totalcount = 0;
		Double depositviacount = 0.0;
		Double percentage = 0.0;
		
		List<String> depositvia = new ArrayList<>();
		depositvia.add("WeChat");
		depositvia.add("Line");
		depositvia.add("LiveChat");
		depositvia.add("Whatapps");
		depositvia.add("Telegram");
		depositvia.add("Call In");
		depositvia.add("Self Submission");
		depositvia.add("Mistake");
		if(newplayerreg.getCompanyid1() !=null) {
			
			for(String depositvia_itr : depositvia) {
				 Map<String,Object> temp_non_player = new HashMap<>();
				 
				
				
				List<Wettopup> countofdeposit = wettopupService.claimviadate(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(), depositvia_itr);
				
				List<Wettopup> totalcountofdeposit = wettopupService.totalclaimviadate(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1());
				
				 for(Object totalcountofdeposit_itr :totalcountofdeposit) {
					 
					 totalcount = ((Integer)totalcountofdeposit_itr);
			        	
			        }
				
				 for(Object countofdeposit_itr :countofdeposit) {
			        	
			        	System.out.println(((Integer)countofdeposit_itr));
			        	
			        	depositviacount = new Double(countofdeposit_itr.toString());
			        	
			        }
				 
				 if(depositviacount != 0) {
					 temp_non_player.put("depositvia", depositvia_itr);
					 temp_non_player.put("total", depositviacount);
					 temp_non_player.put("percentage", depositviacount / totalcount * 100);
					 
					 result_list.add(temp_non_player);
					
				 }
				
				
			}
			
			 Map<String,Object> report_total = new HashMap<>();
			 
			 report_total.put("depositvia", "Report Total:");
			 report_total.put("total", totalcount);	
			 
			 for(Map<String, Object> rs_itr: result_list) {
				 Double percentage_itr =  (Double) rs_itr.get("percentage");
				 percentage = percentage + percentage_itr;
			 }
			 
			 report_total.put("percentage", percentage);
			 result_list.add(report_total);
			
		}
		

		response.put("code", HttpStatus.OK);
	    response.put("msg", "Claim by date data");
	    response.put("data", result_list);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
