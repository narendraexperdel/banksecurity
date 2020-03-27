package com.example.bank.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.example.bank.bean.Newplayerregbean;
import com.example.bank.model.Teledata;
import com.example.bank.model.Wettopup;
import com.example.bank.service.CmcproductService;
import com.example.bank.service.KioskDepositService;
import com.example.bank.service.KioskWithdrawService;
import com.example.bank.service.MasterKioskService;
import com.example.bank.service.TeleDataService;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WemplayerService;
import com.example.bank.service.WettopupService;

@RestController
@RequestMapping("/api/")
public class TeleDataController {
	
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
	TeleDataService teledataService;
	
	@PostMapping("teledata")
	public ResponseEntity<Object> teledata(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		
		if(newplayerreg.getCompanyid1() !=null) {
			Date date = newplayerreg.getDateOfissue();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date); 
			
			String month = Integer.toString(cal.get(Calendar.MONTH)+1); 
			String year = Integer.toString(cal.get(Calendar.YEAR)); 
			
			
			List<Wettopup> distinctdate = wettopupService.teledata(month, year, newplayerreg.getCompanyid1());
			
			Iterator distinctdate_itr = distinctdate.iterator();
			while(distinctdate_itr.hasNext()){
				String obj = (String) distinctdate_itr.next();
				
				
				List<Wettopup> lastdeposit = wettopupService.lastdepositdateandamount(newplayerreg.getCompanyid1(),String.valueOf(obj),month, year);
				
				Iterator lastdeposit_itr = lastdeposit.iterator();
				while(lastdeposit_itr.hasNext()){
					Object[] obj1 = (Object[]) lastdeposit_itr.next();
					
					Teledata teledata = new Teledata();
					
					/*String sDate1=obj1[2];  
				    Date date1 = new Date();
					try {
						date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  */
					
					teledata.setUserid(String.valueOf(obj1[0]));
					teledata.setLastdepositamount(new Double(obj1[1].toString()));
					teledata.setLastdepositdate((Date) obj1[2]);
					teledata.setCompanyname(String.valueOf(obj1[3]));
					
					teledataService.saveteledata(teledata);
					
				}
				
			}
		}
		
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Telep data Generated");
	    response.put("data", null);
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		
	}

}
