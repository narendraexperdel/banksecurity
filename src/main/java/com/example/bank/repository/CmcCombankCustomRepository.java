package com.example.bank.repository;

import java.util.List;

import com.example.bank.model.CmcProduct;
import com.example.bank.model.Cmccombank;

public interface CmcCombankCustomRepository {

	public List<Cmccombank> getallbankbycompany(Integer companyid); 
}
