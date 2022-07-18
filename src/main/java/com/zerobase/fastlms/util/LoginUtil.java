package com.zerobase.fastlms.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

public class LoginUtil {
    public static String getUserIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        return ip == null ? request.getRemoteAddr() : ip;
    }

    public static String getUserAgent(HttpServletRequest request){

        return request.getHeader("user-agent");
    }



}
