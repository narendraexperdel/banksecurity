package com.example.bank.repository;

import java.util.Date;
import java.util.List;

import com.example.bank.model.Wettopup;

public interface WettopupCustomRepository {
	
	public List<Wettopup> getallUnclaimlist();
	
	public List<Wettopup> getpromoredem(Date fromdate, Date todate, List<String> promoname,List<String> userid,Integer companyid);
	
	public List<Wettopup> gettopupbypromo(Date fromdate, Date todate,String promoname,Integer companyid);
	
	public List<Wettopup> getpromobyuserid(Date fromdate, Date todate,String userid,Integer companyid);
	
	public Double getamountbypromo(Date fromdate, Date todate,String userid,String promoname,Integer companyid);
	
	public Double getamountbyuserid(Date fromdate, Date todate,String userid,Integer companyid);
	
	public List<String> getuseridbytopuo(Date fromdate, Date todate,Integer companyid);
	
	public Double getsumamountbyuserid(String userid,String productid,Integer companyid);
	
	public List<Wettopup> topupfornonactive(String userid,String productid,Integer companyid);
	
	public List<Wettopup> topupfornonactiveexcludefree(String userid,String productid,Integer companyid, Date fromdate,Date todate);
	
	public Double vipamount(List<String> userid,Integer companyid);
	
	public List<Wettopup> distinctmonthandyear(Integer companyid);
	
	public List<Wettopup> monthlysalestopup(String month, String year, Integer companyid);
	
	public List<Wettopup> monthlysalesbonus(String month, String year, Integer companyid);
	
	public List<Wettopup> monthlysalesadjustment(String month, String year, Integer companyid);
	
	public List<Wettopup> claimviadate(Date fromdate, Date todate, Integer companyid,String depositvia);
	
	public List<Wettopup> totalclaimviadate(Date fromdate, Date todate, Integer companyid);
	
	public Double topupamount_newplayerreg(List<String> userid,Integer companyid,List<String> productid);
	
	public Long retopup_newplayerreg(List<String> userid,Integer companyid,List<String> productid);
	
	public Double bankcashflow(Date date,Integer companyid);
	
	public List<Wettopup> distinct(String month,String year,Integer companyid);
	
	public List bankcashflow_totolamount(String month, String year, Integer companyid);
	
	public Double memberaccount_topup(String userid,Integer companyid);
	
	public Double memberaccount_bonus(String userid,Integer companyid);
	
	public Double memberaccount_adjustment(String userid,Integer companyid);
	
	public List<Wettopup> distinctadjcategory(Date fromdate, Date todate, Integer companyid);
	
	public Long adjustment_totaltransaction(Date fromdate, Date todate, Integer companyid,String adjustment);
	
	public Double adjustment_totalamount(Date fromdate, Date todate, Integer companyid,String adjustment);
	
    public Long adjustment__report_totaltransaction(Date fromdate, Date todate, Integer companyid);
	
	public Double adjustment_report_totalamount(Date fromdate, Date todate, Integer companyid);
	
	public List<Wettopup> distinctuserid(Date fromdate, Date todate, Integer companyid);
	
	public Double playercashflow_topup(Date fromdate, Date todate, Integer companyid,String userid);
	
	public Double playercashflow_bonus(Date fromdate, Date todate, Integer companyid,String userid);
	
	public Double playercashflow_adjustment(Date fromdate, Date todate, Integer companyid,String userid);
	
	public Double playercashflow__report_topup(Date fromdate, Date todate, Integer companyid);
	
	public Double playercashflow__report_bonus(Date fromdate, Date todate, Integer companyid);
	
	public Double playercashflow__report_adjustment(Date fromdate, Date todate, Integer companyid);
	
	public List<Wettopup> freebonus(Date fromdate, Date todate, Integer companyid);
	
	 public Double productdaily_bonus(Date fromdate, Integer companyid,String product);
	 
	 public Double productdaily_topup(Date fromdate, Integer companyid,String product);
	 
	 public Double productdaily_adjustment(Date fromdate, Integer companyid,String product);
	 
	 public Double productdaily_master(Date fromdate, Integer companyid,String product);
	 
     public Double productdaily_bonus_total(Date fromdate, Integer companyid);
	 
	 public Double productdaily_topup_total(Date fromdate, Integer companyid);
	 
	 public Double productdaily_adjustment_total(Date fromdate, Integer companyid);
	 
	 public Double productdaily_master_total(Date fromdate, Integer companyid);
	 
	 public List<Wettopup> topwlplayer(Date fromdate,Date todate, Integer companyid, List<String> productid);

	 public Double topwlplayer_topup(String userid,Integer companyid, String productid,Date fromdate,Date todate );
	 
	 public List<Wettopup> latetopupwithdraw(Date fromdate, Date todate, Integer companyid);
	 
	 public List<Wettopup> cashflowproduct(Date fromdate,Date todate, Integer companyid, String productid);
	 
	 public Double topwlplayer_adjustment(String userid,Integer companyid, String productid,Date fromdate,Date todate );
	 
	 public Double topwlplayer_bonus(String userid,Integer companyid, String productid,Date fromdate,Date todate );
	 
	 public Double cashflowproduct_topup_total(Integer companyid, String productid,Date fromdate,Date todate);
	 
      public Double cashflowproduct_adjustment_total(Integer companyid, String productid,Date fromdate,Date todate );
 	 
	 public Double cashflowproduct_bonus_total(Integer companyid, String productid,Date fromdate,Date todate );
	 
	 public List<Wettopup> cashflowbank(Date fromdate,Date todate, Integer companyid, String bank);
	 
	 public Double cashflowbank_topup(String userid,Integer companyid, String bank,Date fromdate,Date todate );
	 
	 public Double cashflowbank_topup_total(Integer companyid, String bank,Date fromdate,Date todate );
	 
	 public Double cashflowbank_topup__report_total(Integer companyid,Date fromdate,Date todate );
	 
	 public  List<Wettopup>  monthlyactiveplayer(Integer companyid,Date fromdate,String productid);
	 
	 public  List<Wettopup>  monthlyactiveplayer_total(Integer companyid,Date fromdate,String productid);
	 
	 public  List<Wettopup>  monthlyactiveplayer_chart(Integer companyid,Date fromdate,List<String> productid);
	 
	 public  List<Wettopup>  monthlyactiveplayer_average(Integer companyid,Date fromdate,List<String> productid);
	 
	 public  List<Wettopup>  monthlyactiveplayer_total(Integer companyid,Date fromdate);
	 
	 public  List<Wettopup>  lasttopupandamount(Integer companyid,String product,String userid);
	 
	 public Double getsumbonusbyuserid(String userid,String productid,Integer companyid);
	 
	 public Double getadjustmentbonusbyuserid(String userid,String productid,Integer companyid);
	 
	 public Double bonus_newplayerreg(List<String> userid,Integer companyid,List<String> productid);
	 
	 public Double adjustment_newplayerreg(List<String> userid,Integer companyid,List<String> productid);
	 
	 public Double topvswithdraw_topup(Date fromdate, Integer companyid,List<String> product);
	 
	 public List<Wettopup> topvswithdraw_topuprange_hundred(String month,String year, Integer companyid,List<String> product);
	 
	 public List<Wettopup> topvswithdraw_topuprange_hundredone(String month,String year, Integer companyid,List<String> product);
	 
	 public List<Wettopup> topvswithdraw_topuprange_fivehundred(String month,String year, Integer companyid,List<String> product);
	 
	 public List<Wettopup> topvswithdraw_topup_transac(Date date, Integer companyid,List<String> product);
	 
	 public List<Wettopup> freebonus_userid(Date fromdate, Date todate, Integer companyid,String userid,String product);
	 
	 public  List<Wettopup>  lasttopupandamount_customerlist(Integer companyid,String product,String userid, Date fromdate,Date todate);
	 
	 public  List<Wettopup>  daily_mix_wettopup(Integer companyid,Date fromdate, Date todate,List<String> productid);
	 
	 public  Double  daily_mix_topup_amount(Integer companyid,Date fromdate, Date todate,List<String> productid);
	 
	 public  Double  daily_mix_topup_madj_amount(Integer companyid,Date fromdate, Date todate,List<String> productid);
	 
	 public  Double  daily_mix_topup_bonus(Integer companyid,Date fromdate, Date todate,List<String> productid);
	 
	 public  Long  daily_mix_topup_trancid(Integer companyid,Date fromdate, Date todate,List<String> productid);
	 
	 public List<Wettopup> outside_report_unclaim(List<String> bank,Integer companyid,List<String> productid,Date fromdate,Date todate);
	 
	 public List<Wettopup> outside_report_claim(List<String> bank,Integer companyid,List<String> productid,Date fromdate,Date todate,List<String> status);
	 
	 public List<Wettopup> outside_report_claim_bonus(List<String> bank,Integer companyid,List<String> productid,Date fromdate,Date todate,List<String> status);
	 
     public List<Wettopup> outside_report_madj_main_pending(Integer companyid,List<String> productid,Date fromdate,Date todate,List<String> adjustmenttype, List<String> adjustmentcategory);
	 
	 public List<Wettopup> outside_report_madj_main_done(Integer companyid,List<String> productid,Date fromdate,Date todate,List<String> status,List<String> adjustmenttype, List<String> adjustmentcategory);
	 
 public List<Wettopup> outside_report_madj_birth_pending(Integer companyid,List<String> productid,Date fromdate,Date todate,List<String> adjustmenttype, List<String> adjustmentcategory);
	 
	 public List<Wettopup> outside_report_madj_birth_done(Integer companyid,List<String> productid,Date fromdate,Date todate,List<String> status,List<String> adjustmenttype, List<String> adjustmentcategory);
	 
 public List<Wettopup> outside_report_madj_late_pending(Integer companyid,List<String> productid,Date fromdate,Date todate,List<String> adjustmenttype, List<String> adjustmentcategory);
	 
	 public List<Wettopup> outside_report_madj_late_done(Integer companyid,List<String> productid,Date fromdate,Date todate,List<String> status,List<String> adjustmenttype, List<String> adjustmentcategory);
	 
 public List<Wettopup> outside_report_madj_day_pending(Integer companyid,List<String> productid,Date fromdate,Date todate,List<String> adjustmenttype, List<String> adjustmentcategory);
	 
	 public List<Wettopup> outside_report_day_main_done(Integer companyid,List<String> productid,Date fromdate,Date todate,List<String> status,List<String> adjustmenttype, List<String> adjustmentcategory);
	 
	 public List<Wettopup> homependingtopup(Integer companyid,List<String> productid,Date fromdate,Date todate);
	 
	 public List<Wettopup> freebonus_home(Date fromdate, Date todate, Integer companyid);
}
