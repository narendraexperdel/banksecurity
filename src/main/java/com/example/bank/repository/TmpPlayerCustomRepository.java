package com.example.bank.repository;

import java.util.Date;
import java.util.List;

import com.example.bank.model.TmpPlayer;

public interface TmpPlayerCustomRepository {
	
	public List<TmpPlayer> getalluserid(String productid,String userid,Integer companyid); 
	
	public List<TmpPlayer> getuseridbycomapnyid(Integer companyid); 
	
	public List<TmpPlayer> getuseridbyplayer(Integer companyid, Integer wemplayerid,List<String> productid);
	
	public List<TmpPlayer> getuseridbyplayer(Integer companyid, Integer wemplayerid); 
	
	public List<TmpPlayer> getuserid(Integer companyid, Integer wemplayerid); 
	
	public List<TmpPlayer> getuseridfornewplayerreg(Integer companyid,List<String> productid, List<Integer> wemplayerid);
	
	public List<TmpPlayer> gettopupplayer(Integer companyid,List<String> productid,Integer wemplayerid);
	
	public List<TmpPlayer> tmpplayer_list(Integer companyid,Date fromdate,Date todate);
	
	public List<TmpPlayer> tmpplayer_list(Integer companyid,Date fromdate,Date todate,String userid);
	
	public List<String> playername(String userid,String productid,Integer companyid);
	
	public List<String> productname(String userid,Integer companyid);
	
	public List<Integer> playercount_closesum(List<String> userid,Integer companyid);
	
	public List<TmpPlayer> checkuserid(String productid,String userid,Integer companyid);
	
	public List<TmpPlayer> allprofileuserid(Integer wemplayerid,Integer companyid);
	
	public List<TmpPlayer> getwemplayerid(String userid,Integer companyid);
	
	public List<TmpPlayer> gettopupplayer_statisticcs(Integer companyid,List<String> productid,Integer wemplayerid,Date fromdate,Date todate);

}
