package com.example.bank.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.bank.model.Profiledata;

public class ProfiledataRepositoryImpl implements ProfiledataCustomRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void saveprofiledta(Profiledata profiledata) {
		String SQL_Query = "\r\n" + 
				"Insert into PROFILE_DATA(FLNAME, TELNO, BANKACC, TOTAL_IN ,TOTAL_OUT, WL, COMPANY_ID )" + 
				"VALUES(:flname,:telno,:bankacc, :totalin,:totalout,:wl,:companyid)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("flname", profiledata.getFlname());
		query.setParameter("telno", profiledata.getTelno());
		query.setParameter("bankacc", profiledata.getBankacc());
		query.setParameter("totalin", profiledata.getTotalin());
		query.setParameter("totalout", profiledata.getTotalout());
		
		query.setParameter("wl", profiledata.getWl());
		query.setParameter("companyid", profiledata.getCompanyid().getId());
		
		int result =  query.executeUpdate();
	}

}
