package org.example.base;

import com.google.common.io.Files;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ImmutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Browser {
    private Logger log = LogManager.getLogger(Browser.class);
    private WebDriver driver;
    public static Parameter param;
    private DriverService driverService;

    public enum BrowserType {
        FIREFOX, IE, CHROME, SAFARI
    }

    public Browser(String propertiesfile) {
        initScriptsParameters(propertiesfile);
        createWebDriver(param.getBrowserType());
    }

    private static synchronized File initDriverFile(String driverName) {
        InputStream input = null;
        FileOutputStream output = null;
        try {
            File driverFile = new File("target\\classes\\web-driver\\" + driverName);
            if (driverFile.exists()) {
                return driverFile;
            }

            System.out.println("get driver file from stream");

            File driverFolder = driverFile.getParentFile();
            if (!driverFolder.exists()) {
                driverFolder.mkdirs();
            }

            System.out.println(Browser.class.getClassLoader().getResource("web-driver/" + driverName));
            input = Browser.class.getClassLoader().getResourceAsStream("web-driver/" + driverName);
            File tempfile = File.createTempFile("temp_driver_file", driverName);
            output = new FileOutputStream(tempfile);

            IOUtils.copy(input, output);
            Files.copy(tempfile, driverFile);
            tempfile.delete();

            System.out.println("remove the temp file");

            return driverFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
    }

//    public void DriverInit(Logger log) {
//
//        WebDriverManager.chromedriver().setup();
//    }
    public void createWebDriver(BrowserType browserType) {
        File driverFile = null;

        switch (browserType) {
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (param.isGrid()) {
                    try {
                        log.info("Will use Remote FireFox Browser.");
                        driver = new RemoteWebDriver(new URL(param.getRemoteNodeUrl()), firefoxOptions);
                    } catch (MalformedURLException e) {
                        log.error("Malformed URL");
                        throw new WebDriverException(e);
                    }
                } else {
                    driverFile = initDriverFile("geckodriver.exe");
                    driverService = new GeckoDriverService.Builder().usingDriverExecutable(driverFile).usingAnyFreePort()
                            .build();
                    try {
                        driverService.start();
                    } catch (IOException e) {
                        throw new WebDriverException(e);
                    }
                    log.info("Will use Local Chrome Browser.");
                    driver = new RemoteWebDriver(driverService.getUrl(), firefoxOptions);
                }
                return;
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
//                if (param.isGrid()) {
//                    try {
//                        log.info("Will use Remote Chrome Browser.");
//                        URL remoteUrl = new URL(param.getRemoteNodeUrl());
//                        synchronized (Browser.class) {
//                            driver = new RemoteWebDriver(remoteUrl, chromeOptions);
//                        }
//                    } catch (MalformedURLException e) {
//                        log.error("Malformed URL");
//                        throw new WebDriverException(e);
//                    }
//                } else {
//                    driverFile = initDriverFile("chromedriver.exe");
//                    driverService = new ChromeDriverService.Builder().usingDriverExecutable(driverFile).usingAnyFreePort()
//                            .build();
//                    try {
//                        driverService.start();
//                    } catch (IOException e) {
//                        throw new WebDriverException(e);
//                    }
//                    log.info("Will use Local Chrome Browser.");
//                    driver = new RemoteWebDriver(driverService.getUrl(), chromeOptions);
                driver = new ChromeDriver(chromeOptions);
                driver.manage().window().maximize();
//                }
                return;
            default:
                throw new RuntimeException("Browser type unsupported");
        }
    }

    public void setLog(Logger log) {
        if (log == null) {
            this.log = LogManager.getLogger(this.getClass().getName());
            return;
        }

        this.log = log;
    }

    public Logger getLog() {
        return log;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void open() {
        driver.manage().window().maximize();
        //driver.get(param.getURL());
        log.info("OPENING browser on page: " + param.getURL());
    }

    public void close() {
        if (param.isCloseBrowserOnFinish()) {
            driver.quit();
            if (driverService != null) {
                driverService.stop();
            }
        }
    }

    public static String getResourceServer() {
        return param.getResourceServer();
    }

    /**
     * Initialization script parameters
     */
    private synchronized void initScriptsParameters(String propertiesfile) {
        if (Browser.param == null) {
            Browser.param = new Parameter(propertiesfile);
        }
    }
}
