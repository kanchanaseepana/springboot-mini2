FROM openjdk:11-alpine
EXPOSE 8085
ADD target/springboot-docker-one.jar springboot-docker-one.jar
ENTRYPOINT ["java", "-jar","/springboot-docker-one.jar"]
