package com.example.bank.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.bank.model.WemUser;
import com.example.bank.model.Wettopup;

public class WemUserRepositoryImpl implements WemUserCustomRepository {
	
	@Autowired
	EntityManager entityManager;

	@Override
	public List<WemUser> getwemuser(String username, String password) {
		 Criteria cr = entityManager.unwrap(Session.class).createCriteria(WemUser.class);
		   cr.add(Restrictions.eq("flname", username));
		   cr.add(Restrictions.eq("password", password));
	       return cr.list();
	}

}
