package com.zwy.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zwy.dataobject.AccountDO;
import com.zwy.service.IAccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private IAccountService accountService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	List<AccountDO> getAccounts() {
		return accountService.findAccountList();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	AccountDO getAccountById(@PathVariable int id) {
		return accountService.findAccountById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	String update(@PathVariable("id") int id, @RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "money", required = true) double money) {
		AccountDO accountDO = new AccountDO();
		accountDO.setMoney(money);
		accountDO.setName(name);
		accountDO.setId(id);
		int t = accountService.updateAccount(accountDO);
		if (t == 1) {
			return accountDO.toString();
		} else {
			return "fail";
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	String add(@RequestParam(value = "name") String name, @RequestParam(value = "money") double money) {
		AccountDO accountDO = new AccountDO();
		accountDO.setMoney(money);
		accountDO.setName(name);
		int t = accountService.addAccount(accountDO);
		if (t == 1) {
			return accountDO.toString();
		} else {
			return "fail";
		}

	}
}
