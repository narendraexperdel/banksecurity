package com.example.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.ProductClosing;

@Service
public interface ProductClosingService {
	
	public void saveproductclosing(ProductClosing productclosing);
	
	public List<ProductClosing> getclosing(Integer companyid,String product, Date date);
	
	public Double getclosingsumreport(Integer companyid, Date date); 

}
