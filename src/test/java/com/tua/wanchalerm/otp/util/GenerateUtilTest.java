package com.tua.wanchalerm.otp.util;


import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GenerateUtilTest {

    @Test
    public void generateAlphabeticString() {
        val result = GenerateUtil.generateAlphabeticString(4);
        assertEquals(4, result.length());
    }

    @Test
    public void generateNumber() {
        val result = GenerateUtil.generateNumber(4);
        assertEquals(4, result.length());
    }
}