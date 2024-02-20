// bankservice.java
package com.sai.server;

import java.util.List;

import com.sai.model.bankapplication;

public interface bankservice {

    public bankapplication banksave(bankapplication Bankapplication);

    public bankapplication findById(Integer id);

    public bankapplication balance(long account, String name, String password);

    public bankapplication withdraw(long account, String name, String password, double amount);

    public bankapplication deposit(long account, String name, String password, double amount);

    public bankapplication transfer(long account, String name, String password, int targetAccount, double amount);
}
