package com.example.bank.model;

import java.io.Serializable;
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
@Table(name="TMP_PLAYER")
public class TmpPlayer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "PRODUCT_ID")
	private String productid;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "USED")
	private String used;
	
	@Column(name = "ISSUEDBY")
	private String issuedby;
	
	@Column(name="ISSUEDDT")
	private Date issueddt;
	
	 @OneToOne
     @JoinColumn(name = "WEMPLAYER_ID")
	private WemPlayer wemplayerid;
	
	@Column(name = "ALLOCATION_STATUS")
	private char allocationstatus;
	
	@Column(name = "USER_ID")
	private String userid;
	
	@OneToOne
    @JoinColumn(name="COMPANY_ID")
	private MainCompany companyid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getIssuedby() {
		return issuedby;
	}

	public void setIssuedby(String issuedby) {
		this.issuedby = issuedby;
	}

	public Date getIssueddt() {
		return issueddt;
	}

	public void setIssueddt(Date issueddt) {
		this.issueddt = issueddt;
	}

	public WemPlayer getWemplayerid() {
		return wemplayerid;
	}

	public void setWemplayerid(WemPlayer wemplayerid) {
		this.wemplayerid = wemplayerid;
	}

	public char getAllocationstatus() {
		return allocationstatus;
	}

	public void setAllocationstatus(char allocationstatus) {
		this.allocationstatus = allocationstatus;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public MainCompany getCompanyid() {
		return companyid;
	}

	public void setCompanyid(MainCompany companyid) {
		this.companyid = companyid;
	}
	
	
	

}
