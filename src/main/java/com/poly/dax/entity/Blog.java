package com.poly.dax.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the blog database table.
 * 
 */
@Entity
@Table(name="blogs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog implements Serializable {
	@Id
	private int id;

	private String content;

	private String summary;

	@Temporal(TemporalType.DATE)
	@Column(name="create_date")
	private Date createDate;

	@Column(name="donate_count")
	private float donateCount;

	private float donated;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="goal_donate")
	private float goalDonate;

	@Column(name="is_display")
	private Boolean isDisplay;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	private String title;

	@Column(name="view_count")
	private BigInteger viewCount;

	//bidirectional many-to-one association to BlogImage
	@JsonIgnore
	@OneToMany(mappedBy="blog")
	private List<BlogImage> blogImages;

	//bidirectional many-to-one association to Account
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="account_id")
	private Account account;

	//bidirectional many-to-one association to CategoryBlog
	@JsonIgnore
	@OneToMany(mappedBy="blog")
	private List<CategoryBlog> categoryBlogs;
}