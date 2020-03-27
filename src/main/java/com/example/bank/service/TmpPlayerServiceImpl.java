package com.example.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.TmpPlayer;
import com.example.bank.repository.TmpPlayerRepository;

@Component
public class TmpPlayerServiceImpl implements TmpPlayerService{
	
	@Autowired
	TmpPlayerRepository tmpplayerRepository;

	@Override
	public List<TmpPlayer> getalluserid(String productid, String userid, Integer companyid) {
		// TODO Auto-generated method stub
		return tmpplayerRepository.getalluserid(productid, userid, companyid);
	}

	@Override
	public List<TmpPlayer> getuseridbycomapnyid(Integer companyid) {
		// TODO Auto-generated method stub
		return tmpplayerRepository.getuseridbycomapnyid(companyid);
	}

	@Override
	public List<TmpPlayer> getuseridbyplayer(Integer companyid, Integer wemplayerid,List<String> productid) {
		// TODO Auto-generated method stub
		return tmpplayerRepository.getuseridbyplayer(companyid, wemplayerid,productid);
	}

	@Override
	public List<TmpPlayer> getuseridbyplayer(Integer companyid, Integer wemplayerid) {
		// TODO Auto-generated method stub
		return tmpplayerRepository.getuseridbyplayer(companyid, wemplayerid);
	}

	@Override
	public List<TmpPlayer> getuserid(Integer companyid, Integer wemplayerid) {
		// TODO Auto-generated method stub
		return tmpplayerRepository.getuserid(companyid, wemplayerid);
	}

	@Override
	public List<TmpPlayer> getuseridfornewplayerreg(Integer companyid, List<String> productid,
			List<Integer> wemplayerid) {
		// TODO Auto-generated method stub
		return tmpplayerRepository.getuseridfornewplayerreg(companyid, productid, wemplayerid);
	}

	@Override
	public List<TmpPlayer> gettopupplayer(Integer companyid, List<String> productid, Integer wemplayerid) {
		// TODO Auto-generated method stub
		return tmpplayerRepository.gettopupplayer(companyid, productid, wemplayerid);
	}

	@Override
	public List<TmpPlayer> tmpplayer_list(Integer companyid, Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return tmpplayerRepository.tmpplayer_list(companyid, fromdate, todate);
	}

	@Override
	public List<TmpPlayer> tmpplayer_list(Integer companyid, Date fromdate, Date todate, String userid) {
		// TODO Auto-generated method stub
		return tmpplayerRepository.tmpplayer_list(companyid, fromdate, todate,userid);
	}

	@Override
	public List<String> playername(String userid, String productid, Integer companyid) {
		// TODO Auto-generated method stub
		return tmpplayerRepository.playername(userid, productid, companyid);
	}

	@Override
	public List<String> productname(String userid, Integer companyid) {
		// TODO Auto-generated method stub
		return tmpplayerRepository.productname(userid, companyid);
	}

	@Override
	public List<Integer> playercount_closesum(List<String> userid, Integer companyid) {
		// TODO Auto-generated method stub
		return tmpplayerRepository.playercount_closesum(userid, companyid);
	}

	@Override
	public List<TmpPlayer> checkuserid(String productid, String userid, Integer companyid) {
		// TODO Auto-generated method stub
		return tmpplayerRepository.checkuserid(productid, userid, companyid);
	}

	@Override
	public List<TmpPlayer> allprofileuserid(Integer wemplayerid, Integer companyid) {
		// TODO Auto-generated method stub
		return tmpplayerRepository.allprofileuserid(wemplayerid, companyid);
	}

	@Override
	public List<TmpPlayer> getwemplayerid(String userid, Integer companyid) {
		// TODO Auto-generated method stub
		return tmpplayerRepository.getwemplayerid(userid, companyid);
	}

	@Override
	public List<TmpPlayer> gettopupplayer_statisticcs(Integer companyid, List<String> productid, Integer wemplayerid,
			Date fromdate, Date todate) {
		// TODO Auto-generated method stub
		return tmpplayerRepository.gettopupplayer_statisticcs(companyid, productid, wemplayerid, fromdate, todate);
	}

}
