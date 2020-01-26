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
import com.example.bank.model.ReferalBonus;
import com.example.bank.model.TmpPlayer;
import com.example.bank.model.Wettopup;
import com.example.bank.model.Wetwithdraw;
import com.example.bank.service.CmcproductService;
import com.example.bank.service.ReferalbonusService;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WettpService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class CashflowproductplayerController {
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	CmcproductService cmcproductService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;
	
	@Autowired
	WettpService wettpService;
	
	@Autowired
	ReferalbonusService referalbonusService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	
	@PostMapping("cashflow-product")
	public ResponseEntity<Object> cashflowproduct(@RequestBody Wettopuprequestbean wettopup){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<Map<String,Object>> result_list_total = new ArrayList<>();
		List<Wettopup> mix_userid = new ArrayList<>();
		String conmethod = null;
		Double wl = 0.0;
		
		if(wettopup.getCompanyid1() != null) {
			String productlist = wettopup.getProductid().get(0);
			
			List<String> temp_product_list = new ArrayList<>();
			
			if(productlist == null) {
				
					List<CmcProduct> productlist_all = cmcproductService.getallproductbycompany(wettopup.getCompanyid1());
					for(CmcProduct productlist_itr: productlist_all) {
						temp_product_list.add(productlist_itr.getFldesc());
					}
				}else {
					temp_product_list = wettopup.getProductid();
				}
			
			
			for(String product_itr : temp_product_list) {
				
				   
				
				  List<Wettopup> cashflowproductplayer = wettopupService.cashflowproduct(wettopup.getDateOfissue(), wettopup.getTodate(), wettopup.getCompanyid1(), product_itr);
				 
				  List<Wetwithdraw> cashflowproductplayer_withdraw = wetwithdrawService.cashflowproduct_withdraw(wettopup.getDateOfissue(), wettopup.getTodate(), wettopup.getCompanyid1(), product_itr);
				  
				  List<ReferalBonus> cashflowproductplayer_referal = referalbonusService.cashflowproduct_referal(wettopup.getDateOfissue(), wettopup.getTodate(), wettopup.getCompanyid1(), product_itr);
				  
				  Iterator cashflowproductplayer_itr = cashflowproductplayer.iterator();
		 		   
				   while(cashflowproductplayer_itr.hasNext()){
					   Wettopup wettopup1 = new Wettopup();
					   Object[] obj1 = (Object[]) cashflowproductplayer_itr.next();
					   wettopup1.setUserid(String.valueOf(obj1[0]));
					   wettopup1.setProductid(String.valueOf(obj1[1]));
					   mix_userid.add(wettopup1);
				   }
				   
				   Iterator cashflowproductplayer_withdraw_itr = cashflowproductplayer_withdraw.iterator();
				   
				   while(cashflowproductplayer_withdraw_itr.hasNext()){
					   Wettopup wettopup1 = new Wettopup();
					   Object[] obj1 = (Object[]) cashflowproductplayer_withdraw_itr.next();
					   
					   
					   wettopup1.setUserid(String.valueOf(obj1[0]));
					   wettopup1.setProductid(String.valueOf(obj1[2]));
					   
					  boolean ans = mix_userid.contains(wettopup1);
					  if(!ans) {
						  mix_userid.add(wettopup1);
					  }
					   
					  
				   }
				  
				  
				   Iterator cashflowproductplayer_referal_itr = cashflowproductplayer_referal.iterator();
				   while(cashflowproductplayer_referal_itr.hasNext()){
					   Wettopup wettopup1 = new Wettopup();
					   Object[] obj1 = (Object[]) cashflowproductplayer_referal_itr.next();
					   
					   
					   wettopup1.setUserid(String.valueOf(obj1[0]));
					   wettopup1.setProductid(String.valueOf(obj1[1]));
					   
					  boolean ans = mix_userid.contains(wettopup1);
					  if(!ans) {
						  mix_userid.add(wettopup1);
					  }
					   
					  
				   }
			}  
				  
				  Iterator mix_userid_itr = mix_userid.iterator();
		 		   
				   while(mix_userid_itr.hasNext()){
					   Wettopup obj1 = (Wettopup) mix_userid_itr.next();
					   
					   Map <String , Object> temp_cashflowproduct_list = new HashMap<>();
						
						Double cashflowproduct_topup = wettopupService.topwlplayer_topup(obj1.getUserid(), wettopup.getCompanyid1(),obj1.getProductid(), wettopup.getDateOfissue(), wettopup.getTodate());
						
						Double cashflowproduct_withdraw = wetwithdrawService.topwlplayer_withdraw(obj1.getUserid(), wettopup.getCompanyid1(),obj1.getProductid(), wettopup.getDateOfissue(), wettopup.getTodate());
						
						Double cashflowproduct_transferout = wettpService.cashflowproduct_transferout(obj1.getUserid(), wettopup.getCompanyid1(),obj1.getProductid(), wettopup.getDateOfissue(), wettopup.getTodate());
				   
						Double cashflowproduct_transferin = wettpService.cashflowproduct_transferin(obj1.getUserid(), wettopup.getCompanyid1(),obj1.getProductid(), wettopup.getDateOfissue(), wettopup.getTodate());
						
						Double cashflowproduct_adjustment = wettopupService.topwlplayer_adjustment(obj1.getUserid(), wettopup.getCompanyid1(),obj1.getProductid(), wettopup.getDateOfissue(), wettopup.getTodate());
						
						Double cashflowproduct_bonus = wettopupService.topwlplayer_bonus(obj1.getUserid(), wettopup.getCompanyid1(),obj1.getProductid(), wettopup.getDateOfissue(), wettopup.getTodate());
						
						Double cashflowproduct_referal = referalbonusService.cashflowproduct_referal(obj1.getUserid(), wettopup.getCompanyid1(),obj1.getProductid(), wettopup.getDateOfissue(), wettopup.getTodate());
				  
						
						if(cashflowproduct_topup == null || cashflowproduct_topup == 0.0) {
							cashflowproduct_topup = 0.0;
						}
						
						if(cashflowproduct_withdraw == null || cashflowproduct_withdraw == 0.0) {
							cashflowproduct_withdraw = 0.0;
						}
						
						
						if(cashflowproduct_transferout == null || cashflowproduct_transferout == 0.0) {
							cashflowproduct_transferout = 0.0;
						}
						
						
						if(cashflowproduct_transferin == null || cashflowproduct_transferin == 0.0) {
							cashflowproduct_transferin = 0.0;
						}
						
						if(cashflowproduct_adjustment == null || cashflowproduct_adjustment == 0.0) {
							cashflowproduct_adjustment = 0.0;
						}
						
						if(cashflowproduct_bonus == null || cashflowproduct_bonus == 0.0) {
							cashflowproduct_bonus = 0.0;
						}
						

						if(cashflowproduct_referal == null || cashflowproduct_referal == 0.0) {
							cashflowproduct_referal = 0.0;
						}

						
						
						
						List<String> playerandtel = tmpplayerService.playername(obj1.getUserid(),obj1.getProductid(), wettopup.getCompanyid1());
						
						Iterator playerandtel_itr = playerandtel.iterator();
						
						while(playerandtel_itr.hasNext()) {
							 Object[] playerandtel_obj = (Object[]) playerandtel_itr.next();
							 conmethod = String.valueOf(playerandtel_obj[2]);
						}
						
						temp_cashflowproduct_list.put("product", obj1.getProductid());
						temp_cashflowproduct_list.put("userid", obj1.getUserid());
						temp_cashflowproduct_list.put("contactmethod", conmethod);
						temp_cashflowproduct_list.put("topup", cashflowproduct_topup);
						temp_cashflowproduct_list.put("withdraw", cashflowproduct_withdraw);
						temp_cashflowproduct_list.put("transferout", cashflowproduct_transferout);
						temp_cashflowproduct_list.put("transferin", cashflowproduct_transferin);
						
						temp_cashflowproduct_list.put("adjustment", cashflowproduct_adjustment);
						temp_cashflowproduct_list.put("bonus", cashflowproduct_bonus);
						temp_cashflowproduct_list.put("referalbonus", cashflowproduct_referal);
						temp_cashflowproduct_list.put("wl", cashflowproduct_topup - cashflowproduct_withdraw + cashflowproduct_transferin - cashflowproduct_transferout - cashflowproduct_adjustment - cashflowproduct_referal);
						
				        result_list.add(temp_cashflowproduct_list);
				   
				   }
			
			
		
		
		
			
			for(String product_itr_total : temp_product_list) {
				

					   Map <String , Object> temp_cashflowproducttotal_list = new HashMap<>();
						
						Double cashflowproduct_topup_total = wettopupService.cashflowproduct_topup_total(wettopup.getCompanyid1(), product_itr_total, wettopup.getDateOfissue(), wettopup.getTodate());
						
						Double cashflowproduct_withdraw_total = wetwithdrawService.cashflowproduct_withdraw_total(wettopup.getCompanyid1(), product_itr_total, wettopup.getDateOfissue(), wettopup.getTodate());
						
						Double cashflowproduct_transferout_total = wettpService.cashflowproduct_transferout_total(wettopup.getCompanyid1(),product_itr_total, wettopup.getDateOfissue(), wettopup.getTodate());
				   
						Double cashflowproduct_transferin_total = wettpService.cashflowproduct_transferin_total(wettopup.getCompanyid1(),product_itr_total, wettopup.getDateOfissue(), wettopup.getTodate());
						
						Double cashflowproduct_adjustment_total = wettopupService.cashflowproduct_adjustment_total(wettopup.getCompanyid1(),product_itr_total, wettopup.getDateOfissue(), wettopup.getTodate());
						
						Double cashflowproduct_bonus_total = wettopupService.cashflowproduct_bonus_total( wettopup.getCompanyid1(),product_itr_total, wettopup.getDateOfissue(), wettopup.getTodate());
						
						Double cashflowproduct_referal_total = referalbonusService.cashflowproduct_referal_total(wettopup.getCompanyid1(),product_itr_total, wettopup.getDateOfissue(), wettopup.getTodate());
				  
						
						if(cashflowproduct_topup_total == null || cashflowproduct_topup_total == 0.0) {
							cashflowproduct_topup_total = 0.0;
						}
						
						if(cashflowproduct_withdraw_total == null || cashflowproduct_withdraw_total == 0.0) {
							cashflowproduct_withdraw_total = 0.0;
						}
						
						
						if(cashflowproduct_transferout_total == null || cashflowproduct_transferout_total == 0.0) {
							cashflowproduct_transferout_total = 0.0;
						}
						
						
						if(cashflowproduct_transferin_total == null || cashflowproduct_transferin_total == 0.0) {
							cashflowproduct_transferin_total = 0.0;
						}
						
						if(cashflowproduct_adjustment_total == null || cashflowproduct_adjustment_total == 0.0) {
							cashflowproduct_adjustment_total = 0.0;
						}
						
						if(cashflowproduct_bonus_total == null || cashflowproduct_bonus_total == 0.0) {
							cashflowproduct_bonus_total = 0.0;
						}
						

						if(cashflowproduct_referal_total == null || cashflowproduct_referal_total == 0.0) {
							cashflowproduct_referal_total = 0.0;
						}

						
						
						temp_cashflowproducttotal_list.put("product", product_itr_total);
						temp_cashflowproducttotal_list.put("topup", cashflowproduct_topup_total);
						temp_cashflowproducttotal_list.put("withdraw", cashflowproduct_withdraw_total);
						temp_cashflowproducttotal_list.put("transferout", cashflowproduct_transferout_total);
						temp_cashflowproducttotal_list.put("transferin", cashflowproduct_transferin_total);
						
						temp_cashflowproducttotal_list.put("adjustment", cashflowproduct_adjustment_total);
						temp_cashflowproducttotal_list.put("bonus", cashflowproduct_bonus_total);
						temp_cashflowproducttotal_list.put("referalbonus", cashflowproduct_referal_total);
						temp_cashflowproducttotal_list.put("wl", cashflowproduct_topup_total - cashflowproduct_withdraw_total + cashflowproduct_transferin_total - cashflowproduct_transferout_total - cashflowproduct_adjustment_total - cashflowproduct_referal_total);
						
						result_list_total.add(temp_cashflowproducttotal_list);
				 
			}
			
			if(productlist == null) {
				
				 Map <String , Object> temp_cashflowproducttotal_report_list = new HashMap<>();
				
				Double cashfproduct_report_topup = wettopupService.playercashflow__report_topup(wettopup.getDateOfissue(), wettopup.getTodate(), wettopup.getCompanyid1());
				
				Double cashfproduct_report_withdraw = wetwithdrawService.playercashflow_report_withdraw(wettopup.getDateOfissue(), wettopup.getTodate(), wettopup.getCompanyid1());
				
				Double cashfproduct_report_transferout = wettpService.player_transferout_report_amount(wettopup.getDateOfissue(), wettopup.getTodate(), wettopup.getCompanyid1());
				
				Double cashfproduct_report_transferin = wettpService.player_transferin__report_amount(wettopup.getDateOfissue(), wettopup.getTodate(), wettopup.getCompanyid1());
				
				Double cashfproduct_report_adjustment = wettopupService.playercashflow__report_adjustment(wettopup.getDateOfissue(), wettopup.getTodate(), wettopup.getCompanyid1());
				
				Double cashfproduct_report_bonus = wettopupService.playercashflow__report_bonus(wettopup.getDateOfissue(), wettopup.getTodate(), wettopup.getCompanyid1());
				
				Double cashfproduct_report_referal = referalbonusService.cashflowproduct_referal_report_total(wettopup.getCompanyid1(), wettopup.getDateOfissue(), wettopup.getTodate());
			
				
				if(cashfproduct_report_topup == null || cashfproduct_report_topup == 0.0) {
					cashfproduct_report_topup = 0.0;
				}
				
				if(cashfproduct_report_withdraw == null || cashfproduct_report_withdraw == 0.0) {
					cashfproduct_report_withdraw = 0.0;
				}
				
				
				if(cashfproduct_report_transferout == null || cashfproduct_report_transferout == 0.0) {
					cashfproduct_report_transferout = 0.0;
				}
				
				
				if(cashfproduct_report_transferin == null || cashfproduct_report_transferin == 0.0) {
					cashfproduct_report_transferin = 0.0;
				}
				
				if(cashfproduct_report_adjustment == null || cashfproduct_report_adjustment == 0.0) {
					cashfproduct_report_adjustment = 0.0;
				}
				
				if(cashfproduct_report_bonus == null || cashfproduct_report_bonus == 0.0) {
					cashfproduct_report_bonus = 0.0;
				}
				

				if(cashfproduct_report_referal == null || cashfproduct_report_referal == 0.0) {
					cashfproduct_report_referal = 0.0;
				}

				
			
				temp_cashflowproducttotal_report_list.put("product", "Report Total:");
				temp_cashflowproducttotal_report_list.put("topup", cashfproduct_report_topup);
				temp_cashflowproducttotal_report_list.put("withdraw", cashfproduct_report_withdraw);
				temp_cashflowproducttotal_report_list.put("transferout", cashfproduct_report_transferout);
				temp_cashflowproducttotal_report_list.put("transferin", cashfproduct_report_transferin);
				
				temp_cashflowproducttotal_report_list.put("adjustment", cashfproduct_report_adjustment);
				temp_cashflowproducttotal_report_list.put("bonus", cashfproduct_report_bonus);
				temp_cashflowproducttotal_report_list.put("referalbonus", cashfproduct_report_referal);
				temp_cashflowproducttotal_report_list.put("wl", cashfproduct_report_topup - cashfproduct_report_withdraw + cashfproduct_report_transferin - cashfproduct_report_transferout - cashfproduct_report_adjustment - cashfproduct_report_referal);
				
				result_list_total.add(temp_cashflowproducttotal_report_list);
				
			}else {
				for(String product_itr_total : temp_product_list) {
					

					   Map <String , Object> temp_cashflowproducttotal_list = new HashMap<>();
						
						Double cashflowproduct_topup_total = wettopupService.cashflowproduct_topup_total(wettopup.getCompanyid1(), product_itr_total, wettopup.getDateOfissue(), wettopup.getTodate());
						
						Double cashflowproduct_withdraw_total = wetwithdrawService.cashflowproduct_withdraw_total(wettopup.getCompanyid1(), product_itr_total, wettopup.getDateOfissue(), wettopup.getTodate());
						
						Double cashflowproduct_transferout_total = wettpService.cashflowproduct_transferout_total(wettopup.getCompanyid1(),product_itr_total, wettopup.getDateOfissue(), wettopup.getTodate());
				   
						Double cashflowproduct_transferin_total = wettpService.cashflowproduct_transferin_total(wettopup.getCompanyid1(),product_itr_total, wettopup.getDateOfissue(), wettopup.getTodate());
						
						Double cashflowproduct_adjustment_total = wettopupService.cashflowproduct_adjustment_total(wettopup.getCompanyid1(),product_itr_total, wettopup.getDateOfissue(), wettopup.getTodate());
						
						Double cashflowproduct_bonus_total = wettopupService.cashflowproduct_bonus_total( wettopup.getCompanyid1(),product_itr_total, wettopup.getDateOfissue(), wettopup.getTodate());
						
						Double cashflowproduct_referal_total = referalbonusService.cashflowproduct_referal_total(wettopup.getCompanyid1(),product_itr_total, wettopup.getDateOfissue(), wettopup.getTodate());
				  
						
						if(cashflowproduct_topup_total == null || cashflowproduct_topup_total == 0.0) {
							cashflowproduct_topup_total = 0.0;
						}
						
						if(cashflowproduct_withdraw_total == null || cashflowproduct_withdraw_total == 0.0) {
							cashflowproduct_withdraw_total = 0.0;
						}
						
						
						if(cashflowproduct_transferout_total == null || cashflowproduct_transferout_total == 0.0) {
							cashflowproduct_transferout_total = 0.0;
						}
						
						
						if(cashflowproduct_transferin_total == null || cashflowproduct_transferin_total == 0.0) {
							cashflowproduct_transferin_total = 0.0;
						}
						
						if(cashflowproduct_adjustment_total == null || cashflowproduct_adjustment_total == 0.0) {
							cashflowproduct_adjustment_total = 0.0;
						}
						
						if(cashflowproduct_bonus_total == null || cashflowproduct_bonus_total == 0.0) {
							cashflowproduct_bonus_total = 0.0;
						}
						

						if(cashflowproduct_referal_total == null || cashflowproduct_referal_total == 0.0) {
							cashflowproduct_referal_total = 0.0;
						}

						
						
						temp_cashflowproducttotal_list.put("product", product_itr_total);
						temp_cashflowproducttotal_list.put("topup", cashflowproduct_topup_total);
						temp_cashflowproducttotal_list.put("withdraw", cashflowproduct_withdraw_total);
						temp_cashflowproducttotal_list.put("transferout", cashflowproduct_transferout_total);
						temp_cashflowproducttotal_list.put("transferin", cashflowproduct_transferin_total);
						
						temp_cashflowproducttotal_list.put("adjustment", cashflowproduct_adjustment_total);
						temp_cashflowproducttotal_list.put("bonus", cashflowproduct_bonus_total);
						temp_cashflowproducttotal_list.put("referalbonus", cashflowproduct_referal_total);
						temp_cashflowproducttotal_list.put("wl", wl +(cashflowproduct_topup_total - cashflowproduct_withdraw_total + cashflowproduct_transferin_total - cashflowproduct_transferout_total - cashflowproduct_adjustment_total - cashflowproduct_referal_total));
						
						result_list_total.add(temp_cashflowproducttotal_list);
				 
			
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
