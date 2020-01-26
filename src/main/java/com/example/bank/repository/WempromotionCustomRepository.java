package com.example.bank.repository;

import java.util.List;

import com.example.bank.model.WemPromotion;
import com.example.bank.model.Wettopup;

public interface WempromotionCustomRepository {
	
	public List<WemPromotion> getwempromobypromoname(String promoname,Integer companyid);
	
	public List<WemPromotion> getwempromobycompanyid(Integer companyid);

}
