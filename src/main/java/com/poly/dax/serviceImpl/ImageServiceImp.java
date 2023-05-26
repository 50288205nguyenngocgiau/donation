package com.poly.dax.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dax.dao.ImageDAO;
import com.poly.dax.entity.Image;
import com.poly.dax.service.ImageService;

@Service
public class ImageServiceImp implements ImageService{

	@Autowired
	ImageDAO dao;
	
	@Override
    public Image create(String name) {
        Image image = new Image();
        image.setName(name);
        return dao.save(image);
    }

	@Override
	public Image findById(Integer id) {
		return dao.findById(id).get();
	}
	

}
