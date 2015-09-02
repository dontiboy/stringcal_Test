package com.test.stringcal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public static final int REGEX_GROUP_NUMBER_STRING = 2;
    public static final int REGEX_NUMBER_OF_USER_DEFINED_DELIMITERS = 1;
    public static final String PREDEFINED_REGEX = ",|\n";
    public static final String _USER_DEFINED_REGEX_PATTERN = "^//(.)\n(.*)";

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
            return matcher.group(REGEX_NUMBER_OF_USER_DEFINED_DELIMITERS);
        }else{
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
        for (String str : numberStrings) {
            sum = sum + convertStringToInt(str);
        }
        return sum;
    }

    private int convertStringToInt(String str) {
        return Integer.parseInt(str);
    }
}
