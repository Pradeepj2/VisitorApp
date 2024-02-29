package com.visitorsApp.visitors.Model;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "visitor")
public class Visitor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "MobileNumber")
	private String mobNo;
	
	@Column(name = "VisitorName")
	private String name;
	
	@Column(name = "HostName")
	private String hostName;
	
	@Column(name = "HostMobNo")
	private String hostMobNo;
	
	@Column(name = "Department")
	private String department;
	
	@Column(name = "Office")
	private String office;
	
	@Column(name = "PurposeOfVisit")
	private String purposeOfVisit;
	
	@Column(name = "ProfileImg")
	private String imageName;
	
 

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

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostMobNo() {
		return hostMobNo;
	}

	public void setHostMobNo(String hostMobNo) {
		this.hostMobNo = hostMobNo;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getPurposeOfVisit() {
		return purposeOfVisit;
	}
	

	public void setPurposeOfVisit(String purposeOfVisit) {
		this.purposeOfVisit = purposeOfVisit;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "Visitor [id=" + id + ", mobNo=" + mobNo + ", name=" + name + ", hostName=" + hostName + ", hostMobNo="
				+ hostMobNo + ", department=" + department + ", office=" + office + ", purposeOfVisit=" + purposeOfVisit
				+ ", imageName=" + imageName + "]";
	}


	
}
