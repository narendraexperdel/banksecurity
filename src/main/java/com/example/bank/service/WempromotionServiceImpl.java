package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.WemPromotion;
import com.example.bank.repository.WempromotionRepository;

@Component
public class WempromotionServiceImpl implements WempromotionService {
	
	@Autowired
	WempromotionRepository wempromoRepository;

	@Override
	public List<WemPromotion> getwempromobypromoname(String promoname, Integer companyid) {
		// TODO Auto-generated method stub
		return wempromoRepository.getwempromobypromoname(promoname, companyid);
	}

	@Override
	public List<WemPromotion> getwempromobycompanyid(Integer companyid) {
		// TODO Auto-generated method stub
		return wempromoRepository.getwempromobycompanyid(companyid);
	}

}
