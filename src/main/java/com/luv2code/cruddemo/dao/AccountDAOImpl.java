package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO{

    private String name;
    private String serviceCode;

    @Override
    public String getName() {
        System.out.println(getClass() + ": in getName()");
        return name;
    }

    @Override
    public void setName(String name) {
        System.out.println(getClass() + ": in setName()");
        this.name = name;
    }

    @Override
    public String getServiceCode() {
        System.out.println(getClass() + ": in getServiceCode()");
        return serviceCode;
    }

    @Override
    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ": in setServiceCode()");
        this.serviceCode = serviceCode;
    }

    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": doWork()");
        return false;
    }
}
