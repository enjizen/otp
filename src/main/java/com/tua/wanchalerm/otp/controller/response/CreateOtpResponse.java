package com.tua.wanchalerm.otp.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOtpResponse {
    @JsonProperty("ref_code")
    private String refCode;
}
