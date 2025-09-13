package com.test;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Epic("E2E Web UI")
@Feature("Авторизація (SauceDemo)")
class LoginTests extends BaseTest  {

    private LoginPage loginPage;

    @BeforeMethod
    public void openApp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    // ⬇️ НЕ потрібно, якщо в BaseTest уже є @AfterMethod із quit()
    // @AfterMethod(alwaysRun = true)
    // public void closeApp() { if (driver != null) driver.quit(); }

    @Test(description = "Перевірка заголовку сторінки")
    @Story("Відкриття сторінки логіну")
    @Severity(SeverityLevel.NORMAL)
    @Description("Перевіряємо, що title дорівнює 'Swag Labs' і містить цей текст.")
    public void verifyPageTitle() {
        String title = driver.getTitle();
        Assert.assertEquals(title, "Swag Labs", "Назва сторінки не співпадає!");
        Assert.assertTrue(title.contains("Swag Labs"), "Заголовок не містить 'Swag Labs'");

        SoftAssert sa = new SoftAssert();
        sa.assertTrue(driver.getPageSource().contains("Username"), "Немає тексту 'Username'");
        sa.assertTrue(driver.getPageSource().contains("Password"), "Немає тексту 'Password'");
        sa.assertAll();
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

    @Test(dataProvider = "loginData",
            description = "Ввід логіну/паролю та клік Login (без перевірки авторизації)")
    @Story("Ввід облікових даних")
    @Severity(SeverityLevel.MINOR)
    @Description("Для кожної пари вводимо логін/пароль та натискаємо 'Login'.")
    public void testLoginWithMultipleCredentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        System.out.println("Введено логін: " + username + " | пароль: " + password);
        loginPage.clickLogin(); // переконайся, що саме така назва методу у LoginPage
    }
}