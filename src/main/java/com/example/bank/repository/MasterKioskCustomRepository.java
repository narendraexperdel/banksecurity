package com.example.bank.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.MasterKiosk;

@Service
public interface MasterKioskCustomRepository {
	
	public List<MasterKiosk> getmasterkiosk(Integer companyid,Integer productid);

}
