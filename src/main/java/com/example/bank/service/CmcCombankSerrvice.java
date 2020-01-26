package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.Cmccombank;

@Service
public interface CmcCombankSerrvice {

	public List<Cmccombank> getallbankbycompany(Integer companyid); 
}
