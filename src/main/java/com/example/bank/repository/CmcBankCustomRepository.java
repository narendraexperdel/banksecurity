package com.example.bank.repository;

import java.util.List;

import com.example.bank.model.Cmcbank;
import com.example.bank.model.Cmccombank;

public interface CmcBankCustomRepository {
	
	public List<Cmcbank> getallbankbycompany(Integer companyid); 

}
