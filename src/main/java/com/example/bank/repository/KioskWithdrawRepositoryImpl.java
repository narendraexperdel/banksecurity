package com.example.bank.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.bank.model.KioskWithdraw;

public class KioskWithdrawRepositoryImpl implements KioskWithdrawCustomRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void savekioskwithdraw(KioskWithdraw kioskwithdraw) {
		String SQL_Query = "\r\n" + 
				"Insert into KIOSK_WITHDRAW( USERNAME, PASSWORD, AMOUNT, USER_ID, PRODUCT_ID, COMPANY_ID , SCRAPER_RESP)" + 
				"VALUES(:username,:password,:amount,:userid, :productid, :companyid, :scraperresp)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("username", kioskwithdraw.getUsername());
		query.setParameter("password", kioskwithdraw.getPassword());
		query.setParameter("amount", kioskwithdraw.getAmount());
		query.setParameter("userid", kioskwithdraw.getUserid());
		query.setParameter("productid", kioskwithdraw.getProductid());
		query.setParameter("companyid", kioskwithdraw.getCompanyid());
		query.setParameter("scraperresp", kioskwithdraw.getScraperresp());
		
		
		int result =  query.executeUpdate();
		
	}

}
