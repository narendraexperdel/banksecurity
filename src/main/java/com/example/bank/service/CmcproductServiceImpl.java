package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.CmcProduct;
import com.example.bank.repository.CmcProductRepository;

@Component
public class CmcproductServiceImpl implements CmcproductService {
	
	@Autowired
	private CmcProductRepository cmcproductrepository;

	@Override
	public List<CmcProduct> getallproductbycompany(Integer companyid) {
		// TODO Auto-generated method stub
		return cmcproductrepository.getallproductbycompany(companyid);
	}

	@Override
	public List<CmcProduct> getclosing(Integer companyid, String product) {
		// TODO Auto-generated method stub
		return cmcproductrepository.getclosing(companyid, product);
	}

	@Override
	public Double getreportclosing(Integer companyid) {
		// TODO Auto-generated method stub
		return cmcproductrepository.getreportclosing(companyid);
	}

}
