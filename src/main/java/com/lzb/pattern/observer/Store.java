package com.lzb.pattern.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// store is used to produce products
// once the store produces or changes the price of the product
// we want each observer can view the difference
public class Store {

    private List<ProductObserver> observers = new ArrayList<>();
    private Map<String, Product> products = new HashMap<>();

    public void addObserver(ProductObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(ProductObserver observer) {
        this.observers.remove(observer);
    }

    public void addNewProduct(String name, double price) {
        Product p = new Product(name, price);
        products.put(p.getName(), p);
        observers.forEach(o -> o.onPublished(p));
    }

    public void setProductPrice(String name, double price) {
        Product p = products.get(name);
        p.setPrice(price);
        observers.forEach(o -> o.onPriceChanged(p));
    }
}
