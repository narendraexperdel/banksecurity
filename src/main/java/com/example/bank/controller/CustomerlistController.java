package com.example.bank.controller;

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

import com.example.bank.bean.Newplayerregbean;
import com.example.bank.model.TmpPlayer;
import com.example.bank.model.Wettopup;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class CustomerlistController {
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;

	@PostMapping("customer-list")
	public ResponseEntity<Object> statisticreportcs(@RequestBody  Newplayerregbean newplayerreg){
		
		List<TmpPlayer> tmpplayer = new ArrayList<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Map<String, Object> response = new HashMap<>();
		
		
		if(newplayerreg.getCompanyid1() != null) {
			String userid1 = newplayerreg.getUserid();
			
			if(userid1 == null) {
				tmpplayer = tmpplayerService.tmpplayer_list(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate());
				
			}else {
				
				tmpplayer = tmpplayerService.tmpplayer_list(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), userid1);
				
			}
			
			
		
			
			Iterator tmpplayer_itr = tmpplayer.iterator();
			
			List<String> userid = new ArrayList();
			List<String> productid = new ArrayList<>();
			List<Wettopup> wettopup_list = new ArrayList<>();
			
			while(tmpplayer_itr.hasNext()) {
				
				Wettopup wettopup = new Wettopup();
				
				   Object[] obj = (Object[]) tmpplayer_itr.next();
				   wettopup.setUserid(String.valueOf(obj[8]));
				   wettopup.setProductid(String.valueOf(obj[1]));
				   wettopup.setIssueddate((Date)obj[5]);
				   wettopup_list.add(wettopup);  
			}
			
				   for(Wettopup wettopup_itr: wettopup_list) {
					   
					   Map<String,Object>  temp_customer_list = new HashMap<>();
					   
					   temp_customer_list.put("userid", wettopup_itr.getUserid());
					   temp_customer_list.put("createplayerdate", wettopup_itr.getIssueddate());
					   
					   List<Wettopup> lasttopupamount = wettopupService.lasttopupandamount_customerlist(newplayerreg.getCompanyid1(), wettopup_itr.getProductid(), wettopup_itr.getUserid(), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
					   
					   if(lasttopupamount.isEmpty()) {
						   temp_customer_list.put("lastdepositdate", "");
					   }else {
						   
						   Iterator lasttopamount_itr = lasttopupamount.iterator();
						   
						   while(lasttopamount_itr.hasNext()) {
							   
								   Object[] obj1 = (Object[]) lasttopamount_itr.next();
								   
								   temp_customer_list.put("lastdepositdate", obj1[0]);    
							}
						   
					   }
					  
					   
					   List<String> sourcesandmethod = tmpplayerService.playername(wettopup_itr.getUserid(), wettopup_itr.getProductid(), newplayerreg.getCompanyid1());
					   
                          Iterator sourcesandmethod_itr = sourcesandmethod.iterator();
					   
					   while(sourcesandmethod_itr.hasNext()) {
						   
						   Object[] obj2 = (Object[]) sourcesandmethod_itr.next();
							   
							   temp_customer_list.put("contactmethod",String.valueOf(obj2[2])); 
							   temp_customer_list.put("sources",String.valueOf(obj2[3])); 
						}
					   
					   
					   result_list.add(temp_customer_list);
					   
				   }
				   
				   
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Customerlist data");
	    response.put("data", result_list);
	    response.put("datasize", result_list.size());
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
