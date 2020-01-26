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
import com.example.bank.model.Wettopup;
import com.example.bank.service.CmcproductService;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class TopwlplayerController {
	

	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	WetwithdrawService wetwthdrawService;
	
	@Autowired
	CmcproductService cmcproductService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	@PostMapping("topwlplayer")
	public ResponseEntity<Object> topwlplayer(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		Double topups = 0.0;
		Double withdraws = 0.0;
		String playername = null;
		String telno = null;
		
		if(newplayerreg.getCompanyid1() !=null) {
			
			String product = newplayerreg.getProduct().get(0);
			
			List<String> temp_product_list = new ArrayList<>();
			
			if(product == null) {
				List<CmcProduct> productlist = cmcproductService.getallproductbycompany(newplayerreg.getCompanyid1());
				for(CmcProduct productlist_itr: productlist) {
					temp_product_list.add(productlist_itr.getFldesc());
				}
			}else {
				temp_product_list = newplayerreg.getProduct();
			}
			
		
			List<Wettopup> topwlplayers = wettopupService.topwlplayer(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1(), temp_product_list);
			
			
			Iterator itr = topwlplayers.iterator();
			while(itr.hasNext()){
			
				
				 Object[] obj = (Object[]) itr.next();
			
				
				Map <String , Object> temp_topwlplayer_list = new HashMap<>();
				
				Double topwlplayer_topup = wettopupService.topwlplayer_topup(String.valueOf(obj[0]), newplayerreg.getCompanyid1(),String.valueOf(obj[1]), newplayerreg.getDateOfissue(), newplayerreg.getTodate());
				
				Double topwlplayer_withdraw = wetwthdrawService.topwlplayer_withdraw(String.valueOf(obj[0]), newplayerreg.getCompanyid1(),String.valueOf(obj[1]), newplayerreg.getDateOfissue(), newplayerreg.getTodate());
				
				if(topwlplayer_topup == null || topwlplayer_topup == 0.0) {
					topwlplayer_topup = 0.0;
				}
				
				if(topwlplayer_withdraw == null || topwlplayer_withdraw == 0.0) {
					topwlplayer_withdraw = 0.0;
				}
				
				List<String> playerandtel = tmpplayerService.playername(String.valueOf(obj[0]),String.valueOf(obj[1]), newplayerreg.getCompanyid1());
				
				Iterator playerandtel_itr = playerandtel.iterator();
				
				while(playerandtel_itr.hasNext()) {
					 Object[] playerandtel_obj = (Object[]) playerandtel_itr.next();
					 playername = String.valueOf(playerandtel_obj[0]);
					 telno = String.valueOf(playerandtel_obj[1]);
				}
				
				temp_topwlplayer_list.put("userid", String.valueOf(obj[0]));
				temp_topwlplayer_list.put("telno", telno);
				temp_topwlplayer_list.put("product", String.valueOf(obj[1]));
				temp_topwlplayer_list.put("topup", topwlplayer_topup);
				temp_topwlplayer_list.put("withdraw", topwlplayer_withdraw);
				temp_topwlplayer_list.put("wl", topwlplayer_topup - topwlplayer_withdraw);
				
				
				
				result_list.add(temp_topwlplayer_list);
				
			}
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Top WL data");
	    response.put("data", result_list);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
