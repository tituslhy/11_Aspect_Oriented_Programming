package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {

        //simulate an exception
        if (tripWire){
            throw new RuntimeException("No soup for you!!!");
        }

        List<Account> myAccounts = new ArrayList<>();

        Account account1 = new Account("John", "Silver");
        Account account2 = new Account("Madhu", "Platinum");
        Account account3 = new Account("Luca", "Gold");

        myAccounts.add(account1);
        myAccounts.add(account2);
        myAccounts.add(account3);

        return myAccounts;
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
