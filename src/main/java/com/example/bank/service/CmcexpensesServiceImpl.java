package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.Cmcexpenses;
import com.example.bank.repository.CmcexpensesRepository;

@Component
public class CmcexpensesServiceImpl implements CmcexpensesService {
	
	@Autowired
	CmcexpensesRepository cmcexpensesRepository;

	@Override
	public List<Cmcexpenses> getallexpensesbycompany(Integer companyid) {
		// TODO Auto-generated method stub
		return cmcexpensesRepository.getallexpensesbycompany(companyid);
	}

}
