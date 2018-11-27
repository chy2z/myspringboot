package com.springboot.controller;


import com.springboot.model.RequestResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

/**
* @Title: GlobalExceptionHandler
* @Description: 全局异常 和 aop 冲突
* @author chy
* @date 2018/8/27 21:25
*/
@RestControllerAdvice
@EnableWebMvc
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RequestResult handleException(HttpServletRequest request, Exception ex) {
        RequestResult resp = new RequestResult();
        resp.setCode(300);
        resp.setSuccess(false);
        resp.setException("exception-Handler-handleException");
        return resp;
    }

    /**
     * 捕获异常
     * @return

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public RequestResult runtimeExceptionHandler(RuntimeException e, HttpServletResponse response) {
        RequestResult resp = new RequestResult();
        resp.setCode(300);
        resp.setSuccess(false);
        resp.setException("exception-Handler-RuntimeException");
        return resp;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public RequestResult nullPointerException(RuntimeException e, HttpServletResponse response) {
        RequestResult resp = new RequestResult();
        resp.setCode(300);
        resp.setSuccess(false);
        resp.setException("exception-Handler-NullPointerException");
        return resp;
    }
     */

}
