package com.tua.wanchalerm.otp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PayloadConstant {
    PAYLOAD("payload"),
    DEVICE_ID("device_id"),
    CLIENT_ID("client_id");

    @Getter
    private final String key;

}
