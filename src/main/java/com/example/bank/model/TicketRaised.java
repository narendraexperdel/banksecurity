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
	
	@Column(name = "Email_ID")
	private String email;
	
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
	
	

	public TicketRaised(Integer id, String subject, String description, String email, String ticketid,
			String ticketraiseddate) {
		super();
		this.id = id;
		this.subject = subject;
		this.description = description;
		this.email = email;
		this.ticketid = ticketid;
		this.ticketraiseddate = ticketraiseddate;
	}

	/*@Override
	public String toString() {
		return "TicketRaised [id=" + id + ", subject=" + subject + ", description=" + description + ", userid=" + userid
				+ ", ticketid=" + ticketid + ", ticketraiseddate=" + ticketraiseddate + "]";
	}*/

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TicketRaised() {
		super();
	}
	
	
	
    
}
