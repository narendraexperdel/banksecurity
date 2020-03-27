package com.example.bank.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TELE_DATA")
public class Teledata {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "LAST_DEPOSIT_AMOUNT")
	private Double lastdepositamount;
	
	@Column(name = "LAST_DEPOSIT_DATE")
	private Date lastdepositdate;
	
	@Column(name = "USER_ID")
	private String userid;
	
	@Column(name = "COMPANY_NAME")
	private String companyname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getLastdepositamount() {
		return lastdepositamount;
	}

	public void setLastdepositamount(Double lastdepositamount) {
		this.lastdepositamount = lastdepositamount;
	}

	public Date getLastdepositdate() {
		return lastdepositdate;
	}

	public void setLastdepositdate(Date lastdepositdate) {
		this.lastdepositdate = lastdepositdate;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
}
