package com.lzb.pattern.adapter;

import java.util.concurrent.Callable;

public class Task implements Callable<Long> {
    private long num;

    public Task(long num) {
        this.num = num;
    }

    @Override
    public Long call() {
        long r = 0;
        for (long n = 1; n <= this.num; n++) {
            r += n;
        }
        System.out.println("Result = " + r);
        return r;
    }
}
