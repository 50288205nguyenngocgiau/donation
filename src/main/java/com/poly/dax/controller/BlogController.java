package com.poly.dax.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.dax.common.entity.BlogBinding;
import com.poly.dax.entity.Account;
import com.poly.dax.entity.Blog;
import com.poly.dax.entity.Image;
import com.poly.dax.service.AccountService;
import com.poly.dax.service.BlogImageService;
import com.poly.dax.service.BlogService;
import com.poly.dax.service.ImageService;


@Controller
public class BlogController {
	@Autowired
	AccountService accountService;
	@Autowired
	BlogService blogService;
	@Autowired
    private ImageService imageService;
	@Autowired
    private BlogImageService blogImageService;
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping("/admin/blog")
	public String admin(Model m, BlogBinding blogBinding,Principal principal) {
		String username = request.getUserPrincipal().getName();
		if(!username.equals("admin@gmail.com")) {
			return "redirect:/security/unauthoried";
		}
		List<Blog> lsBlog = blogService.findAll();
		System.out.println("username: "+username);
		
		m.addAttribute("lsBlog", lsBlog);
		m.addAttribute("username",username);
		m.addAttribute("imageList", blogImageService.findAllImage());
		m.addAttribute("blogBinding",blogBinding);
		return "blog_create_edit";
	}
	@RequestMapping("/admin/blog/add")
	public String addBlog(Model m, BlogBinding blogBinding,Principal principal) {
		
		if(!(principal.getName().equals("admin@gmail.com"))) {
			return "redirect:/security/unauthoried";
		}
		Account account = accountService.findById(principal.getName());
		Blog blog = new Blog();
		
		blog.setTitle(blogBinding.getTitle());
		blog.setSummary(blogBinding.getSummary());
		blog.setContent(blogBinding.getContent());
		blog.setViewCount(BigInteger.valueOf(0));
		blog.setDonated(0);
		blog.setGoalDonate(blogBinding.getGoalDonate());
		blog.setDonateCount(0);
		blog.setStartDate(blogBinding.getStartDate());
		blog.setEndDate(blogBinding.getEndDate());
		blog.setCreateDate(new Date());
		blog.setIsDisplay(blogBinding.getIsDisplay());
		blog.setAccount(account);
		
		blogService.create(blog);
		
		m.addAttribute("msg","Create Blog Success!!!");
		return "redirect:/admin/blog";
	}
	@RequestMapping("/admin/blog/edit/{id}")
	public String edit(Model m, @PathVariable("id") Integer id, Principal principal) {
		if(!(principal.getName().equals("admin@gmail.com"))) {
			return "redirect:/security/unauthoried";
		}
		Blog blog = blogService.findBlogById(id);
		BlogBinding binding = new BlogBinding();
		
		binding.setId(blog.getId());
		binding.setTitle(blog.getTitle());
		binding.setSummary(blog.getSummary());
		binding.setViewCount(blog.getViewCount());
		binding.setDonated(blog.getDonated());
		binding.setGoalDonate(blog.getGoalDonate());
		binding.setDonateCount(blog.getDonateCount());
		binding.setCreateDate(blog.getCreateDate());
		binding.setStartDate(blog.getCreateDate());
		binding.setEndDate(blog.getEndDate());
		binding.setIsDisplay(blog.getIsDisplay());
		binding.setCreateBy(blog.getAccount().getUsername());
		binding.setContent(blog.getContent());
		
		m.addAttribute("blogBinding",binding);
		
		return "/blog_create_edit";
	}
	@RequestMapping("/admin/blog/update")
	public String update(Model m, 
			@RequestParam("id") Integer id,
			BlogBinding binding,Principal principal) {
		if(!(principal.getName().equals("admin@gmail.com"))) {
			return "redirect:/security/unauthoried";
		}
		Blog blog = blogService.findBlogById(id);
		Blog blogUpdate = new Blog();
		
		blogUpdate.setId(blog.getId());
		blogUpdate.setTitle(binding.getTitle());
		blogUpdate.setSummary(binding.getSummary());
		blogUpdate.setViewCount(blog.getViewCount());
		blogUpdate.setDonated(binding.getDonated());
		blogUpdate.setGoalDonate(binding.getGoalDonate());
		blogUpdate.setDonateCount(binding.getDonateCount());
		blogUpdate.setCreateDate(blog.getCreateDate());
		blogUpdate.setStartDate(binding.getStartDate());
		blogUpdate.setEndDate(binding.getEndDate());
		blogUpdate.setIsDisplay(binding.getIsDisplay());
//		blogUpdate.setCreateBy(blog.getAccount().getUsername());
		blogUpdate.setAccount(blog.getAccount());
		blogUpdate.setContent(binding.getContent());
		
		System.out.println("ID: "+blog);
		
		blogService.update(blogUpdate);
		
		m.addAttribute("msg","update success");
		
		return "redirect:/admin/blog";
	}
	@RequestMapping("/admin/blog/edit/upload")
	public String upload(Model model, @RequestParam("id") Integer id,
            @RequestParam("file") List<MultipartFile> files,
            BlogBinding binding) throws IOException {
		System.out.println("id upload: "+id);
		File dir = new File("src/main/resources/static/img");
		System.out.println("Path: " + dir.getAbsolutePath());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		for(MultipartFile file : files) {
		String s = System.currentTimeMillis() + file.getOriginalFilename();
		String name = "image-" + id + "-" + Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
		
		File destinationFile = new File(dir.getAbsolutePath(), name);
		file.transferTo(destinationFile);
		System.out.println("save");
		Image image = imageService.create(name);
		blogImageService.create(id, image.getId());
		}
		return "redirect:/admin/blog";
	}
}
