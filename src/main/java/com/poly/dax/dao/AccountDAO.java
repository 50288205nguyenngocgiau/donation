package com.poly.dax.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.dax.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String>{
	@Query("select a from Account a where a.email=?1 or a.username=?1")
	Account findByEmail(String emai);
}
