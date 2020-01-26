package com.example.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.Wetwithdraw;

@Service
public interface WetwithdrawService {
	
	public Double getamountbyuseridinwithdraw(Date fromdate, Date todate,String userid,Integer companyid);
	
	public List<Wetwithdraw> monthlysalestopup(String month, String year, Integer companyid);
	
	public Double withdrawamount_newplayerreg(List<String> userid,Integer companyid,List<String> productid);
	
	public Double bankcashflow(Date date,Integer companyid);
	
	public List bankcashflow_totolamount(String month, String year, Integer companyid);
	
	public Double memberaccount_withdraw(String userid,Integer companyid);
	
	public Double playercashflow_withdraw(Date fromdate, Date todate, Integer companyid,String userid);
	
	public Double playercashflow_report_withdraw(Date fromdate, Date todate, Integer companyid);
	
	 public Double productdaily_withdraw_total(Date fromdate, Integer companyid);
	 
     public Double productdaily_withdraw(Date fromdate, Integer companyid,String product);
     
     public Double topwlplayer_withdraw(String userid,Integer companyid, String productid,Date fromdate,Date todate );
	 
     public List<Wetwithdraw> latewithdraw(Date fromdate, Date todate, Integer companyid);

     public Double cashflowproduct_withdraw_total(Integer companyid, String productid,Date fromdate,Date todate );

	 public Double cashflowbank_withdraw(String userid,Integer companyid, String bank,Date fromdate,Date todate );
	 
	 public Double cashflowbank_withdraw_total(Integer companyid, String bank,Date fromdate,Date todate );
	 
	 public Double cashflowbank_withdraw_report_total(Integer companyid,Date fromdate,Date todate );
	 
	 public List<Wetwithdraw> cashflowproduct_withdraw(Date fromdate,Date todate, Integer companyid, String productid);
	 
	 public List<Wetwithdraw> cashflowbank_withdraw(Date fromdate, Date todate, Integer companyid, String bank);

	 public Double getsumamountbyuserid_withdraw(String userid,String productid,Integer companyid);
	 
	 public  List<Wetwithdraw>  lasttopupandamount(Integer companyid,String product,String userid);
	 
	 public Double topvswithdraw_withdraw(Date fromdate, Integer companyid,List<String> product);
	 
 public List<Wetwithdraw> topvswithdraw_topuprange_hundred(String month,String year, Integer companyid,List<String> product);
	 
	 public List<Wetwithdraw> topvswithdraw_topuprange_hundredone(String month,String year, Integer companyid,List<String> product);
	 
	 public List<Wetwithdraw> topvswithdraw_topuprange_fivehundred(String month,String year, Integer companyid,List<String> product);
	 
	 public List<Wetwithdraw> topvswithdraw_withdraw_transac(Date date, Integer companyid,List<String> product);
	 
 public  List<Wetwithdraw>  daily_mix_wetwithdraw(Integer companyid,Date fromdate, Date todate,List<String> productid);
	 
	 public  Double  daily_mix_withdraw_amount(Integer companyid,Date fromdate, Date todate,List<String> productid);
	 
	 public  Double  daily_mix_withdraw_bonus(Integer companyid,Date fromdate, Date todate,List<String> productid);
	 
	 public  Long  daily_mix_withdraw_trancid(Integer companyid,Date fromdate, Date todate,List<String> productid);
	 
	 public  List<Wetwithdraw> outside_report_unclaim(Integer companyid,List<String> productid,Date fromdate,Date todate);
	 
	 public List<Wetwithdraw> outside_report_claim(Integer companyid,List<String> productid,Date fromdate,Date todate,List<String> status);
	 
     public  List<Wetwithdraw> outside_report_unclaim_gst(Integer companyid,List<String> productid,Date fromdate,Date todate);
	 
	 public List<Wetwithdraw> outside_report_claim_gst(Integer companyid,List<String> productid,Date fromdate,Date todate,List<String> status);
	 
	 public  Double  daily_mix_withdraw_taxamount(Integer companyid,Date fromdate, Date todate,List<String> productid);
}
