package com.example.bank.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.example.bank.model.Cmcexpenses;
import com.example.bank.model.Cmcexpensesdet;
import com.example.bank.model.Wettopup;

public class CmcexpensesdetRepositoryImpl implements CmcexpensesdetCustomRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Cmcexpensesdet> getallexpensesdetbycompany(Integer companyid, List<String> expenses) {
	
			Criteria cr =  entityManager.unwrap(Session.class).createCriteria(Cmcexpensesdet.class).add(
			        Restrictions.and
			        
			        (
			        		
			            Restrictions.eq("companyid.id", companyid),
			            Restrictions.in("exid", expenses)
			           
			        )
			    );
		 
		
	       return cr.list();
		
	}

}
