package com.example.bank.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import com.example.bank.model.CmcProduct;
import com.example.bank.model.Wettopup;
import com.example.bank.service.CmcproductService;
import com.example.bank.service.ReferalbonusService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WettpService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class ProductdailyreportController {
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;
	
	@Autowired
	WettpService wettpService;
	
	@Autowired
	CmcproductService cmcproductService;
	
	@Autowired
	ReferalbonusService referalbonusService;
	
	@PostMapping("productdaily")
	public ResponseEntity<Object> productdaily(@RequestBody  Newplayerregbean newplayerreg){
		
		List<Wettopup> wettopup = new ArrayList<Wettopup>();
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Date   date = newplayerreg.getDateOfissue();
		
		Date dt =newplayerreg.getDateOfissue();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, -1);
		dt = c.getTime();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		String dateString = format.format(newplayerreg.getDateOfissue());
		String dateString1 = format.format(dt);
		try {
			  date  = format.parse (dateString);
			  dt = format.parse(dateString1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		
		if(newplayerreg.getCompanyid1() != null) {
			
			List<CmcProduct> cmcproduct = cmcproductService.getallproductbycompany(newplayerreg.getCompanyid1());
			
			for(CmcProduct cmcproduct_itr: cmcproduct) {
				
				Map<String, Object> temp_member_account = new HashMap<>();
				
				
               Double topup_yest = wettopupService.productdaily_topup(dt, newplayerreg.getCompanyid1(), cmcproduct_itr.getFldesc());
				
				Double withdraw_yest = wetwithdrawService.productdaily_withdraw(dt, newplayerreg.getCompanyid1(), cmcproduct_itr.getFldesc());
				
				Double bonus_yest = wettopupService.productdaily_bonus(dt, newplayerreg.getCompanyid1(), cmcproduct_itr.getFldesc());
				
				Double transferin_yest = wettpService.productdaily_in(dt, newplayerreg.getCompanyid1(), cmcproduct_itr.getFldesc());
				
				Double transferout_yest = wettpService.productdaily_out(dt, newplayerreg.getCompanyid1(), cmcproduct_itr.getFldesc());
				
				Double masterpoint_yest = wettopupService.productdaily_master(dt, newplayerreg.getCompanyid1(), cmcproduct_itr.getFldesc());
				
				Double referalbonus_yest = referalbonusService.productdaily_referal(dt, newplayerreg.getCompanyid1(), cmcproduct_itr.getFldesc());
				
				Double adjustment_yest = wettopupService.productdaily_adjustment(date, newplayerreg.getCompanyid1(), cmcproduct_itr.getFldesc());
				
				
				
				if(topup_yest == null || topup_yest == 0.0) {
					topup_yest = 0.0;
				}
				
				if(bonus_yest == null || bonus_yest == 0.0) {
					bonus_yest = 0.0;
				}
				
				
				if(adjustment_yest == null || adjustment_yest == 0.0) {
					adjustment_yest = 0.0;
				}
				
				
				if(withdraw_yest == null || withdraw_yest == 0.0) {
					withdraw_yest = 0.0;
				}
				
				if(transferin_yest == null || transferin_yest == 0.0) {
					transferin_yest = 0.0;
				}
				
				if(transferout_yest == null || transferout_yest == 0.0) {
					transferout_yest = 0.0;
				}
				

				if(masterpoint_yest == null || masterpoint_yest == 0.0) {
					masterpoint_yest = 0.0;
				}

				if(referalbonus_yest == null || referalbonus_yest == 0.0) {
					referalbonus_yest = 0.0;
				}
				
				
				
				
				
				Double topup = wettopupService.productdaily_topup(date, newplayerreg.getCompanyid1(), cmcproduct_itr.getFldesc());
				
				Double withdraw = wetwithdrawService.productdaily_withdraw(date, newplayerreg.getCompanyid1(), cmcproduct_itr.getFldesc());
				
				Double bonus = wettopupService.productdaily_bonus(date, newplayerreg.getCompanyid1(), cmcproduct_itr.getFldesc());
				
				Double transferin = wettpService.productdaily_in(date, newplayerreg.getCompanyid1(), cmcproduct_itr.getFldesc());
				
				Double transferout = wettpService.productdaily_out(date, newplayerreg.getCompanyid1(), cmcproduct_itr.getFldesc());
				
				Double masterpoint = wettopupService.productdaily_master(date, newplayerreg.getCompanyid1(), cmcproduct_itr.getFldesc());
				
				Double referalbonus = referalbonusService.productdaily_referal(date, newplayerreg.getCompanyid1(), cmcproduct_itr.getFldesc());
				
				Double adjustment = wettopupService.productdaily_adjustment(date, newplayerreg.getCompanyid1(), cmcproduct_itr.getFldesc());
				
				
				
				
			 	if(topup == null || topup == 0.0) {
					topup = 0.0;
				}
				
				if(bonus == null || bonus == 0.0) {
					bonus = 0.0;
				}
				
				
				if(adjustment == null || adjustment == 0.0) {
					adjustment = 0.0;
				}
				
				
				if(withdraw == null || withdraw == 0.0) {
					withdraw = 0.0;
				}
				
				if(transferin == null || transferin == 0.0) {
					transferin = 0.0;
				}
				
				if(transferout == null || transferout == 0.0) {
					transferout = 0.0;
				}
				

				if(masterpoint == null || masterpoint == 0.0) {
					masterpoint = 0.0;
				}

				if(referalbonus == null || referalbonus == 0.0) {
					referalbonus = 0.0;
				}
				
				temp_member_account.put("product", cmcproduct_itr.getFldesc());
				temp_member_account.put("openning", 0.00);
				temp_member_account.put("in", topup);
				temp_member_account.put("out", withdraw);
				temp_member_account.put("bonus", bonus);
				temp_member_account.put("point", transferin - transferout);
				temp_member_account.put("masterpoint", masterpoint);
				temp_member_account.put("referalbonus", referalbonus);
				temp_member_account.put("wl", topup - withdraw);
				
			
				temp_member_account.put("closing", cmcproduct_itr.getClosing());;
				
			result_list.add(temp_member_account);
				
				
				
			}
			
			 Map<String,Object> report_total = new HashMap<>();
			 
			 

		       Double topup_total_yest = wettopupService.productdaily_topup_total(dt, newplayerreg.getCompanyid1());
		       
		       Double withdraw_total_yest = wetwithdrawService.productdaily_withdraw_total(dt, newplayerreg.getCompanyid1());
		       
		       Double bonus_total_yest = wettopupService.productdaily_bonus_total(dt, newplayerreg.getCompanyid1());
		       
		       Double transferin_total_yest =  wettpService.productdaily_in_total(dt, newplayerreg.getCompanyid1());
		       
		       Double transferout_total_yest = wettpService.productdaily_out_total(dt, newplayerreg.getCompanyid1());
		       
		       Double master_total_yest = wettopupService.productdaily_master_total(dt, newplayerreg.getCompanyid1());
		       
		       Double referalbonus_total_yest = referalbonusService.productdaily_referal_total(dt, newplayerreg.getCompanyid1());
		       
		       Double adjustment_total_yest = wettopupService.productdaily_adjustment_total(dt, newplayerreg.getCompanyid1());
		    		   
		   	if(topup_total_yest == null || topup_total_yest == 0.0) {
		   		topup_total_yest = 0.0;
			}
			
			if(bonus_total_yest == null || bonus_total_yest == 0.0) {
				bonus_total_yest = 0.0;
			}
			
			
			if(adjustment_total_yest == null || adjustment_total_yest == 0.0) {
				adjustment_total_yest = 0.0;
			}
			
			
			if(withdraw_total_yest == null || withdraw_total_yest == 0.0) {
				withdraw_total_yest = 0.0;
			}
			
			if(transferin_total_yest == null || transferin_total_yest == 0.0) {
				transferin_total_yest = 0.0;
			}
			
			if(transferout_total_yest == null || transferout_total_yest == 0.0) {
				transferout_total_yest = 0.0;
			}
			

			if(master_total_yest == null || master_total_yest == 0.0) {
				master_total_yest = 0.0;
			}

			if(referalbonus_total_yest == null || referalbonus_total_yest == 0.0) {
				referalbonus_total_yest = 0.0;
			}

			 
			 

	       Double topup_total = wettopupService.productdaily_topup_total(date, newplayerreg.getCompanyid1());
	       
	       Double withdraw_total = wetwithdrawService.productdaily_withdraw_total(date, newplayerreg.getCompanyid1());
	       
	       Double bonus_total = wettopupService.productdaily_bonus_total(date, newplayerreg.getCompanyid1());
	       
	       Double transferin_total =  wettpService.productdaily_in_total(date, newplayerreg.getCompanyid1());
	       
	       Double transferout_total = wettpService.productdaily_out_total(date, newplayerreg.getCompanyid1());
	       
	       Double master_total = wettopupService.productdaily_master_total(date, newplayerreg.getCompanyid1());
	       
	       Double referalbonus_total = referalbonusService.productdaily_referal_total(date, newplayerreg.getCompanyid1());
	       
	       Double adjustment_total = wettopupService.productdaily_adjustment_total(date, newplayerreg.getCompanyid1());
	    		   
	   	if(topup_total == null || topup_total == 0.0) {
	   		topup_total = 0.0;
		}
		
		if(bonus_total == null || bonus_total == 0.0) {
			bonus_total = 0.0;
		}
		
		
		if(adjustment_total == null || adjustment_total == 0.0) {
			adjustment_total = 0.0;
		}
		
		
		if(withdraw_total == null || withdraw_total == 0.0) {
			withdraw_total = 0.0;
		}
		
		if(transferin_total == null || transferin_total == 0.0) {
			transferin_total = 0.0;
		}
		
		if(transferout_total == null || transferout_total == 0.0) {
			transferout_total = 0.0;
		}
		

		if(master_total == null || master_total == 0.0) {
			master_total = 0.0;
		}

		if(referalbonus_total == null || referalbonus_total == 0.0) {
			referalbonus_total = 0.0;
		}
		
		report_total.put("product", "Report Total:");
		report_total.put("openning","" );
		report_total.put("in", topup_total);
		report_total.put("out", withdraw_total);
		report_total.put("bonus", bonus_total);
		report_total.put("point", transferin_total - transferout_total);
		report_total.put("masterpoint", master_total);
		report_total.put("referalbonus", referalbonus_total);
		report_total.put("wl", topup_total - withdraw_total);
		
		Double reportclosing = cmcproductService.getreportclosing(newplayerreg.getCompanyid1());
		
		if(reportclosing == null || reportclosing == 0.0) {
			reportclosing = 0.0;
		}
		
		report_total.put("closing", reportclosing);
		
	result_list.add(report_total);
		
			
		}
		response.put("code", HttpStatus.OK);
	    response.put("msg", "product cash flow data");
	    response.put("data", result_list);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		}

}
