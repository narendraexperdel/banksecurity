package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.Cmcbank;

@Service
public interface CmcBankService {
	
	public List<Cmcbank> getallbankbycompany(Integer companyid); 

}
