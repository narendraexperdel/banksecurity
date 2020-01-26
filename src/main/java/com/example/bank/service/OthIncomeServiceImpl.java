package com.example.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.OthIncome;
import com.example.bank.repository.OthIncomeRepository;

@Component
public class OthIncomeServiceImpl implements OthIncomeService{
	
	@Autowired
	OthIncomeRepository othincomeRepository;

	@Override
	public Double bankcashflow_othincome(Date date, Integer companyid) {
		// TODO Auto-generated method stub
		return othincomeRepository.bankcashflow_othincome(date, companyid);
	}

	@Override
	public List bankcashflow_totol_othincome(String month, String year, Integer companyid) {
		// TODO Auto-generated method stub
		return othincomeRepository.bankcashflow_totol_othincome(month, year, companyid);
	}

	@Override
	public List<OthIncome> outside_report_othincome(List<String> bank, Integer companyid, List<String> incometype,
			List<String> incomedet, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return othincomeRepository.outside_report_othincome(bank, companyid, incometype, incomedet, fromdate, todate);
	}

}
