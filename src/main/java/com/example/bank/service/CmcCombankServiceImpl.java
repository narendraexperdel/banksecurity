package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.Cmccombank;
import com.example.bank.repository.CmcCombankRepository;

@Component
public class CmcCombankServiceImpl implements CmcCombankSerrvice{
	
	@Autowired
	CmcCombankRepository cmccombankRepository;

	@Override
	public List<Cmccombank> getallbankbycompany(Integer companyid) {
		// TODO Auto-generated method stub
		return cmccombankRepository.getallbankbycompany(companyid);
	}

}
