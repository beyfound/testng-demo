import org.example.base.BaseTest;
import org.example.base.StaticProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Listeners({org.example.listener.AlterSuiteNameListener.class})
public class SampleTest extends BaseTest{

    //@Factory
    public static Object[] create() {
        Object[] result = new Object[3];
        for (int i = 0; i < 3; i++){
            result[i] = new SampleTest(i*10);
        }

        return result;
    }
    private int lauguage;

    public SampleTest(int lauguageq) {
        this.lauguage = lauguageq;
    }

//    @BeforeClass
    public void setUp() {
        // code that will be invoked when this test is instantiated
    }

//    @Test(groups = {"fast"})

    public void aFastTest() {
        System.out.println(lauguage + "Fast test");
    }

//    @Test(groups = {"slow"})
//    @Ignore
    public void aSlowTest() {

        //WebDriver driver = getDriver();

        System.out.println("Slow test");
    }

    @DataProvider(name = "test1",parallel = true)
    public Object[][] createData1() {
        return new Object[][]{
                {"Cedric", 36},
                {"Anne", 37},
        };
    }

    @DataProvider(name = "test2",parallel = true)
    public Object[][] createData2() {
        return new Object[][]{
                {"2-Cedric", 36},
                {"2-Anne", 37},
        };
    }

//    @Test(dataProvider = "test1",priority=1)
//    @Ignore

    public void verifyData1(String n1, Integer n2) {

        System.out.println(n1 + " " + n2);
    }

//    @Test(dataProvider = "create3", dataProviderClass = StaticProvider.class)
//    @Ignore
    public void verifyData2(Integer n2) {

        System.out.println(" " + n2);
    }

    //@Test
    public void testWebDriver() {
        WebDriver driver = getDriver();
        driver.get("https://www.baidu.com");
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://blog.51cto.com/");
        WebElement element = driver.findElement(By.id("kw"));
        List<WebElement> elements = driver.findElements(By.id("kw"));
        elements.forEach(webElement -> System.out.println(webElement.getText()));
        driver.findElement(By.id("su")).click();
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.id(""))).clickAndHold();
    }

}
