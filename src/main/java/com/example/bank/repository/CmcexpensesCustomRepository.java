package com.example.bank.repository;

import java.util.List;

import com.example.bank.model.Cmccombank;
import com.example.bank.model.Cmcexpenses;

public interface CmcexpensesCustomRepository {

	public List<Cmcexpenses> getallexpensesbycompany(Integer companyid); 
	
}
