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
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
	@Id
	private String id;

	private String name;

	//bidirectional many-to-one association to Authority
	@JsonIgnore
	@OneToMany(mappedBy="role")
	private List<Authority> authorities;
}