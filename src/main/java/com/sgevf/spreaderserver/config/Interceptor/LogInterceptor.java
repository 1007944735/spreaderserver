package com.sgevf.spreaderserver.config.Interceptor;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Component
public class LogInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if("GET".equals(request.getMethod())) {
            logger.info("parameters:{}", request.getQueryString());
        }else if("POST".equals(request.getMethod())){
            Enumeration<String> names=request.getParameterNames();
            JSONObject json=new JSONObject();
            while (names.hasMoreElements()){
                String n=names.nextElement();
                try {
                    json.put(n,request.getParameter(n));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            logger.info("parameters:{}", json.toString());
        }
        return true;
    }
}
