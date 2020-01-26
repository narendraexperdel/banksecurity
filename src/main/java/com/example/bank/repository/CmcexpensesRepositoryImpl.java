package com.example.bank.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.example.bank.model.CmcProduct;
import com.example.bank.model.Cmcexpenses;

public class CmcexpensesRepositoryImpl implements CmcexpensesCustomRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Cmcexpenses> getallexpensesbycompany(Integer companyid) {
		 Criteria cre = entityManager.unwrap(Session.class).createCriteria(Cmcexpenses.class);
			cre.add(Restrictions.eq("companyid.id", companyid));
		
			return (List<Cmcexpenses>) cre.list();
	}

}
