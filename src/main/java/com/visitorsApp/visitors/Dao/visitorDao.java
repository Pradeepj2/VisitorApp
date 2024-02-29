package com.visitorsApp.visitors.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.visitorsApp.visitors.Model.Visitor;
import com.visitorsApp.visitors.Model.VisitorModalDto;

public interface visitorDao extends JpaRepository<Visitor,Integer>{

}
