package com.example.bank.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bank.model.TicketRaised;

@Component
public class TicketRaisedRepositoryImpl {
	
	@Autowired
	TicketRaisedRepository ticketraisedRepository;
	
	public TicketRaised saveticket(TicketRaised ticketraised) {
		return ticketraisedRepository.save(ticketraised);
	}

}
