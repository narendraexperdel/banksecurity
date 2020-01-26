package com.example.bank.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.CmcSources;

@Service
public interface CmcsourcesCustomRepository {

	public List<CmcSources> getallsourcebycompany(Integer companyid); 
}
