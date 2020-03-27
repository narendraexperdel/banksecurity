package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.MasterKiosk;
import com.example.bank.model.MasterWithdraw;

@Service
public interface MasterWithdrawService {

	public void savemasterwithdraw(MasterWithdraw masterwithdraw);
	
        public List<MasterWithdraw> getmasterwithdraw();
	
	public void deletemasterwithdraw(MasterWithdraw masterwithdraw);
	
	public void updatemasterwithdraw(MasterWithdraw masterwithdraw);
}
