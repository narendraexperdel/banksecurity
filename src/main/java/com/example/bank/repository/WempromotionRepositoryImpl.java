package com.example.bank.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.bank.model.WemPromotion;
import com.example.bank.model.Wettopup;

public class WempromotionRepositoryImpl implements WempromotionCustomRepository{
	
	@Autowired
	EntityManager entityManager;

	@Override
	public List<WemPromotion> getwempromobypromoname(String fldesc, Integer companyid) {
		 Criteria cr = entityManager.unwrap(Session.class).createCriteria(WemPromotion.class);
		 cr.add(Restrictions.eq("fldesc", fldesc));  
		   cr.add(Restrictions.eq("companyid.id", companyid));
		   cr.add(Restrictions.eq("companyid.id", companyid));
		   cr.add(Restrictions.eq("expstatus", "Active"));
		   
		   
		return  cr.list();
	}

	@Override
	public List<WemPromotion> getwempromobycompanyid(Integer companyid) {
		 Criteria cr = entityManager.unwrap(Session.class).createCriteria(WemPromotion.class);
		 
		   cr.add(Restrictions.eq("companyid.id", companyid));
		   cr.add(Restrictions.eq("expstatus", "Active"));
		   
		return  cr.list();
	}

}
