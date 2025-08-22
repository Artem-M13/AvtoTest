package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    // Елементи сторінки
    @FindBy(xpath = "//input[@name=\"user-name\"]")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@name=\"password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@name=\"login-button\"]")
    private WebElement loginButton;

    // повідомлення про помилку
    @FindBy(css = "[data-test='error']")
    private WebElement errorBanner;

    // Конструктор
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // Заповнити поле логіну
public void enterUsername(String username){
    passwordField.clear();
    passwordField.sendKeys(username);
    }
    // Заповнити поле паролю
    public void enterPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    // Натиснути кнопку "Login"
    public void clickLoginButton(){
        loginButton.click();
    }
    // Комбінований метод для логіну
    public void Login(String username,String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();

    }
}

