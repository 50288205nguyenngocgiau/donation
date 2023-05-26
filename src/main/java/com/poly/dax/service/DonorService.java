package com.poly.dax.service;

import java.util.List;
import com.poly.dax.entity.Donor;

public interface DonorService {
	Donor create(Donor donnor);
	
	List<Donor> findAllByBlogId(Integer id);

	List<Donor> findAll();
	
	void updateConfirm(Boolean confirm, Integer id);
	
//	List<Donor> findByEmail(String email);
}
