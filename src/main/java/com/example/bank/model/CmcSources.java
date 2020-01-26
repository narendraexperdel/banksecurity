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
@Table(name="CMC_SOURCES")
public class CmcSources {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "SOURCE_ID")
	private Integer sourceid;
	
	@Column(name = "FLDESC")
	private String fldesc;
	
	@Column(name = "SEQNO")
	private Integer seqno;
	
	@Column(name = "CLOSE_")
	private String close;
	
	@OneToOne
    @JoinColumn(name="COMPANY_ID")
	private MainCompany companyid;

	public Integer getSourceid() {
		return sourceid;
	}

	public void setSourceid(Integer sourceid) {
		this.sourceid = sourceid;
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

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public MainCompany getCompanyid() {
		return companyid;
	}

	public void setCompanyid(MainCompany companyid) {
		this.companyid = companyid;
	}


	
}
