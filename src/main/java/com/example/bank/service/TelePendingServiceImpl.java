package com.example.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.TelePending;
import com.example.bank.repository.TelePendingRepository;

@Component
public class TelePendingServiceImpl implements TelePendingService{
	
	@Autowired
	TelePendingRepository telependingRepository;

	@Override
	public List<TelePending> checktelepending(String userid, String generatedfrom, Integer companyid) {
		// TODO Auto-generated method stub
		return telependingRepository.checktelepending(userid, generatedfrom, companyid);
	}

	@Override
	public void savetelepending(TelePending telepending) {
		// TODO Auto-generated method stub
		 telependingRepository.savetelepending(telepending);
	}

	@Override
	public List<TelePending> countofteleremark(Date fromdate, Date todate, String remark, String assignto,
			Integer companyid) {
		// TODO Auto-generated method stub
		return telependingRepository.countofteleremark(fromdate, todate, remark, assignto, companyid);
	}

	@Override
	public List<TelePending> countofteleremark_total(Date fromdate, Date todate, String remark, Integer companyid) {
		// TODO Auto-generated method stub
		return telependingRepository.countofteleremark_total(fromdate, todate, remark, companyid);
	}

	@Override
	public List<TelePending> distinctuserid(Date fromdate, Date todate, String assignto, Integer companyid) {
		// TODO Auto-generated method stub
		return telependingRepository.distinctuserid(fromdate, todate, assignto, companyid);
	}

	@Override
	public List<String> distinctassign(Date fromdate, Date todate, String assignto, Integer companyid) {
		// TODO Auto-generated method stub
		return telependingRepository.distinctassign(fromdate, todate, assignto, companyid);
	}

	@Override
	public List<String> distinctuserid_closesum(Date fromdate, Date todate, String assignto, String gentype,
			Integer companyid) {
		// TODO Auto-generated method stub
		return telependingRepository.distinctuserid_closesum(fromdate, todate, assignto, gentype, companyid);
	}

}
