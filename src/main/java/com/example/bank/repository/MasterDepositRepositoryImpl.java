package com.example.bank.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.bank.model.MasterDeposit;

public class MasterDepositRepositoryImpl implements MasterDepositCustomRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public void savemasterdeposit(MasterDeposit masterdeposit) {
		String SQL_Query = "\r\n" + 
				"Insert into MASTER_DEPOSIT(USERNAME, PASSWORD, BANK_ID, COMPANY_ID )" + 
				"VALUES(:username,:password,:bankid, :companyid)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("username", masterdeposit.getUsername());
		query.setParameter("password", masterdeposit.getPassword());
		query.setParameter("bankid", masterdeposit.getCmccombank().getId());
		query.setParameter("companyid", masterdeposit.getCompanyid().getId());
		
		
		int result =  query.executeUpdate();
		
	}

}
