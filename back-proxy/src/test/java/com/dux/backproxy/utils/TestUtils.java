package com.dux.backproxy.utils;

import com.dux.backproxy.client.security.dto.User;
import com.dux.backproxy.client.teams.dto.Team;
import com.dux.backproxy.dto.response.BackProxyResponse;

import com.dux.backproxy.exception.BackProxyException;
import com.dux.backproxy.exception.BackProxyGenericException;
import com.dux.backproxy.exception.errormanager.ErrorResponse;
import com.dux.backproxy.exception.errormanager.TranslateErrorResponse;
import feign.Request;
import feign.RequestTemplate;
import feign.RetryableException;

import java.util.HashMap;

public class TestUtils {

  public static final String TOKEN = "TOKEN";
  public static final String STRING = "STRING";
  public static final String ERROR_MESSAGE = "ERROR_MESSAGE";
  public static final Integer STATUS_OK = 200;
  public static final Integer ERROR_CODE = 0;
  public static final Integer HTTP_STATUS_BAD_REQUEST = 400;


  public static BackProxyException buildBackProxyException() {
    return new BackProxyGenericException(ERROR_MESSAGE, ERROR_CODE);
  }

  public static TranslateErrorResponse buildTranslateErrorResponse() {
    return TranslateErrorResponse.builder().status(STRING).code(STRING).severity(STRING).type(STRING)
        .description(STRING).detail(STRING).build();
  }

  public static ErrorResponse buildErrorResponse(TranslateErrorResponse response) {
    return ErrorResponse.builder()
        .status(response.getStatus())
        .code(response.getCode())
        .subType(response.getSeverity())
        .type(response.getType())
        .message(response.getDescription())
        .detail(response.getDetail())
        .build();
  }

  public static ErrorResponse buildErrorResponseGeneric() {
    return ErrorResponse.builder()
        .status("ABORT")
        .code("400")
        .subType("ERROR")
        .type("WARNING")
        .message("")
        .build();
  }

  public static RetryableException buildRetryableException() {
    return new RetryableException(HTTP_STATUS_BAD_REQUEST, TestUtils.ERROR_MESSAGE, Request.HttpMethod.POST, null,
        buildRequest());
  }

  public static Request buildRequest() {
    return Request.create(Request.HttpMethod.POST, STRING, new HashMap<>(), Request.Body.create(STRING),
        new RequestTemplate());
  }








}
