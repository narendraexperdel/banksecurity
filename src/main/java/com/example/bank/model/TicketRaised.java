package com.example.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TicketRaised")
public class TicketRaised {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Id")
	private Integer id;
	
	@Column(name = "Subject")
	private String subject;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "User_ID")
	private String userid;
	
	@Column(name = "Ticket_ID")
	private String ticketid;
	
	@Column(name="Ticket_Raised_Date")
	private String ticketraiseddate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTicketid() {
		return ticketid;
	}

	public void setTicketid(String ticketid) {
		this.ticketid = ticketid;
	}

	public String getTicketraiseddate() {
		return ticketraiseddate;
	}

	public void setTicketraiseddate(String ticketraiseddate) {
		this.ticketraiseddate = ticketraiseddate;
	}
	
    
}
