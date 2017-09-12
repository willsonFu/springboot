package com.zwy.application.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zwy.application.domain.Account;
import com.zwy.application.services.AccountService;

@RestController
@RequestMapping(value="/account")
public class AccountController {

	private static final Logger logger = Logger.getLogger(AccountController.class);
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Account> getAccountList() {
		List<Account> accountList =  accountService.findAllAccount();
		logger.debug(accountList);
		return accountList;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Account getAccountById(@PathVariable int id) {
		System.out.println(id);
		return accountService.getAccountById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Account update(@PathVariable("id") int id, @ModelAttribute Account account) {
		account.setId(id);
        return accountService.updateAccount(account);
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public Account postAccount(@ModelAttribute Account account) {
        return accountService.saveAccount(account);
    }
}
