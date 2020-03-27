package com.example.bank.bean;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;


public class TicketRaisedBean {
	
	
	private Integer id;
	
	
	private String subject;
	
	
	private String description;
	
	
	private String email;
	
	
	private String ticketid;
	
	
	private Date ticketraiseddate;
	
	
	private String casenumber;
	
	
	private String usercontact;
	
	
	private String status;
	
	
	private Date lasteditdate;
	
	
	private String assignto;
	
	
	private String gamename;
	
	
	private String userid;
	
	
	private String playername;
	
	
	private String image1;
	
	
	private String fromtransfer;
	

	private Double amount;
	
	private String bankname;
	
	
	private String bankaccount;
	
	
	private String togame;
	
	private String ticketdate;
	
	
	public String getTicketdate() {
		return ticketdate;
	}

	public void setTicketdate(String ticketdate) {
		this.ticketdate = ticketdate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public String getTogame() {
		return togame;
	}

	public void setTogame(String togame) {
		this.togame = togame;
	}

	public String getTouserid() {
		return touserid;
	}

	public void setTouserid(String touserid) {
		this.touserid = touserid;
	}

	private String touserid;
	
	
	
/*	private StandardMultipartHttpServletRequest image;*/
	
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

	public Date getTicketraiseddate() {
		return ticketraiseddate;
	}

	public void setTicketraiseddate(Date ticketraiseddate) {
		this.ticketraiseddate = ticketraiseddate;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Date getLasteditdate() {
		return lasteditdate;
	}

	public void setLasteditdate(Date lasteditdate) {
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

	

	public String getFromtransfer() {
		return fromtransfer;
	}

	public void setFromtransfer(String fromtransfer) {
		this.fromtransfer = fromtransfer;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	/*public StandardMultipartHttpServletRequest getImage() {
		return image;
	}

	public void setImage(StandardMultipartHttpServletRequest image) {
		this.image = image;
	}
*/
    
	
}
