package com.example.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="REMOVE_VS_ADD")
public class Removevsadd {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	 @OneToOne
     @JoinColumn(name="FROM_PRODUCT")
	 private CmcProduct fromproduct;
	
	 @OneToOne
     @JoinColumn(name="TO_PRODUCT")
	 private CmcProduct toproduct;
	 
	 
	 @Column(name="FROM_PLAYER")
	 private String fromplayer;
	    
	
	 @Column(name="TO_PLAYER")
	 private String toplayer;
	   
	 @Column(name = "AMOUNT")
	 private Double amount;
	 
	 @OneToOne
     @JoinColumn(name="COMPANY_ID")
	 private MainCompany companyid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CmcProduct getFromproduct() {
		return fromproduct;
	}

	public void setFromproduct(CmcProduct fromproduct) {
		this.fromproduct = fromproduct;
	}

	public CmcProduct getToproduct() {
		return toproduct;
	}

	public void setToproduct(CmcProduct toproduct) {
		this.toproduct = toproduct;
	}

	public String getFromplayer() {
		return fromplayer;
	}

	public void setFromplayer(String fromplayer) {
		this.fromplayer = fromplayer;
	}

	public String getToplayer() {
		return toplayer;
	}

	public void setToplayer(String toplayer) {
		this.toplayer = toplayer;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public MainCompany getCompanyid() {
		return companyid;
	}

	public void setCompanyid(MainCompany companyid) {
		this.companyid = companyid;
	}
	    
     
}
