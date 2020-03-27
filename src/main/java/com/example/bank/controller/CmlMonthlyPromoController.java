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

import com.example.bank.bean.Newplayerregbean;
import com.example.bank.model.TmpPlayer;
import com.example.bank.model.WemPromotion;
import com.example.bank.model.Wettopup;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WempromotionService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class CmlMonthlyPromoController {
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;
	
	@Autowired
	WempromotionService wempromotionService;

	@PostMapping("cml-promo")
	public ResponseEntity<Object> getcmlpromotion(@RequestBody  Newplayerregbean newplayerreg){
		
		List<TmpPlayer> tmpplayer = new ArrayList<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		
		Map<String,Object> response1 = new HashMap<>();
		Integer wemid = 0;
		List<String> userid = new ArrayList<>();
		Double promo_count = 0.0;
		Double promo_count_monthly = 0.0;
		
		if(newplayerreg.getCompanyid1() != null && newplayerreg.getUserid() != null) {
			
			List<TmpPlayer> tmpplayer_wemid = tmpplayerService.getwemplayerid(newplayerreg.getUserid(), newplayerreg.getCompanyid1());
			
			for(TmpPlayer tmpplayer_wemid_itr: tmpplayer_wemid) {
				wemid = tmpplayer_wemid_itr.getWemplayerid().getId();
			}
			
			List<TmpPlayer> profileuserid = tmpplayerService.allprofileuserid(wemid, newplayerreg.getCompanyid1());
			
			
			for(TmpPlayer profileuserid_itr: profileuserid) {
				userid.add(profileuserid_itr.getUserid());
			}
			
			List<WemPromotion>  wempromotion = wempromotionService.getwempromobycompanyid(newplayerreg.getCompanyid1());
			
			for(WemPromotion wempromo_itr : wempromotion) {
				
				Map<String, Object> response = new HashMap<>();
				
				List<Wettopup> promotioncount = wettopupService.promotioncount(wempromo_itr.getFldesc(), userid, newplayerreg.getCompanyid1());
				
				 for(Object promotioncount_itr :promotioncount) {
			        	
			        	System.out.println(((Integer)promotioncount_itr));
			        	
			        	promo_count = new Double(promotioncount_itr.toString());
			        	response.put("alltimetaken", promo_count);
			        }
				 
				 List<Wettopup> promotioncount_monthly = wettopupService.promotioncount_monthly(wempromo_itr.getFldesc(), userid, newplayerreg.getCompanyid1());
					
				 for(Object promotioncount_monthly_itr :promotioncount_monthly) {
			        	
			        	System.out.println(((Integer)promotioncount_monthly_itr));
			        	
			        	promo_count_monthly = new Double(promotioncount_monthly_itr.toString());
			        	response.put("monthlytaken", promo_count_monthly);
			        	
			        }
				 
				 response.put("promotion", wempromo_itr.getFldesc());
				
				 System.out.println(wempromo_itr.getMonthlyqty());
				 
				 if(wempromo_itr.getMonthlyqty() == null) {
					 response.put("maxclaimmonth", "Unlimited");
				 }else {
					 response.put("maxclaimmonth", wempromo_itr.getMonthlyqty());
				 }
				 
				 
				 result_list.add(response);
			}
			
			
			
			
		}
		
		    response1.put("code", HttpStatus.OK);
		    response1.put("msg", "all cml promo");
		    response1.put("data", result_list);
		    
			return new ResponseEntity<Object>(response1, HttpStatus.OK);
	}

}
