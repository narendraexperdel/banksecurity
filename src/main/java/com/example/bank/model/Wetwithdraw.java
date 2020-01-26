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
@Table(name = "WET_WITHDRAW")
public class Wetwithdraw {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TRANC_ID")
	private Integer trancid;
	
	@Column(name = "TELNO")
	private String telno;
	
	@Column(name = "AMOUNT")
	private Double amount;
	
	@Column(name = "BONUS")
	private Double bonus;
	
	@Column(name = "ISSUEDDT")
	private Date issueddate;
	
	@Column(name = "ISSUEDBY")
	private String issuedby;
	
	@Column(name = "USER_ID")
	private String userid;
	
	
	@Column(name = "SHIFT")
	private String shift;
	
	@Column(name = "TIME")
	private Date time;
	
	@Column(name = "PRODUCT_ID")
	private String productid;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "TAXAMT")
	private Double taxamt;
	
	@Column(name = "COMPANY_BANK")
	private String companybank;
	
	
	@Column(name = "REMARK")
	private String remark;
	
	
	@Column(name = "BANK_ID")
	private Integer bankid;
	
	@Column(name = "BANKCD")
	private String bankcd;
	
	@Column(name = "BANK_NAME")
	private String bankname;
	
	@Column(name = "BANKACC")
	private String bankacc;
	
	
	@Column(name = "CS_APPROVE")
	private String csapprove;
	

	@Column(name = "BANK_APPROVE")
	private String bankapprove;
	
	@Column(name = "HOLDER_NAME")
	private String holdername;
	
	@Column(name = "CASHOUT_STATUS")
	private String cashoutstatus;
	
	@Column(name = "RCPDATE")
	private String rcpdate;
	
	@Column(name = "BANK_DONE_DATETIME")
	private Date bankdonetime;
	
	@Column(name = "CASHOUT_DONE_DATETIME")
	private Date cashoutdonetime;
	
	@Column(name = "CS_DONE_DATETIME")
	private Date csdonetime;
	
	

	@Column(name = "LATE_TRANC_ID")
	private Integer latetrancid;
	
/*
	@Column(name = "4D")
	private String d;*/
	
	@Column(name = "DUP_TRANC_ID")
	private Integer duptrancid;
		
	 @OneToOne
     @JoinColumn(name="COMPANY_ID")
	 private MainCompany companyid;

	public Integer getTrancid() {
		return trancid;
	}

	public void setTrancid(Integer trancid) {
		this.trancid = trancid;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
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

	public Double getTaxamt() {
		return taxamt;
	}

	public void setTaxamt(Double taxamt) {
		this.taxamt = taxamt;
	}

	public String getCompanybank() {
		return companybank;
	}

	public void setCompanybank(String companybank) {
		this.companybank = companybank;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getBankid() {
		return bankid;
	}

	public void setBankid(Integer bankid) {
		this.bankid = bankid;
	}

	public String getBankcd() {
		return bankcd;
	}

	public void setBankcd(String bankcd) {
		this.bankcd = bankcd;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBankacc() {
		return bankacc;
	}

	public void setBankacc(String bankacc) {
		this.bankacc = bankacc;
	}

	public String getCsapprove() {
		return csapprove;
	}

	public void setCsapprove(String csapprove) {
		this.csapprove = csapprove;
	}

	public String getBankapprove() {
		return bankapprove;
	}

	public void setBankapprove(String bankapprove) {
		this.bankapprove = bankapprove;
	}

	public String getHoldername() {
		return holdername;
	}

	public void setHoldername(String holdername) {
		this.holdername = holdername;
	}

	public String getCashoutstatus() {
		return cashoutstatus;
	}

	public void setCashoutstatus(String cashoutstatus) {
		this.cashoutstatus = cashoutstatus;
	}

	public String getRcpdate() {
		return rcpdate;
	}

	public void setRcpdate(String rcpdate) {
		this.rcpdate = rcpdate;
	}

	public Integer getLatetrancid() {
		return latetrancid;
	}

	public void setLatetrancid(Integer latetrancid) {
		this.latetrancid = latetrancid;
	}

	/*public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}*/

	public Integer getDuptrancid() {
		return duptrancid;
	}

	public void setDuptrancid(Integer duptrancid) {
		this.duptrancid = duptrancid;
	}

	public MainCompany getCompanyid() {
		return companyid;
	}

	public void setCompanyid(MainCompany companyid) {
		this.companyid = companyid;
	}

	public Date getBankdonetime() {
		return bankdonetime;
	}

	public void setBankdonetime(Date bankdonetime) {
		this.bankdonetime = bankdonetime;
	}

	public Date getCashoutdonetime() {
		return cashoutdonetime;
	}

	public void setCashoutdonetime(Date cashoutdonetime) {
		this.cashoutdonetime = cashoutdonetime;
	}

	public Date getCsdonetime() {
		return csdonetime;
	}

	public void setCsdonetime(Date csdonetime) {
		this.csdonetime = csdonetime;
	}
	 
	@Override
    public boolean equals(Object obj) {
        return (this.userid.equals(((Wetwithdraw) obj).userid)
                && this.companybank.equals(((Wetwithdraw) obj).companybank));
    }

}
