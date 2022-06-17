package com.example.warproject.handler;

import com.example.warproject.ErrorCode;
import net.minidev.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//서버에 요청을 할 때 액섹스가 가능한지 권한을 체크후 액세스 할 수 없는 요청을 했을시 동작된다.
@Component
public class WebAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorCode errorCode = ErrorCode.ForbiddenException;
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject json=new JSONObject();
        json.put("code",errorCode.getCode());
        json.put("message",errorCode.getMessage());
        response.getWriter().print(json);
    }
}