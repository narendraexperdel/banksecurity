package com.example.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.Teledata;
import com.example.bank.repository.TeleDataRepository;

@Component
public class TeleDataServiceImpl implements TeleDataService{

	@Autowired
	TeleDataRepository teledataRepository;

	@Override
	public void saveteledata(Teledata teledata) {
		// TODO Auto-generated method stub
		teledataRepository.saveteledata(teledata);
	}
	
	
	
}
