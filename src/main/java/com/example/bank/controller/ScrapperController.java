package com.example.bank.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.bank.bean.Newplayerregbean;
import com.example.bank.model.CmcProduct;
import com.example.bank.model.KioskDeposit;
import com.example.bank.model.KioskWithdraw;
import com.example.bank.model.MainCompany;
import com.example.bank.model.MasterKiosk;
import com.example.bank.model.TmpPlayer;
import com.example.bank.service.CmcproductService;
import com.example.bank.service.KioskDepositService;
import com.example.bank.service.KioskWithdrawService;
import com.example.bank.service.MasterKioskService;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WemplayerService;
import com.example.bank.service.WettopupService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api/")
public class ScrapperController {
	
	@Autowired
	WemplayerService wemplayerService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	MasterKioskService masterkioskService;
	
	@Autowired
	CmcproductService cmcproductService;
	
	@Autowired
	KioskDepositService kioskdepositService;
	
	@Autowired
	KioskWithdrawService kioskwithdrawService;
	
	@PostMapping("creditin")
	public ResponseEntity<Object> creditin(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<TmpPlayer> tmpplayer = new ArrayList<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
	          tmpplayer = tmpplayerService.checkuserid(newplayerreg.getProductid(), newplayerreg.getUserid(), newplayerreg.getCompanyid1());
			
		if(tmpplayer.size() == 0) {
			
			response.put("code", HttpStatus.BAD_REQUEST);
		    response.put("msg", "playerid or product id not exist");
		    response.put("data", null);
		    
			
		}else {
			
			List<CmcProduct> cmcproduct = cmcproductService.getclosing(newplayerreg.getCompanyid1(), newplayerreg.getProductid());
			
			Integer productid = cmcproduct.get(0).getProduct_id();
			
			List<MasterKiosk> masterkiosk = masterkioskService.getmasterkiosk(newplayerreg.getCompanyid1(), productid);
			
			String username = masterkiosk.get(0).getUsername();
			
			String password = masterkiosk.get(0).getPassword();
			
			Newplayerregbean reqbody = new Newplayerregbean();
			
			reqbody.setKiosk(newplayerreg.getProductid());
			reqbody.setAmount(newplayerreg.getAmount());
			reqbody.setUsername(username);
			reqbody.setPassword(password);
			reqbody.setPlayerid(newplayerreg.getUserid());
			
			Map request = new HashMap<>();
			
			request.put("playerId", newplayerreg.getUserid());
			request.put("amount", newplayerreg.getAmount().intValue());
			request.put("username", username);
			request.put("password",password);
			request.put("kiosk", newplayerreg.getProductid());
			
			
			System.out.println(request);
			
			final String uri = "http://132.148.22.127:3002/api/kiosk/deposit/transact";

		    RestTemplate restTemplate = new RestTemplate();
		    
		    System.out.println(restTemplate.postForEntity(uri, request, String.class));
		    
		    ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		    System.out.println(result);
		    
		    System.out.println(result.getBody());
		    
		    System.out.println(result.getBody().indexOf("stdout"));
		    
		    if(result.getBody().equals("\"stdout\":\"Successful transaction\\r\\n\"")) {
		    	
		    	KioskDeposit kioskdeposit = new KioskDeposit();
		    	
		    	kioskdeposit.setUsername(username);
		    	kioskdeposit.setPassword(password);
		    	kioskdeposit.setAmount(newplayerreg.getAmount());
		    	
		    	TmpPlayer tmPlayer = tmpplayer.get(0);
		    	
		    	CmcProduct cmcProduct = cmcproduct.get(0);
		    	
		    	
		    	kioskdeposit.setUserid(tmPlayer);
		    	
		    	kioskdeposit.setProductid(cmcProduct);
		    	
		    	kioskdeposit.setScraperresp("Successful transaction");
		    	
		    	MainCompany maincompany = new MainCompany();
				maincompany.setId(newplayerreg.getCompanyid1());
				
				kioskdeposit.setCompanyid(maincompany);
		    	
		    	kioskdepositService.savekioskdeposit(kioskdeposit);
		    	
		    	
				response.put("code", HttpStatus.OK);
			    response.put("msg", "Successful transaction");
			    response.put("data", kioskdeposit);
			    
		
		    	
		    	
		    }else if(result.getBody().equals("\"stdout\":\"Unable to add credit\\r\\n\"")){
		    	
	              KioskDeposit kioskdeposit = new KioskDeposit();
		    	
		    	kioskdeposit.setUsername(username);
		    	kioskdeposit.setPassword(password);
		    	kioskdeposit.setAmount(newplayerreg.getAmount());
		    	
		    	TmpPlayer tmPlayer = tmpplayer.get(0);
		    	
		    	CmcProduct cmcProduct = cmcproduct.get(0);
		    	
		    	
		    	kioskdeposit.setUserid(tmPlayer);
		    	
		    	kioskdeposit.setProductid(cmcProduct);
		    	
		    	kioskdeposit.setScraperresp("Unable to add credit");
		    	
		    	MainCompany maincompany = new MainCompany();
				maincompany.setId(newplayerreg.getCompanyid1());
				
				kioskdeposit.setCompanyid(maincompany);
		    	
		    	kioskdepositService.savekioskdeposit(kioskdeposit);
		    	
		    	response.put("code", HttpStatus.OK);
			    response.put("msg", "Unable to add credit");
			    response.put("data", kioskdeposit);
			    
		    	
		    }else {
		    	
                KioskDeposit kioskdeposit = new KioskDeposit();
		    	
		    	kioskdeposit.setUsername(username);
		    	kioskdeposit.setPassword(password);
		    	kioskdeposit.setAmount(newplayerreg.getAmount());
		    	
		    	TmpPlayer tmPlayer = tmpplayer.get(0);
		    	
		    	CmcProduct cmcProduct = cmcproduct.get(0);
		    	
		    	
		    	kioskdeposit.setUserid(tmPlayer);
		    	
		    	kioskdeposit.setProductid(cmcProduct);
		    	
		    	
		    	MainCompany maincompany = new MainCompany();
				maincompany.setId(newplayerreg.getCompanyid1());
				
				kioskdeposit.setCompanyid(maincompany);
		    	
		    	kioskdeposit.setScraperresp(result.getBody());
		    	
		    	kioskdepositService.savekioskdeposit(kioskdeposit);
		    	
		    	response.put("code", HttpStatus.OK);
			    response.put("msg", "failed");
			    response.put("data", kioskdeposit);
			    
				
		    	
		    	
		    }
		    
		   
			
		}
		
			
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
		}
	
	
	@PostMapping("creditout")
	public ResponseEntity<Object> creditout(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<TmpPlayer> tmpplayer = new ArrayList<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
	          tmpplayer = tmpplayerService.checkuserid(newplayerreg.getProductid(), newplayerreg.getUserid(), newplayerreg.getCompanyid1());
			
		if(tmpplayer.size() == 0) {
			
			response.put("code", HttpStatus.BAD_REQUEST);
		    response.put("msg", "playerid or product id not exist");
		    response.put("data", null);
		    
			
		}else {
			
			List<CmcProduct> cmcproduct = cmcproductService.getclosing(newplayerreg.getCompanyid1(), newplayerreg.getProductid());
			
			Integer productid = cmcproduct.get(0).getProduct_id();
			
			List<MasterKiosk> masterkiosk = masterkioskService.getmasterkiosk(newplayerreg.getCompanyid1(), productid);
			
			String username = masterkiosk.get(0).getUsername();
			
			String password = masterkiosk.get(0).getPassword();
			
			Newplayerregbean reqbody = new Newplayerregbean();
			
			reqbody.setKiosk(newplayerreg.getProductid());
			reqbody.setAmount(newplayerreg.getAmount());
			reqbody.setUsername(username);
			reqbody.setPassword(password);
			reqbody.setPlayerid(newplayerreg.getUserid());
			
			Map request = new HashMap<>();
			
			request.put("playerId", newplayerreg.getUserid());
			request.put("amount", newplayerreg.getAmount().intValue());
			request.put("username", username);
			request.put("password",password);
			request.put("kiosk", newplayerreg.getProductid());
			
			
			System.out.println(request);
			
			final String uri = "http://132.148.22.127:3002/api/kiosk/withdrawal/transact";

		    RestTemplate restTemplate = new RestTemplate();
		    
		    System.out.println(restTemplate.postForEntity(uri, request, String.class));
		    
		    ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		    System.out.println(result);
		    
		    System.out.println(result.getBody());
		    
		    Gson gson = new Gson();

		      //convert java object to JSON format
		      String json = gson.toJson(result.getBody());

		      if(json.contains("Unable to add credit")){
		    	     System.out.println("if part==========================");
		    	     System.out.println(json.contains("Unable to add credit"));
		      }else {
		    	  System.out.println("else part==========================");
		    	     System.out.println(json.contains("Not Unable to add credit"));
		      }
		     
		      
		    System.out.println(result.getBody().indexOf("stdout"));
		    
		    if(result.getBody().equals("\"stdout\":\"Successful transaction\\r\\n\"")) {
		    	
		    	KioskWithdraw kioskdeposit = new KioskWithdraw();
		    	
		    	kioskdeposit.setUsername(username);
		    	kioskdeposit.setPassword(password);
		    	kioskdeposit.setAmount(newplayerreg.getAmount());
		    	
		    	TmpPlayer tmPlayer = tmpplayer.get(0);
		    	
		    	CmcProduct cmcProduct = cmcproduct.get(0);
		    	
		    	
		    	kioskdeposit.setUserid(tmPlayer);
		    	
		    	kioskdeposit.setProductid(cmcProduct);
		    	
		    	kioskdeposit.setScraperresp("Successful transaction");
		    	
		    	MainCompany maincompany = new MainCompany();
				maincompany.setId(newplayerreg.getCompanyid1());
				
				kioskdeposit.setCompanyid(maincompany);
		    	
				kioskwithdrawService.savekioskwithdraw(kioskdeposit);
		    	
		    	
				response.put("code", HttpStatus.OK);
			    response.put("msg", "Successful transaction");
			    response.put("data", kioskdeposit);
			    
		
		    	
		    	
		    }else if(result.getBody().equals("\"stdout\":\"Unable to add credit\\r\\n\"")){
		    	
		    	KioskWithdraw kioskdeposit = new KioskWithdraw();
		    	
		    	kioskdeposit.setUsername(username);
		    	kioskdeposit.setPassword(password);
		    	kioskdeposit.setAmount(newplayerreg.getAmount());
		    	
		    	TmpPlayer tmPlayer = tmpplayer.get(0);
		    	
		    	CmcProduct cmcProduct = cmcproduct.get(0);
		    	
		    	
		    	kioskdeposit.setUserid(tmPlayer);
		    	
		    	kioskdeposit.setProductid(cmcProduct);
		    	
		    	kioskdeposit.setScraperresp("Unable to add credit");
		    	
		    	MainCompany maincompany = new MainCompany();
				maincompany.setId(newplayerreg.getCompanyid1());
				
				kioskdeposit.setCompanyid(maincompany);
		    	
				kioskwithdrawService.savekioskwithdraw(kioskdeposit);
		    	
		    	response.put("code", HttpStatus.OK);
			    response.put("msg", "Unable to add credit");
			    response.put("data", kioskdeposit);
			    
		    	
		    }else {
		    	
                KioskWithdraw kioskdeposit = new KioskWithdraw();
		    	
		    	kioskdeposit.setUsername(username);
		    	kioskdeposit.setPassword(password);
		    	kioskdeposit.setAmount(newplayerreg.getAmount());
		    	
		    	TmpPlayer tmPlayer = tmpplayer.get(0);
		    	
		    	CmcProduct cmcProduct = cmcproduct.get(0);
		    	
		    	
		    	kioskdeposit.setUserid(tmPlayer);
		    	
		    	kioskdeposit.setProductid(cmcProduct);
		    	
		    	
		    	MainCompany maincompany = new MainCompany();
				maincompany.setId(newplayerreg.getCompanyid1());
				
				kioskdeposit.setCompanyid(maincompany);
		    	
		    	kioskdeposit.setScraperresp(result.getBody());
		    	
		    	kioskwithdrawService.savekioskwithdraw(kioskdeposit);
		    	
		    	response.put("code", HttpStatus.OK);
			    response.put("msg", "failed");
			    response.put("data", kioskdeposit);
			    
				
		    	
		    	
		    }
		    
		   
			
		}
		
			
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
		}

}
