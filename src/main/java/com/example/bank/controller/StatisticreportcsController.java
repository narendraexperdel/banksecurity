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
import com.example.bank.model.CmcProduct;
import com.example.bank.model.CmcSources;
import com.example.bank.model.TmpPlayer;
import com.example.bank.model.WemPlayer;
import com.example.bank.model.Wettopup;
import com.example.bank.service.CmcproductService;
import com.example.bank.service.CmcsourcesService;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WemplayerService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class StatisticreportcsController {
	 String test="";
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;
	
	@Autowired
    WemplayerService wemplayerService;

	@Autowired
	CmcsourcesService cmcsourcesService;
	
	@Autowired
	CmcproductService cmcproductService;
	
	@PostMapping("statistic-report-cs")
	public ResponseEntity<Object> statisticreportcs(@RequestBody  Newplayerregbean newplayerreg){
		
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<Wettopup> wettopup_list = new ArrayList<>();
		List<String> useridlist = new ArrayList<>();
		List<TmpPlayer> useridlist_topup = null;
		List<Integer> wemplayeridlist = new ArrayList<>();
		int topupplayer_count = 0;
		int nontopupplayer_count = 0;
		int retopup_count = 0;
		Double totaltopup = 0.0;
		Double withdrawtopup = 0.0;
		List<String> temp_product_list = new ArrayList<>();
		List<String> temp_contsources_list = new ArrayList<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			String selection = newplayerreg.getSelectiontype();
			
			List<WemPlayer> wemplayer = wemplayerService.getallplayebydate(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate());
			
			Iterator wemplayer_itr = wemplayer.iterator();
			
			while(wemplayer_itr.hasNext()) {
				Object[] obj1 = (Object[]) wemplayer_itr.next();
				
				List<TmpPlayer> tmpplayer = tmpplayerService.getuseridbyplayer(newplayerreg.getCompanyid1(), Integer.parseInt(String.valueOf(obj1[0])));
				
				/*Iterator tmpplayer_itr = tmpplayer.iterator();*/
				
				List<String> userid = new ArrayList();
				List<String> productid = new ArrayList<>();
			
				
				for(TmpPlayer tmpplayerlist_itr: tmpplayer) {
					
					Wettopup wettopup = new Wettopup();
					
					  
					   wettopup.setUserid(tmpplayerlist_itr.getUserid());
					   wettopup.setProductid(tmpplayerlist_itr.getProductid());
					   wettopup.setIssueddate(tmpplayerlist_itr.getIssueddt());
					   wettopup_list.add(wettopup);  
					
					
				}
				
			/*	
				
				while(tmpplayer_itr.hasNext()) {
					
					Wettopup wettopup = new Wettopup();
					
					   Object[] obj = (Object[]) tmpplayer_itr.next();
					   wettopup.setUserid(String.valueOf(obj[8]));
					   wettopup.setProductid(String.valueOf(obj[1]));
					   wettopup.setIssueddate((Date)obj[5]);
					   wettopup_list.add(wettopup);  
				}*/
				
				
			}
			
			
		/*	List<TmpPlayer> tmpplayer = tmpplayerService.tmpplayer_list(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate());
			
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
			}*/
			
			
			   
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
							 
							List<Wettopup> wet_topup_excludefree = wettopupService.topupfornonactiveexcludefree(String.valueOf(obj[6]), String.valueOf(obj[12]), newplayerreg.getCompanyid1(),newplayerreg.getDateOfissuetopup(),newplayerreg.getTodatetopup());
							
							if(wet_topup_excludefree.isEmpty()) {
								Map<String, Object>  temp_statistic_list = new HashMap();
								
								temp_statistic_list.put("registerdate", String.valueOf(obj[4]));
								temp_statistic_list.put("userid", String.valueOf(obj[6]));
								
								
								List<String> conmethod = tmpplayerService.playername(String.valueOf(obj[6]), String.valueOf(obj[12]), newplayerreg.getCompanyid1());
								
								Iterator conmethod_itr = conmethod.iterator();
								
								while(conmethod_itr.hasNext()) {
									Object[] obj1 = (Object[]) conmethod_itr.next();
									
									temp_statistic_list.put("telno", String.valueOf(obj1[1]));
									temp_statistic_list.put("contactmethod", String.valueOf(obj1[2]));
								}
								
								 
								Double topup = wettopupService.topwlplayer_topup(String.valueOf(obj[6]), newplayerreg.getCompanyid1(), String.valueOf(obj[12]), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
								
								Double bonus = wettopupService.topwlplayer_bonus(String.valueOf(obj[6]), newplayerreg.getCompanyid1(), String.valueOf(obj[12]), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
								
								Double withdraw = wetwithdrawService.topwlplayer_withdraw(String.valueOf(obj[6]), newplayerreg.getCompanyid1(), String.valueOf(obj[12]), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
								
								
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
				   
				   
				  
						List<CmcSources> sourceslist = cmcsourcesService.getallsourcebycompany(newplayerreg.getCompanyid1());
						for(CmcSources sourceslist_itr: sourceslist) {
							temp_contsources_list.add(sourceslist_itr.getFldesc());
						}
						
						
						List<CmcProduct> productlist = cmcproductService.getallproductbycompany(newplayerreg.getCompanyid1());
						for(CmcProduct productlist_itr: productlist) {
							temp_product_list.add(productlist_itr.getFldesc());
						}
					
				   
				   
				   
				   List<WemPlayer>  wemplayerlist = wemplayerService.getallplayer(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_contsources_list);
					Iterator itr = wemplayerlist.iterator();
					 useridlist_topup = new ArrayList<>();
					while(itr.hasNext()){
						
						Map <String , Object> temp_player_map_non_topup = new HashMap<>();
						Map<String , Object> temp_userid_non_topup = null;
						
						List<Map<String,Object>> tmp_userid_list_non_topup = new ArrayList<>();
						
						Map <String , Object> temp_player_map_re_topup = new HashMap<>();
						Map<String , Object> temp_userid_re_topup = null;
						
						List<Map<String,Object>> tmp_userid_list_re_topup = new ArrayList<>();
						
						
						   Object[] obj = (Object[]) itr.next();
						  
						   wemplayeridlist.add(Integer.parseInt(String.valueOf(obj[0])));
						   List<TmpPlayer> tmpplayer_topupplayer = tmpplayerService.gettopupplayer_statisticcs(newplayerreg.getCompanyid1(), temp_product_list, Integer.parseInt(String.valueOf(obj[0])),newplayerreg.getDateOfissue(), newplayerreg.getTodate());
						   Iterator tmpplayer_itr_topupplayer = tmpplayer_topupplayer.iterator();
							while(tmpplayer_itr_topupplayer.hasNext()){
								   Object[] obj1 = (Object[]) tmpplayer_itr_topupplayer.next();
								   
								   TmpPlayer tmpplayer = new TmpPlayer();
								   tmpplayer.setUserid(String.valueOf(obj1[8]));
								   tmpplayer.setProductid(String.valueOf(obj1[1]));
								   tmpplayer.setIssueddt((Date)(obj1[5]));
								   useridlist_topup.add(tmpplayer);
								   
							}
					}
							
							for(TmpPlayer tmpplayer_itr : useridlist_topup ) {
								
								List<String> useridlist1 = new ArrayList<>();
								useridlist1.add(tmpplayer_itr.getUserid());
								
								Double totaltopup_palyer = wettopupService.topupamount_newplayerreg(useridlist1, newplayerreg.getCompanyid1(), temp_product_list);
							/*	Long retopup_palyer = wettopupService.retopup_newplayerreg(useridlist_topup, newplayerreg.getCompanyid1(), temp_product_list);*/
							   if(totaltopup_palyer == null || totaltopup_palyer == 0.0) {
								   nontopupplayer_count = nontopupplayer_count+1;
								   
								   Map<String, Object>  temp_statistic_list = new HashMap();
									
									temp_statistic_list.put("registerdate", tmpplayer_itr.getIssueddt());
									temp_statistic_list.put("userid", tmpplayer_itr.getUserid());
									
									
									List<String> conmethod = tmpplayerService.playername(tmpplayer_itr.getUserid(), tmpplayer_itr.getProductid(), newplayerreg.getCompanyid1());
									
									Iterator conmethod_itr = conmethod.iterator();
									
									while(conmethod_itr.hasNext()) {
										Object[] obj1 = (Object[]) conmethod_itr.next();
										
										temp_statistic_list.put("telno", String.valueOf(obj1[1]));
										temp_statistic_list.put("contactmethod", String.valueOf(obj1[2]));
									}
									
									 
									Double topup = wettopupService.topwlplayer_topup(tmpplayer_itr.getUserid(), newplayerreg.getCompanyid1(), tmpplayer_itr.getProductid(), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
									
									Double bonus = wettopupService.topwlplayer_bonus(tmpplayer_itr.getUserid(), newplayerreg.getCompanyid1(), tmpplayer_itr.getProductid(), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
									
									Double withdraw = wetwithdrawService.topwlplayer_withdraw(tmpplayer_itr.getUserid(), newplayerreg.getCompanyid1(), tmpplayer_itr.getProductid(), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
									
									
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
				   
		   
		   
             /*       for(Wettopup wettopup_itr: wettopup_list) {
					   
					   List<Wettopup> freebonususerid = wettopupService.freebonus_userid(newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup(), newplayerreg.getCompanyid1(), wettopup_itr.getUserid(),wettopup_itr.getProductid());
						
						if(! freebonususerid.isEmpty()) {
					   Iterator freebonususerid_itr = freebonususerid.iterator();
						
						while(freebonususerid_itr.hasNext()) {
							 Object[] obj = (Object[]) freebonususerid_itr.next();
							 
							 Date fromdate = (Date) obj[5];
							 Calendar c = Calendar.getInstance(); 
								c.setTime(fromdate); 
								c.add(Calendar.DATE, 1);
								Date todate = c.getTime();
							 
							List<Wettopup> wet_topup_excludefree = wettopupService.topupfornonactiveexcludefree(String.valueOf(obj[6]), String.valueOf(obj[12]), newplayerreg.getCompanyid1(),newplayerreg.getDateOfissuetopup(),newplayerreg.getTodatetopup());
							
							if(!wet_topup_excludefree.isEmpty()) {
								
								if(wet_topup_excludefree.size() == 1) {
								
								Map<String, Object>  temp_statistic_list = new HashMap();
								
								temp_statistic_list.put("registerdate", String.valueOf(obj[4]));
								temp_statistic_list.put("userid", String.valueOf(obj[12]));
								
								
								List<String> conmethod = tmpplayerService.playername(String.valueOf(obj[6]), String.valueOf(obj[12]), newplayerreg.getCompanyid1());
								
								Iterator conmethod_itr = conmethod.iterator();
								
								while(conmethod_itr.hasNext()) {
									Object[] obj1 = (Object[]) conmethod_itr.next();
									
									temp_statistic_list.put("telno", String.valueOf(obj1[1]));
									temp_statistic_list.put("contactmethod", String.valueOf(obj1[2]));
								}
								
								 
								Double topup = wettopupService.topwlplayer_topup(String.valueOf(obj[6]), newplayerreg.getCompanyid1(), String.valueOf(obj[12]), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
								
								Double bonus = wettopupService.topwlplayer_bonus(String.valueOf(obj[6]), newplayerreg.getCompanyid1(), String.valueOf(obj[12]), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
								
								Double withdraw = wetwithdrawService.topwlplayer_withdraw(String.valueOf(obj[6]), newplayerreg.getCompanyid1(), String.valueOf(obj[12]), newplayerreg.getDateOfissuetopup(), newplayerreg.getTodatetopup());
								
								
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
			   */
			   }
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Statistic report data");
	    response.put("data", result_list);
	    response.put("datasize", result_list.size());
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
	}

}
