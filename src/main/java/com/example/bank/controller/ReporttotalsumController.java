package com.example.bank.controller;

import java.math.BigDecimal;
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

import com.example.bank.bean.Newplayerregbean;
import com.example.bank.model.CmcIncome;
import com.example.bank.model.CmcIncomedet;
import com.example.bank.model.CmcProduct;
import com.example.bank.model.Cmccombank;
import com.example.bank.model.Cmcexpenses;
import com.example.bank.model.Cmcexpensesdet;
import com.example.bank.model.Expensestype;
import com.example.bank.model.OthIncome;
import com.example.bank.model.ReferalBonus;
import com.example.bank.model.Wettopup;
import com.example.bank.model.Wettp;
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
import com.example.bank.service.WettopupService;
import com.example.bank.service.WettpService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class ReporttotalsumController {
	
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
	
	@PostMapping("unclaim-total")
	public ResponseEntity<Object> unclaimtotal(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> report_total = new HashMap<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
			 String status = newplayerreg.getStatus().get(0);
			 
			 List<String> status_list = new ArrayList<>();
			
			 if(status == null) {
				 
				   status_list.add("Transit");
					status_list.add("Cancel");
					status_list.add("Posted");
				 
			 }else {
				 status_list = newplayerreg.getStatus();
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
			
                String banklist = newplayerreg.getBank().get(0);
			
			List<String> temp_bank_list = new ArrayList<>();
			
			if(banklist == null) {
				
					List<Cmccombank> banklist_all = cmccombankService.getallbankbycompany(newplayerreg.getCompanyid1());
					for(Cmccombank banklist_itr: banklist_all) {
						temp_bank_list.add(banklist_itr.getFldesc());
					}
				}else {
					temp_bank_list = newplayerreg.getBank();
				}
			
			List<Wettopup> unclaimtotal = wettopupService.outside_report_unclaim(temp_bank_list, newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate());
			
			Iterator unclaimtotal_itr = unclaimtotal.iterator();
			
			while(unclaimtotal_itr.hasNext()) {
				
				Double obj1 = (Double) unclaimtotal_itr.next();
				
				if(obj1 == null || obj1 == 0.0) {
					obj1 = 0.0;
				}
				
				report_total.put("unclaim", obj1);
			}
			
			List<Wettopup> claimtotal = wettopupService.outside_report_claim(temp_bank_list, newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate(),status_list);
			
                Iterator claimtotal_itr = claimtotal.iterator();
			
			while(claimtotal_itr.hasNext()) {
				
				Double obj2 = (Double) claimtotal_itr.next();
				
				if(obj2 == null || obj2 == 0.0) {
					obj2 = 0.0;
				}
				
				report_total.put("claim", obj2);
			}
			
			
			List<Wettopup> claimtotal_bonus = wettopupService.outside_report_claim_bonus(temp_bank_list, newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate(),status_list);
			
            Iterator claimtotal_bonus_itr = claimtotal_bonus.iterator();
		
		while(claimtotal_bonus_itr.hasNext()) {
			
			Double obj2 = (Double) claimtotal_bonus_itr.next();
			
			if(obj2 == null || obj2 == 0.0) {
				obj2 = 0.0;
			}
			
			report_total.put("claim_bonus", obj2);
		}
			
			
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "player top up report total data");
	    response.put("data", report_total);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	
	@PostMapping("withdraw-total")
	public ResponseEntity<Object> withdrawtotal(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> report_total = new HashMap<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
			 String status = newplayerreg.getStatus().get(0);
			 
			 List<String> status_list = new ArrayList<>();
			
			 if(status == null) {
				 
				   status_list.add("Transit");
					status_list.add("Cancel");
					status_list.add("Posted");
				 
			 }else {
				 status_list = newplayerreg.getStatus();
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
			
            
			
			List<Wetwithdraw> unclaimtotal = wetwithdrawService.outside_report_unclaim(newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate());
			
			Iterator unclaimtotal_itr = unclaimtotal.iterator();
			
			while(unclaimtotal_itr.hasNext()) {
				
				Double obj1 = (Double) unclaimtotal_itr.next();
				
				if(obj1 == null || obj1 == 0.0) {
					obj1 = 0.0;
				}
				
				report_total.put("unclaim", obj1);
			}
			
			List<Wetwithdraw> claimtotal = wetwithdrawService.outside_report_claim(newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate(),status_list);
			
                Iterator claimtotal_itr = claimtotal.iterator();
			
			while(claimtotal_itr.hasNext()) {
				
				Double obj2 = (Double) claimtotal_itr.next();
				
				if(obj2 == null || obj2 == 0.0) {
					obj2 = 0.0;
				}
				
				report_total.put("claim", obj2);
			}
			
			
    List<Wetwithdraw> unclaimtotal_gst = wetwithdrawService.outside_report_unclaim_gst(newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate());
			
			Iterator unclaimtotal_gst_itr = unclaimtotal_gst.iterator();
			
			while(unclaimtotal_gst_itr.hasNext()) {
				
				Double obj1 = (Double) unclaimtotal_gst_itr.next();
				
				if(obj1 == null || obj1 == 0.0) {
					obj1 = 0.0;
				}
				
				report_total.put("unclaim_gst", obj1);
			}
			
			List<Wetwithdraw> claimtotal_gst = wetwithdrawService.outside_report_claim_gst(newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate(),status_list);
			
                Iterator claimtotal_gst_itr = claimtotal_gst.iterator();
			
			while(claimtotal_gst_itr.hasNext()) {
				
				Double obj2 = (Double) claimtotal_gst_itr.next();
				
				if(obj2 == null || obj2 == 0.0) {
					obj2 = 0.0;
				}
				
				report_total.put("claim_gst", obj2);
			}
			
			
			
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Withdraw report total data");
	    response.put("data", report_total);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("tp-total")
	public ResponseEntity<Object> tptotal(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> report_total = new HashMap<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
			 String status = newplayerreg.getStatus().get(0);
			 
			 List<String> status_list = new ArrayList<>();
			
			 if(status == null) {
				 
				   status_list.add("Transfer Point");
					status_list.add("Split Point");
				 
			 }else {
				 status_list = newplayerreg.getStatus();
			 }
			
			
        String frmproductlist = newplayerreg.getFrmproduct().get(0);
			
			List<String> temp_frmproduct_list = new ArrayList<>();
			
			if(frmproductlist == null) {
				
					List<CmcProduct> productlist_all = cmcproductService.getallproductbycompany(newplayerreg.getCompanyid1());
					for(CmcProduct productlist_itr: productlist_all) {
						temp_frmproduct_list.add(productlist_itr.getFldesc());
					}
				}else {
					temp_frmproduct_list = newplayerreg.getFrmproduct();
				}
			
			  String toproductlist = newplayerreg.getToproduct().get(0);
				
				List<String> temp_toproduct_list = new ArrayList<>();
				
				if(toproductlist == null) {
					
						List<CmcProduct> productlist_all = cmcproductService.getallproductbycompany(newplayerreg.getCompanyid1());
						for(CmcProduct productlist_itr: productlist_all) {
							temp_toproduct_list.add(productlist_itr.getFldesc());
						}
					}else {
						temp_toproduct_list = newplayerreg.getToproduct();
					}
			
			List<Wettp> unclaimtotal = wettpService.outside_report_unclaim(newplayerreg.getCompanyid1(), temp_frmproduct_list, temp_toproduct_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate());
			
			Iterator unclaimtotal_itr = unclaimtotal.iterator();
			
			while(unclaimtotal_itr.hasNext()) {
				
				Double obj1 = (Double) unclaimtotal_itr.next();
				
				if(obj1 == null || obj1 == 0.0) {
					obj1 = 0.0;
				}
				
				report_total.put("unclaim", obj1);
			}
			
			List<Wettp> claimtotal = wettpService.outside_report_claim(newplayerreg.getCompanyid1(), temp_frmproduct_list, temp_toproduct_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate(),status_list);
			
                Iterator claimtotal_itr = claimtotal.iterator();
			
			while(claimtotal_itr.hasNext()) {
				
				Double obj2 = (Double) claimtotal_itr.next();
				
				if(obj2 == null || obj2 == 0.0) {
					obj2 = 0.0;
				}
				
				report_total.put("claim", obj2);
			}
			
			
			
			
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Transfer Point report total data");
	    response.put("data", report_total);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("madj-birth-total")
	public ResponseEntity<Object> madjbirthtotal(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> report_total = new HashMap<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
			 String status = newplayerreg.getStatus().get(0);
			 
			 List<String> status_list = new ArrayList<>();
			
			 if(status == null) {
				 
				   status_list.add("Transit");
					status_list.add("Cancel");
					status_list.add("Posted");
				 
			 }else {
				 status_list = newplayerreg.getStatus();
			 }
			 
			 
           String adjustmenttype = newplayerreg.getAdjustmenttype().get(0);
			 
			 List<String> adjustmenttype_list = new ArrayList<>();
			
			 
				 adjustmenttype_list.add("User");
				 
			
			 
			 
              String adjustmentcategory = newplayerreg.getAdjustmentcategory().get(0);
			 
			 List<String> adjustmentcategory_list = new ArrayList<>();
			
		
				 adjustmentcategory_list.add("Birthday");
			
			
			
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
			
             
			
			
			List<Wettopup> unclaimtotal = wettopupService.outside_report_madj_main_pending(newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate(),adjustmenttype_list,adjustmentcategory_list);
			
			Iterator unclaimtotal_itr = unclaimtotal.iterator();
			
			while(unclaimtotal_itr.hasNext()) {
				
				Double obj1 = (Double) unclaimtotal_itr.next();
				
				if(obj1 == null || obj1 == 0.0) {
					obj1 = 0.0;
				}
				
				report_total.put("unclaim", obj1);
			}
			
			List<Wettopup> claimtotal = wettopupService.outside_report_madj_main_done(newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate(),adjustmenttype_list,adjustmentcategory_list,status_list);
			
                Iterator claimtotal_itr = claimtotal.iterator();
			
			while(claimtotal_itr.hasNext()) {
				
				Double obj2 = (Double) claimtotal_itr.next();
				
				if(obj2 == null || obj2 == 0.0) {
					obj2 = 0.0;
				}
				
				report_total.put("claim", obj2);
			}
			
			
			
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "madj main total data");
	    response.put("data", report_total);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("madj-main-total")
	public ResponseEntity<Object> madjmaintotal(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> report_total = new HashMap<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
			 String status = newplayerreg.getStatus().get(0);
			 
			 List<String> status_list = new ArrayList<>();
			
			 if(status == null) {
				 
				   status_list.add("Transit");
					status_list.add("Cancel");
					status_list.add("Posted");
				 
			 }else {
				 status_list = newplayerreg.getStatus();
			 }
			 
			 
           String adjustmenttype = newplayerreg.getAdjustmenttype().get(0);
			 
			 List<String> adjustmenttype_list = new ArrayList<>();
			
			 if(adjustmenttype == null) {
				 
				 adjustmenttype_list.add("User");
				 adjustmenttype_list.add("Master");
				 
				 
			 }else {
				 adjustmenttype_list = newplayerreg.getAdjustmenttype();
			 }
			 
			 
              String adjustmentcategory = newplayerreg.getAdjustmentcategory().get(0);
			 
			 List<String> adjustmentcategory_list = new ArrayList<>();
			
			 if(adjustmentcategory == null) {
				 
				 adjustmentcategory_list.add("Manual");
				 adjustmentcategory_list.add("Birthday");
				 adjustmentcategory_list.add("Late");
				 adjustmentcategory_list.add("Rescue");
				 adjustmentcategory_list.add("Day Day Bonus");
				 adjustmentcategory_list.add("Registered");
				 adjustmentcategory_list.add("First Top Up");
				 adjustmentcategory_list.add("WeChat 5");
				 adjustmentcategory_list.add("Line 5");
				 adjustmentcategory_list.add("Weekly Lucky Draw");
				 adjustmentcategory_list.add("Angpau");
				 adjustmentcategory_list.add("Grand Angpau");
				 adjustmentcategory_list.add("WeChat Deposit Attendance");
				 adjustmentcategory_list.add("Facebook Share");
				 adjustmentcategory_list.add("Telegram Deposit Attendance");
				 adjustmentcategory_list.add("WeChat Post");
				 
			 }else {
				 adjustmentcategory_list = newplayerreg.getAdjustmentcategory();
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
			
             
			
			
			List<Wettopup> unclaimtotal = wettopupService.outside_report_madj_main_pending(newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate(),adjustmenttype_list,adjustmentcategory_list);
			
			Iterator unclaimtotal_itr = unclaimtotal.iterator();
			
			while(unclaimtotal_itr.hasNext()) {
				
				Double obj1 = (Double) unclaimtotal_itr.next();
				
				if(obj1 == null || obj1 == 0.0) {
					obj1 = 0.0;
				}
				
				report_total.put("unclaim", obj1);
			}
			
			List<Wettopup> claimtotal = wettopupService.outside_report_madj_main_done(newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate(),status_list,adjustmenttype_list,adjustmentcategory_list);
			
                Iterator claimtotal_itr = claimtotal.iterator();
			
			while(claimtotal_itr.hasNext()) {
				
				Double obj2 = (Double) claimtotal_itr.next();
				
				if(obj2 == null || obj2 == 0.0) {
					obj2 = 0.0;
				}
				
				report_total.put("claim", obj2);
			}
			
			
			
			
			
			
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "madj main total data");
	    response.put("data", report_total);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("madj-day-total")
	public ResponseEntity<Object> madjdaytotal(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> report_total = new HashMap<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
			 String status = newplayerreg.getStatus().get(0);
			 
			 List<String> status_list = new ArrayList<>();
			
			 if(status == null) {
				 
				   status_list.add("Transit");
					status_list.add("Cancel");
					status_list.add("Posted");
				 
			 }else {
				 status_list = newplayerreg.getStatus();
			 }
			 
			 
           String adjustmenttype = newplayerreg.getAdjustmenttype().get(0);
			 
			 List<String> adjustmenttype_list = new ArrayList<>();
			
				 
				 adjustmenttype_list.add("User");
				 
				 
			
			 
			 
              String adjustmentcategory = newplayerreg.getAdjustmentcategory().get(0);
			 
			 List<String> adjustmentcategory_list = new ArrayList<>();
			
			
				 
				 adjustmentcategory_list.add("Day Day Bonus");
				 
			
			
			
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
			
             
			
			
			List<Wettopup> unclaimtotal = wettopupService.outside_report_madj_main_pending(newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate(),adjustmenttype_list,adjustmentcategory_list);
			
			Iterator unclaimtotal_itr = unclaimtotal.iterator();
			
			while(unclaimtotal_itr.hasNext()) {
				
				Double obj1 = (Double) unclaimtotal_itr.next();
				
				if(obj1 == null || obj1 == 0.0) {
					obj1 = 0.0;
				}
				
				report_total.put("unclaim", obj1);
			}
			
			List<Wettopup> claimtotal = wettopupService.outside_report_madj_main_done(newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate(),status_list,adjustmenttype_list,adjustmentcategory_list);
			
                Iterator claimtotal_itr = claimtotal.iterator();
			
			while(claimtotal_itr.hasNext()) {
				
				Double obj2 = (Double) claimtotal_itr.next();
				
				if(obj2 == null || obj2 == 0.0) {
					obj2 = 0.0;
				}
				
				report_total.put("claim", obj2);
			}
			
			
			
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "madj main total data");
	    response.put("data", report_total);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("madj-late-total")
	public ResponseEntity<Object> madjlatetotal(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> report_total = new HashMap<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
			 String status = newplayerreg.getStatus().get(0);
			 
			 List<String> status_list = new ArrayList<>();
			
			 if(status == null) {
				 
				   status_list.add("Transit");
					status_list.add("Cancel");
					status_list.add("Posted");
				 
			 }else {
				 status_list = newplayerreg.getStatus();
			 }
			 
			 
           String adjustmenttype = newplayerreg.getAdjustmenttype().get(0);
			 
			 List<String> adjustmenttype_list = new ArrayList<>();
			
		
				 
				 adjustmenttype_list.add("User");
				 
				
			 
			 
              String adjustmentcategory = newplayerreg.getAdjustmentcategory().get(0);
			 
			 List<String> adjustmentcategory_list = new ArrayList<>();
			
			
				 
				 adjustmentcategory_list.add("Late");
				 
			
			
			
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
			
             
			
			
			List<Wettopup> unclaimtotal = wettopupService.outside_report_madj_main_pending(newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate(),adjustmenttype_list,adjustmentcategory_list);
			
			Iterator unclaimtotal_itr = unclaimtotal.iterator();
			
			while(unclaimtotal_itr.hasNext()) {
				
				Double obj1 = (Double) unclaimtotal_itr.next();
				
				if(obj1 == null || obj1 == 0.0) {
					obj1 = 0.0;
				}
				
				report_total.put("unclaim", obj1);
			}
			
			List<Wettopup> claimtotal = wettopupService.outside_report_madj_main_done(newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate(),status_list,adjustmenttype_list,adjustmentcategory_list);
			
                Iterator claimtotal_itr = claimtotal.iterator();
			
			while(claimtotal_itr.hasNext()) {
				
				Double obj2 = (Double) claimtotal_itr.next();
				
				if(obj2 == null || obj2 == 0.0) {
					obj2 = 0.0;
				}
				
				report_total.put("claim", obj2);
			}
			
			
			
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "madj main total data");
	    response.put("data", report_total);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("oth-income-total")
	public ResponseEntity<Object> othincometotal(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> report_total = new HashMap<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
			
               String incometype = newplayerreg.getIncometype().get(0);
			
			List<String> incometype_list = new ArrayList<>();
			
			if(incometype == null) {
				
					List<CmcIncome> cmcincome_all = cmcincomeService.getallincomesbycompany(newplayerreg.getCompanyid1());
					for(CmcIncome cmcincome_itr: cmcincome_all) {
						incometype_list.add(cmcincome_itr.getFldesc());
					}
				}else {
					incometype_list = newplayerreg.getIncometype();
				}
			
			  String incomedet = newplayerreg.getIncomedet().get(0);
				
				List<String> incomedet_list = new ArrayList<>();
				
				if(incomedet == null) {
					
						List<CmcIncomedet> cmcincomedet_all = cmcincomedetService.getallincomedetbycompany(newplayerreg.getCompanyid1(), incometype_list);
						for(CmcIncomedet cmcincomedet_itr: cmcincomedet_all) {
							incomedet_list.add(cmcincomedet_itr.getFldesc());
						}
					}else {
						incomedet_list = newplayerreg.getIncomedet();
					}
			
		
                String banklist = newplayerreg.getBank().get(0);
			
			List<String> temp_bank_list = new ArrayList<>();
			
			if(banklist == null) {
				
					List<Cmccombank> banklist_all = cmccombankService.getallbankbycompany(newplayerreg.getCompanyid1());
					for(Cmccombank banklist_itr: banklist_all) {
						temp_bank_list.add(banklist_itr.getFldesc());
					}
				}else {
					temp_bank_list = newplayerreg.getBank();
				}
			
			List<OthIncome> othincomeamount = othincomeService.outside_report_othincome(temp_bank_list, newplayerreg.getCompanyid1(), incometype_list, incomedet_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate());
			
			Iterator othincomeamount_itr = othincomeamount.iterator();
			
			while(othincomeamount_itr.hasNext()) {
				
				BigDecimal obj1 = (BigDecimal) othincomeamount_itr.next();
				
				if(obj1 != null) {
				
				Double  obj2 = obj1.doubleValue();
				
				
				if(obj2 == null || obj2 == 0.0) {
					obj2 = 0.0;
				}
				
				report_total.put("unclaim", obj2);
			
			}else {
				report_total.put("unclaim", 0);
			}
			}
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Othincome total report data");
	    response.put("data", report_total);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("referal-total")
	public ResponseEntity<Object> referaltotal(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> report_total = new HashMap<>();
		
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
			
			List<ReferalBonus> referalamount = referalbonusService.outside_report_referal_pending(newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate());
			
			Iterator referalamount_itr = referalamount.iterator();
			
			while(referalamount_itr.hasNext()) {
				
				BigDecimal obj1 = (BigDecimal) referalamount_itr.next();
				
				if(obj1 != null) {
				
				Double  obj2 = obj1.doubleValue();
				
				
				if(obj2 == null || obj2 == 0.0) {
					obj2 = 0.0;
				}
				
				report_total.put("unclaim", obj2);
				}else {
					report_total.put("unclaim", 0);
				}
			}
			
       List<ReferalBonus> referaldoneamount = referalbonusService.outside_report_referal_done(newplayerreg.getCompanyid1(), temp_product_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate());
			
			Iterator referaldoneamount_itr = referaldoneamount.iterator();
			
			while(referaldoneamount_itr.hasNext()) {
				
				BigDecimal obj1 = (BigDecimal) referaldoneamount_itr.next();
				if(obj1 != null) {
				Double  obj2 = obj1.doubleValue();
				
				
				if(obj2 == null || obj2 == 0.0) {
					obj2 = 0.0;
				}
				
				report_total.put("claim", obj2);
			
			}else {
				report_total.put("claim", 0);
			}
			
			}
		}
			
		
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "referal total  report data");
	    response.put("data", report_total);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		}	
	

	
	@PostMapping("expenses-total")
	public ResponseEntity<Object> expensestotal(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> report_total = new HashMap<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
			
               String expensetype = newplayerreg.getExpensetype().get(0);
			
			List<String> expensetype_list = new ArrayList<>();
			
			if(expensetype == null) {
				
					List<Cmcexpenses> cmcexpense_all = cmcexpenseService.getallexpensesbycompany(newplayerreg.getCompanyid1());
					for(Cmcexpenses cmcexpense_itr: cmcexpense_all) {
						expensetype_list.add(cmcexpense_itr.getFldesc());
					}
				}else {
					expensetype_list = newplayerreg.getExpensetype();
				}
			
			  String expensedet = newplayerreg.getExpensedet().get(0);
				
				List<String> expensedet_list = new ArrayList<>();
				
				if(expensedet == null) {
					
						List<Cmcexpensesdet> cmcexpensedet_all = cmcexpensedetService.getallexpensesdetbycompany(newplayerreg.getCompanyid1(), expensetype_list);
						for(Cmcexpensesdet cmcexpensedet_itr: cmcexpensedet_all) {
							expensedet_list.add(cmcexpensedet_itr.getFldesc());
						}
					}else {
						expensedet_list = newplayerreg.getExpensedet();
					}
			
		
                String banklist = newplayerreg.getBank().get(0);
			
			List<String> temp_bank_list = new ArrayList<>();
			
			if(banklist == null) {
				
					List<Cmccombank> banklist_all = cmccombankService.getallbankbycompany(newplayerreg.getCompanyid1());
					for(Cmccombank banklist_itr: banklist_all) {
						temp_bank_list.add(banklist_itr.getFldesc());
					}
				}else {
					temp_bank_list = newplayerreg.getBank();
				}
			
			
			List<Expensestype>  expenseamount = expensetypeService.outside_report_expense_amount(temp_bank_list, newplayerreg.getCompanyid1(), expensetype_list, expensedet_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate());
			
                 Iterator expenseamount_itr = expenseamount.iterator();
			
			while(expenseamount_itr.hasNext()) {
				
				BigDecimal obj1 = (BigDecimal) expenseamount_itr.next();
				
				if(obj1 != null) {
				
				Double  obj2 = obj1.doubleValue();
				
				
				if(obj2 == null || obj2 == 0.0) {
					obj2 = 0.0;
				}
				
				report_total.put("expamount", obj2);
			
			}else {
				report_total.put("expamount", 0);
			}
			
			}	
				List<Expensestype>  expensegst = expensetypeService.outside_report_expense_gst(temp_bank_list, newplayerreg.getCompanyid1(), expensetype_list, expensedet_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate());
				
                Iterator expensegst_itr = expensegst.iterator();
			
			while(expensegst_itr.hasNext()) {
				
				BigDecimal obje = (BigDecimal) expensegst_itr.next();
				
				if(obje != null) {
				
				Double  obj2 = obje.doubleValue();
				
				
				if(obj2 == null || obj2 == 0.0) {
					obj2 = 0.0;
				}
				
				report_total.put("expgst", obj2);
			
			}else {
				report_total.put("expgst", 0);
			}
			
			
			}	
List<Expensestype>  pettyamount = expensetypeService.outside_report_petty_amount(temp_bank_list, newplayerreg.getCompanyid1(), expensetype_list, expensedet_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate());
				
                Iterator pettyamount_itr = pettyamount.iterator();
			
			while(pettyamount_itr.hasNext()) {
				
				BigDecimal objpa = (BigDecimal) pettyamount_itr.next();
				
				if(objpa != null) {
				
				Double  obj2 = objpa.doubleValue();
				
				
				if(obj2 == null || obj2 == 0.0) {
					obj2 = 0.0;
				}
				
				report_total.put("pettyamount", obj2);
			
			}else {
				report_total.put("pettyamount", 0);
			}
			}
				
List<Expensestype>  pettygst= expensetypeService.outside_report_petty_gst(temp_bank_list, newplayerreg.getCompanyid1(), expensetype_list, expensedet_list, newplayerreg.getDateOfissue(), newplayerreg.getTodate());
				
                Iterator pettygst_itr = pettygst.iterator();
			
			while(pettygst_itr.hasNext()) {
				
				BigDecimal objpg = (BigDecimal) pettygst_itr.next();
				
				if(objpg != null) {
				
				Double  obj2 = objpg.doubleValue();
				
				
				if(obj2 == null || obj2 == 0.0) {
					obj2 = 0.0;
				}
				
				report_total.put("pettygst", obj2);
			
			}else {
				report_total.put("pettygst", 0);
			}
			
			
			}
			}
	
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Expense total report data");
	    response.put("data", report_total);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
			
	
}
}
		
