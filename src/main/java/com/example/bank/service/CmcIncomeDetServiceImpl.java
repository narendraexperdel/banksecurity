package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.CmcIncomedet;
import com.example.bank.repository.CmcIncomeDetRepository;

@Component
public class CmcIncomeDetServiceImpl implements CmcIncomeDetService{
	
	@Autowired
	CmcIncomeDetRepository cmcincomedetRepository;

	@Override
	public List<CmcIncomedet> getallincomedetbycompany(Integer companyid, List<String> income) {
		// TODO Auto-generated method stub
		return cmcincomedetRepository.getallincomedetbycompany(companyid, income);
	}
	
	

}
