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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.bank.bean.Newplayerregbean;
import com.example.bank.model.CmcProduct;
import com.example.bank.model.Cmcbank;
import com.example.bank.model.Cmccombank;
import com.example.bank.model.KioskDeposit;
import com.example.bank.model.KioskWithdraw;
import com.example.bank.model.MainCompany;
import com.example.bank.model.MasterDeposit;
import com.example.bank.model.MasterKiosk;
import com.example.bank.model.MasterWithdraw;
import com.example.bank.model.Removevsadd;
import com.example.bank.model.ScrapperWithdrawal;
import com.example.bank.model.TmpPlayer;
import com.example.bank.repository.MasterDepositRepository;
import com.example.bank.repository.RemovevsaddRepository;
import com.example.bank.service.CmcBankService;
import com.example.bank.service.CmcCombankSerrvice;
import com.example.bank.service.CmcproductService;
import com.example.bank.service.KioskDepositService;
import com.example.bank.service.KioskWithdrawService;
import com.example.bank.service.MasterDepositService;
import com.example.bank.service.MasterKioskService;
import com.example.bank.service.MasterWithdrawService;
import com.example.bank.service.RemovevsaddService;
import com.example.bank.service.ScrapperWithdrawalService;
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
	
	@Autowired
	MasterDepositService masterdepositService;
	
	@Autowired
	CmcCombankSerrvice cmccombankService;
	
	@Autowired
	MasterWithdrawService masterwithdrawService;
	
	@Autowired
	RemovevsaddService removevsaddService;
	
	@Autowired
	CmcBankService cmcbankService;
	
	@Autowired
	ScrapperWithdrawalService scrapperwithdrawalService;
	
	@PostMapping("creditin")
	public ResponseEntity<Object> creditin(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<TmpPlayer> tmpplayer = new ArrayList<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
	       /*   tmpplayer = tmpplayerService.checkuserid(newplayerreg.getProductid(), newplayerreg.getUserid(), newplayerreg.getCompanyid1());
			
		if(tmpplayer.size() == 0) {
			
			response.put("code", HttpStatus.BAD_REQUEST);
		    response.put("msg", "playerid or product id not exist");
		    response.put("data", null);
		    
			
		}else {*/
			
			List<CmcProduct> cmcproduct = cmcproductService.getclosing(newplayerreg.getCompanyid1(), newplayerreg.getProductid());
			
			Integer productid = cmcproduct.get(0).getProduct_id();
			
			List<MasterKiosk> masterkiosk = masterkioskService.getmasterkiosk(newplayerreg.getCompanyid1(), productid);
			
			if(masterkiosk.size() == 0) {
				
				response.put("code", HttpStatus.BAD_REQUEST);
			    response.put("msg", "username and password not registered for productid");
			    response.put("data", null);
				
			}else {
				
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
				
				final String uri = "http://18.138.22.201:3002/api/kiosk/deposit/transact";

			    RestTemplate restTemplate = new RestTemplate();
			    
			  /*  System.out.println(restTemplate.postForEntity(uri, request, String.class));*/
			    
			    ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

			    System.out.println(result);
			    
			    System.out.println(result.getBody());
			    
			    System.out.println(result.getBody().indexOf("stdout"));
			    
			   /* if(result.getBody().equals("\"stdout\":\"Successful transaction\\r\\n\"")) {*/
			    	
			    	KioskDeposit kioskdeposit = new KioskDeposit();
			    	
			    	kioskdeposit.setUsername(username);
			    	kioskdeposit.setPassword(password);
			    	kioskdeposit.setAmount(newplayerreg.getAmount());
			    	
			    	/*TmpPlayer tmPlayer = tmpplayer.get(0);*/
			    	
			    	CmcProduct cmcProduct = cmcproduct.get(0);
			    	
			    	
			    	kioskdeposit.setUserid(newplayerreg.getUserid());
			    	
			    	kioskdeposit.setProductid(cmcProduct);
			    	
			    	kioskdeposit.setScraperresp(result.getBody());
			    	
			    	MainCompany maincompany = new MainCompany();
					maincompany.setId(newplayerreg.getCompanyid1());
					
					kioskdeposit.setCompanyid(maincompany);
			    	
			    	kioskdepositService.savekioskdeposit(kioskdeposit);
			    	
			    	
					response.put("code", HttpStatus.OK);
				    response.put("msg", result.getBody());
				    response.put("data", kioskdeposit);
				    
			
			    	
			    /*	
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
				    
					
			    	
			    	
			    }*/
				
				
				
			/*}*/
			
			
		    
		   
			
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
			
	       /*   tmpplayer = tmpplayerService.checkuserid(newplayerreg.getProductid(), newplayerreg.getUserid(), newplayerreg.getCompanyid1());
			
		if(tmpplayer.size() == 0) {
			
			response.put("code", HttpStatus.BAD_REQUEST);
		    response.put("msg", "playerid or product id not exist");
		    response.put("data", null);
		    
			
		}else {*/
			
			List<CmcProduct> cmcproduct = cmcproductService.getclosing(newplayerreg.getCompanyid1(), newplayerreg.getProductid());
			
			Integer productid = cmcproduct.get(0).getProduct_id();
			
			List<MasterKiosk> masterkiosk = masterkioskService.getmasterkiosk(newplayerreg.getCompanyid1(), productid);
			
			if(masterkiosk.size() == 0) {
				
				response.put("code", HttpStatus.BAD_REQUEST);
			    response.put("msg", "username and password not registered for productid");
			    response.put("data", null);
				
				
			}else {
				
				

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
				
				final String uri = "http://18.138.22.201:3002/api/kiosk/withdrawal/transact";

			    RestTemplate restTemplate = new RestTemplate();
			    
			    /*System.out.println(restTemplate.postForEntity(uri, request, String.class));*/
			    
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
			    	
			    	/*TmpPlayer tmPlayer = tmpplayer.get(0);*/
			    	
			    	CmcProduct cmcProduct = cmcproduct.get(0);
			    	
			    	
			    	kioskdeposit.setUserid(newplayerreg.getUserid());
			    	
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
			    	
			    	/*TmpPlayer tmPlayer = tmpplayer.get(0);*/
			    	
			    	CmcProduct cmcProduct = cmcproduct.get(0);
			    	
			    	
			    	kioskdeposit.setUserid(newplayerreg.getUserid());
			    	
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
			    	
			    	/*TmpPlayer tmPlayer = tmpplayer.get(0);*/
			    	
			    	CmcProduct cmcProduct = cmcproduct.get(0);
			    	
			    	
			    	kioskdeposit.setUserid(newplayerreg.getUserid());
			    	
			    	kioskdeposit.setProductid(cmcProduct);
			    	
			    	
			    	MainCompany maincompany = new MainCompany();
					maincompany.setId(newplayerreg.getCompanyid1());
					
					kioskdeposit.setCompanyid(maincompany);
			    	
			    	kioskdeposit.setScraperresp(result.getBody());
			    	
			    	kioskwithdrawService.savekioskwithdraw(kioskdeposit);
			    	
			    	response.put("code", HttpStatus.OK);
				    response.put("msg", "failed");
				    response.put("data", kioskdeposit);
				    
					
			    	
			    	
			   /* }*/
				
				
			}
			
			
			
		    
		   
			
		}
		
			
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
		}
	
	
	@PostMapping("masterdeposit")
	public ResponseEntity<Object> banklogin(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<TmpPlayer> tmpplayer = new ArrayList<>();
		
			MasterDeposit masterdeposit = new MasterDeposit();
			
			masterdeposit.setUsername(newplayerreg.getLoginid());
		    masterdeposit.setPassword(newplayerreg.getPassword());
		    
		    MainCompany maincompany = new MainCompany();
		    
		    maincompany.setId(newplayerreg.getCompanyid());
		    
		    masterdeposit.setCompanyid(maincompany);
			
		     Cmccombank cmccombank = new Cmccombank();
		     
		     cmccombank.setId(newplayerreg.getBankid());
		     
		     masterdeposit.setCmccombank(cmccombank);
		     
		     masterdepositService.savemasterdeposit(masterdeposit);
			
		


		response.put("code", HttpStatus.OK);
	    response.put("msg", "Master deposit saved successfully");
	    response.put("data", null);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
		}
	
	
	@PostMapping("masterkiosk")
	public ResponseEntity<Object> productlogin(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<TmpPlayer> tmpplayer = new ArrayList<>();
		
			MasterKiosk masterkiosk = new MasterKiosk();
			
			masterkiosk.setUsername(newplayerreg.getLoginid());
			masterkiosk.setPassword(newplayerreg.getPassword());
		    
		    MainCompany maincompany = new MainCompany();
		    
		    maincompany.setId(newplayerreg.getCompanyid());
		    
		    masterkiosk.setCompanyid(maincompany);
			
		     CmcProduct cmcproduct = new CmcProduct();
		     
		     cmcproduct.setProduct_id(newplayerreg.getProduct_id());
		     
		     masterkiosk.setProductid(cmcproduct);
		     
		   masterkioskService.savemasterkiosk(masterkiosk);
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Master Kiosk saved successfully");
	    response.put("data", null);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
		}
	
	
	@PostMapping("masterwithdraw")
	public ResponseEntity<Object> masterwithdraw(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<TmpPlayer> tmpplayer = new ArrayList<>();
		
			MasterWithdraw masterwithdraw = new MasterWithdraw();
			
			masterwithdraw.setUsername(newplayerreg.getLoginid());
			masterwithdraw.setPassword(newplayerreg.getPassword());
		    
		    MainCompany maincompany = new MainCompany();
		    
		    maincompany.setId(newplayerreg.getCompanyid());
		    
		    masterwithdraw.setCompanyid(maincompany);
			
		     Cmccombank cmccombank = new Cmccombank();
		     
		     cmccombank.setId(newplayerreg.getBankid());
		     
		     masterwithdraw.setBankid(cmccombank);
		     
		     masterwithdrawService.savemasterwithdraw(masterwithdraw);
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Master Withdraw saved successfully");
	    response.put("data", null);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
		}
	
	@PostMapping("bankdetail")
	public ResponseEntity<Object> bankdetail(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Cmccombank> banklist = new ArrayList<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
			List<Cmccombank> combanklist = cmccombankService.getallbankbycompany(newplayerreg.getCompanyid1());
			
			for(Cmccombank cmccombank_itr : combanklist) {
				
				Cmccombank combank = new Cmccombank();
				combank.setId(cmccombank_itr.getId());
				combank.setFldesc(cmccombank_itr.getFldesc());
				banklist.add(combank);
			}
		}
			
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Bank Detail List");
	    response.put("data", banklist);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
		}
	
	
	@PostMapping("productdetail")
	public ResponseEntity<Object> productdetail(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<CmcProduct> productlist = new ArrayList<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
			List<CmcProduct> cmcproductlist = cmcproductService.getallproductbycompany(newplayerreg.getCompanyid1());
			
			for(CmcProduct cmcproductlist_itr : cmcproductlist) {
				
				CmcProduct comproduct = new CmcProduct();
				comproduct.setProduct_id(cmcproductlist_itr.getProduct_id());
				comproduct.setFldesc(cmcproductlist_itr.getFldesc());
				productlist.add(comproduct);
			}
		}
			
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Product Detail List");
	    response.put("data", productlist);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
		}
	
	
	@PostMapping("removevsadd")
	public ResponseEntity<Object> removevsadd(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<TmpPlayer> tmpplayer = new ArrayList<>();
		
			Removevsadd removevsadd = new Removevsadd();
			
			CmcProduct fromproduct = new CmcProduct();
			
			fromproduct.setProduct_id(newplayerreg.getFromproductid());
			
			removevsadd.setFromproduct(fromproduct);

			CmcProduct toproduct = new CmcProduct();
			
			toproduct.setProduct_id(newplayerreg.getToproductid());
			
			removevsadd.setToproduct(toproduct);
			
		
			removevsadd.setFromplayer(newplayerreg.getFromplayerid());
			
			removevsadd.setToplayer(newplayerreg.getToplayerid());
			
			removevsadd.setAmount(newplayerreg.getAmount());
			
			MainCompany maincompany = new MainCompany();
			
			maincompany.setId(newplayerreg.getCompanyid1());
			
			removevsadd.setCompanyid(maincompany);
		    
			removevsaddService.saveremovevsadd(removevsadd);
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Remove vs add saved successfully");
	    response.put("data", null);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
		}
	
	
	@PostMapping("banks")
	public ResponseEntity<Object> banks(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Cmccombank> banklist = new ArrayList<>();
		
		if(newplayerreg.getCompanyid1() != null) {
			
			List<Cmcbank> combanklist = cmcbankService.getallbankbycompany(newplayerreg.getCompanyid1());
			
			for(Cmcbank cmccombank_itr : combanklist) {
				
				Cmccombank combank = new Cmccombank();
				combank.setId(cmccombank_itr.getId());
				combank.setFldesc(cmccombank_itr.getFldesc());
				banklist.add(combank);
			}
		}
			
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Banks Detail List");
	    response.put("data", banklist);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
		}
	
	@PostMapping("scarpper-withdrawal")
	public ResponseEntity<Object> scrapperwithdrawal(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<TmpPlayer> tmpplayer = new ArrayList<>();
		
			ScrapperWithdrawal scrapperwithdrawal = new ScrapperWithdrawal();
			
			Cmccombank cmccombank = new Cmccombank();
			
			cmccombank.setId(newplayerreg.getBankid());
			
			scrapperwithdrawal.setCombank(cmccombank);
			
			Cmcbank cmcbank = new Cmcbank();
			
			cmcbank.setId(newplayerreg.getBid());
			
			scrapperwithdrawal.setBank(cmcbank);
			
			scrapperwithdrawal.setBankacc(newplayerreg.getBankacc());
			
			scrapperwithdrawal.setBankholder(newplayerreg.getBankholder());
			
			scrapperwithdrawal.setAmount(newplayerreg.getAmount());
			
		
			
			MainCompany maincompany = new MainCompany();
			
			maincompany.setId(newplayerreg.getCompanyid1());
			
			scrapperwithdrawal.setCompanyid(maincompany);
		    
			scrapperwithdrawalService.savescrapperwithdrawal(scrapperwithdrawal);
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Scarapper Withdrawal saved successfully");
	    response.put("data", null);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
		}
	
	@GetMapping("master-kiosk")
	public ResponseEntity<Object> masterkiosk(){
		
		Map<String, Object> response = new HashMap<>();
		
		List<MasterKiosk> masterkiosk = masterkioskService.getmasterkiosk();
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Master kioks List");
	    response.put("data", masterkiosk );
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("master-kiosk")
	public ResponseEntity<Object> deletemasterkiosk(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		
		MasterKiosk masterkiosk = new MasterKiosk();
		
		masterkiosk.setId(newplayerreg.getMasterkioskid());
		
		
		 masterkioskService.deletemasterkiosk(masterkiosk);;
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Master kioks deleted");
	    response.put("data", masterkiosk );
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PutMapping("master-kiosk")
	public ResponseEntity<Object> masterkioskupdate(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<TmpPlayer> tmpplayer = new ArrayList<>();
		
			MasterKiosk masterkiosk = new MasterKiosk();
			
			masterkiosk.setUsername(newplayerreg.getLoginid());
			masterkiosk.setPassword(newplayerreg.getPassword());
		    
		    MainCompany maincompany = new MainCompany();
		    
		    maincompany.setId(newplayerreg.getCompanyid());
		    
		    masterkiosk.setCompanyid(maincompany);
			
		     CmcProduct cmcproduct = new CmcProduct();
		     
		     cmcproduct.setProduct_id(newplayerreg.getProduct_id());
		     
		     masterkiosk.setProductid(cmcproduct);
		     
		     masterkiosk.setId(newplayerreg.getMasterkioskid());
		     
		   masterkioskService.updatemasterkiosk(masterkiosk);
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Master Kiosk updated successfully");
	    response.put("data", masterkiosk);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
		}
	
	
	@GetMapping("master-withdraw")
	public ResponseEntity<Object> masterwithdraw(){
		
		Map<String, Object> response = new HashMap<>();
		
		List<MasterWithdraw> masterwithdraw = masterwithdrawService.getmasterwithdraw();
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Master Withdraw List");
	    response.put("data", masterwithdraw );
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("master-withdraw")
	public ResponseEntity<Object> deletemasterwithdraw(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		
		MasterWithdraw masterwithdraw = new MasterWithdraw();
		
		masterwithdraw.setId(newplayerreg.getMasterwithdrawid());
		
		 masterwithdrawService.deletemasterwithdraw(masterwithdraw);
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Master kioks deleted");
	    response.put("data", masterwithdraw );
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PutMapping("master-withdraw")
	public ResponseEntity<Object> masterwithdrawupdate(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<TmpPlayer> tmpplayer = new ArrayList<>();
		
			MasterWithdraw masterwithdraw = new MasterWithdraw();
			
			masterwithdraw.setUsername(newplayerreg.getLoginid());
			masterwithdraw.setPassword(newplayerreg.getPassword());
		    
		    MainCompany maincompany = new MainCompany();
		    
		    maincompany.setId(newplayerreg.getCompanyid());
		    
		    masterwithdraw.setCompanyid(maincompany);
		    
		    Cmccombank cmccombank = new Cmccombank();
		     
		     cmccombank.setId(newplayerreg.getBankid());
		     
		     masterwithdraw.setBankid(cmccombank);;
	
		     masterwithdraw.setId(newplayerreg.getMasterwithdrawid());
		     
		     masterwithdrawService.updatemasterwithdraw(masterwithdraw);
		     
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Master Withdraw updated successfully");
	    response.put("data", masterwithdraw);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
		}
	
	@GetMapping("removevs-add")
	public ResponseEntity<Object> removevsdd(){
		
		Map<String, Object> response = new HashMap<>();
		
		List<Removevsadd> removevsadd = removevsaddService.getremovevsadd();
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Remove vs add List");
	    response.put("data", removevsadd);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("removevs-add")
	public ResponseEntity<Object> deleteremovevsadd(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		
		Removevsadd removevsadd = new Removevsadd();
		
		removevsadd.setId(newplayerreg.getRemovevsaddid());
		
		removevsaddService.deleteremovevsadd(removevsadd);
		
		 
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Remove vs add deleted");
	    response.put("data", removevsadd );
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PutMapping("removevs-add")
	public ResponseEntity<Object> updateremovevsadd(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<TmpPlayer> tmpplayer = new ArrayList<>();
		
		Removevsadd removevsadd = new Removevsadd();
		
		CmcProduct fromproduct = new CmcProduct();
		
		fromproduct.setProduct_id(newplayerreg.getFromproductid());
		
		removevsadd.setFromproduct(fromproduct);

		CmcProduct toproduct = new CmcProduct();
		
		toproduct.setProduct_id(newplayerreg.getToproductid());
		
		removevsadd.setToproduct(toproduct);
		
	
		removevsadd.setFromplayer(newplayerreg.getFromplayerid());
		
		removevsadd.setToplayer(newplayerreg.getToplayerid());
		
		removevsadd.setAmount(newplayerreg.getAmount());
		
		MainCompany maincompany = new MainCompany();
		
		maincompany.setId(newplayerreg.getCompanyid1());
		
		removevsadd.setCompanyid(maincompany);
	    
		removevsadd.setId(newplayerreg.getRemovevsaddid());
		
		removevsaddService.updateremovevsadd(removevsadd);
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Remove vs add updated successfully");
	    response.put("data", removevsadd);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
		}
	
	@GetMapping("master-deposit")
	public ResponseEntity<Object> masterdeposit(){
		
		Map<String, Object> response = new HashMap<>();
		
		List<MasterDeposit> masterdeposit = masterdepositService.getmasterdeposit();
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Master Deposit List");
	    response.put("data", masterdeposit );
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("master-deposit")
	public ResponseEntity<Object> deletemasterdeposit(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		
		MasterDeposit masterdeposit = new MasterDeposit();
		
		masterdeposit.setId(newplayerreg.getMasterdepositid());
		
		 masterdepositService.deletemasterdeposit(masterdeposit);
		 
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Master Deposit deleted");
	    response.put("data", masterdeposit );
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PutMapping("master-deposit")
	public ResponseEntity<Object> masterdepositupdate(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<TmpPlayer> tmpplayer = new ArrayList<>();
		
			MasterDeposit masterdeposit = new MasterDeposit();
			
			masterdeposit.setUsername(newplayerreg.getLoginid());
			masterdeposit.setPassword(newplayerreg.getPassword());
		    
		    MainCompany maincompany = new MainCompany();
		    
		    maincompany.setId(newplayerreg.getCompanyid());
		    
		    masterdeposit.setCompanyid(maincompany);
		    
		    Cmccombank cmccombank = new Cmccombank();
		     
		     cmccombank.setId(newplayerreg.getBankid());
		     
		     masterdeposit.setCmccombank(cmccombank);
	
		     masterdeposit.setId(newplayerreg.getMasterdepositid());
		     
		     masterdepositService.updatemasterdeposit(masterdeposit);
		     
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Master Deposit updated successfully");
	    response.put("data", masterdeposit);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
		}
	
	  
	@GetMapping("scrapper-withdrawal")
	public ResponseEntity<Object> scrapperwithdrawal(){
		
		Map<String, Object> response = new HashMap<>();
		
		List<ScrapperWithdrawal> scrapperwithdrwal = scrapperwithdrawalService.getscrapperwithdraw();
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Scrapper Withdrawal List");
	    response.put("data", scrapperwithdrwal );
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("scrapper-withdrawal")
	public ResponseEntity<Object> deletescrapperwithdrawal(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		
		ScrapperWithdrawal scrapperwithdrawal = new ScrapperWithdrawal();
		
		scrapperwithdrawal.setId(newplayerreg.getScrapperwithdrawid());
		
		scrapperwithdrawalService.deletescrapperwithdrawal(scrapperwithdrawal);
		
		 
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Scrapper Withdrawal deleted");
	    response.put("data", scrapperwithdrawal );
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PutMapping("scrapper-withdrawal")
	public ResponseEntity<Object> scrapperwithdrawalupdate(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<TmpPlayer> tmpplayer = new ArrayList<>();
		
		
      ScrapperWithdrawal scrapperwithdrawal = new ScrapperWithdrawal();
			
			Cmccombank cmccombank = new Cmccombank();
			
			cmccombank.setId(newplayerreg.getBankid());
			
			scrapperwithdrawal.setCombank(cmccombank);
			
			Cmcbank cmcbank = new Cmcbank();
			
			cmcbank.setId(newplayerreg.getBid());
			
			scrapperwithdrawal.setBank(cmcbank);
			
			scrapperwithdrawal.setBankacc(newplayerreg.getBankacc());
			
			scrapperwithdrawal.setBankholder(newplayerreg.getBankholder());
			
			scrapperwithdrawal.setAmount(newplayerreg.getAmount());
			
		
			
			MainCompany maincompany = new MainCompany();
			
			maincompany.setId(newplayerreg.getCompanyid1());
			
			scrapperwithdrawal.setCompanyid(maincompany);
			
			scrapperwithdrawal.setId(newplayerreg.getScrapperwithdrawid());
		     
		     scrapperwithdrawalService.updatescrapperwithdrawal(scrapperwithdrawal);
		     
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Scarpper Withdrawal updated successfully");
	    response.put("data", scrapperwithdrawal);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	
		}
	
	
	@GetMapping("kiosk-deposit")
	public ResponseEntity<Object> getkioskdeposit(){
		
		Map<String, Object> response = new HashMap<>();
		
		List<KioskDeposit> kioskdeposit = kioskdepositService.getkioskdeposit();
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "kiosk deposit List");
	    response.put("data", kioskdeposit );
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("kiosk-deposit")
	public ResponseEntity<Object> deletekioskdeposit(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		
		KioskDeposit kioskdeposit = new KioskDeposit();
		
		kioskdeposit.setId(newplayerreg.getKioskdepositid());
		
		kioskdepositService.deletekioskdeposit(kioskdeposit);
		
		 
		response.put("code", HttpStatus.OK);
	    response.put("msg", "kiosk deposit deleted");
	    response.put("data", kioskdeposit );
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("kiosk-withdraw")
	public ResponseEntity<Object> getkioskwithdraw(){
		
		Map<String, Object> response = new HashMap<>();
		
		List<KioskWithdraw> kioskwithdraw = kioskwithdrawService.getkioskwithdraw();
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "kiosk Withdraw List");
	    response.put("data", kioskwithdraw );
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("kiosk-withdraw")
	public ResponseEntity<Object> deletekioskwithdraw(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		
		KioskWithdraw kioskwithdraw = new KioskWithdraw();
		
		kioskwithdraw.setId(newplayerreg.getKioskwithdrawid());
		
		kioskwithdrawService.deletekioskwithdraw(kioskwithdraw);
		 
		response.put("code", HttpStatus.OK);
	    response.put("msg", "kiosk withdraw deleted");
	    response.put("data", kioskwithdraw );
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	

}
