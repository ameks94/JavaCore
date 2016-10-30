package com.labs;

import com.labs.task1.Account;
import com.labs.task1.Bank;

import java.util.Random;

/**
 * Created by ameks on 23.10.2016.
 */
public class TestConfigurer {
    public static final int accountNumber = 100;
    public static final int initialAmount = 1000_000;
    public static final int totalAmount = accountNumber * initialAmount;
    public static final int threadCountForTest = 1_000;
    public static final int operationCountPerThread = 10;
    public static final Random random = new Random();

    @FunctionalInterface
    public interface TransferMethod {
        Boolean transfer(Account from, Account to, int amountToTransfer);
    }
}
