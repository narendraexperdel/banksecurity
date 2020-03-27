package com.example.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.Profiledata;
import com.example.bank.repository.ProfiledataRepository;

@Component
public class ProfiledataServiceImpl implements ProfiledataService {

	@Autowired
	ProfiledataRepository profiledataRepository;

	@Override
	public void saveprofiledta(Profiledata profiledata) {
		// TODO Auto-generated method stub
		profiledataRepository.saveprofiledta(profiledata);
	}
	
	
}
