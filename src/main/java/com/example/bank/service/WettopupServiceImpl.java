package com.example.bank.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.Wettopup;
import com.example.bank.repository.WettopupRepository;

@Component
public class WettopupServiceImpl implements WettopupService {

	@Autowired
	private WettopupRepository wettopupRepository;
	
	@Override
	public Optional<Wettopup> findWettopupbyId(Integer id) {
		return null;
	}

	@Override
	public List<Wettopup> getallUnclaimlist() {
		return wettopupRepository.getallUnclaimlist();
	}

	@Override
	public List<Wettopup> getpromoredem(Date fromdate, Date todate, List<String> promoname, List<String> userid, Integer companyid) {
		return wettopupRepository.getpromoredem(fromdate, todate, promoname, userid,companyid);
	}

	@Override
	public List<Wettopup> gettopupbypromo(Date fromdate, Date todate, String promoname, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.gettopupbypromo(fromdate, todate, promoname, companyid);
	}

	@Override
	public List<Wettopup> getpromobyuserid(Date fromdate, Date todate, String userid, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.getpromobyuserid(fromdate, todate, userid, companyid);
	}

	@Override
	public Double getamountbypromo(Date fromdate, Date todate, String userid, String promoname, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.getamountbypromo(fromdate, todate, userid, promoname, companyid);
	}

	@Override
	public Double getamountbyuserid(Date fromdate, Date todate, String userid, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.getamountbyuserid(fromdate, todate, userid, companyid);
	}

	@Override
	public List<String> getuseridbytopuo(Date fromdate, Date todate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.getuseridbytopuo(fromdate, todate, companyid);
	}

	@Override
	public Double getsumamountbyuserid(String userid,String productid, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.getsumamountbyuserid(userid, productid, companyid);
	}

	@Override
	public List<Wettopup> topupfornonactive(String userid, String productid, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.topupfornonactive(userid, productid, companyid);
	}

	@Override
	public List<Wettopup> topupfornonactiveexcludefree(String userid, String productid, Integer companyid, Date fromdate,Date todate ) {
		// TODO Auto-generated method stub
		return wettopupRepository.topupfornonactiveexcludefree(userid, productid, companyid,fromdate,todate);
	}

	@Override
	public Double vipamount(List<String> userid, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.vipamount(userid, companyid);
	}

	@Override
	public List<Wettopup> distinctmonthandyear(Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.distinctmonthandyear(companyid);
	}

	@Override
	public List<Wettopup> monthlysalestopup(String month, String year, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.monthlysalestopup(month, year, companyid);
	}

	@Override
	public List<Wettopup> monthlysalesbonus(String month, String year, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.monthlysalesbonus(month, year, companyid);
	}

	@Override
	public List<Wettopup> monthlysalesadjustment(String month, String year, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.monthlysalesadjustment(month, year, companyid);
	}

	@Override
	public List<Wettopup> claimviadate(Date fromdate, Date todate, Integer companyid, String depositvia) {
		// TODO Auto-generated method stub
		return wettopupRepository.claimviadate(fromdate, todate, companyid, depositvia);
	}

	@Override
	public List<Wettopup> totalclaimviadate(Date fromdate, Date todate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.totalclaimviadate(fromdate, todate, companyid);
	}

	@Override
	public Double topupamount_newplayerreg(List<String> userid, Integer companyid, List<String> productid) {
		// TODO Auto-generated method stub
		return wettopupRepository.topupamount_newplayerreg(userid, companyid, productid);
	}

	@Override
	public Long retopup_newplayerreg(List<String> userid, Integer companyid, List<String> productid) {
		// TODO Auto-generated method stub
		return wettopupRepository.retopup_newplayerreg(userid, companyid, productid);
	}

	@Override
	public Double bankcashflow(Date date, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.bankcashflow(date, companyid);
	}

	@Override
	public List<Wettopup> distinct(String month, String year, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.distinct(month, year, companyid);
	}

	@Override
	public List bankcashflow_totolamount(String month, String year, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.bankcashflow_totolamount(month, year, companyid);
	}

	@Override
	public Double memberaccount_topup(String userid, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.memberaccount_topup(userid, companyid);
	}

	@Override
	public Double memberaccount_bonus(String userid, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.memberaccount_bonus(userid, companyid);
	}

	@Override
	public Double memberaccount_adjustment(String userid, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.memberaccount_adjustment(userid, companyid);
	}

	@Override
	public List<Wettopup> distinctadjcategory(Date fromdate, Date todate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.distinctadjcategory(fromdate, todate, companyid);
	}

	@Override
	public Long adjustment_totaltransaction(Date fromdate, Date todate, Integer companyid, String adjustment) {
		// TODO Auto-generated method stub
		return wettopupRepository.adjustment_totaltransaction(fromdate, todate, companyid, adjustment);
	}

	@Override
	public Double adjustment_totalamount(Date fromdate, Date todate, Integer companyid, String adjustment) {
		// TODO Auto-generated method stub
		return wettopupRepository.adjustment_totalamount(fromdate, todate, companyid, adjustment);
	}

	@Override
	public Long adjustment__report_totaltransaction(Date fromdate, Date todate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.adjustment__report_totaltransaction(fromdate, todate, companyid);
	}

	@Override
	public Double adjustment_report_totalamount(Date fromdate, Date todate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.adjustment_report_totalamount(fromdate, todate, companyid);
	}

	@Override
	public List<Wettopup> distinctuserid(Date fromdate, Date todate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.distinctuserid(fromdate, todate, companyid);
	}

	@Override
	public Double playercashflow_topup(Date fromdate, Date todate, Integer companyid, String userid) {
		// TODO Auto-generated method stub
		return wettopupRepository.playercashflow_topup(fromdate, todate, companyid, userid);
	}

	@Override
	public Double playercashflow_bonus(Date fromdate, Date todate, Integer companyid, String userid) {
		// TODO Auto-generated method stub
		return wettopupRepository.playercashflow_bonus(fromdate, todate, companyid, userid);
	}

	@Override
	public Double playercashflow_adjustment(Date fromdate, Date todate, Integer companyid, String userid) {
		// TODO Auto-generated method stub
		return wettopupRepository.playercashflow_adjustment(fromdate, todate, companyid, userid);
	}

	@Override
	public Double playercashflow__report_topup(Date fromdate, Date todate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.playercashflow__report_topup(fromdate, todate, companyid);
	}

	@Override
	public Double playercashflow__report_bonus(Date fromdate, Date todate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.playercashflow__report_bonus(fromdate, todate, companyid);
	}

	@Override
	public Double playercashflow__report_adjustment(Date fromdate, Date todate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.playercashflow__report_adjustment(fromdate, todate, companyid);
	}

	@Override
	public List<Wettopup> freebonus(Date fromdate, Date todate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.freebonus(fromdate, todate, companyid);
	}

	@Override
	public Double productdaily_bonus(Date fromdate, Integer companyid, String product) {
		// TODO Auto-generated method stub
		return wettopupRepository.productdaily_bonus(fromdate, companyid, product);
	}

	@Override
	public Double productdaily_bonus_total(Date fromdate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.productdaily_bonus_total(fromdate, companyid);
	}

	@Override
	public Double productdaily_topup_total(Date fromdate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.productdaily_topup_total(fromdate, companyid);
	}

	@Override
	public Double productdaily_adjustment_total(Date fromdate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.productdaily_adjustment_total(fromdate, companyid);
	}

	@Override
	public Double productdaily_master_total(Date fromdate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.productdaily_master_total(fromdate, companyid);
	}

	@Override
	public Double productdaily_topup(Date fromdate, Integer companyid, String product) {
		// TODO Auto-generated method stub
		return wettopupRepository.productdaily_topup(fromdate, companyid, product);
	}

	@Override
	public Double productdaily_adjustment(Date fromdate, Integer companyid, String product) {
		// TODO Auto-generated method stub
		return wettopupRepository.productdaily_adjustment(fromdate, companyid, product);
	}

	@Override
	public Double productdaily_master(Date fromdate, Integer companyid, String product) {
		// TODO Auto-generated method stub
		return wettopupRepository.productdaily_master(fromdate, companyid, product);
	}

	@Override
	public List<Wettopup> topwlplayer(Date fromdate, Date todate, Integer companyid, List<String> productid) {
		// TODO Auto-generated method stub
		return wettopupRepository.topwlplayer(fromdate, todate, companyid, productid);
	}

	@Override
	public Double topwlplayer_topup(String userid, Integer companyid, String productid, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wettopupRepository.topwlplayer_topup(userid, companyid, productid, fromdate, todate);
	}

	@Override
	public List<Wettopup> latetopupwithdraw(Date fromdate, Date todate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.latetopupwithdraw(fromdate, todate, companyid);
	}

	@Override
	public List<Wettopup> cashflowproduct(Date fromdate, Date todate, Integer companyid, String productid) {
		// TODO Auto-generated method stub
		return wettopupRepository.cashflowproduct(fromdate, todate, companyid, productid);
	}

	@Override
	public Double topwlplayer_adjustment(String userid, Integer companyid, String productid, Date fromdate,
			Date todate) {
		// TODO Auto-generated method stub
		return wettopupRepository.topwlplayer_adjustment(userid, companyid, productid, fromdate, todate);
	}

	@Override
	public Double topwlplayer_bonus(String userid, Integer companyid, String productid, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wettopupRepository.topwlplayer_bonus(userid, companyid, productid, fromdate, todate);
	}

	@Override
	public Double cashflowproduct_topup_total(Integer companyid, String productid, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wettopupRepository.cashflowproduct_topup_total(companyid, productid, fromdate, todate);
	}

	@Override
	public Double cashflowproduct_adjustment_total(Integer companyid, String productid, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wettopupRepository.cashflowproduct_adjustment_total(companyid, productid, fromdate, todate);
	}

	@Override
	public Double cashflowproduct_bonus_total(Integer companyid, String productid, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wettopupRepository.cashflowproduct_bonus_total(companyid, productid, fromdate, todate);
	}

	@Override
	public Double cashflowbank_topup(String userid, Integer companyid, String bank, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wettopupRepository.cashflowbank_topup(userid, companyid, bank, fromdate, todate);
	}

	@Override
	public Double cashflowbank_topup_total(Integer companyid, String bank, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wettopupRepository.cashflowbank_topup_total(companyid, bank, fromdate, todate);
	}

	@Override
	public Double cashflowbank_topup__report_total(Integer companyid, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wettopupRepository.cashflowbank_topup__report_total(companyid, fromdate, todate);
	}

	@Override
	public List<Wettopup> cashflowbank(Date fromdate, Date todate, Integer companyid, String bank) {
		// TODO Auto-generated method stub
		return wettopupRepository.cashflowbank(fromdate, todate, companyid, bank);
	}

	@Override
	public List<Wettopup> monthlyactiveplayer(Integer companyid, Date fromdate, String productid) {
		// TODO Auto-generated method stub
		return wettopupRepository.monthlyactiveplayer(companyid, fromdate, productid);
	}

	@Override
	public List<Wettopup> monthlyactiveplayer_total(Integer companyid, Date fromdate, String productid) {
		// TODO Auto-generated method stub
		return wettopupRepository.monthlyactiveplayer_total(companyid, fromdate, productid);
	}

	@Override
	public List<Wettopup> monthlyactiveplayer_chart(Integer companyid, Date fromdate, List<String> productid) {
		// TODO Auto-generated method stub
		return wettopupRepository.monthlyactiveplayer_chart(companyid, fromdate, productid);
	}

	@Override
	public List<Wettopup> monthlyactiveplayer_average(Integer companyid, Date fromdate, List<String> productid) {
		// TODO Auto-generated method stub
		return wettopupRepository.monthlyactiveplayer_average(companyid, fromdate, productid);
	}

	@Override
	public List<Wettopup> monthlyactiveplayer_total(Integer companyid, Date fromdate) {
		// TODO Auto-generated method stub
		return wettopupRepository.monthlyactiveplayer_total(companyid, fromdate);
	}

	@Override
	public List<Wettopup> lasttopupandamount(Integer companyid, String product, String userid) {
		// TODO Auto-generated method stub
		return wettopupRepository.lasttopupandamount(companyid, product, userid);
	}

	@Override
	public Double getsumbonusbyuserid(String userid, String productid, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.getsumbonusbyuserid(userid, productid, companyid);
	}

	@Override
	public Double getadjustmentbonusbyuserid(String userid, String productid, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.getadjustmentbonusbyuserid(userid, productid, companyid);
	}

	@Override
	public Double bonus_newplayerreg(List<String> userid, Integer companyid, List<String> productid) {
		// TODO Auto-generated method stub
		return wettopupRepository.bonus_newplayerreg(userid, companyid, productid);
	}

	@Override
	public Double adjustment_newplayerreg(List<String> userid, Integer companyid, List<String> productid) {
		// TODO Auto-generated method stub
		return wettopupRepository.adjustment_newplayerreg(userid, companyid, productid);
	}

	@Override
	public Double topvswithdraw_topup(Date fromdate, Integer companyid, List<String> product) {
		// TODO Auto-generated method stub
		return wettopupRepository.topvswithdraw_topup(fromdate, companyid, product);
	}

	@Override
	public List<Wettopup> topvswithdraw_topuprange_hundred(String month, String year, Integer companyid,
			List<String> product) {
		// TODO Auto-generated method stub
		return wettopupRepository.topvswithdraw_topuprange_hundred(month, year, companyid, product);
	}

	@Override
	public List<Wettopup> topvswithdraw_topuprange_hundredone(String month, String year, Integer companyid,
			List<String> product) {
		// TODO Auto-generated method stub
		return wettopupRepository.topvswithdraw_topuprange_hundredone(month, year, companyid, product);
	}

	@Override
	public List<Wettopup> topvswithdraw_topuprange_fivehundred(String month, String year, Integer companyid,
			List<String> product) {
		// TODO Auto-generated method stub
		return wettopupRepository.topvswithdraw_topuprange_fivehundred(month, year, companyid, product);
	}

	@Override
	public List<Wettopup> topvswithdraw_topup_transac(Date date, Integer companyid, List<String> product) {
		// TODO Auto-generated method stub
		return wettopupRepository.topvswithdraw_topup_transac(date, companyid, product);
	}

	@Override
	public List<Wettopup> freebonus_userid(Date fromdate, Date todate, Integer companyid, String userid,
			String product) {
		// TODO Auto-generated method stub
		return wettopupRepository.freebonus_userid(fromdate, todate, companyid, userid, product);
	}

	@Override
	public List<Wettopup> lasttopupandamount_customerlist(Integer companyid, String product, String userid,
			Date fromdate,Date todate) {
		// TODO Auto-generated method stub
		return wettopupRepository.lasttopupandamount_customerlist(companyid, product, userid, fromdate, todate);
	}

	@Override
	public List<Wettopup> daily_mix_wettopup(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		// TODO Auto-generated method stub
		return wettopupRepository.daily_mix_wettopup(companyid, fromdate, todate, productid);
	}

	@Override
	public Double daily_mix_topup_amount(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		// TODO Auto-generated method stub
		return wettopupRepository.daily_mix_topup_amount(companyid, fromdate, todate, productid);
	}

	@Override
	public Double daily_mix_topup_bonus(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		// TODO Auto-generated method stub
		return wettopupRepository.daily_mix_topup_bonus(companyid, fromdate, todate, productid);
	}

	@Override
	public Long daily_mix_topup_trancid(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		// TODO Auto-generated method stub
		return wettopupRepository.daily_mix_topup_trancid(companyid, fromdate, todate, productid);
	}

	@Override
	public List<Wettopup> outside_report_unclaim(List<String> bank, Integer companyid, List<String> productid,
			Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wettopupRepository.outside_report_unclaim(bank, companyid, productid, fromdate, todate);
	}

	@Override
	public List<Wettopup> outside_report_claim(List<String> bank, Integer companyid, List<String> productid,
			Date fromdate, Date todate, List<String> status) {
		// TODO Auto-generated method stub
		return wettopupRepository.outside_report_claim(bank, companyid, productid, fromdate, todate, status);
	}

	@Override
	public List<Wettopup> outside_report_madj_main_pending(Integer companyid, List<String> productid,
			Date fromdate, Date todate, List<String> adjustmenttype, List<String> adjustmentcategory) {
		// TODO Auto-generated method stub
		return wettopupRepository.outside_report_madj_main_pending( companyid, productid, fromdate, todate, adjustmenttype, adjustmentcategory);
	}

	@Override
	public List<Wettopup> outside_report_madj_main_done( Integer companyid, List<String> productid,
			Date fromdate, Date todate, List<String> status, List<String> adjustmenttype,
			List<String> adjustmentcategory) {
		// TODO Auto-generated method stub
		return wettopupRepository.outside_report_madj_main_done( companyid, productid, fromdate, todate, status, adjustmenttype, adjustmentcategory);
	}

	@Override
	public List<Wettopup> outside_report_madj_birth_pending(Integer companyid, List<String> productid, Date fromdate,
			Date todate, List<String> adjustmenttype, List<String> adjustmentcategory) {
		// TODO Auto-generated method stub
		return wettopupRepository.outside_report_madj_birth_pending(companyid, productid, fromdate, todate, adjustmenttype, adjustmentcategory);
	}

	@Override
	public List<Wettopup> outside_report_madj_birth_done(Integer companyid, List<String> productid, Date fromdate,
			Date todate, List<String> status, List<String> adjustmenttype, List<String> adjustmentcategory) {
		// TODO Auto-generated method stub
		return wettopupRepository.outside_report_madj_birth_done(companyid, productid, fromdate, todate, status, adjustmenttype, adjustmentcategory);
	}

	@Override
	public List<Wettopup> outside_report_madj_late_pending(Integer companyid, List<String> productid, Date fromdate,
			Date todate, List<String> adjustmenttype, List<String> adjustmentcategory) {
		// TODO Auto-generated method stub
		return wettopupRepository.outside_report_madj_late_pending(companyid, productid, fromdate, todate, adjustmenttype, adjustmentcategory);
	}

	@Override
	public List<Wettopup> outside_report_madj_late_done(Integer companyid, List<String> productid, Date fromdate,
			Date todate, List<String> status, List<String> adjustmenttype, List<String> adjustmentcategory) {
		// TODO Auto-generated method stub
		return wettopupRepository.outside_report_madj_late_done(companyid, productid, fromdate, todate, status, adjustmenttype, adjustmentcategory);
	}

	@Override
	public List<Wettopup> outside_report_madj_day_pending(Integer companyid, List<String> productid, Date fromdate,
			Date todate, List<String> adjustmenttype, List<String> adjustmentcategory) {
		// TODO Auto-generated method stub
		return wettopupRepository.outside_report_madj_day_pending(companyid, productid, fromdate, todate, adjustmenttype, adjustmentcategory);
	}

	@Override
	public List<Wettopup> outside_report_day_main_done(Integer companyid, List<String> productid, Date fromdate,
			Date todate, List<String> status, List<String> adjustmenttype, List<String> adjustmentcategory) {
		// TODO Auto-generated method stub
		return wettopupRepository.outside_report_day_main_done(companyid, productid, fromdate, todate, status, adjustmenttype, adjustmentcategory);
	}

	@Override
	public Double daily_mix_topup_madj_amount(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		// TODO Auto-generated method stub
		return wettopupRepository.daily_mix_topup_madj_amount(companyid, fromdate, todate, productid);
	}

	@Override
	public List<Wettopup> homependingtopup(Integer companyid, List<String> productid, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wettopupRepository.homependingtopup(companyid, productid, fromdate, todate);
	}

	@Override
	public List<Wettopup> freebonus_home(Date fromdate, Date todate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettopupRepository.freebonus_home(fromdate, todate, companyid);
	}

	@Override
	public List<Wettopup> outside_report_claim_bonus(List<String> bank, Integer companyid, List<String> productid,
			Date fromdate, Date todate, List<String> status) {
		// TODO Auto-generated method stub
		return wettopupRepository.outside_report_claim_bonus(bank, companyid, productid, fromdate, todate, status);
	}
	
	
}
