package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.Cmcexpensesdet;
import com.example.bank.repository.CmcexpensesdetRepository;

@Component
public class CmcexpensesdetServiceImpl implements CmcexpensesdetService {
	
	@Autowired
	CmcexpensesdetRepository cmcexpensesdetRepository;

	@Override
	public List<Cmcexpensesdet> getallexpensesdetbycompany(Integer companyid, List<String> expenses) {
		// TODO Auto-generated method stub
		return cmcexpensesdetRepository.getallexpensesdetbycompany(companyid, expenses);
	}

}
