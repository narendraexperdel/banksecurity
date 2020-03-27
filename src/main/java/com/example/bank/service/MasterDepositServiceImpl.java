package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.MasterDeposit;
import com.example.bank.repository.MasterDepositRepository;

@Component
public class MasterDepositServiceImpl implements MasterDepositService {
	
	@Autowired
	MasterDepositRepository masterdepositRepository;

	@Override
	public void savemasterdeposit(MasterDeposit masterdeposit) {
		// TODO Auto-generated method stub
		masterdepositRepository.savemasterdeposit(masterdeposit);
	}

	@Override
	public List<MasterDeposit> getmasterdeposit() {
		// TODO Auto-generated method stub
		return masterdepositRepository.findAll();
	}

	@Override
	public void deletemasterdeposit(MasterDeposit masterdeposit) {
		// TODO Auto-generated method stub
		masterdepositRepository.delete(masterdeposit);
	}

	@Override
	public void updatemasterdeposit(MasterDeposit masterdeposit) {
		// TODO Auto-generated method stub
		masterdepositRepository.save(masterdeposit);
	}

}
