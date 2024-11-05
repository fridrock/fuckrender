FROM openjdk:21-jdk

WORKDIR /app

COPY . /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/build/prac5-0.0.1-SNAPSHOT.jar"]