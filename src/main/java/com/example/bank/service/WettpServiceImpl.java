package com.example.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.Wettp;
import com.example.bank.repository.WettpRepository;

@Component
public class WettpServiceImpl implements WettpService {
	
	@Autowired
	WettpRepository wettpRepository;

	@Override
	public Double player_transferin_amount(Date fromdate, Date todate, Integer companyid, String touserid) {
		// TODO Auto-generated method stub
		return wettpRepository.player_transferin_amount(fromdate, todate, companyid, touserid);
	}

	@Override
	public Double player_transferout_amount(Date fromdate, Date todate, Integer companyid, String touserid) {
		// TODO Auto-generated method stub
		return wettpRepository.player_transferout_amount(fromdate, todate, companyid, touserid);
	}

	@Override
	public Double player_transferin__report_amount(Date fromdate, Date todate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettpRepository.player_transferin__report_amount(fromdate, todate, companyid);
	}

	@Override
	public Double player_transferout_report_amount(Date fromdate, Date todate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettpRepository.player_transferout_report_amount(fromdate, todate, companyid);
	}

	@Override
	public Double productdaily_in(Date fromdate, Integer companyid, String product) {
		// TODO Auto-generated method stub
		return wettpRepository.productdaily_in(fromdate, companyid, product);
	}

	@Override
	public Double productdaily_out(Date fromdate, Integer companyid, String product) {
		// TODO Auto-generated method stub
		return wettpRepository.productdaily_out(fromdate, companyid, product);
	}

	@Override
	public Double productdaily_in_total(Date fromdate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettpRepository.productdaily_in_total(fromdate, companyid);
	}

	@Override
	public Double productdaily_out_total(Date fromdate, Integer companyid) {
		// TODO Auto-generated method stub
		return wettpRepository.productdaily_out_total(fromdate, companyid);
	}

	@Override
	public Double cashflowproduct_transferout(String userid, Integer companyid, String productid, Date fromdate,
			Date todate) {
		// TODO Auto-generated method stub
		return wettpRepository.cashflowproduct_transferout(userid, companyid, productid, fromdate, todate);
	}

	@Override
	public Double cashflowproduct_transferin(String userid, Integer companyid, String productid, Date fromdate,
			Date todate) {
		// TODO Auto-generated method stub
		return wettpRepository.cashflowproduct_transferin(userid, companyid, productid, fromdate, todate);
	}

	@Override
	public Double cashflowproduct_transferout_total(Integer companyid, String productid, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wettpRepository.cashflowproduct_transferout_total(companyid, productid, fromdate, todate);
	}

	@Override
	public Double cashflowproduct_transferin_total(Integer companyid, String productid, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wettpRepository.cashflowproduct_transferin_total(companyid, productid, fromdate, todate);
	}

	@Override
	public List<Wettp> daily_mix_wettp(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		// TODO Auto-generated method stub
		return wettpRepository.daily_mix_wettp(companyid, fromdate, todate, productid);
	}

	@Override
	public Double daily_mix_wettp_amount(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		// TODO Auto-generated method stub
		return wettpRepository.daily_mix_wettp_amount(companyid, fromdate, todate, productid);
	}

	@Override
	public Double daily_mix_wettp_bonus(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long daily_mix_wettp_trancid(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		// TODO Auto-generated method stub
		return wettpRepository.daily_mix_wettp_trancid(companyid, fromdate, todate, productid);
	}

	@Override
	public List<Wettp> outside_report_unclaim(Integer companyid, List<String> frmproductid, List<String> toproductid,
			Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wettpRepository.outside_report_unclaim(companyid, frmproductid, toproductid, fromdate, todate);
	}

	@Override
	public List<Wettp> outside_report_claim(Integer companyid, List<String> frmproductid, List<String> toproductid,
			Date fromdate, Date todate, List<String> status) {
		// TODO Auto-generated method stub
		return wettpRepository.outside_report_claim(companyid, frmproductid, toproductid, fromdate, todate, status);
	}

}
