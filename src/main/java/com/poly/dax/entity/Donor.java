package com.poly.dax.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the donor database table.
 * 
 */
@Entity
@Table(name="donor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Donor implements Serializable {
	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;

	private float donated;

	@Column(name="full_name")
	private String fullName;

	private String phone;

	@ManyToOne
	private Blog blog;
	
	private boolean confirm;
	
	@ManyToOne
	@JoinColumn(name = "account_id", nullable=false)
	private Account account;

	public Donor(Date createDate, float donated, String fullName, String phone, Blog blog, Account account,boolean confirm) {
		super();
		this.createDate = createDate;
		this.donated = donated;
		this.fullName = fullName;
		this.phone = phone;
		this.blog = blog;
		this.account = account;
		this.confirm = confirm;
	}
	
	
}