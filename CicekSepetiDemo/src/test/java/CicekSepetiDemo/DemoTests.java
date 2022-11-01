package CicekSepetiDemo;

import Pages.*;
import javafx.scene.layout.Priority;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static java.lang.Thread.*;
import static org.testng.Assert.*;

public class DemoTests {

    public static WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public static void setup(String browser) throws Exception{
        //Check if parameter passed from TestNG is 'firefox'
        if(browser.equalsIgnoreCase("Firefox")){
            System.setProperty("webdriver.gecko.driver", "src/test/java/Drivers/geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            options.setProfile(new FirefoxProfile());
            options.addPreference("dom.webnotifications.enabled", false);

            driver = new FirefoxDriver(options);
        }
        //Check if parameter passed as 'chrome'
        else if(browser.equalsIgnoreCase("Chrome")){
            System.setProperty("webdriver.chrome.driver","src/test/java/Drivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);

        }
        else{
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.mizu.com/en-mx/login");
    }

    @Test(priority=1)
    public static void CheckLoginPageUrl() throws Exception {
        LoginPage.checkLoginPageUrl();
    }

    @Test(priority=2)
    public static void LoginPageTest(){
        String homePageTitle = LoginPage.verifyLoginPageTitle();
        Assert.assertEquals(homePageTitle, "Sign in - Mizu","Home page title not matched!");

    }

    @Test(priority=3)
    public static void LoginWithValidCredentials() throws Exception {
        LoginPage.enterValidEmail();
        LoginPage.enterValidPassword();
        LoginPage.clickSignIn();
        LoginPage.checkHomePageUrl();
        LoginPage.homePageSearchBarIsDisplayed();
        LoginPage.clickBlankArea();
        LoginPage.clickMyAccount();
        LoginPage.clickLogOut();
        LoginPage.clickAccountSignIn();
        Thread.sleep(2000);
        LoginPage.clickSubSignIn();
        LoginPage.checkLoginPageUrl();
    }

    @Test(priority=4)
    public static void LoginWithInvalidEmailValidPassword() throws Exception {
        LoginPage.enterInvalidEmail();
        LoginPage.enterValidPassword();
        LoginPage.clickSignIn();
        LoginPage.checkWrongCredentialsErrorMessage();
        Thread.sleep(1000);
        LoginPage.clickOk();
    }

    @Test(priority=5)
    public static void LoginWithValidEmailInvalidPassword() throws Exception {
        LoginPage.enterValidEmail();
        LoginPage.enterInvalidPassword();
        LoginPage.clickSignIn();
        LoginPage.checkWrongCredentialsErrorMessage();
        Thread.sleep(1000);
        LoginPage.clickOk();
    }

    @Test(priority=6)
    public static void CheckLoginWithTwoCharacterLongPassword() throws Exception {
        LoginPage.enterValidEmail();
        LoginPage.enterTwoCharacterPassword();
        Thread.sleep(2000);
        LoginPage.clickSignIn();
        LoginPage.checkPasswordError();
    }

    @AfterMethod //AfterMethod annotation - This method executes after every test execution
    public void screenShot(ITestResult result){
        //using ITestResult.FAILURE is equals to result.getStatus then it enter into if condition
        if(ITestResult.FAILURE==result.getStatus()){
            try{
                // To create reference of TakesScreenshot
                TakesScreenshot screenshot=(TakesScreenshot)driver;
                // Call method to capture screenshot
                File src=screenshot.getScreenshotAs(OutputType.FILE);
                // Copy files to specific location
                // result.getName() will return name of test case so that screenshot name will be same as test case name
                FileUtils.copyFile(src, new File("src/test/java/screenshots/"+result.getName()+".png"));
                System.out.println("Successfully captured a screenshot");
            }catch (Exception e){
                System.out.println("Exception while taking screenshot "+e.getMessage());
            }
        }
        //driver.quit();
    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }

}