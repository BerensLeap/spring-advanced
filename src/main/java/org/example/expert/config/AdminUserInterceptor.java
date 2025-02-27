package org.example.expert.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.expert.annotation.AdminUser;
import org.example.expert.domain.user.enums.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Component
public class AdminUserInterceptor implements HandlerInterceptor {

    //로깅 객체 생성
    private static final Logger logger = LoggerFactory.getLogger(AdminUserInterceptor.class);

    // preHandle을 통과해서 인증 성공 => 요청 시각과 URL 로깅
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception{

        // @AdminUser 애노테이션이 붙어있는지 확인전, 핸들러에서 메서드 추출.
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            // @AdminUser 애노테이션 체크
            if (method.isAnnotationPresent(AdminUser.class)) {

                // 애노테이션이 있다면 인증 확인
                if (!isAuthenticated(request)) {
                    logger.warn("인증되지 않은 사용자 접근 시도: {}, 접근 시간: {}",request.getRequestURI(), LocalDateTime.now());
                    response.sendError(HttpServletResponse.SC_FORBIDDEN,"인증되지 않은 사용자입니다.");
                    return false;
                }
            }

        }
        return true;
    }


    // 요청 이후 추가적인 로깅,
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("접근 시도 URL: {}, 접근 시간: {}", request.getRequestURI(), LocalDateTime.now());
    }

    // 요청 완료 후 예외처리, 완료 메세지.
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        // 요청 처리 후 예외가 발생했을 경우에 로그 기록
        if (ex != null) {
            logger.error("요청 처리 중 예외 발생, URI: " + request.getRequestURI(), ex);
        }

        // 정상 완료인 경우
        logger.info("요청 완료된 URL: {} 완료 시간: {}", request.getRequestURI(), LocalDateTime.now());
    }



    private boolean isAuthenticated(HttpServletRequest request) {
        UserRole userRole = (UserRole)request.getAttribute("userRole");
        //인증이 없거나, 어드민 권한이 아닌 경우
        return userRole != null && UserRole.ADMIN.equals(userRole);
    }
}
