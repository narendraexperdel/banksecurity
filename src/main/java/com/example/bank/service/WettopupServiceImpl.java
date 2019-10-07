package com.example.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.Wettopup;
import com.example.bank.repository.WettopupRepository;

@Component
public class WettopupServiceImpl implements WettopupService {

	@Autowired
	private WettopupRepository wettopupRepository;
	
	@Override
	public Optional<Wettopup> findWettopupbyId(Integer id) {
		return null;
	}

	@Override
	public List<Wettopup> getallUnclaimlist() {
		return wettopupRepository.getallUnclaimlist();
	}
}
