package com.poly.dax.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the blog_image database table.
 * 
 */
@Entity
@Table(name="blog_image")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogImage implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//bidirectional many-to-one association to Blog
	@ManyToOne
	private Blog blog;

	//bidirectional many-to-one association to Image
	@ManyToOne
	private Image image;
}