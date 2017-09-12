package com.zwy.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwy.application.dao.AccountRepository;
import com.zwy.application.domain.Account;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	public List<Account> findAllAccount() {
		return accountRepository.findAll();
	}
	
	public Account getAccountById(int id) {
		return accountRepository.findOne(id);
	}
	
	public Account updateAccount(Account account) {
		return accountRepository.saveAndFlush(account);
	}
	
	public Account saveAccount(Account account) {
		return accountRepository.save(account);
	}
}
