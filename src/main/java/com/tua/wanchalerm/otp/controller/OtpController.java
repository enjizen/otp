package com.tua.wanchalerm.otp.controller;

import com.tua.wanchalerm.otp.constant.Channel;
import com.tua.wanchalerm.otp.constant.Response;
import com.tua.wanchalerm.otp.controller.request.OtpCreateRequest;
import com.tua.wanchalerm.otp.controller.response.CreateOtpResponse;
import com.tua.wanchalerm.otp.factory.ResponseFactory;
import com.tua.wanchalerm.otp.model.Payload;
import com.tua.wanchalerm.otp.service.EmailService;
import com.tua.wanchalerm.otp.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;

    private final EmailService emailService;

    private final ResponseFactory responseFactory;

    @PostMapping("/v1/create-otp")
    public ResponseEntity createOtp(@RequestAttribute Payload payload, @RequestParam(name = "channel") Channel channel, @RequestBody OtpCreateRequest request) {
        log.info("========== Start create otp =======");

        if (StringUtils.isBlank(request.getSendTo())) {
            log.info("Send to is blank");
            return responseFactory.error(HttpStatus.BAD_REQUEST, Response.INVALID_REQUEST.getCode());
        }

        val otpEntity = otpService.createOtp(payload.getClientId(), payload.getDeviceId(), request.getSendTo());

        if (Channel.EMAIL == channel) {
            val content = new HashMap<String, String>();
            content.put("ref_id", otpEntity.getRefId());
            content.put("otp", otpEntity.getOtp());
            emailService.sendEmail(request.getSendTo(), content);
        }

        val createOtpResponse = new CreateOtpResponse(otpEntity.getRefId());

        log.info("========== End create otp =======");
        return responseFactory.success(createOtpResponse, CreateOtpResponse.class);
    }
}
