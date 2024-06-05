import org.example.base.BaseTest;
import org.example.page.BaiduPage;
import org.testng.annotations.Test;

public class CustomerTest extends BaseTest {
    @Test
    public void customerSelenium()
    {
        BaiduPage baiduPage = new BaiduPage(browser);
        baiduPage.search("customerSelenium");
        baiduPage.sleep(1);
    }

    @Test
    public void customerTestNG()
    {
        BaiduPage baiduPage = new BaiduPage(browser);
        baiduPage.search("customerSelenium");
        baiduPage.sleep(1);
        assert 1 ==2;
    }

    @Test
    public void customerJava()
    {
        BaiduPage baiduPage = new BaiduPage(browser);
        baiduPage.search("customerSelenium");
        baiduPage.sleep(1);
    }
}
