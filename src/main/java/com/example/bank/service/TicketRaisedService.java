package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.model.TicketRaised;

@Service
public interface TicketRaisedService {
	
	public TicketRaised saveticketraised(TicketRaised ticketraised);
	
	public List<TicketRaised> allTicket();
		
}
