package com.example.bank.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.example.bank.bean.GenerateteleBean;
import com.example.bank.bean.Newplayerregbean;
import com.example.bank.model.MainCompany;
import com.example.bank.model.TelePending;
import com.example.bank.model.TmpPlayer;
import com.example.bank.service.ExpensestypeService;
import com.example.bank.service.OthIncomeService;
import com.example.bank.service.TelePendingService;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class GenerateteleController {
	

	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;;
	
	@Autowired
	ExpensestypeService expensetypeService;
	
	@Autowired
	OthIncomeService othincomeService;
	
	@Autowired
	TelePendingService telependingService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	
	@PostMapping("generate-tele")
	public ResponseEntity<Object> bankcashflow(@RequestBody  GenerateteleBean[] generatetele){
		Map<String, Object> response = new HashMap<>();
		List<TelePending> result_list = new ArrayList<>();
		String productid = null;
		String flname = null;
		String wcws = null;
		String sources = null;
		
		for(GenerateteleBean generatetele_itr: generatetele) {
			
			System.out.println(generatetele_itr.getUserid());
			System.out.println(generatetele_itr.getTelno());
			System.out.println(generatetele_itr.getGeneratefrom());
			System.out.println(generatetele_itr.getCompanyid1());
			
			List<TelePending> checkteleexistornot = telependingService.checktelepending(generatetele_itr.getUserid(), generatetele_itr.getGeneratefrom(), generatetele_itr.getCompanyid1());
			
			if(checkteleexistornot.isEmpty()) {
				
				TelePending telepending = new TelePending();
				
				String pattern = "yyyy-mm-dd";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				String date = simpleDateFormat.format(generatetele_itr.getIssueddate());
				System.out.println(date);
				Date date1 = null;
				try {
					date1 = new SimpleDateFormat("yyyy-mm-dd").parse(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				telepending.setIssuedby(generatetele_itr.getIssuedby());
				telepending.setTelno(generatetele_itr.getTelno());
				telepending.setUserid(generatetele_itr.getUserid());
				telepending.setIssueddt(generatetele_itr.getIssueddate());
				telepending.setIssued_date(date1);
				telepending.setGentype(generatetele_itr.getGeneratefrom());
				telepending.setResolutiondt(generatetele_itr.getRegisterdate());
				
				MainCompany maincompany = new MainCompany();
				maincompany.setId(generatetele_itr.getCompanyid1());
				
				telepending.setCompanyid(maincompany);
				
				List<String> productname = tmpplayerService.productname(generatetele_itr.getUserid(), generatetele_itr.getCompanyid1());
				
				Iterator productname_itr = productname.iterator();
				
				while(productname_itr.hasNext()) {
					 String productname_obj = (String) productname_itr.next();
					 productid = productname_obj;
				}
				
				List<String> playername = tmpplayerService.playername(generatetele_itr.getUserid(), productid, generatetele_itr.getCompanyid1());
				
                     Iterator playername_itr = playername.iterator();
				
				while(playername_itr.hasNext()) {
					 Object[] playername_obj = (Object[]) playername_itr.next();
					 flname = String.valueOf(playername_obj[0]);
					 wcws = String.valueOf(playername_obj[4]);
					 sources = String.valueOf(playername_obj[3]);
				}
				
				telepending.setNickname(flname);
				telepending.setWcws(wcws);
				telepending.setSources(sources);
				telepending.setProduct(productid);
				
				 telependingService.savetelepending(telepending);
				
			
			}
			
		
		}
	
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Telep Pending Generated data");
	    response.put("data", result_list);
	    response.put("datasize", result_list.size());
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	
	
	
	

}
