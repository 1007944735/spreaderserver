package com.sgevf.spreaderserver.config.Interceptor;

import com.sgevf.spreaderserver.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if ("GET".equals(request.getMethod())) {
            return true;
        } else if ("POST".equals(request.getMethod())) {
            String token = request.getParameter("token");
            return checkToken(token);
        }
        return false;
    }

    /**
     * 检查token
     *
     * @return
     */
    private boolean checkToken(String token) {
        if (token == null)
            return false;
        String id = redisService.get(token,1);
        return id != null;
    }
}
