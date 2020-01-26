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
@Table(name = "WET_TP")
public class Wettp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TRANC_ID")
	private Integer trancid;
	
	@Column(name = "ISSUEDDT")
	private Date issueddate;
	

	@Column(name = "ISSUEDBY")
	private String issuedby;
	
	@Column(name = "FRM_PRODUCT")
	private String frmproduct;
	
	@Column(name = "FRM_USER")
	private String frmuser;
	
	@Column(name = "TO_PRODUCT")
	private String toproduct;
	
	@Column(name = "TO_USER")
	private String touser;
	
	@Column(name = "AMOUNT")
	private Double amount;

	@Column(name = "SHIFT")
	private String shift;
	
	@Column(name = "REMARK")
	private String remark;
	
	@Column(name = "KIOSK_STATUS")
	private String kioskstatus;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "BANK_APPROVEBY")
	private String bankapproveby;
	
	@Column(name = "CS_APPROVEBY")
	private String csapproveby;
	
	@Column(name = "TYPE")
	private String type;
	
	 @OneToOne
    @JoinColumn(name="COMPANY_ID")
	 private MainCompany companyid;
	
	 @Column(name = "TIME")
	 private Date time;
	 
	 @Column(name = "CS_DONE_DATETIME")
		private Date csdonedatetime;

	public Integer getTrancid() {
		return trancid;
	}

	public void setTrancid(Integer trancid) {
		this.trancid = trancid;
	}

	public Date getIssueddate() {
		return issueddate;
	}

	public void setIssueddate(Date issueddate) {
		this.issueddate = issueddate;
	}

	public String getIssuedby() {
		return issuedby;
	}

	public void setIssuedby(String issuedby) {
		this.issuedby = issuedby;
	}

	public String getFrmproduct() {
		return frmproduct;
	}

	public void setFrmproduct(String frmproduct) {
		this.frmproduct = frmproduct;
	}

	public String getFrmuser() {
		return frmuser;
	}

	public void setFrmuser(String frmuser) {
		this.frmuser = frmuser;
	}

	public String getToproduct() {
		return toproduct;
	}

	public void setToproduct(String toproduct) {
		this.toproduct = toproduct;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getKioskstatus() {
		return kioskstatus;
	}

	public void setKioskstatus(String kioskstatus) {
		this.kioskstatus = kioskstatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBankapproveby() {
		return bankapproveby;
	}

	public void setBankapproveby(String bankapproveby) {
		this.bankapproveby = bankapproveby;
	}

	public String getCsapproveby() {
		return csapproveby;
	}

	public void setCsapproveby(String csapproveby) {
		this.csapproveby = csapproveby;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MainCompany getCompanyid() {
		return companyid;
	}

	public void setCompanyid(MainCompany companyid) {
		this.companyid = companyid;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getCsdonedatetime() {
		return csdonedatetime;
	}

	public void setCsdonedatetime(Date csdonedatetime) {
		this.csdonedatetime = csdonedatetime;
	}
	 
	
	 
	
}
