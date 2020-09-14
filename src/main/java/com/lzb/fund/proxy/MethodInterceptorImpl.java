package com.lzb.fund.proxy;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MethodInterceptorImpl implements MethodInterceptor {
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        getMoney();

        Object invoke = methodProxy.invokeSuper(obj, objects);

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
