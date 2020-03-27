package com.example.bank.repository;

import org.springframework.stereotype.Service;

import com.example.bank.model.KioskDeposit;
import com.example.bank.model.Teledata;

@Service
public interface TeleDataCustomRepository {
	
	public void saveteledata(Teledata teledata);

}
