package com.bankmgmt.app.service;

import com.bankmgmt.app.entity.Account;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class BankService {

    private List<Account> accounts = new ArrayList<>();
    private Integer currentId = 1;

    // TODO: Method to Create a new Account
    public Account createAccount( String accountHolderName, String accountType, String email)
    {
        try{
            accounts.add(new Account(currentId,accountHolderName,accountType,0.0,email));
            this.currentId++;
        }
        catch (Exception e)
        {
            return null;
        }

        return accounts.get(accounts.size()-1);
    }

    // TODO: Method to Get All Accounts
    public List<Account> getAccounts()
    {
        return accounts;
    }

    // TODO: Method to Get Account by ID
    public Account getAccount(int id)
    {
        return accounts.stream().filter(acc->acc.getId()==id).toList().get(0);
    }

    // TODO: Method to Deposit Money to the specified account id
    public Account depositMoney( int id, int amount)
    {
        if(amount<0 || id>accounts.size())return null;

        Account temp = accounts.get(id-1);
        temp.setBalance(temp.getBalance()+amount);
        return accounts.get(id-1);
    }

    // TODO: Method to Withdraw Money from the specified account id
    public Account withdrawMoney( int id, int amount)
    {
        if(amount<0 || id>accounts.size())return null;

        Account temp = accounts.get(id-1);
        if(temp.getBalance()<amount){
            return null;
        }
        else {
            temp.setBalance(temp.getBalance() - amount);
        }
        return accounts.get(id-1);
    }

    // TODO: Method to Transfer Money from one account to another
    public List<Account> transferMoney( int fromId, int toId, int amount)
    {
        if(fromId>accounts.size() || toId>accounts.size() || amount<0)return null;

        Account fromTemp = accounts.get(fromId-1);
        Account toTemp = accounts.get(toId-1);

        if(fromTemp.getBalance()<amount){
            return null;
        }
        else {
            fromTemp.setBalance(fromTemp.getBalance() - amount);
            toTemp.setBalance(toTemp.getBalance() + amount);
        }

        List<Account> response = new ArrayList<>();
        response.add(accounts.get(fromId-1));
        response.add(accounts.get(toId-1));
        return response;
    }
    
    // TODO: Method to Delete Account given a account id
    public boolean deleteAccount(int id)
    {
        if(id>accounts.size())return false;

        accounts.remove(id-1);
        this.currentId--;

        return true;
    }


}
