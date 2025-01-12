FROM amazoncorretto:20-alpine-jdk
COPY target/*.jar eureka-server-24.1.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/eureka-server-24.1.1-SNAPSHOT.jar"]