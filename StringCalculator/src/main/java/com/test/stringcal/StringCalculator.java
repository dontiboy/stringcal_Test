package com.test.stringcal;

public class StringCalculator {

    public static final String NEGATIVE_NUMBERS_ARE_NOT_ALLOWED_MESSAGE = "Negative Numbers are not allowed :";
    public static final String SPACE = " ";
    public static final int THOUSAND_NUMBER = 1000;
    private DelimiterHandler delimiterHandler;

    public StringCalculator() {
        delimiterHandler = new DelimiterHandler();
    }

    public int add(String str) {
        String delimiters = delimiterHandler.getDelimiters(str);
        String numberString = delimiterHandler.getNumberString(str);
        return calculateSum(getNumbers(numberString, delimiters));
    }

    private String[] getNumbers(String str, String delimiter) {
        if (str.isEmpty()) {
            return new String[0];
        } else {
            return str.split(delimiter);
        }
    }

    private int calculateSum(String[] numberStrings) {
        int sum = 0;
        StringBuilder errorBuilder = new StringBuilder();
        for (String str : numberStrings) {
            sum = sum + convertStringToInt(str, errorBuilder);
        }
        throwIfErrorForNegativeNumbers(errorBuilder);
        return sum;
    }

    private void throwIfErrorForNegativeNumbers(StringBuilder errorBuilder) {
        if (errorBuilder.length() > 0)
            throw new RuntimeException(NEGATIVE_NUMBERS_ARE_NOT_ALLOWED_MESSAGE + errorBuilder.toString());
    }

    private int convertStringToInt(String str, StringBuilder errorBuilder) {
        int num = Integer.parseInt(str);
        if (num < 0) {
            errorBuilder.append(SPACE).append(num);
        }
        return ignoreNumsMorethan1000(num);
    }

    private int ignoreNumsMorethan1000(int num) {
        if (num > THOUSAND_NUMBER) {
            return 0;
        } else {
            return num;
        }
    }
}
