package com.poly.dax.common.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogBinding {
	private Integer id;
	private String title;
	
	private String summary;
	
	private String content;
	
	private BigInteger viewCount;
	
	private float donated;
	
	private float goalDonate;
	
	private float donateCount;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="MM-dd-yyyy")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM-dd-yyyy")
	private Date endDate;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM-dd-yyyy")
	private Date createDate;
	
	private Boolean isDisplay;
	
	private String createBy;
}
