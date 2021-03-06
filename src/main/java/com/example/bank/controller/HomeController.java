package com.example.bank.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.bean.Newplayerregbean;
import com.example.bank.model.CmcProduct;
import com.example.bank.model.OthIncome;
import com.example.bank.model.WemPlayer;
import com.example.bank.model.Wettopup;
import com.example.bank.model.Wetwithdraw;
import com.example.bank.service.CmcCombankSerrvice;
import com.example.bank.service.CmcIncomeDetService;
import com.example.bank.service.CmcIncomeService;
import com.example.bank.service.CmcexpensesService;
import com.example.bank.service.CmcexpensesdetService;
import com.example.bank.service.CmcproductService;
import com.example.bank.service.ExpensestypeService;
import com.example.bank.service.OthIncomeService;
import com.example.bank.service.ReferalbonusService;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WemplayerService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WettpService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class HomeController {

	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;

	@Autowired
	CmcproductService cmcproductService;
	
	@Autowired
	CmcCombankSerrvice cmccombankService;
	
	@Autowired
	WettpService wettpService;
	
	@Autowired
	CmcIncomeService cmcincomeService;
	
	@Autowired
	CmcIncomeDetService cmcincomedetService;
	
	@Autowired
	OthIncomeService othincomeService;
	
	@Autowired
	ReferalbonusService referalbonusService;
	
	@Autowired
	CmcexpensesService cmcexpenseService;
	
	@Autowired
	CmcexpensesdetService cmcexpensedetService;
	
	@Autowired
	ExpensestypeService expensetypeService;
	
	@Autowired
	WemplayerService wemplayerService;
	
	@PostMapping("home-total")
	public ResponseEntity<Object> hometotal(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> report_total = new HashMap<>();
		int i = 0;
		Double topup_trancid = 0.0;
		
		
		   
		
		if(newplayerreg.getCompanyid1() != null) {
			
			
			if(newplayerreg.getDateOfissue() != null && newplayerreg.getTodate() != null) {
				 Date fromdate = newplayerreg.getDateOfissue();
				 Date toadte = newplayerreg.getDateOfissue();
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
				    newplayerreg.setDateOfissue(new Date(sdf.format(fromdate)));
				    newplayerreg.setTodate(new Date(sdf.format(fromdate)));
			}
		
			
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
			
			Double home_expense = expensetypeService.expensereportreportamount(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1());
			
			if(home_expense == null || home_expense == 0.0) {
				home_expense = 0.0;
			}
			
			report_total.put("expense", home_expense);
			
			Double home_transferout = wettpService.daily_mix_wettp_amount(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_product_list);
			
			if(home_transferout == null || home_transferout == 0.0) {
				home_transferout = 0.0;
			}
			
			report_total.put("transferout", home_transferout);
			
			List<Wettopup> home_topup = wettopupService.home_daily_mix_topup_amount(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_product_list);
			
             Iterator home_topup_itr = home_topup.iterator();
			
			while(home_topup_itr.hasNext()) {
				
				Double d_t_amount = (Double) home_topup_itr.next();
				
				if(d_t_amount == null || d_t_amount == 0.0) {
					d_t_amount = 0.0;
				}
				
				report_total.put("topup", d_t_amount);
			}
			
			
			List<Wettopup> home_topup_trancid = wettopupService.home_daily_mix_topup_trancid(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_product_list);
			
			
			for(Object home_topup_trancid_itr :home_topup_trancid) {
	        	
	        	System.out.println(((Integer)home_topup_trancid_itr));
	        	
	        	topup_trancid = new Double(home_topup_trancid_itr.toString());
	        	report_total.put("topuptrancid", topup_trancid);
	        	
	        }
			
		
			
			Double  home_withdraw = wetwithdrawService.daily_mix_withdraw_amount(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_product_list);
			
			if(home_withdraw == null || home_withdraw == 0.0) {
				home_withdraw = 0.0;
			}
			
			report_total.put("withdraw", home_withdraw);
			
			Long home_withdraw_trancid = wetwithdrawService.daily_mix_withdraw_trancid(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_product_list);
			
			report_total.put("withdrawtrancid", home_withdraw_trancid);
			
           Double  home_withdraw_gst = wetwithdrawService.daily_mix_withdraw_taxamount(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_product_list);
			
			if(home_withdraw_gst == null || home_withdraw_gst == 0.0) {
				home_withdraw_gst = 0.0;
			}
			
			report_total.put("withdrawgst", home_withdraw_gst);
			
			Double home_madj_amount = wettopupService.daily_mix_topup_madj_amount(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_product_list);
			
			if(home_madj_amount == null || home_madj_amount == 0.0) {
				home_madj_amount = 0.0;
			}
			
			report_total.put("madjamount", home_madj_amount);
			
             List<WemPlayer> activeplayer = wemplayerService.homeactiveplayer(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_product_list);
			
             Iterator activeplayer_itr = activeplayer.iterator();
             
             while(activeplayer_itr.hasNext()) {
            	 Integer obj1 = (Integer) activeplayer_itr.next();
            	 report_total.put("activeplayer", obj1);
             }
             
             
             List<Wettopup> d_topup_bonus = wettopupService.home_daily_mix_topup_bonus(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_product_list);
    		 
			 Iterator d_topup_bonus_itr = d_topup_bonus.iterator();
			
			while(d_topup_bonus_itr.hasNext()) {
				
				Double d_t_bonus = (Double) d_topup_bonus_itr.next();
				
				if(d_t_bonus == null || d_t_bonus == 0.0) {
					d_t_bonus = 0.0;
				}
				
				 report_total.put("topupbonus", d_t_bonus);
			}
             
             
             List<Wettopup> freebonususerid = wettopupService.freebonus_home(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1());
 			
 			Iterator freebonususerid_itr = freebonususerid.iterator();
 			
 			while(freebonususerid_itr.hasNext()) {
 				 Object[] obj = (Object[]) freebonususerid_itr.next();
 				 
 				/*
 				 Date fromdate = (Date) obj[2];
 				 Calendar c = Calendar.getInstance(); 
 					c.setTime(fromdate); 
 					c.add(Calendar.DATE, 1);
 					Date todate = c.getTime();*/
 				 
 				List<Wettopup> wet_topup_excludefree = wettopupService.topupfornonactiveexcludefree(String.valueOf(obj[0]), String.valueOf(obj[1]), newplayerreg.getCompanyid1(),newplayerreg.getDateOfissue(),newplayerreg.getTodate());
 				
 				if(wet_topup_excludefree.isEmpty()) {
                     i = i +1;
                     
 				}
             
             
             
 			}
             
 			report_total.put("nonactiveplayer", i);
             
             
             
             
          /*   List<WemPlayer> nonactiveplayer = wemplayerService.homenonactiveplayer(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_product_list);
 			
             Iterator nonactiveplayer_itr = nonactiveplayer.iterator();
             
             while(nonactiveplayer_itr.hasNext()) {
            	 Integer obj1 = (Integer) nonactiveplayer_itr.next();
            	 report_total.put("nonactiveplayer", obj1);
             }*/
          
             List<Wettopup> unclaimtotal = wettopupService.homependingtopup(newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate());
 			
 			Iterator unclaimtotal_itr = unclaimtotal.iterator();
 			
 			while(unclaimtotal_itr.hasNext()) {
 				
 				Double obj1 = (Double) unclaimtotal_itr.next();
 				
 				if(obj1 == null || obj1 == 0.0) {
 					obj1 = 0.0;
 				}
 				
 				report_total.put("pendingtopup", obj1);
 			}
			
 			
 			
			List<Wetwithdraw> unclaimtotal1 = wetwithdrawService.outside_report_unclaim(newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate());
			
			Iterator unclaimtotal1_itr = unclaimtotal1.iterator();
			
			while(unclaimtotal1_itr.hasNext()) {
				
				Double obj1 = (Double) unclaimtotal1_itr.next();
				
				if(obj1 == null || obj1 == 0.0) {
					obj1 = 0.0;
				}
				
				report_total.put("pendingwithdraw", obj1);
			}
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "home report data");
	    response.put("data", report_total);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("home-total-transferout")
	public ResponseEntity<Object> transferout(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> report_total = new HashMap<>();
		int i = 0;
		Double topup_trancid = 0.0;
		
		
		   
		
		if(newplayerreg.getCompanyid1() != null) {
			
			
			/*if(newplayerreg.getDateOfissue() != null && newplayerreg.getTodate() != null) {
				 Date fromdate = newplayerreg.getDateOfissue();
				 Date toadte = newplayerreg.getDateOfissue();
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
				    newplayerreg.setDateOfissue(new Date(sdf.format(fromdate)));
				    newplayerreg.setTodate(new Date(sdf.format(fromdate)));
			}*/
		
			
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
			
		
			
			Double home_transferout = wettpService.daily_mix_wettp_amount(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_product_list);
			
			if(home_transferout == null || home_transferout == 0.0) {
				home_transferout = 0.0;
			}
			
			report_total.put("transferout", home_transferout);
			
			
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "home report data");
	    response.put("data", report_total);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("home-total-topup")
	public ResponseEntity<Object> toupup(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> report_total = new HashMap<>();
		int i = 0;
		Double topup_trancid = 0.0;
		
		
		   
		
		if(newplayerreg.getCompanyid1() != null) {
			
			
			/*if(newplayerreg.getDateOfissue() != null && newplayerreg.getTodate() != null) {
				 Date fromdate = newplayerreg.getDateOfissue();
				 Date toadte = newplayerreg.getDateOfissue();
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
				    newplayerreg.setDateOfissue(new Date(sdf.format(fromdate)));
				    newplayerreg.setTodate(new Date(sdf.format(fromdate)));
			}*/
		
			
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
			
		
			
			List<Wettopup> home_topup = wettopupService.home_daily_mix_topup_amount(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_product_list);
			
            Iterator home_topup_itr = home_topup.iterator();
			
			while(home_topup_itr.hasNext()) {
				
				Double d_t_amount = (Double) home_topup_itr.next();
				
				if(d_t_amount == null || d_t_amount == 0.0) {
					d_t_amount = 0.0;
				}
				
				report_total.put("topup", d_t_amount);
			}
			
			
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "home report data");
	    response.put("data", report_total);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("home-total-trancid")
	public ResponseEntity<Object> toupuptrancid(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> report_total = new HashMap<>();
		int i = 0;
		Double topup_trancid = 0.0;
		
		
		   
		
		if(newplayerreg.getCompanyid1() != null) {
			
			
			/*if(newplayerreg.getDateOfissue() != null && newplayerreg.getTodate() != null) {
				 Date fromdate = newplayerreg.getDateOfissue();
				 Date toadte = newplayerreg.getDateOfissue();
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
				    newplayerreg.setDateOfissue(new Date(sdf.format(fromdate)));
				    newplayerreg.setTodate(new Date(sdf.format(fromdate)));
			}*/
		
			
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
			
		
			
	List<Wettopup> home_topup_trancid = wettopupService.home_daily_mix_topup_trancid(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_product_list);
			
			
			for(Object home_topup_trancid_itr :home_topup_trancid) {
	        	
	        	System.out.println(((Integer)home_topup_trancid_itr));
	        	
	        	topup_trancid = new Double(home_topup_trancid_itr.toString());
	        	report_total.put("topuptrancid", topup_trancid);
	        	
	        }
			
			
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "home report data");
	    response.put("data", report_total);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("home-withdraw")
	public ResponseEntity<Object> withdraw(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> report_total = new HashMap<>();
		int i = 0;
		Double topup_trancid = 0.0;
		
		
		   
		
		if(newplayerreg.getCompanyid1() != null) {
			
			
			/*if(newplayerreg.getDateOfissue() != null && newplayerreg.getTodate() != null) {
				 Date fromdate = newplayerreg.getDateOfissue();
				 Date toadte = newplayerreg.getDateOfissue();
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
				    newplayerreg.setDateOfissue(new Date(sdf.format(fromdate)));
				    newplayerreg.setTodate(new Date(sdf.format(fromdate)));
			}*/
		
			
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
			
          Double  home_withdraw = wetwithdrawService.daily_mix_withdraw_amount(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_product_list);
			
			if(home_withdraw == null || home_withdraw == 0.0) {
				home_withdraw = 0.0;
			}
			
			report_total.put("withdraw", home_withdraw);
			
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "home report data");
	    response.put("data", report_total);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("home-withdrawtrancid")
	public ResponseEntity<Object> withdrawtrancid(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> report_total = new HashMap<>();
		int i = 0;
		Double topup_trancid = 0.0;
		
		
		   
		
		if(newplayerreg.getCompanyid1() != null) {
			
			
			/*if(newplayerreg.getDateOfissue() != null && newplayerreg.getTodate() != null) {
				 Date fromdate = newplayerreg.getDateOfissue();
				 Date toadte = newplayerreg.getDateOfissue();
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
				    newplayerreg.setDateOfissue(new Date(sdf.format(fromdate)));
				    newplayerreg.setTodate(new Date(sdf.format(fromdate)));
			}*/
		
			
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
			
          Double  home_withdraw = wetwithdrawService.daily_mix_withdraw_amount(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_product_list);
			
			if(home_withdraw == null || home_withdraw == 0.0) {
				home_withdraw = 0.0;
			}
			
			report_total.put("withdraw", home_withdraw);
			
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "home report data");
	    response.put("data", report_total);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	
	@PostMapping("Oth-income")
	public ResponseEntity<Object> otherincome(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> report_total = new HashMap<>();
		int i = 0;
		Double topup_trancid = 0.0;
		
		if(newplayerreg.getCompanyid1() != null) {
			
		
			List<OthIncome> home_othincome = othincomeService.home_othincome(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate()); 
      
			
            Iterator othincomeamount_itr = home_othincome.iterator();
			
			while(othincomeamount_itr.hasNext()) {
				
				BigDecimal obj1 = (BigDecimal) othincomeamount_itr.next();
				
				if(obj1 != null) {
				
				Double  obj2 = obj1.doubleValue();
				
				
				if(obj2 == null || obj2 == 0.0) {
					obj2 = 0.0;
				}
				
				report_total.put("homeothincome", obj2);
			
			}else {
				report_total.put("homeothincome", 0);
			}
			
			
			}
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "home othincome data");
	    response.put("data", report_total);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("home-transferin")
	public ResponseEntity<Object> hometransferin(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> report_total = new HashMap<>();
		int i = 0;
		Double topup_trancid = 0.0;
		
		
		if(newplayerreg.getCompanyid1() != null) {
			
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
			
          Double home_transferin = wettpService.daily_mix_wettp_transferin_amount(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_product_list);
			
			if(home_transferin == null || home_transferin == 0.0) {
				home_transferin = 0.0;
			}
			
			report_total.put("transferin", home_transferin);
			
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "home report data");
	    response.put("data", report_total);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("left-nav")
	public ResponseEntity<Object> leftnav(@RequestBody  Newplayerregbean newplayerreg){
		
		
		
		return null;
	}
	
	
}
