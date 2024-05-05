FROM openjdk:18-jdk-slim
WORKDIR /app
COPY target/sapientapp-0.0.1-SNAPSHOT.jar /app/sapientapp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "sapientapp-0.0.1-SNAPSHOT.jar"]