package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.Cmcexpensesdet;

@Service
public interface CmcexpensesdetService {
	
	public List<Cmcexpensesdet> getallexpensesdetbycompany(Integer companyid, List<String> expenses);

}
