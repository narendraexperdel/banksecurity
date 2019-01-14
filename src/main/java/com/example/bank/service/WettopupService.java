package com.example.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.bank.model.Wettopup;

@Service
public interface WettopupService {
   
	public Optional<Wettopup> findWettopupbyId(Integer id);
	
	public List<Wettopup> getallUnclaimlist();
}
