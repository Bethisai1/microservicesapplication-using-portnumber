package com.sai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class bankapplications {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String bankname;
	private String bankaddress;
	public bankapplications() {
		super();
	}
	public bankapplications(int id, String bankname, String bankaddress) {
		super();
		this.id = id;
		this.bankname = bankname;
		this.bankaddress = bankaddress;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBankaddress() {
		return bankaddress;
	}
	public void setBankaddress(String bankaddress) {
		this.bankaddress = bankaddress;
	}
	@Override
	public String toString() {
		return "bankapplications [id=" + id + ", bankname=" + bankname + ", bankaddress=" + bankaddress + "]";
	}
	
	

}
