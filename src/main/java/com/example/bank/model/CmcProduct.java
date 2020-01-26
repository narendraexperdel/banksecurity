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
@Table(name="CMC_PRODUCT")
public class CmcProduct {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PRODUCT_ID")
	private Integer product_id;
	
	
	@Column(name = "FLDESC")
	private String fldesc;
	
	@Column(name = "SEQNO")
	private Integer seqno;
	
	@Column(name = "DTDESC")
	private String dtdesc;
	
	@Column(name = "CLOSE_")
	private String close;
	
	@Column(name = "DECIMAL_ARROW")
	private String decimalallow;
	
	@Column(name = "OPENNING")
	private Double openning;
	
	@Column(name = "OPENNINGDT")
	private Date openningdate;
	
	@Column(name ="MASTER_POINT")
	private Double masterpoint;
	
	@Column(name = "ADVANCE")
	private Double advance;
	
	@Column(name = "WITHDRAW")
	private Double withdraw;
	
	@Column(name = "TOP_UP")
	private Double topup;
	
	@Column(name = "BONUS")
	private Double bonus;
	
	@Column(name = "POINT")
	private Double point;
	
	@Column(name = "CLOSING")
	private Double closing;
	
	 @OneToOne
     @JoinColumn(name="COMPANY_ID")
	 private MainCompany companyid;

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
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

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public String getDecimalallow() {
		return decimalallow;
	}

	public void setDecimalallow(String decimalallow) {
		this.decimalallow = decimalallow;
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

	public Double getMasterpoint() {
		return masterpoint;
	}

	public void setMasterpoint(Double masterpoint) {
		this.masterpoint = masterpoint;
	}

	public Double getAdvance() {
		return advance;
	}

	public void setAdvance(Double advance) {
		this.advance = advance;
	}

	public Double getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(Double withdraw) {
		this.withdraw = withdraw;
	}

	public Double getTopup() {
		return topup;
	}

	public void setTopup(Double topup) {
		this.topup = topup;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public Double getPoint() {
		return point;
	}

	public void setPoint(Double point) {
		this.point = point;
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
	
	

}
