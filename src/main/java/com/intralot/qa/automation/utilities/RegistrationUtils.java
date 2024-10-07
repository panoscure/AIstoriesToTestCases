package com.intralot.qa.automation.utilities;

import java.util.Collections;

public class RegistrationUtils {

    public static String generateRandomUsername(String prefix) {
        return prefix + generateRandomNumber(10);
    }

    public static long generateRandomNumber(int len) {
        return (long) Math.floor(Math.random() * Double.parseDouble("8" + String.join("", Collections.nCopies(len - 1, "9"))) + Double.parseDouble("1" + String.join("", Collections.nCopies(len - 1, "0"))));
    }

}
