package com.visitorsApp.visitors.Model;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VisitorModalDto {

	private int id;

	private String mobNo;

	private String name;

	private String hostName;

	private String hostMobNo;

	private String department;

	private String office;

	private String purposeOfVisit;

	private String imageName;

	private MultipartFile imageMultipart;
	
	  public VisitorModalDto() {
	        // Initialize any properties if needed
	    }
	
}
