package org.example.factory;

public class IPhone implements Phone{
    @Override
    public void call() {
        System.out.println("使用IPhone打电话");
    }
}
