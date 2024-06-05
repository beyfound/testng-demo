package org.example.base;

import org.example.util.SeleniumHelper;
import org.example.util.SeleniumHelperImpl;
import org.openqa.selenium.By;

import java.io.File;
import java.text.MessageFormat;

public abstract class BasePage {
    protected SeleniumHelper helper;
    protected Browser browser;

    public static By byText(String xpath, String texts) {
        return By.xpath(MessageFormat.format(xpath, texts));
    }

    public static By byCss(String cssSelector) {
        return By.cssSelector(cssSelector);
    }

    public static By byXpath(String cssSelector) {
        return By.xpath(cssSelector);
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {

        }
    }
    public BasePage(Browser browser) {
        this.browser = browser;
        this.helper = new SeleniumHelperImpl(browser.getDriver(), browser.getLog());

    }

    public abstract void logOut();
}
