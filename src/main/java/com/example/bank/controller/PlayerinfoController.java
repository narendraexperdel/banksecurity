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
import com.example.bank.model.CmcProduct;
import com.example.bank.model.CmcSources;
import com.example.bank.model.TmpPlayer;
import com.example.bank.model.WemPlayer;
import com.example.bank.model.Wettopup;
import com.example.bank.model.Wetwithdraw;
import com.example.bank.service.CmcproductService;
import com.example.bank.service.CmcsourcesService;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WemplayerService;
import com.example.bank.service.WempromotionService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class PlayerinfoController {
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	WempromotionService wempromoService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;
	
	@Autowired
	CmcproductService cmcproductService;
	
	@Autowired
	CmcsourcesService cmcsourcesService;
	
	@Autowired
	WemplayerService wemplayerService;
	
	@PostMapping("player-info")
	public ResponseEntity<Object> playerinfo(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>(); 
	
		List<Map<String , Object>> result_list = new ArrayList<>();
		List<Map<String , Object>> result_list_non_topup = new ArrayList<>();
		List<Map<String , Object>> result_list_re_topup = new ArrayList<>();
		Map <String , Object> summary = new HashMap<>();
		
		List<String> useridlist = new ArrayList<>();
		List<String> useridlist_topup = null;
		List<Integer> wemplayeridlist = new ArrayList<>();
		int topupplayer_count = 0;
		int nontopupplayer_count = 0;
		int retopup_count = 0;
		Double totaltopup = 0.0;
		Double withdrawtopup = 0.0;
		
		List<WemPlayer> wemplayer = new ArrayList<>();

		if(newplayerreg.getCompanyid1() != null) {
		String product = newplayerreg.getProduct().get(0);
		String contactsources = newplayerreg.getContactsources().get(0);
		String playername = newplayerreg.getPlayername().get(0);
		
		List<String> temp_product_list = new ArrayList<>();
		List<String> temp_contsources_list = new ArrayList<>();
		
		if(product == null) {
			List<CmcProduct> productlist = cmcproductService.getallproductbycompany(newplayerreg.getCompanyid1());
			for(CmcProduct productlist_itr: productlist) {
				temp_product_list.add(productlist_itr.getFldesc());
			}
		}else {
			temp_product_list = newplayerreg.getProduct();
		}
		
		if(contactsources == null) {
			List<CmcSources> sourceslist = cmcsourcesService.getallsourcebycompany(newplayerreg.getCompanyid1());
			for(CmcSources sourceslist_itr: sourceslist) {
				temp_contsources_list.add(sourceslist_itr.getFldesc());
			}
		}else {
			temp_contsources_list = newplayerreg.getContactsources();
		}
		
		
		if(playername == null) {
			wemplayer = wemplayerService.getallplayer(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_contsources_list);
		}else {
			wemplayer = wemplayerService.getallplayerinfo(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_contsources_list, playername);
		}
		
	
		Iterator itr = wemplayer.iterator();
		while(itr.hasNext()){
			
			   Object[] obj = (Object[]) itr.next();
			   
			   Double balance = 0.0;
			   
			   List<TmpPlayer> tmpplayer = tmpplayerService.gettopupplayer(newplayerreg.getCompanyid1(), temp_product_list, Integer.parseInt(String.valueOf(obj[0])));
			   int i =1;
			   Iterator tmpplayer_itr = tmpplayer.iterator();
			   while(tmpplayer_itr.hasNext()) {
				   Map<String, Object> temp_player_info = new HashMap<>();
				   
				   Object[] obj1 = (Object[]) tmpplayer_itr.next();
				   
				   if(i == 1) {
					   temp_player_info.put("flanme", String.valueOf(obj[2]));
					   temp_player_info.put("vip", String.valueOf(obj[18]));
					   temp_player_info.put("gender", String.valueOf(obj[4]));
					   temp_player_info.put("race", String.valueOf(obj[5]));
					   temp_player_info.put("issuedby", String.valueOf(obj[8]));
					   temp_player_info.put("issueddate", String.valueOf(obj[9]));
					   temp_player_info.put("contactmethod", String.valueOf(obj[10]));
					   temp_player_info.put("contactsources", String.valueOf(obj[3]));
					   temp_player_info.put("product", String.valueOf(obj1[1]));
					   temp_player_info.put("userid", String.valueOf(obj1[8]));
					   
					   List<Wettopup> lasttopup = wettopupService.lasttopupandamount(newplayerreg.getCompanyid1(), String.valueOf(obj1[1]), String.valueOf(obj1[8]));
					   
					   
					   if(lasttopup.isEmpty()) {
						   
						   temp_player_info.put("lasttopupdate", "-");
						   temp_player_info.put("lasttopupamount", "-");
						   
					   }else {
						   
						   Iterator lasttopup_itr = lasttopup.iterator();
						   while(lasttopup_itr.hasNext()) {
							   
							   Object[] obj2 = (Object[]) lasttopup_itr.next();
							   
							   temp_player_info.put("lasttopupdate", String.valueOf(obj2[0]));
							   temp_player_info.put("lasttopupamount", String.valueOf(obj2[1]));
							   
						   }
						   
					   }
					  
					   
                      List<Wetwithdraw> lastwithdraw = wetwithdrawService.lasttopupandamount(newplayerreg.getCompanyid1(), String.valueOf(obj1[1]), String.valueOf(obj1[8]));
					   
                      
                      if(lastwithdraw.isEmpty()) {
                    	  temp_player_info.put("lastwithdrawdate", "-");
						   temp_player_info.put("lastwithdrawamount", "-");
                      }else {
                    	  Iterator lastwithdraw_itr = lastwithdraw.iterator();
   					   while(lastwithdraw_itr.hasNext()) {
   						   
   						   Object[] obj3 = (Object[]) lastwithdraw_itr.next();
   						   
   						   temp_player_info.put("lastwithdrawdate", String.valueOf(obj3[0]));
   						   temp_player_info.put("lastwithdrawamount", String.valueOf(obj3[1]));
   						   
   					   }
                      }
					 
					   
					   Double topup = wettopupService.getsumamountbyuserid(String.valueOf(obj1[8]), String.valueOf(obj1[1]), newplayerreg.getCompanyid1());
					   
					   Double withdraw = wetwithdrawService.getsumamountbyuserid_withdraw(String.valueOf(obj1[8]), String.valueOf(obj1[1]), newplayerreg.getCompanyid1());
				 
				       Double bonus = wettopupService.getsumbonusbyuserid(String.valueOf(obj1[8]), String.valueOf(obj1[1]), newplayerreg.getCompanyid1());
				       
				       Double adjustment = wettopupService.getadjustmentbonusbyuserid(String.valueOf(obj1[8]), String.valueOf(obj1[1]), newplayerreg.getCompanyid1());
				   
				   
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
						
					   
						if(topup != 0.0 && withdraw !=0.0) {
						  balance = topup - withdraw;	
						}
				       
						    temp_player_info.put("topup", topup);
						   temp_player_info.put("withdraw", withdraw);
						   temp_player_info.put("bonus", bonus);
						   temp_player_info.put("adjustment", adjustment);
						   temp_player_info.put("balance", balance);
						   
						   result_list.add(temp_player_info);
				   }else if(i > 1){
					   
					   temp_player_info.put("flanme", " ");
					   temp_player_info.put("vip", String.valueOf(obj[18]));
					   temp_player_info.put("gender", String.valueOf(obj[4]));
					   temp_player_info.put("race", String.valueOf(obj[5]));
					   temp_player_info.put("issuedby", String.valueOf(obj[8]));
					   temp_player_info.put("issueddate", String.valueOf(obj[9]));
					   temp_player_info.put("contactmethod", String.valueOf(obj[10]));
					   temp_player_info.put("contactsources", String.valueOf(obj[3]));
					   temp_player_info.put("product", String.valueOf(obj1[1]));
					   temp_player_info.put("userid", String.valueOf(obj1[8]));
					   
					   List<Wettopup> lasttopup = wettopupService.lasttopupandamount(newplayerreg.getCompanyid1(), String.valueOf(obj1[1]), String.valueOf(obj1[8]));
					   
                      if(lasttopup.isEmpty()) {
						   
						   temp_player_info.put("lasttopupdate", "-");
						   temp_player_info.put("lasttopupamount", "-");
						   
					   }else {
						   
						   Iterator lasttopup_itr = lasttopup.iterator();
						   while(lasttopup_itr.hasNext()) {
							   
							   Object[] obj2 = (Object[]) lasttopup_itr.next();
							   
							   temp_player_info.put("lasttopupdate", String.valueOf(obj2[0]));
							   temp_player_info.put("lasttopupamount", String.valueOf(obj2[1]));
							   
						   }
						   
					   }
					  
					   
                      List<Wetwithdraw> lastwithdraw = wetwithdrawService.lasttopupandamount(newplayerreg.getCompanyid1(), String.valueOf(obj1[1]), String.valueOf(obj1[8]));
					   
                      
                      if(lastwithdraw.isEmpty()) {
                    	  temp_player_info.put("lastwithdrawdate", "-");
						   temp_player_info.put("lastwithdrawamount", "-");
                      }else {
                    	  Iterator lastwithdraw_itr = lastwithdraw.iterator();
   					   while(lastwithdraw_itr.hasNext()) {
   						   
   						   Object[] obj3 = (Object[]) lastwithdraw_itr.next();
   						   
   						   temp_player_info.put("lastwithdrawdate", String.valueOf(obj3[0]));
   						   temp_player_info.put("lastwithdrawamount", String.valueOf(obj3[1]));
   						   
   					   }
                      }
					   
					   Double topup = wettopupService.getsumamountbyuserid(String.valueOf(obj1[8]), String.valueOf(obj1[1]), newplayerreg.getCompanyid1());
					   
					   Double withdraw = wetwithdrawService.getsumamountbyuserid_withdraw(String.valueOf(obj1[8]), String.valueOf(obj1[1]), newplayerreg.getCompanyid1());
				 
				       Double bonus = wettopupService.getsumbonusbyuserid(String.valueOf(obj1[8]), String.valueOf(obj1[1]), newplayerreg.getCompanyid1());
				       
				       Double adjustment = wettopupService.getadjustmentbonusbyuserid(String.valueOf(obj1[8]), String.valueOf(obj1[1]), newplayerreg.getCompanyid1());
				   
				   
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
						
					   
						if(topup != 0.0 && withdraw !=0.0) {
						  balance = topup - withdraw;	
						}
				       
						    temp_player_info.put("topup", topup);
						   temp_player_info.put("withdraw", withdraw);
						   temp_player_info.put("bonus", bonus);
						   temp_player_info.put("adjustment", adjustment);
						   temp_player_info.put("balance", balance);
					   
						   result_list.add(temp_player_info);
				   }
				 
				   i++;
				   
			   }
			   
			   Map<String, Object> temp_player_info = new HashMap<>();
			   List<String> productid = new ArrayList<>();
			   List<String> userid = new ArrayList<>();
			   Double balance1 = 0.0;
			   
			   temp_player_info.put("flanme", "Name Total: ");
			   temp_player_info.put("vip", "");
			   temp_player_info.put("gender", "");
			   temp_player_info.put("race", "");
			   temp_player_info.put("issuedby", "");
			   temp_player_info.put("issueddate", "");
			   temp_player_info.put("contactmethod", "");
			   temp_player_info.put("contactsources", "");
			   temp_player_info.put("product", "");
			   temp_player_info.put("userid", "");
			   temp_player_info.put("lasttopupdate", "");
			   temp_player_info.put("lasttopupamount", "");
			   temp_player_info.put("lastwithdrawdate", "");
			   temp_player_info.put("lastwithdrawamount", "");
			   
			   Iterator tmpplayer_report_itr = tmpplayer.iterator();
			   while(tmpplayer_report_itr.hasNext()) {
				   
				   Object[] obj1 = (Object[]) tmpplayer_report_itr.next();
				   
				   productid.add(String.valueOf(obj1[1]));
				   userid.add(String.valueOf(obj1[8]));
			   }
			   
			   Double topup = wettopupService.topupamount_newplayerreg(userid, newplayerreg.getCompanyid1(), productid);
			   
			   Double withdraw = wetwithdrawService.withdrawamount_newplayerreg(userid, newplayerreg.getCompanyid1(), productid);
			   
			   Double bonus = wettopupService.bonus_newplayerreg(userid, newplayerreg.getCompanyid1(), productid);
			   
			   Double adjustment = wettopupService.adjustment_newplayerreg(userid, newplayerreg.getCompanyid1(), productid);
			   
		   
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
				
			   
				if(topup != 0.0 && withdraw !=0.0) {
				  balance1 = topup - withdraw;	
				}
		       
				    temp_player_info.put("topup", topup);
				   temp_player_info.put("withdraw", withdraw);
				   temp_player_info.put("bonus", bonus);
				   temp_player_info.put("adjustment", adjustment);
				   temp_player_info.put("balance", balance1);
			   
				   result_list.add(temp_player_info);
		   }
		
		
		List<Integer> wemplayerid = new ArrayList<>();
		List<String> productid = new ArrayList<>();
		List<String> userid = new ArrayList<>();
		Map<String, Object> temp_player_info = new HashMap<>();
		Double balance2 = 0.0;
		
		Iterator report_itr = wemplayer.iterator();
		while(report_itr.hasNext()){
			
			   Object[] obj = (Object[]) report_itr.next();
			   wemplayerid.add(Integer.parseInt(String.valueOf(obj[0]))); 
		}
		
		
		List<TmpPlayer> tmpPlayers = tmpplayerService.getuseridfornewplayerreg(newplayerreg.getCompanyid1(), temp_product_list, wemplayerid);
		
		Iterator tmpPlayers_itr = tmpPlayers.iterator();
		while(tmpPlayers_itr.hasNext()){
			
			   Object[] obj = (Object[]) tmpPlayers_itr.next();
			   productid.add(String.valueOf(obj[1])); 
			   userid.add(String.valueOf(obj[8])); 
		}
		
		 temp_player_info.put("flanme", "Grand Total: ");
		   temp_player_info.put("vip", "");
		   temp_player_info.put("gender", "");
		   temp_player_info.put("race", "");
		   temp_player_info.put("issuedby", "");
		   temp_player_info.put("issueddate", "");
		   temp_player_info.put("contactmethod", "");
		   temp_player_info.put("contactsources", "");
		   temp_player_info.put("product", "");
		   temp_player_info.put("userid", "");
		   temp_player_info.put("lasttopupdate", "");
		   temp_player_info.put("lasttopupamount", "");
		   temp_player_info.put("lastwithdrawdate", "");
		   temp_player_info.put("lastwithdrawamount", "");
		
		Double topup = wettopupService.topupamount_newplayerreg(userid, newplayerreg.getCompanyid1(), productid);
		   
		   Double withdraw = wetwithdrawService.withdrawamount_newplayerreg(userid, newplayerreg.getCompanyid1(), productid);
		   
		   Double bonus = wettopupService.bonus_newplayerreg(userid, newplayerreg.getCompanyid1(), productid);
		   
		   Double adjustment = wettopupService.adjustment_newplayerreg(userid, newplayerreg.getCompanyid1(), productid);
		   
	   
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
			
		   
			if(topup != 0.0 && withdraw !=0.0) {
				balance2 = topup - withdraw;	
			}
	       
			    temp_player_info.put("topup", topup);
			   temp_player_info.put("withdraw", withdraw);
			   temp_player_info.put("bonus", bonus);
			   temp_player_info.put("adjustment", adjustment);
			   temp_player_info.put("balance", balance2);
		   
			   result_list.add(temp_player_info);
		
		
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Player info data");
	    response.put("data", result_list);
	    response.put("datasize", wemplayer.size());
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}



}
