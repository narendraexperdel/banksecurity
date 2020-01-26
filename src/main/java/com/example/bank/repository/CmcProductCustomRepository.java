package com.example.bank.repository;

import java.util.List;

import com.example.bank.model.CmcProduct;
import com.example.bank.model.TmpPlayer;

public interface CmcProductCustomRepository {
	
	public List<CmcProduct> getallproductbycompany(Integer companyid); 
	
	public List<CmcProduct> getclosing(Integer companyid,String product); 
	
	public Double getreportclosing(Integer companyid); 

}
