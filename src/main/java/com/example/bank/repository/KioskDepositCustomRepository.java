package com.example.bank.repository;

import java.util.Date;

import com.example.bank.model.KioskDeposit;

public interface KioskDepositCustomRepository {
	
	public Double getkioskdeposit(Integer companyid);
	
	public void savekioskdeposit(KioskDeposit kioskdeposit);

}
