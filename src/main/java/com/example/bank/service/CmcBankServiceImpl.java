package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.Cmcbank;
import com.example.bank.repository.CmcBankRepository;

@Component
public class CmcBankServiceImpl implements CmcBankService{
	
	@Autowired
	CmcBankRepository cmcbankRepository;

	@Override
	public List<Cmcbank> getallbankbycompany(Integer companyid) {
		// TODO Auto-generated method stub
		return cmcbankRepository.getallbankbycompany(companyid);
	}

}
