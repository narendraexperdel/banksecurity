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
import com.example.bank.service.CmcproductService;
import com.example.bank.service.ExpensestypeService;
import com.example.bank.service.OthIncomeService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class MonthlyactiveplayerController {
	
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
	
	
	@PostMapping("monthly-active-player")
	public ResponseEntity<Object> monthlyactiveplayer(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<Integer,Object>> result_list = new ArrayList<>();
		List<Map<String,Object>> result_list1_product = new ArrayList<>();
		
	
		
		if(newplayerreg.getCompanyid1() !=null) {
			
			
			List<String> temp_product_list = new ArrayList<>();
			
			
				
					List<CmcProduct> productlist_all = cmcproductService.getallproductbycompany(newplayerreg.getCompanyid1());
					for(CmcProduct productlist_itr: productlist_all) {
						temp_product_list.add(productlist_itr.getFldesc());
					}
				
		
			
			Date date = newplayerreg.getDateOfissue();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date); 
			
			String month = Integer.toString(cal.get(Calendar.MONTH)+1); 
			String year = Integer.toString(cal.get(Calendar.YEAR)); 
			
			
			List<Wettopup> distinctdate = wettopupService.distinct(month, year, newplayerreg.getCompanyid1());
			Map<String,Object> result_list2 = new HashMap<>();
			
			Map<String,Object> temp_firstrow1_list = new HashMap<>();
			
			Map<Integer,Object> map1 = new HashMap<>();
			
			map1.put(0,  "Date");
			 int i = 1;
			 for(String cmcproduct_itr1 : temp_product_list) {
				
				 map1.put(i, cmcproduct_itr1);
				 i++;
			 }
			System.out.println(map1);
			result_list.add(map1);
			
			
			/* for(String cmcproduct_itr1 : temp_product_list) {
				 
				 Map<String, Object> temp_month_productlist1_list = new HashMap<>();
				 Map<String,Object> temp_firstrow_list = new HashMap<>();
				 temp_firstrow_list.put("Date", "Date");
				 temp_month_productlist1_list.put("product", cmcproduct_itr1);
				 temp_month_productlist1_list.put("cmcproduct_itr", cmcproduct_itr1);   
				 temp_firstrow1_list.put("productcount", temp_month_productlist1_list);
				 result_list.add(temp_firstrow1_list);
			 }*/
			
			
			   
			
			
			Iterator distinctdate_itr = distinctdate.iterator();
			while(distinctdate_itr.hasNext()){
				   Date obj = (Date) distinctdate_itr.next();
				   
					String pattern = "yyyy-MM-dd";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					String date1 = simpleDateFormat.format(obj);
				   
				   Map<String, Object> temp_month_active_list = new HashMap<>();
				  
					Map<String,Object> temp_monthlyt_list = new HashMap<>();
				
					Map<String,Object> result_list1 = new HashMap<>();
					
					
					int j= 1;
					
					Map<Integer,Object> map2 = new HashMap<>();
					map2.put(0, date1);
				
				   for(String cmcproduct_itr : temp_product_list) {
					   
					   List<Wettopup> monthly_activeplayer = wettopupService.monthlyactiveplayer(newplayerreg.getCompanyid1(), obj, cmcproduct_itr);
					   
					   Iterator monthly_activeplayer_itr = monthly_activeplayer.iterator();
						while(monthly_activeplayer_itr.hasNext()){
							Integer count = (Integer) monthly_activeplayer_itr.next();
							
							 map2.put(j, count);
							 j++;
							
							/*Map<String,Object> temp_user_list = new HashMap<>();
							temp_user_list.put("Date", date1);
//							  temp_month_productlist_list = new HashMap<>();
							   Integer count = (Integer) monthly_activeplayer_itr.next();
							   Map<String, Object> temp_month_productlist_list = new HashMap<>();
							   temp_month_productlist_list.put("product", cmcproduct_itr);
							   temp_month_productlist_list.put("cmcproduct_itr", count);   
							   temp_user_list.put("productcount", temp_month_productlist_list);
							   result_list.add(temp_user_list);*/
						}
			
				   }
				
				   result_list.add(map2);
			
			}
			
     Map<Integer,Object> map4 = new HashMap<>();
			
			map4.put(0, "Report Total :");
			
			int l = 1;
			
			  int regionIndex = 1;
			
			
			   for(Map<Integer, Object> region : result_list) {
				   
				   System.out.println("\nIndia Region - " + regionIndex);
				   
		            System.out.println("============================"
		                    + "======================");
		 
		            // get entrySet() into Set
		            Set<Integer> setOfIndianStates = region.keySet();
		 
		            // Collection Iterator
		            Iterator<Integer> iterator = 
		                    setOfIndianStates.iterator();
		 
		            // iterate using while-loop after getting Iterator
		            while(iterator.hasNext()) {
		 
		                Integer key = iterator.next();
		                
		                if(regionIndex > 1 && key !=0) {
		                
		                System.out.println("State : " + key 
		                        + "\tCapital : " + region.get(key));
		                
		                
		                if(regionIndex == 2) {
		                map4.put(key, (Integer)region.get(key) );
		                }else {
		                	 map4.put(key, (Integer)region.get(key) + (Integer)map4.get(key) );
		                }
		                
		                }
		            }
		 
		            // increment region index by 1
		            regionIndex++;
		        
		          
		        }
			
			
			   result_list.add(map4);
			
			
			for(String cmcproduct_itr1 : temp_product_list) {
				
				List<Wettopup> monthly_activeplayer_total = wettopupService.monthlyactiveplayer_total(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), cmcproduct_itr1);
				
				 
				   Iterator monthly_activeplayer_total_itr = monthly_activeplayer_total.iterator();
					while(monthly_activeplayer_total_itr.hasNext()){
						Map<String,Object> map3 = new HashMap<>();
						
						Integer count = (Integer) monthly_activeplayer_total_itr.next();
						 map3.put("count", count);
						 map3.put("product", cmcproduct_itr1);
						 result_list1_product.add(map3);
					}
				
				
			 }
			
			
			
			 Map<String,Object> map7 = new HashMap<>();		
				
				  int regionIndex1 = 1;
				
				
				   for(Map<String, Object> region : result_list1_product) {
					   
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
			                
			                
			                if(regionIndex1 == 1 && key == "count") {
			                map7.put("count", (Integer)region.get(key) );
			                }else if(key == "count" && regionIndex1 > 1) {
			                	System.out.println((Integer)region.get(key));
			                	System.out.println((Integer)map7.get(key));
			                	 map7.put("count", (Integer)region.get(key) + (Integer)map7.get(key) );
			                }
			                
			                
			            }
			 
			            // increment region index by 1
			            regionIndex1++;
			        
			          
			        }
					map7.put("product", "");
				
				   result_list1_product.add(map7);
			
			
			/*
			List<Wettopup> monthly_activeplayer_report_total = wettopupService.monthlyactiveplayer_total(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue());
			 Iterator monthly_activeplayer_report_total_itr = monthly_activeplayer_report_total.iterator();
				while(monthly_activeplayer_report_total_itr.hasNext()){
					Map<String,Object> map6 = new HashMap<>();
					
					Integer count = (Integer) monthly_activeplayer_report_total_itr.next();
					map6.put("count", count);
					map6.put("product", "");
					 result_list1_product.add(map6);
				}*/
			
		}
		
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Monthly Active player data");
	    response.put("data", result_list);
	    response.put("productdata", result_list1_product);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
	}
	
	@PostMapping("monthly-palyer-chart")
	public ResponseEntity<Object> monthlyactiveplyer_chart(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<Map<String,Object>> result_list1_product = new ArrayList<>();
		double d = 0.0;
	
		
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
			System.out.println(distinctdate.size()+"date size");
			
			int datesize = distinctdate.size();
			 d=datesize;
			
			/*List<Wettopup> monthly_activeplayer_total = wettopupService.monthlyactiveplayer_average(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), temp_product_list);
			
			   Iterator monthly_activeplayer_total_itr = monthly_activeplayer_total.iterator();
				while(monthly_activeplayer_total_itr.hasNext()){
					Map<String,Object> map3 = new HashMap<>();
					
					Integer count = (Integer) monthly_activeplayer_total_itr.next();
					 map3.put("averageplayer", count/d);
					 result_list1_product.add(map3);
				}*/
			
			
			Iterator distinctdate_itr = distinctdate.iterator();
			while(distinctdate_itr.hasNext()){
				   Date obj = (Date) distinctdate_itr.next();
				   
					String pattern = "yyyy-MM-dd";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					String date1 = simpleDateFormat.format(obj);
				   
				
					   List<Wettopup> monthly_activeplayer = wettopupService.monthlyactiveplayer_chart(newplayerreg.getCompanyid1(), obj, temp_product_list);
					   
					   Iterator monthly_activeplayer_itr = monthly_activeplayer.iterator();
						while(monthly_activeplayer_itr.hasNext()){
							Integer count = (Integer) monthly_activeplayer_itr.next();
							Map<String,Object> map5 = new HashMap<>();
							 map5.put("date", date1);
							 map5.put("count", count);
							result_list.add(map5);
						
						}
			
				   
			
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
	                
	                
	                if(regionIndex1 == 1 && key == "count") {
	                map7.put("count", (Integer)region.get(key) );
	                }else if(key == "count" && regionIndex1 > 1) {
	                	System.out.println((Integer)region.get(key));
	                	System.out.println((Integer)map7.get(key));
	                	 map7.put("count", (Integer)region.get(key) + (Integer)map7.get(key) );
	                }
	                
	                
	            }
	 
	            // increment region index by 1
	            regionIndex1++;
	        
	          
	        }
		   
		   Map<String,Object> map3 = new HashMap<>();
			
			
		   Double ap = (Integer) map7.get("count") / d;
		   

			 map3.put("averageplayer", ap);
			 result_list1_product.add(map3);
		}
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Monthly Active player chart data");
	    response.put("data", result_list);
	    response.put("averageplayer", result_list1_product);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
	}
}
