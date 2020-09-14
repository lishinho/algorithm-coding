package com.lzb.fund.multiThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample extends RecursiveTask<Integer> {
    private final int threshold = 5;
    private int first;
    private int last;

    public ForkJoinExample(int first, int last) {
        this.first = first;
        this.last = last;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if (last -first <= threshold) {
            for (int i = first; i <= last; i++) {
                result += i;
            }
        } else {
            int middle = first + (last - first) / 2;
            ForkJoinExample leftTask = new ForkJoinExample(first, middle);
            ForkJoinExample rightTask = new ForkJoinExample(middle + 1, last);
            leftTask.fork();
            rightTask.fork();
            result = leftTask.join() + rightTask.join();
        }
        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinExample example = new ForkJoinExample(1, 1000000);

        // forkJoinPool is a special thread pool which the numbers of thread depends on CPU cores
        // each thread in the pool maintains a deque based on array
        // the free thread can steal task to execute from other running thread
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // future can get result asynchronously
        Future<Integer> result = forkJoinPool.submit(example);
        System.out.println(result.get());

        // it costs more time than iteration in this demo
        // because forkJoin is used by parallel computing which can use CPU effectively
        // but for the simple computing which can be done in single core,
        // it would cost much on task distributing and joining result to main thread
        System.out.println("Used time: " + (System.currentTimeMillis()-start));
    }
}
