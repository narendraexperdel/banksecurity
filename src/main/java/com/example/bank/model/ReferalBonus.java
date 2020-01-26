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
@Table(name = "REFERAL_BONUS")
public class ReferalBonus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "ISSUEDBY")
	private String issuedby;
	
	@Column(name = "ISSUEDDATE")
	private Date issueddate;
	
	@Column(name = "ISSUEDTIME")
	private Date issuedtime;
	
	@Column(name = "PRODUCT")
	private String product;
	
	@Column(name = "REMARK")
	private String remark;
	
	@Column(name = "USER_ID")
	private String userid;

	@Column(name = "AMOUNT")
	private Double amount;
	

	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "CORPORATE_ID")
	private String corporateid;
	
	@OneToOne
    @JoinColumn(name="COMPANY_ID")
	 private MainCompany companyid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIssuedby() {
		return issuedby;
	}

	public void setIssuedby(String issuedby) {
		this.issuedby = issuedby;
	}

	public Date getIssueddate() {
		return issueddate;
	}

	public void setIssueddate(Date issueddate) {
		this.issueddate = issueddate;
	}

	public Date getIssuedtime() {
		return issuedtime;
	}

	public void setIssuedtime(Date issuedtime) {
		this.issuedtime = issuedtime;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCorporateid() {
		return corporateid;
	}

	public void setCorporateid(String corporateid) {
		this.corporateid = corporateid;
	}

	public MainCompany getCompanyid() {
		return companyid;
	}

	public void setCompanyid(MainCompany companyid) {
		this.companyid = companyid;
	}
	
	

}
