package com.example.bank.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private Integer amount;
	@Column(name = "BONUS")
	private Integer bonus;
	@Column(name = "ISSUEDDT")
	private String issueddate;
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
	private Integer productid;
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
	private Integer promotioncd;
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
	@Column(name = "RCPDATE")
	private String rcpdate;
	@Column(name = "LATE_TRANC_ID")
	private Integer latetrancid;
	@Column(name = "ADJUSTMENT_CATEGORY")
	private String adjustmentcategory;
	@Column(name = "SPECIAL")
	private String special;
	@Column(name = "SPECIAL_AMT1")
	private Integer specialamt1;
	@Column(name = "SPECIAL_AMT2")
	private Integer specialmat2;
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
	private Integer totalamount;
	@Column(name = "CUSTOMER_NAME")
	private String customername;
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
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getBonus() {
		return bonus;
	}
	public void setBonus(Integer bonus) {
		this.bonus = bonus;
	}
	public String getIssueddate() {
		return issueddate;
	}
	public void setIssueddate(String issueddate) {
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
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
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
	public Integer getPromotioncd() {
		return promotioncd;
	}
	public void setPromotioncd(Integer promotioncd) {
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
	public Integer getSpecialamt1() {
		return specialamt1;
	}
	public void setSpecialamt1(Integer specialamt1) {
		this.specialamt1 = specialamt1;
	}
	public Integer getSpecialmat2() {
		return specialmat2;
	}
	public void setSpecialmat2(Integer specialmat2) {
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
	public Integer getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(Integer totalamount) {
		this.totalamount = totalamount;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	
	

}
