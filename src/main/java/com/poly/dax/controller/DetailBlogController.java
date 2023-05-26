package com.poly.dax.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.dax.entity.Blog;
import com.poly.dax.service.BlogImageService;
import com.poly.dax.service.BlogService;
import com.poly.dax.service.DonorService;

@Controller
@RequestMapping("/blog")
public class DetailBlogController {
	@Autowired
	BlogService blogService;
	@Autowired
	BlogImageService blogImageService;
	@Autowired
	DonorService donorService;
	
	@GetMapping("/detail/{id}")
	public String getBlogById(@PathVariable("id") Integer id, Model m) {
		Blog blog = blogService.findBlogById(id);
		m.addAttribute("imageList", blogImageService.findAllImage());
		m.addAttribute("item",blog);
		m.addAttribute("percentFinish", calculatePercentage(BigDecimal.valueOf(blog.getDonated()), BigDecimal.valueOf(blog.getGoalDonate())));
		m.addAttribute("daysBetween", getDayBetween(blog.getStartDate(), blog.getEndDate()));
		m.addAttribute("blogList",donorService.findAllByBlogId(id));
		
		return "blog_detail";
	}
	private BigDecimal calculatePercentage(BigDecimal a, BigDecimal b) {
        return a.divide(b, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
    }
	private Long getDayBetween(Date start, Date end){
        java.sql.Date sqlStartDate = (java.sql.Date) start;
        java.sql.Date sqlEndDate = (java.sql.Date) end;
        LocalDate startDate = sqlStartDate.toLocalDate();
        LocalDate endDate = sqlEndDate.toLocalDate();
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
	
}
