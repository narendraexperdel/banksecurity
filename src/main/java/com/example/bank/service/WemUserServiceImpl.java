package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.WemUser;
import com.example.bank.repository.WemUserRepository;

@Component
public class WemUserServiceImpl implements WemUserService {
	
	@Autowired
	WemUserRepository wemuserRepository;

	@Override
	public List<WemUser> getwemuser(String username, String password) {
		// TODO Auto-generated method stub
		return wemuserRepository.getwemuser(username, password);
	}

}
