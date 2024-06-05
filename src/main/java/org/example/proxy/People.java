package org.example.proxy;

public class People implements ISay{
    @Override
    public void say() {
        System.out.println("say something");
    }
}
