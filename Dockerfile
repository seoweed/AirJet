# 베이스 이미지 설정
FROM eclipse-temurin:17

# JAR 파일 이름을 빌드 시 설정 가능하게 처리
ARG JAR_FILE=build/libs/*.jar

# Build-time ARG 정의
ARG DATABASE_USERNAME
ARG DATABASE_PASSWORD
ARG DATABASE_URL
ARG AWS_BUCKET_NAME
ARG AWS_REGION
ARG AWS_ACCESS_KEY_ID
ARG AWS_SECRET_ACCESS_KEY

# ARG를 런타임 ENV로 변환
ENV DATABASE_USERNAME=$DATABASE_USERNAME
ENV DATABASE_PASSWORD=$DATABASE_PASSWORD
ENV DATABASE_URL=$DATABASE_URL
ENV AWS_BUCKET_NAME=$AWS_BUCKET_NAME
ENV AWS_REGION=$AWS_REGION
ENV AWS_ACCESS_KEY_ID=$AWS_ACCESS_KEY_ID
ENV AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY

# JAR 파일을 컨테이너 내부로 복사
COPY ${JAR_FILE} app.jar

# 컨테이너 실행 명령어 설정
CMD ["java","-jar","/app.jar"]

