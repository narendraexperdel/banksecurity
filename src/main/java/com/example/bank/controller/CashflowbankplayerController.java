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

import com.example.bank.bean.Wettopuprequestbean;
import com.example.bank.model.CmcProduct;
import com.example.bank.model.Cmccombank;
import com.example.bank.model.Wettopup;
import com.example.bank.model.Wetwithdraw;
import com.example.bank.service.CmcCombankSerrvice;
import com.example.bank.service.CmcproductService;
import com.example.bank.service.ReferalbonusService;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WettpService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class CashflowbankplayerController {
	
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	CmcproductService cmcproductService;
	
	@Autowired
	CmcCombankSerrvice cmccombankService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;
	
	@Autowired
	WettpService wettpService;
	
	@Autowired
	ReferalbonusService referalbonusService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	
	@PostMapping("cashflow-bank")
	public ResponseEntity<Object> cashflowbank(@RequestBody Wettopuprequestbean wettopup){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<Map<String,Object>> result_list_total = new ArrayList<>();
		List<Wetwithdraw> mix_userid = new ArrayList<>();
		String conmethod = null;
		Double wl = 0.0;
		
		if(wettopup.getCompanyid1() != null) {
			String banklist = wettopup.getBank().get(0);
			
			List<String> temp_bank_list = new ArrayList<>();
			
			if(banklist == null) {
				
					List<Cmccombank> banklist_all = cmccombankService.getallbankbycompany(wettopup.getCompanyid1());
					for(Cmccombank banklist_itr: banklist_all) {
						temp_bank_list.add(banklist_itr.getFldesc());
					}
				}else {
					temp_bank_list = wettopup.getBank();
				}
			
			
			for(String bank_itr : temp_bank_list) {
				
				  List<Wettopup> cashflowbankplayer = wettopupService.cashflowbank(wettopup.getDateOfissue(), wettopup.getTodate(), wettopup.getCompanyid1(), bank_itr);
				  
				  List<Wetwithdraw> cashflowbankplayer_withdraw = wetwithdrawService.cashflowbank_withdraw(wettopup.getDateOfissue(), wettopup.getTodate(), wettopup.getCompanyid1(), bank_itr);
				
				  
				  Iterator cashflowbankplayer_itr = cashflowbankplayer.iterator();
		 		   
				   while(cashflowbankplayer_itr.hasNext()){
					   Wetwithdraw wetwithdraw1 = new Wetwithdraw();
					   Object[] obj1 = (Object[]) cashflowbankplayer_itr.next();
					   wetwithdraw1.setUserid(String.valueOf(obj1[0]));
					   wetwithdraw1.setCompanybank(String.valueOf(obj1[1]));
					   wetwithdraw1.setProductid(String.valueOf(obj1[2]));
					   mix_userid.add(wetwithdraw1);
				   }
				   
				   Iterator cashflowbankplayer_withdraw_itr = cashflowbankplayer_withdraw.iterator();
				   
				   while(cashflowbankplayer_withdraw_itr.hasNext()){
					   Wetwithdraw wetwithdraw1 = new Wetwithdraw();
					   Object[] obj1 = (Object[]) cashflowbankplayer_withdraw_itr.next();
					   wetwithdraw1.setUserid(String.valueOf(obj1[0]));
					   wetwithdraw1.setCompanybank(String.valueOf(obj1[1]));
					   wetwithdraw1.setProductid(String.valueOf(obj1[2]));
					   
					  boolean ans = mix_userid.contains(wetwithdraw1);
					  if(!ans) {
						  mix_userid.add(wetwithdraw1);
					  }
					   
					  
				   }
				  
			}
				  
			 Iterator mix_userid_itr = mix_userid.iterator();
	 		   
			   while(mix_userid_itr.hasNext()){
				   Wetwithdraw obj1 = (Wetwithdraw) mix_userid_itr.next(); 
				  
				 
					   
					   Map <String , Object> temp_cashflowbank_list = new HashMap<>();
						
						Double cashflowproduct_topup = wettopupService.cashflowbank_topup(obj1.getUserid(), wettopup.getCompanyid1(),obj1.getCompanybank(), wettopup.getDateOfissue(), wettopup.getTodate());
						
						Double cashflowproduct_withdraw = wetwithdrawService.cashflowbank_withdraw(obj1.getUserid(), wettopup.getCompanyid1(),obj1.getCompanybank(), wettopup.getDateOfissue(), wettopup.getTodate());
						
				      
						
						
						
						
						
						if(cashflowproduct_topup == null || cashflowproduct_topup == 0.0) {
							cashflowproduct_topup = 0.0;
						}
						
						if(cashflowproduct_withdraw == null || cashflowproduct_withdraw == 0.0) {
							cashflowproduct_withdraw = 0.0;
						}
						
						
						
						
						List<String> playerandtel = tmpplayerService.playername(obj1.getCompanybank(),obj1.getProductid(), wettopup.getCompanyid1());
						
						Iterator playerandtel_itr = playerandtel.iterator();
						
						while(playerandtel_itr.hasNext()) {
							 Object[] playerandtel_obj = (Object[]) playerandtel_itr.next();
							 conmethod = String.valueOf(playerandtel_obj[2]);
						}
						
						temp_cashflowbank_list.put("bank", obj1.getCompanybank());
						temp_cashflowbank_list.put("userid", obj1.getUserid().toUpperCase());
						temp_cashflowbank_list.put("contactmethod", conmethod);
						temp_cashflowbank_list.put("topup", cashflowproduct_topup);
						temp_cashflowbank_list.put("withdraw", cashflowproduct_withdraw);
						
						temp_cashflowbank_list.put("wl", cashflowproduct_topup - cashflowproduct_withdraw );
						
				        result_list.add(temp_cashflowbank_list);
				   
				   }
			
			
		
		
		
			
			for(String bank_itr_total : temp_bank_list) {
				

					   Map <String , Object> temp_cashflowbanktotal_list = new HashMap<>();
						
						Double cashflowbank_topup_total = wettopupService.cashflowbank_topup_total(wettopup.getCompanyid1(), bank_itr_total, wettopup.getDateOfissue(), wettopup.getTodate());
						
						Double cashflowbank_withdraw_total = wetwithdrawService.cashflowbank_withdraw_total(wettopup.getCompanyid1(), bank_itr_total, wettopup.getDateOfissue(), wettopup.getTodate());

						
						if(cashflowbank_topup_total == null || cashflowbank_topup_total == 0.0) {
							cashflowbank_topup_total = 0.0;
						}
						
						if(cashflowbank_withdraw_total == null || cashflowbank_withdraw_total == 0.0) {
							cashflowbank_withdraw_total = 0.0;
						}
						
					
						
						temp_cashflowbanktotal_list.put("bank", bank_itr_total);
						temp_cashflowbanktotal_list.put("topup", cashflowbank_topup_total);
						temp_cashflowbanktotal_list.put("withdraw", cashflowbank_withdraw_total);
						
						temp_cashflowbanktotal_list.put("wl", cashflowbank_topup_total - cashflowbank_withdraw_total );
						
						result_list_total.add(temp_cashflowbanktotal_list);
				 
			}
			
			if(banklist == null) {
				
				 Map <String , Object> temp_cashflowbanktotal_report_list = new HashMap<>();
				
				Double cashfproduct_report_topup = wettopupService.cashflowbank_topup__report_total(wettopup.getCompanyid1(),wettopup.getDateOfissue(), wettopup.getTodate());
				
				Double cashfproduct_report_withdraw = wetwithdrawService.cashflowbank_withdraw_report_total(wettopup.getCompanyid1(),wettopup.getDateOfissue(), wettopup.getTodate());
				
				
				
				if(cashfproduct_report_topup == null || cashfproduct_report_topup == 0.0) {
					cashfproduct_report_topup = 0.0;
				}
				
				if(cashfproduct_report_withdraw == null || cashfproduct_report_withdraw == 0.0) {
					cashfproduct_report_withdraw = 0.0;
				}
				
				
				temp_cashflowbanktotal_report_list.put("product", "Report Total:");
				temp_cashflowbanktotal_report_list.put("topup", cashfproduct_report_topup);
				temp_cashflowbanktotal_report_list.put("withdraw", cashfproduct_report_withdraw);
				temp_cashflowbanktotal_report_list.put("wl", wl + (cashfproduct_report_topup - cashfproduct_report_withdraw));
				
				result_list_total.add(temp_cashflowbanktotal_report_list);
				
			}else {
				for(String bank_itr_total : temp_bank_list) {
					
					

					  Map <String , Object> temp_cashflowbanktotal_list = new HashMap<>();
						
						Double cashflowbank_topup_total = wettopupService.cashflowbank_topup_total(wettopup.getCompanyid1(), bank_itr_total, wettopup.getDateOfissue(), wettopup.getTodate());
						
						Double cashflowbank_withdraw_total = wetwithdrawService.cashflowbank_withdraw_total(wettopup.getCompanyid1(), bank_itr_total, wettopup.getDateOfissue(), wettopup.getTodate());

						
						if(cashflowbank_topup_total == null || cashflowbank_topup_total == 0.0) {
							cashflowbank_topup_total = 0.0;
						}
						
						if(cashflowbank_withdraw_total == null || cashflowbank_withdraw_total == 0.0) {
							cashflowbank_withdraw_total = 0.0;
						}
						
					
						
						temp_cashflowbanktotal_list.put("bank", bank_itr_total);
						temp_cashflowbanktotal_list.put("topup", cashflowbank_topup_total);
						temp_cashflowbanktotal_list.put("withdraw", cashflowbank_withdraw_total);
						
						temp_cashflowbanktotal_list.put("wl", wl +(cashflowbank_topup_total - cashflowbank_withdraw_total) );
						
						result_list_total.add(temp_cashflowbanktotal_list);
				 
			
			}
			
			
			}
			
		}

		response.put("code", HttpStatus.OK);
	    response.put("msg", "bank cash flow data");
	    response.put("data", result_list);
	    response.put("summary", result_list_total);
	    response.put("datasize", result_list.size());
	    response.put("summarysize", result_list_total.size());
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
}

}
