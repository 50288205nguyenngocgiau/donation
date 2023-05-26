package com.poly.dax.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name="categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
	@Id
	private int id;

	private String name;

	//bidirectional many-to-one association to CategoryBlog
	@JsonIgnore
	@OneToMany(mappedBy="category")
	private List<CategoryBlog> categoryBlogs;
}