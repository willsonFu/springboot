package com.zwy.service;

import java.util.List;

import com.zwy.dataobject.AccountDO;

public interface IAccountService {

	int addAccount(AccountDO accountDO);
	
	int updateAccount(AccountDO accountDO);
	
	int deleteAccount(int id);
	
	AccountDO findAccountById(int id);
	
	List<AccountDO> findAccountList();
}
