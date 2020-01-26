package com.example.bank.bean;

import java.util.Date;
import java.util.List;

import com.example.bank.support.CustomerDateAndTimeDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class GenerateteleBean {
	
	private Date registerdate;
	  
	  private String userid;
	  
	  private String telno;
	 
	 private Integer companyid1;
	 
	 private Double wl;
	 
	 private Double topup;
	 
	 private Double bonus;
	 
	 private Double withdraw;
	 
	 private String contactmethod;

	 private String generatefrom;
	 
	 private String issuedby;
	 
	 private Date issueddate;
	 
	 private Date resolutiondate;
	 
	 @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	public Date getRegisterdate() {
		return registerdate;
	}

	 @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	public void setRegisterdate(Date registerdate) {
		this.registerdate = registerdate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public Integer getCompanyid1() {
		return companyid1;
	}

	public void setCompanyid1(Integer companyid1) {
		this.companyid1 = companyid1;
	}

	public Double getWl() {
		return wl;
	}

	public void setWl(Double wl) {
		this.wl = wl;
	}

	public Double getTopup() {
		return topup;
	}

	public void setTopup(Double topup) {
		this.topup = topup;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public Double getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(Double withdraw) {
		this.withdraw = withdraw;
	}

	public String getContactmethod() {
		return contactmethod;
	}

	public void setContactmethod(String contactmethod) {
		this.contactmethod = contactmethod;
	}

	public String getGeneratefrom() {
		return generatefrom;
	}

	public void setGeneratefrom(String generatefrom) {
		this.generatefrom = generatefrom;
	}

	public String getIssuedby() {
		return issuedby;
	}

	public void setIssuedby(String issuedby) {
		this.issuedby = issuedby;
	}

	 @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	public Date getIssueddate() {
		return issueddate;
	}

	 @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	public void setIssueddate(Date issueddate) {
		this.issueddate = issueddate;
	}

	 @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	public Date getResolutiondate() {
		return resolutiondate;
	}

	 @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	public void setResolutiondate(Date resolutiondate) {
		this.resolutiondate = resolutiondate;
	}
	
	 
	
}
