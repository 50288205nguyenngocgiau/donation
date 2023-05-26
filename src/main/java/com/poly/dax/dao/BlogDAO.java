package com.poly.dax.dao;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.dax.entity.Blog;


public interface BlogDAO extends JpaRepository<Blog, Integer>{
	@Query(value = "call getAllBlog()", nativeQuery = true)
    List<Tuple> getAllBlog();
	
	List<Blog> findBlogByAccountEmail(String email);
	
}
