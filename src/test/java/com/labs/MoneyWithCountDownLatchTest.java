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
public class MoneyWithCountDownLatchTest {
    private final List<Account> accountList = new ArrayList<>();

    public MoneyWithCountDownLatchTest(){
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
    
    private void testMoney(TestConfigurer.TransferMethod transferMethod) throws InterruptedException {
        final CountDownLatch gate = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(threadCountForTest, Executors.defaultThreadFactory());
        for (int i = 0; i < threadCountForTest; i++) {
            executorService.submit(new WorkerThread(gate, accountList, transferMethod));
        }
        gate.countDown();

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

    private static class WorkerThread extends Thread{
        private CountDownLatch gate;
        private TestConfigurer.TransferMethod transferMethod;
        private final List<Account> accountList;


        public WorkerThread( CountDownLatch gate, List<Account> accountList, TestConfigurer.TransferMethod transferMethod) {
            this.transferMethod = transferMethod;
            this.gate = gate;
            this.accountList = accountList;
        }

        @Override
        public void run() {
            try {
                gate.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            makeOperations(transferMethod, operationCountPerThread, accountList);
        }
    }
}
