package com.zwy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zwy.entity.Account;
import com.zwy.services.AccountService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;

	@ApiOperation(value="获取账户列表", notes="获取所有账户信息")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Account> getAccounts() {
		return accountService.findAccountList();
	}

	@ApiOperation(value="获取账户信息", notes="根据账户ID获取账户信息")
	@ApiImplicitParam(name="id", required=true, value="账户ID", paramType="path", dataType="int")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Account getAccountById(@PathVariable int id) {
		return accountService.findAccount(id);
	}

	@ApiOperation(value="修改账户信息", notes="")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", required=true, value="账户ID", dataType="int", paramType="path"),
		@ApiImplicitParam(name="name", required=true, value="账户名称", dataType="String", paramType="form"),
		@ApiImplicitParam(name = "money", required = true, value = "账户余额", dataType="Double", paramType="form")
	})
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateAccount(@PathVariable int id, @RequestParam String name, @RequestParam double money) {
		int t = accountService.update(name, money, id);
		if (t == 1) {
			return "success";
		} else {
			return "fail";
		}

	}

	@ApiOperation(value="删除账户信息", notes="")
	@ApiImplicitParam(name="id", required=true, value="账户ID", dataType="int", paramType="path")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable int id) {
		int t = accountService.delete(id);
		if (t == 1) {
			return "success";
		} else {
			return "fail";
		}

	}

	@ApiOperation(value="添加新账户", notes="")
	@ApiImplicitParams({
		@ApiImplicitParam(name="name", required=true, value="账户名称", dataType="String", paramType="form"),
		@ApiImplicitParam(name = "money", required = true, value = "账户余额", dataType="Double", paramType="form")
	})
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postAccount(@RequestParam String name, @RequestParam double money) {

		int t = accountService.add(name, money);
		if (t > 0) {
			return "success";
		} else {
			return "fail";
		}

	}
}
