FROM openjdk:17-jdk-slim
WORKDIR /app
ARG JAR_FILE
COPY target/${JAR_FILE} /app/app.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "app.jar"]