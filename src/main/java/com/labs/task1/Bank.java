package com.labs.task1;

/**
 * Created by ameks on 22.10.2016.
 */
public class Bank {
    public Boolean transfer(Account from, Account to,  int amount){
//        synchronized (this) {
            if (from.withdraw(amount)) {
                to.deposit(amount);
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
//        }
        
    }

}
