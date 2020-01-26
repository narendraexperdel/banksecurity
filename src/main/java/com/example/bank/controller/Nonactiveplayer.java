package com.example.bank.controller;

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
import com.example.bank.model.TmpPlayer;
import com.example.bank.model.WemPlayer;
import com.example.bank.model.Wettopup;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WemplayerService;
import com.example.bank.service.WettopupService;

@RestController
@RequestMapping("/api/")
public class Nonactiveplayer {
	
	@Autowired
	private WemplayerService wemplayerService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	@Autowired
	WettopupService wettopupService;
	
	@PostMapping("nonactive-player")
	public ResponseEntity<Object> newplayerreg(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		String playername = null ;
		String telno = null ;
	 
		
		if(newplayerreg.getCompanyid1() != null) {
			
			List<Wettopup> freebonususerid = wettopupService.freebonus(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), newplayerreg.getCompanyid1());
			
			Iterator freebonususerid_itr = freebonususerid.iterator();
			
			while(freebonususerid_itr.hasNext()) {
				 Object[] obj = (Object[]) freebonususerid_itr.next();
				 
				
					 
				
					 
				 
				 Date fromdate = (Date) obj[2];
				 Calendar c = Calendar.getInstance(); 
					c.setTime(fromdate); 
					c.add(Calendar.DATE, 1);
					Date todate = c.getTime();
				 
				List<Wettopup> wet_topup_excludefree = wettopupService.topupfornonactiveexcludefree(String.valueOf(obj[0]), String.valueOf(obj[1]), newplayerreg.getCompanyid1(),fromdate,todate);
				
				if(wet_topup_excludefree.isEmpty()) {

					List<String> playerandtel = tmpplayerService.playername(String.valueOf(obj[0]), String.valueOf(obj[1]), newplayerreg.getCompanyid1());
					
					Iterator playerandtel_itr = playerandtel.iterator();
					
					while(playerandtel_itr.hasNext()) {
						 Object[] playerandtel_obj = (Object[]) playerandtel_itr.next();
						 playername = String.valueOf(playerandtel_obj[0]);
						 telno = String.valueOf(playerandtel_obj[1]);
					}
					
					Map<String,Object> temp_non_player = new HashMap<>();
					
					temp_non_player.put("name", playername);
					temp_non_player.put("telno", telno);
					temp_non_player.put("product", String.valueOf(obj[1]));
					temp_non_player.put("userid", String.valueOf(obj[0]));
					temp_non_player.put("bonus",String.valueOf(obj[3]));
					
					result_list.add(temp_non_player);
				
			}
					    
			/*List<WemPlayer> wemplayer_list = wemplayerService.getallplayebydate(newplayerreg.getCompanyid1(), newplayerreg.getDateOfissue(), newplayerreg.getTodate());
		
			Iterator itr = wemplayer_list.iterator();
			while(itr.hasNext()){
				 Object[] obj = (Object[]) itr.next();
				 
				 List<TmpPlayer> tmpplayer_list = tmpplayerService.getuseridbyplayer(newplayerreg.getCompanyid1(),  Integer.parseInt(String.valueOf(obj[0])));
					
					for(TmpPlayer tmpplayer_itr: tmpplayer_list) {
						
						List<Wettopup> wet_topup = wettopupService.topupfornonactive(tmpplayer_itr.getUserid(), tmpplayer_itr.getProductid(), newplayerreg.getCompanyid1());
						
						if(! wet_topup.isEmpty()) {
							
							Date fromdate = new Date();
							Date todate = new Date();
							
							for(Wettopup wettopup_itr: wet_topup) {
								fromdate = wettopup_itr.getIssueddate();
								
								Calendar c = Calendar.getInstance(); 
								c.setTime(fromdate); 
								c.add(Calendar.DATE, 1);
								todate = c.getTime();
								
							}
							
							List<Wettopup> wet_topup_excludefree = wettopupService.topupfornonactiveexcludefree(tmpplayer_itr.getUserid(), tmpplayer_itr.getProductid(), newplayerreg.getCompanyid1(),fromdate,todate);
							
							if(wet_topup_excludefree.isEmpty()) {

								Map<String,Object> temp_non_player = new HashMap<>();
								
								temp_non_player.put("name", String.valueOf(obj[2]));
								temp_non_player.put("telno", String.valueOf(obj[1]));
								temp_non_player.put("product", tmpplayer_itr.getProductid());
								temp_non_player.put("userid", tmpplayer_itr.getUserid());
								temp_non_player.put("bonus", wet_topup.get(0).getAmount());
								
								result_list.add(temp_non_player);
							}
						}
					}
				
				*/
			}
			
	
				
				
			
		}
		 response.put("code", HttpStatus.OK);
		    response.put("msg", "All Unclaim List by promotion");
		    response.put("data", result_list);
		    
			return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
