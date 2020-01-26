package com.example.bank.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.example.bank.model.CmcIncomedet;
import com.example.bank.model.Cmcexpensesdet;

public class CmcIncomeDetRepositoryImpl implements CmcIncomeDetCustomRepository{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CmcIncomedet> getallincomedetbycompany(Integer companyid, List<String> income) {
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(CmcIncomedet.class).add(
		        Restrictions.and
		        
		        (
		        		
		            Restrictions.eq("companyid.id", companyid),
		            Restrictions.in("exid", income)
		           
		        )
		    );
	 
	
       return cr.list();
	}
}
