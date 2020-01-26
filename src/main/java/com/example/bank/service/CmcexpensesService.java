package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.Cmcexpenses;

@Service
public interface CmcexpensesService {
	
	public List<Cmcexpenses> getallexpensesbycompany(Integer companyid); 

}
