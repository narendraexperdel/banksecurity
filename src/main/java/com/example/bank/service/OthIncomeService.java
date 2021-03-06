package com.example.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.OthIncome;

@Service
public interface OthIncomeService {
	
	public Double bankcashflow_othincome(Date date,Integer companyid);
	
	public List bankcashflow_totol_othincome(String month, String year, Integer companyid);
	
	public List<OthIncome> outside_report_othincome(List<String> bank,Integer companyid,List<String> incometype,List<String> incomedet,Date fromdate,Date todate);
	
	 public  List<OthIncome>  home_othincome(Integer companyid,Date fromdate, Date todate);

}
