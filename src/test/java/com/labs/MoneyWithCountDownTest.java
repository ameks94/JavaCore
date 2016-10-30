package com.labs;

import com.labs.task1.Account;
import com.labs.task1.Bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import org.junit.Test;

import static com.labs.TestConfigurer.*;
import static com.labs.TestHelper.makeOperations;
import static org.junit.Assert.assertEquals;

/**
 * Created by ameks on 23.10.2016.
 */
public class MoneyWithCountDownTest {
    private final Bank bank = new Bank();
    private final List<Account> accountList = new ArrayList<>();

    public MoneyWithCountDownTest(){
        for (int i = 0; i < accountNumber; i++) {
            Account account = new Account(initialAmount);
            accountList.add(account);
        }
    }

    @Test
    public void testMoneyTransferSynchronizeBank() throws BrokenBarrierException, InterruptedException {
        final CountDownLatch gate = new CountDownLatch(1);

        ExecutorService executorService = Executors.newFixedThreadPool(threadCountForTest, Executors.defaultThreadFactory());    

        for (int i = 0; i < threadCountForTest; i++) {
            executorService.submit(new WorkerThread(gate, bank, accountList));
        }
        gate.countDown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals(totalAmount, countTotalAmount(accountList));
    }
    
    private int countTotalAmount(List<Account> accountList) {
        return accountList
                .stream()
                .mapToInt(item -> item.getAmount())
                .sum();
    }

    private static class WorkerThread extends Thread{
        private CountDownLatch gate;
        private Bank bank;
        private final List<Account> accountList;


        public WorkerThread(CountDownLatch gate, Bank bank, List<Account> accountList) {
            this.gate = gate;
            this.bank = bank;
            this.accountList = accountList;
        }

        @Override
        public void run() {
            try {
                gate.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            makeOperations(operationCountPerThread, bank, accountList);
        }
    }
}
