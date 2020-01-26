package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.CmcSources;
import com.example.bank.repository.CmcsourcesRepository;

@Component
public class CmcsourcesServiceImpl implements CmcsourcesService{
	
	@Autowired
	private CmcsourcesRepository cmcsourcesRepository;

	@Override
	public List<CmcSources> getallsourcebycompany(Integer companyid) {
		// TODO Auto-generated method stub
		return cmcsourcesRepository.getallsourcebycompany(companyid);
	}

}
