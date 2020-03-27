package com.example.bank.service;

import java.util.List;

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

	@Override
	public List<KioskDeposit> getkioskdeposit() {
		// TODO Auto-generated method stub
		return kioskdepositRepository.findAll();
	}

	@Override
	public void deletekioskdeposit(KioskDeposit kioskdeposit) {
		// TODO Auto-generated method stub
		kioskdepositRepository.delete(kioskdeposit);
	}

	@Override
	public void updatekioskdeposit(KioskDeposit kioskdeposit) {
		// TODO Auto-generated method stub
		kioskdepositRepository.save(kioskdeposit);
	}

}
