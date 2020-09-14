package com.lzb.struct;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// this struct tells you how to use thread pools
// and introduce the basic funds behind this
public class ThreadPoolExamples {
    // use LinkedBlockingQueue which is infinite capacity on defaults
    // single override finalize method which means gc process will shutdown thread pool
    ExecutorService executor1 = Executors.newSingleThreadExecutor();
    ExecutorService executor2 = Executors.newFixedThreadPool(5);

    // use ScheduledThreadPoolExecutor.DelayedWorkQueue
    ExecutorService executor3 = Executors.newSingleThreadScheduledExecutor();
    ExecutorService executor4 = Executors.newScheduledThreadPool(5);

    // use new SynchronousQueue
    ExecutorService executor5 = Executors.newCachedThreadPool();

    ExecutorService executor6 = Executors.newWorkStealingPool();
}
