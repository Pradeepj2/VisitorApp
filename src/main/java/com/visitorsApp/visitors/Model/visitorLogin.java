package com.visitorsApp.visitors.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "VisitorLogin")
public class visitorLogin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "MobileNumber")
	private String mobNo;
	
	@Column(name = "Otp")
	private int otp;
	
	@Column(name = "Status")
	private String  status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public visitorLogin(int id, String mobNo, int otp, String status) {
		super();
		this.id = id;
		this.mobNo = mobNo;
		this.otp = otp;
		this.status = status;
	}

	public visitorLogin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
