package ru.netology.test;


import lombok.val;
import org.junit.jupiter.api.Test;

import static ru.netology.data.RestApiHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.data.DataHelper.*;


public class ApiTest {

    @Test
    void shouldGiveResponseForValidApprovedDebitCard() {
        val validApprovedCardForApi = getValidApprovedCard();
        val response = fillPaymentFormWithDebitCardData(validApprovedCardForApi);
        assertTrue(response.contains("APPROVED"));
    }

    @Test
    void shouldGiveResponseForValidDeclinedDebitCard() {
        val validDeclinedCardForApi = getValidDeclinedCard();
        val response = fillPaymentFormWithDebitCardData(validDeclinedCardForApi);
        assertTrue(response.contains("DECLINED"));
    }

    @Test
    void shouldGiveResponseForValidApprovedCreditCard() {
        val validApprovedCardForApi = getValidApprovedCard();
        val response = fillPaymentFormWithCreditCardData(validApprovedCardForApi);
        assertTrue(response.contains("APPROVED"));
    }

    @Test
    void shouldGiveResponseForValidDeclinedCreditCard() {
        val validDeclinedCardForApi = getValidDeclinedCard();
        val response = fillPaymentFormWithCreditCardData(validDeclinedCardForApi);
        assertTrue(response.contains("DECLINED"));
    }
}