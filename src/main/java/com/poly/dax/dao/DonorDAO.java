package com.poly.dax.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.poly.dax.entity.Donor;

public interface DonorDAO extends JpaRepository<Donor, Integer>{
	List<Donor> findAllByBlogId(Integer id);
	
	@Query("select d from Donor d where d.account.email=?1")
	List<Donor> findDonorByAccountId(String email);
	
	@Transactional 
    @Modifying
	@Query("update Donor d SET d.confirm =?1 where d.id = ?2 ")
	 void updateConfirm(Boolean confirm, Integer id);
}
