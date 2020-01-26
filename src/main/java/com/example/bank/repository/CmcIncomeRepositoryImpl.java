package com.example.bank.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.example.bank.model.CmcIncome;
import com.example.bank.model.Cmcexpenses;

public class CmcIncomeRepositoryImpl implements CmcIncomeCustomRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CmcIncome> getallincomesbycompany(Integer companyid) {
		 Criteria cre = entityManager.unwrap(Session.class).createCriteria(CmcIncome.class);
			cre.add(Restrictions.eq("companyid.id", companyid));
		
			return (List<CmcIncome>) cre.list();
	}

}
