package com.tua.wanchalerm.otp.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpCreateRequest {
    @JsonProperty("send_to")
    private String sendTo;
}
