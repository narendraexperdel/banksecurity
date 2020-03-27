package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.MasterWithdraw;
import com.example.bank.model.Removevsadd;

@Service
public interface RemovevsaddService {

	    public void saveremovevsadd(Removevsadd removevsadd);
	
	    public List<Removevsadd> getremovevsadd();
		
		public void deleteremovevsadd(Removevsadd removevsadd);
		
		public void updateremovevsadd(Removevsadd removevsadd);
}
