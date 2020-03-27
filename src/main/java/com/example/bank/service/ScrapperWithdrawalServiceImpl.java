package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.ScrapperWithdrawal;
import com.example.bank.repository.ScrapperWithdrawalRepository;

@Component
public class ScrapperWithdrawalServiceImpl implements ScrapperWithdrawalService{

	@Autowired
	ScrapperWithdrawalRepository scrapperwithdrawalRepository;
	
	@Override
	public void savescrapperwithdrawal(ScrapperWithdrawal scrapperwithdrawal) {
		// TODO Auto-generated method stub
		scrapperwithdrawalRepository.savescrapperwithdrawal(scrapperwithdrawal);
	}

	@Override
	public List<ScrapperWithdrawal> getscrapperwithdraw() {
		// TODO Auto-generated method stub
		return scrapperwithdrawalRepository.findAll();
	}

	@Override
	public void deletescrapperwithdrawal(ScrapperWithdrawal scrapperwithdrawal) {
		// TODO Auto-generated method stub
		scrapperwithdrawalRepository.delete(scrapperwithdrawal);
	}

	@Override
	public void updatescrapperwithdrawal(ScrapperWithdrawal scrapperwithdrawal) {
		// TODO Auto-generated method stub
		scrapperwithdrawalRepository.save(scrapperwithdrawal);
	}

}
