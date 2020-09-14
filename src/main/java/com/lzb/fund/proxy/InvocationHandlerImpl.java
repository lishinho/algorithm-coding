package com.lzb.fund.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationHandlerImpl implements InvocationHandler {
    ShowService target;

    public InvocationHandlerImpl(ShowService target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        getMoney();

        Object invoke = method.invoke(target, args);

        writeReceipt();

        return invoke;
    }

    private void getMoney() {
        System.out.println("get money");
    }

    private void writeReceipt() {
        System.out.println("write receipt");
    }
}
