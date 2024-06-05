package org.example.page;

import org.apache.log4j.Logger;
import org.example.base.BasePage;
import org.example.base.Browser;
import org.openqa.selenium.By;

public class BaiduPage extends BasePage {

    private static final Logger log = Logger.getLogger(BaiduPage.class);
    public BaiduPage(Browser browser) {
        super(browser);
    }

    @Override
    public void logOut() {
        browser.getDriver().quit();
    }

    public void search(String keyword) {
        browser.getDriver().get("https://www.baidu.com");
        helper.enterText(By.id("kw"), keyword);
        helper.click(By.id("su"));
    }
}
