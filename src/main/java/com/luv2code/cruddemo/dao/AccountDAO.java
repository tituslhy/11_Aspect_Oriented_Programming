package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.Account;

import java.util.List;

public interface AccountDAO {

    void addAccount(Account theAccount, boolean vipFlag);

    boolean doWork();

    String getName();

    String getServiceCode();

    void setName(String name);

    void setServiceCode(String serviceCode);

    List<Account> findAccounts();
}
