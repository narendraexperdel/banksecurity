package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.MasterWithdraw;
import com.example.bank.repository.MasterWithdrawRepository;

@Component
public class MasterWithdrawServiceImpl implements MasterWithdrawService{
	
	@Autowired
	MasterWithdrawRepository masterwithdrawRepository;

	@Override
	public void savemasterwithdraw(MasterWithdraw masterwithdraw) {
		// TODO Auto-generated method stub
		masterwithdrawRepository.savemasterwithdraw(masterwithdraw);
	}

	@Override
	public List<MasterWithdraw> getmasterwithdraw() {
		// TODO Auto-generated method stub
		return masterwithdrawRepository.findAll();
	}

	@Override
	public void deletemasterwithdraw(MasterWithdraw masterwithdraw) {
		// TODO Auto-generated method stub
		masterwithdrawRepository.delete(masterwithdraw);
	}

	@Override
	public void updatemasterwithdraw(MasterWithdraw masterwithdraw) {
		// TODO Auto-generated method stub
		masterwithdrawRepository.save(masterwithdraw);
	}

}
