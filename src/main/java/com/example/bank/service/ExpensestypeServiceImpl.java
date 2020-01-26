package com.example.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.Expensestype;
import com.example.bank.repository.ExpensestypeRepository;

@Component
public class ExpensestypeServiceImpl implements ExpensestypeService {
	
	@Autowired
	ExpensestypeRepository expensetypeRepository;

	@Override
	public Double bankcashflow_expenses(Date date, Integer companyid) {
		// TODO Auto-generated method stub
		return expensetypeRepository.bankcashflow_expenses(date, companyid);
	}

	@Override
	public List bankcashflow_totol_expenses(String month, String year, Integer companyid) {
		// TODO Auto-generated method stub
		return expensetypeRepository.bankcashflow_totol_expenses(month, year, companyid);
	}

	@Override
	public List<Expensestype> distinctexpenses(Date fromdate, Date todate, List<String> expenses,
			List<String> expensesdet, List<String> type, Integer companyid) {
		// TODO Auto-generated method stub
		return expensetypeRepository.distinctexpenses(fromdate, todate, expenses, expensesdet, type, companyid);
	}

	@Override
	public List<Expensestype> expensereport(Date fromdate, Date todate, String expenses, String expensesdet,
			Integer companyid) {
		// TODO Auto-generated method stub
		return expensetypeRepository.expensereport(fromdate, todate, expenses, expensesdet, companyid);
	}

	@Override
	public Double expensereportamount(Date fromdate, Date todate, String expenses, String expensesdet,
			Integer companyid) {
		// TODO Auto-generated method stub
		return expensetypeRepository.expensereportamount(fromdate, todate, expenses, expensesdet, companyid);
	}

	@Override
	public Double expensereporttaxamount(Date fromdate, Date todate, String expenses, String expensesdet,
			Integer companyid) {
		// TODO Auto-generated method stub
		return expensetypeRepository.expensereporttaxamount(fromdate, todate, expenses, expensesdet, companyid);
	}

	@Override
	public Double expensereportreportamount(Date fromdate, Date todate, Integer companyid) {
		// TODO Auto-generated method stub
		return expensetypeRepository.expensereportreportamount(fromdate, todate, companyid);
	}

	@Override
	public Double expensereportreportamount_tax(Date fromdate, Date todate, Integer companyid) {
		// TODO Auto-generated method stub
		return expensetypeRepository.expensereportreportamount_tax(fromdate, todate, companyid);
	}

	@Override
	public List<Expensestype> outside_report_expense_amount(List<String> bank, Integer companyid,
			List<String> expensetype, List<String> expensedet, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return expensetypeRepository.outside_report_expense_amount(bank, companyid, expensetype, expensedet, fromdate, todate);
	}

	@Override
	public List<Expensestype> outside_report_expense_gst(List<String> bank, Integer companyid, List<String> expensetype,
			List<String> expensedet, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return expensetypeRepository.outside_report_expense_gst(bank, companyid, expensetype, expensedet, fromdate, todate);
	}

	@Override
	public List<Expensestype> outside_report_petty_amount(List<String> bank, Integer companyid,
			List<String> expensetype, List<String> expensedet, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return expensetypeRepository.outside_report_petty_amount(bank, companyid, expensetype, expensedet, fromdate, todate);
	}

	@Override
	public List<Expensestype> outside_report_petty_gst(List<String> bank, Integer companyid, List<String> expensetype,
			List<String> expensedet, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return expensetypeRepository.outside_report_petty_gst(bank, companyid, expensetype, expensedet, fromdate, todate);
	}

}
