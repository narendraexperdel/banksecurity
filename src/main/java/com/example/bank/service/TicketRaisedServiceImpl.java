package com.example.bank.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.TicketRaised;
import com.example.bank.repository.TicketRaisedRepositoryImpl;

@Component
public class TicketRaisedServiceImpl implements TicketRaisedService{
	
	@Autowired
	TicketRaisedRepositoryImpl ticketraisedrepositoryImpl;

	@Override
	public TicketRaised saveticketraised(TicketRaised ticketraised) throws SQLException {
		// TODO Auto-generated method stub
		return ticketraisedrepositoryImpl.saveticket(ticketraised);
	}

	@Override
	public List<TicketRaised> allTicket() throws SQLException {
		// TODO Auto-generated method stub
		return ticketraisedrepositoryImpl.allTicket();
	}

	
}
