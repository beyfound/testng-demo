package org.example.factory;

public class HuaweiPhone implements Phone{
    @Override
    public void call() {
        System.out.println("使用华为打电话");
    }
}
