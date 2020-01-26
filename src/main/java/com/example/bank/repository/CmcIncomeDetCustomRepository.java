package com.example.bank.repository;

import java.util.List;

import com.example.bank.model.CmcIncomedet;
import com.example.bank.model.Cmcexpensesdet;

public interface CmcIncomeDetCustomRepository {
	
	public List<CmcIncomedet> getallincomedetbycompany(Integer companyid, List<String> income);

}
