FROM openjdk:18-jdk-slim
WORKDIR /app
COPY target/Docker-Jenkins-Integration.jar /app/Docker-Jenkins-Integration.jar
ENTRYPOINT ["java", "-jar", "Docker-Jenkins-Integration.jar"]