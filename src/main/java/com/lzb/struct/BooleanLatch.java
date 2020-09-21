package com.lzb.struct;

import java.time.LocalDateTime;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

// BooleanLatch is a simplified version of countDownLatch
// which uses AQS structure as the synchronized collection
public class BooleanLatch {

    private final Sync sync = new Sync();

    public boolean isSignalled() {
        return sync.isSignalled();
    }

    // release lock
    public void signal() {
        sync.releaseShared(1);
    }

    // waiting for acquiring lock
    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    // Sync is the basic tool which extends AbstractQueuedSynchronizer
    private static class Sync extends AbstractQueuedSynchronizer {

        boolean isSignalled() {
            return getState() == 0;
        }

        @Override
        protected int tryAcquireShared(int i) {
            return isSignalled() ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int i) {
            setState(1);
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BooleanLatch latch = new BooleanLatch();
        new Thread(() -> {
            try {
                // thread executes
                Thread.sleep(3000);
                // thread works done
            } catch (InterruptedException e) {
                // ignore
            }
            // just like countDownLatch's countDown() method
            // it notices the thread works done!
            latch.signal();
        }).start();

        System.out.println(String.format("[%s]-主线程进入阻塞...", LocalDateTime.now()));

        // waiting for thread working done
        latch.await();

        // thread works done !
        System.out.println(String.format("[%s]-主线程进被唤醒...", LocalDateTime.now()));
    }


}
