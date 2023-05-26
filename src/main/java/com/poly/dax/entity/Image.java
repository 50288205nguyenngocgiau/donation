package com.poly.dax.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the image database table.
 * 
 */
@Entity
@Table(name="images")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	//bidirectional many-to-one association to BlogImage
	@JsonIgnore
	@OneToMany(mappedBy="image")
	private List<BlogImage> blogImages;
}