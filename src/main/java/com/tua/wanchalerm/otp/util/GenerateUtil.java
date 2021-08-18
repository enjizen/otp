package com.tua.wanchalerm.otp.util;

import lombok.val;
import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;

public class GenerateUtil {
    public static String generateAlphabeticString(int count) {
        return RandomStringUtils.randomAlphabetic(count);
    }

    public static String generateNumber(int count) {
        val result = new StringBuilder();
        val rand = new SecureRandom();
        for(int i = 0; i < count; i++) {
            result.append(rand.nextInt(10));
        }

        return result.toString();
    }
}
