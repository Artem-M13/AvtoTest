package com.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginPageElements() {
              // Перевірка елементів логіну
        try {
            WebElement usernameField = driver.findElement(By.id("user-name"));
            System.out.println("Поле логіну знайдено.");

            WebElement passwordField = driver.findElement(By.id("password"));
            System.out.println("Поле паролю знайдено.");

            WebElement loginButton = driver.findElement(By.id("login-button"));
            System.out.println("Кнопка логіну знайдена.");
        } catch (Exception e) {
            System.out.println("Помилка при пошуку елементів: " + e.getMessage());
        }
                // Перевірка заголовка сторінки
        String pageTitle = driver.getTitle();
        if (pageTitle.contains("Swag Labs")) {
            System.out.println("Заголовок сторінки містить 'Swag Labs'.");
        } else {
            System.out.println("Заголовок сторінки НЕ містить 'Swag Labs'. Заголовок: " + pageTitle);
        }
            try {
                // Перевірка елемента за ID
                WebElement usernameField = driver.findElement(By.id("user-name"));
                System.out.println("Елемент з ID 'user-name' знайдено.");

                // Перевірка елемента за ClassName
                WebElement loginBox = driver.findElement(By.className("login-box"));
                System.out.println("Елемент з ClassName 'login-box' знайдено.");

                // Перевірка елемента за XPath
                WebElement loginButton = driver.findElement(By.xpath("//input[@id='login-button']"));
                System.out.println("Елемент з XPath '//input[@id='login-button']' знайдено.");

                // Перевірка елемента за CSS Selector
                WebElement passwordField = driver.findElement(By.cssSelector("input[data-test='password']"));
                System.out.println("Елемент з CSS Selector 'input[data-test=\"password\"]' знайдено.");

            } catch (Exception e) {
                System.out.println("Помилка при пошуку елементів: " + e.getMessage());
            }
        }
    }