package com.mycom.myapp.aspect;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {
	
//	private final HttpSession session;
	@Pointcut(value="execution(* com.mycom.myapp.phone.service.*.*(..) )")
	private void logPointcut() { }
	
	@Before("logPointcut()")	
	public void logRepositoryMethodCall(JoinPoint joinPoint) {
//		String username = (String) session.getAttribute("username");
//		if( username == null ) return;
		String methodName = joinPoint.getSignature().getName(); //메소드 명
		log.info( methodName+"을 "+ LocalDateTime.now()+"에 호출했습니다.");
	}
	
}
