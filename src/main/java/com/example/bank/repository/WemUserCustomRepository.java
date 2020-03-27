package com.example.bank.repository;

import java.util.List;

import com.example.bank.model.WemPromotion;
import com.example.bank.model.WemUser;

public interface WemUserCustomRepository {

	public List<WemUser> getwemuser(String username,String password);
	
}
