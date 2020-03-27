package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.KioskDeposit;
import com.example.bank.model.MasterDeposit;

@Service
public interface KioskDepositService {

	    public void savekioskdeposit(KioskDeposit kioskdeposit);
	
	    public List<KioskDeposit> getkioskdeposit();
		
		public void deletekioskdeposit(KioskDeposit kioskdeposit);
		
		public void updatekioskdeposit(KioskDeposit kioskdeposit);
}
