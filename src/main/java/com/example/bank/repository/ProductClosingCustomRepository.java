package com.example.bank.repository;

import java.util.Date;
import java.util.List;

import com.example.bank.model.CmcProduct;
import com.example.bank.model.ProductClosing;

public interface ProductClosingCustomRepository {
	
	public void saveproductclosing(ProductClosing productclosing);
	
	public List<ProductClosing> getclosing(Integer companyid,String product, Date date); 
	
	public Double getclosingsumreport(Integer companyid, Date date); 

}
