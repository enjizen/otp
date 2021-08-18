package com.tua.wanchalerm.otp.factory;

import com.tua.wanchalerm.otp.controller.response.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.tua.wanchalerm.otp.constant.Response.SUCCESS;


@Component
public class ResponseFactory {

    public ResponseEntity success() {
        GeneralResponse<Object> responseObject = new GeneralResponse();
        responseObject.setCode(SUCCESS.getCode());
        return ResponseEntity.ok(responseObject);
    }

    public ResponseEntity success(Object data, Class clazz) {
        GeneralResponse<Object> responseObject = new GeneralResponse();
        responseObject.setCode(SUCCESS.getCode());
        responseObject.setData(clazz.cast(data));
        return ResponseEntity.ok(responseObject);
    }

    public ResponseEntity error(HttpStatus httpStatus, String errorCode) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        responseObject.setCode(errorCode);
        return new ResponseEntity(responseObject, httpStatus);
    }

    public ResponseEntity error(HttpStatus httpStatus, String errorCode, Object data, Class clazz) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        responseObject.setCode(errorCode);
        responseObject.setData(clazz.cast(data));
        return new ResponseEntity(responseObject, httpStatus);

    }

}
