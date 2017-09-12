package com.zwy.dao;

import java.util.List;

import com.zwy.dataobject.AccountDO;

public interface IAccountDAO {

	int add(AccountDO accountDO);
	
	int update(AccountDO accountDO);
	
	int delete(int id);
	
	AccountDO findById(int id);
	
	List<AccountDO> findAccountList();
}
