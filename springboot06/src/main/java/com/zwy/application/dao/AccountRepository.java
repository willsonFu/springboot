package com.zwy.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zwy.application.domain.Account;

public interface AccountRepository extends JpaRepository<Account,Integer> {

}
