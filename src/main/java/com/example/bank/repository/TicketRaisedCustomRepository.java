package com.example.bank.repository;

import java.sql.SQLException;
import java.util.List;

import com.example.bank.bean.TicketRaised;
import com.example.bank.bean.TicketRaisedBean;

public interface TicketRaisedCustomRepository {

	public Long pendingticketcount();
	
	public TicketRaised saveticket(TicketRaisedBean ticketraised) throws SQLException;
	
	public List<TicketRaised> allTicket() throws SQLException;
	 
}
