package com.example.bank.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.example.bank.model.Cmcbank;
import com.example.bank.model.Cmccombank;

public class CmcBankRepositoryImpl implements CmcBankCustomRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Cmcbank> getallbankbycompany(Integer companyid) {
		 Criteria cre = entityManager.unwrap(Session.class).createCriteria(Cmcbank.class);
			cre.add(Restrictions.eq("companyid.id", companyid));
			cre.add(Restrictions.eq("close", "No"));
		
			return (List<Cmcbank>) cre.list();
	}

}
