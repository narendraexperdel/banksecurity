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
@Table(name="WEM_PLAYER")
public class WemPlayer implements Serializable  {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "TELNO")
	private String telno;
	
	@Column(name = "FLNAME")
	private String flname;
	
	@Column(name = "SOURCES")
	private String sources;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name="RACE")
	private String race;
	
	@Column(name = "BANKCD")
	private String bankcd;
	
	@Column(name = "BANKACC")
	private String bankacc;
	
	@Column(name = "ISSUEDBY")
	private String issuedby;
	
	@Column(name = "ISSUEDDT")
	private Date issueddt;
	
	@Column(name = "CONMETHOD")
	private String conmethod;
	
	@Column(name = "TELNO2")
	private String telno2;
	
	@Column(name = "TELNO3")
	private String telno3;
	
	@Column(name = "REFERER")
	private String referer;
	
	@Column(name = "WECHAT")
	private String wechat;
	
	@Column(name = "WHATAPPS")
	private String whatapps;
	
	@Column(name = "SMS_NOTIFICATION")
	private String smsnotification;
	
	@Column(name = "WKK_STAFF")
	private String wkkstaff;
	
	@Column(name = "VIP")
	private String vip;
	
	@Column(name = "ICNO")
	private String icno;
	
	@Column(name = "BANK_HOLDER")
	private String bankholder;
	
	@Column(name = "WCWS")
	private String wcws;
	
	@Column(name = "DOB")
	private Date dob;
	
	@Column(name = "NAME_OF_COMP")
	private String nameofcomp;
	
	@Column(name = "NAME_OF_PRODU")
	private String nameofprodu;
	
	@Column(name = "API_LINKED_ID")
	private String apilinkedid;
	
	@Column(name = "TYPE")
	private String type;
	
	 @OneToOne
     @JoinColumn(name="COMPANY_ID")
	 private MainCompany companyid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getFlname() {
		return flname;
	}

	public void setFlname(String flname) {
		this.flname = flname;
	}

	public String getSources() {
		return sources;
	}

	public void setSources(String sources) {
		this.sources = sources;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getBankcd() {
		return bankcd;
	}

	public void setBankcd(String bankcd) {
		this.bankcd = bankcd;
	}

	public String getBankacc() {
		return bankacc;
	}

	public void setBankacc(String bankacc) {
		this.bankacc = bankacc;
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

	public String getConmethod() {
		return conmethod;
	}

	public void setConmethod(String conmethod) {
		this.conmethod = conmethod;
	}

	public String getTelno2() {
		return telno2;
	}

	public void setTelno2(String telno2) {
		this.telno2 = telno2;
	}

	public String getTelno3() {
		return telno3;
	}

	public void setTelno3(String telno3) {
		this.telno3 = telno3;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getWhatapps() {
		return whatapps;
	}

	public void setWhatapps(String whatapps) {
		this.whatapps = whatapps;
	}

	public String getSmsnotification() {
		return smsnotification;
	}

	public void setSmsnotification(String smsnotification) {
		this.smsnotification = smsnotification;
	}

	public String getWkkstaff() {
		return wkkstaff;
	}

	public void setWkkstaff(String wkkstaff) {
		this.wkkstaff = wkkstaff;
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	public String getIcno() {
		return icno;
	}

	public void setIcno(String icno) {
		this.icno = icno;
	}

	public String getBankholder() {
		return bankholder;
	}

	public void setBankholder(String bankholder) {
		this.bankholder = bankholder;
	}

	public String getWcws() {
		return wcws;
	}

	public void setWcws(String wcws) {
		this.wcws = wcws;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getNameofcomp() {
		return nameofcomp;
	}

	public void setNameofcomp(String nameofcomp) {
		this.nameofcomp = nameofcomp;
	}

	public String getNameofprodu() {
		return nameofprodu;
	}

	public void setNameofprodu(String nameofprodu) {
		this.nameofprodu = nameofprodu;
	}

	public String getApilinkedid() {
		return apilinkedid;
	}

	public void setApilinkedid(String apilinkedid) {
		this.apilinkedid = apilinkedid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MainCompany getCompanyid() {
		return companyid;
	}

	public void setCompanyid(MainCompany companyid) {
		this.companyid = companyid;
	}
	 
	 

}
