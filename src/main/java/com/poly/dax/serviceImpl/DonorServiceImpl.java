package com.poly.dax.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dax.dao.DonorDAO;
import com.poly.dax.entity.Blog;
import com.poly.dax.entity.Donor;
import com.poly.dax.service.DonorService;

@Service
public class DonorServiceImpl implements DonorService{
	@Autowired
	DonorDAO dao;

	@Override
	public Donor create(Donor donnor) {
		return dao.save(donnor);
	}

	@Override
	public List<Donor> findAllByBlogId(Integer id) {
		return dao.findAllByBlogId(id);
	}

	@Override
	public List<Donor> findAll() {
		return dao.findAll();
	}

	@Override
	public void updateConfirm(Boolean confirm, Integer id) {
		dao.updateConfirm(confirm, id);
	}
	

//	@Override
//	public List<Donor> findByEmail(String email) {
//		return dao.findByEmail(email);
//	}

}
