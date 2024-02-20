// mycontroller.java
package com.sai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sai.model.bankapplication;
import com.sai.server.bankservice;

@RestController
@RequestMapping("/bank")
public class mycontroller {

    @Autowired
    private bankservice bank;

    @PostMapping("/save")
    public ResponseEntity<String> banksave(@RequestBody bankapplication Bankapplication) {
        bankapplication sa = bank.banksave(Bankapplication);
        if (sa != null) {
            return ResponseEntity.ok("Data saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data not saved");
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<bankapplication> getById(@PathVariable Integer id) {
        bankapplication bankApplication = bank.findById(id);
        if (bankApplication != null) {
            return ResponseEntity.ok(bankApplication);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<String> balance(@RequestParam long account, @RequestParam String name, @RequestParam String password) {
        bankapplication authenticatedUser = bank.balance(account, name, password);
        if (authenticatedUser != null) {
            return ResponseEntity.ok("User authenticated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    @GetMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam long account, @RequestParam String name, @RequestParam String password, @RequestParam double amount) {
        bankapplication result = bank.withdraw(account, name, password, amount);
        if (result != null) {
            return ResponseEntity.ok("Withdrawal successful. New balance: " + result.getAmount());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Withdrawal failed");
        }
    }

    @GetMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam long account, @RequestParam String name, @RequestParam String password, @RequestParam double amount) {
        bankapplication result = bank.deposit(account, name, password, amount);
        if (result != null) {
            return ResponseEntity.ok("Deposit successful. New balance: " + result.getAmount());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deposit failed");
        }
    }

    @GetMapping("/transfer")
    public ResponseEntity<String> transferAmount(@RequestParam long account, @RequestParam String name, @RequestParam String password,
                                                  @RequestParam int targetAccount, @RequestParam double amount) {
        bankapplication result = bank.transfer(account, name, password, targetAccount, amount);
        if (result != null) {
            return ResponseEntity.ok("Transfer successful. New balance: " + result.getAmount());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transfer failed");
        }
    }
}
