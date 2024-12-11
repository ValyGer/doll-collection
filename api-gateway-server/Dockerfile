FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar api-gateway-server-24.1.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/api-gateway-server-24.1.1-SNAPSHOT.jar"]