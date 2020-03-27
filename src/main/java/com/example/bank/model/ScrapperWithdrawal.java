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
@Table(name="SCRAPPER_WITHDRAWAL")
public class ScrapperWithdrawal {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	 @OneToOne
     @JoinColumn(name="COMBANK")
	 private Cmccombank combank;
	
	 @OneToOne
     @JoinColumn(name="BANK")
	 private Cmcbank bank;
	 
	 @Column(name="BANKACC")
	 private String bankacc;
	    
	
	 @Column(name="BANK_HOLDER")
	 private String bankholder;
	   
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

	public Cmccombank getCombank() {
		return combank;
	}

	public void setCombank(Cmccombank combank) {
		this.combank = combank;
	}

	public Cmcbank getBank() {
		return bank;
	}

	public void setBank(Cmcbank bank) {
		this.bank = bank;
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
