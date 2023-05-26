package com.poly.dax.service;

import java.util.List;

import com.poly.dax.common.entity.BlogList;
import com.poly.dax.entity.Blog;

public interface BlogService {
	List<BlogList> getAllBlog();
	
	Blog findBlogById(int id);
	
	List<Blog> findBlogByAccountEmail(String email);

	Blog create(Blog blog);
	
	List<Blog> findAll();

	Blog update(Blog blogUpdate);
	
	Blog findById(Integer id);
	
}
