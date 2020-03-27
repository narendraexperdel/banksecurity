package com.example.bank.repository;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import com.example.bank.bean.TicketRaised;
import com.example.bank.bean.TicketRaisedBean;
import com.example.bank.model.Wettopup;


public class TicketRaisedRepositoryImpl implements TicketRaisedCustomRepository {
	
	@Autowired
	TicketRaisedRepository ticketraisedRepository;
	
	 @Autowired
	    DataSource dataSource;
	 
	 @Autowired
	  private JdbcTemplate jdbcTemplate;
	 
	 @Autowired
	 private EntityManager entityManager;
	 
	 SecureRandom random = new SecureRandom();
	
	public TicketRaised saveticket(TicketRaisedBean ticketraised) throws SQLException {
		
		TicketRaised tckraisemodel = new TicketRaised();
		
		tckraisemodel.setTicketraiseddate(ticketraised.getTicketraiseddate());
		tckraisemodel.setLasteditdate(ticketraised.getLasteditdate());
		tckraisemodel.setTicketdate(ticketraised.getTicketdate());
	
		tckraisemodel.setCasenumber(ticketraised.getCasenumber());
		
		tckraisemodel.setTicketid(ticketraised.getTicketid());
		tckraisemodel.setAssignto(ticketraised.getAssignto());
		tckraisemodel.setStatus(ticketraised.getStatus());
		tckraisemodel.setImage(ticketraised.getImage1());
		tckraisemodel.setUsercontact(ticketraised.getUsercontact());
		tckraisemodel.setGamename(ticketraised.getGamename());
		tckraisemodel.setPlayername(ticketraised.getPlayername());
		tckraisemodel.setSubject(ticketraised.getSubject());
		tckraisemodel.setDescription(ticketraised.getDescription());
		tckraisemodel.setBankaccount(ticketraised.getBankaccount());
		tckraisemodel.setBankname(ticketraised.getBankname());
		tckraisemodel.setAmount(ticketraised.getAmount());
		tckraisemodel.setTogame(ticketraised.getTogame());
		tckraisemodel.setTouserid(ticketraised.getTogame());
		tckraisemodel.setUserid(ticketraised.getUserid());
		
		System.out.println("Connection URL: "+ jdbcTemplate.getDataSource().getConnection().getMetaData().getURL());
		System.out.println("Our DataSource is = " + dataSource);
	
		return ticketraisedRepository.save(tckraisemodel);
	}
	
	public List<TicketRaised> allTicket() throws SQLException{
		System.out.println("Our DataSource is = " + dataSource);
		System.out.println("Our DataSource is = " + dataSource.getConnection());
		return ticketraisedRepository.findAll();
	}

	@Override
	public Long pendingticketcount() {
		
	
		Criteria cr =  entityManager.unwrap(Session.class).createCriteria(TicketRaised.class).add(
		        Restrictions.and
		        
		        (
		            Restrictions.eq("status", "Pending")
		           
		        )
		    );
		
		ProjectionList projectionList = Projections.projectionList();
	    
	    cr.setProjection(Projections.count("id"));
		return (Long) cr.uniqueResult();
	
	}

}
