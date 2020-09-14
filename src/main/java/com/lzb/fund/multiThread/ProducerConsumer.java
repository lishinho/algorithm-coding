package com.lzb.fund.multiThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerConsumer {

    // this is a demo handling with Producer-consumer problem based on blockingqueue
    // ArrayBlockingQueue is one of implements that based on array (the constructor can be fixed on capacity)
    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

    // AtomicInteger makes the No.product atomic based on CompareAndSwap
    private static AtomicInteger num = new AtomicInteger(1);

    private static class Producer extends Thread {
        @Override
        public void run() {
            String product = "Product-" + num.getAndIncrement();
            try {
                queue.put(product);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // note this can be delayed after printing consumer because it is not atomic
            System.out.println("Producing " + product);
        }
    }

    private static class Consumer extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("consuming " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            Producer producer = new Producer();
            producer.start();
        }
        // consumer will wait for the producer thread
        for (int i = 0; i < 5; i++) {
            Consumer consumer = new Consumer();
            consumer.start();
        }
        // producer put product into blockingQueue and consumer thread would run concurrently
        for (int i = 0; i < 3; i++) {
            Producer producer = new Producer();
            producer.start();
        }
    }
}
