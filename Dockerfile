# 베이스 이미지 설정
FROM eclipse-temurin:17

# JAR 파일 이름을 빌드 시 설정 가능하게 처리
ARG JAR_FILE=build/libs/*.jar

# JAR 파일을 컨테이너 내부로 복사
COPY ${JAR_FILE} app.jar

# 컨테이너 실행 명령어 설정
ENTRYPOINT ["java","-jar","/app.jar"]

