package com.example.bank.bean;

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
	
	@Column(name = "CASE_NUMBER")
	private String casenumber;
	
	@Column(name = "USER_CONTACT")
	private String usercontact;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "LAST_EDIT_DATE")
	private String lasteditdate;
	
	@Column(name="ASSIGNTO")
	private String assignto;
	
	@Column(name="GAME_NAME")
	private String gamename;
	
	@Column(name = "USER_ID")
	private String userid;
	
	@Column(name = "PLAYER_NAME")
	private String playername;
	
	@Column(name = "IMAGE")
	private String image;
	
	@Column(name = "FROM_TRANSFER")
	private String fromtransfer;
	
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

	public String getCasenumber() {
		return casenumber;
	}

	public void setCasenumber(String casenumber) {
		this.casenumber = casenumber;
	}

	public String getUsercontact() {
		return usercontact;
	}

	public void setUsercontact(String usercontact) {
		this.usercontact = usercontact;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLasteditdate() {
		return lasteditdate;
	}

	public void setLasteditdate(String lasteditdate) {
		this.lasteditdate = lasteditdate;
	}

	public String getAssignto() {
		return assignto;
	}

	public void setAssignto(String assignto) {
		this.assignto = assignto;
	}

	public String getGamename() {
		return gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPlayername() {
		return playername;
	}

	public void setPlayername(String playername) {
		this.playername = playername;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFromtransfer() {
		return fromtransfer;
	}

	public void setFromtransfer(String fromtransfer) {
		this.fromtransfer = fromtransfer;
	}

	
    
}
