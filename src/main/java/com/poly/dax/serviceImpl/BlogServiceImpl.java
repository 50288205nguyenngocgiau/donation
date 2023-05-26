package com.poly.dax.serviceImpl;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dax.common.entity.BlogList;
import com.poly.dax.dao.BlogDAO;
import com.poly.dax.entity.Blog;
import com.poly.dax.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	BlogDAO dao;
	@Override
    public List<BlogList> getAllBlog(){
        List<Tuple> tuples = dao.getAllBlog();
        List<BlogList> blogsLists = tuples.stream().map(t -> new BlogList(
                t.get(0, Integer.class),
                t.get(1, String.class),
                t.get(2, Float.class),
                t.get(3, Float.class),
                t.get(4, Float.class),
                t.get(5, BigInteger.class),
                t.get(6, String.class),
                t.get(7, String.class),
                t.get(8, String.class),
                t.get(9, Double.class)
        )).collect(Collectors.toList());
        return blogsLists;
    }

	@Override
	public Blog findBlogById(int id) {
		return dao.findById(id).get();
	}

	@Override
	public List<Blog> findBlogByAccountEmail(String email) {
		return dao.findBlogByAccountEmail(email);
	}

	@Override
	public Blog create(Blog blog) {
		return dao.save(blog);
	}

	@Override
	public List<Blog> findAll() {
		return dao.findAll();
	}

	@Override
	public Blog update(Blog blogUpdate) {
		return dao.save(blogUpdate);
	}

	@Override
	public Blog findById(Integer id) {
		return dao.findById(id).get();
	}


}
