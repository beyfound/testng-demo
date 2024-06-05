import org.example.base.BaseTest;
import org.example.listener.RetryAnalyzer;
import org.example.page.BaiduPage;
import org.testng.annotations.Test;

public class BuyerTest extends BaseTest{
    @Test
    public void buyerSelenium()
    {
        BaiduPage baiduPage = new BaiduPage(browser);
        baiduPage.search("selenium");
        baiduPage.sleep(1);
    }

    @Test(dependsOnMethods = {"buyerSelenium"})
    public void buyerTestNG()
    {
        BaiduPage baiduPage = new BaiduPage(browser);
        baiduPage.search("buyerTestNG");
        baiduPage.sleep(1);
        assert 1 ==2;
    }

    @Test
    public void buyerJava()
    {
        BaiduPage baiduPage = new BaiduPage(browser);
        baiduPage.search("buyerJava");
        baiduPage.sleep(1);
    }
}
