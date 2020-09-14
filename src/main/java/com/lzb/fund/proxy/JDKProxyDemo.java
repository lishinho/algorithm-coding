package com.lzb.fund.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JDKProxyDemo {
    // class used JDK proxy must have an interface
    public static void main(String[] args) {
        // proxied class
        Star star = new Star("Lishinho");

        // get its classloader
        ClassLoader classLoader = star.getClass().getClassLoader();

        // get its interfaces
        Class[] interfaces = star.getClass().getInterfaces();

        // handle invoking method by your own handler
        InvocationHandler invocationHandler = new InvocationHandlerImpl(star);

        // need all interfaces to create a proxy instance
        Object obj = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        ShowService service = (ShowService) obj;
        service.sing("WuXingHongQi...");

    }
}
