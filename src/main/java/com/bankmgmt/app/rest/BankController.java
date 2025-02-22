package com.bankmgmt.app.rest;

import com.bankmgmt.app.entity.Account;
import com.bankmgmt.app.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// TODO: Make this class a Rest Controller
@RestController
public class BankController {

    // TODO Autowired the BankService class
    @Autowired
    private BankService bankService;

    // TODO: API to Create a new account
    @PostMapping("/accounts")
    @ResponseBody
    public ResponseEntity<Account> newAccount(@RequestBody Account acc)
    {
        Account res = bankService.createAccount(acc.getAccountHolderName(),acc.getAccountType(),acc.getEmail());
//        if(res==null)
//        {
//            return new ResponseEntity<>(,200)
//        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // TODO: API to Get all accounts
    @GetMapping("/accounts")
    @ResponseBody
    public ResponseEntity<List<Account>> getAccounts()
    {
        List<Account> res = bankService.getAccounts();
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    // TODO: API to Get an account by ID
    @GetMapping("/accounts/{id}")
    @ResponseBody
    public ResponseEntity<Account> getAccounts(@PathVariable int id)
    {
        Account res = bankService.getAccount(id);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }


    // TODO: API to Deposit money
    @PostMapping("/accounts/{id}/deposit")
    @ResponseBody
    public ResponseEntity<Account> depositMoney(@PathVariable int id, @RequestBody Map<String, Integer> map)
    {
        Account res = bankService.depositMoney(id,map.get("amount"));
        if(res  == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // TODO: API to Withdraw money
    @PostMapping("/accounts/{id}/withdraw")
    @ResponseBody
    public ResponseEntity<Account> withdrawMoney(@PathVariable int id,@RequestBody Map<String, Integer> map)
    {
        Account res = bankService.withdrawMoney(id,map.get("amount"));

        if(res  == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // TODO: API to Transfer money between accounts
    @PostMapping("/accounts/transfer")
    @ResponseBody
    public ResponseEntity<List<Account>> transferMoney(@RequestParam int fromId,@RequestParam int toId,@RequestParam int amount)
    {
        List<Account> res = bankService.transferMoney(fromId,toId,amount);

        if(res  == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // TODO: API to Delete an account
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable int id)
    {
        boolean res = bankService.deleteAccount(id);
        if(!res)
        {
            return new ResponseEntity<>("Account not found!!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Account deleted successfully!!", HttpStatus.NO_CONTENT);


    }
    
}
