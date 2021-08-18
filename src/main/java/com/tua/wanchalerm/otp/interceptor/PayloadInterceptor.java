package com.tua.wanchalerm.otp.interceptor;

import com.tua.wanchalerm.otp.constant.PayloadConstant;
import com.tua.wanchalerm.otp.model.Payload;
import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PayloadInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         val payload = new Payload();
         payload.setDeviceId(request.getHeader(PayloadConstant.DEVICE_ID.getKey()));
         payload.setClientId(request.getHeader(PayloadConstant.CLIENT_ID.getKey()));

         request.setAttribute(PayloadConstant.PAYLOAD.getKey(), payload);
        return super.preHandle(request, response, handler);
    }
}
