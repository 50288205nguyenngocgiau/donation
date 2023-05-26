package com.poly.dax.service;

import java.util.List;

import com.poly.dax.common.entity.BlogImageList;
import com.poly.dax.entity.BlogImage;


public interface BlogImageService {
	
	BlogImage create(Integer id, Integer image);

    List<BlogImageList> findAllImage();
}
