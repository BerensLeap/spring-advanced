## 1. 개발환경
  * JDK Temurin-17.0.5+8
  * Spring Framework Boot 3.3.3
  * Spring Web 6.1.12
  * Gradle 8.11.1
  * Bcrypt 0.10.2
  * h2 Database 2.2.2.224
  * Slf4j 2.23.1
  * Hiberante Validator 8.0.1


## 2. 요구사항

1. 코드 개선
2. 코드 리팩토링
3. validation을 통한 코드 개선
4. N+1 문제 해결
5. 테스트코드 작성
6. API로깅
7. 이외의 문제 정의와 해결
8. 테스트 커버리지

## 3. 주요 코드 설명 
@AdminUser 커스텀 애노테이션을 사용하여 lv4의 어드민 사용자만 접근할 수 있는 컨트롤러 메서드 기능 구현.
AdminUserInterceptor클래스에서 
애노테이션 여부 검증을 통해 changeUserRole 메서드와 deleteComment에 한정하여 작동.
이후 isAuthenticated 메서드를 통해 인증이 없거나, 어드민 권한이 아닌 경우 검증. 

## 4. 문제 사항
gradle, (gradle - wrapper) 문제로 인한 컴파일 오류 발생. 
intelliJ IDEA를 통해 Build and Run을 해보기도 하고, 
gradle 재설치를 해보았으나, 문제를 해결하지 못함. 



![image](https://github.com/user-attachments/assets/d38cc9ee-cc34-4026-92dc-3351cc1130de)
4번의 문제사항을 개인적으로 해결하느라, 시간이 부족해 lv5의 요구사항을 수행하지 못하였으나, lv6의 테스트 커버리지에 호기심이 생겨서 해봤습니다.
