package com.example.bank.service;

import org.springframework.stereotype.Service;

import com.example.bank.model.KioskWithdraw;

@Service
public interface KioskWithdrawService {

	public void savekioskwithdraw(KioskWithdraw kioskwithdraw);
}
