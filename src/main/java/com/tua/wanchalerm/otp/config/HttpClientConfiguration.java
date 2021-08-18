package com.tua.wanchalerm.otp.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@Data
public class HttpClientConfiguration {
    @Value("${rest.client.read.timeout}")
    private int readTimeout;

    @Value("${rest.client.max.per.route}")
    private int maxPerRoute;

    @Value("${rest.client.max.total.connection}")
    private int maxTotalConnection;

    @Value("${otp.host}")
    private String otpHost;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        restTemplate.setMessageConverters(getForwarderConverter());
        return restTemplate;
    }

    @Bean
    public HttpHeaders httpHeaders() {
        return new HttpHeaders();
    }

    @Bean
    public ClientHttpRequestFactory getClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(getHttpClient());
        factory.setConnectTimeout(readTimeout);
        factory.setReadTimeout(readTimeout);
        return factory;
    }

    @Bean
    public CloseableHttpClient getHttpClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(maxPerRoute);
        connectionManager.setMaxTotal(maxTotalConnection);
        return HttpClients.custom().setConnectionManager(connectionManager).build();
    }

    public List getForwarderConverter() {
        List<HttpMessageConverter<Object>> converterList = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        objectMapper.enable(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS);
        objectMapper.enable(DeserializationFeature.USE_LONG_FOR_INTS);
        converter.setObjectMapper(objectMapper);
        converterList.add(converter);
        return converterList;
    }
}
