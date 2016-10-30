package com.labs.task1;

/**
 * Created by ameks on 22.10.2016.
 */
public class Bank {
    public static Boolean transferBankSynchronization(Account from, Account to,  int amount){
        synchronized (Bank.class) {
            if (from.withdraw(amount)) {
                to.deposit(amount);
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
    }
    
    public static Boolean transferAccountSynchronization(Account from, Account to,  int amount){
        Account firstLock, secondLock;
        if ( from.getAccountNumber() == to.getAccountNumber() )
            return false;
        
        if (from.getAccountNumber() < to.getAccountNumber()) {
            firstLock = from;
            secondLock = to;
        } else {
            firstLock = to;
            secondLock = from;
        }
        synchronized (firstLock) {
            synchronized (secondLock) {
                if ( from.withdraw(amount) ) {
                    to.deposit(amount);
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }
    

}
