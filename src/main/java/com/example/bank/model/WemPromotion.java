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
@Table(name = "WEM_PROMOTION")
public class WemPromotion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "PROMOTIONCD")
	private String promotioncd;
	
	@Column(name = "FLDESC")
	private String fldesc;
	
	@Column(name = "DISCRT")
	private String discrt;
	
	@Column(name = "EFTDTFRM")
	private Date eftdtfrm;
	
	@Column(name = "EFTDTTO")
	private Date eftdtto;
	
	@Column(name = "APRODUCT_ID")
	private String aproductid;
	
	@Column(name = "ISSUEDDT")
	private Date issueddt;
	
	@Column(name = "ISSUEDBY")
	private String issuedby;
	
	@Column(name = "`CLOSE`")
	private String close;
	
	@Column(name = "LIMIT")
	private Double limit;
	
	@Column(name = "DAILY_QTY")
	private Integer dailyqty;
	
	@Column(name = "SEQNO")
	private Integer seqno;
	
	@Column(name = "MONTHLY_QTY")
	private Integer monthlyqty;
	
	@Column(name = "MIN_TOPUP")
	private Double mintopup;
	
	@Column(name = "PROMOTION_RATE")
	private Double promorate;
	
	 @OneToOne
     @JoinColumn(name="COMPANY_ID")
	 private MainCompany companyid;
	 
	 @Column(name = "EXPIRY_STATUS")
		private String expstatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPromotioncd() {
		return promotioncd;
	}

	public void setPromotioncd(String promotioncd) {
		this.promotioncd = promotioncd;
	}

	public String getFldesc() {
		return fldesc;
	}

	public void setFldesc(String fldesc) {
		this.fldesc = fldesc;
	}

	public String getDiscrt() {
		return discrt;
	}

	public void setDiscrt(String discrt) {
		this.discrt = discrt;
	}

	public Date getEftdtfrm() {
		return eftdtfrm;
	}

	public void setEftdtfrm(Date eftdtfrm) {
		this.eftdtfrm = eftdtfrm;
	}

	public Date getEftdtto() {
		return eftdtto;
	}

	public void setEftdtto(Date eftdtto) {
		this.eftdtto = eftdtto;
	}

	public String getAproductid() {
		return aproductid;
	}

	public void setAproductid(String aproductid) {
		this.aproductid = aproductid;
	}

	public Date getIssueddt() {
		return issueddt;
	}

	public void setIssueddt(Date issueddt) {
		this.issueddt = issueddt;
	}

	public String getIssuedby() {
		return issuedby;
	}

	public void setIssuedby(String issuedby) {
		this.issuedby = issuedby;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public Double getLimit() {
		return limit;
	}

	public void setLimit(Double limit) {
		this.limit = limit;
	}

	public Integer getDailyqty() {
		return dailyqty;
	}

	public void setDailyqty(Integer dailyqty) {
		this.dailyqty = dailyqty;
	}

	public Integer getSeqno() {
		return seqno;
	}

	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}

	public Integer getMonthlyqty() {
		return monthlyqty;
	}

	public void setMonthlyqty(Integer monthlyqty) {
		this.monthlyqty = monthlyqty;
	}

	public Double getMintopup() {
		return mintopup;
	}

	public void setMintopup(Double mintopup) {
		this.mintopup = mintopup;
	}

	public Double getPromorate() {
		return promorate;
	}

	public void setPromorate(Double promorate) {
		this.promorate = promorate;
	}

	public MainCompany getCompanyid() {
		return companyid;
	}

	public void setCompanyid(MainCompany companyid) {
		this.companyid = companyid;
	}

	public String getExpstatus() {
		return expstatus;
	}

	public void setExpstatus(String expstatus) {
		this.expstatus = expstatus;
	}
	

}
