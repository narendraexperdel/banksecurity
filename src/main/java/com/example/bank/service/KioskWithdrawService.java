package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.KioskDeposit;
import com.example.bank.model.KioskWithdraw;

@Service
public interface KioskWithdrawService {

	 public void savekioskwithdraw(KioskWithdraw kioskwithdraw);
	
	 public List<KioskWithdraw> getkioskwithdraw();
		
	 public void deletekioskwithdraw(KioskWithdraw kioskwithdraw);
		
	 public void updatekioskwithdraw(KioskWithdraw kioskwithdraw);
}
