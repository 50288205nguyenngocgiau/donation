package com.poly.dax.common.entity;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class BlogList {
    @Id
    private Integer id;
    private String title;
    private Float donated;
    private Float goalDonate;
    private Float donateCount;
    private BigInteger leftDate;
    private String username;
    private String userAvatar;
    private String blogImage;
    private Double percentDonate;
}
