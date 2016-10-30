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

public class MoneyTest {
    private final Bank bank = new Bank();
    private final List<Account> accountList = new ArrayList<>();
    
    public MoneyTest(){
        for (int i = 0; i < accountNumber; i++) {
            Account account = new Account(initialAmount);
            accountList.add(account);
        }
    }

    @Test
    public void testMoneySynchronizeBank() throws BrokenBarrierException, InterruptedException {
        
        ExecutorService executorService = Executors.newFixedThreadPool(threadCountForTest, Executors.defaultThreadFactory());

        executorService.<Boolean>invokeAll(getWorkerList(threadCountForTest));

        executorService.awaitTermination(5, TimeUnit.SECONDS);
        
        assertEquals(totalAmount, countTotalAmount(accountList));
    }

    private int countTotalAmount(List<Account> accountList) {
        return accountList
                .stream()
                .mapToInt(item -> item.getAmount())
                .sum();
    }
    
    private List<Callable<Boolean>> getWorkerList(int operationNumber) {
        List<Callable<Boolean>> operations = new ArrayList<>();
        for (int i = 0; i < operationNumber; i++) {
            operations.add(() -> {
                makeOperations(operationCountPerThread, bank, accountList); return null;
            });
        }
        return operations;
    }
    
}
