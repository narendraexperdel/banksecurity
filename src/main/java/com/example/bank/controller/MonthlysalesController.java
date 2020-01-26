package com.example.bank.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.bean.Newplayerregbean;
import com.example.bank.model.Wettopup;
import com.example.bank.model.Wetwithdraw;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class MonthlysalesController {
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	WetwithdrawService wetwthdrawService;
	
	@PostMapping("monthly-sales")
	public ResponseEntity<Object> monthlysales(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Double topups = 0.0;
		Double withdraws = 0.0;
		
		if(newplayerreg.getCompanyid1() !=null) {
		
		List<Wettopup> distinctmonth = wettopupService.distinctmonthandyear(newplayerreg.getCompanyid1());
		
		for(Object distinctmonth_itr :distinctmonth) {
			
			 Map<String,Object> temp_non_player = new HashMap<>();

			List<Wettopup> topupamount = wettopupService.monthlysalestopup(((Object[])distinctmonth_itr)[0].toString(), ((Object[])distinctmonth_itr)[1].toString(), newplayerreg.getCompanyid1());
		        System.out.println(((Object[])distinctmonth_itr)[0].toString());
		        System.out.println(((Object[])distinctmonth_itr)[1].toString());
		        temp_non_player.put("yearmonth", ((Object[])distinctmonth_itr)[1].toString()+""+((Object[])distinctmonth_itr)[0].toString());
		        for(Object topupamount_itr :topupamount) {
		        	
		        	System.out.println(((Double)topupamount_itr).toString());
		        	temp_non_player.put("topup", ((Double)topupamount_itr).toString());
		        	topups = ((Double)topupamount_itr);
		        	
		        }
		        
		        List<Wetwithdraw> withdrawamount = wetwthdrawService.monthlysalestopup(((Object[])distinctmonth_itr)[0].toString(), ((Object[])distinctmonth_itr)[1].toString(), newplayerreg.getCompanyid1());
		        
                for(Object withdrawamount_itr :withdrawamount) {
		        	
		        	System.out.println(((Double)withdrawamount_itr).toString());
		        	temp_non_player.put("withdraw", ((Double)withdrawamount_itr).toString());
		        	withdraws = ((Double)withdrawamount_itr);
		        }

  
                List<Wettopup> topupbonus = wettopupService.monthlysalesbonus(((Object[])distinctmonth_itr)[0].toString(), ((Object[])distinctmonth_itr)[1].toString(), newplayerreg.getCompanyid1());
		      
		        temp_non_player.put("yearmonth", ((Object[])distinctmonth_itr)[1].toString()+""+((Object[])distinctmonth_itr)[0].toString());
		        for(Object topupbonus_itr :topupbonus) {
		        	
		        	System.out.println(((Double)topupbonus_itr).toString());
		        	temp_non_player.put("bonus", ((Double)topupbonus_itr).toString());
		        }
		        
		        List<Wettopup> topupadjustment = wettopupService.monthlysalesadjustment(((Object[])distinctmonth_itr)[0].toString(), ((Object[])distinctmonth_itr)[1].toString(), newplayerreg.getCompanyid1());
			      
		        temp_non_player.put("yearmonth", ((Object[])distinctmonth_itr)[1].toString()+""+((Object[])distinctmonth_itr)[0].toString());
		        for(Object topupadjustment_itr :topupadjustment) {
		        	
		        	System.out.println(((Double)topupadjustment_itr).toString());
		        	temp_non_player.put("adjustment", ((Double)topupadjustment_itr).toString());
		        }
		        
		        temp_non_player.put("sales", topups - withdraws);
		        
		        
		        result_list.add(temp_non_player);        
		    
			
		}
		}
		response.put("code", HttpStatus.OK);
	    response.put("msg", "All Unclaim List by promotion");
	    response.put("data", result_list);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		
	}

}
