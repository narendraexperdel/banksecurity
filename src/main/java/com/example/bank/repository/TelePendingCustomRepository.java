package com.example.bank.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.model.TelePending;

@Service
public interface TelePendingCustomRepository {
	
	public List<TelePending> checktelepending(String userid,String generatedfrom, Integer companyid);
	
	public void savetelepending(TelePending telepending);
	
	public List<TelePending> countofteleremark(Date fromdate,Date todate,String remark,String assignto,Integer companyid);
	
	public List<TelePending> countofteleremark_total(Date fromdate,Date todate,String remark,Integer companyid);
	
	public List<TelePending> distinctuserid(Date fromdate,Date todate,String assignto,Integer companyid);
	
	public List<String> distinctassign(Date fromdate,Date todate,String assignto,Integer companyid);
	
	public List<String> distinctuserid_closesum(Date fromdate,Date todate,String assignto,String gentype,Integer companyid);

}
