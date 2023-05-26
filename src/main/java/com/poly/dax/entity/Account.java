package com.poly.dax.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="accounts")
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class Account implements Serializable {
    @Id
    private String email;

    private String avatar;

    private String background;

    @Temporal(TemporalType.DATE)
    @Column(name="create_date")
    private Date createDate;

    private String firstname;

    @Column(name="full_name")
    private String fullName;

    private String lastname;

    private String password;

    private String phone;

    private String username;

    //bidirectional many-to-one association to Authority
    @JsonIgnore
    @OneToMany(mappedBy="account", fetch = FetchType.EAGER)
    private List<Authority> authorities;

    //bidirectional many-to-one association to Blog
    @JsonIgnore
    @OneToMany(mappedBy="account")
    private List<Blog> blogs;
    
    @JsonIgnore
    @OneToMany(mappedBy="account")
    private List<Donor> donors;

}
