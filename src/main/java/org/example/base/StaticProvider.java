package org.example.base;

import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.Iterator;

public class StaticProvider {
    @DataProvider(name = "create")
    public Object[][] createData(){
        return new Object[][]{
                { "Cedric", 36},
                { "Anne", 37},
        };
    }

    @DataProvider(name = "create2")
    public Iterator<Object[]> createData1() {
        Object[][] myObjects = new Object[][]{
                {"Cedric", 36},
                {"Anne", 37},
        };
        return Arrays.asList(myObjects).iterator();
    }

    @DataProvider(name = "create3")
    public Object[] createData2() {
        Object[] myObjects = new Object[]{
                37, 36
        };
        return myObjects;
    }
}
