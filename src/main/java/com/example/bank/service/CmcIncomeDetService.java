package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.CmcIncomedet;

@Service
public interface CmcIncomeDetService {
	
	public List<CmcIncomedet> getallincomedetbycompany(Integer companyid, List<String> income);

}
