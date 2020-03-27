package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.MasterDeposit;
import com.example.bank.model.MasterKiosk;

@Service
public interface MasterDepositService {
	
	public void savemasterdeposit(MasterDeposit masterdeposit);
	
    public List<MasterDeposit> getmasterdeposit();
	
	public void deletemasterdeposit(MasterDeposit masterdeposit);
	
	public void updatemasterdeposit(MasterDeposit masterdeposit);

}
