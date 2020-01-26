package com.example.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.WemPlayer;

@Service
public interface WemplayerService {

	public List<WemPlayer> getallplayer(Integer companyid,Date fromdate, Date todate,List<String> sources); 
	
	public List<WemPlayer> getallplayebydate(Integer companyid,Date fromdate, Date todate); 
	
	public List<WemPlayer> getallplayerbycompanyid(Integer companyid); 
	
	public List<WemPlayer> getallplayerinfo(Integer companyid,Date fromdate, Date todate,List<String> sources);
	
	public List<WemPlayer> getallplayerinfo(Integer companyid,Date fromdate, Date todate,List<String> sources,String playername);
	
	public List<WemPlayer> getallplayer_selectioncs(Integer companyid,Date fromdate, Date todate);
	
	public List<WemPlayer> homeactiveplayer(Integer companyid,Date fromdate, Date todate,List<String> productid);
	
	public List<WemPlayer> homenonactiveplayer(Integer companyid,Date fromdate, Date todate,List<String> productid);
}
