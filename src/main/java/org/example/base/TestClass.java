package org.example.base;

import java.io.*;
import java.util.Arrays;
import java.util.function.Function;

public class TestClass {
    public static void main(String[] args) {
        String s = "123";
        change(s, Integer::parseInt);
        change(s, str -> Integer.parseInt(str));
    }

    public static void change (String s, Function<String, Integer> func) {
        System.out.println(func.apply(s));
    }

    public void test(String path) throws IOException {
        FileReader reader = new FileReader(path);
        int data = reader.read();
        while (data != -1) {
            System.out.print((char) data);
            data = reader.read();
        }
        reader.close();

        InputStreamReader reader1 = new InputStreamReader(new FileInputStream(path));
        BufferedReader reader2 = new BufferedReader(reader1);
        String dataString;
        while ((dataString  = reader2.readLine()) != null){
            System.out.println(reader2.readLine());
        }

        File file = new File(path);
        File[] files = file.listFiles();
        Arrays.asList(files).forEach(a -> System.out.println(a.getName()));
        Arrays.asList(files).forEach(System.out::println);
        Arrays.asList(files).toArray(new File[2]);
        reader2.close();
        reader1.close();

        FileInputStream inputStream = new FileInputStream(path);
        while (inputStream.available() > 0) {
            System.out.print((char) inputStream.read());
        }
    }
}
