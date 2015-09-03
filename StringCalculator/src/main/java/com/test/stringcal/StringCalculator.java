package com.test.stringcal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public static final int REGEX_GROUP_NUMBER_STRING = 5;
    public static final int REGEX_NUMBER_OF_USER_DEFINED_SINGLE_CHARACTER_DELIMITERS = 2;
    public static final String PREDEFINED_REGEX = ",|\n";
    public static final String _USER_DEFINED_REGEX_PATTERN = "^//((.)|(\\[(.*)\\]))\n(.*)";
    public static final String NEGATIVE_NUMBERS_ARE_NOT_ALLOWED_MESSAGE = "Negative Numbers are not allowed :";
    public static final String SPACE = " ";
    public static final int THOUSAND_NUMBER = 1000;
    public static final String REGEX_OR_CONDITION = "|";
    public static final String REGEX_ALL_USER_DEFINIED_DELIMITERS_PATTERN = "\\[(.*?)\\]";
    public static final int REGEX_GROUP_NUMBER_OF_ALL_USER_DEFINED_DELIMITERS = 1;
    public static final int REGEX_GROUP_NUMBER_OF_ONE_USER_DEFINED_DELIMITER = 1;

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
            return getUserDefinedDelimter(matcher);
        } else {
            return PREDEFINED_REGEX;
        }
    }

    private String getUserDefinedDelimter(Matcher matcher) {
        if (hasUserDefinedSingleCharacterDelimiter(matcher)) {
            return getUserDefinedSingleCharacterDelimiter(matcher);
        }else{
            return getUserDefinedMultipleDelimiters(matcher);
        }
    }

    private String getUserDefinedMultipleDelimiters(Matcher matcher) {
        String delimiters = matcher.group(REGEX_GROUP_NUMBER_OF_ALL_USER_DEFINED_DELIMITERS);
        Matcher delimiterMatcher= Pattern.compile(REGEX_ALL_USER_DEFINIED_DELIMITERS_PATTERN).matcher(delimiters);
        StringBuilder allDemilitersBuilder= new StringBuilder();
        while(delimiterMatcher.find()){
            addOneUserDefinedDelimitersToAllDelimiters(allDemilitersBuilder, delimiterMatcher.group(REGEX_GROUP_NUMBER_OF_ONE_USER_DEFINED_DELIMITER));
        }
        return allDemilitersBuilder.toString();

    }

    private void addOneUserDefinedDelimitersToAllDelimiters(StringBuilder allDemilitersBuilder, String delimiter) {
        if(allDemilitersBuilder.length() == 0 ){
            allDemilitersBuilder.append(Pattern.quote(delimiter));
        }else{
            allDemilitersBuilder.append(REGEX_OR_CONDITION +Pattern.quote(delimiter));
        }
    }

    private String getUserDefinedSingleCharacterDelimiter(Matcher matcher) {
        return Pattern.quote(matcher.group(REGEX_NUMBER_OF_USER_DEFINED_SINGLE_CHARACTER_DELIMITERS));
    }

    private boolean hasUserDefinedSingleCharacterDelimiter(Matcher matcher) {
        return matcher.group(REGEX_NUMBER_OF_USER_DEFINED_SINGLE_CHARACTER_DELIMITERS) != null;
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
