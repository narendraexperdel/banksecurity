package com.example.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.Wetwithdraw;
import com.example.bank.repository.WetwithdrawRepository;

@Component
public class WetwithdrawServiceImpl implements WetwithdrawService{
	
	@Autowired
	WetwithdrawRepository wetwithdrawRepository;

	@Override
	public Double getamountbyuseridinwithdraw(Date fromdate, Date todate, String userid, Integer companyid) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.getamountbyuseridinwithdraw(fromdate, todate, userid, companyid);
	}

	@Override
	public List<Wetwithdraw> monthlysalestopup(String month, String year, Integer companyid) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.monthlysalestopup(month, year, companyid);
	}

	@Override
	public Double withdrawamount_newplayerreg(List<String> userid, Integer companyid, List<String> productid) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.withdrawamount_newplayerreg(userid, companyid, productid);
	}

	@Override
	public Double bankcashflow(Date date, Integer companyid) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.bankcashflow(date, companyid);
	}

	@Override
	public List bankcashflow_totolamount(String month, String year, Integer companyid) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.bankcashflow_totolamount(month, year, companyid);
	}

	@Override
	public Double memberaccount_withdraw(String userid, Integer companyid) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.memberaccount_withdraw(userid, companyid);
	}

	@Override
	public Double playercashflow_withdraw(Date fromdate, Date todate, Integer companyid, String userid) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.playercashflow_withdraw(fromdate, todate, companyid, userid);
	}

	@Override
	public Double playercashflow_report_withdraw(Date fromdate, Date todate, Integer companyid) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.playercashflow_report_withdraw(fromdate, todate, companyid);
	}

	@Override
	public Double productdaily_withdraw_total(Date fromdate, Integer companyid) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.productdaily_withdraw_total(fromdate, companyid);
	}

	@Override
	public Double productdaily_withdraw(Date fromdate, Integer companyid, String product) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.productdaily_withdraw(fromdate, companyid, product);
	}

	@Override
	public Double topwlplayer_withdraw(String userid, Integer companyid, String productid, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.topwlplayer_withdraw(userid, companyid, productid, fromdate, todate);
	}

	@Override
	public List<Wetwithdraw> latewithdraw(Date fromdate, Date todate, Integer companyid) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.latewithdraw(fromdate, todate, companyid);
	}

	@Override
	public Double cashflowproduct_withdraw_total(Integer companyid, String productid, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.cashflowproduct_withdraw_total(companyid, productid, fromdate, todate);
	}

	@Override
	public Double cashflowbank_withdraw(String userid, Integer companyid, String bank, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.cashflowbank_withdraw(userid, companyid, bank, fromdate, todate);
	}

	@Override
	public Double cashflowbank_withdraw_total(Integer companyid, String bank, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.cashflowbank_withdraw_total(companyid, bank, fromdate, todate);
	}

	@Override
	public Double cashflowbank_withdraw_report_total(Integer companyid, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.cashflowbank_withdraw_report_total(companyid, fromdate, todate);
	}

	@Override
	public List<Wetwithdraw> cashflowproduct_withdraw(Date fromdate, Date todate, Integer companyid, String productid) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.cashflowproduct_withdraw(fromdate, todate, companyid, productid);
	}

	@Override
	public List<Wetwithdraw> cashflowbank_withdraw(Date fromdate, Date todate, Integer companyid, String bank) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.cashflowbank_withdraw(fromdate, todate, companyid, bank);
	}

	@Override
	public Double getsumamountbyuserid_withdraw(String userid, String productid, Integer companyid) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.getsumamountbyuserid_withdraw(userid, productid, companyid);
	}

	@Override
	public List<Wetwithdraw> lasttopupandamount(Integer companyid, String product, String userid) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.lasttopupandamount(companyid, product, userid);
	}

	@Override
	public Double topvswithdraw_withdraw(Date fromdate, Integer companyid, List<String> product) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.topvswithdraw_withdraw(fromdate, companyid, product);
	}

	@Override
	public List<Wetwithdraw> topvswithdraw_topuprange_hundred(String month, String year, Integer companyid,
			List<String> product) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.topvswithdraw_topuprange_hundred(month, year, companyid, product);
	}

	@Override
	public List<Wetwithdraw> topvswithdraw_topuprange_hundredone(String month, String year, Integer companyid,
			List<String> product) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.topvswithdraw_topuprange_hundredone(month, year, companyid, product);
	}

	@Override
	public List<Wetwithdraw> topvswithdraw_topuprange_fivehundred(String month, String year, Integer companyid,
			List<String> product) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.topvswithdraw_topuprange_fivehundred(month, year, companyid, product);
	}

	@Override
	public List<Wetwithdraw> topvswithdraw_withdraw_transac(Date date, Integer companyid, List<String> product) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.topvswithdraw_withdraw_transac(date, companyid, product);
	}

	@Override
	public List<Wetwithdraw> daily_mix_wetwithdraw(Integer companyid, Date fromdate, Date todate,
			List<String> productid) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.daily_mix_wetwithdraw(companyid, fromdate, todate, productid);
	}

	@Override
	public Double daily_mix_withdraw_amount(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.daily_mix_withdraw_amount(companyid, fromdate, todate, productid);
	}

	@Override
	public Double daily_mix_withdraw_bonus(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long daily_mix_withdraw_trancid(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.daily_mix_withdraw_trancid(companyid, fromdate, todate, productid);
	}

	@Override
	public List<Wetwithdraw> outside_report_unclaim(Integer companyid, List<String> productid, Date fromdate,
			Date todate) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.outside_report_unclaim(companyid, productid, fromdate, todate);
	}

	@Override
	public List<Wetwithdraw> outside_report_claim(Integer companyid, List<String> productid, Date fromdate, Date todate,
			List<String> status) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.outside_report_claim(companyid, productid, fromdate, todate, status);
	}

	@Override
	public List<Wetwithdraw> outside_report_unclaim_gst(Integer companyid, List<String> productid, Date fromdate,
			Date todate) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.outside_report_unclaim_gst(companyid, productid, fromdate, todate);
	}

	@Override
	public List<Wetwithdraw> outside_report_claim_gst(Integer companyid, List<String> productid, Date fromdate,
			Date todate, List<String> status) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.outside_report_claim_gst(companyid, productid, fromdate, todate, status);
	}

	@Override
	public Double daily_mix_withdraw_taxamount(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		// TODO Auto-generated method stub
		return wetwithdrawRepository.daily_mix_withdraw_taxamount(companyid, fromdate, todate, productid);
	}

}
