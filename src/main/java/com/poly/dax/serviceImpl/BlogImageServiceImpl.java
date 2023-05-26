package com.poly.dax.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dax.common.entity.BlogImageList;
import com.poly.dax.common.entity.BlogList;
import com.poly.dax.dao.BlogDAO;
import com.poly.dax.dao.BlogImageDAO;
import com.poly.dax.entity.Blog;
import com.poly.dax.entity.BlogImage;
import com.poly.dax.service.BlogImageService;
import com.poly.dax.service.BlogService;
import com.poly.dax.service.ImageService;

@Service
public class BlogImageServiceImpl implements BlogImageService {
    @Autowired
    private BlogImageDAO dao;
    @Autowired
    private BlogService blogService;
    @Autowired
    private ImageService imageService;

    @Override
    public List<BlogImageList> findAllImage() {
        return dao.getListImage();
    }
    
    @Override
    public BlogImage create(Integer id, Integer imageId) {
        BlogImage blogImage = new BlogImage();
        blogImage.setBlog(blogService.findById(id));
        blogImage.setImage(imageService.findById(imageId));
        return dao.save(blogImage);
    }
}
