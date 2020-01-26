package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.CmcProduct;

@Service
public interface CmcproductService {
	
	public List<CmcProduct> getallproductbycompany(Integer companyid); 
	
	public List<CmcProduct> getclosing(Integer companyid,String product); 
	
	public Double getreportclosing(Integer companyid);

}
