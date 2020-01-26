package com.example.bank.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.example.bank.model.CmcProduct;
import com.example.bank.model.CmcSources;

public class CmcsourcesRepositoryImpl implements CmcsourcesCustomRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CmcSources> getallsourcebycompany(Integer companyid) {
		 Criteria cre = entityManager.unwrap(Session.class).createCriteria(CmcSources.class);
			cre.add(Restrictions.eq("companyid.id", companyid));
			cre.add(Restrictions.eq("close", "No"));
		
			return (List<CmcSources>) cre.list();
	}


}
