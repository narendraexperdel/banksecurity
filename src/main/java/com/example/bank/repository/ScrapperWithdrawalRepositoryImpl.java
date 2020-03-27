package com.example.bank.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.bank.model.ScrapperWithdrawal;

public class ScrapperWithdrawalRepositoryImpl implements ScrapperWithdrawalCustomRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void savescrapperwithdrawal(ScrapperWithdrawal scrapperwithdrawal) {
		String SQL_Query = "\r\n" + 
				"Insert into SCRAPPER_WITHDRAWAL(COMBANK, BANK, BANKACC,BANK_HOLDER ,AMOUNT,COMPANY_ID )" + 
				"VALUES(:combank,:bank,:bankacc, :bankholder,:amount,:companyid)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("combank", scrapperwithdrawal.getCombank().getId());
		query.setParameter("bank", scrapperwithdrawal.getBank().getId());
		query.setParameter("bankacc", scrapperwithdrawal.getBankacc());
		query.setParameter("bankholder", scrapperwithdrawal.getBankholder());
		query.setParameter("amount", scrapperwithdrawal.getAmount());
		query.setParameter("companyid", scrapperwithdrawal.getCompanyid().getId());
		
		int result =  query.executeUpdate();
		
	}

}
