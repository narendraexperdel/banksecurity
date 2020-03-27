package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.Removevsadd;
import com.example.bank.repository.RemovevsaddRepository;

@Component
public class RemovevsaddServiceImpl implements RemovevsaddService{
	
	@Autowired
	RemovevsaddRepository removevsaddRepository;

	@Override
	public void saveremovevsadd(Removevsadd removevsadd) {
		// TODO Auto-generated method stub
		removevsaddRepository.saveremovevsadd(removevsadd);
	}

	@Override
	public List<Removevsadd> getremovevsadd() {
		// TODO Auto-generated method stub
		return removevsaddRepository.findAll();
	}

	@Override
	public void deleteremovevsadd(Removevsadd removevsadd) {
		// TODO Auto-generated method stub
		removevsaddRepository.delete(removevsadd);
	}

	@Override
	public void updateremovevsadd(Removevsadd removevsadd) {
		// TODO Auto-generated method stub
		removevsaddRepository.save(removevsadd);
	}

}
