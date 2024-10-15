FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar catalog-service-24.1.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/catalog-service-24.1.1-SNAPSHOT.jar"]