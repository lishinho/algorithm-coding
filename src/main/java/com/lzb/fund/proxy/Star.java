package com.lzb.fund.proxy;

public class Star implements ShowService {
    private String name;

    public Star(String name) {
        this.name = name;
    }

    // used for cglib proxy
    public Star() {}

    @Override
    public void sing(String song) {
        System.out.println(name + " sing a song named " + song);
    }

    @Override
    public void dance() {
        System.out.println(name + " dance");
    }
}
