package com.labs.task1;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ameks on 22.10.2016.
 */
public class Account {
    
    private final int accountNumber = generateNextAccountNumber();
    
    private int amount;

    public Account(int amount) {
        this.amount = amount;
    }

    public Boolean withdraw(int amount) {
        if (amount > this.amount)
            return false;
        this.amount -= amount;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public void deposit(int amount) {
        this.amount += amount;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getAmount() {
        return amount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    //---------Id generation---------
    private static final AtomicInteger lastGeneratedId = new AtomicInteger(0);

    private static int generateNextAccountNumber() {
        return lastGeneratedId.getAndIncrement();
    }
    //--------------------------------
}
