package com.example.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.Wettopup;
import com.example.bank.model.Wettp;
import com.example.bank.model.Wetwithdraw;

@Service
public interface WettpService {
	
    public Double player_transferin_amount(Date fromdate, Date todate, Integer companyid,String touserid);
	
	public Double player_transferout_amount(Date fromdate, Date todate, Integer companyid,String touserid);
	
	 public Double player_transferin__report_amount(Date fromdate, Date todate, Integer companyid);
		
	public Double player_transferout_report_amount(Date fromdate, Date todate, Integer companyid);
	
	 public Double productdaily_in(Date fromdate, Integer companyid,String product);
		
	 public Double productdaily_out(Date fromdate, Integer companyid,String product);
	 
	 public Double productdaily_in_total(Date fromdate, Integer companyid);
		
		public Double productdaily_out_total(Date fromdate, Integer companyid);
		
		 public Double cashflowproduct_transferout(String userid,Integer companyid, String productid,Date fromdate,Date todate );
		 
		 public Double cashflowproduct_transferin(String userid,Integer companyid, String productid,Date fromdate,Date todate );

		 public Double cashflowproduct_transferout_total(Integer companyid, String productid,Date fromdate,Date todate );
		 
		 public Double cashflowproduct_transferin_total(Integer companyid, String productid,Date fromdate,Date todate );
		 
		 public  List<Wettp>  daily_mix_wettp(Integer companyid,Date fromdate, Date todate,List<String> productid);
		 
		 public  Double  daily_mix_wettp_amount(Integer companyid,Date fromdate, Date todate,List<String> productid);
		 
		 public  Double  daily_mix_wettp_bonus(Integer companyid,Date fromdate, Date todate,List<String> productid);
		 
		 public  Long  daily_mix_wettp_trancid(Integer companyid,Date fromdate, Date todate,List<String> productid);
		 
		 public  List<Wettp> outside_report_unclaim(Integer companyid,List<String> frmproductid,List<String> toproductid,Date fromdate,Date todate);
		 
		 public List<Wettp> outside_report_claim(Integer companyid,List<String> frmproductid,List<String> toproductid,Date fromdate,Date todate,List<String> status);
	

}
