package com.example.bank.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.example.bank.model.Wettopup;
import com.example.bank.service.WettopupService;

@RestController
@RequestMapping("/api/")
public class TotalmaualadjController {
	
	@Autowired
	WettopupService wettopupService;
	
	@PostMapping("manualby-date")
	public ResponseEntity<Object> totalmanualbydate(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Double average = 0.0;
		
		if(newplayerreg.getCompanyid1() !=null) {
			
			List<Wettopup> distinctcategory = wettopupService.distinctadjcategory(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1());
			
			Iterator distinctcategory_itr = distinctcategory.iterator();
			while(distinctcategory_itr.hasNext()){
				Map<String, Object> temp_manual_adjusment = new HashMap<>();
				
				String obj = (String) distinctcategory_itr.next();
				
				Long totaltransaction = wettopupService.adjustment_totaltransaction(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(), obj);
				
				Double totalbonus = wettopupService.adjustment_totalamount(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(), obj);
				
				 if(totalbonus == null) {
					 totalbonus = 0.0;
				 }
				
				
				char ch1 = obj.charAt(0);
				
				temp_manual_adjusment.put("code", ch1);
				temp_manual_adjusment.put("adjustmentcategory", obj.replaceAll("\\s+",""));
				temp_manual_adjusment.put("totaltransaction", totaltransaction);
				temp_manual_adjusment.put("totalbonus", totalbonus);
				temp_manual_adjusment.put("average", totalbonus/totaltransaction);
			
				result_list.add(temp_manual_adjusment);
			}
			
		}
		
		 Map<String,Object>  report_total = new HashMap<>();
		 
		 Long report_transaction = wettopupService.adjustment__report_totaltransaction(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1());
		 
		 Double report_totalbonus = wettopupService.adjustment_report_totalamount(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1());
		 
		 if(report_totalbonus == null) {
			 report_totalbonus = 0.0;
		 }
		 
		 for(Map<String, Object> rs_itr: result_list) {
			 Double average1 =  (Double) rs_itr.get("average");
			 average = average + average1;
		 }
		 
		 report_total.put("code", "Report Total:");
		 report_total.put("adjustmentcategory", "");
		 report_total.put("totaltransaction", report_transaction);
		 report_total.put("totalbonus", report_totalbonus);
		 report_total.put("average", average);
		 
		 result_list.add(report_total);
		 
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Claim by date data");
	    response.put("data", result_list);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
