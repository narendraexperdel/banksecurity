package com.example.bank.service;

import org.springframework.stereotype.Service;

import com.example.bank.model.Teledata;

@Service
public interface TeleDataService {
	
	public void saveteledata(Teledata teledata);

}
