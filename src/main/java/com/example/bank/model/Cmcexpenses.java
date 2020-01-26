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
@Table(name="CMC_EXPENSES")
public class Cmcexpenses {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "TRANC_ID")
	private Integer trancid;
	
	
	@Column(name = "FLDESC")
	private String fldesc;
	
	@Column(name = "SEQNO")
	private Integer seqno;
	
	@Column(name = "EX_FLG")
	private String dtdesc;
	
	
	 @OneToOne
     @JoinColumn(name="COMPANY_ID")
	 private MainCompany companyid;


	public Integer getTrancid() {
		return trancid;
	}


	public void setTrancid(Integer trancid) {
		this.trancid = trancid;
	}


	public String getFldesc() {
		return fldesc;
	}


	public void setFldesc(String fldesc) {
		this.fldesc = fldesc;
	}


	public Integer getSeqno() {
		return seqno;
	}


	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}


	public String getDtdesc() {
		return dtdesc;
	}


	public void setDtdesc(String dtdesc) {
		this.dtdesc = dtdesc;
	}


	public MainCompany getCompanyid() {
		return companyid;
	}


	public void setCompanyid(MainCompany companyid) {
		this.companyid = companyid;
	}
	 
	 

}
