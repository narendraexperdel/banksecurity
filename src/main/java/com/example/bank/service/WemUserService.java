package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.WemUser;

@Service
public interface WemUserService {

	public List<WemUser> getwemuser(String username,String password);
}
