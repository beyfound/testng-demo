package org.example.util;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Screenshot {
    private WebDriver driver;

    public Screenshot(WebDriver driver) {
        this.driver = driver;
    }

    public File takeScreenshot(String screenName) throws IOException {
        if (screenName.indexOf("/") != 0) {
            screenName = "/" + screenName;
        }
        File screenshot = new File("test-output/snapshot" + screenName);
        if (!screenshot.getParentFile().exists())
            screenshot.getParentFile().mkdirs();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(scrFile, screenshot);
        return screenshot;
    }

    public static Screenshot getInstance(WebDriver driver) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        return Screenshot.class.getConstructor(WebDriver.class).newInstance(driver);
    }
}
