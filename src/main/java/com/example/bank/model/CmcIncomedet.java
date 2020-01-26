package com.example.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CMC_INCOMEDET")
public class CmcIncomedet {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "TRANC_ID")
	private Integer trancid;
	
	@Column(name = "PID")
	private String exid;
	
	
	@Column(name = "FLDESC")
	private String fldesc;
	
	@Column(name = "SEQNO")
	private Integer seqno;
	
	 @OneToOne
     @JoinColumn(name="COMPANY_id")
	 private MainCompany companyid;

	public Integer getTrancid() {
		return trancid;
	}

	public void setTrancid(Integer trancid) {
		this.trancid = trancid;
	}

	public String getExid() {
		return exid;
	}

	public void setExid(String exid) {
		this.exid = exid;
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

	public MainCompany getCompanyid() {
		return companyid;
	}

	public void setCompanyid(MainCompany companyid) {
		this.companyid = companyid;
	}
	 
	 

}
