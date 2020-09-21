package com.lzb.pattern;

import com.lzb.pattern.adapter.RunnableAdapter;
import com.lzb.pattern.adapter.Task;
import org.junit.Test;

import java.util.concurrent.Callable;

public class AdapterTest {
    @Test
    public void SimpleAdapterTest() throws InterruptedException {
        Callable<Long> callable = new Task(1234000L);
        Thread thread = new Thread(new RunnableAdapter(callable));
        thread.start();
        thread.join();
        // current thread waits for this thread to die
        // the main thread will continue execute here after thread ends
    }
}
