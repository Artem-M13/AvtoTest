package com.test;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Epic("API Tests")
@Feature("Petstore: Pet")
public class ApiTests {

    @Test(description = "POST створює Pet, GET повертає той самий Pet")
    @Story("CRUD: Create & Read Pet")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Створюємо улюбленця (id=666, name=Barsik) через POST /pet та перевіряємо через GET /pet/666: статус 200 і name містить 'Barsik'.")
    public void getPetTest() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        String petJson = """
                {
                  "id": 5,
                  "name": "doggie",
                  "status": "available"
                }
                """;

        // POST – створення
        RestAssured.given()
                .contentType("application/json")
                .body(petJson)
                .when()
                .post("/pet")
                .then()
                .statusCode(200);

        // GET – перевірка
        Response response = RestAssured.given()
                .when()
                .get("/pet/5")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 200, "Статус код не 200!");
        Assert.assertTrue(response.asString().contains("doggie"), "Ім'я doggie не знайдено!");

        Allure.addAttachment("GET /pet/5 response", "application/json",
                response.asPrettyString(), ".json");
    }
}