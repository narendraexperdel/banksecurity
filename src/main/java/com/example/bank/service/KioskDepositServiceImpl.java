package com.example.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.KioskDeposit;
import com.example.bank.repository.KioskDepositRepository;

@Component
public class KioskDepositServiceImpl implements KioskDepositService{
	
	@Autowired
	KioskDepositRepository kioskdepositRepository;

	@Override
	public void savekioskdeposit(KioskDeposit kioskdeposit) {
		// TODO Auto-generated method stub
		 kioskdepositRepository.savekioskdeposit(kioskdeposit);
	}

}
