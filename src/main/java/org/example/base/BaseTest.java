package org.example.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class BaseTest {

    protected Browser browser;
    protected Logger log;

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
    }

    @BeforeClass
    @Parameters({"propertiesfile"})
    public void BaseTestSetup(@Optional("config/test_config_2.properties") String configParameters) {
        browser = new Browser(configParameters);
        browser.open();
    }

//    @BeforeMethod
//    public void openIndexPage(Method m) {
//        log = LogManager.getLogger(m.getDeclaringClass().getSimpleName() + "." + m.getName());
//        browser.setLog(log);
//    }

    @AfterClass(alwaysRun = true)
    public void cleanUp() {
        if (browser != null) {
            browser.close();
        }

    }

    public WebDriver getDriver() {
        return browser.getDriver();
    }

    public Logger getLogger() {

        return log;
    }

    public boolean isGrid() {
        return Browser.param.isGrid();
    }
}
