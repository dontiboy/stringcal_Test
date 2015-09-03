package com.test.stringcal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public static final int REGEX_GROUP_NUMBER_STRING = 2;
    public static final int REGEX_NUMBER_OF_USER_DEFINED_DELIMITERS = 1;
    public static final String PREDEFINED_REGEX = ",|\n";
    public static final String _USER_DEFINED_REGEX_PATTERN = "^//(.)\n(.*)";
    public static final String NEGATIVE_NUMBERS_ARE_NOT_ALLOWED_MESSAGE = "Negative Numbers are not allowed :";
    public static final String SPACE = " ";

    public int add(String str) {

        Matcher matcher = Pattern.compile(_USER_DEFINED_REGEX_PATTERN).matcher(str);
        String delimiters = getDelimiters(matcher);
        String numberString = getNumberString(str, matcher.reset());
        return calculateSum(getNumbers(numberString, delimiters));
    }

    private String getNumberString(String str, Matcher matcher) {
        if (matcher.find()) {
            return matcher.group(REGEX_GROUP_NUMBER_STRING);
        } else {
            return str;
        }
    }

    private String getDelimiters(Matcher matcher) {
        if (matcher.find()) {
            return Pattern.quote(matcher.group(REGEX_NUMBER_OF_USER_DEFINED_DELIMITERS));
        } else {
            return PREDEFINED_REGEX;
        }
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
        return num;
    }
}
