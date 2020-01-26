package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.bank.model.WemPromotion;

@Component
public interface WempromotionService {
	
	public List<WemPromotion> getwempromobypromoname(String promoname,Integer companyid);
	
	public List<WemPromotion> getwempromobycompanyid(Integer companyid);

}
