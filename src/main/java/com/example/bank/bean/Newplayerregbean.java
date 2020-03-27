package com.example.bank.bean;

import java.util.Date;
import java.util.List;

import com.example.bank.support.CustomerDateAndTimeDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Newplayerregbean {
	
	private Date dateOfissue;
	 
	 private Date todate;
	 
	  List<String> product;
	 
	  List<String> contactsources;
	  
	  private String userid;
	 
	 private Integer companyid1;
	 
	 private String playerid;
	 
	 private String productid;
	 
	 private List<String> playername;
	 
	 private String selectiontype;
	 
     private Date dateOfissuetopup;
	 
	 private Date todatetopup;
	 
	 private List<String> bank;
	 
	 private List<String> status;
	 
	 private List<String> frmproduct;
	 
	 private List<String> toproduct;
	 
	 private List<String> adjustmenttype;
	 
	 private List<String> adjustmentcategory;
	 
      private List<String> incometype;
	 
	 private List<String> incomedet;
	 
         private List<String> expensetype;
	 
	 private List<String> expensedet;
	 
	 private String playerId;
	 
	 private Double amount;
	 
	 private String username;
	 
	 private String password;
	 
	 private String kiosk;
	 
	 private String issuedby;
	 
	 private Integer companyid;
	 
	 private Integer bankid;
	 
	 private String loginid;
	 
	 private Integer product_id;
	 
	 private Integer fromproductid;
	 
	 private Integer toproductid;
	 
	 private String fromplayerid;
	 
	 private String toplayerid;
	 
	 private Integer bid;
	 
	 private String bankacc;
	 
	 private String bankholder;
	 
	 private Integer masterkioskid;
	 
	 private Integer masterwithdrawid;
	 
	 private Integer masterdepositid;
	 
	 private Integer removevsaddid;

	 private Integer scrapperwithdrawid;
	 
	 private Integer kioskdepositid;
	 
	 private Integer kioskwithdrawid;
	 
	 @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	public Date getDateOfissue() {
		return dateOfissue;
	}

	 @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	public void setDateOfissue(Date dateOfissue) {
		this.dateOfissue = dateOfissue;
	}

	 @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	public Date getTodate() {
		return todate;
	}

	 @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public List<String> getProduct() {
		return product;
	}

	public void setProduct(List<String> product) {
		this.product = product;
	}

	public List<String> getContactsources() {
		return contactsources;
	}

	public void setContactsources(List<String> contactsources) {
		this.contactsources = contactsources;
	}

	public Integer getCompanyid1() {
		return companyid1;
	}

	public void setCompanyid1(Integer companyid1) {
		this.companyid1 = companyid1;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPlayerid() {
		return playerid;
	}

	public void setPlayerid(String playerid) {
		this.playerid = playerid;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public List<String> getPlayername() {
		return playername;
	}

	public void setPlayername(List<String> playername) {
		this.playername = playername;
	}

	public String getSelectiontype() {
		return selectiontype;
	}

	public void setSelectiontype(String selectiontype) {
		this.selectiontype = selectiontype;
	}

	 @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	public Date getDateOfissuetopup() {
		return dateOfissuetopup;
	}

	 @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	public void setDateOfissuetopup(Date dateOfissuetopup) {
		this.dateOfissuetopup = dateOfissuetopup;
	}

	 @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	public Date getTodatetopup() {
		return todatetopup;
	}

	 @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	public void setTodatetopup(Date todatetopup) {
		this.todatetopup = todatetopup;
	}

	public List<String> getBank() {
		return bank;
	}

	public void setBank(List<String> bank) {
		this.bank = bank;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	public List<String> getFrmproduct() {
		return frmproduct;
	}

	public void setFrmproduct(List<String> frmproduct) {
		this.frmproduct = frmproduct;
	}

	public List<String> getToproduct() {
		return toproduct;
	}

	public void setToproduct(List<String> toproduct) {
		this.toproduct = toproduct;
	}

	public List<String> getAdjustmenttype() {
		return adjustmenttype;
	}

	public void setAdjustmenttype(List<String> adjustmenttype) {
		this.adjustmenttype = adjustmenttype;
	}

	public List<String> getAdjustmentcategory() {
		return adjustmentcategory;
	}

	public void setAdjustmentcategory(List<String> adjustmentcategory) {
		this.adjustmentcategory = adjustmentcategory;
	}

	public List<String> getIncometype() {
		return incometype;
	}

	public void setIncometype(List<String> incometype) {
		this.incometype = incometype;
	}

	public List<String> getIncomedet() {
		return incomedet;
	}

	public void setIncomedet(List<String> incomedet) {
		this.incomedet = incomedet;
	}

	public List<String> getExpensetype() {
		return expensetype;
	}

	public void setExpensetype(List<String> expensetype) {
		this.expensetype = expensetype;
	}

	public List<String> getExpensedet() {
		return expensedet;
	}

	public void setExpensedet(List<String> expensedet) {
		this.expensedet = expensedet;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getKiosk() {
		return kiosk;
	}

	public void setKiosk(String kiosk) {
		this.kiosk = kiosk;
	}

	public String getIssuedby() {
		return issuedby;
	}

	public void setIssuedby(String issuedby) {
		this.issuedby = issuedby;
	}

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public Integer getBankid() {
		return bankid;
	}

	public void setBankid(Integer bankid) {
		this.bankid = bankid;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getFromproductid() {
		return fromproductid;
	}

	public void setFromproductid(Integer fromproductid) {
		this.fromproductid = fromproductid;
	}

	public Integer getToproductid() {
		return toproductid;
	}

	public void setToproductid(Integer toproductid) {
		this.toproductid = toproductid;
	}

	public String getFromplayerid() {
		return fromplayerid;
	}

	public void setFromplayerid(String fromplayerid) {
		this.fromplayerid = fromplayerid;
	}

	public String getToplayerid() {
		return toplayerid;
	}

	public void setToplayerid(String toplayerid) {
		this.toplayerid = toplayerid;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getBankacc() {
		return bankacc;
	}

	public void setBankacc(String bankacc) {
		this.bankacc = bankacc;
	}

	public String getBankholder() {
		return bankholder;
	}

	public void setBankholder(String bankholder) {
		this.bankholder = bankholder;
	}

	public Integer getMasterkioskid() {
		return masterkioskid;
	}

	public void setMasterkioskid(Integer masterkioskid) {
		this.masterkioskid = masterkioskid;
	}

	public Integer getMasterwithdrawid() {
		return masterwithdrawid;
	}

	public void setMasterwithdrawid(Integer masterwithdrawid) {
		this.masterwithdrawid = masterwithdrawid;
	}

	public Integer getMasterdepositid() {
		return masterdepositid;
	}

	public void setMasterdepositid(Integer masterdepositid) {
		this.masterdepositid = masterdepositid;
	}

	public Integer getRemovevsaddid() {
		return removevsaddid;
	}

	public void setRemovevsaddid(Integer removevsaddid) {
		this.removevsaddid = removevsaddid;
	}

	public Integer getScrapperwithdrawid() {
		return scrapperwithdrawid;
	}

	public void setScrapperwithdrawid(Integer scrapperwithdrawid) {
		this.scrapperwithdrawid = scrapperwithdrawid;
	}

	public Integer getKioskdepositid() {
		return kioskdepositid;
	}

	public void setKioskdepositid(Integer kioskdepositid) {
		this.kioskdepositid = kioskdepositid;
	}

	public Integer getKioskwithdrawid() {
		return kioskwithdrawid;
	}

	public void setKioskwithdrawid(Integer kioskwithdrawid) {
		this.kioskwithdrawid = kioskwithdrawid;
	}

	
	
   
}
