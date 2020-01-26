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
@Table(name = "TELE_PENDING")
public class TelePending {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PENDING_ID")
	private Integer pendingid;
	
	@Column(name = "ISSUEDBY")
	private String issuedby;
	
	@Column(name = "ISSUEDDT")
	private Date issueddt;
	
	@Column(name = "ISSUED_DATE")
	private Date issued_date;
	
	@Column(name = "USER_ID")
	private String userid;
	
	@Column(name = "WCWS")
	private String wcws;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "NICKNAME")
	private String nickname;
	
	@Column(name = "SOURCES_ID")
	private String sources;
	
	@Column(name = "TELNO")
	private String telno;
	
	@Column(name="GEN")
	private String gen;
	
	@Column(name = "GEN_TYPE")
	private String gentype;
	
	@Column(name = "RESOLUTIONDT")
	private Date resolutiondt;
	
	
	@Column(name = "PRODUCT")
	private String product;
	
	 @OneToOne
     @JoinColumn(name="COMPANY_ID")
	 private MainCompany companyid;

	public Integer getPendingid() {
		return pendingid;
	}

	public void setPendingid(Integer pendingid) {
		this.pendingid = pendingid;
	}

	public String getIssuedby() {
		return issuedby;
	}

	public void setIssuedby(String issuedby) {
		this.issuedby = issuedby;
	}

	public Date getIssueddt() {
		return issueddt;
	}

	public void setIssueddt(Date issueddt) {
		this.issueddt = issueddt;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getWcws() {
		return wcws;
	}

	public void setWcws(String wcws) {
		this.wcws = wcws;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSources() {
		return sources;
	}

	public void setSources(String sources) {
		this.sources = sources;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getGen() {
		return gen;
	}

	public void setGen(String gen) {
		this.gen = gen;
	}

	public String getGentype() {
		return gentype;
	}

	public void setGentype(String gentype) {
		this.gentype = gentype;
	}

	public Date getResolutiondt() {
		return resolutiondt;
	}

	public void setResolutiondt(Date resolutiondt) {
		this.resolutiondt = resolutiondt;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public MainCompany getCompanyid() {
		return companyid;
	}

	public void setCompanyid(MainCompany companyid) {
		this.companyid = companyid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getIssued_date() {
		return issued_date;
	}

	public void setIssued_date(Date issued_date) {
		this.issued_date = issued_date;
	}
	 
	 

}
