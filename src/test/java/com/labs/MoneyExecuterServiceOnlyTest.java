package com.labs;

import com.labs.task1.Account;
import com.labs.task1.Bank;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static com.labs.TestConfigurer.*;
import static com.labs.TestHelper.makeOperations;
import static org.junit.Assert.assertEquals;

public class MoneyExecuterServiceOnlyTest {
    
    private final List<Account> accountList = new ArrayList<>();
    
    public MoneyExecuterServiceOnlyTest(){
        for (int i = 0; i < accountNumber; i++) {
            Account account = new Account(initialAmount);
            accountList.add(account);
        }
    }

    @Test
    public void testMoneyTransferSynchronizeBank() throws BrokenBarrierException, InterruptedException {
        testMoney(Bank::transferBankSynchronization);
    }

    @Test
    public void testMoneyTransferSynchronizeAccounts() throws BrokenBarrierException, InterruptedException {
        testMoney(Bank::transferAccountSynchronization);
    }

    public void testMoney(TestConfigurer.TransferMethod transferMethod) throws BrokenBarrierException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCountForTest, Executors.defaultThreadFactory());
        executorService.<Boolean>invokeAll(getWorkerList(transferMethod, threadCountForTest));


        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
        }
        
        assertEquals(totalAmount, countTotalAmount(accountList));
    }

    private int countTotalAmount(List<Account> accountList) {
        return accountList
                .stream()
                .mapToInt(item -> item.getAmount())
                .sum();
    }
    
    private List<Callable<Boolean>> getWorkerList(TestConfigurer.TransferMethod transferMethod, int operationNumber) {
        List<Callable<Boolean>> operations = new ArrayList<>();
        for (int i = 0; i < operationNumber; i++) {
            operations.add(() -> {
                makeOperations(transferMethod, operationCountPerThread, accountList); return null;
            });
        }
        return operations;
    }
    
}
