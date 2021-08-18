package com.tua.wanchalerm.otp.service;

import com.tua.wanchalerm.otp.model.redis.OtpEntity;
import com.tua.wanchalerm.otp.repository.OtpRepository;
import com.tua.wanchalerm.otp.util.GenerateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OtpService {

    private final OtpRepository otpRepository;

    public OtpEntity createOtp(String clientId, String deviceId, String sendTo) {
        val refId = GenerateUtil.generateAlphabeticString(4);
        val otp = GenerateUtil.generateNumber(6);

        val otpEntity = new OtpEntity();
        otpEntity.setRefId(refId);
        otpEntity.setOtp(otp);
        otpEntity.setClientId(clientId);
        otpEntity.setDeviceId(deviceId);
        otpEntity.setSentTo(sendTo);

        return otpRepository.save(otpEntity);
    }

}
