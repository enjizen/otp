package com.tua.wanchalerm.otp.model.redis;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash("otp")
@Data
public class OtpEntity {
    @Id
    private String refId;
    private String clientId;
    private String deviceId;
    private String otp;
    private String sentTo;
    @TimeToLive
    private Long timeToLive;
}
