package com.tua.wanchalerm.otp.controller;

import com.tua.wanchalerm.otp.constant.Channel;
import com.tua.wanchalerm.otp.controller.request.OtpCreateRequest;
import com.tua.wanchalerm.otp.controller.response.GeneralResponse;
import com.tua.wanchalerm.otp.factory.ResponseFactory;
import com.tua.wanchalerm.otp.model.Payload;
import com.tua.wanchalerm.otp.model.redis.OtpEntity;
import com.tua.wanchalerm.otp.service.EmailService;
import com.tua.wanchalerm.otp.service.OtpService;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.text.NumberFormat;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OtpControllerTest {

    @InjectMocks
    private OtpController otpController;

    @Mock
    private OtpService otpService;

    @Mock
    private EmailService emailService;

    @Mock
    private ResponseFactory responseFactory;

    private Payload payload;

    @Before
    public void setup() {
        payload = new Payload();
        payload.setClientId("client_id");
        payload.setDeviceId("device_id");

//        when(responseFactory.success()).thenCallRealMethod();
       // when(responseFactory.success(ArgumentMatchers.any(), ArgumentMatchers.any())).thenCallRealMethod();

    }

    @Test
    public void createOtp_SendToIsBlank_ReturnBadRequest() {
        val request = new OtpCreateRequest();

        when(responseFactory.error(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenCallRealMethod();

        val response = otpController.createOtp(payload, Channel.EMAIL, request);
        GeneralResponse<Object> result = (GeneralResponse<Object>) response.getBody();

        assertNotNull(response);
        assertNotNull(result);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("invalid_request", result.getCode());

        verify(otpService, never()).createOtp(anyString(), anyString(), anyString());
        verify(emailService, never()).sendEmail(anyString(), anyMap());
    }

    @Test
    public void createOtp_Success() {
        val request = new OtpCreateRequest();
        request.setSendTo("email@email.com");

        val otpEntity = new OtpEntity();
        otpEntity.setRefId("IURO");
        otpEntity.setClientId(payload.getClientId());
        otpEntity.setDeviceId(payload.getDeviceId());
        otpEntity.setOtp("234542");


        when(otpService.createOtp(anyString(), anyString(), anyString())).thenReturn(otpEntity);

        when(responseFactory.success())
                .thenCallRealMethod();

        val response = otpController.createOtp(payload, Channel.EMAIL, request);
        GeneralResponse<Object> result = (GeneralResponse<Object>) response.getBody();

        assertNotNull(response);
        assertNotNull(result);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", result.getCode());

        verify(otpService, times(1)).createOtp(anyString(), anyString(), anyString());
        verify(emailService, times(1)).sendEmail(anyString(), anyMap());
    }

    @Test
    public void testNumber() {
        double number = 2000D;

        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("th", "TH"));
        String numberCurrency = format.format(number);
        System.out.println("Current = ".concat(numberCurrency));


    }
}