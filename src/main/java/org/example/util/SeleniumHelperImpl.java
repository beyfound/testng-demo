package org.example.util;

import org.apache.logging.log4j.Logger;
import org.example.base.Browser;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class SeleniumHelperImpl implements SeleniumHelper{
    protected WebDriver driver;
    protected Logger log;

    public SeleniumHelperImpl(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    @Override
    public void click(By by) {
        WebElement element = waitForVisible(by);
        scrollIntoView(element);
        element.click();
        log.info("Click element: " + by.toString());
    }

    @Override
    public void sendKeyBoardKeys(By by, Keys key) {
        WebElement element = waitForVisible(by);
        scrollIntoView(element);
        element.sendKeys(key);
        log.info("Enter : " + key.toString() + " in " + by);
    }

    @Override
    public void enterText(By by, String text) {
        WebElement element = waitForVisible(by);
        scrollIntoView(element);
        element.sendKeys(text);
        log.info("Enter : " + text + " in " + by);

    }

    @Override
    public void scrollIntoView(By by) {
        WebElement element = waitForVisible(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded();", element);

    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded();", element);
    }

    @Override
    public void selectSelectorOptionByText(By by, String text) {
        WebElement element = waitForVisible(by);
        scrollIntoView(by);
        Select select = new Select(element);
        String currentText = select.getFirstSelectedOption().getText();
        if (!currentText.equals(text)) {
            select.selectByVisibleText(text);
            log.info("Select : " + text + " in " + by);
        }

    }

    @Override
    public void sleep(int s) {
        try {
            Thread.sleep(1000 * s);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public boolean isVisilble(By by) {
        WebElement element = waitForVisible(by);
        return element != null ? true : false;
    }

    @Override
    public void waitForPageLoad() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Override
    public WebElement waitForVisible(By by) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            log.error("Could not find element: " + by.toString() + " in 30s.");
            e.printStackTrace();
            throw new WebDriverException(e);
        }
    }

    @Override
    public WebElement findElement(By by) {
        try {
            ExpectedCondition<Boolean> b = ExpectedConditions.titleContains ("百度一下");

            new WebDriverWait(driver, Duration.ofSeconds(5)).until(b);
            return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            log.error("Could not find element: " + by.toString() + " in 30s.");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    @Override
    public void waitForDisappear(By by) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            log.error("Element: " + by.toString() + " not disappear in 30s.");
            e.printStackTrace();
            throw new WebDriverException(e);
        }

    }

    @Override
    public void uploadFile(By by, File file) {
        if (Browser.param.isGrid()) {
            uploadServerFile(by, Paths.get(Browser.getResourceServer(), file.getPath()).toString());
            return;
        }

        if (file.exists()) {
            uploadServerFile(by, file.getAbsolutePath());
        } else {
            log.error("File does not exist: " + file.getAbsolutePath());
            Assert.fail("File for upload is missing");
        }
    }

    /**
     * Upload a file in the browser
     *
     * @author zdu
     */
    private void uploadServerFile(By by, String filePath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element = wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver input) {
                return input.findElement(by);
            }
        });

        element.sendKeys(filePath);
    }

    @Override
    public void switchToWindow(String title) {
        String currentHandle = "";
        try {
            currentHandle = driver.getWindowHandle();
        } catch (NoSuchWindowException e) {
        }

        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentHandle)) {
                driver.switchTo().window(windowHandle);
                if (driver.getTitle().equals(title)) {
                    currentHandle = null;
                    break;
                }
            }
        }
        if (currentHandle != null) {
            throw new WebDriverException("can't switchTo window:  " + title);
        }

    }

    @Override
    public String getElementValue(By by) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElement(by).getText();
    }

    @Override
    public void executeScript(String script, By... bys) {
        for (By by : bys) {
            try {
                WebElement element = waitForVisible(by);
                log.info("Execute script of element: " + by.toString() + ", script: " + script);
                executeScript(script, element);
            } catch (Exception e) {
                continue;
            }
        }
    }

    protected void executeScript(String script, Object o) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript(script, o);
    }


}
