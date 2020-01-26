package com.example.bank.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name = "WET_TOPUP")
public class Wettopup {
	
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
	
	@Column(name = "CLAIM")
	private String claim;
	
	@Column(name = "SHIFT")
	private String shift;
	
	@Column(name = "TIME")
	private Date time;
	
	@Column(name = "BANK_ID")
	private Integer bankid;
	
	@Column(name = "BANK")
	private String bank;
	
	@Column(name = "PRODUCT_ID")
	private String productid;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "COMPANY_BANK")
	private Integer companybank;
	
	@Column(name = "CLAIM_BY")
	private String claimby;
	
	@Column(name = "CLAIMDT")
	private Date claimdate;
	
	@Column(name = "ADJUSTMENT")
	private String adjustment;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "ADJUSTMENT_REMARK")
	private String adjustmentremark;
	
	@Column(name = "PROMOTIONCD")
	private String promotioncd;
	
	@Column(name = "REMARK")
	private String remark;
	
	@Column(name = "ADJUSTMENT_TYPE")
	private String adjustmenttype;
	
	@Column(name = "KIOSK_STATUS")
	private String kioskstatus;
	
	@Column(name = "CS_APPROVEBY")
	private String csapproveby;
	
	@Column(name = "BANK_APPROVEBY")
	private String bankapproveby;
	
	@Column(name = "KIOSK_DONE_BY")
	private String kioskdoneby;
	
	@Column(name = "RCPDATE")
	private String rcpdate;
	
	@Column(name = "LATE_TRANC_ID")
	private Integer latetrancid;
	
	@Column(name = "ADJUSTMENT_CATEGORY")
	private String adjustmentcategory;
	
	@Column(name = "SPECIAL")
	private String special;
	
	@Column(name = "SPECIAL_AMT1")
	private Double specialamt1;
	
	@Column(name = "SPECIAL_AMT2")
	private Double specialmat2;
	
	@Column(name = "ANGPAU_NAME")
	private String angpauname;
	
	@Column(name = "DEPOSIT_VIA")
	private String depositvia;
	
	@Column(name = "BANK_IN_TYPE")
	private String bankintype;
	
	@Column(name = "CS_CLAIM")
	private String csclaim;
	
	@Column(name = "PLAYER_ID")
	private String playerid;
	
	@Column(name = "TOTAL_AMOUNT")
	private Double totalamount;
	
	@Column(name = "CUSTOMER_NAME")
	private String customername;
	
	@Column(name = "CS_DONE_DATETIME")
	private Date csdonetime;
	
	@Column(name = "BANK_DONE_DATETIME")
	private Date bankdonetime;
	
	
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
	public String getClaim() {
		return claim;
	}
	public void setClaim(String claim) {
		this.claim = claim;
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
	public Integer getBankid() {
		return bankid;
	}
	public void setBankid(Integer bankid) {
		this.bankid = bankid;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
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
	public Integer getCompanybank() {
		return companybank;
	}
	public void setCompanybank(Integer companybank) {
		this.companybank = companybank;
	}
	public String getClaimby() {
		return claimby;
	}
	public void setClaimby(String claimby) {
		this.claimby = claimby;
	}
	public Date getClaimdate() {
		return claimdate;
	}
	public void setClaimdate(Date claimdate) {
		this.claimdate = claimdate;
	}
	public String getAdjustment() {
		return adjustment;
	}
	public void setAdjustment(String adjustment) {
		this.adjustment = adjustment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAdjustmentremark() {
		return adjustmentremark;
	}
	public void setAdjustmentremark(String adjustmentremark) {
		this.adjustmentremark = adjustmentremark;
	}
	public String getPromotioncd() {
		return promotioncd;
	}
	public void setPromotioncd(String promotioncd) {
		this.promotioncd = promotioncd;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAdjustmenttype() {
		return adjustmenttype;
	}
	public void setAdjustmenttype(String adjustmenttype) {
		this.adjustmenttype = adjustmenttype;
	}
	public String getKioskstatus() {
		return kioskstatus;
	}
	public void setKioskstatus(String kioskstatus) {
		this.kioskstatus = kioskstatus;
	}
	public String getCsapproveby() {
		return csapproveby;
	}
	public void setCsapproveby(String csapproveby) {
		this.csapproveby = csapproveby;
	}
	public String getBankapproveby() {
		return bankapproveby;
	}
	public void setBankapproveby(String bankapproveby) {
		this.bankapproveby = bankapproveby;
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
	public String getAdjustmentcategory() {
		return adjustmentcategory;
	}
	public void setAdjustmentcategory(String adjustmentcategory) {
		this.adjustmentcategory = adjustmentcategory;
	}
	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
	}
	public Double getSpecialamt1() {
		return specialamt1;
	}
	public void setSpecialamt1(Double specialamt1) {
		this.specialamt1 = specialamt1;
	}
	public Double getSpecialmat2() {
		return specialmat2;
	}
	public void setSpecialmat2(Double specialmat2) {
		this.specialmat2 = specialmat2;
	}
	public String getAngpauname() {
		return angpauname;
	}
	public void setAngpauname(String angpauname) {
		this.angpauname = angpauname;
	}
	public String getDepositvia() {
		return depositvia;
	}
	public void setDepositvia(String depositvia) {
		this.depositvia = depositvia;
	}
	public String getBankintype() {
		return bankintype;
	}
	public void setBankintype(String bankintype) {
		this.bankintype = bankintype;
	}
	public String getCsclaim() {
		return csclaim;
	}
	public void setCsclaim(String csclaim) {
		this.csclaim = csclaim;
	}
	public String getPlayerid() {
		return playerid;
	}
	public void setPlayerid(String playerid) {
		this.playerid = playerid;
	}
	public Double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public MainCompany getCompanyid() {
		return companyid;
	}
	public void setCompanyid(MainCompany companyid) {
		this.companyid = companyid;
	}
	public Date getCsdonetime() {
		return csdonetime;
	}
	public void setCsdonetime(Date csdonetime) {
		this.csdonetime = csdonetime;
	}
	public Date getBankdonetime() {
		return bankdonetime;
	}
	public void setBankdonetime(Date bankdonetime) {
		this.bankdonetime = bankdonetime;
	}
	public String getKioskdoneby() {
		return kioskdoneby;
	}
	public void setKioskdoneby(String kioskdoneby) {
		this.kioskdoneby = kioskdoneby;
	}
	
	  @Override
	    public boolean equals(Object obj) {
	        return (this.userid.equalsIgnoreCase(((Wettopup) obj).userid)
	                && this.productid.equals(((Wettopup) obj).productid));
	    }

}
