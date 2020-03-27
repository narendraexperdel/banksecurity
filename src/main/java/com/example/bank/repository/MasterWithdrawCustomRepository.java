package com.example.bank.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.bank.model.MasterDeposit;
import com.example.bank.model.MasterWithdraw;

@Service
public interface MasterWithdrawCustomRepository {
	
	public void savemasterwithdraw(MasterWithdraw masterwithdraw);

}
