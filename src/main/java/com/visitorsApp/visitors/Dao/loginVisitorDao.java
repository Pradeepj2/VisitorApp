package com.visitorsApp.visitors.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.visitorsApp.visitors.Model.VisitorModalDto;
import com.visitorsApp.visitors.Model.visitorLogin;

public interface loginVisitorDao extends JpaRepository<visitorLogin, Integer> {
	@Query(value = "select count(mobile_number) from visitor_login where mobile_number = :mobNo and otp = :otp" , nativeQuery = true)
	int isVerified(String mobNo, int otp);
	
	@Query(value = "select * from visitor_login where mobile_number = :mobNo" , nativeQuery = true)
	visitorLogin findByMobNo(String mobNo);
}
