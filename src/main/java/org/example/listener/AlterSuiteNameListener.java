package org.example.listener;

import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;

import java.util.List;

public class AlterSuiteNameListener implements IAlterSuiteListener {
    public void alter(List<XmlSuite> suites) {
        XmlSuite suite = suites.get(0);
        suite.setName(getClass().getSimpleName());
    }
}
