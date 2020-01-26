package com.example.bank.repository;

import java.util.List;

import com.example.bank.model.Cmccombank;
import com.example.bank.model.Cmcexpensesdet;

public interface CmcexpensesdetCustomRepository {
	
	public List<Cmcexpensesdet> getallexpensesdetbycompany(Integer companyid, List<String> expenses);

}
