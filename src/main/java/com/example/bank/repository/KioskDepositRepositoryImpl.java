package com.example.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.bank.model.Expensestype;
import com.example.bank.model.KioskDeposit;

public class KioskDepositRepositoryImpl implements KioskDepositCustomRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private KioskDepositRepository kioskdepositRepository;
	
	@Override
	public Double getkioskdeposit(Integer companyid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void savekioskdeposit(KioskDeposit kioskdeposit) {
		String SQL_Query = "\r\n" + 
				"Insert into KIOSK_DEPOSIT( USERNAME, PASSWORD, AMOUNT, USER_ID, PRODUCT_ID, COMPANY_ID , SCRAPER_RESP)" + 
				"VALUES(:username,:password,:amount,:userid, :productid, :companyid, :scraperresp)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("username", kioskdeposit.getUsername());
		query.setParameter("password", kioskdeposit.getPassword());
		query.setParameter("amount", kioskdeposit.getAmount());
		query.setParameter("userid", kioskdeposit.getUserid());
		query.setParameter("productid", kioskdeposit.getProductid());
		query.setParameter("companyid", kioskdeposit.getCompanyid());
		query.setParameter("scraperresp", kioskdeposit.getScraperresp());
		
		
		int result =  query.executeUpdate();
		 
		 
	}



}
