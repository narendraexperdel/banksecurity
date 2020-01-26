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
@Table(name = "EXPENSES_TYPE")
public class Expensestype {
	
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

	
	@Column(name = "BANK_NAME")
	private String bankname;
	
	@Column(name = "EXPENSES_ID")
	private Integer expenseid;
	

	@Column(name = "EXPENSES_NAME")
	private String expensename;
	
	@Column(name = "EXPENSESDT_NAME")
	private String expensedtname;
	
	
	@Column(name = "AMOUNT")
	private Double amount;
	
	@Column(name = "GST_AMOUNT")
	private Double gstamount;
	
	@Column(name = "REMARK")
	private String remark;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "STATUS")
	private String status;
	
	@OneToOne
    @JoinColumn(name="COMPANY_id")
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

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public Integer getExpenseid() {
		return expenseid;
	}

	public void setExpenseid(Integer expenseid) {
		this.expenseid = expenseid;
	}

	public String getExpensename() {
		return expensename;
	}

	public void setExpensename(String expensename) {
		this.expensename = expensename;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getGstamount() {
		return gstamount;
	}

	public void setGstamount(Double gstamount) {
		this.gstamount = gstamount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public MainCompany getCompanyid() {
		return companyid;
	}

	public void setCompanyid(MainCompany companyid) {
		this.companyid = companyid;
	}

	public String getExpensedtname() {
		return expensedtname;
	}

	public void setExpensedtname(String expensedtname) {
		this.expensedtname = expensedtname;
	}
	
	

}
