package com.example.bank.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.bank.model.Wettopup;

public class WettopupRepositoryImpl implements WettopupCustomRepository{

	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<Wettopup> getallUnclaimlist() {
		 Criteria cr = entityManager.unwrap(Session.class).createCriteria(Wettopup.class);
		   cr.add(Restrictions.eq("claim", "N"));
	       return cr.list();
	}

}
