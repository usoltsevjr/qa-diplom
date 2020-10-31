package ru.netology.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.val;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.data.DataHelper.*;


public class ApiTest {
    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Test
    void shouldGiveResponseForValidApprovedDebitCard() {
        val cardInformationForApi = getApprovedCardForApi();
        val response = given()
                .spec(requestSpec)
                .body(cardInformationForApi)

                .when()
                .post("/api/v1/pay")

                .then()
                .statusCode(200)
                .extract().response().asString();
        assertTrue(response.contains("APPROVED"));
    }

    @Test
    void shouldGiveResponseForValidDeclinedDebitCard() {
        val cardInformationForApi = getDeclinedCardForApi();
        val response = given()
                .spec(requestSpec)
                .body(cardInformationForApi)

                .when()
                .post("/api/v1/pay")

                .then()
                .statusCode(200)
                .extract().response().asString();
        assertTrue(response.contains("DECLINED"));
    }

    @Test
    void shouldGiveResponseForValidApprovedCreditCard() {
        val cardInformationForApi = getApprovedCardForApi();
        val response = given()
                .spec(requestSpec)
                .body(cardInformationForApi)

                .when()
                .post("/api/v1/credit")

                .then()
                .statusCode(200)
                .extract().response().asString();
        assertTrue(response.contains("APPROVED"));
    }

    @Test
    void shouldGiveResponseForValidDeclinedCreditCard() {
        val cardInformationForApi = getDeclinedCardForApi();
        val response = given()
                .spec(requestSpec)
                .body(cardInformationForApi)

                .when()
                .post("/api/v1/credit")

                .then()
                .statusCode(200)
                .extract().response().asString();
        assertTrue(response.contains("DECLINED"));
    }
}