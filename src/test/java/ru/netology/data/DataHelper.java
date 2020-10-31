package ru.netology.data;

import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor
public class DataHelper {

        @Value
        public static class Number {
            String Number;
        }

        public static Number getValidApprovedCard() {
            return new Number("4444444444444441");
        }

        public static Number getValidDeclinedCard() {
            return new Number("4444444444444442");
        }

        public static Number getEmptyCard() {
            return new Number("");
        }

        public static Number getInvalidCardWith15Numbers() {
            return new Number("444444444444444");
        }

        public static Number getAnotherBankCard() {
            return new Number("2904444444444444");
        }

        @Value
        public static class Month {
            String Month;
        }

        public static Month getValidMonth() {
            return new Month("10");
        }

        public static Month getEmptyMonth() {
            return new Month("");
        }

        public static Month getInvalidFormatMonthIsZero() {
            return new Month("00");
        }

        public static Month getInvalidFormatMonthIsIncorrect() {
            return new Month("15");
        }

        public static Month getInvalidFormatMonthIsOneNumber() {
            return new Month("7");
        }

        @Value
        public static class Year {
            String Year;
        }

        public static Year getValidYear() {
            return new Year("22");
        }

        public static Year getEmptyYear() {
            return new Year("");
        }

        public static Year getEarlyYear() {
            return new Year("18");
        }

        public static Year getFutureYear() {
            return new Year("30");
        }

        @Value
        public static class Owner {
            String Owner;
        }

        public static Owner getValidOwner() {
            return new Owner("Antonov Anton");
        }

        public static Owner getEmptyOwner() {
            return new Owner("");
        }

        public static Owner getInvalidOwnerUsingOneWord() {
            return new Owner("Vladimir");
        }

        public static Owner getInvalidOwnerWithThreeWords() {
            return new Owner("Antonov Anton Anton");
        }

        public static Owner getInvalidOwnerWithLowerCase() {
            return new Owner("antonov anton");
        }

        public static Owner getInvalidOwnerWithUpperCase() {
            return new Owner("ANTONOV ANTON");
        }

        public static Owner getInvalidOwnerRu() {
            return new Owner("Антонов Антон");
        }

        public static Owner getInvalidOwnerWithNumbers() {
            return new Owner("12468");
        }

        public static Owner getInvalidOwnerWithSymbols() {
            return new Owner("~@#$%");
        }

        @Value
        public static class Cvv {
            String Cvv;
        }

        public static Cvv getValidCVV() {
            return new Cvv("101");
        }

        public static Cvv getEmptyCVV() {
            return new Cvv("");
        }

        public static Cvv getInvalidCVVWith1Number() {
            return new Cvv("1");
        }

        public static Cvv getInvalidCVVWith2Numbers() {
            return new Cvv("11");
        }

        @Value
        public static class CardInformationForAPI {
            String holder;
            String month;
            String number;
            String year;
            String cvv;
        }

        public static CardInformationForApi getValidApprovedCardForApi() {
            return new CardInformationForApi("Petrov Alexandr", "10", "4444444444444441", "22", "101");
        }

        public static CardInformationForApi getValidDeclinedCardForApi() {
            return new CardInformationForApi("Petrov Alexandr", "10", "4444444444444442", "22", "101");
        }
}