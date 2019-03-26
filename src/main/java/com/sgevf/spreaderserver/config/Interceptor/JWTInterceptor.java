package com.sgevf.spreaderserver.config.Interceptor;

import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
            if (checkToken(token)) {
                return true;
            } else {
                PrintWriter out = null;
                try {
                    JSONObject json = new JSONObject(new Response<>(HttpResponse.UNLOGIN, "未登录，请先登录", null));
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;charset=UTF-8");
                    out = response.getWriter();
                    out.append(json.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (out != null)
                        out.close();
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    /**
     * 检查token
     *
     * @return
     */
    private boolean checkToken(String token) {
        if (token == null)
            return false;
        String id = redisService.get(token, 1);
        return id != null;
    }


}
