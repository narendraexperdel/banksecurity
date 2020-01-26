package com.example.bank.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.model.WemPromotion;
import com.example.bank.model.Wettopup;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WempromotionService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WetwithdrawService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.example.bank.bean.Wettopuprequestbean;
import com.example.bank.model.*;

@RestController
@RequestMapping("/api/")
public class UnclaimController {
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	WempromotionService wempromoService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;

	/*@GetMapping("")
	public String test() {
		return "banksecurity working";
	}*/
	
	@GetMapping("topup/{id}")
	public ResponseEntity<Object> unclaimListbyId(@PathVariable Integer id) {
		
		Optional<Wettopup> wettopup = wettopupService.findWettopupbyId(id);
		
		Map<String,Object> response = new HashMap<>();
		
		response.put("code", HttpStatus.OK);
		response.put("msg", "Topup found by Id");
		response.put("data", wettopup);
		
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("unclaim")
	public ResponseEntity<Object> allUnclaimList(){
		
		List<Wettopup> unclaimlist = wettopupService.getallUnclaimlist();
		
		Map<String,Object>  customizedlist = null;
		List<Map<String, Object>> totallist = new ArrayList<>();
		
		Map<String, Object> response = new HashMap<>();
		
		for(Wettopup wettopup : unclaimlist) {
			customizedlist = new HashMap<>();
		customizedlist.put("trancid", wettopup.getTrancid());
		customizedlist.put("issuedby", wettopup.getIssuedby());
		customizedlist.put("issueddate", wettopup.getIssueddate());
		customizedlist.put("bankintype", wettopup.getBankintype());
		customizedlist.put("amount", wettopup.getAmount());
		customizedlist.put("bank", wettopup.getBank());
		customizedlist.put("depositvia", wettopup.getDepositvia());
		customizedlist.put("bonus", wettopup.getBonus());
		customizedlist.put("status", wettopup.getStatus());
		customizedlist.put("remark", wettopup.getRemark());
		customizedlist.put("claimby", wettopup.getClaimby());
		customizedlist.put("bankapprove", wettopup.getBankapproveby());
		customizedlist.put("csapproveby", wettopup.getCsapproveby());
		totallist.add(customizedlist);
		}
		
        response.put("code", HttpStatus.OK);
        response.put("msg", "All Unclaim List");
        response.put("data", totallist);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		
		
	}
	
	
	@PostMapping("promo-redem")
	public ResponseEntity<Object> promoredem(@RequestBody Wettopuprequestbean wettopup){
		
		Map<String, Object> response = new HashMap<>(); 
		int frequent = 1;
		Double promorate = 0.0;
		
//		Map<String, List<Map<String, Object>>> promo_result = new HashMap<>();
//		List<Map<String, Object>> promo_totalamount = new ArrayList<>();
//		Map<String, Object> promo_map = new HashMap<>();
		
		List<Map<String , Object>> result_list = new ArrayList<>();
		List<Map<String , Object>> promo_list = new ArrayList<>();

		if(wettopup.getCompanyid1() != null) {
		String promolist = wettopup.getPromoname().get(0);
		String userids = wettopup.getUserids().get(0);
		
		List<String> temp_promo_list = new ArrayList<>();
		List<String> temp_userid_list = new ArrayList<>();
		
		if(promolist == null) {
			List<WemPromotion> promobycompanyid = wempromoService.getwempromobycompanyid(wettopup.getCompanyid1());
			for(WemPromotion promobycompanyid_itr: promobycompanyid) {
				temp_promo_list.add(promobycompanyid_itr.getFldesc());
			}
		}else {
			temp_promo_list = wettopup.getPromoname();
		}
		
		if(userids == null) {
			List<String> useridbycompanyid = wettopupService.getuseridbytopuo(wettopup.getDateOfissue(), wettopup.getTodate(), wettopup.getCompanyid1());
			for(String useridbycompanyid_itr: useridbycompanyid) {
				temp_userid_list.add(useridbycompanyid_itr);
			}
		}else {
			temp_userid_list = wettopup.getUserids();
		}
		
		
	List<Wettopup> wettopuppromo =	wettopupService.getpromoredem(wettopup.getDateOfissue(), wettopup.getTodate(), temp_promo_list, temp_userid_list,wettopup.getCompanyid1());
	

	for(Wettopup topup_res : wettopuppromo ) {
		/**
		 * Iterate Result list to find userId exist or not
		 */
	
		int user_index = -1;
		int i = 0;
		for(Map<String , Object> user : result_list) {
			
			if(user.get("user_id").equals(topup_res.getUserid())) {
				user_index = i;
			}
			i++;
			
		}
		
		/**
		 * Check is user_id exists
		 */
		
		if(user_index != -1) {
			
			/**
			 * if user_id exist
			 */
			
			/**
			 * Promotion is null?
			 */
			if(topup_res.getPromotioncd() == null) {
				
				Map<String , Object> temp_user_map = new HashMap<>();
				List<Map<String , Object>> temp_promotion_list = new ArrayList<>();
				Object temp_promotion_list1 = new ArrayList<>();
				Map<String , Object> temp_promotion = new HashMap<>();
				Map<String , Object> temp_promotion1 = new HashMap<>();
				
				temp_user_map = result_list.get(user_index);
				temp_promotion_list = (List<Map<String, Object>>) temp_user_map.get("promotions");
				temp_promotion_list1 =  temp_user_map.get("total_top_up");
				
				
				int promotion_index = -1;
				int j = 0;
				int k = 0;
				for(Map<String , Object> promotion : temp_promotion_list) {
					
					if(promotion.get("promotion_name") == "None") {
						promotion_index = j;
					}
					
					j++;
				}
				
				/**
				 * If Promotion found
				 */
			
				if(promotion_index != -1) {
					temp_promotion = temp_promotion_list.get(promotion_index);
//					temp_user_map.put("total_top_up", Double(temp_user_map.get("total_top_up"))+topup_res.getAmount());
					
				}
				
			}else if(topup_res.getPromotioncd() != null){
				
				/**
				 * Promotion is not null
				 */
				
				Map<String , Object> temp_user_map = new HashMap<>();
				List<Map<String , Object>> temp_promotion_list = new ArrayList<>();
				Map<String , Object> temp_player_id_total_list = new HashMap<>();
				Object temp_promotion_list1 = new ArrayList<>();
				Map<String , Object> temp_promotion = new HashMap<>();
				Map<String , Object> temp_playerid = new HashMap<>();
				
				temp_user_map = result_list.get(user_index);
				temp_promotion_list = (List<Map<String, Object>>) temp_user_map.get("promotions");
				temp_player_id_total_list = (Map<String, Object>) temp_user_map.get("player_id_total");
				temp_promotion_list1 =  temp_user_map.get("total_top_up");
				
			
				int promotion_index = -1;
				int j = 0;
		
				for(Map<String , Object> promotion : temp_promotion_list) {
					
					if(promotion.get("promotion_name").equals(topup_res.getPromotioncd())) {
						promotion_index = j;
					}
					j++;
		
				}
				if(promotion_index != -1) {
					
					/**
					 * Found promotion
					 */
					temp_promotion=temp_promotion_list.get(promotion_index);
					temp_playerid=(Map<String, Object>) temp_user_map.get("player_id_total");
					temp_playerid.put("report_topup", Double(temp_playerid.get("report_topup"))+topup_res.getAmount());
					temp_playerid.put("report_frequent", Double(temp_playerid.get("report_frequent"))+1);
					temp_playerid.put("report_bonus", Double(temp_playerid.get("report_bonus"))+topup_res.getBonus());
					temp_promotion.put("topup", Double(temp_promotion.get("topup"))+topup_res.getAmount());
					temp_promotion.put("frequent", Double(temp_promotion.get("frequent"))+1);
					temp_promotion.put("totalbonus_redem", Double(temp_promotion.get("totalbonus_redem"))+topup_res.getBonus());
					temp_promotion_list.set(promotion_index, temp_promotion);
					temp_user_map.put("player_id_total", temp_playerid);
					temp_user_map.put("promotions", temp_promotion_list);
					result_list.set(user_index, temp_user_map);
					
					
				}else {
					/**
					 * Promotion Not found
					 */
					
					temp_promotion.put("promotion_name" , topup_res.getPromotioncd());
					List<WemPromotion> wemPromotion = wempromoService.getwempromobypromoname(topup_res.getPromotioncd(), wettopup.getCompanyid1());
					for(WemPromotion wem_itr: wemPromotion) {
						promorate =  wem_itr.getPromorate();
					}
					temp_playerid=(Map<String, Object>) temp_user_map.get("player_id_total");
					temp_playerid.put("report_topup", Double(temp_playerid.get("report_topup"))+topup_res.getAmount());
					temp_playerid.put("report_frequent", Double(temp_playerid.get("report_frequent"))+1);
					temp_playerid.put("report_bonus", Double(temp_playerid.get("report_bonus"))+topup_res.getBonus());
					temp_promotion.put("promotion_rate" , promorate);
					temp_promotion.put("topup", topup_res.getAmount());
					temp_promotion.put("totalbonus_redem", topup_res.getBonus());
					temp_promotion.put("frequent", frequent);
					temp_promotion_list.add(temp_promotion);
					temp_user_map.put("promotions", temp_promotion_list);
					temp_user_map.put("player_id_total", temp_playerid);
					result_list.set(user_index, temp_user_map);
					
				}
				
				
			
				/*if(promotion_index_none != -1) {
					temp_promotion = temp_promotion_list.get(promotion_index_none);
//					temp_promotion1 = temp_promotion_list1.get(promotion_index_none);
					temp_promotion.put("topup", Double(temp_promotion.get("topup"))+topup_res.getAmount());
					temp_promotion.put("frequent", Double(temp_promotion.get("frequent"))+1);
					temp_promotion.put("totalbonus_redem", Double(temp_promotion.get("totalbonus_redem"))+topup_res.getBonus());
//					temp_user_map.put("total_top_up", Double(temp_user_map.get("total_top_up"))+topup_res.getAmount());
//					temp_promotion1.put("total_top_up", Double(temp_promotion.get("total_top_up"))+topup_res.getAmount());
					
				}else if(promotion_index != -1) {
					
					
					
					
					Map <String , Object> temp_user_map_newuserid = new HashMap<>();
					temp_user_map.put("user_id", topup_res.getUserid());
					Double totaltopupamount = wettopupService.getamountbyuserid(wettopup.getDateOfissue(), wettopup.getTodate(), topup_res.getUserid(), wettopup.getCompanyid1());
					temp_user_map.put("total_top_up", totaltopupamount);
					List<Map<String , Object>> temp_promotion_list__newuserid = new ArrayList<>();
					Map<String , Object> temp_promotion__newuserid = new HashMap<>();
					
					Prmotion is null
					 
					if(topup_res.getPromotioncd() == null) {
						temp_promotion.put("promotion_name" , "None");
					}else {
						temp_promotion.put("promotion_name" , topup_res.getPromotioncd());
						temp_promotion.put("totalbonus_redem", topup_res.getBonus());
						temp_promotion.put("frequent", frequent);
						List<WemPromotion> wemPromotion = wempromoService.getwempromobypromoname(topup_res.getPromotioncd(), wettopup.getCompanyid1());
						for(WemPromotion wem_itr: wemPromotion) {
							promorate =  wem_itr.getPromorate();
						}
						temp_promotion.put("promotion_rate" , promorate);
					}
					
					
					temp_promotion.put("topup", topup_res.getAmount());
					//temp_promotion.put("total_top_up", topup_res.getAmount());
					
					//Append tempp_promotion in list
					
					temp_promotion_list.add(temp_promotion);
					
					//add promotion list to map
					
					temp_user_map.put("promotions", temp_promotion_list);
					
					//Add temp_user_map to Result_list
					
					result_list.add(temp_user_map);
					
				}*/
				
				
			}
			
			
		}else {
			
			/**
			 * user_id not found
			 */
			
			
			Map <String , Object> temp_user_map = new HashMap<>();
			temp_user_map.put("user_id", topup_res.getUserid());
			temp_user_map.put("total_top_up", topup_res.getAmount());
			Double totaltopupamount = wettopupService.getamountbyuserid(wettopup.getDateOfissue(), wettopup.getTodate(), topup_res.getUserid(), wettopup.getCompanyid1());
			Double totalwithdrawupamount = wetwithdrawService.getamountbyuseridinwithdraw(wettopup.getDateOfissue(), wettopup.getTodate(), topup_res.getUserid(), wettopup.getCompanyid1());
			if(totaltopupamount == null) {
				totaltopupamount = 0.0;
			}
			
          if(totalwithdrawupamount == null) {
	          totalwithdrawupamount = 0.0;
			}
			temp_user_map.put("total_top_up", totaltopupamount);
			temp_user_map.put("total_withdraw", totalwithdrawupamount);
			Double winlose = totaltopupamount - totalwithdrawupamount;
			temp_user_map.put("winlose", winlose);
			List<Map<String , Object>> temp_promotion_list = new ArrayList<>();
			Map<String , Object> temp_promotion = new HashMap<>();
			Map<String , Object> temp_playerid_total = new HashMap<>();
			
			/**
			 * Prmotion is null
			 */
			if(topup_res.getPromotioncd() == null) {
				temp_promotion.put("promotion_name" , "None");
			}else {
				temp_promotion.put("promotion_name" , topup_res.getPromotioncd());
				List<WemPromotion> wemPromotion = wempromoService.getwempromobypromoname(topup_res.getPromotioncd(), wettopup.getCompanyid1());
				for(WemPromotion wem_itr: wemPromotion) {
					promorate =  wem_itr.getPromorate();
				}
				temp_promotion.put("promotion_rate" , promorate);
			}
			
			
			
			
			temp_promotion.put("topup", topup_res.getAmount());
			temp_promotion.put("totalbonus_redem", topup_res.getBonus());
			temp_promotion.put("frequent", frequent);
			temp_playerid_total.put("report_topup",topup_res.getAmount());
			temp_playerid_total.put("report_bonus", topup_res.getBonus());
			temp_playerid_total.put("report_frequent", frequent);
			//temp_promotion.put("total_top_up", topup_res.getAmount());
			
			//Append tempp_promotion in list
			
			temp_promotion_list.add(temp_promotion);
			
			//add promotion list to map
			
			temp_user_map.put("promotions", temp_promotion_list);
			temp_user_map.put("player_id_total", temp_playerid_total);
			
			//Add temp_user_map to Result_list
			
			result_list.add(temp_user_map);
			
			
		}
		
		
		
		
	}
	
/*	for(Wettopup trans_itr: wettopuppromo) {
		
		if(!promo_result.isEmpty()) {
			
			for(Entry<String, List<Map<String, Object>>> k : promo_result.entrySet()) {
				
				for(Map<String, Object> k_itr : k.getValue()) {
				
				if(k.getKey().equals(trans_itr.getUserid())){
					 Map<String, Object> m1 = (Map<String, Object>) promo_result.get(k.getValue());

					    for(Entry<String, Object> k1 : k_itr.entrySet()) {
					    	if(k_itr.containsKey("promoname") && k_itr.containsValue(trans_itr.getPromotioncd())) {
					    		
					    		k_itr.put("topup", Double(k_itr.get("topup")) + trans_itr.getAmount());
					    		k_itr.put("totaltopup", Double(k_itr.get("totaltopup")) + trans_itr.getAmount());
					    		k_itr.put("frequent", Double(k_itr.get("frequent")) + 1);
					    	}
					    	
					    
					    }
					
				}else {
					
					if(trans_itr.getPromotioncd() != null) {
						promo_map.put("promoname", trans_itr.getPromotioncd());
						promo_map.put("promorate", 5);
						promo_map.put("topup", trans_itr.getAmount());
						promo_map.put("totaltopup", trans_itr.getAmount());
						promo_map.put("frequent", 1);
						}else {
							promo_map.put("promoname", null);
							promo_map.put("promorate", 5);
							promo_map.put("topup", 0);
							promo_map.put("totaltopup", trans_itr.getAmount());
							promo_map.put("frequent", 1);
						}
					promo_totalamount.add(promo_map);
						promo_result.put(trans_itr.getUserid(), promo_totalamount);
					
				}
			   
			}
			}
			
		}else {
			if(trans_itr.getPromotioncd() != null) {
				promo_map.put("promoname", trans_itr.getPromotioncd());
				promo_map.put("promorate", 5);
				promo_map.put("topup", trans_itr.getAmount());
				promo_map.put("totaltopup", trans_itr.getAmount());
				promo_map.put("frequent", 1);
			}else {
				promo_map.put("promoname", null);
				promo_map.put("promorate", 5);
				promo_map.put("topup", 0);
				promo_map.put("totaltopup", trans_itr.getAmount());
				promo_map.put("frequent", 1);
			}
			promo_totalamount.add(promo_map);
			promo_result.put(trans_itr.getUserid(), promo_totalamount);
			
			
		}
		
		
	}*/
	
	
	
		}
	
	
	
    
    
    
    /**
     * Formatting data based on datatables
     */
    
    
    List<Map<String , Object>> formatted_result = new ArrayList<>();
    
    
    for(Map<String , Object> user : result_list ) {
    	List <Map<String , Object>> temp_promotion_list = (List<Map<String, Object>>) user.get("promotions");
    	int i=0;
    	
    	for(Map<String , Object> promotion : temp_promotion_list) {
    		Map<String , Object> temp_data_obj = new HashMap<>();
    		if(i==0)
    			temp_data_obj.put("player_id" , user.get("user_id"));
    		else
    			temp_data_obj.put("player_id" , "");
    		
    		temp_data_obj.put("promotion_name", promotion.get("promotion_name"));
    		temp_data_obj.put("promotion_rate", promotion.get("promotion_rate"));
    		temp_data_obj.put("topup", promotion.get("topup"));
    		temp_data_obj.put("totalbonus_redem", promotion.get("totalbonus_redem"));
    		temp_data_obj.put("frequent", promotion.get("frequent"));
    		temp_data_obj.put("total_top_up","");
    		temp_data_obj.put("total_withdraw","");
    		temp_data_obj.put("winlose","");
    		
    		formatted_result.add(temp_data_obj);
    		
    	}
    	Map<String , Object> group_row = new HashMap<>();
    	
    	
    	group_row.put("player_id" , "Player ID Total:");
		
		
    	group_row.put("promotion_name", "");
    	group_row.put("promotion_rate", "");
    	group_row.put("topup", ((Map<String, Object>) user.get("player_id_total")).get("report_topup"));
    	group_row.put("totalbonus_redem",((Map<String, Object>) user.get("player_id_total")).get("report_bonus"));
    	group_row.put("frequent",((Map<String, Object>) user.get("player_id_total")).get("report_frequent"));
		group_row.put("total_top_up",user.get("total_top_up"));
		group_row.put("total_withdraw",user.get("total_withdraw"));
		group_row.put("winlose",user.get("winlose"));
		formatted_result.add(group_row);
    	
    }
    
    response.put("code", HttpStatus.OK);
    response.put("msg", "All Unclaim List by promotion");
    response.put("data", formatted_result);
    
	return new ResponseEntity<Object>(response, HttpStatus.OK);
		
		
	}

	private Double Double(Object object) {
		Double val = null;
	    if (object instanceof Number) {
	        val = ((Number) object).doubleValue();
	    }
	    return val;
	}
	
	
	
}
