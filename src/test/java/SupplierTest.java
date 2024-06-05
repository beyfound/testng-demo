import org.example.base.BaseTest;
import org.example.page.BaiduPage;
import org.testng.annotations.Test;

public class SupplierTest extends BaseTest {
    @Test
    public void supplierSelenium()
    {
        BaiduPage baiduPage = new BaiduPage(browser);
        baiduPage.search("supplierSelenium");
        baiduPage.sleep(1);
    }

    @Test
    public void supplierTestNG()
    {
        BaiduPage baiduPage = new BaiduPage(browser);
        baiduPage.search("supplierTestNG");
        baiduPage.sleep(1);
    }

    @Test
    public void supplierJava()
    {
        BaiduPage baiduPage = new BaiduPage(browser);
        baiduPage.search("supplierJava");
        baiduPage.sleep(1);
    }
}
