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

public class DebitCardTest {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
        mainPage.payWithDebitCard();
    }

    @AfterAll
    static void cleanDataBases() {
        SQLHelper.cleanDb();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @Nested
    public class FirstTests {
        private final DataHelper.Month month = getValidMonth();
        private final DataHelper.Year year = getValidYear();
        private final DataHelper.Owner owner = getValidOwner();
        private final DataHelper.Cvv cvv = getValidCVV();

        @Test
        public void shouldSubmitIfApprovedCard() {
            val number = getValidApprovedCard();
            paymentPage.fillForm(number, month, year, owner, cvv);
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
            val number = getValidDeclinedCard();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.failMessage();
            val paymentId = getPaymentId();
            val expectedStatus = "DECLINED";
            val actualStatus = getStatusForPaymentWithDebitCard(paymentId);
            assertEquals(expectedStatus, actualStatus);
        }
    }

    @Nested
    public class FieldNumberTests {
        private final DataHelper.Month month = getValidMonth();
        private final DataHelper.Year year = getValidYear();
        private final DataHelper.Owner owner = getValidOwner();
        private final DataHelper.Cvv cvv = getValidCVV();

        @Test
        void shouldNotSubmitIfFieldCardEmpty() {
            val number = getEmptyCard();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.shouldFillMessage();
        }

        @Test
        void shouldNotSubmitIfCardNotFull() {
            val number = getInvalidCardWith15Numbers();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }

        @Test
        void shouldNotSubmitIfAnotherBankCard() {
            val number = getAnotherBankCard();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }
    }

    @Nested
    public class FieldMonthTests {
        private final DataHelper.Number number = getValidApprovedCard();
        private final DataHelper.Year year = getValidYear();
        private final DataHelper.Owner owner = getValidOwner();
        private final DataHelper.Cvv cvv = getValidCVV();

        @Test
        void shouldNotSubmitIfEmptyMonth() {
            val month = getEmptyMonth();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.shouldFillMessage();
        }

        @Test
        void shouldNotSubmitIfMonthIsZero() {
            val month = getInvalidFormatMonthIsZero();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongTermMessage();
        }

        @Test
        void shouldNotSubmitIfMonth1Number() {
            val month = getInvalidFormatMonthIsOneNumber();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }

        @Test
        void shouldNotSubmitIfMonthIncorrect() {
            val month = getInvalidFormatMonthIsIncorrect();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongTermMessage();
        }
    }

    @Nested
    public class FieldYearTests {
        private final DataHelper.Number number = getValidApprovedCard();
        private final DataHelper.Month month = getValidMonth();
        private final DataHelper.Owner owner = getValidOwner();
        private final DataHelper.Cvv cvv = getValidCVV();

        @Test
        void shouldNotSubmitIfYearEmpty() {
            val year = getEmptyYear();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.shouldFillMessage();
        }

        @Test
        void shouldNotSubmitIfYearEarly() {
            val year = getEarlyYear();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.cardExpiredMessage();
        }

        @Test
        void shouldNotSubmitIfYearFuture() {
            val year = getFutureYear();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongTermMessage();
        }
    }

    @Nested
    public class FieldOwnerTests {
        private final DataHelper.Number number = getValidApprovedCard();
        private final DataHelper.Month month = getValidMonth();
        private final DataHelper.Year year = getValidYear();
        private final DataHelper.Cvv cvv = getValidCVV();

        @Test
        void shouldNotSubmitWhenOwnerEmpty() {
            val owner = getEmptyOwner();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.shouldFillMessage();
        }

        @Test
        void shouldNotSubmitIfOwnerUsingOneWord() {
            val owner = getInvalidOwnerUsingOneWord();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }

        @Test
        void shouldNotSubmitIf3Words() {
            val owner = getInvalidOwnerWithThreeWords();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }

        @Test
        void shouldNotSubmitIfOwnerUsingLowerCase() {
            val owner = getInvalidOwnerWithLowerCase();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }

        @Test
        void shouldNotSubmitIfOwnerUsingUpperCase() {
            val owner = getInvalidOwnerWithUpperCase();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }

        @Test
        void shouldNotSubmitIfOwnerRu() {
            val owner = getInvalidOwnerRu();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }

        @Test
        void shouldNotSubmitOwnersWithNumbers() {
            val owner = getInvalidOwnerWithNumbers();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }

        @Test
        void shouldNotSubmitOwnersWithSymbols() {
            val owner = getInvalidOwnerWithSymbols();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }
    }

    @Nested
    public class FieldCVVTests {
        private final DataHelper.Number number = getValidApprovedCard();
        private final DataHelper.Month month = getValidMonth();
        private final DataHelper.Year year = getValidYear();
        private final DataHelper.Owner owner = getValidOwner();

        @Test
        void shouldSubmitIfСVVIsEmpty() {
            val cvv = getEmptyCVV();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.shouldFillMessage();
        }

        @Test
        void shouldNotSubmitIfCVVOneNumber() {
            val cvv = getInvalidCVVWith1Number();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }

        @Test
        void shouldNotSubmitIfCVVTwoNumbers() {
            val cvv = getInvalidCVVWith2Numbers();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }
    }
}
