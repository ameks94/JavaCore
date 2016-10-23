package com.labs.task1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ameks on 22.10.2016.
 */
public class Account {
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

}
