package com.example.bank.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
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
public class BankcashflowController {
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;;
	
	@Autowired
	ExpensestypeService expensetypeService;
	
	@Autowired
	OthIncomeService othincomeService;
	
	
	@PostMapping("bankcashflow")
	public ResponseEntity<Object> bankcashflow(@RequestBody  Newplayerregbean newplayerreg){
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
				  
				   Double topupamount = wettopupService.bankcashflow(obj, newplayerreg.getCompanyid1());
				   
				   Double withdrawamount = wetwithdrawService.bankcashflow(obj, newplayerreg.getCompanyid1());
				   
				   Double othincome_amount = othincomeService.bankcashflow_othincome(obj, newplayerreg.getCompanyid1());
				   
				   Double expense_Amount = expensetypeService.bankcashflow_expenses(obj, newplayerreg.getCompanyid1());
				   
				   Map<String, Object> temp_bankcashflow = new HashMap<>();
				   
				   
					if(topupamount == null || topupamount == 0.0) {
						topupamount = 0.0;
					}
					
					if(withdrawamount == null || withdrawamount == 0.0) {
						withdrawamount = 0.0;
					}
					
					
					if(othincome_amount == null || othincome_amount == 0.0) {
						othincome_amount = 0.0;
					}
					
					
					if(expense_Amount == null || expense_Amount == 0.0) {
						expense_Amount = 0.0;
					}
					
					String pattern = "yyyy-MM-dd";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					String date1 = simpleDateFormat.format(obj);
					
				   
				   temp_bankcashflow.put("Date", date1);
				   temp_bankcashflow.put("topup", topupamount + othincome_amount);
				   temp_bankcashflow.put("withdraw", withdrawamount + expense_Amount);
				      
				   result_list.add(temp_bankcashflow);
			}
			
                 Map<String,Object> report_total = new HashMap<>();

            List total_topup_amount = wettopupService.bankcashflow_totolamount(month, year, newplayerreg.getCompanyid1());
            List total_withdraw_amount = wetwithdrawService.bankcashflow_totolamount(month, year, newplayerreg.getCompanyid1());
            List total_othincome_amount = othincomeService.bankcashflow_totol_othincome(month, year, newplayerreg.getCompanyid1());
            List total_expenses_Amount = expensetypeService.bankcashflow_totol_expenses(month, year, newplayerreg.getCompanyid1());
            
            if(total_topup_amount.get(0) == null ) {
            	total_topup_amount.set(0, 0.0);
            }
			
			if(total_othincome_amount.get(0)== null ) {
				total_othincome_amount.set(0, 0.0);
			}
			
			
			if(total_withdraw_amount.get(0) == null) {
				total_withdraw_amount.set(0, 0.0);
			}
			
			
			if(total_expenses_Amount.get(0) == null) {
				total_expenses_Amount.set(0, 0.0);
			}
            
			
			    Double d1 = (Double) total_topup_amount.get(0);
			    Double d2 = (Double) total_withdraw_amount.get(0);
			    BigDecimal d3 =  (BigDecimal) total_othincome_amount.get(0);
			    BigDecimal d4 = (BigDecimal) total_expenses_Amount.get(0);
			    
			    BigDecimal bd1 = BigDecimal.valueOf(d1.doubleValue());
			    Double dt = bd1.doubleValue();
			    
			    BigDecimal bd2 = BigDecimal.valueOf(d2.doubleValue());
			    Double dw = bd2.doubleValue();
			    
//			    BigDecimal bd3 = BigDecimal.valueOf(d3.doubleValue());
			    Double doth = d3.doubleValue();
			    
//			    BigDecimal bd4 = BigDecimal.valueOf(d4.doubleValue());
			    Double de = d4.doubleValue();
            
			 report_total.put("Date", "Total:");
			 report_total.put("topup", dt + doth);	
			 
			 report_total.put("withdraw",  dw + de);
			 result_list.add(report_total);
		
			
		}
		response.put("code", HttpStatus.OK);
	    response.put("msg", "bank cash flow data");
	    response.put("data", result_list);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
		

}
