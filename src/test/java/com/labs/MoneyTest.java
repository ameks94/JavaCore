package com.labs;

import com.labs.task1.Account;
import com.labs.task1.Bank;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;

public class MoneyTest {
    private final Bank bank = new Bank();
    private final List<Account> accountList = new ArrayList<>();
    private final int totalAmount;
    private final int accountNumber = 3;
    private final int threadCountForTest = 10_000;
    private final int initialAmount = 1000_000;
    private final Random random = new Random();
    
    public MoneyTest(){
        int sum = 0;
        for (int i = 0; i < accountNumber; i++) {
            int amount = initialAmount;
            Account account = new Account(amount);
            accountList.add(account);
            sum += amount;
        }
        totalAmount = sum;
    }

    @Test
    public void testMoney() throws BrokenBarrierException, InterruptedException {
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
            Account from = accountList.get(random.nextInt(accountNumber));
            Account to = accountList.get(random.nextInt(accountNumber));
            operations.add(() -> bank.transfer(from, to, random.nextInt(initialAmount)));
        }
        return operations;
    }
    
//    @Test
//    public void testMoney() throws BrokenBarrierException, InterruptedException {
//        final CountDownLatch gate = new CountDownLatch(threadCountForTest + 1);
//        
//        ExecutorService executorService = Executors.newFixedThreadPool(threadCountForTest, Executors.defaultThreadFactory());    
//        
//        for (int i = 0; i < threadCountForTest; i++) {
//            Account from = accountList.get(new Random().nextInt(accountNumber));
//            Account to = accountList.get(new Random().nextInt(accountNumber));
//            Thread workerThread = new Thread(() -> {
//                try {
//                    gate.await();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                bank.transfer(from, to, initialAmount / 1000);
//            });
//            executorService.execute(workerThread);
//        }
//        
//        gate.await();
//
//        executorService.shutdown();
//        
//        
//    }
    
//    private void Thread createWorkerThread(CountDownLatch gate) {
//       
//    }
    
//    private static class WorkerThread extends Thread{
//        private CyclicBarrier gate;
//        private final Bank bank;
//        private final Account from;
//        private final Account to;
//        private final int amount;
//
//
//        public WorkerThread(CyclicBarrier gate, Bank bank, Account from, Account to, int amount) {
//            this.bank = bank;
//            this.from = from;
//            this.to = to;
//            this.amount = amount;
//        }
//
//        @Override
//        public void run() {
//            try {
//                gate.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (BrokenBarrierException e) {
//                e.printStackTrace();
//            }
//            bank.transfer(from, to, amount);
//        }
//    }
}
