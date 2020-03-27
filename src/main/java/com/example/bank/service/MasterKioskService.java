package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.MasterKiosk;

@Service
public interface MasterKioskService {
	
	public List<MasterKiosk> getmasterkiosk(Integer companyid,Integer productid);
	
	public void savemasterkiosk(MasterKiosk masterkiosk);
	
	public List<MasterKiosk> getmasterkiosk();
	
	public void deletemasterkiosk(MasterKiosk masterkiosk);
	
	public void updatemasterkiosk(MasterKiosk masterkiosk);

}
