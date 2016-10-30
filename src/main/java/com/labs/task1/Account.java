package com.labs.task1;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ameks on 22.10.2016.
 */
public class Account {
    
    private final int accountNumber = generateNextAccountNumber();
    
    private volatile int amount;

    public Account(int amount) {
        this.amount = amount;
    }

    public Boolean withdraw(int amount) {
        if (amount > this.amount)
            return false;
        this.amount -= amount;
        return true;
    }
    
    public void deposit(int amount) {
        this.amount += amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return accountNumber == account.accountNumber;

    }

    @Override
    public int hashCode() {
        return accountNumber;
    }

    //---------Id generation---------
    private static final AtomicInteger lastGeneratedId = new AtomicInteger(0);

    private static int generateNextAccountNumber() {
        return lastGeneratedId.getAndIncrement();
    }
    //--------------------------------
}
