package com.tua.wanchalerm.otp.service;

import com.tua.wanchalerm.otp.model.redis.OtpEntity;
import com.tua.wanchalerm.otp.repository.OtpRepository;
import lombok.val;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class OtpServiceTest {

    @InjectMocks
    private OtpService otpService;

    @Mock
    private OtpRepository otpRepository;

    @Test
    public void createOtp() {

        val otpEntity = new OtpEntity();
        otpEntity.setRefId("refId");
        otpEntity.setOtp("otp");
        otpEntity.setClientId("client_id");
        otpEntity.setDeviceId("device_id");
        otpEntity.setSentTo("send_to");

        when(otpRepository.save(any())).thenReturn(otpEntity);

        val response = otpService.createOtp("client_id", "device_id", "send_to");

        assertNotNull(response);
        assertEquals("refId", response.getRefId());
        assertEquals("otp", response.getOtp());
        assertEquals("client_id", response.getClientId());
        assertEquals("device_id", response.getDeviceId());
        assertEquals("send_to", response.getSentTo());

        verify(otpRepository, times(1)).save(any());

    }
}