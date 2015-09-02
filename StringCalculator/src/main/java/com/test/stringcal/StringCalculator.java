package com.test.stringcal;

public class StringCalculator {
    private static final String DELIMITER = ",|\n";

    public int add(String str) {
        return calculateSum(getNumbers(str));
    }

    private String[] getNumbers(String str) {
        if (str.isEmpty()) {
            return new String[0];
        } else {
            return str.split(DELIMITER);
        }
    }

    private int calculateSum(String[] numberStrings) {
        int sum = 0;
        for (String str : numberStrings) {
            sum = sum + convertStringToInt(str);
        }
        return sum;
    }

    private int convertStringToInt(String str) {
        return Integer.parseInt(str);
    }
}
