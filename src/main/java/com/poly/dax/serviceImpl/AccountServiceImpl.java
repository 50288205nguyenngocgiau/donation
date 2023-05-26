package com.poly.dax.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dax.dao.AccountDAO;
import com.poly.dax.entity.Account;
import com.poly.dax.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountDAO dao;

	@Override
	public Account createAccount(Account account) {
		return dao.save(account);
	}

	@Override
	public List<Account> findEmail() {
		return dao.findAll();
	}
	
	@Override
	public Account findById(String username) {
		return dao.findById(username).get();
	}

//	@Override
//	public List<Account> getAdministrators() {
//		return dao.getAdministrators();
//	}

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}

	@Override
	public Account findByEmail(String email) {
		return dao.findByEmail(email);
	}

}
