package com.tua.wanchalerm.otp.service;

import com.tua.wanchalerm.otp.client.NotificationClient;
import com.tua.wanchalerm.otp.controller.response.GeneralResponse;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private NotificationClient notificationClient;

    @Test
    public void sendEmail() {

        val content = new HashMap<String, String>();
        content.put("otp", "123455");

        emailService.sendEmail("email", content);

        verify(notificationClient, times(1)).sendEmailOtp(any());
    }
}