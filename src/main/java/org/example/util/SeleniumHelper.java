package org.example.util;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;

public interface SeleniumHelper {
    void click(By by);

    void sendKeyBoardKeys(By by, Keys key);

    void enterText(By by, String text);

    void scrollIntoView(By by);

    void selectSelectorOptionByText(By by, String text);

    void sleep(int s);

    boolean isVisilble(By by);

    void waitForPageLoad();

    WebElement waitForVisible(By by);

    WebElement findElement(By by);

    List<WebElement> findElements(By by);

    void waitForDisappear(By by);

    void uploadFile(By by, File file);

    void switchToWindow(String title);

    String getElementValue(By by);

    void executeScript(String script, By... bys);

}
