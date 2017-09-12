package com.zwy.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zwy.entity.Account;

public interface AccountMapper {

	int addAccount(@Param("name") String name, @Param("money") double money);

	int updateAccount(@Param("name") String name, @Param("money") double money, @Param("id") int id);

	int deleteAccount(@Param("id") int id);

	Account findAccountById(@Param("id") int id);

	List<Account> findAccountList();
}
