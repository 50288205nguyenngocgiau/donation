package com.poly.dax.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.dax.common.entity.BlogImageList;
import com.poly.dax.entity.BlogImage;

public interface BlogImageDAO extends JpaRepository<BlogImage, Integer> {
    @Query("select new BlogImageList(i.id, i.name, bi.blog.id) from BlogImage bi left join Image i on i.id = bi.image.id")
    List<BlogImageList> getListImage();
}
