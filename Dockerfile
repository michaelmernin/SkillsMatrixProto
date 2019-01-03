FROM java:8
WORKDIR /
ADD /build/libs/gs-spring-boot-docker-0.1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
