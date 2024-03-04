package com.visitorsApp.visitors.Service;
 
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.visitorsApp.visitors.Model.Visitor;
import com.visitorsApp.visitors.Model.VisitorModalDto;
import com.visitorsApp.visitors.Model.visitorLogin;

public interface LoginService {
  
	public boolean saveVisitor(Visitor visitor , MultipartFile img);

	public int isVerified(String mobNo , int otp);

	public void saveVisitorLogin(visitorLogin visitor);

	public visitorLogin findByMobNo(String mobNo);

	public List<Visitor> getVisitor();


}
