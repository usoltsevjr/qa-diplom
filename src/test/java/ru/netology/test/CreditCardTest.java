package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SQLHelper.*;

public class CreditCardTest {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
        mainPage.payWithCreditCard();
    }

    @AfterAll
    static void cleanDataBases() {
        SQLHelper.cleanDb();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @Test
    public void shouldSubmitIfApprovedCard() {
        val info = getValidApprovedCard();
        paymentPage.fillForm(info);
        paymentPage.successMessage();
        val paymentId = getPaymentId();
        val expectedAmount = "4500000";
        val actualAmount = getPaymentAmount(paymentId);
        val expectedStatus = "APPROVED";
        val actualStatus = getStatusForPaymentWithDebitCard(paymentId);
        assertEquals(expectedAmount, actualAmount);
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void shouldNotSubmitIfDeclinedCard() {
        val info = getValidDeclinedCard();
        paymentPage.fillForm(info);
        paymentPage.failMessage();
        val paymentId = getPaymentId();
        val expectedStatus = "DECLINED";
        val actualStatus = getStatusForPaymentWithDebitCard(paymentId);
        assertEquals(expectedStatus, actualStatus);
    }


    @Test
    void shouldNotSubmitIfFieldCardEmpty() {
        val info = getEmptyCard();
        paymentPage.fillForm(info);
        paymentPage.shouldFillMessage();
    }

    @Test
    void shouldNotSubmitIfCardNotFull() {
        val info = getInvalidCardWith15Numbers();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitIfAnotherBankCard() {
        val info = getAnotherBankCard();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }


    @Test
    void shouldNotSubmitIfEmptyMonth() {
        val info = getEmptyMonth();
        paymentPage.fillForm(info);
        paymentPage.shouldFillMessage();
    }

    @Test
    void shouldNotSubmitIfMonthIsZero() {
        val info = getInvalidFormatMonthIsZero();
        paymentPage.fillForm(info);
        paymentPage.wrongTermMessage();
    }

    @Test
    void shouldNotSubmitIfMonth1Number() {
        val info = getInvalidFormatMonthIsOneNumber();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitIfMonthIncorrect() {
        val info = getInvalidFormatMonthIsIncorrect();
        paymentPage.fillForm(info);
        paymentPage.wrongTermMessage();
    }

    @Test
    void shouldNotSubmitIfYearEmpty() {
        val info = getEmptyYear();
        paymentPage.fillForm(info);
        paymentPage.shouldFillMessage();
    }

    @Test
    void shouldNotSubmitIfYearEarly() {
        val info = getEarlyYear();
        paymentPage.fillForm(info);
        paymentPage.cardExpiredMessage();
    }

    @Test
    void shouldNotSubmitIfYearFuture() {
        val info = getFutureYear();
        paymentPage.fillForm(info);
        paymentPage.wrongTermMessage();
    }

    @Test
    void shouldNotSubmitWhenOwnerEmpty() {
        val info = getEmptyOwner();
        paymentPage.fillForm(info);
        paymentPage.shouldFillMessage();
    }

    @Test
    void shouldNotSubmitIfOwnerUsingOneWord() {
        val info = getInvalidOwnerUsingOneWord();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitIf3Words() {
        val info = getInvalidOwnerWithThreeWords();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitIfOwnerUsingLowerCase() {
        val info = getInvalidOwnerWithLowerCase();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitIfOwnerUsingUpperCase() {
        val info = getInvalidOwnerWithUpperCase();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitIfOwnerRu() {
        val info = getInvalidOwnerRu();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitOwnersWithNumbers() {
        val info = getInvalidOwnerWithNumbers();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitOwnersWithSymbols() {
        val info = getInvalidOwnerWithSymbols();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldSubmitIfСVVIsEmpty() {
        val info = getEmptyCVV();
        paymentPage.fillForm(info);
        paymentPage.shouldFillMessage();
    }

    @Test
    void shouldNotSubmitIfCVVOneNumber() {
        val info = getInvalidCVVWith1Number();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitIfCVVTwoNumbers() {
        val info = getInvalidCVVWith2Numbers();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }
}
