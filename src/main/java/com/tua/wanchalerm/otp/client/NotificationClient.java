package com.tua.wanchalerm.otp.client;

import com.tua.wanchalerm.otp.client.request.EmailRequest;
import com.tua.wanchalerm.otp.config.HttpClientConfiguration;
import com.tua.wanchalerm.otp.controller.response.GeneralResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationClient {
    private final RestTemplate restTemplate;
    private final HttpClientConfiguration httpClientConfiguration;

    public GeneralResponse sendEmailOtp(EmailRequest emailRequest) {
        log.info("======= Start sent otp to email =========");
        val url = "/notification/v1/email?template=OTP";
        val requestHeader = new HttpHeaders();
        requestHeader.setContentType(MediaType.APPLICATION_JSON);

        val httpEntity = new HttpEntity<>(emailRequest, requestHeader);

        val endpoint = httpClientConfiguration.getOtpHost().concat(url);

        val response = restTemplate.exchange(endpoint,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<GeneralResponse>() {});


        log.info("======= End sent otp to email =========");
        return response.getBody();
    }
}
