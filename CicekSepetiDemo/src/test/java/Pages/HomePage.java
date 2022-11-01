package Pages;

import CicekSepetiDemo.DemoTests;

import static CicekSepetiDemo.DemoTests.driver;
import static java.lang.Thread.sleep;

public class HomePage extends DemoTests {
    public static void checkHomePageUrl() throws InterruptedException {
        String urlCheck = driver.getCurrentUrl();
        urlCheck.contains("https://www.mizu.com/en-mx/");
        sleep(3000);
    }
}
