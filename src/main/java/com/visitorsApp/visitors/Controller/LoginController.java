package com.visitorsApp.visitors.Controller;

import java.awt.font.ImageGraphicAttribute;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.visitorsApp.visitors.Model.Visitor;

import com.visitorsApp.visitors.Model.visitorLogin;
import com.visitorsApp.visitors.Response.GeneralMessage;
import com.visitorsApp.visitors.Response.ServiceResponse;
import com.visitorsApp.visitors.Service.LoginService;

@RestController
public class LoginController {

	@Autowired
	LoginService loginService;

	public int generate5DigitOtp() {
		Random random = new Random();
		int otp = 10000 + random.nextInt(90000);
		return otp;
	}

	public void sendOtp(String mobilenumber, int otp) {
		String jsonString1 = "{\"username\":\"cyfuttr3\",\"password\":\"hdkt694\",\"from\":\"CYFCLD\",\"pe_id\":\"1701161218507374157\",\"template_id\":\"1707162374991206542\",\"to\":["
				+ mobilenumber + "],\"text\":\"" + otp
				+ " is your Cyfuture - Cloud verification code. You need to enter this code in order to  verify your account.\",\"coding\":\"0\"}";
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<>(jsonString1, headers);
			restTemplate.postForEntity("http://web.smsgw.in/smsapi/jsonapi.jsp", request, String.class);
		} catch (Exception e) {
			System.err.println("error");
		}
	}

	// Method to validate mobile number
	private boolean isValidMobileNumber(String mobNo) {
		if (mobNo == null || mobNo.isEmpty()) {
			return false;
		}

		if (!mobNo.matches("\\d{10}")) { // Assuming 10 digits for a mobile number
			return false;
		}
		// Additional checks can be added based on your requirements
		return true;
	}

	@PostMapping("/login")
	public ServiceResponse<?> login(@RequestBody visitorLogin visitor) {

		String mobNo = visitor.getMobNo();
		int otp = generate5DigitOtp();

		if (isValidMobileNumber(mobNo)) {
			try {
				
				try {
					visitorLogin newOtpToVisitor = loginService.findByMobNo(mobNo);
					newOtpToVisitor.setOtp(otp);
					loginService.saveVisitorLogin(newOtpToVisitor);
				} catch (Exception e) {
					visitor.setOtp(otp);
					loginService.saveVisitorLogin(visitor);
				}
				sendOtp(mobNo, otp);
				return new ServiceResponse<>(new GeneralMessage<>("Otp Sent",1, 201), HttpStatus.CREATED);
			} catch (Exception e) {
				return new ServiceResponse<>(new GeneralMessage<>("something went wrong ", 0, 502),
						HttpStatus.BAD_GATEWAY);
			}
		} else {
			return new ServiceResponse<>(new GeneralMessage<>("Invalid mobile number", 0, 400), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/otpValidate", method = RequestMethod.POST)
	public ServiceResponse<?> validateOtp(@RequestBody visitorLogin visitor) {

		String mobNoString = visitor.getMobNo();
		int otp = visitor.getOtp();

		try {
			int count = loginService.isVerified(mobNoString, otp);
			if (count > 0)
				return new ServiceResponse<>(new GeneralMessage<>("Verified", 1, 200), HttpStatus.OK);
			else
				return new ServiceResponse<>(new GeneralMessage<>("Wrong Otp", 0, 400), HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			return new ServiceResponse<>(new GeneralMessage<>("Something went wrong", 0, 500),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "/newVisitor", method = RequestMethod.POST )
	public ServiceResponse<?> newVisitor( @RequestPart("img") MultipartFile img ,  @RequestPart("newVisitor") Visitor visitor) {  
		
		try {
			loginService.saveVisitor(visitor , img);
			return new ServiceResponse<>(new GeneralMessage<>("new Visitor Registered", 1, 200), HttpStatus.OK);
		} catch (Exception e) {
			return new ServiceResponse<>(new GeneralMessage<>("Something went wrong", 1, 500), HttpStatus.INTERNAL_SERVER_ERROR);
		}
			 	
	}
	
	@RequestMapping(value = "/getVisitor", method = RequestMethod.GET )
	public ServiceResponse<?> getAllVisitors() {  
		
		try {
			List<Visitor> visitors =  loginService.getVisitor();
			return new ServiceResponse<>(new GeneralMessage<>("Data found",visitors, 1, 200), HttpStatus.OK);
		} catch (Exception e) {
			return new ServiceResponse<>(new GeneralMessage<>("Something went wrong", 1, 500), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
