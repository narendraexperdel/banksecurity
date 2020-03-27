package com.example.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.ProductClosing;
import com.example.bank.repository.ProductClosingRepository;

@Component
public class ProductClosingServiceImpl implements ProductClosingService{
	
	@Autowired
	ProductClosingRepository productclosingRepository;

	@Override
	public void saveproductclosing(ProductClosing productclosing) {
		// TODO Auto-generated method stub
		
		productclosingRepository.saveproductclosing(productclosing);
		
	}

	@Override
	public List<ProductClosing> getclosing(Integer companyid, String product, Date date) {
		// TODO Auto-generated method stub
		return productclosingRepository.getclosing(companyid, product, date);
	}

	@Override
	public Double getclosingsumreport(Integer companyid, Date date) {
		// TODO Auto-generated method stub
		return productclosingRepository.getclosingsumreport(companyid, date);
	}

}
