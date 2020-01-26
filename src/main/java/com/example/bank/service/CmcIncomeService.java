package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.CmcIncome;

@Service
public interface CmcIncomeService {
	
	public List<CmcIncome> getallincomesbycompany(Integer companyid); 

}
