package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.CmcIncome;
import com.example.bank.repository.CmcIncomeRepository;

@Component
public class CmcIncomeServiceImpl implements CmcIncomeService {
	
	@Autowired
	CmcIncomeRepository cmcincomeRepository;

	@Override
	public List<CmcIncome> getallincomesbycompany(Integer companyid) {
		// TODO Auto-generated method stub
		return cmcincomeRepository.getallincomesbycompany(companyid);
	}

}
