package com.example.bank.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.bean.Newplayerregbean;
import com.example.bank.model.CmcProduct;
import com.example.bank.model.Wettopup;
import com.example.bank.model.Wetwithdraw;
import com.example.bank.service.CmcproductService;
import com.example.bank.service.ExpensestypeService;
import com.example.bank.service.OthIncomeService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class TopvswithdrawController {
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;;
	
	@Autowired
	ExpensestypeService expensetypeService;
	
	@Autowired
	OthIncomeService othincomeService;
	
	@Autowired
	CmcproductService cmcproductService;
	
	
	@PostMapping("top-vs-withdraw")
	public ResponseEntity<Object> topvswithdraw(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<Map<String,Object>> result_list1_product = new ArrayList<>();
		List<Map<String,Object>> result_topuprange_product = new ArrayList<>();
		List<Map<String,Object>> result_withdrawrange_product = new ArrayList<>();
		double d= 0.0;
	
		
		if(newplayerreg.getCompanyid1() !=null) {
			
            String productlist = newplayerreg.getProduct().get(0);
			
			List<String> temp_product_list = new ArrayList<>();
			
			  if(productlist == null) {
					List<CmcProduct> productlist_all = cmcproductService.getallproductbycompany(newplayerreg.getCompanyid1());
					for(CmcProduct productlist_itr: productlist_all) {
						temp_product_list.add(productlist_itr.getFldesc());
					}
			  }else {
				  temp_product_list = newplayerreg.getProduct();
			  }
				
		
			
			Date date = newplayerreg.getDateOfissue();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date); 
			
			String month = Integer.toString(cal.get(Calendar.MONTH)+1); 
			String year = Integer.toString(cal.get(Calendar.YEAR)); 
			
			Map<String,Object> temp_topuprange_list = new HashMap<>();
			Map<String,Object> temp_twithdrawrange_list = new HashMap<>();
			
			
			List<Wettopup> topup1 = wettopupService.topvswithdraw_topuprange_hundred(month, year, newplayerreg.getCompanyid1(), temp_product_list);
			
			List<Wettopup> topup2 = wettopupService.topvswithdraw_topuprange_hundredone(month, year, newplayerreg.getCompanyid1(), temp_product_list);
			
			List<Wettopup> topup3 = wettopupService.topvswithdraw_topuprange_fivehundred(month, year, newplayerreg.getCompanyid1(), temp_product_list);
			
			
           List<Wetwithdraw> withdraw1 = wetwithdrawService.topvswithdraw_topuprange_hundred(month, year, newplayerreg.getCompanyid1(), temp_product_list);
			
			List<Wetwithdraw> withdraw2 = wetwithdrawService.topvswithdraw_topuprange_hundredone(month, year, newplayerreg.getCompanyid1(), temp_product_list);
			
			List<Wetwithdraw> withdraw3 = wetwithdrawService.topvswithdraw_topuprange_fivehundred(month, year, newplayerreg.getCompanyid1(), temp_product_list);
			
			
			Iterator topup1_itr = topup1.iterator();
			while(topup1_itr.hasNext()){
				 Integer obj = (Integer) topup1_itr.next();
				 
				 temp_topuprange_list.put("< 100", obj);
				 
			}
			
			Iterator topup2_itr = topup2.iterator();
			while(topup2_itr.hasNext()){
				 Integer obj = (Integer) topup2_itr.next();
				 
				 temp_topuprange_list.put("100 - 500", obj);
				 
			}
			
			Iterator topup3_itr = topup3.iterator();
			while(topup3_itr.hasNext()){
				 Integer obj = (Integer) topup3_itr.next();
				 
				 temp_topuprange_list.put("> 500", obj);
				 
			}
			
			
			Iterator withdraw1_itr = withdraw1.iterator();
			while(withdraw1_itr.hasNext()){
				 Integer obj = (Integer) withdraw1_itr.next();
				 
				 temp_twithdrawrange_list.put("< 100", obj);
				 
			}
			
			Iterator withdraw2_itr = withdraw2.iterator();
			while(withdraw2_itr.hasNext()){
				 Integer obj = (Integer) withdraw2_itr.next();
				 
				 temp_twithdrawrange_list.put("100 - 500", obj);
				 
			}
			
			Iterator withdraw3_itr = withdraw3.iterator();
			while(withdraw3_itr.hasNext()){
				 Integer obj = (Integer) withdraw3_itr.next();
				 
				 temp_twithdrawrange_list.put("> 500", obj);
				 
			}
			
			result_topuprange_product.add(temp_topuprange_list);
			result_withdrawrange_product.add(temp_twithdrawrange_list);
			
			
			List<Wettopup> distinctdate = wettopupService.distinct(month, year, newplayerreg.getCompanyid1());
			
			Iterator distinctdate_itr = distinctdate.iterator();
			while(distinctdate_itr.hasNext()){
				 
				Map<String,Object> temp_topwith_list = new HashMap<>();
				
				   Date obj = (Date) distinctdate_itr.next();
				   
					String pattern = "yyyy-MM-dd";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					String date1 = simpleDateFormat.format(obj);
					
					Double topup = wettopupService.topvswithdraw_topup(obj, newplayerreg.getCompanyid1(), temp_product_list);
					
					Double withdraw = wetwithdrawService.topvswithdraw_withdraw(obj, newplayerreg.getCompanyid1(), temp_product_list);
					
					if(topup == null || topup == 0.0) {
						topup = 0.0;
					}
					
					if(withdraw == null || withdraw == 0.0) {
						withdraw = 0.0;
					}
					
					temp_topwith_list.put("date", date1);
					temp_topwith_list.put("topup", topup);
					temp_topwith_list.put("withdraw", withdraw);
					temp_topwith_list.put("wl", topup - withdraw);
					
					result_list.add(temp_topwith_list);
					
			}
			
			 Map<String,Object> map7 = new HashMap<>();		
				
			  int regionIndex1 = 1;
			
			
			   for(Map<String, Object> region : result_list) {
				   
				   System.out.println("\nIndia Region - " + regionIndex1);
				   
		            System.out.println("============================"
		                    + "======================");
		 
		            // get entrySet() into Set
		            Set<String> setOfIndianStates = region.keySet();
		 
		            // Collection Iterator
		            Iterator<String> iterator = 
		                    setOfIndianStates.iterator();
		 
		            // iterate using while-loop after getting Iterator
		            while(iterator.hasNext()) {
		 
		                String key = iterator.next();
		                
		                System.out.println("State : " + key 
		                        + "\tCapital : " + region.get(key));
		                
		                
		                if(regionIndex1 == 1 && key == "topup") {
		                map7.put("topup", (Double)region.get(key) );
		                }else if(key == "topup" && regionIndex1 > 1) {
		                	System.out.println((Double)region.get(key));
		                	System.out.println((Double)map7.get(key));
		                	 map7.put("topup", (Double)region.get(key) + (Double)map7.get(key) );
		                }
		                
		                if(regionIndex1 == 1 && key == "withdraw") {
			                map7.put("withdraw", (Double)region.get(key) );
			                }else if(key == "withdraw" && regionIndex1 > 1) {
			                	System.out.println((Double)region.get(key));
			                	System.out.println((Double)map7.get(key));
			                	 map7.put("withdraw", (Double)region.get(key) + (Double)map7.get(key) );
			                }
		                
		                if(regionIndex1 == 1 && key == "wl") {
			                map7.put("wl", (Double)region.get(key) );
			                }else if(key == "withdraw" && regionIndex1 > 1) {
			                	System.out.println((Double)region.get(key));
			                	System.out.println((Double)map7.get(key));
			                	 map7.put("wl", (Double)region.get(key) + (Double)map7.get(key) );
			                }
		                
		                
		            }
		 
		            // increment region index by 1
		            regionIndex1++;
		        
		          
		        }
			   
			   Map<String,Object> map3 = new HashMap<>();
				int datesize = distinctdate.size();
				 d=datesize;
				
			   Double averagetopup = (Double) map7.get("topup") / d;
			   Double averagewithdraw = (Double) map7.get("withdraw") / d;
			   Double averagewl = (Double) map7.get("wl") / d;
			   

				 map3.put("averagetopup", averagetopup);
				 map3.put("averagewithdraw", averagewithdraw);
				 map3.put("averagewl", averagetopup - averagewithdraw);
				 result_list1_product.add(map3);
			
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "top vs withdraw chart  data");
	    response.put("data", result_list);
	    response.put("avragedata", result_list1_product);
	    response.put("topuprange", result_topuprange_product);
	    response.put("withdrawrange", result_withdrawrange_product);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("top-vs-withdraw-trans")
	public ResponseEntity<Object> topvswithdrawtransaction(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<Map<String,Object>> result_list1_product = new ArrayList<>();
		List<Map<String,Object>> result_topuprange_product = new ArrayList<>();
		List<Map<String,Object>> result_withdrawrange_product = new ArrayList<>();
		double d= 0.0;
	
		
		if(newplayerreg.getCompanyid1() !=null) {
			
            String productlist = newplayerreg.getProduct().get(0);
			
			List<String> temp_product_list = new ArrayList<>();
			
			  if(productlist == null) {
					List<CmcProduct> productlist_all = cmcproductService.getallproductbycompany(newplayerreg.getCompanyid1());
					for(CmcProduct productlist_itr: productlist_all) {
						temp_product_list.add(productlist_itr.getFldesc());
					}
			  }else {
				  temp_product_list = newplayerreg.getProduct();
			  }
				
		
			
			Date date = newplayerreg.getDateOfissue();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date); 
			
			String month = Integer.toString(cal.get(Calendar.MONTH)+1); 
			String year = Integer.toString(cal.get(Calendar.YEAR)); 
			
			List<Wettopup> distinctdate = wettopupService.distinct(month, year, newplayerreg.getCompanyid1());
			
			Iterator distinctdate_itr = distinctdate.iterator();
			while(distinctdate_itr.hasNext()){
				 
				Map<String,Object> temp_topwith_list = new HashMap<>();
				
				   Date obj = (Date) distinctdate_itr.next();
				   
					String pattern = "yyyy-MM-dd";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					String date1 = simpleDateFormat.format(obj);
					
					List<Wettopup> topup = wettopupService.topvswithdraw_topup_transac(obj, newplayerreg.getCompanyid1(), temp_product_list);
					
					List<Wetwithdraw> withdraw = wetwithdrawService.topvswithdraw_withdraw_transac(obj, newplayerreg.getCompanyid1(), temp_product_list);
					
					temp_topwith_list.put("date", date1);
					
					Iterator topup_itr = topup.iterator();
					while(topup_itr.hasNext()){
						 
						
						Integer obj1 = (Integer) topup_itr.next();
						temp_topwith_list.put("topuptransaction", obj1);
						   
					}
					
					
					Iterator withdraw_itr = withdraw.iterator();
					while(withdraw_itr.hasNext()){
						 
						
						Integer obj2 = (Integer) withdraw_itr.next();
						temp_topwith_list.put("withdrawtransaction", obj2);  
						   
					}
					
					result_list.add(temp_topwith_list);
					
			}
			
			 Map<String,Object> map7 = new HashMap<>();		
				
			  int regionIndex1 = 1;
			
			
			   for(Map<String, Object> region : result_list) {
				   
				   System.out.println("\nIndia Region - " + regionIndex1);
				   
		            System.out.println("============================"
		                    + "======================");
		 
		            // get entrySet() into Set
		            Set<String> setOfIndianStates = region.keySet();
		 
		            // Collection Iterator
		            Iterator<String> iterator = 
		                    setOfIndianStates.iterator();
		 
		            // iterate using while-loop after getting Iterator
		            while(iterator.hasNext()) {
		 
		                String key = iterator.next();
		                
		                System.out.println("State : " + key 
		                        + "\tCapital : " + region.get(key));
		                
		                
		                if(regionIndex1 == 1 && key == "topuptransaction") {
		                map7.put("topuptransaction", (Integer)region.get(key) );
		                }else if(key == "topuptransaction" && regionIndex1 > 1) {
		                	System.out.println((Integer)region.get(key));
		                	System.out.println((Integer)map7.get(key));
		                	 map7.put("topuptransaction", (Integer)region.get(key) + (Integer)map7.get(key) );
		                }
		                
		                if(regionIndex1 == 1 && key == "withdrawtransaction") {
			                map7.put("withdrawtransaction", (Integer)region.get(key) );
			                }else if(key == "withdrawtransaction" && regionIndex1 > 1) {
			                	System.out.println((Integer)region.get(key));
			                	System.out.println((Integer)map7.get(key));
			                	 map7.put("withdrawtransaction", (Integer)region.get(key) + (Integer)map7.get(key) );
			                }
		                
		              
		                
		                
		            }
		 
		            // increment region index by 1
		            regionIndex1++;
		        
		          
		        }
			   
			   Map<String,Object> map3 = new HashMap<>();
				int datesize = distinctdate.size();
				 d=datesize;
				
			   Double averagetopup = (Integer) map7.get("topuptransaction") / d;
			   Double averagewithdraw = (Integer) map7.get("withdrawtransaction") / d;
			   

				 map3.put("averagetopuptransaction", averagetopup);
				 map3.put("averagewithdrawtransaction", averagewithdraw);
				 result_list1_product.add(map3);
			
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "top vs withdraw chart  data");
	    response.put("data", result_list);
	    response.put("avragedata", result_list1_product);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}


}
