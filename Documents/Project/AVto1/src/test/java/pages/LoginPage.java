package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    // Локатори
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorBanner = By.cssSelector("[data-test='error']");

    // Конструктор
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Заповнити поле логіну
    public void enterUsername(String username) {
        WebElement usernameInput = driver.findElement(usernameField);
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    // Заповнити поле паролю
    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(passwordField);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    // Натиснути кнопку "Login"
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Отримати повідомлення про помилку
    public String getErrorMessage() {
        return driver.findElement(errorBanner).getText();
    }
}