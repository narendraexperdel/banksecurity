package com.example.bank.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.bean.Wettopuprequestbean;
import com.example.bank.model.CmcProduct;
import com.example.bank.model.Cmcexpenses;
import com.example.bank.model.Cmcexpensesdet;
import com.example.bank.model.Expensestype;
import com.example.bank.service.CmcCombankSerrvice;
import com.example.bank.service.CmcexpensesService;
import com.example.bank.service.CmcexpensesdetService;
import com.example.bank.service.CmcproductService;
import com.example.bank.service.ExpensestypeService;
import com.example.bank.service.ReferalbonusService;
import com.example.bank.service.TmpPlayerService;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WettpService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api/")
public class ExpensereportController {
	

	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	CmcproductService cmcproductService;
	
	@Autowired
	CmcCombankSerrvice cmccombankService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;
	
	@Autowired
	WettpService wettpService;
	
	@Autowired
	ReferalbonusService referalbonusService;
	
	@Autowired
	TmpPlayerService tmpplayerService;
	
	@Autowired
	CmcexpensesService cmcexpensesService;
	
	@Autowired
	CmcexpensesdetService cmcexpensesdetService;
	
	@Autowired
	ExpensestypeService expensetypeService;
	
	
	@PostMapping("expenses-report")
	public ResponseEntity<Object> expensesreport(@RequestBody Wettopuprequestbean wettopup){
		
		Map<String, Object> response = new HashMap<>();
		List<Map<String,Object>> result_list = new ArrayList<>();
		List<Map<String,Object>> result_summary_list = new ArrayList<>();
		
		if(wettopup.getCompanyid1() != null) {
			String expenseslist = wettopup.getExpense().get(0);
			String expensesdetlist = wettopup.getExpensedet().get(0);
			String type = wettopup.getType().get(0);
			
			List<String> temp_expenses_list = new ArrayList<>();
			List<String> temp_expensesdet_list = new ArrayList<>();
			List<String> type_list = new ArrayList<>();
			
			if(type == null) {
				type_list.add("Bank");
				type_list.add("Petty");
			}else {
				type_list = wettopup.getType();
			}
			
			if(expenseslist == null) {
				
					List<Cmcexpenses> expenseslist_all = cmcexpensesService.getallexpensesbycompany(wettopup.getCompanyid1());
					for(Cmcexpenses expenseslist_all_itr: expenseslist_all) {
						temp_expenses_list.add(expenseslist_all_itr.getFldesc());
					}
				}else {
					temp_expenses_list = wettopup.getExpense();
				}
			
			if(expensesdetlist == null) {
				
				List<Cmcexpensesdet> expensesdetlist_all = cmcexpensesdetService.getallexpensesdetbycompany(wettopup.getCompanyid1(), temp_expenses_list);
				for(Cmcexpensesdet Cmcexpensesdet: expensesdetlist_all) {
					temp_expensesdet_list.add(Cmcexpensesdet.getFldesc());
				}
			}else {
				temp_expensesdet_list = wettopup.getExpensedet();
			}
			
			List<Expensestype> distinctexpenses = expensetypeService.distinctexpenses(wettopup.getDateOfissue(), wettopup.getTodate(), temp_expenses_list, temp_expensesdet_list, type_list, wettopup.getCompanyid1());
			
			Iterator distinctexpenses_itr = distinctexpenses.iterator();
			
			while(distinctexpenses_itr.hasNext()) {
				 Object[] obj = (Object[]) distinctexpenses_itr.next();
				 
				 List<Expensestype> expensereport = expensetypeService.expensereport(wettopup.getDateOfissue(), wettopup.getTodate(), String.valueOf(obj[0]), String.valueOf(obj[1]), wettopup.getCompanyid1());
				 
				 for(Expensestype expensereport_itr:expensereport) {
					 
					 Map<String,Object> temp_expense_list = new HashMap<>();
					 
					 temp_expense_list.put("expenses", expensereport_itr.getExpensename());
					 temp_expense_list.put("expensesdetails", expensereport_itr.getExpensedtname());
					 if(expensereport_itr.getExpensename().equals("Petty Cash") && expensereport_itr.getExpensedtname().equals("Petty Cash")) {
						 temp_expense_list.put("type", "Petty");
					 }else {
						 temp_expense_list.put("type", "Bank");
					 }
					 
					 temp_expense_list.put("remark", expensereport_itr.getRemark());
					 temp_expense_list.put("amount", expensereport_itr.getAmount());
					 temp_expense_list.put("taxamount", expensereport_itr.getGstamount());
					 temp_expense_list.put("issueddate", expensereport_itr.getIssueddate());
					 
					 result_list.add(temp_expense_list);
					 
				 }
			}
			
			Iterator distinctexpenses_sum_itr = distinctexpenses.iterator();
			
			while(distinctexpenses_sum_itr.hasNext()) {
				 Object[] obj = (Object[]) distinctexpenses_sum_itr.next();
				 
				 Map<String,Object> temp_expense_list = new HashMap<>();
				 
				 Double exp_amount  = expensetypeService.expensereportamount(wettopup.getDateOfissue(), wettopup.getTodate(), String.valueOf(obj[0]), String.valueOf(obj[1]), wettopup.getCompanyid1());
				 
				 Double exp_taxamount  = expensetypeService.expensereporttaxamount(wettopup.getDateOfissue(), wettopup.getTodate(), String.valueOf(obj[0]), String.valueOf(obj[1]), wettopup.getCompanyid1());
			
				 temp_expense_list.put("expenses",  String.valueOf(obj[0]));
				 temp_expense_list.put("expensesdetails", String.valueOf(obj[1]));
				 temp_expense_list.put("amount", exp_amount);
				 temp_expense_list.put("taxamount", exp_taxamount);
				 result_summary_list.add(temp_expense_list);
				 
			}
			
			 Map<String,Object> temp_expense_report_list = new HashMap<>();
			Double expent_report_amount = expensetypeService.expensereportreportamount(wettopup.getDateOfissue(), wettopup.getTodate(), wettopup.getCompanyid1());
			
			Double expent_report_taxamount = expensetypeService.expensereportreportamount_tax(wettopup.getDateOfissue(), wettopup.getTodate(), wettopup.getCompanyid1());
			
			temp_expense_report_list.put("expenses",  "Report Total : ");
			temp_expense_report_list.put("expensesdetails", "");
			temp_expense_report_list.put("amount", expent_report_amount);
			temp_expense_report_list.put("taxamount", expent_report_taxamount);
			 result_summary_list.add(temp_expense_report_list);
		  }
		response.put("code", HttpStatus.OK);
	    response.put("msg", "Expensereport data");
	    response.put("data", result_list);
	    response.put("datasize", result_list.size());
	    response.put("summary", result_summary_list);
	    response.put("summarysize", result_summary_list.size());
	    
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
