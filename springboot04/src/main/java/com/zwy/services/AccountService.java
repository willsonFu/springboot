package com.zwy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwy.entity.Account;
import com.zwy.mappers.AccountMapper;

@Service
public class AccountService {

	@Autowired
	private AccountMapper accountMapper;

	public int add(String name, double money) {
		return accountMapper.addAccount(name, money);
	}

	public int update(String name, double money, int id) {
		return accountMapper.updateAccount(name, money, id);
	}

	public int delete(int id) {
		return accountMapper.deleteAccount(id);
	}

	public Account findAccount(int id) {
		return accountMapper.findAccountById(id);
	}

	public List<Account> findAccountList() {
		return accountMapper.findAccountList();
	}
}
