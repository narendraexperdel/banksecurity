package com.example.bank.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.example.bank.model.TmpPlayer;
import com.example.bank.model.WemPlayer;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WemplayerService;
import com.example.bank.service.WettopupService;

@RestController
@RequestMapping("/api/")
public class VipCustomerController {
	
	@Autowired
	WemplayerService wemplayerService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	@Autowired
	WettopupService wettopupService;
	
	@PostMapping("vip-customer")
	public ResponseEntity<Object> newplayerreg(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
			List<WemPlayer> wemplayer_id = wemplayerService.getallplayebydate(newplayerreg.getCompanyid1(),newplayerreg.getDateOfissue(),newplayerreg.getTodate());
			
			Iterator itr = wemplayer_id.iterator();
			while(itr.hasNext()){
				 Object[] obj = (Object[]) itr.next();
				
				List<TmpPlayer>  tmp_userid = tmpplayerService.getuserid(newplayerreg.getCompanyid1() , Integer.parseInt(String.valueOf(obj[0])));
				
				List<String> tmpuser_append = new ArrayList<>();
				
				for(TmpPlayer tmpplayer_itr: tmp_userid) {
					tmpuser_append.add(tmpplayer_itr.getUserid());
				}
				 
				
				if(! tmp_userid.isEmpty()) {
					Double vipamount = wettopupService.vipamount(tmpuser_append, newplayerreg.getCompanyid1());
					
					if(vipamount != null) {
						 Map<String,Object> temp_non_player = new HashMap<>();
						if(vipamount >=100000 && vipamount < 150000) {
							
	                           
							
							temp_non_player.put("customer_name",  String.valueOf(obj[2]));
							temp_non_player.put("signup_date",  String.valueOf(obj[9]));
							temp_non_player.put("amount", vipamount);
							temp_non_player.put("level", "Over 100k");
							
							result_list.add(temp_non_player);	
							
						}else if(vipamount >=150000 && vipamount < 200000) {
							
							 
								
								temp_non_player.put("customer_name", String.valueOf(obj[2]));
								temp_non_player.put("signup_date", String.valueOf(obj[9]));
								temp_non_player.put("amount", vipamount);
								temp_non_player.put("level", "Over 150k");
								
								result_list.add(temp_non_player);
							
						}else if(vipamount >=200000 && vipamount < 250000) {
							
								temp_non_player.put("customer_name", String.valueOf(obj[2]));
								temp_non_player.put("signup_date", String.valueOf(obj[9]));
								temp_non_player.put("amount", vipamount);
								temp_non_player.put("level", "Over 200k");
								
								result_list.add(temp_non_player);
							
						} else if(vipamount >=250000 && vipamount < 300000) {
							temp_non_player.put("customer_name", String.valueOf(obj[2]));
							temp_non_player.put("signup_date", String.valueOf(obj[9]));
							temp_non_player.put("amount", vipamount);
							temp_non_player.put("level", "Over 250k");
							
							result_list.add(temp_non_player);
							
						}else if(vipamount >=300000 && vipamount < 500000) {
							temp_non_player.put("customer_name", String.valueOf(obj[2]));
							temp_non_player.put("signup_date", String.valueOf(obj[9]));
							temp_non_player.put("amount", vipamount);
							temp_non_player.put("level", "Over 300k");
							
							result_list.add(temp_non_player);
							
						}else if(vipamount >=500000 && vipamount < 800000) {
							temp_non_player.put("customer_name",String.valueOf(obj[2]));
							temp_non_player.put("signup_date", String.valueOf(obj[9]));
							temp_non_player.put("amount", vipamount);
							temp_non_player.put("level", "Over 500k");
							
							result_list.add(temp_non_player);
							
						}else if(vipamount >=800000 && vipamount < 1000000) {
							temp_non_player.put("customer_name", String.valueOf(obj[2]));
							temp_non_player.put("signup_date", String.valueOf(obj[9]));
							temp_non_player.put("amount", vipamount);
							temp_non_player.put("level", "Over 800k");
							
							result_list.add(temp_non_player);
							
						}else if(vipamount >=1000000) {
							temp_non_player.put("customer_name", String.valueOf(obj[2]));
							temp_non_player.put("signup_date", String.valueOf(obj[9]));
							temp_non_player.put("amount", vipamount);
							temp_non_player.put("level", "Over 1M");
							
							result_list.add(temp_non_player);
							
						}
					}
							
				}
				
			}
		}
		
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "All Unclaim List by promotion");
	    response.put("data", result_list);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		
	}

}
