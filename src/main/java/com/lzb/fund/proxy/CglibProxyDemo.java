package com.lzb.fund.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class CglibProxyDemo {
    public static void main(String[] args) {
        Star star = new Star("Lishinho");

        MethodInterceptor methodInterceptor = new MethodInterceptorImpl();

        // create dynamic proxy by enhancer
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(star.getClass());

        enhancer.setCallback(methodInterceptor);

        ShowService service = (ShowService)enhancer.create();
        service.sing("AiWoZhongHua...");
    }
}
