package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public EmailPage(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );
    }

    public void clickCompose() {

        WebElement compose = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[text()='Compose']")
                )
        );

        compose.click();
    }

    public void enterRecipient(String email) {

        WebElement to = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("to")
                )
        );

        to.sendKeys(email);

        to.sendKeys("\n");
    }

    public void enterSubject(String subject) {

        WebElement subjectBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("subjectbox")
                )
        );

        subjectBox.sendKeys(subject);
    }

    public void enterMessage(String message) {

        WebElement body = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@aria-label='Message Body']")
                )
        );

        body.sendKeys(message);
    }

    // Attach Resume
    public void attachResume(String resumePath) {

        WebElement fileInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//input[@type='file']")
                )
        );

        fileInput.sendKeys(resumePath);
    }

    public void clickSend() {

        WebElement send = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(
                            "//div[@role='button' and @aria-label='Send']"
                        )
                )
        );

        send.click();
    }

    // Send email with attachment
    public void sendEmail(
            String email,
            String subject,
            String message,
            String resumePath) {

        clickCompose();

        enterRecipient(email);

        enterSubject(subject);

        enterMessage(message);

        // Attach resume
        attachResume(resumePath);

        // Wait for upload
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Send
        clickSend();
    }
}