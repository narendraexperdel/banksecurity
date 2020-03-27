package com.example.bank.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.bank.model.Teledata;

public class TeleDataRepositoryImpl implements TeleDataCustomRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void saveteledata(Teledata teledata) {
		String SQL_Query = "\r\n" + 
				"Insert into TELE_DATA(USER_ID, LAST_DEPOSIT_AMOUNT, LAST_DEPOSIT_DATE, COMPANY_NAME)" + 
				"VALUES(:userid,:lastdepositamount,:lastdepositdate,:companyname)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("userid", teledata.getUserid());
		query.setParameter("lastdepositamount", teledata.getLastdepositamount());
		query.setParameter("lastdepositdate", teledata.getLastdepositdate());
		query.setParameter("companyname", teledata.getCompanyname());
		
		int result =  query.executeUpdate();
		
	}

}
