package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.MasterKiosk;
import com.example.bank.model.ScrapperWithdrawal;

@Service
public interface ScrapperWithdrawalService {

	public void savescrapperwithdrawal(ScrapperWithdrawal scrapperwithdrawal);
	
    public List<ScrapperWithdrawal> getscrapperwithdraw();
	
	public void deletescrapperwithdrawal(ScrapperWithdrawal scrapperwithdrawal);
	
	public void updatescrapperwithdrawal(ScrapperWithdrawal scrapperwithdrawal);
}
