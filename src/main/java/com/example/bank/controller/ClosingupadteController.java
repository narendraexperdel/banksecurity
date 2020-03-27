package com.example.bank.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.bean.Newplayerregbean;
import com.example.bank.model.CmcProduct;
import com.example.bank.model.MainCompany;
import com.example.bank.model.ProductClosing;
import com.example.bank.service.CmcproductService;
import com.example.bank.service.ProductClosingService;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WemplayerService;
import com.example.bank.service.WettopupService;

@RestController
@RequestMapping("/api/")
public class ClosingupadteController {
	
	@Autowired
	WemplayerService wemplayerService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	CmcproductService cmcproductService;
	
	@Autowired
	ProductClosingService productclosingService;
	
	Map<String,Object> response = new HashMap<>();
	
	@PostMapping("closing-test")
	public ResponseEntity<Object> clodingupdate(){
		
		Newplayerregbean newplayerregBean = new Newplayerregbean();
		newplayerregBean.setCompanyid1(8);
		
		List<CmcProduct> cmcproduct = cmcproductService.getallproductbycompany(newplayerregBean.getCompanyid1());
		
		for(CmcProduct cmcproduct_itr : cmcproduct) {
			
			TimeZone timeZone = TimeZone.getTimeZone("Asia/Singapore");
			
			System.out.println(timeZone);
			
			Calendar calendar =  Calendar.getInstance();
			
			calendar.setTimeZone(timeZone);
			
			calendar.add(Calendar.HOUR, 2);
			calendar.add(Calendar.MINUTE, 30);
			
			System.out.println(calendar.getTime());
			
			ProductClosing productclosing = new ProductClosing();
			
			productclosing.setDate(calendar.getTime());
			
			MainCompany maincompany = new MainCompany();
			maincompany.setId(newplayerregBean.getCompanyid1());
			
			
			productclosing.setCompanyid(maincompany);
			productclosing.setProductid(cmcproduct_itr.getFldesc());
			productclosing.setClosing(cmcproduct_itr.getClosing());
			
			productclosingService.saveproductclosing(productclosing);
			
			
		}
		
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Product Closing inserted");
	    response.put("data", null);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	
	@PostMapping("closing-update-bw")
	public ResponseEntity<Object> clodingupdatebw(){
		
		Newplayerregbean newplayerregBean = new Newplayerregbean();
		newplayerregBean.setCompanyid1(5);
		
		List<CmcProduct> cmcproduct = cmcproductService.getallproductbycompany(newplayerregBean.getCompanyid1());
		
		for(CmcProduct cmcproduct_itr : cmcproduct) {
			
			TimeZone timeZone = TimeZone.getTimeZone("Asia/Singapore");
			
			System.out.println(timeZone);
			
			Calendar calendar =  Calendar.getInstance();
			
			calendar.setTimeZone(timeZone);
			
			calendar.add(Calendar.HOUR, 2);
			calendar.add(Calendar.MINUTE, 30);
			
			System.out.println(calendar.getTime());
			
			ProductClosing productclosing = new ProductClosing();
			
			productclosing.setDate(calendar.getTime());
			
			MainCompany maincompany = new MainCompany();
			maincompany.setId(newplayerregBean.getCompanyid1());
			
			
			productclosing.setCompanyid(maincompany);
			productclosing.setProductid(cmcproduct_itr.getFldesc());
			productclosing.setClosing(cmcproduct_itr.getClosing());
			
			productclosingService.saveproductclosing(productclosing);
			
			
		}
		
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Product Closing inserted");
	    response.put("data", null);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("closing-update-sg")
	public ResponseEntity<Object> clodingupdatesg(){
		
		Newplayerregbean newplayerregBean = new Newplayerregbean();
		newplayerregBean.setCompanyid1(1);
		
		List<CmcProduct> cmcproduct = cmcproductService.getallproductbycompany(newplayerregBean.getCompanyid1());
		
		for(CmcProduct cmcproduct_itr : cmcproduct) {
			
			TimeZone timeZone = TimeZone.getTimeZone("Asia/Singapore");
			
			System.out.println(timeZone);
			
			Calendar calendar =  Calendar.getInstance();
			
			calendar.setTimeZone(timeZone);
			
			calendar.add(Calendar.HOUR, 2);
			calendar.add(Calendar.MINUTE, 30);
			
			System.out.println(calendar.getTime());
			
			ProductClosing productclosing = new ProductClosing();
			
			productclosing.setDate(calendar.getTime());
			
			MainCompany maincompany = new MainCompany();
			maincompany.setId(newplayerregBean.getCompanyid1());
			
			
			productclosing.setCompanyid(maincompany);
			productclosing.setProductid(cmcproduct_itr.getFldesc());
			productclosing.setClosing(cmcproduct_itr.getClosing());
			
			productclosingService.saveproductclosing(productclosing);
			
			
		}
		
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Product Closing inserted");
	    response.put("data", null);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("closing-update-vw")
	public ResponseEntity<Object> clodingupdatevw(){
		
		Newplayerregbean newplayerregBean = new Newplayerregbean();
		newplayerregBean.setCompanyid1(2);
		
		List<CmcProduct> cmcproduct = cmcproductService.getallproductbycompany(newplayerregBean.getCompanyid1());
		
		for(CmcProduct cmcproduct_itr : cmcproduct) {
			
			TimeZone timeZone = TimeZone.getTimeZone("Asia/Singapore");
			
			System.out.println(timeZone);
			
			Calendar calendar =  Calendar.getInstance();
			
			calendar.setTimeZone(timeZone);
			
			calendar.add(Calendar.HOUR, 2);
			calendar.add(Calendar.MINUTE, 30);
			
			System.out.println(calendar.getTime());
			
			ProductClosing productclosing = new ProductClosing();
			
			productclosing.setDate(calendar.getTime());
			
			MainCompany maincompany = new MainCompany();
			maincompany.setId(newplayerregBean.getCompanyid1());
			
			
			productclosing.setCompanyid(maincompany);
			productclosing.setProductid(cmcproduct_itr.getFldesc());
			productclosing.setClosing(cmcproduct_itr.getClosing());
			
			productclosingService.saveproductclosing(productclosing);
			
			
		}
		
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Product Closing inserted");
	    response.put("data", null);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("closing-update-vg")
	public ResponseEntity<Object> clodingupdatevg(){
		
		Newplayerregbean newplayerregBean = new Newplayerregbean();
		newplayerregBean.setCompanyid1(3);
		
		List<CmcProduct> cmcproduct = cmcproductService.getallproductbycompany(newplayerregBean.getCompanyid1());
		
		for(CmcProduct cmcproduct_itr : cmcproduct) {
			
			TimeZone timeZone = TimeZone.getTimeZone("Asia/Singapore");
			
			System.out.println(timeZone);
			
			Calendar calendar =  Calendar.getInstance();
			
			calendar.setTimeZone(timeZone);
			
			calendar.add(Calendar.HOUR, 2);
			calendar.add(Calendar.MINUTE, 30);
			
			System.out.println(calendar.getTime());
			
			ProductClosing productclosing = new ProductClosing();
			
			productclosing.setDate(calendar.getTime());
			
			MainCompany maincompany = new MainCompany();
			maincompany.setId(newplayerregBean.getCompanyid1());
			
			
			productclosing.setCompanyid(maincompany);
			productclosing.setProductid(cmcproduct_itr.getFldesc());
			productclosing.setClosing(cmcproduct_itr.getClosing());
			
			productclosingService.saveproductclosing(productclosing);
			
			
		}
		
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Product Closing inserted");
	    response.put("data", null);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("closing-update-gsc")
	public ResponseEntity<Object> clodingupdategsc(){
		
		Newplayerregbean newplayerregBean = new Newplayerregbean();
		newplayerregBean.setCompanyid1(4);
		
		List<CmcProduct> cmcproduct = cmcproductService.getallproductbycompany(newplayerregBean.getCompanyid1());
		
		for(CmcProduct cmcproduct_itr : cmcproduct) {
			
			TimeZone timeZone = TimeZone.getTimeZone("Asia/Singapore");
			
			System.out.println(timeZone);
			
			Calendar calendar =  Calendar.getInstance();
			
			calendar.setTimeZone(timeZone);
			
			calendar.add(Calendar.HOUR, 2);
			calendar.add(Calendar.MINUTE, 30);
			
			System.out.println(calendar.getTime());
			
			ProductClosing productclosing = new ProductClosing();
			
			productclosing.setDate(calendar.getTime());
			
			MainCompany maincompany = new MainCompany();
			maincompany.setId(newplayerregBean.getCompanyid1());
			
			
			productclosing.setCompanyid(maincompany);
			productclosing.setProductid(cmcproduct_itr.getFldesc());
			productclosing.setClosing(cmcproduct_itr.getClosing());
			
			productclosingService.saveproductclosing(productclosing);
			
			
		}
		
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Product Closing inserted");
	    response.put("data", null);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("closing-update-ecw")
	public ResponseEntity<Object> clodingupdateecw(){
		
		Newplayerregbean newplayerregBean = new Newplayerregbean();
		newplayerregBean.setCompanyid1(6);
		
		List<CmcProduct> cmcproduct = cmcproductService.getallproductbycompany(newplayerregBean.getCompanyid1());
		
		for(CmcProduct cmcproduct_itr : cmcproduct) {
			
			TimeZone timeZone = TimeZone.getTimeZone("Asia/Singapore");
			
			System.out.println(timeZone);
			
			Calendar calendar =  Calendar.getInstance();
			
			calendar.setTimeZone(timeZone);
			
			calendar.add(Calendar.HOUR, 2);
			calendar.add(Calendar.MINUTE, 30);
			
			System.out.println(calendar.getTime());
			
			ProductClosing productclosing = new ProductClosing();
			
			productclosing.setDate(calendar.getTime());
			
			MainCompany maincompany = new MainCompany();
			maincompany.setId(newplayerregBean.getCompanyid1());
			
			
			productclosing.setCompanyid(maincompany);
			productclosing.setProductid(cmcproduct_itr.getFldesc());
			productclosing.setClosing(cmcproduct_itr.getClosing());
			
			productclosingService.saveproductclosing(productclosing);
			
			
		}
		
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Product Closing inserted");
	    response.put("data", null);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	
}
