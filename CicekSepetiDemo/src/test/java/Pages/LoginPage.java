package Pages;
import CicekSepetiDemo.DemoTests;
import org.openqa.selenium.By;
import static java.lang.Thread.sleep;

public class LoginPage extends DemoTests {

    public static String verifyLoginPageTitle(){
        return driver.getTitle();
    }

    public static void checkLoginPageUrl() throws InterruptedException {
        String urlCheck = driver.getCurrentUrl();
        urlCheck.contains("https://www.mizu.com/en-mx/login");
        sleep(3000);
    }

    public static void checkHomePageUrl() throws InterruptedException {
        String urlCheck = driver.getCurrentUrl();
        urlCheck.contains("https://www.mizu.com/en-mx/");
        sleep(3000);
    }

    public static void homePageSearchBarIsDisplayed(){
        driver.findElement(By.xpath("//div[@class='district-search-button__text-inner js-district-search-button-text']")).isDisplayed();
    }

    public static void enterValidEmail(){
        driver.findElement(By.xpath("//input[@id='EmailLogin']")).clear();
        driver.findElement(By.xpath("//input[@id='EmailLogin']")).isDisplayed();
        driver.findElement(By.xpath("//input[@id='EmailLogin']")).sendKeys("autotest@test.com");
    }

    public static void enterValidPassword(){
        driver.findElement(By.xpath("//input[@id='Password']")).clear();
        driver.findElement(By.xpath("//input[@id='Password']")).isDisplayed();
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("test123");
    }

    public static void enterInvalidEmail(){
        driver.findElement(By.xpath("//input[@id='EmailLogin']")).clear();
        driver.findElement(By.xpath("//input[@id='EmailLogin']")).isDisplayed();
        driver.findElement(By.xpath("//input[@id='EmailLogin']")).sendKeys("autotest@test.coo");
    }

    public static void enterInvalidPassword(){
        driver.findElement(By.xpath("//input[@id='Password']")).clear();
        driver.findElement(By.xpath("//input[@id='Password']")).isDisplayed();
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("testtseet");
    }

    public static void enterTwoCharacterPassword(){
        driver.findElement(By.xpath("//input[@id='Password']")).clear();
        driver.findElement(By.xpath("//input[@id='Password']")).isDisplayed();
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("ab");
    }

    public static void clickSignIn(){
        driver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
        driver.findElement(By.xpath("//button[text()='Sign In']")).click();
    }

    public static void checkWrongCredentialsErrorMessage(){
        driver.findElement(By.xpath("//div[text()='E-mail address or password is incorrect. Please check your information and try again.']")).isDisplayed();
    }

    public static void clickOk(){
        driver.findElement(By.xpath("//button[text()='Ok']")).isDisplayed();
        driver.findElement(By.xpath("//button[text()='Ok']")).click();
    }

    public static void checkPasswordError(){
        driver.findElement(By.xpath("//span[text()='Please enter minimum 3 and maximum 20 characters.']")).isDisplayed();
    }

    public static void clickMyAccount(){
        driver.findElement(By.xpath("//a[@title='My Account'][1]")).isDisplayed();
        driver.findElement(By.xpath("//a[@title='My Account'][1]")).click();
    }

    public static void clickLogOut(){
        driver.findElement(By.xpath("(//a[@title='Log Out'])[last()-1]")).isDisplayed();
        driver.findElement(By.xpath("(//a[@title='Log Out'])[last()-1]")).click();
    }

    public static void clickAccountSignIn(){
        driver.findElement(By.xpath("(//span[text()='Sign in'])[2]")).isDisplayed();
        driver.findElement(By.xpath("(//span[text()='Sign in'])[2]")).click();
    }

    public static void clickSubSignIn(){
        driver.findElement(By.xpath("(//span[text()='Sign in'])[3]")).isDisplayed();
        driver.findElement(By.xpath("(//span[text()='Sign in'])[3]")).click();
    }

    public static void clickBlankArea(){
        driver.findElement(By.xpath("//div[@style='display: block;']")).isDisplayed();
        driver.findElement(By.xpath("//div[@style='display: block;']")).click();
    }

}
