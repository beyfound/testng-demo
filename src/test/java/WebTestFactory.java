import org.testng.annotations.Factory;

public class WebTestFactory {
    @Factory
    public Object[] createInstances() {
        Object[] result = new Object[3];
        for (int i = 0; i <= 2; i++) {
            result[i] = new WebTest((i + 1) * 10);
        }
        return result;
    }
}