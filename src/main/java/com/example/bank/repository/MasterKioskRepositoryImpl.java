package com.example.bank.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
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
//			cre.add(Restrictions.eq("companyid.id", companyid));
			cre.add(Restrictions.eq("productid.product_id", productid));
		
			return (List<MasterKiosk>) cre.list();
	}


}
