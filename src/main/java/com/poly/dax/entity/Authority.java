package com.poly.dax.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the authority database table.
 * 
 */
@Entity
@Table(name="authorities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//bidirectional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;

	//bidirectional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
}