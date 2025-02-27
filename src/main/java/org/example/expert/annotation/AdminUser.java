package org.example.expert.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @AdminUser 애노테이션이 붙은 메서드만 호출전에 어드민 사용자 여부 확인.
@Target(ElementType.METHOD) // 메서드에만 적용
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminUser {
}
