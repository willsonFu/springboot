package com.zwy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwy.dao.IAccountDAO;
import com.zwy.dataobject.AccountDO;
import com.zwy.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {
	
	@Autowired
	private IAccountDAO accountDAO;

	@Override
	public int addAccount(AccountDO accountDO) {
		return this.accountDAO.add(accountDO);
	}

	@Override
	public int updateAccount(AccountDO accountDO) {
		return this.accountDAO.update(accountDO);
	}

	@Override
	public int deleteAccount(int id) {
		return this.accountDAO.delete(id);
	}

	@Override
	public AccountDO findAccountById(int id) {
		return this.accountDAO.findById(id);
	}

	@Override
	public List<AccountDO> findAccountList() {
		return this.accountDAO.findAccountList();
	}

}
