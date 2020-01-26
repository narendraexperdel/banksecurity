package com.example.bank.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.example.bank.service.ExpensestypeService;
import com.example.bank.service.OthIncomeService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class ProductcashflowController {
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;;
	
	@Autowired
	ExpensestypeService expensetypeService;
	
	@Autowired
	OthIncomeService othincomeService;
	
	
	@PostMapping("productcashflow")
	public ResponseEntity<Object> productcashflow(@RequestBody  Newplayerregbean newplayerreg){
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		
		if(newplayerreg.getCompanyid1() !=null) {
              Date date = newplayerreg.getDateOfissue();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date); 
			
			String month = Integer.toString(cal.get(Calendar.MONTH)+1); 
			String year = Integer.toString(cal.get(Calendar.YEAR)); 
			
			
			List<Wettopup> distinctdate = wettopupService.distinct(month, year, newplayerreg.getCompanyid1());
			
			Iterator distinctdate_itr = distinctdate.iterator();
			while(distinctdate_itr.hasNext()){
				   Date obj = (Date) distinctdate_itr.next();
				   
				   Map<String, Object> temp_bankcashflow = new HashMap<>();
				   
                   Double topupamount = wettopupService.bankcashflow(obj, newplayerreg.getCompanyid1());
				   
				   Double withdrawamount = wetwithdrawService.bankcashflow(obj, newplayerreg.getCompanyid1());
				   
					if(topupamount == null || topupamount == 0.0) {
						topupamount = 0.0;
					}
					
					if(withdrawamount == null || withdrawamount == 0.0) {
						withdrawamount = 0.0;
					}
					
					String pattern = "yyyy-MM-dd";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					String date1 = simpleDateFormat.format(obj);
					
					   temp_bankcashflow.put("Date", date1);
					   temp_bankcashflow.put("topup", topupamount );
					   temp_bankcashflow.put("withdraw", withdrawamount);
					   temp_bankcashflow.put("wl", topupamount - withdrawamount);
					      
					   result_list.add(temp_bankcashflow);
					
					
			}
			
			  Map<String,Object> report_total = new HashMap<>();

		         List total_topup_amount = wettopupService.bankcashflow_totolamount(month, year, newplayerreg.getCompanyid1());
		         List total_withdraw_amount = wetwithdrawService.bankcashflow_totolamount(month, year, newplayerreg.getCompanyid1());
				
		         if(total_topup_amount.get(0) == null ) {
		            	total_topup_amount.set(0, 0.0);
		            }
				
					
					if(total_withdraw_amount.get(0) == null) {
						total_withdraw_amount.set(0, 0.0);
					}
					
					 report_total.put("Date", "Total:");
					 report_total.put("topup", total_topup_amount.get(0));	
					 
					 report_total.put("withdraw", total_withdraw_amount.get(0));
					 report_total.put("wl", (Double) total_topup_amount.get(0) - (Double) total_withdraw_amount.get(0));
					 result_list.add(report_total);
			
		}
		
	   
		response.put("code", HttpStatus.OK);
	    response.put("msg", "product cash flow data");
	    response.put("data", result_list);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		}

}
