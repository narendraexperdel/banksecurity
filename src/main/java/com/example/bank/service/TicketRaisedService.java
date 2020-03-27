package com.example.bank.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bank.bean.TicketRaised;
import com.example.bank.bean.TicketRaisedBean;

@Service
public interface TicketRaisedService {
	
	public TicketRaised saveticketraised(TicketRaisedBean ticketraised) throws SQLException;
	
	public List<TicketRaised> allTicket() throws SQLException;
	
	public Long pendingticketcount();
		
}
