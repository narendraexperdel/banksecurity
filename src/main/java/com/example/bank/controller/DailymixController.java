package com.example.bank.controller;

import java.text.ParseException;
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

import com.example.bank.bean.Wettopuprequestbean;
import com.example.bank.model.CmcProduct;
import com.example.bank.model.Wettopup;
import com.example.bank.model.Wettp;
import com.example.bank.model.Wetwithdraw;
import com.example.bank.service.CmcproductService;
import com.example.bank.service.ReferalbonusService;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WettpService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class DailymixController {
	
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
	
	
	@PostMapping("daily-mix")
	public ResponseEntity<Object> dailymix(@RequestBody Wettopuprequestbean wettopup){
	Map<String, Object> response = new HashMap<>();
	List<Map<String,Object>> result_list_d_topup = new ArrayList<>();
	List<Map<String,Object>> result_list_d_topup_total = new ArrayList<>();
	List<Map<String,Object>> result_list_d_withdraw = new ArrayList<>();
	List<Map<String,Object>> result_list_d_withdraw_total = new ArrayList<>();
	List<Map<String,Object>> result_list_d_tp = new ArrayList<>();
	List<Map<String,Object>> result_list_d_tp_total = new ArrayList<>();
	List<Map<String,Object>> result_list_total = new ArrayList<>();
	List<Wettopup> mix_userid = new ArrayList<>();
	String conmethod = null;
	Double wl = 0.0;
	Double topup_trancid = 0.0;
	
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
		
		List<Wettopup> dailymix_topup = wettopupService.daily_mix_wettopup(wettopup.getCompanyid1(), wettopup.getDateOfissue(), wettopup.getTodate(), temp_product_list);
		 
		
		   Iterator dailymix_topup_itr = dailymix_topup.iterator();
		   while(dailymix_topup_itr.hasNext()) {
			   Map<String, Object> temp_player_info = new HashMap<>();
			   
			   Object[] obj = (Object[]) dailymix_topup_itr.next();
			   
			   Map<String,Object> temp_dailymix_topup = new HashMap<>();
				 
				 temp_dailymix_topup.put("trancid", String.valueOf(obj[0]));
				 temp_dailymix_topup.put("userid", String.valueOf(obj[6]));
				 temp_dailymix_topup.put("product", String.valueOf(obj[12]));
				 temp_dailymix_topup.put("amount", String.valueOf(obj[2]));
				 temp_dailymix_topup.put("bonus", String.valueOf(obj[3]));
				 
				 
				 Calendar cal = Calendar.getInstance();
				// remove next line if you're always using the current time.
				 Date date1 = new Date();
				try {
					date1 = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(obj[16]));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				 
				cal.setTime(date1);
				cal.add(Calendar.HOUR, -8);
				Date fiveandHourBack = cal.getTime();
				 
				 
				 
				 temp_dailymix_topup.put("claimdate", String.valueOf(obj[16]));
				 
				 result_list_d_topup.add(temp_dailymix_topup);
		   }
		
		
		
		 /*for(Wettopup wettopup_itr : dailymix_topup) {
			 
			 Map<String,Object> temp_dailymix_topup = new HashMap<>();
			 
			 temp_dailymix_topup.put("trancid", wettopup_itr.getTrancid());
			 temp_dailymix_topup.put("userid", wettopup_itr.getUserid());
			 temp_dailymix_topup.put("product", wettopup_itr.getProductid());
			 temp_dailymix_topup.put("amount", wettopup_itr.getAmount());
			 temp_dailymix_topup.put("bonus", wettopup_itr.getBonus());
			 
			 
			 Calendar cal = Calendar.getInstance();
			// remove next line if you're always using the current time.
			cal.setTime(wettopup_itr.getClaimdate());
			cal.add(Calendar.HOUR, -8);
			Date fiveandHourBack = cal.getTime();
			 
			 
			 
			 temp_dailymix_topup.put("claimdate", fiveandHourBack);
			 
			 result_list_d_topup.add(temp_dailymix_topup);
		 }*/
		 
		 Map<String,Object> temp_dailymix_topup = new HashMap<>();
		 
		 List<Wettopup>  d_topup_amount = wettopupService.daily_mix_topup_amount(wettopup.getCompanyid1(), wettopup.getDateOfissue(), wettopup.getTodate(), temp_product_list);
		 
		 Iterator d_topup_amount_itr = d_topup_amount.iterator();
			
			while(d_topup_amount_itr.hasNext()) {
				
				Double d_t_amount = (Double) d_topup_amount_itr.next();
				
				if(d_t_amount == null || d_t_amount == 0.0) {
					d_t_amount = 0.0;
				}
				
				temp_dailymix_topup.put("amount_total", d_t_amount);
			}
				List<Wettopup> d_topup_bonus = wettopupService.daily_mix_topup_bonus(wettopup.getCompanyid1(), wettopup.getDateOfissue(), wettopup.getTodate(), temp_product_list);
		 
				 Iterator d_topup_bonus_itr = d_topup_bonus.iterator();
				
				while(d_topup_bonus_itr.hasNext()) {
					
					Double d_t_bonus = (Double) d_topup_bonus_itr.next();
					
					if(d_t_bonus == null || d_t_bonus == 0.0) {
						d_t_bonus = 0.0;
					}
					
					temp_dailymix_topup.put("bonus_total", d_t_bonus);
				}
				
				List<Wettopup> d_topup_trancid = wettopupService.daily_mix_topup_trancid(wettopup.getCompanyid1(), wettopup.getDateOfissue(), wettopup.getTodate(), temp_product_list);
	
				for(Object d_topup_trancid_itr :d_topup_trancid) {
		        	
		        	System.out.println(((Integer)d_topup_trancid_itr));
		        	
		        	topup_trancid = new Double(d_topup_trancid_itr.toString());
		        	temp_dailymix_topup.put("trancid_total", topup_trancid);
		        	
		        }
				
			result_list_d_topup_total.add(temp_dailymix_topup);
			
			
			List<Wetwithdraw> dailymix_withdraw = wetwithdrawService.daily_mix_wetwithdraw(wettopup.getCompanyid1(), wettopup.getDateOfissue(), wettopup.getTodate(), temp_product_list);
			 
			 for(Wetwithdraw wetwithdraw_itr : dailymix_withdraw) {
				 
				 Map<String,Object> temp_dailymix_withdraw = new HashMap<>();
				 
				 temp_dailymix_withdraw.put("trancid", wetwithdraw_itr.getTrancid());
				 temp_dailymix_withdraw.put("userid", wetwithdraw_itr.getUserid());
				 temp_dailymix_withdraw.put("product", wetwithdraw_itr.getProductid());
				 temp_dailymix_withdraw.put("amount", wetwithdraw_itr.getAmount());
				 
				 Calendar cal = Calendar.getInstance();
					// remove next line if you're always using the current time.
					cal.setTime(wetwithdraw_itr.getCsdonetime());
					cal.add(Calendar.HOUR, -8);
					Date fiveandHourBack = cal.getTime();
				 
				 
				 
				 temp_dailymix_withdraw.put("withdrawdate", fiveandHourBack);
				 
				 result_list_d_withdraw.add(temp_dailymix_withdraw);
			 }
			 
			 Map<String,Object> temp_dailymix_withdraw = new HashMap<>();
			 
			 Double  d_withdraw_amount = wetwithdrawService.daily_mix_withdraw_amount(wettopup.getCompanyid1(), wettopup.getDateOfissue(), wettopup.getTodate(), temp_product_list);
			 
			 Long d_withdraw_trancid = wetwithdrawService.daily_mix_withdraw_trancid(wettopup.getCompanyid1(), wettopup.getDateOfissue(), wettopup.getTodate(), temp_product_list);
		
			   if(d_withdraw_amount == null || d_withdraw_amount == 0.0) {
				   d_withdraw_amount = 0.0;
				}
				
				if(d_withdraw_trancid == null || d_withdraw_trancid == 0) {
					d_withdraw_trancid = (long) 0;
				}
				
				
				
				temp_dailymix_withdraw.put("trancid_total", d_withdraw_trancid);
				temp_dailymix_withdraw.put("amount_total", d_withdraw_amount);
				
				result_list_d_withdraw_total.add(temp_dailymix_withdraw);
			
				List<Wettp> dailymix_tp = wettpService.daily_mix_wettp(wettopup.getCompanyid1(), wettopup.getDateOfissue(), wettopup.getTodate(), temp_product_list);
				 
				 for(Wettp wettp_itr : dailymix_tp) {
					 
					 Map<String,Object> temp_dailymix_tp = new HashMap<>();
					 
					 temp_dailymix_tp.put("trancid", wettp_itr.getTrancid());
					 temp_dailymix_tp.put("fromuserid", wettp_itr.getFrmuser());
					 temp_dailymix_tp.put("fromproduct", wettp_itr.getFrmproduct());
					 temp_dailymix_tp.put("touserid", wettp_itr.getTouser());
					 temp_dailymix_tp.put("toproduct", wettp_itr.getToproduct());
					 temp_dailymix_tp.put("amount", wettp_itr.getAmount());
					 
					 Calendar cal = Calendar.getInstance();
						// remove next line if you're always using the current time.
						cal.setTime(wettp_itr.getCsdonedatetime());
						cal.add(Calendar.HOUR, -8);
						Date fiveandHourBack = cal.getTime();
					 
					 
					 temp_dailymix_tp.put("transferdate", fiveandHourBack);
					 
					 result_list_d_tp.add(temp_dailymix_tp);
				 }
				 
				 Map<String,Object> temp_dailymix_tp = new HashMap<>();
				 
				 Double  d_tp_amount = wettpService.daily_mix_wettp_amount(wettopup.getCompanyid1(), wettopup.getDateOfissue(), wettopup.getTodate(), temp_product_list);
				 
				 Long d_tp_trancid = wettpService.daily_mix_wettp_trancid(wettopup.getCompanyid1(), wettopup.getDateOfissue(), wettopup.getTodate(), temp_product_list);
			
				   if(d_tp_amount == null || d_tp_amount == 0.0) {
					   d_tp_amount = 0.0;
					}
					
					if(d_tp_trancid == null || d_tp_trancid == 0) {
						d_tp_trancid = (long) 0;
					}
					
					
					
					temp_dailymix_tp.put("trancid_total", d_tp_amount);
					temp_dailymix_tp.put("amount_total", d_tp_trancid);
					
					result_list_d_tp_total.add(temp_dailymix_tp);
			
		 
	}
		
	response.put("code", HttpStatus.OK);
    response.put("msg", "dailymix data");
    response.put("data", result_list_d_topup);
    response.put("datatotal", result_list_d_topup_total);
    response.put("withdraw", result_list_d_withdraw);
    response.put("withdrawtotal", result_list_d_withdraw_total);
    response.put("tp", result_list_d_tp);
    response.put("tptotal", result_list_d_tp_total);
    
    return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
