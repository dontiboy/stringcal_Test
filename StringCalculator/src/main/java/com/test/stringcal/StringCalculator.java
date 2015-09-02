package com.test.stringcal;

public class StringCalculator {

    public int add(String str) {

        if (str.isEmpty()) {
            return 0;
        } else if (str.contains(",")) {
            String[] numberStrings = str.split(",");
            return convertStringToInt(numberStrings[0]) + convertStringToInt(numberStrings[1]);
        } else {
            return convertStringToInt(str);
        }
    }

    private int convertStringToInt(String str) {
        return Integer.parseInt(str);
    }
}
