package com.tua.wanchalerm.otp.model;

import lombok.Data;

@Data
public class Payload {
    private String deviceId;
    private String clientId;
    private String ipAddress;
    private String os;
    private String model;
}
