package com.labs;

import com.labs.task1.Account;
import com.labs.task1.Bank;

import java.util.List;

import static com.labs.TestConfigurer.initialAmount;
import static com.labs.TestConfigurer.random;

public class TestHelper {

//    public static final Bank bank = new Bank();
    
    public static void makeOperations(TestConfigurer.TransferMethod transferMethod, int operationCount, List<Account> accountList) {
        for (int i = 0; i < operationCount; i++) {
            Account from = accountList.get(random.nextInt(accountList.size()));
            Account to = accountList.get(random.nextInt(accountList.size()));
            int amountToTransfer = random.nextInt(initialAmount);
            transferMethod.transfer(from, to, amountToTransfer);
        }
    }
    
}
