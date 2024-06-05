package org.example.base;

import org.example.util.PropertiesLoader;
import org.openqa.selenium.WebDriverException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Parameter {
    private PropertiesLoader properties;
    private Element documentElement;
    private static final String BROWSER_TYPE = "browserType";
    private static final String GRID = "grid";
    private static final String REMOTE_URL = "remoteNodeUrl";
    private static final String FIREFOX = "firefox";
    private static final String IE = "ie";
    private static final String CHROME = "chrome";
    private static final String URL = "url";
    private static final String RESOURCE_SERVER = "resourceServer";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String CLOSE_BROWSER_ON_FINISH = "closeBrowserOnFinish";

    public Parameter(String path) {
        properties = new PropertiesLoader(path);
    }

    public Browser.BrowserType getBrowserType() {
        String browserType = (String) getProperty(BROWSER_TYPE);
        if (browserType.equalsIgnoreCase(FIREFOX)) {
            return Browser.BrowserType.FIREFOX;
        } else if (browserType.equalsIgnoreCase(CHROME)) {
            return Browser.BrowserType.CHROME;
        } else if (browserType.equalsIgnoreCase(IE)) {
            return Browser.BrowserType.IE;
        }
        throw new WebDriverException("Browser type not supported");
    }

    public String getRemoteNodeUrl() {
        return getProperty(REMOTE_URL);
    }

    public String getURL() {
        return getProperty(URL);
    }

    public String getResourceServer() {
        return getProperty(RESOURCE_SERVER);
    }

    public Boolean isGrid() {
        return Boolean.parseBoolean(getProperty(GRID));
    }

    public String getUsername() {
        return getProperty(USERNAME);
    }

    public String getPassword() {
        return getProperty(PASSWORD);
    }

    public Boolean isCloseBrowserOnFinish() {
        return  Boolean.parseBoolean(getProperty(CLOSE_BROWSER_ON_FINISH));
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
