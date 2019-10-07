package com.example.bank.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import com.example.bank.model.TicketRaised;

@Component
public class TicketRaisedRepositoryImpl {
	
	@Autowired
	TicketRaisedRepository ticketraisedRepository;
	
	 @Autowired
	    DataSource dataSource;
	 
	 @Autowired
	  private JdbcTemplate jdbcTemplate;
	
	public TicketRaised saveticket(TicketRaised ticketraised) throws SQLException {
		
		System.out.println("Connection URL: "+ jdbcTemplate.getDataSource().getConnection().getMetaData().getURL());
		System.out.println("Our DataSource is = " + dataSource);
	
		return ticketraisedRepository.save(ticketraised);
	}
	
	public List<TicketRaised> allTicket() throws SQLException{
		System.out.println("Our DataSource is = " + dataSource);
		System.out.println("Our DataSource is = " + dataSource.getConnection());
		return ticketraisedRepository.findAll();
	}

}
