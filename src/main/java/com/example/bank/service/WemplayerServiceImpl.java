package com.example.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.WemPlayer;
import com.example.bank.repository.WemplayerRepository;

@Component
public class WemplayerServiceImpl implements WemplayerService {
	
	@Autowired
	WemplayerRepository wemplayerRepository;

	@Override
	public List<WemPlayer> getallplayer(Integer companyid, Date fromdate, Date todate, List<String> sources) {
		// TODO Auto-generated method stub
		return wemplayerRepository.getallplayer(companyid, fromdate, todate, sources);
	}

	@Override
	public List<WemPlayer> getallplayebydate(Integer companyid, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wemplayerRepository.getallplayebydate(companyid, fromdate, todate);
	}

	@Override
	public List<WemPlayer> getallplayerbycompanyid(Integer companyid) {
		// TODO Auto-generated method stub
		return wemplayerRepository.getallplayerbycompanyid(companyid);
	}

	@Override
	public List<WemPlayer> getallplayerinfo(Integer companyid, Date fromdate, Date todate, List<String> sources) {
		// TODO Auto-generated method stub
		return wemplayerRepository.getallplayerinfo(companyid, fromdate, todate, sources);
	}

	@Override
	public List<WemPlayer> getallplayerinfo(Integer companyid, Date fromdate, Date todate, List<String> sources,
			String playername) {
		// TODO Auto-generated method stub
		return wemplayerRepository.getallplayerinfo(companyid, fromdate, todate, sources, playername);
	}

	@Override
	public List<WemPlayer> getallplayer_selectioncs(Integer companyid, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return wemplayerRepository.getallplayer_selectioncs(companyid, fromdate, todate);
	}

	@Override
	public List<WemPlayer> homeactiveplayer(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		// TODO Auto-generated method stub
		return wemplayerRepository.homeactiveplayer(companyid, fromdate, todate, productid);
	}

	@Override
	public List<WemPlayer> homenonactiveplayer(Integer companyid, Date fromdate, Date todate, List<String> productid) {
		// TODO Auto-generated method stub
		return wemplayerRepository.homenonactiveplayer(companyid, fromdate, todate, productid);
	}

}
