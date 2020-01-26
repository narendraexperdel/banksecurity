package com.example.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.ReferalBonus;

@Service
public interface ReferalbonusService {

	public Double productdaily_referal(Date fromdate, Integer companyid,String product);
	
	public Double productdaily_referal_total(Date fromdate, Integer companyid);
	
	 public Double cashflowproduct_referal(String userid,Integer companyid, String productid,Date fromdate,Date todate );

	 public Double cashflowproduct_referal_total(Integer companyid, String productid,Date fromdate,Date todate );
	 
	 public Double cashflowproduct_referal_report_total(Integer companyid,Date fromdate,Date todate );
	 
	 public List<ReferalBonus> cashflowproduct_referal(Date fromdate,Date todate, Integer companyid, String productid);
	 
     public List<ReferalBonus> outside_report_referal_pending(Integer companyid,List<String> productid,Date fromdate,Date todate);
	 
	 public List<ReferalBonus> outside_report_referal_done(Integer companyid,List<String> productid,Date fromdate,Date todate);
}
