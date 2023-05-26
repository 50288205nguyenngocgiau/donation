package com.poly.dax.service;

import java.util.List;

import com.poly.dax.entity.Account;

public interface AccountService {
	public Account createAccount(Account account);
	
	public List<Account> findEmail();
	
	public Account findById(String username);

//	List<Account> getAdministrators();

	List<Account> findAll();
	
	Account findByEmail(String email);
}
