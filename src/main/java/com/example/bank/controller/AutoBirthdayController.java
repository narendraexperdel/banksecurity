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
import com.example.bank.model.MainCompany;
import com.example.bank.model.TmpPlayer;
import com.example.bank.model.WemPlayer;
import com.example.bank.model.Wettopup;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WemplayerService;
import com.example.bank.service.WettopupService;

@RestController
@RequestMapping("/api/")
public class AutoBirthdayController {
	
	@Autowired
	WemplayerService wemplayerService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	@Autowired
	WettopupService wettopupService;
	
	Map<String,Object> response = new HashMap<>();
	
	@PostMapping("generate-birthday-sg")
	public ResponseEntity<Object> geneartebirthdaybonus(){
		
		Newplayerregbean newplayerregBean = new Newplayerregbean();
		newplayerregBean.setCompanyid1(1);
		newplayerregBean.setIssuedby("AdminTest-SG");
		
		List<WemPlayer> birthdayplayer = wemplayerService.birthdayplayer(newplayerregBean.getCompanyid1(), newplayerregBean.getDateOfissue(), newplayerregBean.getTodate());
		
		Iterator birthdaypalyer_itr = birthdayplayer.iterator();
		
		while(birthdaypalyer_itr.hasNext()) {
			
			Object[] obj = (Object[]) birthdaypalyer_itr.next();
			List<String> userid = new ArrayList();
			List<String> productid = new ArrayList();
			
			List<TmpPlayer> tmpplayer = tmpplayerService.getuseridbyplayer(newplayerregBean.getCompanyid1(),Integer.parseInt(String.valueOf(obj[0])));
			
			for(TmpPlayer tmpplayer_itr : tmpplayer) {
				
				userid.add(tmpplayer_itr.getUserid());
				productid.add(tmpplayer_itr.getProductid());
				
			}
			
			
			List<Wettopup> eligibleuserid = wettopupService.eligiblebirthday(newplayerregBean.getCompanyid1(), productid, userid);
			
			Iterator eligibleuserid_itr = eligibleuserid.iterator();
			
			while(eligibleuserid_itr.hasNext()) {
				
				Object[] obj1 = (Object[]) eligibleuserid_itr.next();
				
				Wettopup wettopup = new Wettopup();
				
				MainCompany maincompany = new MainCompany();
				maincompany.setId(newplayerregBean.getCompanyid1());
				
				wettopup.setCompanyid(maincompany);
				wettopup.setTelno(String.valueOf(obj[2]));
				wettopup.setIssuedby(newplayerregBean.getIssuedby());
				wettopup.setUserid(String.valueOf(obj1[0]));
				wettopup.setProductid(String.valueOf(obj1[1]));
				
				wettopupService.insertbirthday(wettopup);
				
			}
			
			
			
		}
		    response.put("code", HttpStatus.OK);
		    response.put("msg", "birthday inserted");
		    response.put("data", null);
		    
			return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("generate-birthday-vw")
	public ResponseEntity<Object> geneartebirthdaybonusforvw(){
		
		Newplayerregbean newplayerregBean = new Newplayerregbean();
		newplayerregBean.setCompanyid1(2);
		newplayerregBean.setIssuedby("AdminTest-VW");;
		
		List<WemPlayer> birthdayplayer = wemplayerService.birthdayplayer(newplayerregBean.getCompanyid1(), newplayerregBean.getDateOfissue(), newplayerregBean.getTodate());
		
		Iterator birthdaypalyer_itr = birthdayplayer.iterator();
		
		while(birthdaypalyer_itr.hasNext()) {
			
			Object[] obj = (Object[]) birthdaypalyer_itr.next();
			List<String> userid = new ArrayList();
			List<String> productid = new ArrayList();
			
			List<TmpPlayer> tmpplayer = tmpplayerService.getuseridbyplayer(newplayerregBean.getCompanyid1(),Integer.parseInt(String.valueOf(obj[0])));
			
			for(TmpPlayer tmpplayer_itr : tmpplayer) {
				
				userid.add(tmpplayer_itr.getUserid());
				productid.add(tmpplayer_itr.getProductid());
				
			}
			
			
			List<Wettopup> eligibleuserid = wettopupService.eligiblebirthday(newplayerregBean.getCompanyid1(), productid, userid);
			
			Iterator eligibleuserid_itr = eligibleuserid.iterator();
			
			while(eligibleuserid_itr.hasNext()) {
				
				Object[] obj1 = (Object[]) eligibleuserid_itr.next();
				
				Wettopup wettopup = new Wettopup();
				
				MainCompany maincompany = new MainCompany();
				maincompany.setId(newplayerregBean.getCompanyid1());
				
				wettopup.setCompanyid(maincompany);
				wettopup.setTelno(String.valueOf(obj[2]));
				wettopup.setIssuedby(newplayerregBean.getIssuedby());
				wettopup.setUserid(String.valueOf(obj1[0]));
				wettopup.setProductid(String.valueOf(obj1[1]));
				
				wettopupService.insertbirthday(wettopup);
				
			}
			
			
			
		}
		    response.put("code", HttpStatus.OK);
		    response.put("msg", "birthday inserted");
		    response.put("data", null);
		    
			return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("generate-birthday-vg")
	public ResponseEntity<Object> geneartebirthdaybonusforvg(){
		
		Newplayerregbean newplayerregBean = new Newplayerregbean();
		newplayerregBean.setCompanyid1(3);
		newplayerregBean.setIssuedby("AdminTest-VG");;
		
		List<WemPlayer> birthdayplayer = wemplayerService.birthdayplayer(newplayerregBean.getCompanyid1(), newplayerregBean.getDateOfissue(), newplayerregBean.getTodate());
		
		Iterator birthdaypalyer_itr = birthdayplayer.iterator();
		
		while(birthdaypalyer_itr.hasNext()) {
			
			Object[] obj = (Object[]) birthdaypalyer_itr.next();
			List<String> userid = new ArrayList();
			List<String> productid = new ArrayList();
			
			List<TmpPlayer> tmpplayer = tmpplayerService.getuseridbyplayer(newplayerregBean.getCompanyid1(),Integer.parseInt(String.valueOf(obj[0])));
			
			for(TmpPlayer tmpplayer_itr : tmpplayer) {
				
				userid.add(tmpplayer_itr.getUserid());
				productid.add(tmpplayer_itr.getProductid());
				
			}
			
			
			List<Wettopup> eligibleuserid = wettopupService.eligiblebirthday(newplayerregBean.getCompanyid1(), productid, userid);
			
			Iterator eligibleuserid_itr = eligibleuserid.iterator();
			
			while(eligibleuserid_itr.hasNext()) {
				
				Object[] obj1 = (Object[]) eligibleuserid_itr.next();
				
				Wettopup wettopup = new Wettopup();
				
				MainCompany maincompany = new MainCompany();
				maincompany.setId(newplayerregBean.getCompanyid1());
				
				wettopup.setCompanyid(maincompany);
				wettopup.setTelno(String.valueOf(obj[2]));
				wettopup.setIssuedby(newplayerregBean.getIssuedby());
				wettopup.setUserid(String.valueOf(obj1[0]));
				wettopup.setProductid(String.valueOf(obj1[1]));
				
				wettopupService.insertbirthday(wettopup);
				
			}
			
			
			
		}
		    response.put("code", HttpStatus.OK);
		    response.put("msg", "birthday inserted");
		    response.put("data", null);
		    
			return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("generate-birthday-gsc")
	public ResponseEntity<Object> geneartebirthdaybonusforgsc(){
		
		Newplayerregbean newplayerregBean = new Newplayerregbean();
		newplayerregBean.setCompanyid1(4);
		newplayerregBean.setIssuedby("AdminTest-GSC");;
		
		List<WemPlayer> birthdayplayer = wemplayerService.birthdayplayer(newplayerregBean.getCompanyid1(), newplayerregBean.getDateOfissue(), newplayerregBean.getTodate());
		
		Iterator birthdaypalyer_itr = birthdayplayer.iterator();
		
		while(birthdaypalyer_itr.hasNext()) {
			
			Object[] obj = (Object[]) birthdaypalyer_itr.next();
			List<String> userid = new ArrayList();
			List<String> productid = new ArrayList();
			
			List<TmpPlayer> tmpplayer = tmpplayerService.getuseridbyplayer(newplayerregBean.getCompanyid1(),Integer.parseInt(String.valueOf(obj[0])));
			
			for(TmpPlayer tmpplayer_itr : tmpplayer) {
				
				userid.add(tmpplayer_itr.getUserid());
				productid.add(tmpplayer_itr.getProductid());
				
			}
			
			
			List<Wettopup> eligibleuserid = wettopupService.eligiblebirthday(newplayerregBean.getCompanyid1(), productid, userid);
			
			Iterator eligibleuserid_itr = eligibleuserid.iterator();
			
			while(eligibleuserid_itr.hasNext()) {
				
				Object[] obj1 = (Object[]) eligibleuserid_itr.next();
				
				Wettopup wettopup = new Wettopup();
				
				MainCompany maincompany = new MainCompany();
				maincompany.setId(newplayerregBean.getCompanyid1());
				
				wettopup.setCompanyid(maincompany);
				wettopup.setTelno(String.valueOf(obj[2]));
				wettopup.setIssuedby(newplayerregBean.getIssuedby());
				wettopup.setUserid(String.valueOf(obj1[0]));
				wettopup.setProductid(String.valueOf(obj1[1]));
				
				wettopupService.insertbirthday(wettopup);
				
			}
			
			
			
		}
		    response.put("code", HttpStatus.OK);
		    response.put("msg", "birthday inserted");
		    response.put("data", null);
		    
			return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("generate-birthday-bw")
	public ResponseEntity<Object> geneartebirthdaybonusforbw(){
		
		Newplayerregbean newplayerregBean = new Newplayerregbean();
		newplayerregBean.setCompanyid1(5);
		newplayerregBean.setIssuedby("AdminTest-BW");;
		
		List<WemPlayer> birthdayplayer = wemplayerService.birthdayplayer(newplayerregBean.getCompanyid1(), newplayerregBean.getDateOfissue(), newplayerregBean.getTodate());
		
		Iterator birthdaypalyer_itr = birthdayplayer.iterator();
		
		while(birthdaypalyer_itr.hasNext()) {
			
			Object[] obj = (Object[]) birthdaypalyer_itr.next();
			List<String> userid = new ArrayList();
			List<String> productid = new ArrayList();
			
			List<TmpPlayer> tmpplayer = tmpplayerService.getuseridbyplayer(newplayerregBean.getCompanyid1(),Integer.parseInt(String.valueOf(obj[0])));
			
			for(TmpPlayer tmpplayer_itr : tmpplayer) {
				
				userid.add(tmpplayer_itr.getUserid());
				productid.add(tmpplayer_itr.getProductid());
				
			}
			
			
			List<Wettopup> eligibleuserid = wettopupService.eligiblebirthday(newplayerregBean.getCompanyid1(), productid, userid);
			
			Iterator eligibleuserid_itr = eligibleuserid.iterator();
			
			while(eligibleuserid_itr.hasNext()) {
				
				Object[] obj1 = (Object[]) eligibleuserid_itr.next();
				
				Wettopup wettopup = new Wettopup();
				
				MainCompany maincompany = new MainCompany();
				maincompany.setId(newplayerregBean.getCompanyid1());
				
				wettopup.setCompanyid(maincompany);
				wettopup.setTelno(String.valueOf(obj[2]));
				wettopup.setIssuedby(newplayerregBean.getIssuedby());
				wettopup.setUserid(String.valueOf(obj1[0]));
				wettopup.setProductid(String.valueOf(obj1[1]));
				
				wettopupService.insertbirthday(wettopup);
				
			}
			
			
			
		}
		    response.put("code", HttpStatus.OK);
		    response.put("msg", "birthday inserted");
		    response.put("data", null);
		    
			return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("generate-birthday-ecw")
	public ResponseEntity<Object> geneartebirthdaybonusforecw(){
		
		Newplayerregbean newplayerregBean = new Newplayerregbean();
		newplayerregBean.setCompanyid1(6);
		newplayerregBean.setIssuedby("AdminTest-ECW");;
		
		List<WemPlayer> birthdayplayer = wemplayerService.birthdayplayer(newplayerregBean.getCompanyid1(), newplayerregBean.getDateOfissue(), newplayerregBean.getTodate());
		
		Iterator birthdaypalyer_itr = birthdayplayer.iterator();
		
		while(birthdaypalyer_itr.hasNext()) {
			
			Object[] obj = (Object[]) birthdaypalyer_itr.next();
			List<String> userid = new ArrayList();
			List<String> productid = new ArrayList();
			
			List<TmpPlayer> tmpplayer = tmpplayerService.getuseridbyplayer(newplayerregBean.getCompanyid1(),Integer.parseInt(String.valueOf(obj[0])));
			
			for(TmpPlayer tmpplayer_itr : tmpplayer) {
				
				userid.add(tmpplayer_itr.getUserid());
				productid.add(tmpplayer_itr.getProductid());
				
			}
			
			
			List<Wettopup> eligibleuserid = wettopupService.eligiblebirthday(newplayerregBean.getCompanyid1(), productid, userid);
			
			Iterator eligibleuserid_itr = eligibleuserid.iterator();
			
			while(eligibleuserid_itr.hasNext()) {
				
				Object[] obj1 = (Object[]) eligibleuserid_itr.next();
				
				Wettopup wettopup = new Wettopup();
				
				MainCompany maincompany = new MainCompany();
				maincompany.setId(newplayerregBean.getCompanyid1());
				
				wettopup.setCompanyid(maincompany);
				wettopup.setTelno(String.valueOf(obj[2]));
				wettopup.setIssuedby(newplayerregBean.getIssuedby());
				wettopup.setUserid(String.valueOf(obj1[0]));
				wettopup.setProductid(String.valueOf(obj1[1]));
				
				wettopupService.insertbirthday(wettopup);
				
			}
			
			
			
		}
		    response.put("code", HttpStatus.OK);
		    response.put("msg", "birthday inserted");
		    response.put("data", null);
		    
			return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("generate-birthday-test")
	public ResponseEntity<Object> geneartebirthdaybonusfortest(){
		
		Newplayerregbean newplayerregBean = new Newplayerregbean();
		newplayerregBean.setCompanyid1(8);
		newplayerregBean.setIssuedby("AdminTest-TM");;
		
		List<WemPlayer> birthdayplayer = wemplayerService.birthdayplayer(newplayerregBean.getCompanyid1(), newplayerregBean.getDateOfissue(), newplayerregBean.getTodate());
		
		Iterator birthdaypalyer_itr = birthdayplayer.iterator();
		
		while(birthdaypalyer_itr.hasNext()) {
			
			Object[] obj = (Object[]) birthdaypalyer_itr.next();
			List<String> userid = new ArrayList();
			List<String> productid = new ArrayList();
			
			List<TmpPlayer> tmpplayer = tmpplayerService.getuseridbyplayer(newplayerregBean.getCompanyid1(),Integer.parseInt(String.valueOf(obj[0])));
			
			for(TmpPlayer tmpplayer_itr : tmpplayer) {
				
				userid.add(tmpplayer_itr.getUserid());
				productid.add(tmpplayer_itr.getProductid());
				
			}
			
			
			List<Wettopup> eligibleuserid = wettopupService.eligiblebirthday(newplayerregBean.getCompanyid1(), productid, userid);
			
			Iterator eligibleuserid_itr = eligibleuserid.iterator();
			
			while(eligibleuserid_itr.hasNext()) {
				
				Object[] obj1 = (Object[]) eligibleuserid_itr.next();
				
				Wettopup wettopup = new Wettopup();
				
				MainCompany maincompany = new MainCompany();
				maincompany.setId(newplayerregBean.getCompanyid1());
				
				wettopup.setCompanyid(maincompany);
				wettopup.setTelno(String.valueOf(obj[2]));
				wettopup.setIssuedby(newplayerregBean.getIssuedby());
				wettopup.setUserid(String.valueOf(obj1[0]));
				wettopup.setProductid(String.valueOf(obj1[1]));
				
				wettopupService.insertbirthday(wettopup);
				
			}
			
			
			
		}
		    response.put("code", HttpStatus.OK);
		    response.put("msg", "birthday inserted");
		    response.put("data", null);
		    
			return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
