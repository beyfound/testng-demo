package org.example.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class TestBuilder implements Comparable<TestBuilder>{
    private  int age;
    private  String name;

    public TestBuilder() {
    }

    public TestBuilder(Builder builder) {
        this.age = builder.age;
        this.name = builder.name;
    }

    public TestBuilder(int age,String name) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(TestBuilder o) {
        return o.age-this.age;
    }

    public static class Builder{
        private int age;
        private String name;

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public TestBuilder build() {
            return new TestBuilder(this);
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        TestBuilder builder1 = new TestBuilder(1,"test");
        TestBuilder builder2 = new TestBuilder(2,"test2");
//        Field fle = Class.forName("org.example.base.TestBuilder").getDeclaredField("name");
//        fle.setAccessible(true);
//        fle.set(builder1, "test3");
//        System.out.println(builder1.name);
//
//        Constructor<TestBuilder> constructor = TestBuilder.class.getConstructor();
//        TestBuilder obj = constructor.newInstance();
//
        String s = "Hello word";
//        Field field = String.class.getDeclaredField("value");
//        field.setAccessible(true);
//        byte[] value = (byte[]) field.get(s);
//        value[3] = '5';
//        field.set(s, value);
//        System.out.println(s);

//        StringBuilder builder = new StringBuilder(s);
//        builder.reverse();
//        builder.append("dd");
//        s = builder.toString();
//        System.out.println(s);
//
//        System.out.println(s.indexOf("e"));
//        System.out.println(s.substring(s.indexOf('e'),6));

        Integer a = new Integer(114);
        Integer b = 114;
        int c = 144;
        System.out.println(a==b);
        List<Integer> d = new ArrayList<>();
        Set<Integer> ed = new LinkedHashSet<>();
        Iterator<Integer> iterator = ed.iterator();
        while (iterator.hasNext()){
            int dd = iterator.next();
        }

        List<TestBuilder> list = new ArrayList<>();
        list.add(builder1);
        list.add(builder2);
        Collections.sort(list);
        list.forEach(age -> System.out.println(age.age));
    }

//    private static void swwap(TestBuilder builder1, TestBuilder builder2) {
//        System.out.println(builder1);
//        System.out.println(builder2);
//        TestBuilder temp = builder1;
//        builder1 = builder2;
//        builder2 = temp;
//        System.out.println(builder1);
//        System.out.println(builder2);
//        System.out.println(builder1.name);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestBuilder that = (TestBuilder) o;
        return age == that.age && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

}
