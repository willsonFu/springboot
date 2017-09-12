package com.zwy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zwy.dao.IAccountDAO;
import com.zwy.dataobject.AccountDO;

@Repository
public class AccountDAOImpl implements IAccountDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int add(AccountDO accountDO) {
		return jdbcTemplate.update("insert into account(name,money)values(?,?)", accountDO.getName(),
				accountDO.getMoney());
	}

	@Override
	public int update(AccountDO accountDO) {
		return jdbcTemplate.update("update account set name=?,money=? where id=?", accountDO.getName(),
				accountDO.getMoney(), accountDO.getId());
	}

	@Override
	public int delete(int id) {
		return jdbcTemplate.update("delete from account where id=?", id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public AccountDO findById(int id) {
		List<AccountDO> accountList = jdbcTemplate.query("select id,name,money from account where id=?", new Object[]{id}, new BeanPropertyRowMapper(AccountDO.class));
		if(accountList != null && accountList.size() > 0)
			return accountList.get(0);
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<AccountDO> findAccountList() {
		List<AccountDO> accountList = jdbcTemplate.query("select id,name,money from account", new Object[]{}, new BeanPropertyRowMapper(AccountDO.class));
		return accountList;
	}

}
