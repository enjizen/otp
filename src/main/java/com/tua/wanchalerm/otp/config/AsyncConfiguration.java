package com.tua.wanchalerm.otp.config;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@Slf4j
@EnableAsync
public class AsyncConfiguration {
    @Bean("taskExecutor")
    public Executor taskExecutor() {
        log.info("Creating Async Task Executor");
        val executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(2000);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("EmailThread-");
        executor.initialize();
        return executor;
    }
}
