package com.example.bank.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.Expensestype;
import com.example.bank.model.OthIncome;

@Service
public interface ExpensestypeCustomRepository {

	public Double bankcashflow_expenses(Date date,Integer companyid);
	
	public List bankcashflow_totol_expenses(String month, String year, Integer companyid);
	
	public List<Expensestype> distinctexpenses(Date fromdate, Date todate,List<String> expenses,List<String> expensesdet,List<String> type,Integer companyid);
	
	public List<Expensestype> expensereport(Date fromdate, Date todate,String expenses,String expensesdet,Integer companyid);
	
	public Double expensereportamount(Date fromdate, Date todate,String expenses,String expensesdet,Integer companyid);
	
	public Double expensereporttaxamount(Date fromdate, Date todate,String expenses,String expensesdet,Integer companyid);
	
	public Double expensereportreportamount(Date fromdate, Date todate,Integer companyid);
	
	public Double expensereportreportamount_tax(Date fromdate, Date todate,Integer companyid);
	
	public List<Expensestype> outside_report_expense_amount(List<String> bank,Integer companyid,List<String> expensetype,List<String> expensedet,Date fromdate,Date todate);
	
	public List<Expensestype> outside_report_expense_gst(List<String> bank,Integer companyid,List<String> expensetype,List<String> expensedet,Date fromdate,Date todate);
	
    public List<Expensestype> outside_report_petty_amount(List<String> bank,Integer companyid,List<String> expensetype,List<String> expensedet,Date fromdate,Date todate);
	
	public List<Expensestype> outside_report_petty_gst(List<String> bank,Integer companyid,List<String> expensetype,List<String> expensedet,Date fromdate,Date todate);
}
