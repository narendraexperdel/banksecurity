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
public class StatisticreportcsController {
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;

	@PostMapping("statistic-report-cs")
	public ResponseEntity<Object> statisticreportcs(@RequestBody  Newplayerregbean newplayerreg){
		

		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			String selection = newplayerreg.getSelectiontype();
			
			List<TmpPlayer> tmpplayer = tmpplayerService.tmpplayer_list(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate());
			
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
			
			
			   
			   if(selection.equals("No Deposit After")) {
				   
				   for(Wettopup wettopup_itr: wettopup_list) {
					   
					   List<Wettopup> freebonususerid = wettopupService.freebonus_userid(newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup(), newplayerreg.getCompanyid1(), wettopup_itr.getUserid(),wettopup_itr.getProductid());
						
						if(! freebonususerid.isEmpty()) {
					   Iterator freebonususerid_itr = freebonususerid.iterator();
						
						while(freebonususerid_itr.hasNext()) {
							 Object[] obj = (Object[]) freebonususerid_itr.next();
							 
							/* Date fromdate = (Date) obj[5];
							 Calendar c = Calendar.getInstance(); 
								c.setTime(fromdate); 
								c.add(Calendar.DATE, 1);
								Date todate = c.getTime();*/
							 
							List<Wettopup> wet_topup_excludefree = wettopupService.topupfornonactiveexcludefree(String.valueOf(obj[8]), String.valueOf(obj[1]), newplayerreg.getCompanyid1(),newplayerreg.getDateOfissuetopup(),newplayerreg.getTodatetopup());
							
							if(wet_topup_excludefree.isEmpty()) {
								Map<String, Object>  temp_statistic_list = new HashMap();
								
								temp_statistic_list.put("registerdate", String.valueOf(obj[5]));
								temp_statistic_list.put("userid", String.valueOf(obj[8]));
								
								
								List<String> conmethod = tmpplayerService.playername(String.valueOf(obj[8]), String.valueOf(obj[1]), newplayerreg.getCompanyid1());
								
								Iterator conmethod_itr = conmethod.iterator();
								
								while(conmethod_itr.hasNext()) {
									Object[] obj1 = (Object[]) conmethod_itr.next();
									
									temp_statistic_list.put("telno", String.valueOf(obj1[1]));
									temp_statistic_list.put("contactmethod", String.valueOf(obj1[2]));
								}
								
								 
								Double topup = wettopupService.topwlplayer_topup(String.valueOf(obj[8]), newplayerreg.getCompanyid1(), String.valueOf(obj[1]), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
								
								Double bonus = wettopupService.topwlplayer_bonus(String.valueOf(obj[8]), newplayerreg.getCompanyid1(), String.valueOf(obj[1]), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
								
								Double withdraw = wetwithdrawService.topwlplayer_withdraw(String.valueOf(obj[8]), newplayerreg.getCompanyid1(), String.valueOf(obj[1]), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
								
								
								if(topup == null || topup == 0.0) {
									topup = 0.0;
								}
								
								if(bonus == null || bonus == 0.0) {
									bonus = 0.0;
								}
								
								if(withdraw == null || bonus == 0.0) {
									withdraw = 0.0;
								}
								
								
								temp_statistic_list.put("topup", topup);
								temp_statistic_list.put("bonus", bonus);
								temp_statistic_list.put("withdraw", withdraw);
								temp_statistic_list.put("wl", topup - withdraw);
								
								result_list.add(temp_statistic_list);
								
							
							}
						}
					   
						}
					   
				   }
				   
				   
				   
				   
			   }else if(selection.equals("Winning")) {
				   
                   for(Wettopup wettopup_itr: wettopup_list) {
					   
                		Map<String, Object>  temp_statistic_list = new HashMap();
								
								 
								Double topup = wettopupService.topwlplayer_topup(wettopup_itr.getUserid(), newplayerreg.getCompanyid1(), wettopup_itr.getProductid(), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
								
								Double bonus = wettopupService.topwlplayer_bonus(wettopup_itr.getUserid(), newplayerreg.getCompanyid1(), wettopup_itr.getProductid(), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
								
								Double withdraw = wetwithdrawService.topwlplayer_withdraw(wettopup_itr.getUserid(), newplayerreg.getCompanyid1(), wettopup_itr.getProductid(), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
								
								
								if(topup == null || topup == 0.0) {
									topup = 0.0;
								}
								
								if(bonus == null || bonus == 0.0 ) {
									bonus = 0.0;
								}
								
								if( withdraw ==null || withdraw == 0.0) {
									withdraw = 0.0;
								}
								
								
								if(withdraw > topup) {
									
									List<String> conmethod = tmpplayerService.playername(wettopup_itr.getUserid(), wettopup_itr.getProductid(), newplayerreg.getCompanyid1());
									
									Iterator conmethod_itr = conmethod.iterator();
									
									while(conmethod_itr.hasNext()) {
										Object[] obj1 = (Object[]) conmethod_itr.next();
										
										temp_statistic_list.put("telno", String.valueOf(obj1[1]));
										temp_statistic_list.put("contactmethod", String.valueOf(obj1[2]));
									}
									
									temp_statistic_list.put("registerdate", wettopup_itr.getIssueddate());
									temp_statistic_list.put("userid", wettopup_itr.getUserid());
									temp_statistic_list.put("topup", topup);
									temp_statistic_list.put("bonus", bonus);
									temp_statistic_list.put("withdraw", withdraw);
									temp_statistic_list.put("wl", topup - withdraw);
									
									result_list.add(temp_statistic_list);
									
									
								}
								
						
		}
				   
			   }else if(selection.equals("Losing")) {
				   
				   
				   for(Wettopup wettopup_itr: wettopup_list) {
					   
               		Map<String, Object>  temp_statistic_list = new HashMap();
								
								 
								Double topup = wettopupService.topwlplayer_topup(wettopup_itr.getUserid(), newplayerreg.getCompanyid1(), wettopup_itr.getProductid(), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
								
								Double bonus = wettopupService.topwlplayer_bonus(wettopup_itr.getUserid(), newplayerreg.getCompanyid1(), wettopup_itr.getProductid(), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
								
								Double withdraw = wetwithdrawService.topwlplayer_withdraw(wettopup_itr.getUserid(), newplayerreg.getCompanyid1(), wettopup_itr.getProductid(), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
								
								
								if(topup == null || topup == 0.0) {
									topup = 0.0;
								}
								
								if(bonus == null || bonus == 0.0) {
									bonus = 0.0;
								}
								
								if(withdraw == null || bonus == 0.0) {
									withdraw = 0.0;
								}
								
								
								if(withdraw < topup) {
									
									List<String> conmethod = tmpplayerService.playername(wettopup_itr.getUserid(), wettopup_itr.getProductid(), newplayerreg.getCompanyid1());
									
									Iterator conmethod_itr = conmethod.iterator();
									
									while(conmethod_itr.hasNext()) {
										Object[] obj1 = (Object[]) conmethod_itr.next();
										
										temp_statistic_list.put("telno", String.valueOf(obj1[1]));
										temp_statistic_list.put("contactmethod", String.valueOf(obj1[2]));
									}
									
									temp_statistic_list.put("registerdate", wettopup_itr.getIssueddate());
									temp_statistic_list.put("userid", wettopup_itr.getUserid());
									temp_statistic_list.put("topup", topup);
									temp_statistic_list.put("bonus", bonus);
									temp_statistic_list.put("withdraw", withdraw);
									temp_statistic_list.put("wl", topup - withdraw);
									
									result_list.add(temp_statistic_list);
									
									
								}
								
						
		}
				   
				   
				   
			   }else if(selection.equals("No Re-Deposit")) {
				   
                    for(Wettopup wettopup_itr: wettopup_list) {
					   
					   List<Wettopup> freebonususerid = wettopupService.freebonus_userid(newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup(), newplayerreg.getCompanyid1(), wettopup_itr.getUserid(),wettopup_itr.getProductid());
						
						if(! freebonususerid.isEmpty()) {
					   Iterator freebonususerid_itr = freebonususerid.iterator();
						
						while(freebonususerid_itr.hasNext()) {
							 Object[] obj = (Object[]) freebonususerid_itr.next();
							 
							/* Date fromdate = (Date) obj[5];
							 Calendar c = Calendar.getInstance(); 
								c.setTime(fromdate); 
								c.add(Calendar.DATE, 1);
								Date todate = c.getTime();*/
							 
							List<Wettopup> wet_topup_excludefree = wettopupService.topupfornonactiveexcludefree(String.valueOf(obj[8]), String.valueOf(obj[1]), newplayerreg.getCompanyid1(),newplayerreg.getDateOfissuetopup(),newplayerreg.getTodatetopup());
							
							if(!wet_topup_excludefree.isEmpty()) {
								
								if(wet_topup_excludefree.size() == 1) {
								
								Map<String, Object>  temp_statistic_list = new HashMap();
								
								temp_statistic_list.put("registerdate", String.valueOf(obj[5]));
								temp_statistic_list.put("userid", String.valueOf(obj[8]));
								
								
								List<String> conmethod = tmpplayerService.playername(String.valueOf(obj[8]), String.valueOf(obj[1]), newplayerreg.getCompanyid1());
								
								Iterator conmethod_itr = conmethod.iterator();
								
								while(conmethod_itr.hasNext()) {
									Object[] obj1 = (Object[]) conmethod_itr.next();
									
									temp_statistic_list.put("telno", String.valueOf(obj1[1]));
									temp_statistic_list.put("contactmethod", String.valueOf(obj1[2]));
								}
								
								 
								Double topup = wettopupService.topwlplayer_topup(String.valueOf(obj[8]), newplayerreg.getCompanyid1(), String.valueOf(obj[1]), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
								
								Double bonus = wettopupService.topwlplayer_bonus(String.valueOf(obj[8]), newplayerreg.getCompanyid1(), String.valueOf(obj[1]), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
								
								Double withdraw = wetwithdrawService.topwlplayer_withdraw(String.valueOf(obj[8]), newplayerreg.getCompanyid1(), String.valueOf(obj[1]), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
								
								
								if(topup == null || topup == 0.0) {
									topup = 0.0;
								}
								
								if(bonus == null || bonus == 0.0) {
									bonus = 0.0;
								}
								
								if(withdraw == null || bonus == 0.0) {
									withdraw = 0.0;
								}
								
								
								temp_statistic_list.put("topup", topup);
								temp_statistic_list.put("bonus", bonus);
								temp_statistic_list.put("withdraw", withdraw);
								temp_statistic_list.put("wl", topup - withdraw);
								
								result_list.add(temp_statistic_list);
								
							
							}
							
							}
							
						}
					   
						}
					   
				   }
			   }
			
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Statistic report data");
	    response.put("data", result_list);
	    response.put("datasize", result_list.size());
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
	}

}
