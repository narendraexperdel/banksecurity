package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.MasterKiosk;
import com.example.bank.repository.MasterKioskRepository;

@Component
public class MaterKioskServiceImpl implements MasterKioskService {
	
	@Autowired
	MasterKioskRepository masterkioskRepository;

	@Override
	public List<MasterKiosk> getmasterkiosk(Integer companyid, Integer productid) {
		// TODO Auto-generated method stub
		return masterkioskRepository.getmasterkiosk(companyid, productid);
	}
	
	

}
