package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import pages.EmailPage;

public class BaseTest {

    protected WebDriver driver;
    protected EmailPage emailPage;

    @BeforeClass
    public void setup() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        emailPage = new EmailPage(driver);

        driver.get("https://mail.google.com/");

        System.out.println("Gmail opened.");

        System.out.println(
                "Please login manually before test execution."
        );
    }

    @AfterClass
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}