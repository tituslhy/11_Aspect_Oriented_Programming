package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.Account;

public interface AccountDAO {
    void addAccount(Account theAccount, boolean vipFlag);
}
