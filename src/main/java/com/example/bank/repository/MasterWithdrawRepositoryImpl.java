package com.example.bank.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.bank.model.MasterWithdraw;

public class MasterWithdrawRepositoryImpl implements MasterWithdrawCustomRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void savemasterwithdraw(MasterWithdraw masterwithdraw) {
		String SQL_Query = "\r\n" + 
				"Insert into MASTER_WITHDRAW(USERNAME, PASSWORD, BANK_ID, COMPANY_ID )" + 
				"VALUES(:username,:password,:bankid, :companyid)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("username", masterwithdraw.getUsername());
		query.setParameter("password", masterwithdraw.getPassword());
		query.setParameter("bankid", masterwithdraw.getBankid().getId());
		query.setParameter("companyid", masterwithdraw.getCompanyid().getId());
		
		int result =  query.executeUpdate();
		
	}

}
