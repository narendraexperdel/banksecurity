package com.example.bank.repository;

import java.util.List;

import com.example.bank.model.CmcIncome;
import com.example.bank.model.Cmcexpenses;

public interface CmcIncomeCustomRepository {
	
	public List<CmcIncome> getallincomesbycompany(Integer companyid); 

}
