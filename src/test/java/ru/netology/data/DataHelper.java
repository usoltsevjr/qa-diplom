package ru.netology.data;

import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor
public class DataHelper {

    @Value
    public static class CardInformation {
        String number;
        String month;
        String year;
        String holder;
        String cvv;
    }

        public static CardInformation getValidApprovedCard() {
            return new CardInformation("4444 4444 4444 4441", "10", "22", "Antonov Anton", "101");
        }

        public static CardInformation getValidDeclinedCard() {
            return new CardInformation("4444 4444 4444 4442", "10", "22", "Antonov Anton", "101");
        }

        public static CardInformation getEmptyCard() {
            return new CardInformation("", "10", "22", "Antonov Anton", "101");
        }

        public static CardInformation getInvalidCardWith15Numbers() {
            return new CardInformation("4444 4444 4444 444", "10", "22", "Antonov Anton", "101");
        }

        public static CardInformation getAnotherBankCard() {
            return new CardInformation("2904 4444 4444 4444", "10", "22", "Antonov Anton", "101");
        }


        public static CardInformation getEmptyMonth() {
            return new CardInformation("4444 4444 4444 4441", "", "22", "Antonov Anton", "101");
        }

        public static CardInformation getInvalidFormatMonthIsZero() {
            return new CardInformation("4444 4444 4444 4441", "00", "22", "Antonov Anton", "101");
        }

        public static CardInformation getInvalidFormatMonthIsIncorrect() {
            return new CardInformation("4444 4444 4444 4441", "15", "22", "Antonov Anton", "101");
        }

        public static CardInformation getInvalidFormatMonthIsOneNumber() {
            return new CardInformation("4444 4444 4444 4441", "7", "22", "Antonov Anton", "101");
        }

        public static CardInformation getEmptyYear() {
            return new CardInformation("4444 4444 4444 4441", "10", "", "Antonov Anton", "101");
        }

        public static CardInformation getEarlyYear() {
            return new CardInformation("4444 4444 4444 4441", "10", "18", "Antonov Anton", "101");
        }

        public static CardInformation getFutureYear() {
            return new CardInformation("4444 4444 4444 4441", "10", "30", "Antonov Anton", "101");
        }


        public static CardInformation getEmptyOwner() {
            return new CardInformation("4444 4444 4444 4441", "10", "22", "", "101");
        }

        public static CardInformation getInvalidOwnerUsingOneWord() {
            return new CardInformation("4444 4444 4444 4441", "10", "22", "Antonov", "101");
        }

        public static CardInformation getInvalidOwnerWithThreeWords() {
            return new CardInformation("4444 4444 4444 4441", "10", "22", "Antonov Anton Anton", "101");
        }

        public static CardInformation getInvalidOwnerWithLowerCase() {
            return new CardInformation("4444 4444 4444 4441", "10", "22", "antonov anton", "101");
        }

        public static CardInformation getInvalidOwnerWithUpperCase() {
            return new CardInformation("4444 4444 4444 4441", "10", "22", "ANTONOV ANTON", "101");
        }

        public static CardInformation getInvalidOwnerRu(){return new CardInformation("4444 4444 4444 4441", "10", "22", "Антонов Антон", "101");
        }

        public static CardInformation getInvalidOwnerWithNumbers() {
            return new CardInformation("4444 4444 4444 4441", "10", "22", "124575", "101");
        }

        public static CardInformation getInvalidOwnerWithSymbols() {
            return new CardInformation("4444 4444 4444 4441", "10", "22", "±!@$%^", "101");
        }

        public static CardInformation getEmptyCVV() {
            return new CardInformation("4444 4444 4444 4441", "10", "22", "Antonov Anton", "");
        }

        public static CardInformation getInvalidCVVWith1Number() {
            return new CardInformation("4444 4444 4444 4441", "10", "22", "Antonov Anton", "1");
        }

        public static CardInformation getInvalidCVVWith2Numbers() {
            return new CardInformation("4444 4444 4444 4441", "10", "22", "Antonov Anton", "11");
        }



        public static CardInformation getApprovedCard() {
            return new CardInformation("4444 4444 4444 4441","10", "22", "Petrov Alexandr", "101");
        }

        public static CardInformation getDeclinedCard() {
            return new CardInformation("4444 4444 4444 4442","10", "22", "Petrov Alexandr", "101");
        }
}