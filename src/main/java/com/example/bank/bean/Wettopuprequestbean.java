package com.example.bank.bean;

import java.util.Date;
import java.util.List;

import com.example.bank.support.CustomerDateAndTimeDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.example.bank.support.*;

public class Wettopuprequestbean {
	
 private Date dateOfissue;
	 
	 private Date todate;
	 
	 private  List<String> promoname;
	 
	 private List<String> userids;
	  
	 private List<String> productid;
	  
	  private List<String> bank;
	  
	  private List<String> expense;
	  
	  private List<String> expensedet;
	  
	  private List<String> type;
	 
	 private Integer companyid1;
	 
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
		public List<String> getPromoname() {
			return promoname;
		}
		public void setPromoname(List<String> promoname) {
			this.promoname = promoname;
		}
		public List<String> getUserids() {
			return userids;
		}
		public void setUserids(List<String> userids) {
			this.userids = userids;
		}
		public Integer getCompanyid1() {
			return companyid1;
		}
		public void setCompanyid1(Integer companyid1) {
			this.companyid1 = companyid1;
		}

		public List<String> getProductid() {
			return productid;
		}

		public void setProductid(List<String> productid) {
			this.productid = productid;
		}

		public List<String> getBank() {
			return bank;
		}

		public void setBank(List<String> bank) {
			this.bank = bank;
		}

		public List<String> getExpense() {
			return expense;
		}

		public void setExpense(List<String> expense) {
			this.expense = expense;
		}

		public List<String> getExpensedet() {
			return expensedet;
		}

		public void setExpensedet(List<String> expensedet) {
			this.expensedet = expensedet;
		}

		public List<String> getType() {
			return type;
		}

		public void setType(List<String> type) {
			this.type = type;
		}	
		
		

}
