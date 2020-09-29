package com.lzb.pattern.observer;

// to be used as control product observer to different stores
// which may extend it to own the function
public interface ProductObservable {

    void addObserver(ProductObserver observer);

    void removeObserver(ProductObserver observer);
}
