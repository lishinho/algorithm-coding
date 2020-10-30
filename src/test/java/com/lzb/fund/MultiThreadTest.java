package com.lzb.fund;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang.RandomStringUtils;

public class MultiThreadTest {
    private static final String PREFIX = RandomStringUtils.randomAlphabetic(5) + "ConcurrentTest-";
//    private AtomicInteger num = new AtomicInteger(0);
    private static int num = 0;

    @Test
    public void multiThreadTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        ExecutorService threadPool = Executors.newCachedThreadPool(new DaemonThreadFactory(PREFIX + "thread"));
        for (int i = 0; i < 10; i++) {
            threadPool.submit(new DemoTask(latch));
        }
        latch.await(30000, TimeUnit.MILLISECONDS);
        System.out.println(latch.getCount());
        Assert.assertEquals(40, num);
    }

    class DaemonThreadFactory implements ThreadFactory {

        private AtomicInteger num = new AtomicInteger(0);
        private String threadName;

        public DaemonThreadFactory(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            if (threadName == null || threadName.length() == 0) {
                thread.setName(threadName + "-" + num.getAndIncrement());
            }
            thread.setDaemon(true);
            return thread;
        }
    }

    class DemoTask implements Runnable {
        private CountDownLatch latch;

        public DemoTask(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                // transaction here
                // can verify something
                System.out.println(Thread.currentThread().getName() + "-->" + num++);
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            latch.countDown();
        }
    }
}
