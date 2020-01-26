package com.example.bank.controller;

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

import com.example.bank.bean.Newplayerregbean;
import com.example.bank.model.TelePending;
import com.example.bank.service.TelePendingService;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class TelePendingPerformanceController {
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;
	
	@Autowired
	TelePendingService telependingService;

	@PostMapping("tele-performance")
	public ResponseEntity<Object> statisticreportcs(@RequestBody  Newplayerregbean newplayerreg){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<Map<String,Object>> result_list_closing = new ArrayList<>();
		List<Map<String,Object>> result_list_closing_summary = new ArrayList<>();
		 List<String> useridclosing = new ArrayList<>();
		 List<String> useridclosing_sum = new ArrayList<>();
		 List<String> useridclosing_totalsum = new ArrayList<>();
		 
				 
		
		Integer rc_int =0 ;
		Integer rc_ni =0 ;
		Integer rc_na =0 ;
		Integer rc_vm =0 ;
		Integer rc_cb =0;
		Integer rc_nr = 0;
		Integer rc_fd = 0;
		Integer rc_huc =0;
		
	 
		if(newplayerreg.getCompanyid1() != null) {
			
			List<String> remark = new ArrayList<>();
			List<String> name = new ArrayList<>();
			
			remark.add("Interested");
			remark.add("Not Interested");
			remark.add("Not Answer");
			remark.add("Voicemail");
			remark.add("Call Back");
			remark.add("No Reply");
			remark.add("Follow Deposit");
			remark.add("Hang Up Call");
			
			Map<String,Object> tele_temp = new HashMap<>();
			
			tele_temp.put("name", "Name");
			tele_temp.put("interested", "Interested");
			tele_temp.put("notinterested", "Not Interested");
			tele_temp.put("noanswer", "No Answer");
			tele_temp.put("voicemail", "Voicemail");
			tele_temp.put("callback", "Call Back");
			tele_temp.put("noreply", "No Reply");
			tele_temp.put("followdeposit", "Follow Deposit");
			tele_temp.put("hangupcall", "Hang Up Call");
			
			result_list.add(tele_temp);
			
			name.add("KEVIN");
			name.add("TELE02");
			
			for(String name_itr: name) {
				
				Map<String,Object> tele_remark_temp = new HashMap<>();
				
				tele_remark_temp.put("name", name_itr);
				
				List<TelePending> remark_count = telependingService.countofteleremark(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), "Interested", name_itr, newplayerreg.getCompanyid1());
				
				 for(Object remark_count_itr :remark_count) {
					 
					 rc_int = ((Integer)remark_count_itr);
			        	
			        }
				 
				 tele_remark_temp.put("interested", rc_int); 
				 
				 List<TelePending> remark_count_ni = telependingService.countofteleremark(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), "Not Interested", name_itr, newplayerreg.getCompanyid1());
					
				 for(Object remark_count_ni_itr :remark_count_ni) {
					 
					 rc_ni = ((Integer)remark_count_ni_itr);
			        	
			        }
				 
				 tele_remark_temp.put("notinterested", rc_ni);
				 
				 List<TelePending> remark_count_na = telependingService.countofteleremark(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), "No Answer", name_itr, newplayerreg.getCompanyid1());
					
				 for(Object remark_count_na_itr :remark_count_na) {
					 
					 rc_na = ((Integer)remark_count_na_itr);
			        	
			        }
				 
				 tele_remark_temp.put("noanswer", rc_na);
				 
				 List<TelePending> remark_count_vm = telependingService.countofteleremark(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), "Voicemail", name_itr, newplayerreg.getCompanyid1());
					
				 for(Object remark_count_vm_itr :remark_count_vm) {
					 
					 rc_vm = ((Integer)remark_count_vm_itr);
			        	
			        }
				 
				 tele_remark_temp.put("voicemail", rc_vm);
				 
				 List<TelePending> remark_count_cb = telependingService.countofteleremark(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), "Call Back", name_itr, newplayerreg.getCompanyid1());
					
				 for(Object remark_count_cb_itr :remark_count_cb) {
					 
					 rc_cb = ((Integer)remark_count_cb_itr);
			        	
			        }
				 
				 tele_remark_temp.put("callback", rc_cb);
				 
				 List<TelePending> remark_count_nr = telependingService.countofteleremark(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), "No Reply", name_itr, newplayerreg.getCompanyid1());
					
				 for(Object remark_count_nr_itr :remark_count_nr) {
					 
					 rc_nr = ((Integer)remark_count_nr_itr);
			        	
			        }
				 
				 tele_remark_temp.put("noreply", rc_nr);
				 
				 List<TelePending> remark_count_fd = telependingService.countofteleremark(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), "Follow Deposit", name_itr, newplayerreg.getCompanyid1());
					
				 for(Object remark_count_fd_itr :remark_count_fd) {
					 
					 rc_fd = ((Integer)remark_count_fd_itr);
			        	
			        }
				 
				 tele_remark_temp.put("followdeposit", rc_fd);
				 
				 List<TelePending> remark_count_huc = telependingService.countofteleremark(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), "Hang Up Call", name_itr, newplayerreg.getCompanyid1());
					
				 for(Object remark_count_fd_itr :remark_count_huc) {
					 
					 rc_huc = ((Integer)remark_count_fd_itr);
			        	
			        }
				 
				 tele_remark_temp.put("hangupcall", rc_huc);
				 
				 
				 result_list.add(tele_remark_temp);
				
				/*for(String remark_itr: remark) {
					
					List<TelePending> remark_count = telependingService.countofteleremark(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), remark_itr, name_itr, newplayerreg.getCompanyid1());
					
					 for(Object remark_count_itr :remark_count) {
						 
						 rc = ((Integer)remark_count_itr);
				        	
				        }
					 tele_remark_temp.put("name", name_itr);
					
					
				}*/
				
				
				
			}
			

			Map<String,Object> tele_remark_temp = new HashMap<>();
			
			tele_remark_temp.put("name", "Report Total :");
			
			List<TelePending> remark_count = telependingService.countofteleremark_total(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), "Interested",  newplayerreg.getCompanyid1());
			
			 for(Object remark_count_itr :remark_count) {
				 
				 rc_int = ((Integer)remark_count_itr);
		        	
		        }
			 
			 tele_remark_temp.put("interested", rc_int); 
			 
			 List<TelePending> remark_count_ni = telependingService.countofteleremark_total(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), "Not Interested", newplayerreg.getCompanyid1());
				
			 for(Object remark_count_ni_itr :remark_count_ni) {
				 
				 rc_ni = ((Integer)remark_count_ni_itr);
		        	
		        }
			 
			 tele_remark_temp.put("notinterested", rc_ni);
			 
			 List<TelePending> remark_count_na = telependingService.countofteleremark_total(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), "No Answer", newplayerreg.getCompanyid1());
				
			 for(Object remark_count_na_itr :remark_count_na) {
				 
				 rc_na = ((Integer)remark_count_na_itr);
		        	
		        }
			 
			 tele_remark_temp.put("noanswer", rc_na);
			 
			 List<TelePending> remark_count_vm = telependingService.countofteleremark_total(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), "Voicemail", newplayerreg.getCompanyid1());
				
			 for(Object remark_count_vm_itr :remark_count_vm) {
				 
				 rc_vm = ((Integer)remark_count_vm_itr);
		        	
		        }
			 
			 tele_remark_temp.put("voicemail", rc_vm);
			 
			 List<TelePending> remark_count_cb = telependingService.countofteleremark_total(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), "Call Back",  newplayerreg.getCompanyid1());
				
			 for(Object remark_count_cb_itr :remark_count_cb) {
				 
				 rc_cb = ((Integer)remark_count_cb_itr);
		        	
		        }
			 
			 tele_remark_temp.put("callback", rc_cb);
			 
			 List<TelePending> remark_count_nr = telependingService.countofteleremark_total(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), "No Reply", newplayerreg.getCompanyid1());
				
			 for(Object remark_count_nr_itr :remark_count_nr) {
				 
				 rc_nr = ((Integer)remark_count_nr_itr);
		        	
		        }
			 
			 tele_remark_temp.put("noreply", rc_nr);
			 
			 List<TelePending> remark_count_fd = telependingService.countofteleremark_total(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), "Follow Deposit", newplayerreg.getCompanyid1());
				
			 for(Object remark_count_fd_itr :remark_count_fd) {
				 
				 rc_fd = ((Integer)remark_count_fd_itr);
		        	
		        }
			 
			 tele_remark_temp.put("followdeposit", rc_fd);
			 
			 List<TelePending> remark_count_huc = telependingService.countofteleremark_total(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), "Hang Up Call", newplayerreg.getCompanyid1());
				
			 for(Object remark_count_fd_itr :remark_count_huc) {
				 
				 rc_huc = ((Integer)remark_count_fd_itr);
		        	
		        }
			 
			 tele_remark_temp.put("hangupcall", rc_huc);
			 
			 
			 result_list.add(tele_remark_temp);
			 
			 
			 for(String name_itr_closing : name) {
				 
				 Map<String,Object> tele_closing_temp = new HashMap<>();
				
				 tele_closing_temp.put("name", name_itr_closing);
				 
				 List<TelePending> tele_userid = telependingService.distinctuserid(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), name_itr_closing, newplayerreg.getCompanyid1());
				 
				 Iterator tele_userid_itr = tele_userid.iterator();
				 
					while(tele_userid_itr.hasNext()){
						   Object[] obj1 = (Object[]) tele_userid_itr.next();
						   
						   useridclosing.add(String.valueOf(obj1[0]));
						   
						   tele_closing_temp.put("userid", String.valueOf(obj1[0]));
						   tele_closing_temp.put("teledate", (Date)obj1[1]);
						   tele_closing_temp.put("topupdate", (Date)obj1[2]);
						  
						   Double topup = wettopupService.memberaccount_topup(String.valueOf(obj1[0]), newplayerreg.getCompanyid1());
						   
						   if(topup == null || topup == 0.0) {
							   topup = 0.0;
						   }
						   
						   tele_closing_temp.put("topup", topup);
						
						   result_list_closing.add(tele_closing_temp);
					}
				 
			 }
			 
			 
			 Map<String,Object> tele_closing_temp = new HashMap<>();
			 
			 Double closing_total_amount = wettopupService.vipamount(useridclosing, newplayerreg.getCompanyid1());
			 
			 if(closing_total_amount == null || closing_total_amount == 0.0) {
				 closing_total_amount = 0.0;
			   }
			 
			   tele_closing_temp.put("name", "Name Total :");
			   tele_closing_temp.put("userid", " ");
			   tele_closing_temp.put("teledate", " " );
			   tele_closing_temp.put("topupdate", " " );
			   tele_closing_temp.put("topup", closing_total_amount);
			   
			   Map<String,Object> tele_closing_report_temp = new HashMap<>();
			   
			   tele_closing_report_temp.put("name", "Report Total :");
			   tele_closing_report_temp.put("userid", " ");
			   tele_closing_report_temp.put("teledate", " " );
			   tele_closing_report_temp.put("topupdate", " " );
			   tele_closing_report_temp.put("topup", closing_total_amount);
			
			   result_list_closing.add(tele_closing_report_temp);
			   
			   
			   for(String name_closingsummary: name) {
				   
				   Map<String,Object> tele_closingsum_report_temp = new HashMap<>();
				   
				   tele_closingsum_report_temp.put("name", name_closingsummary);
				   
				   List<String> distinctassign = telependingService.distinctassign(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), name_closingsummary, newplayerreg.getCompanyid1());
				   
				   Iterator distinctassign_itr = distinctassign.iterator();
					while(distinctassign_itr.hasNext()){
						   String obj1 = (String) distinctassign_itr.next();
						   tele_closingsum_report_temp.put("category", obj1);
						   
						   List<String> tele_userid_closesum = telependingService.distinctuserid_closesum(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), name_closingsummary, obj1, newplayerreg.getCompanyid1());
							 
							 Iterator tele_userid_closesum_itr = tele_userid_closesum.iterator();
							 
								while(tele_userid_closesum_itr.hasNext()){
									String obj2 = (String) tele_userid_closesum_itr.next();
									   
									useridclosing_sum.add(String.valueOf(obj2));
						   
								}
								
								
						List<Integer> playercount_totalsum = tmpplayerService.playercount_closesum(useridclosing_sum, newplayerreg.getCompanyid1());		
								
						Iterator playercount_totalsum_itr = playercount_totalsum.iterator();
						 
						while(playercount_totalsum_itr.hasNext()){
							Integer obj3 = (Integer) playercount_totalsum_itr.next();
							   
							tele_closingsum_report_temp.put("totalplayer", obj3);
				   
						}
						
						 Double closingsum_total_amount = wettopupService.vipamount(useridclosing_sum, newplayerreg.getCompanyid1());
						 
						 if(closingsum_total_amount == null || closingsum_total_amount == 0.0) {
							 closingsum_total_amount = 0.0;
						   }
						 
						 tele_closingsum_report_temp.put("topup", closingsum_total_amount);
						 
						 result_list_closing_summary.add(tele_closingsum_report_temp);
						
					}
					
					
					
				   
			   }
			   
			   Map<String,Object> tele_closingsum_report_temp = new HashMap<>();
			   for(String name_closingsummary: name) {
			   List<TelePending> tele_userid = telependingService.distinctuserid(newplayerreg.getDateOfissue(), newplayerreg.getTodate(), name_closingsummary, newplayerreg.getCompanyid1());
				 
				 Iterator tele_userid_itr = tele_userid.iterator();
				 
					while(tele_userid_itr.hasNext()){
						   Object[] obj1 = (Object[]) tele_userid_itr.next();
						   
						   useridclosing_totalsum.add(String.valueOf(obj1[0]));
					}
			   }
			   
			   List<Integer> playercount_totalsum = tmpplayerService.playercount_closesum(useridclosing_totalsum, newplayerreg.getCompanyid1());		
				
			   
			   
				Iterator playercount_totalsum_itr = playercount_totalsum.iterator();
				Integer obj3 =0;
				 
				while(playercount_totalsum_itr.hasNext()){
					 obj3 = (Integer) playercount_totalsum_itr.next();
					   
					tele_closingsum_report_temp.put("totalplayer", obj3);
		   
				}
				tele_closingsum_report_temp.put("name", "Name Total :");
				 tele_closingsum_report_temp.put("category", "" );
				 
				 
				 Double closingsum_total_amount = wettopupService.vipamount(useridclosing_totalsum, newplayerreg.getCompanyid1());
				 
				 if(closingsum_total_amount == null || closingsum_total_amount == 0.0) {
					 closingsum_total_amount = 0.0;
				   }
				 
				 tele_closingsum_report_temp.put("topup", closingsum_total_amount);
				 
				 result_list_closing_summary.add(tele_closingsum_report_temp);
				
				  Map<String,Object> tele_closingsum_report_temp1 = new HashMap<>();
				  
				  tele_closingsum_report_temp1.put("name", "Report Total :");
				  tele_closingsum_report_temp1.put("category", "" );
				  tele_closingsum_report_temp.put("totalplayer", obj3);
				  tele_closingsum_report_temp.put("topup", closingsum_total_amount);
				  
				  result_list_closing_summary.add(tele_closingsum_report_temp1);
			
		}
		
		
		
		
		
		
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Tele Pending Performance data");
	    response.put("data", result_list);
	    response.put("datasize", result_list.size());
	    response.put("closingdata", result_list_closing);
	    response.put("closingdatasize", result_list_closing.size());
	    response.put("closingsummary", result_list_closing_summary);
	    response.put("closingsummarysize", result_list_closing_summary.size());
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
