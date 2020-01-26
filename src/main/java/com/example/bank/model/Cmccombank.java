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
@Table(name="CMC_COMBANK")
public class Cmccombank {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "FLDESC")
	private String fldesc;
	
	@Column(name = "SEQNO")
	private Integer seqno;
	
	
	@Column(name = "CLOSE_")
	private String close;
	

	@Column(name = "OPENNING")
	private Double openning;
	
	@Column(name = "OPENNINGDT")
	private Date openningdate;
	
	
	@Column(name = "CLOSING")
	private Double closing;
	
	 @OneToOne
     @JoinColumn(name="COMP_id")
	 private MainCompany companyid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Double getOpenning() {
		return openning;
	}

	public void setOpenning(Double openning) {
		this.openning = openning;
	}

	public Date getOpenningdate() {
		return openningdate;
	}

	public void setOpenningdate(Date openningdate) {
		this.openningdate = openningdate;
	}

	public Double getClosing() {
		return closing;
	}

	public void setClosing(Double closing) {
		this.closing = closing;
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
	 
	 

}
