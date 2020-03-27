package com.example.bank.model;

import java.io.Serializable;
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
@Table(name="PROFILE_DATA")
public class Profiledata implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "TELNO")
	private String telno;
	
	@Column(name = "FLNAME")
	private String flname;
	
	
	@Column(name = "BANKACC")
	private String bankacc;
	
	@Column(name = "TOTAL_IN")
	private Double totalin;
	
	@Column(name = "TOTAL_OUT")
	private Double totalout;
	
	@Column(name = "WL")
	private Double wl;
	
	 @OneToOne
     @JoinColumn(name="COMPANY_ID")
	 private MainCompany companyid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getFlname() {
		return flname;
	}

	public void setFlname(String flname) {
		this.flname = flname;
	}

	public String getBankacc() {
		return bankacc;
	}

	public void setBankacc(String bankacc) {
		this.bankacc = bankacc;
	}

	public Double getTotalin() {
		return totalin;
	}

	public void setTotalin(Double totalin) {
		this.totalin = totalin;
	}

	public Double getTotalout() {
		return totalout;
	}

	public void setTotalout(Double totalout) {
		this.totalout = totalout;
	}

	public Double getWl() {
		return wl;
	}

	public void setWl(Double wl) {
		this.wl = wl;
	}

	public MainCompany getCompanyid() {
		return companyid;
	}

	public void setCompanyid(MainCompany companyid) {
		this.companyid = companyid;
	}
	 
	 
	 
}
