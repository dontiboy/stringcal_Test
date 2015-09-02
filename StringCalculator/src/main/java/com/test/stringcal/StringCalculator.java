package com.test.stringcal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private String DELIMITER = ",|\n";

    public int add(String str) {
        Matcher matcher = Pattern.compile("^//(.)\n(.*)").matcher(str);
        String numberString = str;
        if (matcher.find()) {
            DELIMITER = matcher.group(1);
            numberString = matcher.group(2);
        }
        return calculateSum(getNumbers(numberString));
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
