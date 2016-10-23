package com.labs;

import com.labs.task1.Account;
import com.labs.task1.Bank;

import java.util.List;

import static com.labs.TestConfigurer.initialAmount;
import static com.labs.TestConfigurer.operationCountPerThread;
import static com.labs.TestConfigurer.random;

/**
 * Created by ameks on 23.10.2016.
 */
public class TestHelper {
    
    public static void makeOperations(int operationCount, Bank bank, List<Account> accountList) {
        for (int i = 0; i < operationCount; i++) {
            Account from = accountList.get(random.nextInt(accountList.size()));
            Account to = accountList.get(random.nextInt(accountList.size()));
            int amountToTransfer = random.nextInt(initialAmount);
            bank.transfer(from, to, amountToTransfer);
        }
    }
}
