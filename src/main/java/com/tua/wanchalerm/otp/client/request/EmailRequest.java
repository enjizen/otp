package com.tua.wanchalerm.otp.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class EmailRequest {
    @JsonProperty("to")
    private String[] to;

    @JsonProperty("cc")
    private String[] cc;

    @JsonProperty("bcc")
    private String[] bcc;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("content")
    private Map<String , String> content;
}
