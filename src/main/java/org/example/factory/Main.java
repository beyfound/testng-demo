package org.example.factory;

public class Main {
    public static void main(String[] args)
    {
        Phone phone = PhoneFactory.createPhone("HuaweiPhone");
        phone.call();
        Phone phone1 = PhoneFactory.createPhone("IPhone");
        phone1.call();
        Phone phone2 = PhoneFactory.createPhoneByClassName("org.example.factory.IPhone");
        phone2.call();
    }
}
