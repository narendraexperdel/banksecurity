package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.KioskWithdraw;
import com.example.bank.repository.KioskWithdrawRepository;

@Component
public class KioskWithdrawServiceImpl implements KioskWithdrawService{
	
	@Autowired
	KioskWithdrawRepository kioskwithdrawRepository;

	@Override
	public void savekioskwithdraw(KioskWithdraw kioskwithdraw) {
		// TODO Auto-generated method stub
		kioskwithdrawRepository.savekioskwithdraw(kioskwithdraw);
		
	}

	@Override
	public List<KioskWithdraw> getkioskwithdraw() {
		// TODO Auto-generated method stub
		return kioskwithdrawRepository.findAll();
	}

	@Override
	public void deletekioskwithdraw(KioskWithdraw kioskwithdraw) {
		// TODO Auto-generated method stub
		kioskwithdrawRepository.delete(kioskwithdraw);
	}

	@Override
	public void updatekioskwithdraw(KioskWithdraw kioskwithdraw) {
		// TODO Auto-generated method stub
		kioskwithdrawRepository.save(kioskwithdraw);
	}

}
