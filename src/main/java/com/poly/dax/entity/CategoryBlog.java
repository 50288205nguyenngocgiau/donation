package com.poly.dax.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the category_blog database table.
 * 
 */
@Entity
@Table(name="category_blog")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryBlog implements Serializable {
	@Id
	private int id;

	//bidirectional many-to-one association to Blog
	@ManyToOne
	private Blog blog;

	//bidirectional many-to-one association to Category
	@ManyToOne
	private Category category;
}