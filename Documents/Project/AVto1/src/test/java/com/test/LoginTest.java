package com.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        // Налаштування драйвера і відкриття браузера
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Перехід на сайт
        driver.get("https://www.saucedemo.com/");

        // Ініціалізація Page Object
        loginPage = new LoginPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void verifyPageTitle() {
        String actualTitle = driver.getTitle();

        // Жорстка перевірка на повний збіг
        Assert.assertEquals(actualTitle, "Swag Labs", "Назва сторінки не співпадає!");

        // М’яка перевірка
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(actualTitle.contains("Swag Labs"), "Заголовок не містить 'Swag Labs'");
        soft.assertAll(); // обов’язково викликати!
    }

    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"}
        };
    }

    @Test(dataProvider = "loginData")
    public void testLoginWithMultipleCredentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        System.out.println("Введено логін: " + username + " | пароль: " + password);

        // поки що без кліку на кнопку
        // loginPage.clickLoginButton();
    }
}