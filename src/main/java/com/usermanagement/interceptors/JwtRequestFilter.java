package com.usermanagement.interceptors;

import com.usermanagement.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtRequestFilter implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
        在跨域请求（CORS）的情况下，浏览器首先发送一个 OPTIONS 请求作为预检（preflight），来确定实际请求是否安全可接受。
        这个 OPTIONS 请求通常不包含 Authorization 等自定义头信息。

        由于拦截器会在处理所有请求（包括 OPTIONS 请求）时尝试读取 Authorization 头，
        所以当它处理 OPTIONS 请求时，由于找不到这个头信息，可能会导致问题。解决这个问题的一种方法是在拦截器中添加逻辑，
        用于检测 OPTIONS 请求，并允许这些请求通过而不进行 JWT 验证。
         */

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true; // 直接放行 OPTIONS 请求
        }

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        if (username != null && jwtUtil.validateToken(jwt)) {
            // JWT 验证通过，继续请求处理
            return true;
        } else {
            // JWT 验证失败，返回错误响应
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return false;
        }
    }
}

