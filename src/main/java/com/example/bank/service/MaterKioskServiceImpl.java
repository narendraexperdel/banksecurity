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

	@Override
	public void savemasterkiosk(MasterKiosk masterkiosk) {
		// TODO Auto-generated method stub
		masterkioskRepository.savemasterkiosk(masterkiosk);
	}

	@Override
	public List<MasterKiosk> getmasterkiosk() {
		return masterkioskRepository.findAll();
		
	}

	@Override
	public void deletemasterkiosk(MasterKiosk masterkiosk) {
		// TODO Auto-generated method stub
		masterkioskRepository.delete(masterkiosk);
	}

	@Override
	public void updatemasterkiosk(MasterKiosk masterkiosk) {
		// TODO Auto-generated method stub
		masterkioskRepository.save(masterkiosk);
	}
	
	

}
