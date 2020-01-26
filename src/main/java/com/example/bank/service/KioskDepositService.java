package com.example.bank.service;

import org.springframework.stereotype.Service;

import com.example.bank.model.KioskDeposit;

@Service
public interface KioskDepositService {

	public void savekioskdeposit(KioskDeposit kioskdeposit);
}
