package com.example.bank.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OTH_INCOME")
public class OthIncome {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TRANC_ID")
	private Integer trancid;
	
	@Column(name = "ISSUEDBY")
	private String issuedby;
	
	@Column(name = "ISSUEDDT")
	private Date issueddate;
	
	@Column(name = "BANK_ID")
	private Integer bankid;
	
	@Column(name = "BANKCD")
	private String bankcd;
	
	@Column(name = "BANK_NAME")
	private String bankname;
	
	@Column(name = "AMOUNT")
	private Double amount;
	
	@Column(name = "REMARK")
	private String remark;
	
	@Column(name = "INCOME_ID")
	private Integer incomeid;
	
	@Column(name = "INCOME_NAME")
	private String incomename;
	
	@Column(name = "INCOMEDT_ID")
	private Integer incomedtid;
	
	@Column(name = "INCMDTL_NAME")
	private String incmdtlname;
	
	@Column(name = "TYPE")
	private String type;

	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "CORPORATE_ID")
	private String corporateid;
	
	@OneToOne
    @JoinColumn(name="COMPANY_ID")
	 private MainCompany companyid;

	public Integer getTrancid() {
		return trancid;
	}

	public void setTrancid(Integer trancid) {
		this.trancid = trancid;
	}

	public String getIssuedby() {
		return issuedby;
	}

	public void setIssuedby(String issuedby) {
		this.issuedby = issuedby;
	}

	public Date getIssueddate() {
		return issueddate;
	}

	public void setIssueddate(Date issueddate) {
		this.issueddate = issueddate;
	}

	public Integer getBankid() {
		return bankid;
	}

	public void setBankid(Integer bankid) {
		this.bankid = bankid;
	}

	public String getBankcd() {
		return bankcd;
	}

	public void setBankcd(String bankcd) {
		this.bankcd = bankcd;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIncomeid() {
		return incomeid;
	}

	public void setIncomeid(Integer incomeid) {
		this.incomeid = incomeid;
	}

	public String getIncomename() {
		return incomename;
	}

	public void setIncomename(String incomename) {
		this.incomename = incomename;
	}

	public Integer getIncomedtid() {
		return incomedtid;
	}

	public void setIncomedtid(Integer incomedtid) {
		this.incomedtid = incomedtid;
	}

	public String getIncmdtlname() {
		return incmdtlname;
	}

	public void setIncmdtlname(String incmdtlname) {
		this.incmdtlname = incmdtlname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCorporateid() {
		return corporateid;
	}

	public void setCorporateid(String corporateid) {
		this.corporateid = corporateid;
	}

	public MainCompany getCompanyid() {
		return companyid;
	}

	public void setCompanyid(MainCompany companyid) {
		this.companyid = companyid;
	}
	
	

}
