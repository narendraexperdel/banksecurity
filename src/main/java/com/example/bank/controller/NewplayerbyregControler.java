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
import com.example.bank.bean.Wettopuprequestbean;
import com.example.bank.model.CmcProduct;
import com.example.bank.model.CmcSources;
import com.example.bank.model.TmpPlayer;
import com.example.bank.model.WemPlayer;
import com.example.bank.model.WemPromotion;
import com.example.bank.model.Wettopup;
import com.example.bank.service.CmcproductService;
import com.example.bank.service.CmcsourcesService;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WemplayerService;
import com.example.bank.service.WempromotionService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class NewplayerbyregControler {
	

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
	
	
	
	@PostMapping("newplayer-reg")
	public ResponseEntity<Object> newplayerreg(@RequestBody  Newplayerregbean newplayerreg){
		
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

		if(newplayerreg.getCompanyid1() != null) {
		String product = newplayerreg.getProduct().get(0);
		String contactsources = newplayerreg.getContactsources().get(0);
		
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
		
		
		List<WemPlayer>  wemplayerlist = wemplayerService.getallplayer(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_contsources_list);
		Iterator itr = wemplayerlist.iterator();
		while(itr.hasNext()){
			
			Map <String , Object> temp_player_map_non_topup = new HashMap<>();
			Map<String , Object> temp_userid_non_topup = null;
			
			List<Map<String,Object>> tmp_userid_list_non_topup = new ArrayList<>();
			
			Map <String , Object> temp_player_map_re_topup = new HashMap<>();
			Map<String , Object> temp_userid_re_topup = null;
			
			List<Map<String,Object>> tmp_userid_list_re_topup = new ArrayList<>();
			
			
			   Object[] obj = (Object[]) itr.next();
			   useridlist_topup = new ArrayList<>();
			   wemplayeridlist.add(Integer.parseInt(String.valueOf(obj[0])));
			   List<TmpPlayer> tmpplayer_topupplayer = tmpplayerService.gettopupplayer(newplayerreg.getCompanyid1(), temp_product_list, Integer.parseInt(String.valueOf(obj[0])));
			   Iterator tmpplayer_itr_topupplayer = tmpplayer_topupplayer.iterator();
				while(tmpplayer_itr_topupplayer.hasNext()){
					   Object[] obj1 = (Object[]) tmpplayer_itr_topupplayer.next();
					   useridlist_topup.add(String.valueOf(obj1[8]));
					   
				}
				
				Double totaltopup_palyer = wettopupService.topupamount_newplayerreg(useridlist_topup, newplayerreg.getCompanyid1(), temp_product_list);
				Long retopup_palyer = wettopupService.retopup_newplayerreg(useridlist_topup, newplayerreg.getCompanyid1(), temp_product_list);
			   if(totaltopup_palyer == null || totaltopup_palyer == 0.0) {
				   nontopupplayer_count = nontopupplayer_count+1;
				   
				   temp_player_map_non_topup.put("player_name", String.valueOf(obj[2]));
				   temp_player_map_non_topup.put("sources", String.valueOf(obj[3]));
				   temp_player_map_non_topup.put("issued_by", String.valueOf(obj[8]));
				   temp_player_map_non_topup.put("issued_date", String.valueOf(obj[9]));
				   temp_player_map_non_topup.put("contact_method", String.valueOf(obj[10]));
				   temp_player_map_non_topup.put("telno", String.valueOf(obj[1]));
				   
				   
				   List<TmpPlayer> tmpplayer_topupplayer_non_topup = tmpplayerService.gettopupplayer(newplayerreg.getCompanyid1(), temp_product_list, Integer.parseInt(String.valueOf(obj[0])));
				   Iterator tmpplayer_itr_topupplayer_non_topup = tmpplayer_topupplayer_non_topup.iterator();
				   
				   while(tmpplayer_itr_topupplayer_non_topup.hasNext()){
					   Object[] obj2 = (Object[]) tmpplayer_itr_topupplayer_non_topup.next();
//					   useridlist_topup.add(String.valueOf(obj1[8]));
					   temp_userid_non_topup = new HashMap<>();
					   temp_userid_non_topup.put("userid",String.valueOf(obj2[8]));
					   temp_userid_non_topup.put("productid",String.valueOf(obj2[1]));
						
						Double userid_topup_amount_nontopup = wettopupService.getsumamountbyuserid(String.valueOf(obj2[8]), String.valueOf(obj2[1]),newplayerreg.getCompanyid1());
						if(userid_topup_amount_nontopup == null) {
							userid_topup_amount_nontopup = 0.0;
						}
						
						temp_userid_non_topup.put("topup", userid_topup_amount_nontopup);
						
						tmp_userid_list_non_topup.add(temp_userid_non_topup);
					   
				}
				   
				   temp_player_map_non_topup.put("userid_list", tmp_userid_list_non_topup);
					
					//Add temp_user_map to Result_list
					
				   result_list_non_topup.add(temp_player_map_non_topup);
				   
			   }else if(totaltopup_palyer != null && totaltopup_palyer != 0.0) {
				   topupplayer_count = topupplayer_count +1;
			   }
			   
			   if(retopup_palyer >=2) {
				   retopup_count = retopup_count+1;
				   
				   temp_player_map_re_topup.put("player_name", String.valueOf(obj[2]));
				   temp_player_map_re_topup.put("sources", String.valueOf(obj[3]));
				   temp_player_map_re_topup.put("issued_by", String.valueOf(obj[8]));
				   temp_player_map_re_topup.put("issued_date", String.valueOf(obj[9]));
				   temp_player_map_re_topup.put("contact_method", String.valueOf(obj[10]));
				   temp_player_map_re_topup.put("telno", String.valueOf(obj[1]));
				   
				   List<TmpPlayer> tmpplayer_topupplayer_re_topup = tmpplayerService.gettopupplayer(newplayerreg.getCompanyid1(), temp_product_list, Integer.parseInt(String.valueOf(obj[0])));
				   Iterator tmpplayer_itr_topupplayer_re_topup = tmpplayer_topupplayer_re_topup.iterator();
				   
				   while(tmpplayer_itr_topupplayer_re_topup.hasNext()){
					   Object[] obj3 = (Object[]) tmpplayer_itr_topupplayer_re_topup.next();
//					   useridlist_topup.add(String.valueOf(obj1[8]));
					   temp_userid_re_topup = new HashMap<>();
					   temp_userid_re_topup.put("userid",String.valueOf(obj3[8]));
					   temp_userid_re_topup.put("productid",String.valueOf(obj3[1]));
						
						Double userid_topup_amount_retopup = wettopupService.getsumamountbyuserid(String.valueOf(obj3[8]), String.valueOf(obj3[1]),newplayerreg.getCompanyid1());
						if(userid_topup_amount_retopup == null) {
							userid_topup_amount_retopup = 0.0;
						}
						
						temp_userid_re_topup.put("topup", userid_topup_amount_retopup);
						
						tmp_userid_list_re_topup.add(temp_userid_re_topup);
					   
				}
				   
				   temp_player_map_re_topup.put("userid_list", tmp_userid_list_re_topup);
					
					//Add temp_user_map to Result_list
					
				   result_list_re_topup.add(temp_player_map_re_topup);
				   
				   
			   }
			   
		}
		
		
		List<TmpPlayer> tmpplayer_fornewplayer = tmpplayerService.getuseridfornewplayerreg(newplayerreg.getCompanyid1(), temp_product_list, wemplayeridlist);
		Iterator tmpplayer_itr = tmpplayer_fornewplayer.iterator();
		while(tmpplayer_itr.hasNext()){
			   Object[] obj = (Object[]) tmpplayer_itr.next();
			   useridlist.add(String.valueOf(obj[8]));
			   
		}
		
		
		 totaltopup = wettopupService.topupamount_newplayerreg(useridlist, newplayerreg.getCompanyid1(), temp_product_list);
		
		 withdrawtopup = wetwithdrawService.withdrawamount_newplayerreg(useridlist, newplayerreg.getCompanyid1(), temp_product_list);
		
		 if(totaltopup == null) {
			 totaltopup = 0.0;
		 }
	
		 if(withdrawtopup == null) {
			 withdrawtopup = 0.0;
		 }
		 
		summary.put("totaltopup", totaltopup);
		summary.put("totalwithdraw", withdrawtopup);
		summary.put("totalnewaccount", tmpplayer_fornewplayer.size());
		summary.put("totalnewplayer", wemplayerlist.size());
		summary.put("totaltopupplayer", topupplayer_count);
		summary.put("totalnontopupplayer", nontopupplayer_count);
		summary.put("retopupcount", retopup_count);
		
		List<WemPlayer>  wemplayerlist_table = wemplayerService.getallplayer(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate(), temp_contsources_list);
		Iterator itr_table = wemplayerlist_table.iterator();
		while(itr_table.hasNext()){
		   Object[] obj = (Object[]) itr_table.next();
		   //now you have one array of Object for each row
		   String client = String.valueOf(obj[0]); // don't know the type of column CLIENT assuming String 
		   Integer service = Integer.parseInt(String.valueOf(obj[1])); //SERVICE assumed as int
		   //same way for all obj[2], obj[3], obj[4]
		
			
			/**
			 * player not found
			 */
			
			
			Map <String , Object> temp_player_map = new HashMap<>();
			Map<String , Object> temp_userid = null;
			
			List<Map<String,Object>> tmp_userid_list = new ArrayList<>();
			
			temp_player_map.put("player_name", String.valueOf(obj[2]));
			temp_player_map.put("sources", String.valueOf(obj[3]));
			temp_player_map.put("issued_by", String.valueOf(obj[8]));
			temp_player_map.put("issued_date", String.valueOf(obj[9]));
			temp_player_map.put("contact_method", String.valueOf(obj[10]));
			temp_player_map.put("telno", String.valueOf(obj[1]));
			
			List<TmpPlayer> tmpplayer_list = tmpplayerService.getuseridbyplayer(newplayerreg.getCompanyid1(), Integer.parseInt(String.valueOf(obj[0])),temp_product_list);
			
			
			if(!tmpplayer_list.isEmpty()) {
				for(TmpPlayer tmpplayerlist_itr: tmpplayer_list) {
					temp_userid = new HashMap<>();
					temp_userid.put("userid", tmpplayerlist_itr.getUserid());
					temp_userid.put("productid", tmpplayerlist_itr.getProductid());
					
					Double userid_topup_amount = wettopupService.getsumamountbyuserid(tmpplayerlist_itr.getUserid(), tmpplayerlist_itr.getProductid(),newplayerreg.getCompanyid1());
					if(userid_topup_amount == null) {
						userid_topup_amount = 0.0;
					}
					
					temp_userid.put("topup", userid_topup_amount);
					
					tmp_userid_list.add(temp_userid);
					
				}
				
			}
			
			
			temp_player_map.put("userid_list", tmp_userid_list);
			
			
			
			//Add temp_user_map to Result_list
			
			result_list.add(temp_player_map);		
		
	  }
	}
    
    /**
     * Formatting data based on datatables
     */
    
    
    List<Map<String , Object>> formatted_result = new ArrayList<>();
    
    
    for(Map<String , Object> player : result_list ) {
    	List <Map<String , Object>> temp_userid_list = (List<Map<String, Object>>) player.get("userid_list");
    	int i=0;
    	
    	for(Map<String , Object> userid : temp_userid_list) {
    		Map<String , Object> temp_data_obj = new HashMap<>();
    		if(i==0) {
    			temp_data_obj.put("playername" , player.get("player_name"));
    		temp_data_obj.put("sources", player.get("sources"));
    		temp_data_obj.put("issued_by", player.get("issued_by"));
    		temp_data_obj.put("issued_date", player.get("issued_date"));
    		temp_data_obj.put("contact_method", player.get("contact_method"));
    		temp_data_obj.put("telno", player.get("telno"));
    		}
    		else {
    			temp_data_obj.put("playername" , "");
    			temp_data_obj.put("sources", "");
        		temp_data_obj.put("issued_by", "");
        		temp_data_obj.put("issued_date", "");
        		temp_data_obj.put("contact_method", "");
        		temp_data_obj.put("telno", "");
    		}
    		
    		temp_data_obj.put("userid", userid.get("userid"));
    		temp_data_obj.put("productid", userid.get("productid"));
    		temp_data_obj.put("topup", userid.get("topup"));
    		
    		
    		i++;
    		formatted_result.add(temp_data_obj);
    	
    	}
    	
    	
    }
    
    
    /**
     * Formatting nontopup data based on datatables
     */
    
    
    List<Map<String , Object>> formatted_result_non_topup = new ArrayList<>();
    
    
    for(Map<String , Object> player : result_list_non_topup ) {
    	List <Map<String , Object>> temp_userid_list = (List<Map<String, Object>>) player.get("userid_list");
    	int i=0;
    	
    	for(Map<String , Object> userid : temp_userid_list) {
    		Map<String , Object> temp_data_obj = new HashMap<>();
    		if(i==0) {
    			temp_data_obj.put("playername" , player.get("player_name"));
    		temp_data_obj.put("sources", player.get("sources"));
    		temp_data_obj.put("issued_by", player.get("issued_by"));
    		temp_data_obj.put("issued_date", player.get("issued_date"));
    		temp_data_obj.put("contact_method", player.get("contact_method"));
    		temp_data_obj.put("telno", player.get("telno"));
    		}
    		else {
    			temp_data_obj.put("playername" , "");
    			temp_data_obj.put("sources", "");
        		temp_data_obj.put("issued_by", "");
        		temp_data_obj.put("issued_date", "");
        		temp_data_obj.put("contact_method", "");
        		temp_data_obj.put("telno", "");
    		}
    		
    		temp_data_obj.put("userid", userid.get("userid"));
    		temp_data_obj.put("productid", userid.get("productid"));
    		temp_data_obj.put("topup", userid.get("topup"));
    		
    		
    		i++;
    		formatted_result_non_topup.add(temp_data_obj);
    	
    	}
    	
    	
    }
    
    /**
     * Formatting nontopup data based on datatables
     */
    
    
    List<Map<String , Object>> formatted_result_re_topup = new ArrayList<>();
    
    
    for(Map<String , Object> player : result_list_re_topup ) {
    	List <Map<String , Object>> temp_userid_list = (List<Map<String, Object>>) player.get("userid_list");
    	int i=0;
    	
    	for(Map<String , Object> userid : temp_userid_list) {
    		Map<String , Object> temp_data_obj = new HashMap<>();
    		if(i==0) {
    			temp_data_obj.put("playername" , player.get("player_name"));
    		temp_data_obj.put("sources", player.get("sources"));
    		temp_data_obj.put("issued_by", player.get("issued_by"));
    		temp_data_obj.put("issued_date", player.get("issued_date"));
    		temp_data_obj.put("contact_method", player.get("contact_method"));
    		temp_data_obj.put("telno", player.get("telno"));
    		}
    		else {
    			temp_data_obj.put("playername" , "");
    			temp_data_obj.put("sources", "");
        		temp_data_obj.put("issued_by", "");
        		temp_data_obj.put("issued_date", "");
        		temp_data_obj.put("contact_method", "");
        		temp_data_obj.put("telno", "");
    		}
    		
    		temp_data_obj.put("userid", userid.get("userid"));
    		temp_data_obj.put("productid", userid.get("productid"));
    		temp_data_obj.put("topup", userid.get("topup"));
    		
    		
    		i++;
    		formatted_result_re_topup.add(temp_data_obj);
    	
    	}
    	
    	
    }
    
    
    response.put("code", HttpStatus.OK);
    response.put("msg", "All Unclaim List by promotion");
    response.put("data", formatted_result);
    response.put("summary", summary);
    response.put("nontopuplist", formatted_result_non_topup);
    response.put("retopuplist", formatted_result_re_topup);
    
	return new ResponseEntity<Object>(response, HttpStatus.OK);
	
	}

}
