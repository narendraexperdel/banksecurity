package com.example.bank.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.bean.TicketRaised;
import com.example.bank.bean.TicketRaisedBean;
import com.example.bank.repository.TicketRaisedRepository;
import com.example.bank.repository.TicketRaisedRepositoryImpl;

@Component
public class TicketRaisedServiceImpl implements TicketRaisedService{
	
	
	@Autowired
	TicketRaisedRepository ticketraisedRepository;

	@Override
	public TicketRaised saveticketraised(TicketRaisedBean ticketraised) throws SQLException {
		// TODO Auto-generated method stub
		return ticketraisedRepository.saveticket(ticketraised);
	}

	@Override
	public List<TicketRaised> allTicket() throws SQLException {
		// TODO Auto-generated method stub
		return ticketraisedRepository.allTicket();
	}

	@Override
	public Long pendingticketcount() {
		// TODO Auto-generated method stub
		return ticketraisedRepository.pendingticketcount();
	}

	
}
