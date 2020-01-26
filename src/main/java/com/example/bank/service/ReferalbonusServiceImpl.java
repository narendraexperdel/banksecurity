package com.example.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.ReferalBonus;
import com.example.bank.repository.ReferalbonusRepository;

@Component
public class ReferalbonusServiceImpl implements ReferalbonusService{
	
	@Autowired
	ReferalbonusRepository referalbonusRepository;

	@Override
	public Double productdaily_referal(Date fromdate, Integer companyid, String product) {
		// TODO Auto-generated method stub
		return referalbonusRepository.productdaily_referal(fromdate, companyid, product);
	}

	@Override
	public Double productdaily_referal_total(Date fromdate, Integer companyid) {
		// TODO Auto-generated method stub
		return referalbonusRepository.productdaily_referal_total(fromdate, companyid);
	}

	@Override
	public Double cashflowproduct_referal(String userid, Integer companyid, String productid, Date fromdate,
			Date todate) {
		// TODO Auto-generated method stub
		return referalbonusRepository.cashflowproduct_referal(userid, companyid, productid, fromdate, todate);
	}

	@Override
	public Double cashflowproduct_referal_total(Integer companyid, String productid, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return referalbonusRepository.cashflowproduct_referal_total(companyid, productid, fromdate, todate);
	}

	@Override
	public Double cashflowproduct_referal_report_total(Integer companyid, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return referalbonusRepository.cashflowproduct_referal_report_total(companyid, fromdate, todate);
	}

	@Override
	public List<ReferalBonus> cashflowproduct_referal(Date fromdate, Date todate, Integer companyid, String productid) {
		// TODO Auto-generated method stub
		return referalbonusRepository.cashflowproduct_referal(fromdate, todate, companyid, productid);
	}

	@Override
	public List<ReferalBonus> outside_report_referal_pending(Integer companyid, List<String> productid, Date fromdate,
			Date todate) {
		// TODO Auto-generated method stub
		return referalbonusRepository.outside_report_referal_pending(companyid, productid, fromdate, todate);
	}

	@Override
	public List<ReferalBonus> outside_report_referal_done(Integer companyid, List<String> productid, Date fromdate,
			Date todate) {
		// TODO Auto-generated method stub
		return referalbonusRepository.outside_report_referal_done(companyid, productid, fromdate, todate);
	}

}
