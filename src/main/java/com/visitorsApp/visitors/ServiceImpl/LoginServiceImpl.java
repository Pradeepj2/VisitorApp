package com.visitorsApp.visitors.ServiceImpl;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.visitorsApp.visitors.Dao.loginVisitorDao;
import com.visitorsApp.visitors.Dao.visitorDao;
import com.visitorsApp.visitors.Model.Visitor;
import com.visitorsApp.visitors.Model.VisitorModalDto;
import com.visitorsApp.visitors.Model.visitorLogin;
import com.visitorsApp.visitors.Service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	loginVisitorDao loginVisitorDao;

	@Autowired
	visitorDao visitorDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public boolean saveVisitor(VisitorModalDto visitor) {

		boolean check = false;
		String pathString = "C:/Users/pradeep.rathor/Desktop/NewVisitor";

        File directoryPath = new File(pathString); 
        if (!directoryPath.exists()) directoryPath.mkdir();
        
        String fileName = visitor.getImageMultipart().getOriginalFilename();
        
        pathString += File.separator +  fileName;
        
        System.err.println(pathString);
        
      try {
    	  visitor.setImageName(fileName); 
   	   Files.copy(visitor.getImageMultipart().getInputStream(), Paths.get(pathString),new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
	} catch (Exception e) {
		// TODO: handle exception
	}
              
              
 /*      String filePath = pathString + File.separator + fileName ;
		//create folder if its not created
	
		File file2 = new File(pathString);
		
		if(!file2.exists()) {
			file2.mkdir();
		}
		
		try {
			Files.copy(img.getInputStream(), Paths.get(filePath),new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
			visitor.setImageName(fileName);
			System.err.println(visitor.toString());
			visitorDao.save(visitor);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
              Visitor visitors =  modelMapper.map(visitor , Visitor.class);
    
            try {
            	visitorDao.save(visitors);
              }
         catch (Exception e) {
                        System.out.println(e.getLocalizedMessage());
                      
            }
              return check;
  
	}

	@Override
	public void saveVisitorLogin(visitorLogin visitor) {
		loginVisitorDao.save(visitor);

	}

	@Override
	public int isVerified(String mobNo, int otp) {
		return loginVisitorDao.isVerified(mobNo, otp);
	}

	@Override
	public visitorLogin findByMobNo(String mobNo) {
		return loginVisitorDao.findByMobNo(mobNo);
	}

	@Override
	public List<Visitor> getVisitor() {
	return visitorDao.findAll();
	}

	@Override
	public Optional<Visitor> findById(int id) {
		return visitorDao.findById(id);
	}

}
