package com.mingyang.bootlaunch.common.internal;

import org.slf4j.MDC;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: ymy
 * @program: boot-lanuch
 * @description:
 * @date: 2022/6/4 20:12
 * @version: 1.0
 */
public class LogInterceptor implements AsyncHandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
        MDC.put("requestId", request.getHeader("requestId"));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,Object handler,Exception ex) throws Exception {
        MDC.clear();
    }
}
