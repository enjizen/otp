package com.tua.wanchalerm.otp.service;

import com.tua.wanchalerm.otp.client.NotificationClient;
import com.tua.wanchalerm.otp.client.NotificationClient2;
import com.tua.wanchalerm.otp.client.request.EmailRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    //private final NotificationClient notificationClient;
    private final NotificationClient2 notificationClient;

    @Async("taskExecutor")
    public void sendEmail(String emailAddress, Map<String, String> content) {
        val request = EmailRequest.builder()
                .to(new String[]{emailAddress})
                .subject("รหัสยืนยัน")
                .content(content)
                .build();
       // notificationClient.sendEmailOtp(request);
        notificationClient.sendOtpViaEmail(request);
    }

}
