package org.example.listener;

import org.apache.log4j.Logger;
import org.example.base.BaseTest;
import org.example.util.Screenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.util.Date;

public class ScreenshotListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult tr) {
        BaseTest appTest = (BaseTest) tr.getInstance();
        Logger log = appTest.getLogger();
        WebDriver driver = null;
        try {
            if (appTest.isGrid()) {
                driver = new Augmenter().augment(appTest.getDriver());
            } else {
                driver = appTest.getDriver();
            }
            String screenName = new Date().getTime() + "-" + log.getName().replace(".", "-")
                    + "-failed" + ".png";
            File screenshot = new Screenshot(driver).takeScreenshot(screenName);
            log.info("Test failed, screenshot path: " + screenshot.getAbsolutePath());
        } catch (Exception e) {
            log.error("Take screenshot failed, error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
