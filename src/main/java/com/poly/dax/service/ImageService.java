package com.poly.dax.service;

import com.poly.dax.entity.Image;

public interface ImageService {
	Image create(String name);
	
	Image findById(Integer id);
}
