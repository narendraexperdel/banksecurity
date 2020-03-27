package com.example.bank.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.bank.model.Removevsadd;

public class RemovevsaddRepositoryImpl implements RemovevsaddCustomRespository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public void saveremovevsadd(Removevsadd removevsadd) {
		String SQL_Query = "\r\n" + 
				"Insert into REMOVE_VS_ADD(FROM_PRODUCT, TO_PRODUCT, FROM_PLAYER,TO_PLAYER ,AMOUNT,COMPANY_ID )" + 
				"VALUES(:fromproduct,:toproduct,:fromplayer, :toplayer,:amount,:companyid)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("fromproduct", removevsadd.getFromproduct().getProduct_id());
		query.setParameter("toproduct", removevsadd.getToproduct().getProduct_id());
		query.setParameter("fromplayer", removevsadd.getFromplayer());
		query.setParameter("toplayer", removevsadd.getToplayer());
		query.setParameter("amount", removevsadd.getAmount());
		query.setParameter("companyid", removevsadd.getCompanyid().getId());
		
		int result =  query.executeUpdate();
		
	}

}
