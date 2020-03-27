package com.example.bank.controller;

import java.math.BigInteger;
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
import com.example.bank.model.MainCompany;
import com.example.bank.model.Profiledata;
import com.example.bank.model.TmpPlayer;
import com.example.bank.model.WemPlayer;
import com.example.bank.model.Wettopup;
import com.example.bank.model.Wetwithdraw;
import com.example.bank.service.CmcproductService;
import com.example.bank.service.CmcsourcesService;
import com.example.bank.service.ProfiledataService;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WemplayerService;
import com.example.bank.service.WempromotionService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class ProfiledataController {
	
	@Autowired
	ProfiledataService profiledataService;
	
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
	
	
	@PostMapping("profiledata-gen")
	public ResponseEntity<Object> playerinfo(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>(); 
	
		List<Map<String , Object>> result_list = new ArrayList<>();
		List<Map<String , Object>> result_list_non_topup = new ArrayList<>();
		List<Map<String , Object>> result_list_re_topup = new ArrayList<>();
		Map <String , Object> summary = new HashMap<>();
		
		List<String> useridlist = null;
		List<String> useridlist_topup = null;
		List<Integer> wemplayeridlist = new ArrayList<>();
		int topupplayer_count = 0;
		int nontopupplayer_count = 0;
		int retopup_count = 0;
		Double totaltopup = 0.0;
		Double withdrawtopup = 0.0;
		
		List<WemPlayer> wemplayer = new ArrayList<>();

		if(newplayerreg.getCompanyid1() != null) {
		/*String product = newplayerreg.getProduct().get(0);
		String contactsources = newplayerreg.getContactsources().get(0);
		String playername = newplayerreg.getPlayername().get(0);*/
		
		List<String> temp_product_list = new ArrayList<>();
		List<String> temp_contsources_list = new ArrayList<>();
		
		
		wemplayer = wemplayerService.getallprofiledatarbycompanyid(newplayerreg.getCompanyid1());
		
		
		Profiledata profiledata = new Profiledata();
		
		Iterator itr = wemplayer.iterator();
		while(itr.hasNext()){
			
			   Object[] obj = (Object[]) itr.next();
			   
			   profiledata.setFlname(String.valueOf(obj[1]));
			   profiledata.setTelno(String.valueOf(obj[2]));
			   profiledata.setBankacc(String.valueOf(obj[3]));
			   
			   Double balance = 0.0;
			   List<TmpPlayer> tmpplayer = tmpplayerService.getuseridbyplayer(newplayerreg.getCompanyid1(), Integer.parseInt(String.valueOf(obj[0])));
			   
			   useridlist = new ArrayList<>();
			  
			 
			   for(TmpPlayer tmpplayer_itr : tmpplayer) {
				   Map<String, Object> temp_player_info = new HashMap<>();
				   
				   useridlist.add(tmpplayer_itr.getUserid());
				   
			   }
			   
			   if(! useridlist.isEmpty()) {
					Double vipamount = wettopupService.vipamount(useridlist, newplayerreg.getCompanyid1());
					
					
					 if(vipamount != null) {
						totaltopup = vipamount;
					 }else {
						 totaltopup =0.0;
					 }
					
					
						profiledata.setTotalin(totaltopup);
					
					
					
					 withdrawtopup = wetwithdrawService.withdrawamount_profiledata(useridlist, newplayerreg.getCompanyid1());
						
					 
				
					 if(withdrawtopup == null) {
						 withdrawtopup = 0.0;
					 }
					 
					 profiledata.setTotalout(withdrawtopup);
					 
					 
					 profiledata.setWl(totaltopup - withdrawtopup);
					 
					 MainCompany maincompany = new MainCompany();
					 
					 maincompany.setId(newplayerreg.getCompanyid1());
					 
					 profiledata.setCompanyid(maincompany);
			   
					 profiledataService.saveprofiledta(profiledata);
			  
			      }
			   }
		}
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Profile data generated");
	    response.put("data", result_list);
	    response.put("datasize", wemplayer.size());
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
