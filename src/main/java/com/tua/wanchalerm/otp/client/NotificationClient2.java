package com.tua.wanchalerm.otp.client;

import com.tua.wanchalerm.otp.client.request.EmailRequest;
import com.tua.wanchalerm.otp.controller.response.GeneralResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "notification")
public interface NotificationClient2 {
    @PostMapping("/notification/v1/email?template=OTP")
    GeneralResponse sendOtpViaEmail(@RequestBody EmailRequest emailRequest);
}
