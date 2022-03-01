package com.activity11.controllers;

import java.util.List;
import java.util.Optional;

import com.activity11.dao.AccountDAO;
import com.activity11.models.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/cuentas")
public class AccountController {
    @Autowired
    private AccountDAO accountDAO;

    @GetMapping(value="")
    public ResponseEntity<List<Account>> getAll() {
        try {
            List<Account> accounts = accountDAO.findAll();
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Account> getById(@PathVariable(value="id") String id) {
        try {
            Optional<Account> account = accountDAO.findById(Integer.parseInt(id));
            Account accountData = account.get();
            return new ResponseEntity<>(accountData, HttpStatus.OK);
        } catch (Exception e) {
        	System.out.println("Llegue");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="")
    public ResponseEntity<Account> create(@RequestBody Account account) {
        try {
            Account accountData = accountDAO.save(account);
            return new ResponseEntity<>(accountData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Account> update(@PathVariable(value="id") String id, @RequestBody Account account) {
        try {
            Optional<Account> accountData = accountDAO.findById(Integer.parseInt(id));
            if (accountData.isPresent()) {
                Account accountUpdate = accountData.get();
                accountUpdate.setBalance(account.getBalance());
                accountUpdate.setStatus(account.getStatus());
                accountUpdate.setId_cliente(account.getId_cliente());
                Account accountUpdated = accountDAO.save(accountUpdate);
                return new ResponseEntity<>(accountUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
        	System.out.println("Llegue1");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value="/{id}")
    public ResponseEntity<Account> changeStatus(@PathVariable(value="id") String id) {
        try {
            Optional<Account> accountData = accountDAO.findById(Integer.parseInt(id));
            if (accountData.isPresent()) {
                Account accountUpdate = accountData.get();
                accountUpdate.setStatus(!accountUpdate.getStatus());
                Account accountUpdated = accountDAO.save(accountUpdate);
                return new ResponseEntity<>(accountUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
        	System.out.println("Lleguew");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Account> delete(@PathVariable(value="id") String id) {
        try {
            Optional<Account> accountData = accountDAO.findById(Integer.parseInt(id));
            if (accountData.isPresent()) {
                Account accountDelete = accountData.get();
                accountDAO.delete(accountDelete);
                return new ResponseEntity<>(accountDelete, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
        	System.out.println("Llegu3");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
