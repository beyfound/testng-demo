package org.example.factory;

import java.lang.reflect.InvocationTargetException;

public class PhoneFactory {
    public static Phone createPhone(String type){
        switch (type){
            case "IPhone":
                return new IPhone();
            case "HuaweiPhone":
                return new HuaweiPhone();
            default:
                return null;
        }
    }

    public static <T> T createPhoneByClassName(String className){
        try {
            Class<?> clazz = Class.forName(className);
            return (T) clazz.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
