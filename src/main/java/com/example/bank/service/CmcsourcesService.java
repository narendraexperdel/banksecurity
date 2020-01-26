package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.CmcSources;

@Service
public interface CmcsourcesService {
	
	public List<CmcSources> getallsourcebycompany(Integer companyid); 

}
