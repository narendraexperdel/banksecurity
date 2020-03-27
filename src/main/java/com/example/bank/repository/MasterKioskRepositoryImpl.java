package com.example.bank.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.example.bank.model.CmcProduct;
import com.example.bank.model.MasterKiosk;

public class MasterKioskRepositoryImpl implements MasterKioskCustomRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<MasterKiosk> getmasterkiosk(Integer companyid, Integer productid) {
		 Criteria cre = entityManager.unwrap(Session.class).createCriteria(MasterKiosk.class);
			cre.add(Restrictions.eq("companyid.id", companyid));
			cre.add(Restrictions.eq("productid.product_id", productid));
		
			return (List<MasterKiosk>) cre.list();
	}

	@Transactional
	@Override
	public void savemasterkiosk(MasterKiosk masterkiosk) {
		String SQL_Query = "\r\n" + 
				"Insert into MASTER_KIOSK(USERNAME, PASSWORD, PRODUCT_ID, COMPANY_ID )" + 
				"VALUES(:username,:password,:productid, :companyid)";
		Query query = entityManager.unwrap(Session.class).createSQLQuery(
				SQL_Query);
		query.setParameter("username", masterkiosk.getUsername());
		query.setParameter("password", masterkiosk.getPassword());
		query.setParameter("productid", masterkiosk.getProductid().getProduct_id());
		query.setParameter("companyid", masterkiosk.getCompanyid().getId());
		
		
		int result =  query.executeUpdate();
		
	}


}
