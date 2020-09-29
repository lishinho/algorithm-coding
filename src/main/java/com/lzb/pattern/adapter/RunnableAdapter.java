package com.lzb.pattern.adapter;

import java.util.concurrent.Callable;

/**
 * Adapter pattern is the design pattern
 * that allows the interface of an existing class
 * to be used as another interface.
 *
 * This is an example of Adapter pattern
 * RunnableAdapter adapts callable to runnable
 * In this way, we can use callable in the runnable way
 */
public class RunnableAdapter implements Runnable {
  private Callable<?> callable;

  public RunnableAdapter(Callable<?> callable) {
    this.callable = callable;
  }
  
  @Override
  public void run() {
    try {
      callable.call();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
